package com.ashil.Towin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class homepage extends AppCompatActivity {
ImageView adsbtn1,adsbtn2,adsbtn3,adsbtn4,adsbtn5,adsbtn6,homebtn;
int coin;
String a;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_homepage);
        System.out.println(coin);
        adsbtn1=findViewById(R.id.ads1);
        adsbtn2=findViewById(R.id.ads2);
        adsbtn3=findViewById(R.id.ads3);
        adsbtn4=findViewById(R.id.ads4);
        adsbtn5=findViewById(R.id.ads5);
        adsbtn6=findViewById(R.id.ads6);
        homebtn=findViewById(R.id.homeid);

        //coin=degiskenler.getCoin();
       // a=degiskenler.getA();
        global global=(global)getApplicationContext();
        a=global.getA();
        coin=global.getCoin();


        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(homepage.this,anim.class);
                startActivity(intent);
            }
        });


        adsbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adsbtn1.setVisibility(View.GONE);
                showIntAdd();
            }
        });

        adsbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adsbtn2.setVisibility(View.GONE);

                showIntAdd();

            }
        });

        adsbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adsbtn3.setVisibility(View.GONE);

                showIntAdd();

            }
        });

        adsbtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adsbtn4.setVisibility(View.GONE);
                showIntAdd();

            }
        });

        adsbtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adsbtn5.setVisibility(View.GONE);

                showIntAdd();

            }
        });

        adsbtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adsbtn6.setVisibility(View.GONE);
                showIntAdd();

            }
        });



        mInterstitialAd = newInterstitialAd();
        loadInterstitial();


    }


    //ca-app-pub-4823108984271958/2567783703 bu bizim asıl id value dosyasındaki stringdeten değiştir
    private InterstitialAd newInterstitialAd() {
        InterstitialAd interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.ad_id_interstitial));
        interstitialAd.setAdListener(new AdListener() {


            @Override
            public void onAdClosed() {
                // Proceed to the next level.


                adsbtn2.setEnabled(false);
                adsbtn3.setEnabled(false);
                adsbtn4.setEnabled(false);
                adsbtn5.setEnabled(false);
                adsbtn1.setEnabled(false);
                adsbtn6.setEnabled(false);




                final Handler handler = new Handler();

                adsbtn2.setImageResource(R.drawable.deaktif3);
                adsbtn3.setImageResource(R.drawable.deaktif3);
                adsbtn4.setImageResource(R.drawable.deaktif3);
                adsbtn5.setImageResource(R.drawable.deaktif3);
                adsbtn1.setImageResource(R.drawable.deaktif3);
                adsbtn6.setImageResource(R.drawable.deaktif3);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Do something after 5s = 5000ms
                        adsbtn2.setImageResource(R.drawable.deaktif2);
                        adsbtn3.setImageResource(R.drawable.deaktif2);
                        adsbtn4.setImageResource(R.drawable.deaktif2);
                        adsbtn5.setImageResource(R.drawable.deaktif2);
                        adsbtn1.setImageResource(R.drawable.deaktif2);
                        adsbtn6.setImageResource(R.drawable.deaktif2);

                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                // Do something after 5s = 5000ms

                                adsbtn2.setImageResource(R.drawable.deaktif1);
                                adsbtn3.setImageResource(R.drawable.deaktif1);
                                adsbtn4.setImageResource(R.drawable.deaktif1);
                                adsbtn5.setImageResource(R.drawable.deaktif1);
                                adsbtn1.setImageResource(R.drawable.deaktif1);
                                adsbtn6.setImageResource(R.drawable.deaktif1);

                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        // Do something after 5s = 5000ms
                                        adsbtn2.setEnabled(true);
                                        adsbtn3.setEnabled(true);
                                        adsbtn4.setEnabled(true);
                                        adsbtn5.setEnabled(true);
                                        adsbtn1.setEnabled(true);
                                        adsbtn6.setEnabled(true);

                                        adsbtn2.setImageResource(R.drawable.ads);
                                        adsbtn3.setImageResource(R.drawable.ads);
                                        adsbtn4.setImageResource(R.drawable.ads);
                                        adsbtn5.setImageResource(R.drawable.ads);
                                        adsbtn1.setImageResource(R.drawable.ads);
                                        adsbtn6.setImageResource(R.drawable.ads);


                                    }
                                }, 1000);

                            }
                        }, 1000);

                    }
                }, 1000);



                //**********************

                goToNextLevel();
            }
        });
        return interstitialAd;




    }

    private void showIntAdd() {
        // Show the ad if it's ready. Otherwise toast and reload the ad.
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
            DatabaseReference myRef = database.getReference(a);
            coin++;
            global global=(global)getApplicationContext();
            global.setCoin(coin);
            myRef.setValue(coin);

        } else {
            adsbtn2.setEnabled(false);
            adsbtn3.setEnabled(false);
            adsbtn4.setEnabled(false);
            adsbtn5.setEnabled(false);
            adsbtn1.setEnabled(false);
            adsbtn6.setEnabled(false);




            final Handler handler = new Handler();

            adsbtn2.setImageResource(R.drawable.deaktif3);
            adsbtn3.setImageResource(R.drawable.deaktif3);
            adsbtn4.setImageResource(R.drawable.deaktif3);
            adsbtn5.setImageResource(R.drawable.deaktif3);
            adsbtn1.setImageResource(R.drawable.deaktif3);
            adsbtn6.setImageResource(R.drawable.deaktif3);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Do something after 5s = 5000ms
                    adsbtn2.setImageResource(R.drawable.deaktif2);
                    adsbtn3.setImageResource(R.drawable.deaktif2);
                    adsbtn4.setImageResource(R.drawable.deaktif2);
                    adsbtn5.setImageResource(R.drawable.deaktif2);
                    adsbtn1.setImageResource(R.drawable.deaktif2);
                    adsbtn6.setImageResource(R.drawable.deaktif2);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // Do something after 5s = 5000ms

                            adsbtn2.setImageResource(R.drawable.deaktif1);
                            adsbtn3.setImageResource(R.drawable.deaktif1);
                            adsbtn4.setImageResource(R.drawable.deaktif1);
                            adsbtn5.setImageResource(R.drawable.deaktif1);
                            adsbtn1.setImageResource(R.drawable.deaktif1);
                            adsbtn6.setImageResource(R.drawable.deaktif1);

                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    // Do something after 5s = 5000ms
                                    adsbtn2.setEnabled(true);
                                    adsbtn3.setEnabled(true);
                                    adsbtn4.setEnabled(true);
                                    adsbtn5.setEnabled(true);
                                    adsbtn1.setEnabled(true);
                                    adsbtn6.setEnabled(true);

                                    adsbtn2.setImageResource(R.drawable.ads);
                                    adsbtn3.setImageResource(R.drawable.ads);
                                    adsbtn4.setImageResource(R.drawable.ads);
                                    adsbtn5.setImageResource(R.drawable.ads);
                                    adsbtn1.setImageResource(R.drawable.ads);
                                    adsbtn6.setImageResource(R.drawable.ads);


                                }
                            }, 1000);

                        }
                    }, 1000);

                }
            }, 1000);
            Toast.makeText(this, "şuan mevcut reklam bulunmamaktadır!!!", Toast.LENGTH_SHORT).show();
            DatabaseReference myRef = database.getReference(a);
            goToNextLevel();
        }


    }

    private void loadInterstitial() {
        // Disable the next level button and load the ad.

        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        mInterstitialAd.loadAd(adRequest);
    }

    private void goToNextLevel() {
        // Show the next level and reload the ad to prepare for the level after.
        mInterstitialAd = newInterstitialAd();
        loadInterstitial();
    }













    public void onBackPressed(){
        Intent intent = new Intent(homepage.this,anim.class);
        startActivity(intent);

    }


}
