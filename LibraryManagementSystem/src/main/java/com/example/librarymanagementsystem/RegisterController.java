package com.example.librarymanagementsystem;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
    public void onClickBack(){}

    @FXML
    protected void onClickRegistered() throws SQLException {

            String FirstName = txtFName.getText();
            String LastName = txtLName.getText();
            String BOD = txtBOD.getText();
            String UserName = txtUserName.getText();
            String Password = txtPassword.getText();
            String RePassword = txtRePassword.getText();
            String Role="";
        if (rdbAdmin.isSelected()) {
            Role = "Admin";
        } else {
            Role = "User";
        }
            /*if (tgRole.getSelectedToggle().equals(rdbAdmin)) {
                String SYSTEM_PASSWORD="MiniProject1234";
                String userInput = JOptionPane.showInputDialog("Enter Password:");
                if (SYSTEM_PASSWORD.equals(userInput)) {
                    Role = "Admin";
                } else {
                    JOptionPane.showMessageDialog(null, "Password is Incorrect!!!", "Error", JOptionPane.ERROR_MESSAGE);
                    Stage stage;
                    stage =(Stage) RegisterPane.getScene().getWindow();
                    stage.close();
                    LoadWindow.loadInterFace("Login.fxml","Login",1280,800);
                }
                Role = "Admin";


            } else if(tgRole.getSelectedToggle().equals(rdbUser)){
                Role = "User";

            }*/
            if (Password.equals(RePassword)) {

                String sql = "INSERT INTO login" + "(FName,LName,BOD,UserName,Password,Role)" +
                        "VALUES(\"" + FirstName + "\",\"" + LastName + "\",\"" + BOD + "\",\"" + UserName + "\",\"" + Password + "\",\"" + Role + "\")";
                //System.out.println(sql);

                Connection connection2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", HelloApplication.DB_USERNAME, HelloApplication.DB_PASSWORD);
                Statement statement2 = connection2.createStatement();
                statement2.execute(sql);
                Stage stage;
                stage =(Stage) RegisterPane.getScene().getWindow();
                stage.close();

                statement2.close();
                connection2.close();
            }

            else{
                lblWarning.setText("Passwords dosen't match");
        }


    }


}

