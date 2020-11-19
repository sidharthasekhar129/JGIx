package com.example.jgix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Events extends AppCompatActivity {
    private ImageView back,img1,img2,img3,img4,img5,img6;
    private static final String TAG ="Events";
    private static final String keyUpcoming1="upcomingpic1";
    private static final String keyUpcoming2="upcomingpic2";
    private static final String keyUpcoming3="upcomingpic3";

    private static final String keyRecent1="recent1";
    private static final String keyRecent2="recent2";
    private static final String keyRecent3="recent3";

    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        back=(ImageView)findViewById(R.id.back);
        img1=(ImageView)findViewById(R.id.img1);
        img2=(ImageView)findViewById(R.id.img2);
        img3=(ImageView)findViewById(R.id.img3);
        img4=(ImageView)findViewById(R.id.img4);
        img5=(ImageView)findViewById(R.id.img5);


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

        DocumentReference noteref=db.collection("Events").document("Upcoming");
        noteref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists())
                {
                    String link1=documentSnapshot.getString(keyUpcoming1);
                    String link2=documentSnapshot.getString(keyUpcoming2);
                    String link3=documentSnapshot.getString(keyUpcoming3);

                    if (!link1.isEmpty())
                    {
                        img1.setVisibility(View.VISIBLE);
                        Glide.with(getApplicationContext()).load(link1).into(img1);

                    }
                    else{
                        img1.setVisibility(View.GONE);
                    }
                    if (!link2.isEmpty())
                    {
                        img2.setVisibility(View.VISIBLE);
                        Glide.with(getApplicationContext()).load(link2).into(img2);

                    }
                    else{
                        img2.setVisibility(View.GONE);
                    }
                    if (!link3.isEmpty())
                    {
                        img3.setVisibility(View.VISIBLE);
                        Glide.with(getApplicationContext()).load(link3).into(img3);

                    }
                    else{
                        img3.setVisibility(View.GONE);
                    }


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

        DocumentReference noteref1=db.collection("Events").document("Recent");
        noteref1.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists())
                {

                    String link4=documentSnapshot.getString(keyRecent1);
                    String link5=documentSnapshot.getString(keyRecent2);
                    String link6=documentSnapshot.getString(keyRecent3);



                    img4.setVisibility(View.VISIBLE);
                        Glide.with(getApplicationContext()).load(link4).into(img4);


                        img5.setVisibility(View.VISIBLE);
                        Glide.with(getApplicationContext()).load(link5).into(img5);








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
