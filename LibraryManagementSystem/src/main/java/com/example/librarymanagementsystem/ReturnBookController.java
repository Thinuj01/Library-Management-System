package com.example.librarymanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

public class ReturnBookController implements Initializable {

    @FXML
    private AnchorPane returnPane;

    @FXML
    private TextField userID;

    @FXML
    private TextField BookID;

    @FXML
    private Text fineDisplay;

    @FXML
    private Button returnBookBtn;

    @FXML
    private Text issuedDate;

    @FXML
    private DatePicker receivedDate;

    @FXML
    private Text msgTxt;

    @FXML
    private Button backBtn;

    @FXML
    void onClickReceivedDate(MouseEvent event) {
            String userid = userID.getText();
            String bookid = BookID.getText();
            String query = "select * from user_books where UserID=? and BookID=?";
            String query2="update user_books set date_of_recived=? where UserID=? and BookID=?";
            String query3="update user_books set amount_of_fine=? where UserID=? and BookID=?";
            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", HelloApplication.DB_USERNAME, HelloApplication.DB_PASSWORD);
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, userid);
                pst.setString(2, bookid);
                ResultSet result = pst.executeQuery();

                result.next();
                String temp1 = result.getString(1);
                String temp2 = result.getString(2);
                System.out.println(temp1+"   "+temp2);
                if (userid.equals(temp1)  && bookid.equals(temp2)) {
                    String idate = String.valueOf(result.getDate(3));
                    issuedDate.setText(idate);
                    issuedDate.setVisible(true);

                    LocalDate recive = LocalDate.now();
                    receivedDate.setValue(recive);

                    String rdate = String.valueOf(receivedDate.getValue());
                    pst.close();
                    con.close();

                    Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", HelloApplication.DB_USERNAME, HelloApplication.DB_PASSWORD);
                    PreparedStatement pst2 = con1.prepareStatement(query2);
                    pst2.setString(1,rdate);
                    pst2.setString(2,userid);
                    pst2.setString(3,bookid);


                    System.out.println(pst2.executeUpdate());

                    int fine = fineCalculator(rdate,idate);
                    String fined = String.valueOf(fine);
                    fineDisplay.setText(("Rs."+fined+".00"));
                    fineDisplay.setVisible(true);

                    pst2.close();
                    con1.close();

                    Connection con2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", HelloApplication.DB_USERNAME, HelloApplication.DB_PASSWORD);
                    PreparedStatement pst3 = con2.prepareStatement(query3);
                    pst3.setString(1,String.valueOf(fine));
                    pst3.setString(2,userid);
                    pst3.setString(3,bookid);
                    System.out.println(pst3.executeUpdate());

                    pst3.close();
                    con2.close();


                } else {
                    msgTxt.setVisible(true);
                }

            } catch (Exception e) {
                System.out.println(e);
            }

    }

    @FXML
    void onClickReturnBook(ActionEvent event) {
        String userid = userID.getText();
        String bookid = BookID.getText();
        //LocalDate rdate = receivedDate.getValue();
        try{
            //String query="update user_books set date_of_recived = CASE when userid=? and bookid=? then ? else date_of_recived end,amount_of_fine= CASE when userid=? and bookid=? then ? else amount_of_fine end,status = CASE when userid=? and bookid=? then \"ok\" else status end";
            String query = "update user_books set status=\"ok\" where userid=? and bookid=?";
            Connection con2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", HelloApplication.DB_USERNAME, HelloApplication.DB_PASSWORD);
            PreparedStatement pst2 = con2.prepareStatement(query);
            /*pst2.setString(1,userid);
            pst2.setString(2,bookid);
            pst2.setDate(3,rdate);*/
            pst2.setString(1,userid);
            pst2.setString(2,bookid);

            System.out.println(pst2.executeUpdate());

            Stage stage;
            stage = (Stage) returnPane.getScene().getWindow();
            stage.close();
            LoadWindow.loadInterFace("Admin_Interface.fxml","Admin_Interface", 1280, 800);

            }catch(Exception e){
            System.out.println(e);
        }
    }
    @FXML
    void onClickBack(ActionEvent event) {
        Stage stage;
        stage = (Stage) returnPane.getScene().getWindow();
        stage.close();
        LoadWindow.loadInterFace("Admin_Interface.fxml","Admin_Interface", 1280, 800);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        msgTxt.setVisible(false);
        fineDisplay.setVisible(false);
        issuedDate.setVisible(false);
    }

    private int fineCalculator(String receive,String issuing){
       LocalDate Rday = LocalDate.parse(receive);
       LocalDate Iday = LocalDate.parse(issuing);

       long noOfDays = ChronoUnit.DAYS.between(Rday,Iday);
       if(noOfDays<0){
           noOfDays *= (-1);
       }
       else {

       }
        {
            if (noOfDays > 7) {
                return (int) (noOfDays - 7) * 10;
            } else {
                return 0;
            }
        }


    }
}
