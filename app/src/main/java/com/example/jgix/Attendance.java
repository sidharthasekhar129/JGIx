package com.example.jgix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Attendance extends AppCompatActivity {
    private ImageView back,pic;
    private TextView name,usn,last2,last3,last,current,overall,remarks,
            sub1,sub2,sub3,sub4,sub5,sub6;

    private static final String TAG ="Attendance";
    private static final String keyUsn="usn";
    private static final String keyName="name";


    private static final String keyLast2="last2";
    private static final String keyLast3="last3";
    private static final String keyLast="last";
    private static final String keyCurrent="current";
    private static final String keyOverall="overall";
    private static final String keyRemarks="remarks";
    private static final String key_pplink="PPLink1";
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    String usn1 ;
    String name1 ;
    String mail ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        name=(TextView)findViewById(R.id.name);
        usn=(TextView)findViewById(R.id.usn);
        last3=(TextView)findViewById(R.id.last3);
        last2=(TextView)findViewById(R.id.last2);
        last=(TextView)findViewById(R.id.last);
        current=(TextView)findViewById(R.id.current);
        overall=(TextView)findViewById(R.id.overall);
        remarks=(TextView)findViewById(R.id.remark);

        sub1=(TextView)findViewById(R.id.sub1);
        sub2=(TextView)findViewById(R.id.sub2);
        sub3=(TextView)findViewById(R.id.sub3);
        sub4=(TextView)findViewById(R.id.sub4);
        sub5=(TextView)findViewById(R.id.sub5);
        sub6=(TextView)findViewById(R.id.sub6);

        final String subcode1="01";
        final  String subcode2="02";
        final String subcode3="03";
        final String subcode4="04";
        final String subcode5="05";
        final  String subcode6="06";

        sub1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AttendanceSubjectwise.class);
                intent.putExtra("usn",usn1);
                intent.putExtra("subcode",subcode1);
                startActivity(intent);
            }
        });
        sub2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(getApplicationContext(),AttendanceSubjectwise.class);
                intent1.putExtra("usn",usn1);
                intent1.putExtra("subcode",subcode2);
                startActivity(intent1);
            }
        });
        sub3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(getApplicationContext(),AttendanceSubjectwise.class);
                intent2.putExtra("usn",usn1);
                intent2.putExtra("subcode",subcode3);
                startActivity(intent2);
            }
        });
        sub4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3=new Intent(getApplicationContext(),AttendanceSubjectwise.class);
                intent3.putExtra("usn",usn1);
                intent3.putExtra("subcode",subcode4);
                startActivity(intent3);
            }
        });
        sub5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AttendanceSubjectwise.class);
                intent.putExtra("usn",usn1);
                intent.putExtra("subcode",subcode5);
                startActivity(intent);
            }
        });
        sub6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AttendanceSubjectwise.class);
                intent.putExtra("usn",usn1);
                intent.putExtra("subcode",subcode6);
                startActivity(intent);
            }
        });


        back=(ImageView)findViewById(R.id.back);
        pic=(ImageView)findViewById(R.id.pic);

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

        usn1=getIntent().getStringExtra("usn");
        name1=getIntent().getStringExtra("name");
        mail=getIntent().getStringExtra("mail");

        name.setText(name1);
        usn.setText(usn1);
       //DocumentReference noteref=db.collection(usn1).document("attendance");

        db.collection(usn1).document("attendance").get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists())
                        {


                            String currentx=documentSnapshot.getString(keyCurrent);
                            String lastx=documentSnapshot.getString(keyLast);
                            String last2x=documentSnapshot.getString(keyLast2);
                            String last3x=documentSnapshot.getString(keyLast3);
                            String overallx=documentSnapshot.getString(keyOverall);
                            String remarksx=documentSnapshot.getString(keyRemarks);


                            current.setText(currentx);
                            last.setText(lastx);
                            last2.setText(last2x);
                            last3.setText(last3x);
                            overall.setText(overallx);
                            remarks.setText(remarksx);



                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Not uploaded yet",Toast.LENGTH_SHORT).show();

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

        DocumentReference noteref1=db.collection(mail).document("profile");
        noteref1.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists())
                {
                    String pplink1=documentSnapshot.getString(key_pplink);

                    if (pplink1.isEmpty())
                    {

                    }else {


                        //String imageUri = "https://i.imgur.com/tGbaZCY.jpg";
                        // ImageView ivBasicImage = (ImageView) findViewById(R.id.ivBasicImage);
                        Glide.with(getApplicationContext())
                                .load(pplink1)

                                 .into(pic);
                    }

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

}
