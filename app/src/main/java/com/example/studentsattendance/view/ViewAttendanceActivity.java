package com.example.studentsattendance.view;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.studentsattendance.R;
import com.example.studentsattendance.adapter.BaseHolderAdapter;
import com.example.studentsattendance.data.BaseClass;
import com.example.studentsattendance.data.Session;
import com.example.studentsattendance.data.Student;
import com.example.studentsattendance.helper.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class ViewAttendanceActivity extends BaseActivity {

    BaseHolderAdapter<Session> arrayAd;
    List<Session> list = new ArrayList<>();
    //List<Session> list1 = new ArrayList<>();
    RecyclerView recView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attendance);

        recView = findViewById(R.id.session_view);
        list = helper.getAllSession();

        arrayAd = new BaseHolderAdapter(list, 1, this);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recView.setLayoutManager(manager);
        recView.setAdapter(arrayAd);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
