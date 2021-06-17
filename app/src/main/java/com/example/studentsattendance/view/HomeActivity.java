package com.example.studentsattendance.view;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.studentsattendance.R;
import com.example.studentsattendance.helper.BaseActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.example.studentsattendance.helper.BaseHelper.baseStartActivity;
import static com.example.studentsattendance.helper.BaseHelper.createToast;

public class HomeActivity extends BaseActivity implements View.OnClickListener {
    private Button btn_reg, btn_attendance, btn_view, btn_stat, btn_reset, btn_session;
    FloatingActionButton fab, fab1, fab2;
    Boolean isFABOpen = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setup();
    }

    private void setup() {
        btn_attendance = findViewById(R.id.btn_take_attendance);
        btn_view = findViewById(R.id.btn_view_attendance);
        btn_stat = findViewById(R.id.btn_statistics);

        btn_attendance.setOnClickListener(this);
        btn_view.setOnClickListener(this);
        btn_stat.setOnClickListener(this);

        fab =  findViewById(R.id.fab);
        fab1 = findViewById(R.id.fab1);
        fab2 = findViewById(R.id.fab2);

        fab1.setVisibility(View.GONE);
        fab2.setVisibility(View.GONE);

        fab.setOnClickListener(v -> {
            if (!isFABOpen) {
                HomeActivity.this.closeFABMenu();
            }
            else{
                HomeActivity.this.showFABMenu();
            }
        });

        fab1.setOnClickListener(v -> {
            baseStartActivity(HomeActivity.this, RegisterActivity.class, false);
        });

        fab2.setOnClickListener(v -> {
            baseStartActivity(HomeActivity.this, TitleActivity.class, false);
        });
    }

    private void showFABMenu(){
        isFABOpen=false;
        fab1.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
        fab2.animate().translationY(-getResources().getDimension(R.dimen.standard_105));
        fab1.setVisibility(View.VISIBLE);
        fab2.setVisibility(View.VISIBLE);
    }

    private void closeFABMenu(){
        isFABOpen=true;
        fab1.animate().translationY(0);
        fab2.animate().translationY(0);
        fab1.setVisibility(View.INVISIBLE);
        fab2.setVisibility(View.INVISIBLE);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.remove_students) {
            createToast(this, ";;;;");
            helper.deleteAllStudents(this);
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_take_attendance:
                baseStartActivity(HomeActivity.this, TakeAttendanceActivity.class, false);
                break;
            case R.id.btn_view_attendance:
                baseStartActivity(HomeActivity.this, ViewAttendanceActivity.class, false);
                break;
            case R.id.btn_statistics:
                baseStartActivity(HomeActivity.this, StatisticsActivity.class, false);
                break;
            default:
        }
    }
}
