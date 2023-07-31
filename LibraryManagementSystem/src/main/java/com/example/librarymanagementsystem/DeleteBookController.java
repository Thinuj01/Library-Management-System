package com.example.librarymanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

public class DeleteBookController implements Initializable{

    @FXML
    private AnchorPane delPane;

    @FXML
    private TextField bookID;

    @FXML
    private Button delBtn;

    @FXML
    private RadioButton yes;

    @FXML
    private ToggleGroup choice;

    @FXML
    private Button cancelBtn;

    @FXML
    void disableDelBtn(MouseEvent event) {
        delBtn.setDisable(true);
    }

    @FXML
    void enableDelBtn(MouseEvent event) {
        delBtn.setDisable(false);
    }

    @FXML
    void onClickCancel(ActionEvent event){
        try{
            Stage stage;
            stage = (Stage)delPane.getScene().getWindow();
            stage.close();
        }
        catch(Exception e){

        }

    }

    @FXML
    void onclickDelBook(ActionEvent event) {
        String BID = bookID.getText();
        String url = "jdbc:mysql://localhost:3306/lms";
        String user = "root";
        String password = "123456789";
        String query = "delete from book_details where Book_ID=?";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url,user,password);
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1,BID);
            pst.executeUpdate();
            pst.close();
            conn.close();
            Stage stage;
            stage = (Stage)delPane.getScene().getWindow();
            stage.close();
            new AdminInterfaceController().refreshWindow();
        }catch(Exception e){
            System.out.println(e);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            delBtn.setDisable(true);
    }
}
