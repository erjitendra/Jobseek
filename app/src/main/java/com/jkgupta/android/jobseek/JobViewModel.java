package com.jkgupta.android.jobseek;

/**
 * Created by Jindal on 12/28/2017.
 */

public class JobViewModel {
    private String description;
    private String name;
    private String email;
    private String skills;
    private String job_post_id;
    private int candidate_applied;
    private String company;
    private String candidate_id;

    public String getCandidate_id() {
        return candidate_id;
    }

    public void setCandidate_id(String candidate_id) {
        this.candidate_id = candidate_id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getJob_post_id() {
        return job_post_id;
    }

    public void setJob_post_id(String job_post_id) {
        this.job_post_id = job_post_id;
    }

    public int getCandidate_applied() {
        return candidate_applied;
    }

    public void setCandidate_applied(int candidate_applied) {
        this.candidate_applied = candidate_applied;
    }
}
