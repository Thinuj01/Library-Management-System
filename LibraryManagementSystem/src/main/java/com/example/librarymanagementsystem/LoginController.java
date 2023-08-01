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
    public static String UserName;
    public static String Password;
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
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "123456789");
            //Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "Thinuja21033");
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
                            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Admin_Interface.fxml"));
                            Scene scene2 = new Scene(fxmlLoader.load(), 1280, 800);
                            Stage stage2 = new Stage();
                            stage2.setTitle("Admin_Interface");
                            stage2.setScene(scene2);
                            stage2.show();

                        break;
                    }
                    else if(z.equals("User")){
                        Stage stage;
                        stage = (Stage) AdminPane.getScene().getWindow();
                        stage.close();
                        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("User_Interface.fxml"));
                        Scene scene2 = new Scene(fxmlLoader.load(), 1280, 800);
                        Stage stage2 = new Stage();
                        stage2.setTitle("User_Interface");
                        stage2.setScene(scene2);
                        stage2.show();
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
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Visitor_Interface.fxml"));
            Scene scene3 = new Scene(fxmlLoader.load(),1280,800);
            Stage stage3 = new Stage();
            stage3.setTitle("Visitor");
            stage3.setScene(scene3);
            stage3.show();

        }catch(Exception e){
            System.out.println(e);
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