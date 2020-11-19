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

public class BugreportActivity extends AppCompatActivity {
    private EditText nameorusn,what,more,where;
    private Button send;
    private ImageView back;
    private static final String TAG="BugreportActivity";

    private static final String Key_name="name/usn";
    private static final String Key_what="whathappend";
    private static final String Key_where="where";
    private static final String Key_description="description";
    private ProgressBar progressBar;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bugreport);
        nameorusn=(EditText)findViewById(R.id.nameorusn);
        what=(EditText)findViewById(R.id.topic);
        more=(EditText)findViewById(R.id.more);
        where=(EditText)findViewById(R.id.updateplace);
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
                String subject=what.getText().toString();
                String more1=more.getText().toString();
                String update=where.getText().toString();

                if (name1.isEmpty())
                {
                    nameorusn.setError("Enter Data");
                    nameorusn.requestFocus();
                    return;
                }
                if (subject.isEmpty())
                {
                    what.setError("Enter Data");
                    what.requestFocus();
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
                    where.setError("Enter Data");
                    where.requestFocus();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                Map<String, Object> bug=new HashMap<>();
                bug.put(Key_name,name1);
                bug.put(Key_what,subject);
                bug.put(Key_where,more1);
                bug.put(Key_description,update);

                db.collection("BugReport").document(name).set(bug)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getApplicationContext(),"Report send sucessfully",Toast.LENGTH_SHORT).show();
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
