package com.jkgupta.android.jobseek;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.jkgupta.android.jobseek.database.DbCreater;

import java.util.List;



public class CandidateAdapter extends RecyclerView.Adapter<CandidateAdapter.ViewHolder> {
    private List<JobViewModel> jobs;
    private Context context;


    public CandidateAdapter(List<JobViewModel> jobs, Context context) {
        this.jobs = jobs;
        this.context = context;
    }

    @Override
    public CandidateAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_view_candidate, parent, false);
        return new CandidateAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final CandidateAdapter.ViewHolder holder, final int position) {
        JobViewModel job;
        job = jobs.get(position);
        Log.v("adapter", job.getSkills());

        holder.skills.setText("Job Type: "+job.getSkills());
        holder.company.setText("Company: "+job.getCompany());
        holder.description.setText("Description: "+job.getDescription());
        holder.email.setText("Email: "+job.getEmail());
        holder.appliedStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbCreater dbCreater = new DbCreater(context);
                dbCreater.applyJob(jobs.get(position).getJob_post_id(), jobs.get(position).getCandidate_id());
                holder.appliedStatus.setText("Applied");
                notifyItemChanged(position);
            }
        });
        //holder.appliedStatus.setText("No. of Applied: "+job.getCandidate_applied());

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
        public TextView company;
        public Button appliedStatus;


        public ViewHolder(View itemView) {
            super(itemView);
            skills = itemView.findViewById(R.id.tv_jobview_candidate_skills);
            description = itemView.findViewById(R.id.tv_jobview_candidate_description);
            email = itemView.findViewById(R.id.tv_jobview_candidate_email);
            company = itemView.findViewById(R.id.tv_jobview_company_name);
            appliedStatus=itemView.findViewById(R.id.btn_jobview_candidate_applied);

        }


    }


}
