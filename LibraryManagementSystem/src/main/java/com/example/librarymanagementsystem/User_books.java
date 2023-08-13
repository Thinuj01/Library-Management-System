package com.example.librarymanagementsystem;

public class User_books {
    String bookID,date_of_issued,date_of_resubmission;

    public User_books( String bookID, String date_of_issued, String date_of_resubmission) {
        this.bookID = bookID;
        this.date_of_issued = date_of_issued;
        this.date_of_resubmission = date_of_resubmission;
    }

    public String getBookID() {
        return bookID;
    }

    public String getDate_of_issued() {
        return date_of_issued;
    }

    public String getDate_of_resubmission() {
        return date_of_resubmission;
    }
}
