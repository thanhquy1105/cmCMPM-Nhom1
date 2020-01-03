package com.example.cnpm;


public class User {

    private String mail;

    private  String pass;

    public User(String email, String pass) {
        this.mail = email;
        this.pass = pass;
    }

    public String getEmail() {
        return mail;
    }

    public void setEmail(String email) {
        this.mail = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

}
