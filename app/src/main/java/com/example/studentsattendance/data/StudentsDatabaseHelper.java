package com.example.studentsattendance.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static com.example.studentsattendance.helper.BaseHelper.KEY_POST_USER_ID_FK;
import static com.example.studentsattendance.helper.BaseHelper.KEY_STD_USER_FN;
import static com.example.studentsattendance.helper.BaseHelper.createToast;
import static com.example.studentsattendance.helper.BaseHelper.getDate;

public class StudentsDatabaseHelper extends SQLiteOpenHelper {
    public static final List<String> LIST = new ArrayList<>();
    // Database Info
    private static final String DATABASE_NAME = "studentsDatabase.db";
    private static final int DATABASE_VERSION = 15;
    private static final String TABLE_USERS = "student";
    private static final String TABLE_USERS_ATTENDANCE = "attendanceStudents";
    // User Table Columns
    private static final String KEY_USER_ID = "id";
    private static final String KEY_USER_NAME = "userName";
    private static final String KEY_USER_FILENUMBER = "userFileNumber";

    public StudentsDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static StudentsDatabaseHelper sInstance;

    public static synchronized StudentsDatabaseHelper getInstance(Context context) {

        if (sInstance == null) {
            sInstance = new StudentsDatabaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    // Called when the database is created for the FIRST time.
    // If a database already exists on disk with the same DATABASE_NAME, this method will NOT be called.
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE student(ID INTEGER PRIMARY KEY AUTOINCREMENT,FILENUMBER INTEGER,FULLNAME TEXT);");
        db.execSQL("CREATE TABLE session(ID_SESSION INTEGER PRIMARY KEY AUTOINCREMENT,TITLE TEXT);");
        db.execSQL("CREATE TABLE attendanceStudents(ID_ATT INTEGER PRIMARY KEY AUTOINCREMENT,FILENUMBER_ATT INTEGER,FULLNAME_ATT TEXT, DATE_ATT TEXT, CHECKED INTEGER, TITLE TEXT);");
    }

    // Called when the database needs to be upgraded.
    // This method will only be called if a database already exists on disk with the same DATABASE_NAME,
    // but the DATABASE_VERSION is different than the version of the database that exists on disk.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            // Simplest implementation is to drop all old tables and recreate them
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
            onCreate(db);
        }
    }

    public void addStudent(Student std) {
        // Create and/or open the database for writing
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put("FILENUMBER", std.getFileNumber());
            values.put("FULLNAME", std.getFullName());
//            values.put("DATE", std.getDate());

            // Notice how we haven't specified the primary key. SQLite auto increments the primary key column.
            db.insertOrThrow(TABLE_USERS, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to add student to the database");
        } finally {
            db.endTransaction();
        }
    }

    public void addSession(Session session) {
        // Create and/or open the database for writing
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put("TITLE", session.getTitle());

            // Notice how we haven't specified the primary key. SQLite auto increments the primary key column.
            db.insertOrThrow("session", null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to add session to the database");
        } finally {
            db.endTransaction();
        }
    }

    public void addStudentAttendance(List<Student> lsStd, String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            for (Student std : lsStd) {
                values.put("FILENUMBER_ATT", std.getFileNumber());
                values.put("FULLNAME_ATT", std.getFullName());
                values.put("DATE_ATT", getDate());
                values.put("CHECKED", std.getChecked());
                values.put("TITLE", title);
                db.insert("attendanceStudents", null, values);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public void deleteAllStudents(Context context) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            // Order of deletions is important when foreign key relationships exist.
            db.delete(TABLE_USERS, null, null);
            db.delete(TABLE_USERS_ATTENDANCE, null, null);
            db.delete("session", null, null);
            db.setTransactionSuccessful();

            File data = Environment.getDataDirectory();
            String currentDBPath = "/data/com.example.studentsattendance/databases/" + DATABASE_NAME;
            File currentDB = new File(data, currentDBPath);
            SQLiteDatabase.deleteDatabase(currentDB);

        } catch (Exception e) {
            Log.d(TAG, "Error while trying to delete all posts and users");
        } finally {
            db.endTransaction();

        }
    }

    public List<Student> getAllStudents() {
        ArrayList<Student> list = new ArrayList<>();
        Cursor curs = getReadableDatabase().rawQuery("SELECT * FROM student;", null);
        curs.moveToFirst();
        while (!curs.isAfterLast()) {
            //if(curs.isNull(0)) { list = new ArrayList<>();}
            list.add(new Student(curs.getInt(1), curs.getString(2)));
            curs.moveToNext();
        }

        return list;
    }

    public List<String> getAllSessions() {
        List<String> list = new ArrayList<>();
        Cursor curs = getReadableDatabase().rawQuery("SELECT * FROM session ;", null);
        curs.moveToFirst();
        while (!curs.isAfterLast()) {
            //if(curs.isNull(0)) { list = new ArrayList<>();}
            list.add(curs.getString(1));
            curs.moveToNext();
        }

        return list;
    }

    public List<Session> getAllSession() {
        List<Session> list = new ArrayList<>();
        Cursor curs = getReadableDatabase().rawQuery("SELECT * FROM session ;", null);
        curs.moveToFirst();
        while (!curs.isAfterLast()) {
            //if(curs.isNull(0)) { list = new ArrayList<>();}
            list.add(new Session(curs.getInt(0), curs.getString(1)));
            curs.moveToNext();
        }

        return list;
    }

    public List<String> getStatAttendance(String fullName, String sessionTitle) {
        List<String> list = new ArrayList<>();
        String query = "select count(*) from attendanceStudents" +
                " where TITLE = '" + sessionTitle + "' and FULLNAME_ATT = '" + fullName + "' and CHECKED=1 " +
                "UNION select count(*) as perc from attendanceStudents " +
                "where TITLE = '" + sessionTitle + "' and FULLNAME_ATT = '" + fullName + "';";

        try {
            Cursor curs = getReadableDatabase().rawQuery(query, null);
            curs.moveToFirst();
            while (!curs.isAfterLast()) {
                //if(curs.isNull(0)) { list = new ArrayList<>();}
                list.add(String.valueOf(curs.getInt(0)));
                curs.moveToNext();
            }
        } catch (Exception e) {
            Log.d("zzzzzzzzzzz", "error while trying to get attendance");
        }
        return list;
    }


    public List<Student> getDetailedStudents(String date, String sessionTitle) {
        List<Student> list = new ArrayList<>();
        String query = "select * from attendanceStudents where title = '"+sessionTitle+"' and DATE_ATT='"+date+"' order by FULLNAME_ATT DESC;";
        try {
            Cursor curs = getReadableDatabase().rawQuery(query, null);
            curs.moveToFirst();
            while (!curs.isAfterLast()) {
                //if(curs.isNull(0)) { list = new ArrayList<>();}
                list.add(new Student(curs.getInt(1), curs.getString(2), curs.getString(3), curs.getInt(4)));
                curs.moveToNext();
            }
        } catch (Exception e) {
            Log.d("zzzzzzzzzzz", "error while trying to get attendance");
        }
        return list;
    }
}
