package com.example.librarymanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import static com.example.librarymanagementsystem.LoginController.Password;
import static com.example.librarymanagementsystem.LoginController.UserName;

public class My_booksController implements Initializable {

    @FXML
    private AnchorPane myBookPane;

    @FXML
    private TableView<User_books> myBookTable;


    @FXML
    private TableColumn<User_books, String> colBookId;

    @FXML
    private TableColumn<User_books, String> colIssuDate;

    @FXML
    private TableColumn<User_books, String> colResubmission;

    @FXML
    private Button backBtn;

    @FXML
    void onclickBack(ActionEvent event) {
        Stage stage;
        stage = (Stage) myBookPane.getScene().getWindow();
        stage.close();
        LoadWindow.loadInterFace("User_Interface.fxml","User_Interface", 1280, 800);

    }



    public void initialize(URL url, ResourceBundle rb){
        try{
            ObservableList<User_books> data = FXCollections.observableArrayList();
            colBookId.setCellValueFactory(new PropertyValueFactory<User_books,String>("bookID"));
            colIssuDate.setCellValueFactory(new PropertyValueFactory<User_books,String>("date_of_issued"));
            colResubmission.setCellValueFactory(new PropertyValueFactory<User_books,String>("date_of_resubmission"));

            Connection con2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms",HelloApplication.DB_USERNAME, HelloApplication.DB_PASSWORD);
            String query1 = "select user_ID from login where UserName=? and Password=? ";
            PreparedStatement pst = con2.prepareStatement(query1);
            pst.setString(1,UserName);
            pst.setString(2,Password);
            ResultSet result = pst.executeQuery();
            result.next();
            int uid = result.getInt(1);
            con2.close();

            String query="select BookID,date_of_issued,date_of_resubmition from user_books where UserID=? and status='pending'";
            Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", HelloApplication.DB_USERNAME, HelloApplication.DB_PASSWORD);
            PreparedStatement pst1 = con1.prepareStatement(query);
            pst1.setInt(1,uid);
            ResultSet rst = pst1.executeQuery();
            while(rst.next()){
                data.add(new User_books(rst.getString(1),rst.getString(2),rst.getString(3)));

            }
            myBookTable.setItems(data);
        }catch(Exception e){
            System.out.println(e);
        }
    }

}
