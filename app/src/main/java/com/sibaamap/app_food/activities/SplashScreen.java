package com.sibaamap.app_food.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sibaamap.app_food.MainActivity;
import com.sibaamap.app_food.R;

public class SplashScreen extends AppCompatActivity {

    LottieAnimationView appFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getWindow().setStatusBarColor(getColor(R.color.white));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        appFood = findViewById(R.id.foodapp);

        appFood.animate().translationX(2000).setDuration(2700).setStartDelay(2900);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                nextActivity();
            }
        },5000);


    }
    private void nextActivity() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null){
            //not logged in
            Intent intent = new Intent(this, welcomeApp.class);
            startActivity(intent);
            finish();
        }else {
            //logged
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

}