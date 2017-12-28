package com.jkgupta.android.jobseek;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RecruiterActivity extends AppCompatActivity {
TextView recruiterInfo;
Button createJob;
RecyclerView recyclerView;
    Bundle b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruiter);
        recruiterInfo=findViewById(R.id.tv_rec_info);
        createJob=findViewById(R.id.bt_postJob);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
 b=getIntent().getExtras();
recruiterInfo.setText("Name: "+b.getString("name")+"\n"+
        "Email: "+b.getString("email")+"\n"+
        "Id: "+b.getString("user_id")+"\n"+
        "Type: "+b.getString("user_type")+"\n");
createJob.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getBaseContext(), PostJobActivity.class);
        intent.putExtras(b);
        startActivity(intent);
    }
});
    }
}
