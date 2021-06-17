package com.example.studentsattendance.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.example.studentsattendance.R;
import com.example.studentsattendance.data.Student;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText idEt, fnEt;
    int id=1000;
    ArrayList<Student> arraySt = new ArrayList();
//    static BaseHolderAdapter<B> adapterSt ;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        add_some_rest();
//
//        adapterSt = new BaseHolderAdapter(this, arraySt);
//        idEt = findViewById(R.id.ID);
//        fnEt = findViewById(R.id.FnId);
//        idEt.setText(String.valueOf(++id));
//        fnEt.requestFocus();
//        intent  = new Intent(this, TakeAttendanceActivity.class);
    }
//
//    public void add_some_rest() {
//        arraySt.add(new Student(1, "1111111"));
//        arraySt.add(new Student(2, "222222"));
//        arraySt.add(new Student(3, "33333"));
//    }
//
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu, menu);
//        return true;
//    }
//
//    public boolean onOptionsItemSelected(MenuItem item) {
//        Intent in = new Intent(this, TakeAttendanceActivity.class);
//        startActivity(in);
//        return true;
//    }
//
//  /*  @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        return super.onPrepareOptionsMenu(menu);
//    }*/
//
//    public void addStd(View view) {
//        String fn = fnEt.getText().toString();
//        Student st = new Student(id, fn);
//        arraySt.add(st);
//        adapterSt.notifyDataSetChanged();
//
//        Toast.makeText(this,"Student added",Toast.LENGTH_SHORT).show();
//        idEt.setText(String.valueOf(++id));
//        fnEt.setText("");
//    }
//
//    public void NextInflater(View view) {
//        startActivity(intent);
//       /* ListView lv = findViewById(R.id.lvId);
//        lv.setAdapter(MainActivity.adapterSt);*/
//    }
}
