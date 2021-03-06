package com.sibaamap.app_food.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.sibaamap.app_food.MainActivity;
import com.sibaamap.app_food.R;

public class loginApp extends AppCompatActivity {


    private EditText edtemail,edtpassword;
    private Button btnSignIn;
    private ProgressDialog progressDialogs;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_app);



        getWindow().setStatusBarColor(getColor(R.color.welcome));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        initUi();

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSignIn();
            }
        });
    }

    private void onClickSignIn() {
        mAuth = FirebaseAuth.getInstance();
        String strEl = edtemail.getText().toString().trim();
        String strPw = edtpassword.getText().toString().trim();

        if(strEl == null){
            Toast.makeText(this, "vui long nhap email", Toast.LENGTH_SHORT).show();
        }
        if(strPw == null){
            Toast.makeText(this, "vui long nhap mat khau", Toast.LENGTH_SHORT).show();
        }

        progressDialogs.show();
        mAuth.signInWithEmailAndPassword(strEl, strPw)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialogs.dismiss();
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Intent intent = new Intent(loginApp.this, MainActivity.class);
                            startActivity(intent);
                            //finishAffinity();
                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(loginApp.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    public void register(View view) {
        startActivity(new Intent(loginApp.this,RegistrationApp.class));
    }


    private void initUi(){
        progressDialogs = new ProgressDialog(this);
        edtemail = findViewById(R.id.edt_lg_email);
        edtpassword = findViewById(R.id.edt_lg_pass);
        btnSignIn = findViewById(R.id.btn_rg_login);

    }

}