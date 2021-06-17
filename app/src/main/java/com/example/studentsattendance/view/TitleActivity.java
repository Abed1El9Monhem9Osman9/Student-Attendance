package com.example.studentsattendance.view;

import android.os.Bundle;
import android.view.View;

import com.example.studentsattendance.R;
import com.example.studentsattendance.data.Session;
import com.example.studentsattendance.helper.BaseActivity;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

import static com.example.studentsattendance.helper.BaseHelper.baseStartActivity;
import static com.example.studentsattendance.helper.BaseHelper.createToast;

public class TitleActivity extends BaseActivity {
    public TextInputEditText sessionTitleEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);
        setup();


    }

    private void setup() {
        sessionTitleEt = findViewById(R.id.id_session_editTxt);
    }

    public void addSession(View view) {

        if (Objects.requireNonNull(sessionTitleEt.getText()).toString().isEmpty()) {
            sessionTitleEt.setError("Please add the session title");
        }
        if ( !sessionTitleEt.getText().toString().isEmpty()) {

            String title = sessionTitleEt.getText().toString();
            if (title.isEmpty()) {
                createToast(this, "Please add the session title");
            } else {
                helper.addSession(new Session(title));
                createToast(this, "Session added successfully");
                //baseStartActivity(this, HomeActivity.class, true);
                this.finish();
            }
        }
    }
}
