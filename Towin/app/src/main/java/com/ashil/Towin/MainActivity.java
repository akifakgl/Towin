package com.ashil.Towin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ashil.Towin.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    ImageView btn,kayitol;
    EditText mailg,sifreg;
    private FirebaseAuth auth;
    private static final int RC_SIGN_IN = 9001;



    SharedPreferences preferences;
    //preferences için bir nesne tanımlıyorum.
    SharedPreferences.Editor editor;
//preferences içerisine bilgi girmek için tanımlama


    //degiskenler degiskenler1 = (degiskenler) getApplicationContext();


    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        btn=findViewById(R.id.girisid);
        kayitol=findViewById(R.id.hesapolusturid);

        mailg=findViewById(R.id.mailg);
        sifreg=findViewById(R.id.sifreg);

        auth = FirebaseAuth.getInstance();




        preferences=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = preferences.edit();


        String girishatirla =preferences.getString("emailadres", "");


        int girisdegeri=0;

        girisdegeri=preferences.getInt("girisdegeri", 0);

        if (girisdegeri==1){
            mailg.setText(girishatirla+".com");
        }






        kayitol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kayitol.setImageResource(R.drawable.kayitolbutton2);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        kayitol.setImageResource(R.drawable.kayitolbtn);
                    }
                }, 200);

                Intent intent = new Intent(MainActivity.this, com.ashil.Towin.kayitol.class);
                startActivity(intent);

            }
        });




        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                btn.setImageResource(R.drawable.girisbtn2);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btn.setImageResource(R.drawable.girisbutton);
                    }
                }, 200);

                final String email = mailg.getText().toString().toLowerCase();
                final String parola = sifreg.getText().toString();




                //Email girilmemiş ise kullanıcıyı uyarıyoruz.
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Lütfen emailinizi giriniz", Toast.LENGTH_SHORT).show();
                    return;
                }
                //Parola girilmemiş ise kullanıcıyı uyarıyoruz.
                if (TextUtils.isEmpty(parola)) {
                    Toast.makeText(getApplicationContext(), "Lütfen parolanızı giriniz", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Firebase üzerinde kullanıcı doğrulamasını başlatıyoruz
                //Eğer giriş başarılı olursa task.isSuccessful true dönecek ve MainActivity e geçilecek
                auth.signInWithEmailAndPassword(email, parola)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    btn.setVisibility(View.GONE);

                                    Toast.makeText(getApplicationContext(),"Giriş yapılıyor...",Toast.LENGTH_SHORT).show();

                                    String a=email.toLowerCase();
                                    int deger=a.length();
                                    deger=deger-4;
                                    a=a.substring(0, deger);


                                    global global=(global)getApplicationContext();
                                    global.setA(a);

                                    editor.putString("emailadres", a).apply();
                                    editor.commit();

                                    editor.putInt("girisdegeri", 1).apply();
                                    editor.commit();

                                    Intent intent = new Intent(MainActivity.this,anim.class);
                                    startActivity(intent);



                                }
                                else {
                                    Toast.makeText(getApplicationContext(),"Giriş hatalı!!!",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });








    }
}
