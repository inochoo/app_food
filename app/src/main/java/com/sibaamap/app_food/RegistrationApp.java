package com.sibaamap.app_food;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationApp extends AppCompatActivity {

    private EditText edtEmail,edtPassword;
    private Button btnRegister;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_app);

        getWindow().setStatusBarColor(getColor(R.color.welcome));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        initUi();
    }

    public void login(View view) {
        startActivity(new Intent(RegistrationApp.this,loginApp.class));


    }

    public void rstStart(View view) {
        //startActivity(new Intent(RegistrationApp.this,IntroActivity.class));
        String strEmail = edtEmail.getText().toString().trim();
        String strPassword = edtPassword.getText().toString().trim();

        if(strEmail == null){
            Toast.makeText(this, "vui long nhap email", Toast.LENGTH_SHORT).show();
        }
        if(strPassword == null){
            Toast.makeText(this, "vui long nhap mat khau", Toast.LENGTH_SHORT).show();
        }

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        progressDialog.show();
        mAuth.createUserWithEmailAndPassword(strEmail, strPassword )
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Intent intent = new Intent(RegistrationApp.this,IntroActivity.class);
                            startActivity(intent);
                            finishAffinity();

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(RegistrationApp.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }
    private void initUi(){
        edtEmail = findViewById(R.id.edt_rg_email);
        edtPassword = findViewById(R.id.edt_rg_pass);
        btnRegister = findViewById(R.id.btn_rg_register);

        progressDialog = new ProgressDialog(this);
    }
}