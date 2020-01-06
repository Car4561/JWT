package com.example.jwtaprend.model;

import com.google.gson.annotations.SerializedName;

public class Login {


    private String password;
    @SerializedName("user")
    private String userr;
    public String getUser() {
        return userr;
    }

    public void setUser(String user) {
        this.userr = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
