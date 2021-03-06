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
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;
import com.sibaamap.app_food.R;
import com.sibaamap.app_food.models.UserModel;

public class RegistrationApp extends AppCompatActivity {

    private EditText edtEmail,edtPassword,edtName;
    private Button btnRegister;
    private ProgressDialog progressDialog;

    private FirebaseDatabase firebaseDatabase;

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
        String strName = edtName.getText().toString().trim();

        if(strEmail == null){
            Toast.makeText(this, "vui long nhap email", Toast.LENGTH_SHORT).show();
        }
        if(strPassword == null){
            Toast.makeText(this, "vui long nhap mat khau", Toast.LENGTH_SHORT).show();
        }

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance("https://appfood-6ca6e-default-rtdb.asia-southeast1.firebasedatabase.app");
        progressDialog.show();
        mAuth.createUserWithEmailAndPassword(strEmail, strPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {

                            UserModel userModel = new UserModel(strName,strEmail,strPassword);
                            String id = task.getResult().getUser().getUid();
                            firebaseDatabase.getReference().child("Users").child(id).setValue(userModel);

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
        edtName = findViewById(R.id.edt_rg_name);
        edtEmail = findViewById(R.id.edt_rg_email);
        edtPassword = findViewById(R.id.edt_rg_pass);
        btnRegister = findViewById(R.id.btn_rg_register);

        progressDialog = new ProgressDialog(this);
    }
}