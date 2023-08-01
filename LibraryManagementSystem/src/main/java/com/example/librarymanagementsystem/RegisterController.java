package com.example.librarymanagementsystem;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;
import java.sql.*;

public class RegisterController {
    @FXML
    private TextField txtFName;
    @FXML
    private TextField txtLName;
    @FXML
    private TextField txtBOD;
    @FXML
    private TextField txtUserName;
    @FXML
    private TextField txtPassword;
    @FXML
    private TextField txtRePassword;
    @FXML
    private ToggleGroup tgRole;
    @FXML
    private RadioButton rdbAdmin;
    @FXML
    private RadioButton rdbUser;
    @FXML
    private Label lblWarning;
    @FXML
    private AnchorPane RegisterPane;
    @FXML
    protected void onClickRegistered() throws SQLException {

            String FirstName = txtFName.getText();
            String LastName = txtLName.getText();
            String BOD = txtBOD.getText();
            String UserName = txtUserName.getText();
            String Password = txtPassword.getText();
            String RePassword = txtRePassword.getText();
            String Role = "";
            if (rdbAdmin.isSelected()) {
                Role = "Admin";
            } else {
                Role = "User";
            }
            if (Password.equals(RePassword)) {

                String sql = "INSERT INTO login" + "(FName,LName,BOD,UserName,Password,Role)" +
                        "VALUES(\"" + FirstName + "\",\"" + LastName + "\",\"" + BOD + "\",\"" + UserName + "\",\"" + Password + "\",\"" + Role + "\")";
                //System.out.println(sql);

                Connection connection2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "123456789");
                //Connection connection2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "Thinuja21033");
                Statement statement2 = connection2.createStatement();
                statement2.execute(sql);

                statement2.close();
                connection2.close();
                Stage stage;
                stage =(Stage) RegisterPane.getScene().getWindow();
                stage.close();
            }

            else{
                lblWarning.setText("Passwords dosen't match");
        }


    }
}

