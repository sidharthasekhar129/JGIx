package com.example.jgix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ProfileActivity extends AppCompatActivity {
    private ImageView back,profilepic;
    private Button save,edit;
    private TextView mail,name,usn,phone,attendance,stream,
            heademail,headname,headusn,passcode,pass,acyear;
    private EditText year,phonex,newpasscode;
    private UploadTask uploadTask;
    private static final String TAG ="ProfileActivity";
    private static final String keyEmail="mail";
    private static final String keyName="name";
    private static final String keyMobile="mobile";
    private static final String keyUsn="usn";
    private static final String keyStream="stream";
    private static final String keypasscode="passcode";
    private static final String keypassword="password";
    private static final String keyacyear="AcademicYear";
    private FirebaseAuth mAuth;
    private static final String key_pplink="PPLink1";
    private StorageReference mStorageRef;
    private Uri imageUri;
    String usnx;
    String imageurl;
    ProgressDialog progressDialog;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);



        mStorageRef = FirebaseStorage.getInstance().getReference("Images");
        back=(ImageView)findViewById(R.id.back);
        profilepic=(ImageView)findViewById(R.id.profilepic);



        mail=(TextView)findViewById(R.id.mail);
        name=(TextView)findViewById(R.id.name);
        usn=(TextView)findViewById(R.id.usn);
        phone=(TextView)findViewById(R.id.mobno);
        attendance=(TextView)findViewById(R.id.attendance);
        stream=(TextView)findViewById(R.id.stream);



        acyear=(TextView)findViewById(R.id.acyear);

        heademail=(TextView)findViewById(R.id.heademail);
        headname=(TextView)findViewById(R.id.headename);
        headusn=(TextView)findViewById(R.id.headusn);

        passcode=(TextView)findViewById(R.id.passcode);
        pass=(TextView)findViewById(R.id.pass);

        phonex=(EditText)findViewById(R.id.phonex);
        year=(EditText)findViewById(R.id.year);
        newpasscode=(EditText)findViewById(R.id.newpasscode);


        edit=(Button)findViewById(R.id.edit);
        save=(Button)findViewById(R.id.save);




        profilepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // filechooser();
                //fileuploader();
                selectImage();
            }
        });
         edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save.setVisibility(View.VISIBLE);
                phonex.setVisibility(View.VISIBLE);
                year.setVisibility(View.VISIBLE);
                newpasscode.setVisibility(View.VISIBLE);

            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save.setVisibility(View.GONE);
                phonex.setVisibility(View.GONE);
                year.setVisibility(View.GONE);
                newpasscode.setVisibility(View.GONE);

                String academicyear=year.getText().toString();
                String newpass=newpasscode.getText().toString();
                String newphone=phonex.getText().toString();

                if (academicyear.isEmpty() || academicyear.length()<9)
                {
                    year.setError("Enter proper date");
                    year.requestFocus();
                    return;
                }
                if (newphone.isEmpty() || newphone.length()<10)
                {
                    phonex.setError("Enter proper phone no.");
                    phonex.requestFocus();
                    return;
                }
                if (newpass.isEmpty() || newpass.length()<4)
                {
                    newpasscode.setError("Enter proper passcode");
                    newpasscode.requestFocus();
                    return;
                }

                String mailget=getIntent().getStringExtra("mail");
                DocumentReference noteref=db.collection(mailget).document("profile");
                noteref.update(keypasscode,newpass);
                noteref.update(keyacyear,academicyear);
                noteref.update(keyMobile,newphone);

                Toast.makeText(getApplicationContext(),"Profile updated sucessfully",Toast.LENGTH_SHORT).show();
                noteref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists())
                        {
                            String emailx=documentSnapshot.getString(keyEmail);
                            String namex=documentSnapshot.getString(keyName);
                            String mobilex=documentSnapshot.getString(keyMobile);
                            usnx=documentSnapshot.getString(keyUsn);
                            String streamx=documentSnapshot.getString(keyStream);
                            String passcodex=documentSnapshot.getString(keypasscode);
                            String passwordx=documentSnapshot.getString(keypassword);
                            String acyear1=documentSnapshot.getString(keyacyear);

                            mail.setText(emailx);
                            name.setText(namex);
                            phone.setText(mobilex);
                            usn.setText(usnx);
                            stream.setText(streamx);
                            acyear.setText(acyear1);

                            heademail.setText(emailx);
                            headname.setText(namex);
                            headusn.setText(usnx);
                            passcode.setText(passcodex);
                            pass.setText(passwordx);
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Document does not exist!",Toast.LENGTH_SHORT).show();

                        }

                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(),"Document does not exist!",Toast.LENGTH_SHORT).show();
                                Log.d(TAG,e.toString());
                            }
                        });

            }
        });



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();


            }
        });




    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        String mailget=getIntent().getStringExtra("mail");
        DocumentReference noteref=db.collection(mailget).document("profile");
        noteref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists())
                {
                    String emailx=documentSnapshot.getString(keyEmail);
                    String namex=documentSnapshot.getString(keyName);
                    String mobilex=documentSnapshot.getString(keyMobile);
                    usnx=documentSnapshot.getString(keyUsn);
                    String streamx=documentSnapshot.getString(keyStream);
                    String passcodex=documentSnapshot.getString(keypasscode);
                    String passwordx=documentSnapshot.getString(keypassword);
                    String acyear1=documentSnapshot.getString(keyacyear);
                    String pplink1=documentSnapshot.getString(key_pplink);

                    mail.setText(emailx);
                    name.setText(namex);
                    phone.setText(mobilex);
                    usn.setText(usnx);
                    stream.setText(streamx);

                    heademail.setText(emailx);
                    headname.setText(namex);
                    headusn.setText(usnx);
                    passcode.setText(passcodex);
                    pass.setText(passwordx);
                    acyear.setText(acyear1);
                    if (pplink1.isEmpty())
                    {
                        Toast.makeText(getApplicationContext(),"Upload a profile pic.",Toast.LENGTH_SHORT).show();
                    }else {


                        //String imageUri = "https://i.imgur.com/tGbaZCY.jpg";
                        // ImageView ivBasicImage = (ImageView) findViewById(R.id.ivBasicImage);
                        Glide.with(getApplicationContext()).load(pplink1).into(profilepic);
                    }


                }
                else {
                    Toast.makeText(getApplicationContext(),"Document does not exist!",Toast.LENGTH_SHORT).show();

                }

            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Document does not exist!",Toast.LENGTH_SHORT).show();
                        Log.d(TAG,e.toString());
                    }
                });
        //  String url="https://firebasestorage.googleapis.com/v0/b/" +
        //"jgifirebase.appspot.com/o/Images%2F17BTRMA042?alt=media&token=7b4fa601-b187-49b0-a15f-1edcaaa01fc6";
        // Glide.with(this)
        //  .load(url)
         //.into(profilepic);
      /*  FirebaseUser user=mAuth.getCurrentUser();

        if (user.isEmailVerified())
        {
            verifyuser.setBackgroundColor(Color.GREEN);
        }
        else {
            verifyuser.setBackgroundColor(Color.RED);
        }*/

    }



    private void selectImage() {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo"))
                {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                    startActivityForResult(intent, 1);
                }
                else if (options[item].equals("Choose from Gallery"))
                {
                    Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);
                }
                else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                File f = new File(Environment.getExternalStorageDirectory().toString());
                for (File temp : f.listFiles()) {
                    if (temp.getName().equals("temp.jpg")) {
                        f = temp;
                        break;
                    }
                }
                try {
                    Bitmap bitmap;
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                    bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(),
                            bitmapOptions);
                    profilepic.setImageBitmap(bitmap);
                    String path = android.os.Environment
                            .getExternalStorageDirectory()
                            + File.separator
                            + "Phoenix" + File.separator + "default";
                    f.delete();
                    OutputStream outFile = null;
                    File file = new File(path, String.valueOf(System.currentTimeMillis()) + ".jpg");
                    try {
                        outFile = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outFile);
                        outFile.flush();
                        outFile.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == 2) {
                Uri selectedImage = data.getData();
                profilepic.setImageURI(selectedImage);
                final StorageReference riversRef = mStorageRef.child(usnx);

                try {
                    uploadTask =  riversRef.putFile(selectedImage);
                }
                catch (Exception e)
                { }

                progressDialog=new ProgressDialog(this);
                progressDialog.setMax(100);
                progressDialog.setMessage("Uploading...");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.show();
                progressDialog.setCancelable(false);

                uploadTask.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                        double progress=(100.0*taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount();
                        progressDialog.incrementProgressBy((int) progress);
                    }
                });
                uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        progressDialog.dismiss();
                    }
                });
                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                    }
                });

             Task<Uri> uriTask=uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                  @Override
                  public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                      if (!task.isSuccessful())
                      {

                          throw task.getException();
                      }
                      return riversRef.getDownloadUrl();
                  }
              }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                  @Override
                  public void onComplete(@NonNull Task<Uri> task) {
                      if (task.isSuccessful())
                      {
                          Uri durl=task.getResult();
                         String link1=durl.toString();

                          String mailget=getIntent().getStringExtra("mail");
                          DocumentReference noteref=db.collection(mailget).document("profile");
                          noteref.update(key_pplink,link1);
                          Toast.makeText(getApplicationContext(), "Uploaded sucessfully",Toast.LENGTH_SHORT).show();

                      }

                  }
              });


            }
        }
    }
}
