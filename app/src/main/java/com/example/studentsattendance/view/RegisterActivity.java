package com.example.studentsattendance.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.studentsattendance.R;
import com.example.studentsattendance.data.Student;
import com.example.studentsattendance.data.StudentsDatabaseHelper;
import com.example.studentsattendance.helper.BaseActivity;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

import static com.example.studentsattendance.helper.BaseHelper.baseStartActivity;
import static com.example.studentsattendance.helper.BaseHelper.createToast;
import static com.example.studentsattendance.helper.BaseHelper.getDate;

public class RegisterActivity extends BaseActivity {
    public TextInputEditText btnIdStudent, btnFullNamestudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setup();


    }
    private void setup() {
        btnIdStudent = findViewById(R.id.id_student_reg);
        btnFullNamestudent = findViewById(R.id.full_name_student_reg);
    }

    public void addStd(View view) {
        if (Objects.requireNonNull(btnIdStudent.getText()).toString().isEmpty()) {
            btnIdStudent.setError("Please add the student id");
        }
        if (Objects.requireNonNull(btnFullNamestudent.getText()).toString().isEmpty()) {
            btnFullNamestudent.setError("Please add the student name");
        }
        if (!btnIdStudent.getText().toString().isEmpty() && !btnFullNamestudent.getText().toString().isEmpty()) {
            createToast(this, "Student added successfully");

            int fileNb = Integer.valueOf(btnIdStudent.getText().toString());
            String fullName = btnFullNamestudent.getText().toString();
            helper.addStudent(new Student(fileNb, fullName));
            this.finish();
            //baseStartActivity(this, HomeActivity.class, true);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
