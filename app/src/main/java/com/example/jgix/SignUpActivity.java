package com.example.jgix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {
    private static final String TAG="SignUpActivity";
    private static final String Key_mail="mail";
    private static final String Key_name="name";
    private static final String Key_usn="usn";
    private static final String Key_mobile="mobile";
    private static final String Key_stream="stream";
    private static final String Key_passcode="passcode";
    private static final String Key_password="password";
    private static final String keyacyear="AcademicYear";
    private static final String key_pplink="PPLink1";

    private FirebaseFirestore db=FirebaseFirestore.getInstance();


    private TextView login;
    private Button signup;
    private ProgressBar progressBar;
    private EditText email,key,rkey,mobile,usn,stream,name,passcode;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        login=(TextView)findViewById(R.id.login);
        progressBar=(ProgressBar)findViewById(R.id.progressbar);
        signup=(Button)findViewById(R.id.signup);


        email=(EditText)findViewById(R.id.ed1);
        key=(EditText)findViewById(R.id.ed2);
        rkey=(EditText)findViewById(R.id.ed3);
        name=(EditText)findViewById(R.id.ed4);
        usn=(EditText)findViewById(R.id.ed5);
        mobile=(EditText)findViewById(R.id.ed6);
        stream=(EditText)findViewById(R.id.ed7);
        passcode=(EditText)findViewById(R.id.passcode);




        mAuth = FirebaseAuth.getInstance();



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registeruser();
            }
        });



    }

    private void registeruser()
    {
        final String username=email.getText().toString().trim();
        final String password=key.getText().toString().trim();

        String mkey=rkey.getText().toString().trim();
        final String names =name.getText().toString();
        final String roll=usn.getText().toString().trim();
        final String rollno=roll.toUpperCase();
        final String phone =mobile.getText().toString().trim();
        final String course1=stream.getText().toString().trim();
        final String course=course1.toUpperCase();
        final String passcode1=passcode.getText().toString();

        final String acyear="x";
        final String PPlink1="";


        if (username.isEmpty())
        {
            email.setError("Email is required!");
            email.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(username).matches())
        {
            email.setError("Valid Email is required!");
            email.requestFocus();
            return;
        }
        if (password.isEmpty())
        {
            key.setError("Password is required!");
            key.requestFocus();
            return;
        }

        if (password.length()<6)
        {
            key.setError("Minimum length is 6");
            key.requestFocus();
            return;
        }
        if (!mkey.equals(password))
        {
            rkey.setError("Password did not match");
            rkey.requestFocus();
            return;
        }
        if (passcode1.isEmpty())
        {
            passcode.setError("Passcode is required ");
            passcode.requestFocus();
            return;
        }
        if (names.isEmpty())
        {
            name.setError("Enter a name");
            name.requestFocus();
            return;
        }
        if (rollno.isEmpty() || (rollno.length() <10))
        {
            usn.setError("Enter a valid USN");
            usn.requestFocus();
            return;
        }
        if (phone.isEmpty() || (phone.length() <10))
        {
            mobile.setError(" Enter a valid mobile number");
            mobile.requestFocus();
            return;
        }

        if (course.isEmpty())
        {
            stream.setError("Stream is required");
            stream.requestFocus();
            return;
        }


        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(username,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful())
                        {
                            Map<String, Object> note=new HashMap<>();
                            note.put(Key_mail,username);
                            note.put(Key_name,names);
                            note.put(Key_usn,rollno);
                            note.put(Key_mobile,phone);
                            note.put(Key_stream,course);
                            note.put(Key_passcode,passcode1);
                            note.put(Key_password,password);
                            note.put(keyacyear,acyear);
                            note.put(key_pplink,PPlink1);

                            db.collection(username).document("profile").set(note)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(getApplicationContext(),"Database created",Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(getApplicationContext(),"Cant create database",Toast.LENGTH_SHORT).show();
                                            Log.d(TAG,e.toString());
                                        }
                                    });

                            Intent intent=new Intent(getApplicationContext(),Main2Activity.class);
                            intent.putExtra("mail",username);
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(),"User registered sucessfully.",Toast.LENGTH_SHORT).show();
                            finish();

                        }
                        else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException)
                            {
                                Toast.makeText(getApplicationContext(),"User already exist.",Toast.LENGTH_SHORT).show();

                            }
                            else {
                                Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                            }
                        }
                    }
                });




    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}

