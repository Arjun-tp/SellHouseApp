package com.example.sellhouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SignScreenActivity extends AppCompatActivity {
    Button signIn, signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_screen);
        signIn = findViewById(R.id.btnSignIn);
        signUp = findViewById(R.id.btnSignUp);


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignScreenActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignScreenActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}