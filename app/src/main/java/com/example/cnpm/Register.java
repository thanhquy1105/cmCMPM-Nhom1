package com.example.cnpm;

public class Register {
    private String mail;
    private  String pass;
    private  String phone;
    private  String name;

    public Register(String mail, String pass, String name, String phone) {
        this.mail = mail;
        this.pass = pass;
        this.phone = phone;
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
