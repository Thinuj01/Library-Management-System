package com.example.librarymanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class IssueBookController implements Initializable {

    @FXML
    private TextField userID;

    @FXML
    private TextField BookID;

    @FXML
    private Button issuingBookBtn;

    @FXML
    private Text okuserIDDisplay;

    @FXML
    private Text invalidUserIDdisplay;

    @FXML
    private Text okbookIDdisplay;

    @FXML
    private Text invalidBookIDDdisplay;

    @FXML
    private DatePicker issuedDate;

    @FXML
    void onClickGetDate(MouseEvent event) {
        LocalDate date = LocalDate.now();
        issuedDate.setValue(date);

        int userid =Integer.parseInt(userID.getText());
        String bookid = BookID.getText();
        String query = "select user_ID from login where user_ID= ? ";
        String query1 ="select Book_ID from book_details where Book_ID = ?";

        try{
            Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", HelloApplication.DB_USERNAME, HelloApplication.DB_PASSWORD);
            PreparedStatement pst1 = con1.prepareStatement(query);
            pst1.setInt(1,userid);
            ResultSet result = pst1.executeQuery();
            result.next();
            if(userid==result.getInt(1)){
                okuserIDDisplay.setVisible(true);
                invalidUserIDdisplay.setVisible(false);
            }else{
                invalidUserIDdisplay.setVisible(true);
                okuserIDDisplay.setVisible(false);
            }
            result.close();
            pst1.close();
            con1.close();

            Connection con2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms",HelloApplication.DB_USERNAME,HelloApplication.DB_PASSWORD);
            PreparedStatement pst2 = con2.prepareStatement(query1);
            pst2.setString(1,bookid);
            ResultSet result1 = pst2.executeQuery();
            result1.next();
            if(bookid.equals(result1.getString(1))){
                okbookIDdisplay.setVisible(true);
                invalidBookIDDdisplay.setVisible(false);
            }else{
                okbookIDdisplay.setVisible(false);
                invalidBookIDDdisplay.setVisible(true);
            }
            result1.close();
            pst2.close();
            con2.close();


        }catch(Exception e){
            System.out.println(e);
        }


    }

    @FXML
    void onClickissuingBook(ActionEvent event) {

    }

    public void initialize(URL url, ResourceBundle resourceBundle){
        okuserIDDisplay.setVisible(false);
        okbookIDdisplay.setVisible(false);
        invalidUserIDdisplay.setVisible(false);
        invalidBookIDDdisplay.setVisible(false);
    }

}
