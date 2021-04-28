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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class kayitol extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();

     ImageView kayitolbtn;
     int x=0;
     String user,mail,sifre1;
     int sifre=0;
     EditText users,mails,sifres,sifres2;
     private FirebaseAuth mAuth;

    SharedPreferences preferences;
    //preferences için bir nesne tanımlıyorum.
    SharedPreferences.Editor editor;
//preferences içerisine bilgi girmek için tanımlama

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_kayitol);

        mAuth = FirebaseAuth.getInstance();

        kayitolbtn=findViewById(R.id.hac);
        mails=findViewById(R.id.mail);
        sifres=findViewById(R.id.sifre);
        sifres2=findViewById(R.id.sifre2);



        preferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = preferences.edit();



        kayitolbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                kayitolbtn.setImageResource(R.drawable.kayitolbutton2);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        kayitolbtn.setImageResource(R.drawable.kayitolbtn);
                    }
                }, 200);

                String sifre=sifres.getText().toString();
                String sifre2= sifres2.getText().toString();
                if(sifre.equals(sifre2)){
                    final String email = mails.getText().toString().toLowerCase();
                    String parola = sifres.getText().toString();

                    if(TextUtils.isEmpty(email)){
                        Toast.makeText(getApplicationContext(),"Lütfen emailinizi giriniz",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(TextUtils.isEmpty(parola)){
                        Toast.makeText(getApplicationContext(),"Lütfen parolanızı giriniz",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (parola.length()<6){
                        Toast.makeText(getApplicationContext(),"Parola en az 6 haneli olmalıdır",Toast.LENGTH_SHORT).show();
                        return;
                    }



                    //FirebaseAuth ile email,parola parametrelerini kullanarak yeni bir kullanıcı oluşturuyoruz.
                    mAuth.createUserWithEmailAndPassword(email,parola)
                            .addOnCompleteListener(kayitol.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    //İşlem başarısız olursa kullanıcıya bir Toast mesajıyla bildiriyoruz.
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(kayitol.this, "girilen bilgilerde hata var veya böyle bir hesap mevcuttur!!!",
                                                Toast.LENGTH_SHORT).show();
                                    }

                                    //İşlem başarılı olduğu takdir de giriş yapılıp MainActivity e yönlendiriyoruz.
                                    else {
                                        Toast.makeText(getApplicationContext(),"kayıt başarılı",Toast.LENGTH_SHORT).show();
                                        String a=email.toLowerCase();
                                        int deger=a.length();
                                        deger=deger-4;
                                        a=a.substring(0, deger);
                                       // a=a.toLowerCase();
                                        editor.putString("emailadres", a).apply();
                                        editor.commit();
                                        DatabaseReference myRef = database.getReference(a);
                                        myRef.setValue(0);
                                        startActivity(new Intent(kayitol.this, MainActivity.class));
                                        finish();
                                    }

                                }
                            });


                }
                else{
                    Toast.makeText(getApplicationContext(),"girilen bilgilerde hata var!!!",Toast.LENGTH_SHORT).show();
                }




            }
        });




    }
}
