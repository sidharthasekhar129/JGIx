package com.example.jgix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Settings extends AppCompatActivity {
    private ImageView back;
    private Switch eandp,fingerprint,storege,text,push,otp,email;
    private static final String TAG="Settings";
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private static final String keyFingerprintStatus="fpstatus";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        eandp=(Switch)findViewById(R.id.eandp);
        fingerprint=(Switch)findViewById(R.id.fingerprint);
        storege=(Switch)findViewById(R.id.storage);
        text=(Switch)findViewById(R.id.textnotification);
        push=(Switch)findViewById(R.id.pushnotification);

        otp=(Switch)findViewById(R.id.otp);
        email=(Switch)findViewById(R.id.email);

        eandp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked==true)
                {
                    eandp.setChecked(true);

                }
                else {
                    eandp.setChecked(true);
                    Toast.makeText(Settings.this, "Can't Disable", Toast.LENGTH_SHORT).show();

                }
            }
        });
        fingerprint.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked==true)
                {



                    String mail=getIntent().getStringExtra("mail");
                    DocumentReference noteref=db.collection(mail).document("profile");
                    noteref.update(keyFingerprintStatus,"enable");


                }
                else {

                    String mail=getIntent().getStringExtra("mail");
                    DocumentReference noteref=db.collection(mail).document("profile");
                    noteref.update(keyFingerprintStatus,"dissable");


                }
            }
        });
        storege.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked==true)
                {
                    storege.setChecked(true);

                }
                else {
                    storege.setChecked(true);
                    Toast.makeText(Settings.this, "Can't Disable", Toast.LENGTH_SHORT).show();

                }

            }
        });
        text.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


            }
        });
        push.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked==true)
                {


                }
                else {


                }

            }
        });
        otp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked==true)
                {
                    otp.setChecked(true);

                }
                else {
                    otp.setChecked(true);
                    Toast.makeText(Settings.this, "Can't Disable", Toast.LENGTH_SHORT).show();
                }
            }
        });
        email.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked==true)
                {
                    email.setChecked(true);

                }
                else {
                    email.setChecked(true);
                    Toast.makeText(Settings.this, "Can't Disable", Toast.LENGTH_SHORT).show();
                }


            }
        });

        back=(ImageView)findViewById(R.id.back);
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

        email.setChecked(true);
        otp.setChecked(true);
        eandp.setChecked(true);
        push.setChecked(true);
        storege.setChecked(true);


    }
}
