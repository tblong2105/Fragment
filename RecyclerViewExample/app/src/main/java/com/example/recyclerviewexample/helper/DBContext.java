package com.example.recyclerviewexample.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.recyclerviewexample.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class DBContext extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "StudentManagement";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "Student";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_CLASS = "Class";
    public static final String COLUMN_ID = "Id";

    public DBContext(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.i(DATABASE_NAME, "Create table");
        String queryCreateTable = "CREATE TABLE " + TABLE_NAME + " ( " +
               COLUMN_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " VARCHAR (255) NOT NULL, "
                + COLUMN_CLASS + " VARCHAR (255) NOT NULL" +
                ")";

        sqLiteDatabase.execSQL(queryCreateTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public List<Student> getStudentList() {

        Log.e(DATABASE_NAME, "GEt DATA");


        List<Student> arrayList = new ArrayList<>();
        String sql = "select * from "+TABLE_NAME;

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        while (c.moveToNext()){

            int id = c.getInt(c.getColumnIndexOrThrow(COLUMN_ID));
            String name = c.getString(c.getColumnIndexOrThrow(COLUMN_NAME));
            String _class = c.getString(c.getColumnIndexOrThrow(COLUMN_CLASS));

            arrayList.add(new Student(id, name, _class));

        }

        return arrayList;
    }


    public void addStudent(Student student) {
        SQLiteDatabase db = getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, student.getName());
        values.put(COLUMN_CLASS, student.get_class());

        db.insert(TABLE_NAME,null,values);
    }


}
