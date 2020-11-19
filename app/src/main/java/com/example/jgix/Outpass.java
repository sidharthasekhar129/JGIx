package com.example.jgix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Outpass extends AppCompatActivity {
    private EditText POV,CStudent,CParent,
            TOut,TIn,DFrom,DTo;
    private Button checkstatus,Send;
    private ImageView back;
    private TextView Name,Usn,namex,usnx,povx,scontactx,pcontactx,
            itimex,otimex,odatex,idatex,outpassno,status;
    private static final String TAG="Outpass";

    private static final String Key_pov="pov";
    private static final String Key_name="name";

    private static final String Key_usn="usn";
    private static final String Key_cstudent="cstudent";
    private static final String Key_cparent="cparent";
    private static final String Key_tout="tout";
    private static final String Key_tin="tin";
    private static final String Key_dfrom="dfrom";
    private static final String Key_dto="fto";
    private static final String Key_status="status";
    private static final String Key_outpassno="outpassno";
    private FirebaseFirestore db=FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outpass);

        back=(ImageView)findViewById(R.id.back);

        Name=(TextView)findViewById(R.id.name);
        Usn=(TextView)findViewById(R.id.usn);

        namex=(TextView)findViewById(R.id.namex);
        usnx=(TextView)findViewById(R.id.usnx);
        povx=(TextView)findViewById(R.id.povx);
        scontactx=(TextView)findViewById(R.id.scontactx);
        pcontactx=(TextView)findViewById(R.id.pcontactx);
        itimex=(TextView)findViewById(R.id.itimex);
        otimex=(TextView)findViewById(R.id.otimex);
        idatex=(TextView)findViewById(R.id.idatex);
        odatex=(TextView)findViewById(R.id.odatex);
        outpassno=(TextView)findViewById(R.id.outpassno);
        status=(TextView)findViewById(R.id.statusx);

        POV=(EditText)findViewById(R.id.pov);
        CStudent=(EditText)findViewById(R.id.scontact);
        CParent=(EditText)findViewById(R.id.pcontact);
        TOut=(EditText)findViewById(R.id.outtime);
        TIn=(EditText)findViewById(R.id.intime);
        DFrom=(EditText)findViewById(R.id.datefrom);
        DTo=(EditText)findViewById(R.id.dateto);





        Send=(Button)findViewById(R.id.send);
        checkstatus=(Button)findViewById(R.id.checkstatus);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        final String namexx=getIntent().getStringExtra("name");
        final String usnxx=getIntent().getStringExtra("usn");
        Name.setText(namexx);
        Usn.setText(usnxx);

        final CardView cardx=(CardView)findViewById(R.id.cardx);




        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name=Name.getText().toString();
                String usn=Usn.getText().toString();
                String pov=POV.getText().toString();
                String cstudent=CStudent.getText().toString();
                String cparent=CParent.getText().toString();
                String tout=TOut.getText().toString();
                String tin=TIn.getText().toString();
                String dfrom=DFrom.getText().toString();
                String dto=DTo.getText().toString();
                String status="waiting";
                String outpassno="0000";

                if (name.isEmpty())
                {
                    Name.setError("Required feild");
                    Name.requestFocus();
                    return;
                }
                if (usn.isEmpty())
                {
                    Usn.setError("Required feild");
                    Usn.requestFocus();
                    return;
                }
                if (pov.isEmpty())
                {
                    POV.setError("Required feild");
                    POV.requestFocus();
                    return;
                }
                if (cstudent.isEmpty() )
                {
                    CStudent.setError("Required feild");
                    CStudent.requestFocus();
                    return;
                }
                if ( cstudent.length()<10)
                {
                    CStudent.setError("Enter valid number");
                    CStudent.requestFocus();
                    return;
                }
                if (cparent.isEmpty() )
                {
                    CParent.setError("Required feild");
                    CParent.requestFocus();
                    return;
                }
                if (cparent.length()<10)
                {
                    CParent.setError("Enter valid number");
                    CParent.requestFocus();
                    return;
                }
                if (tout.isEmpty())
                {
                    TOut.setError("Required feild");
                    TOut.requestFocus();
                    return;
                }
                if (tin.isEmpty())
                {
                    TIn.setError("Required feild");
                    TIn.requestFocus();
                    return;
                }
                if (dfrom.isEmpty())
                {
                    DFrom.setError("Required feild");
                    DFrom.requestFocus();
                    return;
                }
                if (dto.isEmpty())
                {
                    DTo.setError("Required feild");
                    DTo.requestFocus();
                    return;
                }

                Map<String, Object> outpass=new HashMap<>();
                outpass.put(Key_name,name);
                outpass.put(Key_usn,usn);
                outpass.put(Key_pov,pov);
                outpass.put(Key_cstudent,cstudent);
                outpass.put(Key_cparent,cparent);
                outpass.put(Key_tout,tout);
                outpass.put(Key_tin,tin);
                outpass.put(Key_dfrom,dfrom);
                outpass.put(Key_dto,dto);
                outpass.put(Key_status,status);
                outpass.put(Key_outpassno,outpassno);

                db.collection("Outpass").document(usnxx).set(outpass)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getApplicationContext(),"Outpass request send",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(),"Did not send data",Toast.LENGTH_SHORT).show();
                                Log.d(TAG,e.toString());
                            }
                        });


            }
        });

        checkstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                cardx.setVisibility(View.VISIBLE);
                DocumentReference noteref=db.collection("Outpass").document(usnxx);
                noteref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists())
                        {
                            String serialno=documentSnapshot.getString(Key_outpassno);
                            String name=documentSnapshot.getString(Key_name);
                            String usn=documentSnapshot.getString(Key_usn);
                            String pov=documentSnapshot.getString(Key_pov);
                            String contactstudent=documentSnapshot.getString(Key_cstudent);
                            String contactparent=documentSnapshot.getString(Key_cparent);
                            String outtime=documentSnapshot.getString(Key_tout);
                            String intime=documentSnapshot.getString(Key_tin);
                            String outdate=documentSnapshot.getString(Key_dfrom);
                            String indate=documentSnapshot.getString(Key_dto);
                            String statusx=documentSnapshot.getString(Key_status);





                            outpassno.setText(serialno);
                            namex.setText(name);
                            usnx.setText(usn);
                            povx.setText(pov);
                            scontactx.setText(contactstudent);
                            pcontactx.setText(contactparent);
                            itimex.setText(intime);
                            otimex.setText(outtime);
                            idatex.setText(indate);
                            odatex.setText(outdate);
                            status.setText(statusx);



                        }
                        else {
                            Toast.makeText(getApplicationContext(),"U didn't order yet",Toast.LENGTH_SHORT).show();

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

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


}
