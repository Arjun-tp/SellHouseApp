package com.example.sellhouse;

public class User {
    public String email, mobile, password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String email, String mobile, String password) {
        this.email = email;
        this.mobile = mobile;
        this.password = password;
    }
}
