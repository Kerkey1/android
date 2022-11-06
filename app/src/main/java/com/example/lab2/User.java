package com.example.lab2;

public class User {

    int _id;
    String _login;
    String _pass;
    int _department;

    public User() {
    }

    public User(int id, String login, String pass, int department) {
        this._id = id;
        this._login = login;
        this._pass = pass;
        this._department = department;
    }

    public User(String login, String pass, int department) {
        this._login = login;
        this._pass = pass;
        this._department = department;

    }

    public int getID() {
        return this._id;
    }

    public void setID(int id) {
        this._id = id;
    }

    public String getLogin() {
        return this._login;
    }

    public void setLogin(String login) {
        this._login = login;
    }

    public String getPass() {
        return this._pass;
    }

    public void setPass(String pass) {
        this._pass = pass;
    }

    public int getDepartment() {
        return this._department;
    }

    public void setDepartment(int department) {
        this._department = department;
    }
}

