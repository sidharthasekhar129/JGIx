package com.example.jgix;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

public class NotesActivity extends AppCompatActivity {

    private ImageView download1,download2,download3,download4,download5,download6,download7,download8,
            download9,download10,download11,download12,download13,download14,download15,download16,back;

    private TextView notes1,notes2,notes3,notes4,notes5,notes6,notes7,notes8,
            notes9,notes10,notes11,notes12,notes13,notes14,notes15,notes16;

    private LinearLayout LL1,LL2,LL3,LL4,LL5,LL6,LL7,LL8,LL9,LL10,
            LL11,LL12,LL13,LL14,LL15,LL16;

    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private static final String TAG ="NotesActivity";
    private static final String keyNotes1="notes1";
    private static final String keyNotes2="notes2";
    private static final String keyNotes3="notes3";
    private static final String keyNotes4="notes4";
    private static final String keyNotes5="notes5";
    private static final String keyNotes6="notes6";
    private static final String keyNotes7="notes7";
    private static final String keyNotes8="notes8";
    private static final String keyNotes9="notes9";
    private static final String keyNotes10="notes10";
    private static final String keyNotes11="notes11";
    private static final String keyNotes12="notes12";
    private static final String keyNotes13="notes13";
    private static final String keyNotes14="notes14";
    private static final String keyNotes15="notes15";
    private static final String keyNotes16="notes16";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        back=(ImageView)findViewById(R.id.back);
        download1=(ImageView)findViewById(R.id.download1);
        download2=(ImageView)findViewById(R.id.download2);
        download3=(ImageView)findViewById(R.id.download3);
        download4=(ImageView)findViewById(R.id.download4);
        download5=(ImageView)findViewById(R.id.download5);
        download6=(ImageView)findViewById(R.id.download6);
        download7=(ImageView)findViewById(R.id.download7);
        download8=(ImageView)findViewById(R.id.download8);
        download9=(ImageView)findViewById(R.id.download9);
        download10=(ImageView)findViewById(R.id.download10);
        download11=(ImageView)findViewById(R.id.download11);
        download12=(ImageView)findViewById(R.id.download12);
        download13=(ImageView)findViewById(R.id.download13);
        download14=(ImageView)findViewById(R.id.download14);
        download15=(ImageView)findViewById(R.id.download15);
        download16=(ImageView)findViewById(R.id.download16);

        notes1=(TextView)findViewById(R.id.notes1);
        notes2=(TextView)findViewById(R.id.notes2);
        notes3=(TextView)findViewById(R.id.notes3);
        notes4=(TextView)findViewById(R.id.notes4);
        notes5=(TextView)findViewById(R.id.notes5);
        notes6=(TextView)findViewById(R.id.notes6);
        notes7=(TextView)findViewById(R.id.notes7);
        notes8=(TextView)findViewById(R.id.notes8);
        notes9=(TextView)findViewById(R.id.notes9);
        notes10=(TextView)findViewById(R.id.notes10);
        notes11=(TextView)findViewById(R.id.notes11);
        notes12=(TextView)findViewById(R.id.notes12);
        notes13=(TextView)findViewById(R.id.notes13);
        notes14=(TextView)findViewById(R.id.notes14);
        notes15=(TextView)findViewById(R.id.notes15);
        notes16=(TextView)findViewById(R.id.notes16);

        LL1=(LinearLayout)findViewById(R.id.LL1);
        LL2=(LinearLayout)findViewById(R.id.LL2);
        LL3=(LinearLayout)findViewById(R.id.LL3);
        LL4=(LinearLayout)findViewById(R.id.LL4);
        LL5=(LinearLayout)findViewById(R.id.LL5);
        LL6=(LinearLayout)findViewById(R.id.LL6);
        LL7=(LinearLayout)findViewById(R.id.LL7);
        LL8=(LinearLayout)findViewById(R.id.LL8);
        LL9=(LinearLayout)findViewById(R.id.LL9);
        LL10=(LinearLayout)findViewById(R.id.LL10);
        LL11=(LinearLayout)findViewById(R.id.LL11);
        LL12=(LinearLayout)findViewById(R.id.LL12);
        LL13=(LinearLayout)findViewById(R.id.LL13);
        LL14=(LinearLayout)findViewById(R.id.LL14);
        LL15=(LinearLayout)findViewById(R.id.LL15);
        LL16=(LinearLayout)findViewById(R.id.LL16);


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
}
