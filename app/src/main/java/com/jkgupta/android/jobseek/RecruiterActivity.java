package com.jkgupta.android.jobseek;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jkgupta.android.jobseek.database.DbCreater;

import java.util.ArrayList;

public class RecruiterActivity extends AppCompatActivity {
    TextView name,email;
    Button createJob;
    Button logout;
    RecyclerView recyclerView;
    Bundle b;
    private RecyclerView.Adapter adapter;
    private DbCreater dbCreater = new DbCreater(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruiter);
        createJob = findViewById(R.id.bt_postJob);
        logout=findViewById(R.id.bt_rec_logout);



        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));


        b = getIntent().getExtras();
name=findViewById(R.id.tv_rec_name);
email=findViewById(R.id.tv_rec_email);

name.setText("Name: "+b.getString("name"));
email.setText("Email: "+b.getString("email"));


        createJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), PostJobActivity.class);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), LoginActivity.class);

                startActivity(intent);
            }
        });
        ArrayList<JobViewModel> jobs=dbCreater.viewJobPosted(b);
        Log.v("in add job", jobs.toString());
        adapter = new RecruiterAdapter(jobs, this);
        recyclerView.setAdapter(adapter);
        onBackPressed();
    }

    @Override
    public void onBackPressed() {

    }
}
