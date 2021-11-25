package com.example.sellhouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class OtpActivity extends AppCompatActivity {

    EditText otp;
    Button verifyOtp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        otp = findViewById(R.id.extOtp);
        verifyOtp = findViewById(R.id.btnVerify);

        verifyOtp.setOnClickListener(view -> {
            Intent intent = new Intent(OtpActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }
}