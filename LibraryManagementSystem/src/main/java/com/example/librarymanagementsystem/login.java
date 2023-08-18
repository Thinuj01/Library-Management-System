package com.example.librarymanagementsystem;

public class login {
    String userID,Fname,Lname,DOB,UserName,Role;

    public login(String userID, String fname, String lname, String DOB, String userName, String role) {
        this.userID = userID;
        Fname = fname;
        Lname = lname;
        this.DOB = DOB;
        UserName = userName;
        Role = role;
    }

    public String getUserID() {
        return userID;
    }

    public String getFname() {
        return Fname;
    }

    public String getLname() {
        return Lname;
    }

    public String getDOB() {
        return DOB;
    }

    public String getUserName() {
        return UserName;
    }

    public String getRole() {
        return Role;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public void setLname(String lname) {
        Lname = lname;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public void setRole(String role) {
        Role = role;
    }
}
