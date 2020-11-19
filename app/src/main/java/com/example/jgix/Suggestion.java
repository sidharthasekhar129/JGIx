package com.example.jgix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Suggestion extends AppCompatActivity {
    private EditText nameorusn,topic,more,updateplace;
    private Button send;
    private ImageView back;
    private static final String TAG="Suggestion";
    private ProgressBar progressBar;

    private static final String Key_name="name/usn";
    private static final String Key_topic="topic";
    private static final String Key_updateplace="updateplace";
    private static final String Key_description="description";

    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion);
        nameorusn=(EditText)findViewById(R.id.nameorusn);
        topic=(EditText)findViewById(R.id.topic);
        more=(EditText)findViewById(R.id.more);
        updateplace=(EditText)findViewById(R.id.updateplace);
        send=(Button)findViewById(R.id.send);
        progressBar=(ProgressBar)findViewById(R.id.progressbar);
        back=(ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name=nameorusn.getText().toString();
                String name1=name.toUpperCase();
                String subject=topic.getText().toString();
                String more1=more.getText().toString();
                String update=updateplace.getText().toString();

                if (name1.isEmpty())
                {
                    nameorusn.setError("Enter Data");
                    nameorusn.requestFocus();
                    return;
                }
                if (subject.isEmpty())
                {
                    topic.setError("Enter Data");
                    topic.requestFocus();
                    return;
                }
                if (more1.isEmpty())
                {
                    more.setError("Enter Data");
                    more.requestFocus();
                    return;
                }
                if (update.isEmpty())
                {
                    updateplace.setError("Enter Data");
                    updateplace.requestFocus();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                Map<String, Object> suggest=new HashMap<>();
                suggest.put(Key_name,name1);
                suggest.put(Key_topic,subject);
                suggest.put(Key_updateplace,more1);
                suggest.put(Key_description,update);

                db.collection("Suggestion").document(name).set(suggest)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getApplicationContext(),"Idea send sucessfully",Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(),"Can't  send",Toast.LENGTH_SHORT).show();
                                Log.d(TAG,e.toString());
                            }
                        });


            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

