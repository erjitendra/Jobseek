package com.jkgupta.android.jobseek.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.jkgupta.android.jobseek.RegistrationModal;

/**
 * Created by Jindal on 12/27/2017.
 */

public class DbCreater extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "joobseek.db";
    public static final int DATABASE_VERSION = 1;

    public DbCreater(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + UserDataTable.USER_TABLE_NAME;

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_JOBSEEK_TABLE = "CREATE TABLE " + UserDataTable.USER_TABLE_NAME + " ("
                + UserDataTable.USER_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + UserDataTable.USER_COLUMN_EMAIL + " TEXT NOT NULL, "
                + UserDataTable.USER_COLUMN_NAME + " TEXT NOT NULL, "
                + UserDataTable.USER_COLUMN_PASSWORD + " TEXT NOT NULL, "
                + UserDataTable.USER_COLUMN_USER_Type + " TEXT NOT NULL );";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_JOBSEEK_TABLE);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_USER_TABLE);

        // Create tables again
        onCreate(db);
    }

    public void addUser(RegistrationModal user) {
        Log.v("Pune",user.getName());
        SQLiteDatabase db = this.getWritableDatabase();

        Log.v("Pune", "before content model");

        ContentValues values = new ContentValues();
        values.put(UserDataTable.USER_COLUMN_NAME, user.getName());
        values.put(UserDataTable.USER_COLUMN_EMAIL, user.getEmail());
        values.put(UserDataTable.USER_COLUMN_PASSWORD, user.getPassword());
        values.put(UserDataTable.USER_COLUMN_USER_Type, user.getPassword());

        // Inserting Row
        db.insert(UserDataTable.USER_TABLE_NAME, null, values);
        db.close();
    }

}
