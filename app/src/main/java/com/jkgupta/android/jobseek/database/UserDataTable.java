package com.jkgupta.android.jobseek.database;

import android.provider.BaseColumns;

/**
 * Created by Jindal on 12/27/2017.
 */

public class UserDataTable implements BaseColumns {

    public final static String USER_TABLE_NAME = "user";
    public final static String USER_COLUMN_USER_Type = "user_type";
    public final static String USER_COLUMN_ID = BaseColumns._ID;
    public final static String USER_COLUMN_EMAIL = "email";
    public final static String USER_COLUMN_NAME = "name";
    public final static String USER_COLUMN_PASSWORD = "password";

    public UserDataTable() {
    }
}