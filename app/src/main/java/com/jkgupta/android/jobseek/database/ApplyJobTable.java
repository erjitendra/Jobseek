package com.jkgupta.android.jobseek.database;

import android.provider.BaseColumns;

/**
 * Created by Jindal on 12/29/2017.
 */

    public class ApplyJobTable implements BaseColumns {
    public final static String APPLIEDJOB_TABLE_NAME = "appliedjob";
    public final static String APPLIEDJOB_COLUMN_JOBPOST_ID = "job_post_id";
    public final static String APPLIEDJOB_COLUMN_ID = BaseColumns._ID;
    public final static String APPLIEDJOB_COLUMN_CANDIDATE_ID = "candidate_id";

}
