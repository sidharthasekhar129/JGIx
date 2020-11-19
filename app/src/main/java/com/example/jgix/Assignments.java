package com.example.jgix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Assignments extends AppCompatActivity {
    private ImageView back;
    private CardView card1,card2,card3,card4,card5,card6;

    private static final String TAG="Assignments";

    private static final String Key_subnamr="subname";
    private static final String Key_topicname="topicname";
    private static final String Key_lectname="lectname";
    private static final String Key_message="message";
    private static final String Key_deadline="deadline";
    private FirebaseFirestore db=FirebaseFirestore.getInstance();

    private TextView subname,topicname,lectname,message,deadline
            ,subname2,topicname2,lectname2,message2,deadline2,
            subname3,topicname3,lectname3,message3,deadline3,
            subname4,topicname4,lectname4,message4,deadline4,
            subname5,topicname5,lectname5,message5,deadline5,
            subname6,topicname6,lectname6,message6,deadline6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignments);
        back=(ImageView)findViewById(R.id.back);

        subname=(TextView)findViewById(R.id.subname);
        topicname=(TextView)findViewById(R.id.topicname);
        lectname=(TextView)findViewById(R.id.lectname);
        message=(TextView)findViewById(R.id.message);
        deadline=(TextView)findViewById(R.id.deadline);

        subname2=(TextView)findViewById(R.id.subname2);
        topicname2=(TextView)findViewById(R.id.topicname2);
        lectname2=(TextView)findViewById(R.id.lectname2);
        message2=(TextView)findViewById(R.id.message2);
        deadline2=(TextView)findViewById(R.id.deadline2);

        subname3=(TextView)findViewById(R.id.subname3);
        topicname3=(TextView)findViewById(R.id.topicname3);
        lectname3=(TextView)findViewById(R.id.lectname3);
        message3=(TextView)findViewById(R.id.message3);
        deadline3=(TextView)findViewById(R.id.deadline3);

        subname4=(TextView)findViewById(R.id.subname4);
        topicname4=(TextView)findViewById(R.id.topicname4);
        lectname4=(TextView)findViewById(R.id.lectname4);
        message4=(TextView)findViewById(R.id.message4);
        deadline4=(TextView)findViewById(R.id.deadline4);

        subname5=(TextView)findViewById(R.id.subname5);
        topicname5=(TextView)findViewById(R.id.topicname5);
        lectname5=(TextView)findViewById(R.id.lectname5);
        message5=(TextView)findViewById(R.id.message5);
        deadline5=(TextView)findViewById(R.id.deadline5);

        subname6=(TextView)findViewById(R.id.subname6);
        topicname6=(TextView)findViewById(R.id.topicname6);
        lectname6=(TextView)findViewById(R.id.lectname6);
        message6=(TextView)findViewById(R.id.message6);
        deadline6=(TextView)findViewById(R.id.deadline6);

        card1=(CardView)findViewById(R.id.card1);
        card2=(CardView)findViewById(R.id.card2);
        card3=(CardView)findViewById(R.id.card3);
        card4=(CardView)findViewById(R.id.card4);
        card5=(CardView)findViewById(R.id.card5);
        card6=(CardView)findViewById(R.id.card6);


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
        String acyear=getIntent().getStringExtra("acyear");
        String stream=getIntent().getStringExtra("stream");

        DocumentReference noteref=db.collection("Assignment").document(stream).collection(acyear).document("01");
        noteref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists())
                {


                    card1.setVisibility(View.VISIBLE);
                    String subjectname=documentSnapshot.getString(Key_subnamr);
                    String topicname1=documentSnapshot.getString(Key_topicname);
                    String lecturerame=documentSnapshot.getString(Key_lectname);
                    String deadline1=documentSnapshot.getString(Key_deadline);
                    String message1=documentSnapshot.getString(Key_message);
                    subname.setText(subjectname);
                    topicname.setText(topicname1);
                    lectname.setText(lecturerame);
                    message.setText(message1);
                    deadline.setText(deadline1);



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
        DocumentReference noteref1=db.collection("Assignment").document(stream).collection(acyear).document("02");
        noteref1.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists())
                {


                    card2.setVisibility(View.VISIBLE);
                    String subjectname2=documentSnapshot.getString(Key_subnamr);
                    String topicname22=documentSnapshot.getString(Key_topicname);
                    String lecturerame2=documentSnapshot.getString(Key_lectname);
                    String deadline22=documentSnapshot.getString(Key_deadline);
                    String message22=documentSnapshot.getString(Key_message);
                    subname2.setText(subjectname2);
                    topicname2.setText(topicname22);
                    lectname2.setText(lecturerame2);
                    message2.setText(message22);
                    deadline2.setText(deadline22);



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
        DocumentReference noteref2=db.collection("Assignment").document(stream).collection(acyear).document("03");
        noteref2.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists())
                {


                    card3.setVisibility(View.VISIBLE);
                    String subjectname3=documentSnapshot.getString(Key_subnamr);
                    String topicname33=documentSnapshot.getString(Key_topicname);
                    String lecturerame3=documentSnapshot.getString(Key_lectname);
                    String deadline33=documentSnapshot.getString(Key_deadline);
                    String message33=documentSnapshot.getString(Key_message);
                    subname3.setText(subjectname3);
                    topicname3.setText(topicname33);
                    lectname3.setText(lecturerame3);
                    message3.setText(message33);
                    deadline3.setText(deadline33);



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

        DocumentReference noteref4=db.collection("Assignment").document(stream).collection(acyear).document("04");
        noteref4.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists())
                {


                    card4.setVisibility(View.VISIBLE);
                    String subjectname4=documentSnapshot.getString(Key_subnamr);
                    String topicname44=documentSnapshot.getString(Key_topicname);
                    String lecturerame4=documentSnapshot.getString(Key_lectname);
                    String deadline44=documentSnapshot.getString(Key_deadline);
                    String message44=documentSnapshot.getString(Key_message);
                    subname4.setText(subjectname4);
                    topicname4.setText(topicname44);
                    lectname4.setText(lecturerame4);
                    message4.setText(message44);
                    deadline4.setText(deadline44);



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

        DocumentReference noteref5=db.collection("Assignment").document(stream).collection(acyear).document("05");
        noteref5.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists())
                {


                    card5.setVisibility(View.VISIBLE);
                    String subjectname5=documentSnapshot.getString(Key_subnamr);
                    String topicname55=documentSnapshot.getString(Key_topicname);
                    String lecturerame5=documentSnapshot.getString(Key_lectname);
                    String deadline55=documentSnapshot.getString(Key_deadline);
                    String message55=documentSnapshot.getString(Key_message);
                    subname5.setText(subjectname5);
                    topicname5.setText(topicname55);
                    lectname5.setText(lecturerame5);
                    message5.setText(message55);
                    deadline5.setText(deadline55);



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
        DocumentReference noteref6=db.collection("Assignment").document(stream).collection(acyear).document("06");
        noteref6.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists())
                {


                    card6.setVisibility(View.VISIBLE);
                    String subjectname6=documentSnapshot.getString(Key_subnamr);
                    String topicname66=documentSnapshot.getString(Key_topicname);
                    String lecturerame6=documentSnapshot.getString(Key_lectname);
                    String deadline66=documentSnapshot.getString(Key_deadline);
                    String message66=documentSnapshot.getString(Key_message);
                    subname6.setText(subjectname6);
                    topicname6.setText(topicname66);
                    lectname6.setText(lecturerame6);
                    message.setText(message66);
                    deadline6.setText(deadline66);



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
