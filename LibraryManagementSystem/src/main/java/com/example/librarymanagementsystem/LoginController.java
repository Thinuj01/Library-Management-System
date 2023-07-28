package com.example.librarymanagementsystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginController {
   @FXML
    private TextField txtUserName;
    @FXML
    private TextField txtPassword;
    @FXML
    private Label lblStatus;

    @FXML
    protected void onClickLogin(){
        try {
            String UserName = txtUserName.getText();
            String Password = txtPassword.getText();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "Thinuja21033");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from login");

            boolean login_Status= false;
            while(resultSet.next()){
                String x = resultSet.getString("UserName");
                String y =resultSet.getString("Password");
                System.out.println(x);
                System.out.println(y);
                if(UserName.equals(x) && Password.equals(y)) {
                    login_Status =true;
                    lblStatus.setText("Login Success");
                    String z = resultSet.getString("Role");
                    if (z.equals("Admin")) {
                        break;
                    }
                }
            }
            if(!login_Status){
                lblStatus.setText("Invalid userName or Password");
            }

            statement.close();
            connection.close();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    protected void onClickRegister() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Register.fxml"));
        Scene scene1 = new Scene(fxmlLoader.load(), 650, 800);
        Stage stage1 = new Stage();
        stage1.setTitle("Register");
        stage1.setScene(scene1);
        stage1.show();
    }



}