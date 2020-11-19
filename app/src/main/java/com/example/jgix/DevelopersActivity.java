package com.example.jgix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class DevelopersActivity extends AppCompatActivity {
    private ImageView back,developer1,developer2;
    private TextView name1,name2,stream1,stream2,phone1,phone2;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private static final String TAG ="DevelopersActivity";
    private static final String keyDev1="dev1";
    private static final String keyDev2="dev2";

    private static final String keyName1="name1";
    private static final String keyName2="name2";
    private static final String keyPhone1="phone1";
    private static final String keyPhone2="phone2";
    private static final String keyStream1="stream1";
    private static final String keyStream2="stream2";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developers);

        back=(ImageView)findViewById(R.id.back);
        developer1=(ImageView)findViewById(R.id.developer1);
        developer2=(ImageView)findViewById(R.id.developer2);
        name1=(TextView)findViewById(R.id.name1);
        name2=(TextView)findViewById(R.id.name2);

        stream1=(TextView)findViewById(R.id.stream1);
        stream2=(TextView)findViewById(R.id.stream2);

        phone1=(TextView)findViewById(R.id.phone1);
        phone2=(TextView)findViewById(R.id.phone2);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

        DocumentReference noteref=db.collection("Events").document("Developers");
        noteref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists())
                {
                    String link1=documentSnapshot.getString(keyDev1);
                    String link2=documentSnapshot.getString(keyDev2);

                    String name11=documentSnapshot.getString(keyName1);
                    name1.setText(name11);
                    String name22=documentSnapshot.getString(keyName2);
                    name2.setText(name22);
                    String phone11=documentSnapshot.getString(keyPhone1);
                    phone1.setText(phone11);
                    String phone22=documentSnapshot.getString(keyPhone2);
                    phone2.setText(phone22);
                    String stream11=documentSnapshot.getString(keyStream1);
                    stream1.setText(stream11);
                    String stream22=documentSnapshot.getString(keyStream2);
                    stream2.setText(stream22);


                        Glide.with(getApplicationContext()).load(link1).into(developer1);

                        Glide.with(getApplicationContext()).load(link2).into(developer2);



                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Network problems!",Toast.LENGTH_SHORT).show();
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