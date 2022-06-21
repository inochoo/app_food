package com.sibaamap.app_food.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.sibaamap.app_food.R;

public class welcomeApp extends AppCompatActivity {

    //private TextView signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setStatusBarColor(getColor(R.color.welcome));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        //signin = findViewById(R.id.tv_signin);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome_app);



    }

    public void register(View view) {
        startActivity(new Intent(welcomeApp.this,RegistrationApp.class));

    }

    public void login(View view) {
        startActivity(new Intent(welcomeApp.this,loginApp.class));
    }
}