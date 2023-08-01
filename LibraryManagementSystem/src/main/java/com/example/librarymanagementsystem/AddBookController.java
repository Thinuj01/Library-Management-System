package com.example.librarymanagementsystem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;
import java.sql.*;
public class AddBookController {
    @FXML
    private TextField txtBookID;

    @FXML
    private TextField txtBookName;

    @FXML
    private TextField txtAuthor;

    @FXML
    private TextField txtCategory;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtNoPages;

    @FXML
    private TextField txtLocation;

    @FXML
    private AnchorPane AddBookPane;

    private PreparedStatement pst = null;
    @FXML
    protected void onClickAddBook() throws SQLException, IOException {
        String Sql = "INSERT INTO book_details(Book_ID,Book_Name,Author,Category,Price,NoOfPages,Location) VALUES(?,?,?,?,?,?,?)";
        Connection connection2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", HelloApplication.DB_USERNAME, HelloApplication.DB_PASSWORD);


        pst = connection2.prepareStatement(Sql);
        pst.setString(1,txtBookID.getText());
        pst.setString(2,txtBookName.getText());
        pst.setString(3,txtAuthor.getText());
        pst.setString(4,txtCategory.getText());
        pst.setString(5,txtPrice.getText());
        pst.setString(6,txtNoPages.getText());
        pst.setString(7,txtLocation.getText());

        int i = pst.executeUpdate();
        if(i==1){
            connection2.close();
            Stage stage;
            stage =(Stage) AddBookPane.getScene().getWindow();
            stage.close();
            LoadWindow.loadInterFace("Admin_Interface.fxml","Admin_Interface",1280, 800);

        }

    }

}
