package com.example.studentsattendance.view;

import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentsattendance.R;

import static com.example.studentsattendance.helper.BaseHelper.baseStartActivity;
import static com.example.studentsattendance.helper.BaseHelper.duration;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(() -> baseStartActivity(SplashActivity.this, HomeActivity.class, true), duration);
    }
}
