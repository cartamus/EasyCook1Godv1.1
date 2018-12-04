package com.example.intertec.easycook.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
//si wey
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import com.example.intertec.easycook.R;
import com.example.intertec.easycook.Utileria.Util;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class Login extends AppCompatActivity {
private LoginButton loginButton;
private CallbackManager callbackManager;
    private SharedPreferences prefs;
    private EditText edittextPassword;
    private EditText edittextEmail;
    private Button login;
    private Switch recuerdame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        setContentView(R.layout.activity_login);
        callbackManager=CallbackManager.Factory.create();
        loginButton=(LoginButton)findViewById(R.id.login_button);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
gotoMain();
            }

            @Override
            public void onCancel() {
Toast.makeText(getApplicationContext(),R.string.cancel_Login,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(),R.string.error_Login,Toast.LENGTH_SHORT).show();
            }
        });
        bindUI();

        prefs = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        setCredentialsIfExist();

        login.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {
                String email = edittextEmail.getText().toString();
                String password = edittextPassword.getText().toString();
                if (login(email, password)) {
                    gotoMain();
                    saveOnPreferences(email, password);
                }
            }
        });

    }

    private void bindUI() {
        edittextEmail = (EditText) findViewById(R.id.mail);
        edittextPassword = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.button_login);
        recuerdame = (Switch) findViewById(R.id.switch1);

    }

    private void setCredentialsIfExist() {
        String email=Util.getUserMailPrefs(prefs);
        String password=Util.getUserPassPrefs(prefs);
        if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){
            edittextEmail.setText(email);
            edittextPassword.setText(password);
        }

    }

    private boolean login(String email, String password) {
        if (!isValidEmail(email)) {
            Toast.makeText(this, "El E-mail no es valido", Toast.LENGTH_LONG).show();
            return false;
        } else if (!isValidPassword(password)) {
            Toast.makeText(this, "El Password no es valido,pon 4 caracteres", Toast.LENGTH_LONG).show();
            return false;
        } else {
            return true;
        }
    }

    private void saveOnPreferences(String email, String password) {
        if (recuerdame.isChecked()) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("email", email);
            editor.putString("password", password);

            editor.apply();
        }
    }

    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPassword(String password) {
        return password.length() > 4;
    }

    private void gotoMain() {

        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);//sirve para que cierre la aplicacion y no no regrese al login
        startActivity(intent);
    }
@Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
}

}