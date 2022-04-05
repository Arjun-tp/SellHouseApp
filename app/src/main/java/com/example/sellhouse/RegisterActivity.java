package com.example.sellhouse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    Button register;
    EditText email, mobile, password, confirmPassword;
    TextView loginButton;

    private FirebaseAuth mAuth;
//    private DatabaseReference root = db.getReference().child("Users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        register = findViewById(R.id.btnLogin);
        email = findViewById(R.id.extEmail);
        mobile = findViewById(R.id.extMobile);
        password = findViewById(R.id.extPassword);
        confirmPassword = findViewById(R.id.extConfirmPassword);
        loginButton = findViewById(R.id.txvLogin);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }

    private void registerUser(){
        String emailToFirebase = email.getText().toString().trim();
        String mobileToFirebase = mobile.getText().toString().trim();
        String passwordToFirebase = password.getText().toString().trim();


        if (emailToFirebase.isEmpty()){
            email.setError("Email is required!");
            email.requestFocus();
            return;
        }

        if (mobileToFirebase.isEmpty()){
            mobile.setError("Mobile is required");
            mobile.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailToFirebase).matches()){
            email.setError("Please provide valid email!");
            email.requestFocus();
            return;
        }

        if (passwordToFirebase.isEmpty()){
            password.setError("Password is required");
            password.requestFocus();
            return;
        }

        if (passwordToFirebase.length() < 6) {
            password.setError("Min password should be 6 characters!");
            password.requestFocus();
            return;
        }
        mAuth.createUserWithEmailAndPassword(emailToFirebase, passwordToFirebase)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        User user = new User(emailToFirebase, mobileToFirebase, passwordToFirebase, "", "", "","", "","");
                        FirebaseDatabase.getInstance().getReference("Users")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .setValue(user).addOnCompleteListener(task1 -> {
                                    if (task1.isSuccessful()){
                                        Toast.makeText(RegisterActivity.this, "User has been registered successfully!", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(RegisterActivity.this, OtpActivity.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(RegisterActivity.this, "Failed to register!", Toast.LENGTH_LONG).show();
                                    }
                                });
                    } else {
                        Toast.makeText(RegisterActivity.this, "Failed to register!", Toast.LENGTH_LONG).show();
                    }
                });

    }

}