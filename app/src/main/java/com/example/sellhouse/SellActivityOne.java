package com.example.sellhouse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class SellActivityOne extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_one);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}