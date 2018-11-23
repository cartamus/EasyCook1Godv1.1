package com.example.intertec.easycook.Splash;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.example.intertec.easycook.Activity.Login;
import com.example.intertec.easycook.Activity.MainActivity;
import com.example.intertec.easycook.Utileria.Util;

public class SplashActivity extends AppCompatActivity {
private SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs=getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        Intent intentLogin=new Intent(this,Login.class);
        Intent intentMain=new Intent(this,MainActivity.class);
        intentLogin.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
        if(!TextUtils.isEmpty(Util.getUserMailPrefs(prefs)) && !TextUtils.isEmpty(Util.getUserPassPrefs(prefs))){
           startActivity(intentMain);
        }
        else {
            startActivity(intentLogin);
        }

    }

}
