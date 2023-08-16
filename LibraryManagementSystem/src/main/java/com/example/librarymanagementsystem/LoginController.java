package com.example.librarymanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginController {
    public static String UserName = null;
    public static String Password = null;
    @FXML
    private TextField txtUserName;
    @FXML
    private TextField txtPassword;
    @FXML
    private Label lblStatus;
    @FXML
    private Label lblLoginStatus;

    @FXML
    private AnchorPane AdminPane;

    @FXML
    private Button visitorBtn;

    @FXML
    protected void onClickLogin(){
        try {
            UserName = txtUserName.getText();
            Password = txtPassword.getText();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", HelloApplication.DB_USERNAME, HelloApplication.DB_PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from login");

            boolean login_Status= false;
            while(resultSet.next()){
                String x = resultSet.getString("UserName");
                String y =resultSet.getString("Password");
                if(UserName.equals(x) && Password.equals(y)) {
                    login_Status =true;
                    String z = resultSet.getString("Role");
                    if (z.equals("Admin")) {
                            Stage stage;
                            stage = (Stage) AdminPane.getScene().getWindow();
                            stage.close();
                            LoadWindow.loadInterFace("Admin_Interface.fxml","Admin_Interface", 1280, 800);

                        break;
                    }
                    else if(z.equals("User")){
                        Stage stage;
                        stage = (Stage) AdminPane.getScene().getWindow();
                        stage.close();
                        LoadWindow.loadInterFace("User_Interface.fxml","User_Interface",1280, 800);
                    }
//
                }
            }
            if(!login_Status){
                lblLoginStatus.setText("Invalid userName or Password");
                txtUserName.clear();
                txtPassword.clear();
            }

            statement.close();
            connection.close();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void onClickVisitor(ActionEvent event) {
        try{
            UserName="Visitor Account";
            Stage stage = (Stage)AdminPane.getScene().getWindow();
            stage.close();
            LoadWindow.loadInterFace("Visitor_Interface.fxml","Visitor",1280,800);

        }catch(Exception e){
            System.out.println("kasun"+e);
        }
    }

    @FXML
    protected void onClickRegister() throws IOException {
        Stage stage;
        stage =(Stage) AdminPane.getScene().getWindow();
        stage.close();
        LoadWindow.loadInterFace("Register.fxml","Register",650, 800);
    }



}