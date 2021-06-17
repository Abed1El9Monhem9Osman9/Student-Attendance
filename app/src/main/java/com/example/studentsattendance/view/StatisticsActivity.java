package com.example.studentsattendance.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.studentsattendance.R;
import com.example.studentsattendance.data.Student;
import com.example.studentsattendance.helper.BaseActivity;
import com.example.studentsattendance.helper.BaseHelper;

import java.util.ArrayList;
import java.util.List;

public class StatisticsActivity extends BaseActivity {

    Button btnResult;
    TextView txtResult;
    Spinner nameStudentSp, sessionTitleSp;
    String fullName, sessionTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        btnResult = findViewById(R.id.btn_stat_id);
        txtResult = findViewById(R.id.result_stat_id);
        nameStudentSp = findViewById(R.id.full_name_id_stat);
        sessionTitleSp = findViewById(R.id.title_id_stat);

        List<Student> stdList = helper.getAllStudents();
        List<String> iList = new ArrayList<>();
        for (Student std : stdList) {
            iList.add(std.getFullName());
        }
        if (stdList.isEmpty() || stdList == null) {
            stdList = new ArrayList<>();
        }
        List<String> titleList = helper.getAllSessions();
        if (titleList.isEmpty() || titleList == null) {
            titleList = new ArrayList<>();
        }

        BaseHelper.nameSpinner(nameStudentSp, iList, this);
        BaseHelper.nameSpinner(sessionTitleSp, titleList, this);

        nameStudentSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fullName = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        sessionTitleSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sessionTitle = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    public void attendanceCalculate (View view){
        try {
            List<String> ls = helper.getStatAttendance(fullName, sessionTitle);
            int x1 = Integer.parseInt(ls.get(0));
            int x2 = Integer.parseInt(ls.get(1));
            double result = ((double) x1 / x2) * 100;
            txtResult.setText(String.format("%.2f", result) + " % ");
        } catch (Exception e) {
            txtResult.setText(" % ");
            Log.d("zzzzzzzzzzz", "NO ATTENDANCE SUBMITTED !!");
            BaseHelper.createToast(this, "NO ATTENDANCE SUBMITTED !!");
        }
    }

}
