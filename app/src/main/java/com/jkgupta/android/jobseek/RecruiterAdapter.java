package com.jkgupta.android.jobseek;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Jindal on 12/28/2017.
 */

public class RecruiterAdapter extends RecyclerView.Adapter<RecruiterAdapter.ViewHolder> {
    private List<JobViewModel> jobs;
    private Context context;
    private JobViewModel job;

    public RecruiterAdapter(List<JobViewModel> jobs, Context context) {
        this.jobs = jobs;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_view_rec, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        job = jobs.get(position);
        Log.v("adapter", job.getSkills());

        holder.skills.setText("Job Type: "+job.getSkills());
        holder.name.setText("Name: "+job.getName());
        holder.description.setText("Description: "+job.getDescription());
        holder.email.setText("Email: "+job.getEmail());
        holder.appliedNo.setText("No. of  Candidates Applied: "+job.getCandidate_applied());

        Log.v("adapter", job.getEmail());

    }

    @Override
    public int getItemCount() {
        return jobs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView skills;
        public TextView description;
        public TextView email;
        public TextView name;
        public TextView appliedNo;


        public ViewHolder(View itemView) {
            super(itemView);
            skills = itemView.findViewById(R.id.tv_jobview_rec_skills);
            description = itemView.findViewById(R.id.tv_jobview_rec_description);
            email = itemView.findViewById(R.id.tv_jobview_rec_email);
            name = itemView.findViewById(R.id.tv_jobview_rec_name);
            appliedNo=itemView.findViewById(R.id.tv_jobview_rec_applied);

        }


    }
}
