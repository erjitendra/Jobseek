package com.jkgupta.android.jobseek;

/**
 * Created by Jindal on 12/28/2017.
 */

public class JobPostModel {
    private String skills;
    private String company;
    private String description;
    private String rec_id;

    public String getRec_email() {
        return rec_email;
    }

    public void setRec_email(String rec_email) {
        this.rec_email = rec_email;
    }

    private String rec_email;


    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
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

    public String getRec_id() {
        return rec_id;
    }

    public void setRec_id(String rec_id) {
        this.rec_id = rec_id;
    }


}

