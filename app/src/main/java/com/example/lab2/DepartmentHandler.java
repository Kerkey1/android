package com.example.lab2;

import static com.example.lab2.DBContract.Department.TABLE_NAME;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DepartmentHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "department";


    public DepartmentHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + DBContract.Department.COLUMN_NAME_KEY_ID + " INTEGER PRIMARY KEY," +
                DBContract.Department.COLUMN_NAME_DEPARTMENT + " TEXT" + ")";

        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addDepartment(Department department) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBContract.Department.COLUMN_NAME_DEPARTMENT, department.getDepartment());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<Department> getAllDepartments() {
        List<Department> departmentsList = new ArrayList<Department>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Department department = new Department();
                department.setID(Integer.parseInt(cursor.getString(0)));
                department.setDepartment(cursor.getString(1));
                departmentsList.add(department);
            } while (cursor.moveToNext());
        }
        return departmentsList;
    }

    public int getId(String login) {
        int exist = -1;

        List<Department> departments = getAllDepartments();
        for (Department dep : departments) {
            if (dep.getDepartment().equals(login))
                exist = dep.getID();
        }

        return exist;
    }

    public String getDepartmentById(int id) {
        String name = "";
        List<Department> departments = getAllDepartments();
        for (Department dep : departments) {
            if (dep.getID() == id)
                name = dep.getDepartment();
        }

        return name;
    }

}
