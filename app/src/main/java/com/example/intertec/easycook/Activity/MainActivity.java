package com.example.intertec.easycook.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.intertec.easycook.Activity.Login;
import com.example.intertec.easycook.R;

public class MainActivity extends AppCompatActivity {
private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_logout:
                logout();
                return true;
            case R.id.forget_logout:
                removeSharePreferences();
                logout();
                return true;
            default:
                return super.onOptionsItemSelected(item);



        }
    }
    private void logout(){
        Intent intent=new Intent(this, Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);//sirve para que cierre la aplicacion y no no regrese al login
        startActivity(intent);
    }
    private void removeSharePreferences(){
        prefs.edit().clear().apply();//mas simplificado que en el login
    }
}