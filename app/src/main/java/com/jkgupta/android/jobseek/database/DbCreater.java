package com.jkgupta.android.jobseek.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;

import com.jkgupta.android.jobseek.JobPostModel;
import com.jkgupta.android.jobseek.JobViewModel;
import com.jkgupta.android.jobseek.LoginModel;
import com.jkgupta.android.jobseek.RegistrationModal;
import com.jkgupta.android.jobseek.UserModel;

import java.util.ArrayList;

/**
 * Created by Jindal on 12/27/2017.
 */

public class DbCreater extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "joobseek.db";
    public static final int DATABASE_VERSION = 1;
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + UserDataTable.USER_TABLE_NAME;
    private String DROP_JOB_TABLE = "DROP TABLE IF EXISTS " + JobPostTable.JOBPOST_TABLE_NAME;

    public DbCreater(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_JOBSEEK_TABLE = "CREATE TABLE IF NOT EXISTS " + UserDataTable.USER_TABLE_NAME + " ("
                + UserDataTable.USER_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + UserDataTable.USER_COLUMN_EMAIL + " TEXT NOT NULL, "
                + UserDataTable.USER_COLUMN_NAME + " TEXT NOT NULL, "
                + UserDataTable.USER_COLUMN_PASSWORD + " TEXT NOT NULL, "
                + UserDataTable.USER_COLUMN_USER_Type + " TEXT NOT NULL );";

        String SQL_CREATE_JOBPOST_TABLE = "CREATE TABLE IF NOT EXISTS " + JobPostTable.JOBPOST_TABLE_NAME + " ("
                + JobPostTable.JOBPOST_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + JobPostTable.JOBPOST_COLUMN_COMPANY + " TEXT NOT NULL, "
                + JobPostTable.JOBPOST_COLUMN_RECRUITER_ID + " TEXT NOT NULL, "
                + JobPostTable.JOBPOST_COLUMN_DESCRIPTION + " TEXT NOT NULL, "
                + JobPostTable.JOBPOST_COLUMN_RECRUITER_EMAIL + " TEXT NOT NULL, "
                + JobPostTable.JOBPOST_COLUMN_SKILLS + " TEXT NOT NULL );";

        String SQL_CREATE_APPLYJOB_TABLE = "CREATE TABLE IF NOT EXISTS " + ApplyJobTable.APPLIEDJOB_TABLE_NAME + " ("
                + ApplyJobTable.APPLIEDJOB_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ApplyJobTable.APPLIEDJOB_COLUMN_CANDIDATE_ID + " TEXT NOT NULL, "
                + ApplyJobTable.APPLIEDJOB_COLUMN_JOBPOST_ID + " TEXT NOT NULL);";

        // Execute the SQL statement
        Log.v("indb", "createuser");
        db.execSQL(SQL_CREATE_JOBSEEK_TABLE);
        Log.v("indb", "createjob");
        db.execSQL(SQL_CREATE_JOBPOST_TABLE);
        Log.v("indb", "createjobout");
        db.execSQL(SQL_CREATE_APPLYJOB_TABLE);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        // Create tables again
        onCreate(db);
    }

    public void addUser(RegistrationModal user) {
        Log.v("Pune", user.getName());
        SQLiteDatabase db = this.getWritableDatabase();

        Log.v("Pune", "before content model");

        ContentValues values = new ContentValues();
        values.put(UserDataTable.USER_COLUMN_NAME, user.getName());
        values.put(UserDataTable.USER_COLUMN_EMAIL, user.getEmail());
        values.put(UserDataTable.USER_COLUMN_PASSWORD, user.getPassword());
        values.put(UserDataTable.USER_COLUMN_USER_Type, user.getUser_type());

        // Inserting Row
        db.insert(UserDataTable.USER_TABLE_NAME, null, values);
        db.close();
    }


    public UserModel getUser(LoginModel loginModel) {
        //Open the database
        UserModel userModel = new UserModel();
        userModel.setUser_in_Db(false);
        String name = null;

        try {
            SQLiteDatabase db = this.getReadableDatabase();

            String selectQuerySQLCommand = "SELECT * FROM " + UserDataTable.USER_TABLE_NAME + " ORDER BY _id DESC";
            Cursor cursor = db.rawQuery(selectQuerySQLCommand, null);
            if (cursor.moveToFirst()) {

                String email = cursor.getString(cursor.getColumnIndex(UserDataTable.USER_COLUMN_EMAIL));
                String password = cursor.getString(cursor.getColumnIndex(UserDataTable.USER_COLUMN_PASSWORD));
                Log.v("Puneee", email + email.length());
                Log.v("Puneee", password);
                Log.v("Puneee", loginModel.getEmail() + loginModel.getEmail().length());
                Log.v("Puneee", loginModel.getPassword());

                if ((loginModel.getEmail().equals(email)) && (loginModel.getPassword().equals(password))) {

                    name = cursor.getString(cursor.getColumnIndex(UserDataTable.USER_COLUMN_NAME));
                    String user_Type = cursor.getString(cursor.getColumnIndex(UserDataTable.USER_COLUMN_USER_Type));
                    String id = cursor.getString(cursor.getColumnIndex(UserDataTable.USER_COLUMN_ID));
                    userModel.setUser_in_Db(true);
                    userModel.setName(name);
                    userModel.setEmail(email);
                    userModel.setUser_Type(user_Type);
                    userModel.setUser_Id(id);
                    Log.v("Pune1", name);
                    Log.v("Pune1", email);
                }


            }
            cursor.close();

            db.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        //Close the database


        return userModel;


    }

    public void addJob(JobPostModel job) {

        SQLiteDatabase db1 = this.getWritableDatabase();

        Log.v("in add job", "before content model");

        ContentValues values = new ContentValues();
        values.put(JobPostTable.JOBPOST_COLUMN_COMPANY, job.getCompany());
        values.put(JobPostTable.JOBPOST_COLUMN_DESCRIPTION, job.getDescription());
        values.put(JobPostTable.JOBPOST_COLUMN_RECRUITER_ID, job.getRec_id());
        values.put(JobPostTable.JOBPOST_COLUMN_SKILLS, job.getSkills());
        values.put(JobPostTable.JOBPOST_COLUMN_RECRUITER_EMAIL, job.getRec_email());

        // Inserting Row
        db1.insert(JobPostTable.JOBPOST_TABLE_NAME, null, values);
        db1.close();
    }

    public void applyJob(String job_post_id,String candidate_id) {

        SQLiteDatabase db1 = this.getWritableDatabase();

        Log.v("in add job", "before content model");

        ContentValues values = new ContentValues();
        values.put(ApplyJobTable.APPLIEDJOB_COLUMN_JOBPOST_ID,job_post_id );
        values.put(ApplyJobTable.APPLIEDJOB_COLUMN_CANDIDATE_ID, candidate_id);


        // Inserting Row
        db1.insert(ApplyJobTable.APPLIEDJOB_TABLE_NAME, null, values);
        db1.close();
    }

    public ArrayList<JobViewModel> viewJobPosted(Bundle bundle) {
        //Open the database
        ArrayList<JobViewModel> jobs = new ArrayList<>();

        String name = null;

        try {
            SQLiteDatabase db = this.getReadableDatabase();

            String whereCondition = " where recruiter_id=" + bundle.getString("user_id");

            String selectQuerySQLCommand = "SELECT * FROM " + JobPostTable.JOBPOST_TABLE_NAME + whereCondition + " ORDER BY _id DESC";
            Cursor cursor = db.rawQuery(selectQuerySQLCommand, null);


            try {
                while (cursor.moveToNext()) {
                    JobViewModel jobViewModel = new JobViewModel();

                    String skills = cursor.getString(cursor.getColumnIndex(JobPostTable.JOBPOST_COLUMN_SKILLS));
                    String description = cursor.getString(cursor.getColumnIndex(JobPostTable.JOBPOST_COLUMN_DESCRIPTION));
                    String jobPostId = cursor.getString(cursor.getColumnIndex(JobPostTable.JOBPOST_COLUMN_ID));

                    Log.v("viewjob", skills + description);

                    jobViewModel.setSkills(skills);
                    jobViewModel.setDescription(description);
                    jobViewModel.setName(bundle.getString("name"));
                    jobViewModel.setEmail(bundle.getString("email"));
                    jobViewModel.setJob_post_id(jobPostId);
                    jobViewModel.setCandidate_applied(getAppliedNumber(jobPostId));
                    Log.v("viewjob", getAppliedNumber(jobPostId)+"");

                    // todo: fn which returns rows from appliedtable found for job if
                    jobs.add(jobViewModel);

                }
            } finally {
                cursor.close();
            }

//            if (cursor.moveToFirst()) {
//
//
//
//            }
//            cursor.close();

            db.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        //Close the database


        return jobs;


    }

    public ArrayList<JobViewModel> viewJobAll(Bundle bundle) {
        //Open the database
        ArrayList<JobViewModel> jobs = new ArrayList<>();

        String name = null;


        try {
            SQLiteDatabase db = this.getReadableDatabase();

            //String whereCondition = " where recruiter_id=" + bundle.getString("user_id");

            String selectQuerySQLCommand = "SELECT * FROM " + JobPostTable.JOBPOST_TABLE_NAME +  " ORDER BY _id DESC";
            Cursor cursor = db.rawQuery(selectQuerySQLCommand, null);


            try {
                while (cursor.moveToNext()) {
                    JobViewModel jobViewModel = new JobViewModel();

                    String skills = cursor.getString(cursor.getColumnIndex(JobPostTable.JOBPOST_COLUMN_SKILLS));
                    String description = cursor.getString(cursor.getColumnIndex(JobPostTable.JOBPOST_COLUMN_DESCRIPTION));
                    String company = cursor.getString(cursor.getColumnIndex(JobPostTable.JOBPOST_COLUMN_COMPANY));
                    String email = cursor.getString(cursor.getColumnIndex(JobPostTable.JOBPOST_COLUMN_RECRUITER_EMAIL));
                    String jobPostId = cursor.getString(cursor.getColumnIndex(JobPostTable.JOBPOST_COLUMN_ID));

                    Log.v("viewjob", skills + description);

                    jobViewModel.setSkills(skills);
                    jobViewModel.setDescription(description);
                    //jobViewModel.setName(bundle.getString("name"));
                    //jobViewModel.setEmail(bundle.getString("email"));
                    jobViewModel.setCompany(company);
                    jobViewModel.setEmail(email);
                    jobViewModel.setCandidate_id(bundle.getString("user_id"));
//                    jobViewModel.setJob_post_id(jobPostId);
//                    jobViewModel.setCandidate_applied(getAppliedNumber(jobPostId));
                    Log.v("viewjob", getAppliedNumber(jobPostId)+"");

                    // todo: fn which returns rows from appliedtable found for job if
                    jobs.add(jobViewModel);

                }
            } finally {
                cursor.close();
            }

//            if (cursor.moveToFirst()) {
//
//
//
//            }
//            cursor.close();

            db.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        //Close the database


        return jobs;


    }


    public Integer getAppliedNumber(String job_post_id) {
        //Open the database

        Integer candidateApplied=0;

        try {
            SQLiteDatabase db = this.getReadableDatabase();

            String whereCondition = " where job_post_id=" + job_post_id;

            String selectQuerySQLCommand = "SELECT * FROM " + ApplyJobTable.APPLIEDJOB_COLUMN_JOBPOST_ID + whereCondition + " ORDER BY _id DESC";
            Cursor cursor = db.rawQuery(selectQuerySQLCommand, null);


            try {
                cursor.moveToNext();
                candidateApplied=cursor.getCount();
            } finally {
                cursor.close();
            }

//            if (cursor.moveToFirst()) {
//
//
//
//            }
//            cursor.close();

            db.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        //Close the database


        return candidateApplied;


    }



}
