package com.jkgupta.android.jobseek.database;

import android.provider.BaseColumns;

/**
 * Created by Jindal on 12/27/2017.
 */

public class JobPostTable implements BaseColumns {

    public final static String JOBPOST_TABLE_NAME = "jobpost";
    public final static String JOBPOST_COLUMN_RECRUITER_ID = "recruiter_id";
    public final static String JOBPOST_COLUMN_ID = BaseColumns._ID;
    public final static String JOBPOST_COLUMN_COMPANY = "company";
    public final static String JOBPOST_COLUMN_SKILLS = "skills";
    public final static String JOBPOST_COLUMN_DESCRIPTION = "description";
}
