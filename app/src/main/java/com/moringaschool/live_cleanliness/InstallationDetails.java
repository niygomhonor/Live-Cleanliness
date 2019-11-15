package com.moringaschool.live_cleanliness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class InstallationDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_installation_details);


        Intent clean=getIntent();
        String a=clean.getStringExtra("Installation");

    }
}
