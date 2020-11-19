package com.example.jgix;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;


import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.provider.CalendarContract;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private CardView card1,card2,card3,card4;
    LinearLayout mainlayout;
    private ProgressBar progressbar;

    private static final String TAG="Main2Activity";
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private static final String keyUsn="usn";

    private static final String keyName="name";
    private static final String keyPasscode="passcode";
    private static final String key_acyear="AcademicYear";
    private static final String Key_stream="stream";

    private static final String keyUpcoming1="upcomingpic1";
    private static final String keyUpcoming2="upcomingpic2";
    private static final String keyUpcoming3="upcomingpic3";

    private static final String keyRecent1="recent1";
    private static final String keyRecent2="recent2";
    private static final String keyRecent3="recent3";
SliderLayout sliderLayout;
    private static final String key_pplink="PPLink1";
    String usnx;
    String namex;
    String mail;
    String passcodex;

    String academicyearx;
    String streamx;
    private TextView usn,headername,headeremail;
    private ImageView headerimage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        mail=getIntent().getStringExtra("mail");
        usn=(TextView)findViewById(R.id.usn);

        mainlayout=(LinearLayout) findViewById(R.id.maincard);
        progressbar=(ProgressBar)findViewById(R.id.progressbar);

        card4=(CardView)findViewById(R.id.card4);
        card3=(CardView)findViewById(R.id.card3);
        card2=(CardView)findViewById(R.id.card2);
        card1=(CardView)findViewById(R.id.card1);
        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main2Activity.this, ReceiptsActivity.class);
                startActivity(intent);
            }
        });
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main2Activity.this, Studymaterial.class);
                intent.putExtra("acyear",academicyearx);
                intent.putExtra("stream",streamx);
                startActivity(intent);
            }
        });
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main2Activity.this, Results.class);
                intent.putExtra("usn",usnx);
                startActivity(intent);
            }
        });
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, Assignments.class);
                intent.putExtra("stream", streamx);
                intent.putExtra("acyear", academicyearx);
                startActivity(intent);
            }
        });




        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);



        sliderLayout=(SliderLayout) findViewById(R.id.sliderimage);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL);
        sliderLayout.setScrollTimeInSec(2);
        setSliderViews();


        View headerView = navigationView.getHeaderView(0);
        headername = (TextView) headerView.findViewById(R.id.headername1);
        headeremail = (TextView) headerView.findViewById(R.id.headeremail1);
        headerimage=(ImageView)headerView.findViewById(R.id.headerimage);
        headeremail.setText(mail);







    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("Exit")
                    .setCancelable(false)
                    .setMessage("Are you sure?")
                    .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    })
                    .setPositiveButton("Yes",  new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    });
            AlertDialog alertDialog=builder.create();
            alertDialog.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent=new Intent(getApplicationContext(), Settings.class);
            intent.putExtra("mail",mail);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.action_Developers) {
            Intent intent=new Intent(getApplicationContext(), DevelopersActivity.class);
            startActivity(intent);

            return true;
        }

        else if (id == R.id.action_report) {
            Intent intent=new Intent(getApplicationContext(),BugreportActivity.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        }
        else if (id == R.id.nav_profile)
        {
            Intent intent=new Intent(Main2Activity.this,SecurityActivity.class);
            intent.putExtra("passcode",passcodex);
            intent.putExtra("mail",mail);
            startActivity(intent);
        }
        else if (id == R.id.nav_sugestion)
        {
            Intent intent=new Intent(Main2Activity.this,Suggestion.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_outpass)
        {
            Intent intent=new Intent(Main2Activity.this,Outpass.class);
            intent.putExtra("name",namex);
            intent.putExtra("usn",usnx);
            startActivity(intent);
        }
        else if (id == R.id.nav_attendance)
        {
            Intent intent=new Intent(Main2Activity.this,Attendance.class);
            intent.putExtra("mail",mail);
            intent.putExtra("name",namex);
            intent.putExtra("usn",usnx);
            startActivity(intent);
        }
        else if (id == R.id.nav_share)
        {
            Intent intent=new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            String link="https://www.google.com";
            String message="Hey guys check it out this app.";
            intent.putExtra(Intent.EXTRA_TEXT,link);
            intent.putExtra(Intent.EXTRA_SUBJECT,message);
            startActivity(Intent.createChooser(intent,"Share Using"));
        }
        else if (id == R.id.nav_results)
        {



            Intent intent=new Intent(Main2Activity.this, Results.class);
            intent.putExtra("usn",usnx);
            startActivity(intent);

        }
        else if (id == R.id.nav_events)
        {
            Intent intent=new Intent(Main2Activity.this, Events.class);

            startActivity(intent);
        }
        else if (id == R.id.nav_logout)
        {
            SharedPreferences preferences=getSharedPreferences("checkbox",MODE_PRIVATE);
            SharedPreferences.Editor editor=preferences.edit();
            editor.putString("remember","false");
            editor.apply();

            Intent intent=new Intent(Main2Activity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        else if (id == R.id.nav_settings)
        {
            Intent intent=new Intent(Main2Activity.this, Settings.class);
            intent.putExtra("mail",mail);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        progressbar.setVisibility(View.VISIBLE);
        mainlayout.setVisibility(View.GONE);

        DocumentReference noteref=db.collection(mail).document("profile");
        noteref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists())
                {

                    usnx=documentSnapshot.getString(keyUsn);
                    namex=documentSnapshot.getString(keyName);
                    passcodex=documentSnapshot.getString(keyPasscode);
                    academicyearx=documentSnapshot.getString(key_acyear);
                    streamx=documentSnapshot.getString(Key_stream);
                    String linkx=documentSnapshot.getString(key_pplink);


                    usn.setText(usnx);
                    headername.setText(namex);

                    if (!linkx.isEmpty())
                    {
                        Glide.with(getApplicationContext())
                                .load(linkx)
                                .into(headerimage);

                    }

                    progressbar.setVisibility(View.GONE);
                    mainlayout.setVisibility(View.VISIBLE);


                }
                else {
                    Toast.makeText(getApplicationContext(),"Document does not exist!",Toast.LENGTH_SHORT).show();

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


    private void setSliderViews()
    {
        for (int i=0;i<=4;i++)
        {


            final DefaultSliderView sliderView=new DefaultSliderView(this);
            switch (i)
            {
                case 0:
                    DocumentReference noteref2 = db.collection("Events").document("Upcoming");
                    noteref2.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {
                                String slide1 = documentSnapshot.getString(keyUpcoming1);
                                sliderView.setImageUrl(slide1);
                            }
                        }
                    });
                    break;
                case 1:
                    DocumentReference noteref3 = db.collection("Events").document("Upcoming");
                    noteref3.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {
                                String  slide2 = documentSnapshot.getString(keyUpcoming2);
                                sliderView.setImageUrl(slide2);
                            }
                        }
                    });
                    break;
                case 2:
                    DocumentReference noteref4 = db.collection("Events").document("Upcoming");
                    noteref4.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {
                                String  slide3 = documentSnapshot.getString(keyUpcoming3);
                                sliderView.setImageUrl(slide3);
                            }
                        }
                    });
                    break;
                case 3:
                    DocumentReference noteref5=db.collection("Events").document("Recent");
                    noteref5.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists())
                            {
                                String  slide4=documentSnapshot.getString(keyRecent1);
                                sliderView.setImageUrl(slide4);
                            }
                        }
                    });
                    break;
                case 4:
                    DocumentReference noteref6=db.collection("Events").document("Recent");
                    noteref6.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists())
                            {
                                String  slide5=documentSnapshot.getString(keyRecent2);
                                sliderView.setImageUrl(slide5);
                            }
                        }
                    });
                    break;

                case 5:
                    DocumentReference noteref7=db.collection("Events").document("Recent");
                    noteref7.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists())
                            {
                                String  slide6=documentSnapshot.getString(keyRecent3);
                                sliderView.setImageUrl(slide6);
                            }
                        }
                    });
                    break;
            }
            sliderView.setImageScaleType(ImageView.ScaleType.CENTER);
            //sliderView.setDescription("setDescription" +(i+1));
            //it is working
            final int final1=i;
            sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(SliderView sliderView) {

                    if (final1==0)
                    {
                       // Uri uri=Uri.parse("http://www.jainuniversity.ac.in");
                       // Intent intentx=new Intent(Intent.ACTION_VIEW,uri);
                        Intent intentx =new Intent(getApplicationContext(),Events.class);
                        startActivity(intentx);

                    }

                    else   if (final1==1)
                    {
                        // Uri uri=Uri.parse("http://www.jainuniversity.ac.in");
                        // Intent intentx=new Intent(Intent.ACTION_VIEW,uri);
                        Intent intentx =new Intent(getApplicationContext(),Events.class);
                        startActivity(intentx);
                    }

                    else if (final1==2)
                    {
                        // Uri uri=Uri.parse("http://www.jainuniversity.ac.in");
                        // Intent intentx=new Intent(Intent.ACTION_VIEW,uri);
                        Intent intentx =new Intent(getApplicationContext(),Events.class);
                        startActivity(intentx);
                    }

                    else if (final1==3)
                    {
                        // Uri uri=Uri.parse("http://www.jainuniversity.ac.in");
                        // Intent intentx=new Intent(Intent.ACTION_VIEW,uri);
                        Intent intentx =new Intent(getApplicationContext(),Events.class);
                        startActivity(intentx);
                    }
                    else
                    {
                        // Uri uri=Uri.parse("http://www.jainuniversity.ac.in");
                        // Intent intentx=new Intent(Intent.ACTION_VIEW,uri);
                        Intent intentx =new Intent(getApplicationContext(),Events.class);
                        startActivity(intentx);
                    }


                }
            });
            sliderLayout.addSliderView(sliderView);
        }
    }


}
