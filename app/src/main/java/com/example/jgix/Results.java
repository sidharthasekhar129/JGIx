package com.example.jgix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Results extends AppCompatActivity {
    private ImageView back;
    private ProgressBar progressBar;
    private TextView subject1,subject2,subject3,subject4,
            subject5,subject6,
            result1,result2,result3,result4,result5,result6,
            totalmark,fullmark;
    private static final String TAG ="Results";
    private static final String keySubject1="a1";
    private static final String keySubject2="b1";
    private static final String keySubject3="c1";
    private static final String keySubject4="d1";
    private static final String keySubject5="e1";
    private static final String keySubject6="f1";

    private static final String keyResult1="a11";
    private static final String keyResult2="b11";
    private static final String keyResult3="c11";
    private static final String keyResult4="d11";
    private static final String keyResult5="e11";
    private static final String keyResult6="f11";

    private static final String keyFullmark="fullmarks";
    String usn;



    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        progressBar=(ProgressBar)findViewById(R.id.progressbar);


        subject1=(TextView)findViewById(R.id.subject1);
        subject2=(TextView)findViewById(R.id.subject2);
        subject3=(TextView)findViewById(R.id.subject3);
        subject4=(TextView)findViewById(R.id.subject4);
        subject5=(TextView)findViewById(R.id.subject5);
        subject6=(TextView)findViewById(R.id.subject6);

        result1=(TextView)findViewById(R.id.result1);
        result2=(TextView)findViewById(R.id.result2);
        result3=(TextView)findViewById(R.id.result3);
        result4=(TextView)findViewById(R.id.result4);
        result5=(TextView)findViewById(R.id.result5);
        result6=(TextView)findViewById(R.id.result6);
        totalmark=(TextView)findViewById(R.id.totalmarks);

        fullmark=(TextView)findViewById(R.id.fullmarks);
        usn=getIntent().getStringExtra("usn");




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
        progressBar.setVisibility(View.VISIBLE);
        DocumentReference noteref=db.collection(usn).document("results");
        noteref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists())
                {
                    String subject1x=documentSnapshot.getString(keySubject1);
                    String subject2x=documentSnapshot.getString(keySubject2);
                    String subject3x=documentSnapshot.getString(keySubject3);
                    String subject4x=documentSnapshot.getString(keySubject4);
                    String subject5x=documentSnapshot.getString(keySubject5);
                    String subject6x=documentSnapshot.getString(keySubject6);

                    String result1x=documentSnapshot.getString(keyResult1);
                    String result2x=documentSnapshot.getString(keyResult2);
                    String result3x=documentSnapshot.getString(keyResult3);
                    String result4x=documentSnapshot.getString(keyResult4);
                    String result5x=documentSnapshot.getString(keyResult5);
                    String result6x=documentSnapshot.getString(keyResult6);

                    String fullmarkx=documentSnapshot.getString(keyFullmark);


                    subject1.setText(subject1x);
                    subject2.setText(subject2x);
                    subject3.setText(subject3x);
                    subject4.setText(subject4x);
                    subject5.setText(subject5x);
                    subject6.setText(subject6x);

                    result1.setText(result1x);
                    result2.setText(result2x);
                    result3.setText(result3x);
                    result4.setText(result4x);
                    result5.setText(result5x);
                    result6.setText(result6x);

                    fullmark.setText(fullmarkx);
                    progressBar.setVisibility(View.GONE);

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
    }
}
