package com.example.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class AdminAccountVO implements Serializable {
    private String userid;

    private String username;

    private String password;

    private String role;
    public void setuserid(String value) {
        this.userid = value;
    }
    public String getuserid() {
        return this.userid;
    }
    public void setusername(String value) {
        this.username = value;
    }
    public String getusername() {
        return this.username;
    }
    public void setpassword(String value) {
        this.password = value;
    }
    public String getpassword() {
        return this.password;
    }
    public void setrole(String value) {
        this.role = value;
    }
    public String getrole() {
        return this.role;
    }
}