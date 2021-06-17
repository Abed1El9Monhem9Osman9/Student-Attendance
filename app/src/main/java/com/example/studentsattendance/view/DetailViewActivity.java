package com.example.studentsattendance.view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.DatePicker;

import com.example.studentsattendance.R;
import com.example.studentsattendance.adapter.BaseHolderAdapter;
import com.example.studentsattendance.data.Student;
import com.example.studentsattendance.helper.BaseActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DetailViewActivity extends BaseActivity implements DatePicker.OnDateChangedListener {
    DatePicker datePicker;
    RecyclerView recyclerView;
    List<Student> listStd = new ArrayList<>();
    BaseHolderAdapter adapter;
    String title = "";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        title = getIntent().getStringExtra("123").toString();

        recyclerView = findViewById(R.id.rec_view_details);
        datePicker = findViewById(R.id.data_picker_view);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            datePicker.setOnDateChangedListener(this);
        }



    }

    @Override
    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = fmt.parse(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
            String dt = fmt.format(date);
            List<Student> s = helper.getDetailedStudents(dt, title);

            adapter = new BaseHolderAdapter<Student>(s, 2, this);
            recyclerView.setAdapter(adapter);


        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
