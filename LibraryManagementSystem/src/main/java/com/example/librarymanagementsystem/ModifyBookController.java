package com.example.librarymanagementsystem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ModifyBookController implements Initializable {
    @FXML
    private AnchorPane ModifyBookPane;

    @FXML
    private TextField txtMBookID;

    @FXML
    private TextField txtMBookName;

    @FXML
    private TextField txtMAuthor;

    @FXML
    private TextField txtMCategory;

    @FXML
    private TextField txtMPrice;

    @FXML
    private TextField txtMNoPages;

    @FXML
    private TextField txtMLocation;



    @FXML
    protected void onClickModify()throws SQLException,IOException {
        String MBookId = txtMBookID.getText();
        String MBookName = txtMBookName.getText();
        String MAuthor = txtMAuthor.getText();
        String MCategory = txtMCategory.getText();
        String MPrice = txtMPrice.getText();
        String MNoPages = txtMNoPages.getText();
        String MLocation = txtMLocation.getText();
        Connection connection2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "Thinuja21033");
        Statement statement2 = connection2.createStatement();
        String Sql = "UPDATE book_details SET Book_ID=\""+MBookId+"\",Book_Name=\""+MBookName+"\",Author=\""+MAuthor+"\",Category=\""+
                MCategory+"\",Price=\""+MPrice+"\",NoOfPages=\""+MNoPages+"\",Location=\""+MLocation+"\" WHERE Book_ID=\""+MBookId+"\"";
        System.out.println(Sql);
        statement2.execute(Sql);
        statement2.close();
        connection2.close();
        Stage stage;
        stage =(Stage) ModifyBookPane.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Admin_Interface.fxml"));
        Scene scene3 = new Scene(fxmlLoader.load(), 1280, 800);
        Stage stage3 = new Stage();
        stage3.setTitle("Admin_Interface");
        stage3.setScene(scene3);
        stage3.show();



    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            txtMBookID.setText(AdminInterfaceController.RBookID);
            txtMBookName.setText(AdminInterfaceController.RBookName);
            txtMAuthor.setText(AdminInterfaceController.RAuthor);
            txtMCategory.setText(AdminInterfaceController.RCategory);
            txtMPrice.setText(AdminInterfaceController.RPrice);
            txtMNoPages.setText(AdminInterfaceController.RNoPages);
            txtMLocation.setText(AdminInterfaceController.RLocation);

        }
}
