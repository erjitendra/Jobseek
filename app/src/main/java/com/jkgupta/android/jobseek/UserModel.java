package com.jkgupta.android.jobseek;

/**
 * Created by Jindal on 12/27/2017.
 */

public class UserModel {
    private String name;
    private String email;
    private String user_Type;
    private String user_Id;
    private boolean user_in_Db;

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

    public String getUser_Type() {
        return user_Type;
    }

    public void setUser_Type(String user_Type) {
        this.user_Type = user_Type;
    }

    public String getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(String user_Id) {
        this.user_Id = user_Id;
    }

    public boolean isUser_in_Db() {
        return user_in_Db;
    }

    public void setUser_in_Db(boolean user_in_Db) {
        this.user_in_Db = user_in_Db;
    }
}
