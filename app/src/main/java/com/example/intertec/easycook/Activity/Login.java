package com.example.intertec.easycook.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.example.intertec.easycook.R;

public class Login extends AppCompatActivity {
    private Button registro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        setContentView(R.layout.activity_login);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        bindUI();
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
       gotoMainregistro();
            }
        });
    }
    private void bindUI() {
        registro=(Button)findViewById(R.id.registrar);
    }
    private void gotoMainregistro() {
        Intent intent = new Intent(this, Registro.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);//sirve para que cierre la aplicacion y no no regrese al login
        startActivity(intent);
    }
    public void startDemo(Class className) {
        startActivity(new Intent(this, className));
    }
    public void loginAnonimo(View view) {
        startDemo(Anonimo.class);
    }
    public void google(View view) {
        startDemo(Google.class);
    }
    public void face(View view) { startDemo(Facebook.class); }
    public void basedat(View view) { startDemo(PruebaBase.class); }
    public void emailgood(View view) { startDemo(RegisterActivity.class); }

}