package com.example.sellhouse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText email, password;
    Button login;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.extEmail);
        password = findViewById(R.id.extPassword);
        login = findViewById(R.id.btnLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });
    }

    private void userLogin() {
        String emailToFirebase = email.getText().toString().trim();
        String passwordToFirebase = password.getText().toString().trim();

        if (emailToFirebase.isEmpty()){
            email.setError("Mobile is required");
            email.requestFocus();
            return;
        }

        if (passwordToFirebase.isEmpty()){
            password.setError("Mobile is required");
            password.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(emailToFirebase).matches()){
            email.setError("Please provide valid email!");
            email.requestFocus();
            return;
        }

        if (passwordToFirebase.length() < 6) {
            password.setError("Min password should be 6 characters!");
            password.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(emailToFirebase, passwordToFirebase)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Successful!", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(LoginActivity.this, BuyOrSellActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(LoginActivity.this, "Failed to login. Please check the credentials!", Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

}