package com.example.lab2;

public class Department {

    int _id;
    String _department;


    public Department() {
    }

    public Department(int id, String department) {
        this._id = id;
        this._department = department;
    }

    public Department( String department) {
        this._department = department;
    }

    public int getID() {
        return this._id;
    }

    public void setID(int id) {
        this._id = id;
    }

    public String getDepartment() {
        return this._department;
    }

    public void setDepartment(String department) {
        this._department = department;
    }
}
