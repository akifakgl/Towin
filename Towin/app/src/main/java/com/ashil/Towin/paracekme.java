package com.ashil.Towin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class paracekme extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    ImageView gonderm;
    String ibankey,coinmikstg,adsoyadstg,ibanstg;
    EditText coinmikedt,ibanedt,adsoyadedt;

    int coin,cekilencoin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_paracekme);
        gonderm=findViewById(R.id.gonder);

        coinmikedt=findViewById(R.id.coinmiktari);
        ibanedt=findViewById(R.id.iban);
        adsoyadedt=findViewById(R.id.adsoyad);





        final global global=(global)getApplicationContext();
        ibankey=global.getA();
        coin=global.getCoin();



        gonderm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gonderm.setImageResource(R.drawable.talepbtn2);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gonderm.setImageResource(R.drawable.talepbtn);
                    }
                }, 200);

                ibanstg=ibanedt.getText().toString();
                adsoyadstg=adsoyadedt.getText().toString();
                coinmikstg=coinmikedt.getText().toString();




                if(ibanstg.equalsIgnoreCase("")||adsoyadstg.equalsIgnoreCase("")||coinmikstg.equalsIgnoreCase("")){
                    Toast.makeText(getApplicationContext(),"doldurulmamış bilgiler var !!!",Toast.LENGTH_SHORT).show();

                    return;
                }
                else {
                        cekilencoin=Integer.parseInt(coinmikstg);

                        if(coin>=1000&&cekilencoin>=1000){
                            if(coin>=cekilencoin){

                                ibanedt.setText("");
                                adsoyadedt.setText("");
                                coinmikedt.setText("");

                                coin=coin-cekilencoin;
                                global.setCoin(coin);
                                Toast.makeText(getApplicationContext(),"işlem tamamlandı !!!",Toast.LENGTH_SHORT).show();
                                DatabaseReference myRef = database.getReference("00000"+ibankey+"---");
                                myRef.setValue(coinmikstg+"---"+ibanstg+"---"+adsoyadstg);
                                gonderm.setVisibility(View.GONE);
                                DatabaseReference myRef2 = database.getReference(ibankey);
                                myRef2.setValue(coin);

                            }
                            else{
                                Toast.makeText(getApplicationContext(),"yetersiz coin !!!",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else if(coin<cekilencoin){
                            Toast.makeText(getApplicationContext(),"yetersiz coin !!!",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"minimum talep 1000 coin !!!",Toast.LENGTH_SHORT).show();
                    }



                }









            }
        });



    }
    public void onBackPressed(){
        Intent intent = new Intent(paracekme.this,anim.class);
        startActivity(intent);

    }
}
