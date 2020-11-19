package com.example.jgix;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

public class ReceiptsActivity extends AppCompatActivity  {
    private ImageView back;
    private InterstitialAd mInterstitialAd;
    private RewardedVideoAd mAd;
    private Button ads;
    private TextView coins;

    int totalcoins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipts);


       // implements RewardedVideoAdListener

        ads=(Button)findViewById(R.id.ads);
        coins=(TextView)findViewById(R.id.coins);

        totalcoins=Integer.parseInt(coins.getText().toString());

       /* MobileAds.initialize(this);
        mAd = MobileAds.getRewardedVideoAdInstance(this);
        mAd.setRewardedVideoAdListener(this);
        mAd.loadAd("ca-app-pub-3940256099942544/5224354917",
                new AdRequest.Builder().build());

        ads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ads.setEnabled(false);
                if (mAd.isLoaded())
                {
                    mAd.show();
                }
            }
        });*/


      /*  mInterstitialAd = new InterstitialAd(this);
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

        back=(ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

    }
   /* @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onRewardedVideoAdLoaded() {

      //  ads.setEnabled(true);
    }

    @Override
    public void onRewardedVideoAdOpened() {

    }

    @Override
    public void onRewardedVideoStarted() {

    }

    @Override
    public void onRewardedVideoAdClosed() {

    }

    @Override
    public void onRewarded(RewardItem rewardItem) {

        totalcoins += rewardItem.getAmount();
        coins.setText(totalcoins);
        Toast.makeText(this, "Total coins you have"+totalcoins, Toast.LENGTH_SHORT).show();
        // Reward the user.

    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {

    }

    @Override
    public void onRewardedVideoCompleted() {

    }*/
}