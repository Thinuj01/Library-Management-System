package com.example.librarymanagementsystem;

public class User_books {
    String bookID,date_of_issued,date_of_resubmission,date_of_recived,fine,status;


    public User_books( String bookID, String date_of_issued, String date_of_resubmission) {
        this.bookID = bookID;
        this.date_of_issued = date_of_issued;
        this.date_of_resubmission = date_of_resubmission;
    }

    public User_books( String bookID, String date_of_issued, String date_of_resubmission, String date_of_recived,String fine,String status) {

        this.bookID = bookID;
        this.date_of_issued = date_of_issued;
        this.date_of_resubmission = date_of_resubmission;
        this.date_of_recived = date_of_recived;
        this.fine = fine;
        this.status=status;
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

    public String getDate_of_recived() {
        return date_of_recived;
    }

    public String getFine() {
        return fine;
    }

    public String getStatus() {
        return status;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public void setDate_of_issued(String date_of_issued) {
        this.date_of_issued = date_of_issued;
    }

    public void setDate_of_resubmission(String date_of_resubmission) {
        this.date_of_resubmission = date_of_resubmission;
    }

    public void setDate_of_recived(String date_of_recived) {
        this.date_of_recived = date_of_recived;
    }

    public void setFine(String fine) {
        this.fine = fine;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
