package com.example.librarymanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.Date;

import static com.example.librarymanagementsystem.LoginController.Password;
import static com.example.librarymanagementsystem.LoginController.UserName;

public class IssueBooksController implements Initializable {
    @FXML
    private AnchorPane IssuePane;
    @FXML
    private TextField txtUserID;
    @FXML
    private Label lblIDstatus;

    @FXML
    private TableView<Book> issueBookTable;

    @FXML
    private TableColumn<Book,String> colIbookID;

    @FXML
    private TableColumn<Book,String> colIbookName;

    @FXML
    private TableColumn<Book,String> colILocation;

    @FXML
    private Label lblBookID;

    @FXML
    private Label lblIssueDate;



    private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs=null;
    public ObservableList<Book> dataI;
    public static Integer colindex;


    @FXML
    protected void onClickAddBook(){
        colindex = issueBookTable.getSelectionModel().getSelectedIndex();
        String IBookID = (colIbookID.getCellData(IssueBooksController.colindex).toString());
        String IBookName = (colIbookName.getCellData(IssueBooksController.colindex).toString());

        if (colindex<=-1)
            return;

        lblBookID.setText(IBookID);
        lblIssueDate.setText(String.valueOf(java.time.LocalDate.now()));

    }

    @FXML
    protected void onClickIssue(){
        try {
            String id = txtUserID.getText().toString();
            System.out.println(id);
            int userID = Integer.parseInt(id);
            String sql = "Insert into user_books (UserId,BookID,date_of_issued,amount_of_fine) values(" + userID + ",\"" + lblBookID.getText() + "\",\"" + lblIssueDate.getText() + "\",0.0)";
            System.out.println(sql);
            Connection connection2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", HelloApplication.DB_USERNAME, HelloApplication.DB_PASSWORD);
            Statement statement2 = connection2.createStatement();
            statement2.execute(sql);

            statement2.close();
            connection2.close();
            Stage stage;
            stage = (Stage) IssuePane.getScene().getWindow();
            stage.close();
            LoadWindow.loadInterFace("Admin_Interface.fxml","Admin_Interface", 1280, 800);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", HelloApplication.DB_USERNAME, HelloApplication.DB_PASSWORD);
            dataI = FXCollections.observableArrayList();
            colIbookID.setCellValueFactory(new PropertyValueFactory<Book, String>("BookID"));
            colIbookName.setCellValueFactory(new PropertyValueFactory<Book, String>("BookName"));
            colILocation.setCellValueFactory(new PropertyValueFactory<Book, String>("Author"));

            loadDataFromDatabase("Select  * from book_details");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public void loadDataFromDatabase(String Sql) throws SQLException {
        pst = con.prepareStatement(Sql);
        rs = pst.executeQuery();
        while(rs.next()) {
            dataI.add(new Book(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));

        }
        issueBookTable.setItems(dataI);
        userConfirm();
    }

    public  void userConfirm(){
        txtUserID.setOnKeyReleased(e ->{
            String q = "select user_ID from login where user_ID="+txtUserID.getText();
            if(txtUserID.getText().equals("")){
                try{
                    lblIDstatus.setText("");
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
            }else {

                try {
                    lblIDstatus.setText("Invalid");
                    pst = con.prepareStatement(q);
                    rs = pst.executeQuery();
                    rs.next();
                    System.out.println(txtUserID.getText());
                    System.out.println(rs.getString(1));
                    if (txtUserID.getText().toString().equals(rs.getString(1))) {
                        lblIDstatus.setText("OK");
                    } else {
                        lblIDstatus.setText("Invalid");
                    }
                } catch (Exception a) {
                    a.printStackTrace();
                }
            }
        });
    }

}
