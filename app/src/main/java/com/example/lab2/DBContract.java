package com.example.lab2;

import android.provider.BaseColumns;

public class DBContract {
    private DBContract() {
    }

    public static class Users implements BaseColumns {
        public static final String TABLE_NAME = "users";
        public static final String COLUMN_NAME_KEY_ID = "id";
        public static final String COLUMN_NAME_LOGIN = "login";
        public static final String COLUMN_NAME_PASSWORD = "password";
        public static final String COLUMN_NAME_USER_DEPARTMENT = "department";
    }

    public static class Department implements BaseColumns {
        public static final String TABLE_NAME = "users";
        public static final String COLUMN_NAME_KEY_ID = "id";
        public static final String COLUMN_NAME_DEPARTMENT = "department";
    }
}
