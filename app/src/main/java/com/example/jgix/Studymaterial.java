package com.example.jgix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;

public class Studymaterial extends AppCompatActivity {
    private ImageView back;
    private InterstitialAd mInterstitialAd;
    private AdView mAdView;
    private Button download,cancel;
    private TextView sub1,sub2,sub3,sub4,sub5,sub6,sub7;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private static final String TAG ="Studymaterial";
    private static final String keySubject1="sub1";
    private static final String keySubject2="sub2";
    private static final String keySubject3="sub3";
    private static final String keySubject4="sub4";
    private static final String keySubject5="sub5";
    private static final String keySubject6="sub6";
    private static final String keySubject7="sub7";


    private static final String keylink1="notes1";
    private static final String keylink2="notes2";
    long dwnid;
    private LinearLayout l1,l2,l3,l4,l5,l6,l7;
    private StorageReference mStorageRef;
private static final int PERMISION_STORAGE_CODE=1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studymaterial);


        sub1=(TextView)findViewById(R.id.sub1);
        sub2=(TextView)findViewById(R.id.sub2);
        sub3=(TextView)findViewById(R.id.sub3);
        sub4=(TextView)findViewById(R.id.sub4);
        sub5=(TextView)findViewById(R.id.sub5);
        sub6=(TextView)findViewById(R.id.sub6);
        sub7=(TextView)findViewById(R.id.sub7);

        l1=(LinearLayout)findViewById(R.id.l1);
        l2=(LinearLayout)findViewById(R.id.l2);
        l3=(LinearLayout)findViewById(R.id.l3);
        l4=(LinearLayout)findViewById(R.id.l4);
        l5=(LinearLayout)findViewById(R.id.l5);
        l6=(LinearLayout)findViewById(R.id.l6);
        l7=(LinearLayout)findViewById(R.id.l7);
        back=(ImageView)findViewById(R.id.back);





        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String acyear=getIntent().getStringExtra("acyear");
                String stream=getIntent().getStringExtra("stream");
                DocumentReference noteref=db.collection("Studymaterial").document(stream).collection(acyear).document("01");
                noteref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists())
                        {
                            Intent  intent=new Intent(getApplicationContext(),NotesActivity.class);
                            startActivity(intent);


                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Not found",Toast.LENGTH_SHORT).show();
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



       /* download=(Button)findViewById(R.id.download);
        cancel=(Button)findViewById(R.id.cancel);
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.M);
                {
                    if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_DENIED))
                    {
                        String[] permisions={Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permisions,PERMISION_STORAGE_CODE);
                    }
                    else {
                          startDownloading();
                    }
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DownloadManager downloadManager=(DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                downloadManager.remove(dwnid);
            }
        });*/


     /*   MobileAds.initialize(this);
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        mAdView.setAdListener(new AdListener(){
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();

            }
        });


    mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3848401810013100/9245221430");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mInterstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();

                if (mInterstitialAd.isLoaded())
                {
                    mInterstitialAd.show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Ads did not load", Toast.LENGTH_SHORT).show();
                }

            }
        });*/



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
    private void startDownloading()
    {
        String url="https://firebasestorage.googleapis.com/v0/b/jgifirebase.appspot.com/o/" +
                "Installation_and_Configuration_of_Server_Lab%20Manual.docx?alt=media&token=d2fd7b4e-26a1-4d1d-8af2-abad9820b831";
        DownloadManager.Request request=new DownloadManager.Request(Uri.parse(url));
        request.setAllowedNetworkTypes((DownloadManager.Request.NETWORK_MOBILE) | (DownloadManager.Request.NETWORK_WIFI));
        request.setTitle("Download");
        request.setDescription("Downloading Files...");
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,""+ System.currentTimeMillis());
        DownloadManager manager=(DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
       dwnid= manager.enqueue(request);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode)
        {
            case PERMISION_STORAGE_CODE:
            {
                if (grantResults.length> 0 && grantResults[0]== PackageManager.PERMISSION_GRANTED)
                {
                    startDownloading();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Permission Denied",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
