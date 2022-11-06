package com.example.lab2;

import static com.example.lab2.DBContract.Users.TABLE_NAME;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "users";


    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + DBContract.Users.COLUMN_NAME_KEY_ID + " INTEGER PRIMARY KEY," +
                DBContract.Users.COLUMN_NAME_LOGIN + " TEXT," + DBContract.Users.COLUMN_NAME_PASSWORD + " TEXT," +
                DBContract.Users.COLUMN_NAME_USER_DEPARTMENT + " TEXT" + ")";

        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBContract.Users.COLUMN_NAME_LOGIN, user.getLogin());
        values.put(DBContract.Users.COLUMN_NAME_PASSWORD, user.getPass());
        values.put(DBContract.Users.COLUMN_NAME_USER_DEPARTMENT, user.getDepartment());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<User>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setID(Integer.parseInt(cursor.getString(0)));
                user.setLogin(cursor.getString(1));
                user.setPass(cursor.getString(2));
                user.setDepartment(Integer.parseInt(cursor.getString(3)));
                usersList.add(user);
            } while (cursor.moveToNext());
        }
        return usersList;
    }

    public int isUser(String login, String password) {
        int exist = -1;

        List<User> users = getAllUsers();
        for (User usr : users) {
            String log = "Id: " + usr.getID() + " ,Login: " + usr.getLogin() + " ,Password: " + usr.getPass() + " ,Dep: " + usr.getDepartment();
            Log.v("Loading...", log);
            if (usr.getLogin().equals(login) && usr.getPass().equals(password))
                exist = usr.getID();
        }

        return exist;
    }

    public int getId(String login) {
        int exist = -1;

        List<User> users = getAllUsers();
        for (User usr : users) {
            if (usr.getLogin().equals(login))
                exist = usr.getID();
        }

        return exist;
    }

    public int getDepartments(String login) {
        int exist = -1;

        List<User> users = getAllUsers();
        for (User usr : users) {
            if (usr.getLogin().equals(login))
                exist = usr.getDepartment();
        }

        return exist;
    }

    public int deleteUserByName(String login) {
        SQLiteDatabase db = this.getWritableDatabase();
        int id = getId(login);
        int i = db.delete(TABLE_NAME, "id=" + id, null);
        Log.v("NEW...", String.valueOf(i));
        db.close();
        return i;
    }

    public int updatePassword(int id, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password", password);
        int i = db.update(TABLE_NAME, contentValues, "id=" + id, null);
        return i;
    }

}

