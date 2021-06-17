package com.example.studentsattendance.helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.studentsattendance.data.BaseClass;
import com.example.studentsattendance.data.Student;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class BaseHelper {

    public static void baseStartActivity(Activity a, Class cs, boolean x) {
        a.startActivity(new Intent(a, cs));
        if (x) {
            a.finish();
        }
    }

    public static final int duration = 1500;
    public static final String KEY_POST_USER_ID_FK = "123";
    public static final String KEY_STD_USER_FN = "321";

    public static String getDate() {
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        return date;
    }

    public static void createToast(Context c, String message) {
        Toast.makeText(c, message, Toast.LENGTH_SHORT).show();
    }

    public static interface OnItemClick {
        void getStd(List<? extends BaseClass> value);
    }

//    public static interface getSessionTitle {
//        String getTitle = null;
//    }

    public static void nameSpinner(Spinner spinner, List<String> list, Context context) {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, (List<String>) list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }
}
