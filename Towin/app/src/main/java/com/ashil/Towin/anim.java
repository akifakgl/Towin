package com.ashil.Towin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class anim extends AppCompatActivity {
    private ImageView im;
    int coin;
    String a;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
   // degiskenler degiskenler = (degiskenler) getApplicationContext();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_anim);


        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        im = findViewById(R.id.green);

     //   a=degiskenler.getA();
        global global=(global)getApplicationContext();

        a=global.getA();


        DatabaseReference myRef = database.getReference(a);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                coin = dataSnapshot.getValue(int.class);
                // Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                // Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

      //  degiskenler.setCoin(coin);
      //  degiskenler.setA(a);



        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.myanim);
        im.startAnimation(myanim);




        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                Intent intent= new Intent(anim.this,anasayfa.class);
                global global =(global)getApplicationContext();
                global.setCoin(coin);
                startActivity(intent);
            }
        }, 4000);
    }
}
