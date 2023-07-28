package com.example.librarymanagementsystem;

public class Book {
    String BookID,BookName,Author,Category,Price,NoPages,Location;

    public Book(String bookID, String bookName, String author, String category, String price, String noPages, String location) {
        BookID = bookID;
        BookName = bookName;
        Author = author;
        Category = category;
        Price = price;
        NoPages = noPages;
        Location = location;
    }

    public String getBookID() {
        return BookID;
    }

    public String getBookName() {
        return BookName;
    }

    public String getAuthor() {
        return Author;
    }

    public String getCategory() {
        return Category;
    }

    public String getPrice() {
        return Price;
    }

    public String getNoPages() {
        return NoPages;
    }

    public String getLocation() {
        return Location;
    }

    public void setBookID(String bookID) {
        BookID = bookID;
    }

    public void setBookName(String bookName) {
        BookName = bookName;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public void setNoPages(String noPages) {
        NoPages = noPages;
    }

    public void setLocation(String location) {
        Location = location;
    }
}
