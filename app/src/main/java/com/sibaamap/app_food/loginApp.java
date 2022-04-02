package com.sibaamap.app_food;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class loginApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_app);

        getWindow().setStatusBarColor(getColor(R.color.welcome));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

    }

    public void register(View view) {
        startActivity(new Intent(loginApp.this,RegistrationApp.class));
    }

    public void signIn(View view) {
        //startActivity(new Intent(loginApp.this,IntroActivity.class));

    }
    private void initUi(){

    }

}