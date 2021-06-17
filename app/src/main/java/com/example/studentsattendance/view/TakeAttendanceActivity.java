package com.example.studentsattendance.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentsattendance.R;
import com.example.studentsattendance.adapter.BaseHolderAdapter;
import com.example.studentsattendance.data.BaseClass;
import com.example.studentsattendance.data.Student;
import com.example.studentsattendance.helper.BaseActivity;
import com.example.studentsattendance.helper.BaseHelper;

import java.util.ArrayList;
import java.util.List;

import static com.example.studentsattendance.helper.BaseHelper.baseStartActivity;
import static com.example.studentsattendance.helper.BaseHelper.createToast;

public class TakeAttendanceActivity extends BaseActivity implements BaseHelper.OnItemClick {
    BaseHolderAdapter<Student> studentAdapter;
    List<Student> arraySt = new ArrayList();
    RecyclerView recView;
    Spinner spinner;
    private String title = "";

    //    TextInputEditText txtinputEdittxt ;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.take_attendance);

        recView = findViewById(R.id.my_recycler_view);
        spinner = findViewById(R.id.spinner_id);
        List<String> sessionList = helper.getAllSessions();
        if (sessionList.isEmpty() || sessionList == null) {
            sessionList = new ArrayList<>();
        }

        try {
            BaseHelper.nameSpinner(spinner, sessionList, this);
        } catch (Exception e) {


        }
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                title = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        if (helper.getAllStudents() != null && !helper.getAllStudents().isEmpty()) {
            arraySt = helper.getAllStudents();
        }
        studentAdapter = new BaseHolderAdapter<Student>(arraySt, this, 0);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recView.setLayoutManager(manager);
        recView.setAdapter(studentAdapter);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_attendance, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.submit_att) {
            createToast(this, "Submitted");
            if (title.isEmpty()) {
                createToast(this, "Select a title");
            } else {
               helper.addStudentAttendance(arraySt, title);
                baseStartActivity(this, HomeActivity.class, true);
            }
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void getStd(List<? extends BaseClass> value) {
        arraySt = (List<Student>) value;
    }
}
