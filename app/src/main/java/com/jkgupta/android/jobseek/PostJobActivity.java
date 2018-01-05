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
    private DbCreater dbCreater= new DbCreater(this) ;
    EditText skill,company,description;
    Button createJob;
    Bundle b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_job);
        skill=findViewById(R.id.et_jobpost_skills);
        company=findViewById(R.id.et_jobpost_company);
        description=findViewById(R.id.et_jobpost_description);
        createJob=findViewById(R.id.bt_postjob_create);
        b=getIntent().getExtras();


        createJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           JobPostModel jobPostModel=new JobPostModel();
           jobPostModel.setRec_id(b.getString("user_id"));
           jobPostModel.setRec_email(b.getString("email"));
           jobPostModel.setSkills(skill.getText().toString());
           jobPostModel.setCompany(company.getText().toString());
           jobPostModel.setDescription(description.getText().toString());
                Log.v("indb1","createjob"+jobPostModel.getRec_id());
                Log.v("indb2","createjob"+jobPostModel.getCompany());
                Log.v("indb3","createjob"+jobPostModel.getSkills());
                Log.v("indb4","createjob"+jobPostModel.getDescription());

             dbCreater.addJob(jobPostModel);
                Log.v("indb5","createjob"+dbCreater);
                Intent intent = new Intent(getBaseContext(), RecruiterActivity.class);
                intent.putExtras(b);
                startActivity(intent);
            }
        });

onBackPressed();
    }

    @Override
    public void onBackPressed() {



    }
}
