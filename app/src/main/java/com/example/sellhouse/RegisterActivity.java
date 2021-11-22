package com.example.sellhouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    Button register;
    EditText email, mobile, password, confirmPassword;

    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("Users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register = findViewById(R.id.btnRegister);
        email = findViewById(R.id.extEmail);
        mobile = findViewById(R.id.extMobile);
        password = findViewById(R.id.extPassword);
        confirmPassword = findViewById(R.id.extConfirmPassword);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailToFirebase = email.getText().toString();
                String mobileToFirebase = mobile.getText().toString();
                String passwordToFirebase = password.getText().toString();
                HashMap<String , String> userMap = new HashMap<>();
                userMap.put("email", emailToFirebase);
                userMap.put("mobile", mobileToFirebase);
                userMap.put("password", passwordToFirebase);
                root.push().setValue(userMap);


                Intent intent = new Intent(RegisterActivity.this, OtpActivity.class);
                startActivity(intent);

            }
        });

    }
}