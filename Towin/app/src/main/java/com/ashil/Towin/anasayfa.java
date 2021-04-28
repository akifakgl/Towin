package com.ashil.Towin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;

public class anasayfa extends AppCompatActivity {
        ImageView paracek,kazan,bilgi;
        TextView bakiye,mail;
        int coin;
        String a;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_anasayfa);

        paracek=findViewById(R.id.paracek);
        kazan=findViewById(R.id.kazan);
        bilgi=findViewById(R.id.bilgi);
        bakiye=findViewById(R.id.bakiyeid);
        mail=findViewById(R.id.emailid);

        global global=(global)getApplicationContext();
        a=global.getA();
        coin=global.getCoin();


        bakiye.setText(""+coin);
        mail.setText("kullanıcı:"+a);



        paracek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                paracek.setImageResource(R.drawable.paracekbtn2);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        paracek.setImageResource(R.drawable.paracekbtn);
                    }
                }, 200);

                Intent intent=new Intent(anasayfa.this,paracekme.class);
                startActivity(intent);
            }
        });
        kazan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                kazan.setImageResource(R.drawable.coinkazanbtn2);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        kazan.setImageResource(R.drawable.coinkazanbtn);
                    }
                }, 200);

                Intent intent=new Intent(anasayfa.this,homepage.class);
                startActivity(intent);
            }
        });
        bilgi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bilgi.setImageResource(R.drawable.bilgibtn2);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        bilgi.setImageResource(R.drawable.bilgibt);
                    }
                }, 200);

                Intent intent=new Intent(anasayfa.this,bilgi.class);
                startActivity(intent);
            }
        });




    }
    public void onBackPressed(){
        Intent intent = new Intent(anasayfa.this,anim.class);
        startActivity(intent);

    }
}
