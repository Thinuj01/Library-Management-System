module com.example.librarymanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.net.http;
    requires java.desktop;


    opens com.example.librarymanagementsystem to javafx.fxml;
    exports com.example.librarymanagementsystem;
}