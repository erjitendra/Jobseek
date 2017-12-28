package com.jkgupta.android.jobseek;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jkgupta.android.jobseek.database.DbCreater;

public class PostJobActivity extends AppCompatActivity {
    private DbCreater dbCreater = new DbCreater(this);
    EditText skill,company,description;
    Button createJob;
    Bundle b=getIntent().getExtras();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_job);
        skill=findViewById(R.id.et_jobpost_skills);
        company=findViewById(R.id.et_jobpost_company);
        description=findViewById(R.id.et_jobpost_description);
        createJob=findViewById(R.id.bt_postjob_create);


        createJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           JobPostModel jobPostModel=new JobPostModel();
           jobPostModel.setRec_id(b.getString("user_id"));
           jobPostModel.setSkills(skill.getText().toString());
           jobPostModel.setCompany(company.getText().toString());
           jobPostModel.setDescription(description.getText().toString());
              dbCreater.addJob(jobPostModel);
                Intent intent = new Intent(getBaseContext(), RecruiterActivity.class);
                startActivity(intent);
            }
        });


    }
}
