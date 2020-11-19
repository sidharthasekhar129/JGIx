package com.example.jgix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class AttendanceSubjectwise extends AppCompatActivity {
    private TextView sname,a,a1,a2,a3,a4,A,A1,A2,A3,A4,
            b,b1,b2,b3,b4,B,B1,B2,B3,B4,
            c,c1,c2,c3,c4,C,C1,C2,C3,C4,
            d,d1,d2,d3,d4,D,D1,D2,D3,D4,
            e,e1,e2,e3,e4,E,E1,E2,E3,E4,
            f,f1,f2,f3,f4,F,F1,F2,F3,F4,
            g,g1,g2,g3,g4,G,G1,G2,G3,G4,
            h,h1,h2,h3,h4,H,H1,H2,H3,H4,
            i,i1,i2,i3,i4,I,I1,I2,I3,I4,
            j,j1,j2,j3,j4,J,J1,J2,J3,J4;

    private Button back;

    private static final String TAG="AttendanceSubjectwise";
    // private ProgressBar progressBar;

    private static final String Key_a="class1";
    private static final String Key_subject="subject";
    private static final String Key_a1="class2";
    private static final String Key_a2="class3";
    private static final String Key_a3="class4";
    private static final String Key_a4="class5";
    private static final String Key_b="class6";
    private static final String Key_b1="class7";
    private static final String Key_b2="class8";
    private static final String Key_b3="class9";
    private static final String Key_b4="class10";
    private static final String Key_c="class11";
    private static final String Key_c1="class12";
    private static final String Key_c2="class13";
    private static final String Key_c3="class14";
    private static final String Key_c4="class15";
    private static final String Key_d="class16";
    private static final String Key_d1="class17";
    private static final String Key_d2="class18";
    private static final String Key_d3="class19";
    private static final String Key_d4="class20";
    private static final String Key_e="class21";
    private static final String Key_e1="class22";
    private static final String Key_e2="class23";
    private static final String Key_e3="class24";
    private static final String Key_e4="class25";
    private static final String Key_f="class26";
    private static final String Key_f1="class27";
    private static final String Key_f2="class28";
    private static final String Key_f3="class29";
    private static final String Key_f4="class30";
    private static final String Key_g="class31";
    private static final String Key_g1="class32";
    private static final String Key_g2="class33";
    private static final String Key_g3="class34";
    private static final String Key_g4="class35";
    private static final String Key_h="class36";
    private static final String Key_h1="class37";
    private static final String Key_h2="class38";
    private static final String Key_h3="class39";
    private static final String Key_h4="class40";
    private static final String Key_i="class41";
    private static final String Key_i1="class42";
    private static final String Key_i2="class43";
    private static final String Key_i3="class44";
    private static final String Key_i4="class45";
    private static final String Key_j="class46";
    private static final String Key_j1="class47";
    private static final String Key_j2="class48";
    private static final String Key_j3="class49";
    private static final String Key_j4="class50";

    private static final String Key_A="class1A";
    private static final String Key_A1="class2A";
    private static final String Key_A2="class3A";
    private static final String Key_A3="class4A";
    private static final String Key_A4="class5A";
    private static final String Key_B="class6B";
    private static final String Key_B1="class7B";
    private static final String Key_B2="class8B";
    private static final String Key_B3="class9B";
    private static final String Key_B4="class10B";
    private static final String Key_C="class11C";
    private static final String Key_C1="class12C";
    private static final String Key_C2="class13C";
    private static final String Key_C3="class14C";
    private static final String Key_C4="class15C";
    private static final String Key_D="class16D";
    private static final String Key_D1="class17D";
    private static final String Key_D2="class18D";
    private static final String Key_D3="class19D";
    private static final String Key_D4="class20D";
    private static final String Key_E="class21E";
    private static final String Key_E1="class22E";
    private static final String Key_E2="class23E";
    private static final String Key_E3="class24E";
    private static final String Key_E4="class25E";
    private static final String Key_F="class26F";
    private static final String Key_F1="class27F";
    private static final String Key_F2="class28F";
    private static final String Key_F3="class29F";
    private static final String Key_F4="class30F";
    private static final String Key_G="class31G";
    private static final String Key_G1="class32G";
    private static final String Key_G2="class33G";
    private static final String Key_G3="class34G";
    private static final String Key_G4="class35G";
    private static final String Key_H="class36H";
    private static final String Key_H1="class37H";
    private static final String Key_H2="class38H";
    private static final String Key_H3="class39H";
    private static final String Key_H4="class40H";
    private static final String Key_I="class41I";
    private static final String Key_I1="class42I";
    private static final String Key_I2="class43I";
    private static final String Key_I3="class44I";
    private static final String Key_I4="class45I";
    private static final String Key_J="class46J";
    private static final String Key_J1="class47J";
    private static final String Key_J2="class48J";
    private static final String Key_J3="class49J";
    private static final String Key_J4="class50J";
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_subjectwise);
        sname=(TextView)findViewById(R.id.sname);
        a=(TextView)findViewById(R.id.a);
        a1=(TextView)findViewById(R.id.a1);
        a2=(TextView)findViewById(R.id.a2);
        a3=(TextView)findViewById(R.id.a3);
        a4=(TextView)findViewById(R.id.a4);

        A=(TextView)findViewById(R.id.A);
        A1=(TextView)findViewById(R.id.A1);
        A2=(TextView)findViewById(R.id.A2);
        A3=(TextView)findViewById(R.id.A3);
        A4=(TextView)findViewById(R.id.A4);

        b=(TextView)findViewById(R.id.b);
        b1=(TextView)findViewById(R.id.b1);
        b2=(TextView)findViewById(R.id.b2);
        b3=(TextView)findViewById(R.id.b3);
        b4=(TextView)findViewById(R.id.b4);

        B=(TextView)findViewById(R.id.B);
        B1=(TextView)findViewById(R.id.B1);
        B2=(TextView)findViewById(R.id.B2);
        B3=(TextView)findViewById(R.id.B3);
        B4=(TextView)findViewById(R.id.B4);

        c=(TextView)findViewById(R.id.c);
        c1=(TextView)findViewById(R.id.c1);
        c2=(TextView)findViewById(R.id.c2);
        c3=(TextView)findViewById(R.id.c3);
        c4=(TextView)findViewById(R.id.c4);

        C=(TextView)findViewById(R.id.C);
        C1=(TextView)findViewById(R.id.C1);
        C2=(TextView)findViewById(R.id.C2);
        C3=(TextView)findViewById(R.id.C3);
        C4=(TextView)findViewById(R.id.C4);

        d=(TextView)findViewById(R.id.d);
        d1=(TextView)findViewById(R.id.d1);
        d2=(TextView)findViewById(R.id.d2);
        d3=(TextView)findViewById(R.id.d3);
        d4=(TextView)findViewById(R.id.d4);

        D=(TextView)findViewById(R.id.D);
        D1=(TextView)findViewById(R.id.D1);
        D2=(TextView)findViewById(R.id.D2);
        D3=(TextView)findViewById(R.id.D3);
        D4=(TextView)findViewById(R.id.D4);

        e=(TextView)findViewById(R.id.e);
        e1=(TextView)findViewById(R.id.e1);
        e2=(TextView)findViewById(R.id.e2);
        e3=(TextView)findViewById(R.id.e3);
        e4=(TextView)findViewById(R.id.e4);

        E=(TextView)findViewById(R.id.E);
        E1=(TextView)findViewById(R.id.E1);
        E2=(TextView)findViewById(R.id.E2);
        E3=(TextView)findViewById(R.id.E3);
        E4=(TextView)findViewById(R.id.E4);

        f=(TextView)findViewById(R.id.f);
        f1=(TextView)findViewById(R.id.f1);
        f2=(TextView)findViewById(R.id.f2);
        f3=(TextView)findViewById(R.id.f3);
        f4=(TextView)findViewById(R.id.f4);

        F=(TextView)findViewById(R.id.F);
        F1=(TextView)findViewById(R.id.F1);
        F2=(TextView)findViewById(R.id.F2);
        F3=(TextView)findViewById(R.id.F3);
        F4=(TextView)findViewById(R.id.F4);

        g=(TextView)findViewById(R.id.g);
        g1=(TextView)findViewById(R.id.g1);
        g2=(TextView)findViewById(R.id.g2);
        g3=(TextView)findViewById(R.id.g3);
        g4=(TextView)findViewById(R.id.g4);

        G=(TextView)findViewById(R.id.G);
        G1=(TextView)findViewById(R.id.G1);
        G2=(TextView)findViewById(R.id.G2);
        G3=(TextView)findViewById(R.id.G3);
        G4=(TextView)findViewById(R.id.G4);

        h=(TextView)findViewById(R.id.h);
        h1=(TextView)findViewById(R.id.h1);
        h2=(TextView)findViewById(R.id.h2);
        h3=(TextView)findViewById(R.id.h3);
        h4=(TextView)findViewById(R.id.h4);

        H=(TextView)findViewById(R.id.H);
        H1=(TextView)findViewById(R.id.H1);
        H2=(TextView)findViewById(R.id.H2);
        H3=(TextView)findViewById(R.id.H3);
        H4=(TextView)findViewById(R.id.H4);

        i=(TextView)findViewById(R.id.i);
        i1=(TextView)findViewById(R.id.i1);
        i2=(TextView)findViewById(R.id.i2);
        i3=(TextView)findViewById(R.id.i3);
        i4=(TextView)findViewById(R.id.i4);

        I=(TextView)findViewById(R.id.I);
        I1=(TextView)findViewById(R.id.I1);
        I2=(TextView)findViewById(R.id.I2);
        I3=(TextView)findViewById(R.id.I3);
        I4=(TextView)findViewById(R.id.I4);

        j=(TextView)findViewById(R.id.j);
        j1=(TextView)findViewById(R.id.j1);
        j2=(TextView)findViewById(R.id.j2);
        j3=(TextView)findViewById(R.id.j3);
        j4=(TextView)findViewById(R.id.j4);

        J=(TextView)findViewById(R.id.J);
        J1=(TextView)findViewById(R.id.J1);
        J2=(TextView)findViewById(R.id.J2);
        J3=(TextView)findViewById(R.id.J3);
        J4=(TextView)findViewById(R.id.J4);



        back=(Button)findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        String usn=getIntent().getStringExtra("usn");
        String subcode=getIntent().getStringExtra("subcode");


        db.collection(usn).document("attendance").collection("subjects").document(subcode).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists())
                        {


                            String subname=documentSnapshot.getString(Key_subject);
                            String day1=documentSnapshot.getString(Key_a);
                            String day2=documentSnapshot.getString(Key_a1);
                            String day3=documentSnapshot.getString(Key_a2);
                            String day4=documentSnapshot.getString(Key_a3);
                            String day5=documentSnapshot.getString(Key_a4);

                            String day6=documentSnapshot.getString(Key_b);
                            String day7=documentSnapshot.getString(Key_b1);
                            String day8=documentSnapshot.getString(Key_b2);
                            String day9=documentSnapshot.getString(Key_b3);
                            String day10=documentSnapshot.getString(Key_b4);

                            String day11=documentSnapshot.getString(Key_c);
                            String day12=documentSnapshot.getString(Key_c1);
                            String day13=documentSnapshot.getString(Key_c2);
                            String day14=documentSnapshot.getString(Key_c3);
                            String day15=documentSnapshot.getString(Key_c4);

                            String day16=documentSnapshot.getString(Key_d);
                            String day17=documentSnapshot.getString(Key_d1);
                            String day18=documentSnapshot.getString(Key_d2);
                            String day19=documentSnapshot.getString(Key_d3);
                            String day20=documentSnapshot.getString(Key_d4);

                            String day21=documentSnapshot.getString(Key_e);
                            String day22=documentSnapshot.getString(Key_e1);
                            String day23=documentSnapshot.getString(Key_e2);
                            String day24=documentSnapshot.getString(Key_e3);
                            String day25=documentSnapshot.getString(Key_e4);

                            String day26=documentSnapshot.getString(Key_f);
                            String day27=documentSnapshot.getString(Key_f1);
                            String day28=documentSnapshot.getString(Key_f2);
                            String day29=documentSnapshot.getString(Key_f3);
                            String day30=documentSnapshot.getString(Key_f4);

                            String day31=documentSnapshot.getString(Key_g);
                            String day32=documentSnapshot.getString(Key_g1);
                            String day33=documentSnapshot.getString(Key_g2);
                            String day34=documentSnapshot.getString(Key_g3);
                            String day35=documentSnapshot.getString(Key_g4);

                            String day36=documentSnapshot.getString(Key_h);
                            String day37=documentSnapshot.getString(Key_h1);
                            String day38=documentSnapshot.getString(Key_h2);
                            String day39=documentSnapshot.getString(Key_h3);
                            String day40=documentSnapshot.getString(Key_h4);

                            String day41=documentSnapshot.getString(Key_i);
                            String day42=documentSnapshot.getString(Key_i1);
                            String day43=documentSnapshot.getString(Key_i2);
                            String day44=documentSnapshot.getString(Key_i3);
                            String day45=documentSnapshot.getString(Key_i4);

                            String day46=documentSnapshot.getString(Key_j);
                            String day47=documentSnapshot.getString(Key_j1);
                            String day48=documentSnapshot.getString(Key_j2);
                            String day49=documentSnapshot.getString(Key_j3);
                            String day50=documentSnapshot.getString(Key_j4);


                            String cday1=documentSnapshot.getString(Key_A);
                            String cday2=documentSnapshot.getString(Key_A1);
                            String cday3=documentSnapshot.getString(Key_A2);
                            String cday4=documentSnapshot.getString(Key_A3);
                            String cday5=documentSnapshot.getString(Key_A4);

                            String cday6=documentSnapshot.getString(Key_B);
                            String cday7=documentSnapshot.getString(Key_B1);
                            String cday8=documentSnapshot.getString(Key_B2);
                            String cday9=documentSnapshot.getString(Key_B3);
                            String cday10=documentSnapshot.getString(Key_B4);

                            String cday11=documentSnapshot.getString(Key_C);
                            String cday12=documentSnapshot.getString(Key_C1);
                            String cday13=documentSnapshot.getString(Key_C2);
                            String cday14=documentSnapshot.getString(Key_C3);
                            String cday15=documentSnapshot.getString(Key_C4);

                            String cday16=documentSnapshot.getString(Key_D);
                            String cday17=documentSnapshot.getString(Key_D1);
                            String cday18=documentSnapshot.getString(Key_D2);
                            String cday19=documentSnapshot.getString(Key_D3);
                            String cday20=documentSnapshot.getString(Key_D4);

                            String cday21=documentSnapshot.getString(Key_E);
                            String cday22=documentSnapshot.getString(Key_E1);
                            String cday23=documentSnapshot.getString(Key_E2);
                            String cday24=documentSnapshot.getString(Key_E3);
                            String cday25=documentSnapshot.getString(Key_E4);

                            String cday26=documentSnapshot.getString(Key_F);
                            String cday27=documentSnapshot.getString(Key_F1);
                            String cday28=documentSnapshot.getString(Key_F2);
                            String cday29=documentSnapshot.getString(Key_F3);
                            String cday30=documentSnapshot.getString(Key_F4);

                            String cday31=documentSnapshot.getString(Key_G);
                            String cday32=documentSnapshot.getString(Key_G1);
                            String cday33=documentSnapshot.getString(Key_G2);
                            String cday34=documentSnapshot.getString(Key_G3);
                            String cday35=documentSnapshot.getString(Key_g4);

                            String cday36=documentSnapshot.getString(Key_H);
                            String cday37=documentSnapshot.getString(Key_H1);
                            String cday38=documentSnapshot.getString(Key_H2);
                            String cday39=documentSnapshot.getString(Key_H3);
                            String cday40=documentSnapshot.getString(Key_H4);

                            String cday41=documentSnapshot.getString(Key_I);
                            String cday42=documentSnapshot.getString(Key_I1);
                            String cday43=documentSnapshot.getString(Key_I2);
                            String cday44=documentSnapshot.getString(Key_I3);
                            String cday45=documentSnapshot.getString(Key_I4);

                            String cday46=documentSnapshot.getString(Key_J);
                            String cday47=documentSnapshot.getString(Key_J1);
                            String cday48=documentSnapshot.getString(Key_J2);
                            String cday49=documentSnapshot.getString(Key_J3);
                            String cday50=documentSnapshot.getString(Key_J4);


                            sname.setText(subname);
                            a.setText(day1);
                            a1.setText(day2);
                            a2.setText(day3);
                            a3.setText(day4);
                            a4.setText(day5);

                            A.setText(cday1);
                            A1.setText(cday2);
                            A2.setText(cday3);
                            A3.setText(cday4);
                            A4.setText(cday5);

                            b.setText(day6);
                            b1.setText(day7);
                            b2.setText(day8);
                            b3.setText(day9);
                            b4.setText(day10);

                            B.setText(cday6);
                            B1.setText(cday7);
                            B2.setText(cday8);
                            B3.setText(cday9);
                            B4.setText(cday10);

                            c.setText(day11);
                            c1.setText(day12);
                            c2.setText(day13);
                            c3.setText(day14);
                            c4.setText(day15);

                            C.setText(cday11);
                            C1.setText(cday12);
                            C2.setText(cday13);
                            C3.setText(cday14);
                            C4.setText(cday15);

                            d.setText(day16);
                            d1.setText(day17);
                            d2.setText(day18);
                            d3.setText(day19);
                            d4.setText(day20);

                            D.setText(cday16);
                            D1.setText(cday17);
                            D2.setText(cday18);
                            D3.setText(cday19);
                            D4.setText(cday20);

                            e.setText(day21);
                            e1.setText(day22);
                            e2.setText(day23);
                            e3.setText(day24);
                            e4.setText(day25);

                            E.setText(cday21);
                            E1.setText(cday22);
                            E2.setText(cday23);
                            E3.setText(cday24);
                            E4.setText(cday25);

                            f.setText(day26);
                            f1.setText(day27);
                            f2.setText(day28);
                            f3.setText(day29);
                            f4.setText(day30);

                            F.setText(cday26);
                            F1.setText(cday27);
                            F2.setText(cday28);
                            F3.setText(cday29);
                            F4.setText(cday30);

                            g.setText(day31);
                            g1.setText(day32);
                            g2.setText(day33);
                            g3.setText(day34);
                            g4.setText(day35);

                            G.setText(cday31);
                            G.setText(cday32);
                            G2.setText(cday33);
                            G3.setText(cday34);
                            G4.setText(cday35);

                            h.setText(day36);
                            h1.setText(day37);
                            h2.setText(day38);
                            h3.setText(day39);
                            h4.setText(day40);

                            I.setText(cday36);
                            I1.setText(cday37);
                            I2.setText(cday38);
                            I3.setText(cday39);
                            I4.setText(cday40);

                            i.setText(day41);
                            i1.setText(day42);
                            i2.setText(day43);
                            i3.setText(day44);
                            i4.setText(day45);

                            I.setText(cday41);
                            I1.setText(cday42);
                            I2.setText(cday43);
                            I3.setText(cday44);
                            I4.setText(cday45);

                            j.setText(day46);
                            j1.setText(day47);
                            j2.setText(day48);
                            j3.setText(day49);
                            j4.setText(day50);

                            J.setText(cday46);
                            J1.setText(cday47);
                            J2.setText(cday48);
                            J3.setText(cday49);
                            J4.setText(cday50);

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
