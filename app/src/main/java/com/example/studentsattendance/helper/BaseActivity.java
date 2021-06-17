package com.example.studentsattendance.helper;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.studentsattendance.data.StudentsDatabaseHelper;

public class BaseActivity extends AppCompatActivity {
    public StudentsDatabaseHelper helper;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper = StudentsDatabaseHelper.getInstance(this);
    }
}
