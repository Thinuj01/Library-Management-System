package com.example.librarymanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static com.example.librarymanagementsystem.LoginController.UserName;


public class AdminInterfaceController implements Initializable {
    @FXML
    private Label lblProUser;

    @FXML
    private TableView<Book> BookTable;

    @FXML
    private TableColumn<Book,String> colBookID;

    @FXML
    private TableColumn<Book,String> colBookName;

    @FXML
    private TableColumn<Book,String> colAuthor;

    @FXML
    private TableColumn<Book,String> colCategory;

    @FXML
    private TableColumn<Book,String> colPrice;

    @FXML
    private TableColumn<Book,String> colNoPages;

    @FXML
    private TableColumn<Book,String> colLocation;

    @FXML
    private AnchorPane AdminPane;
    @FXML
    protected void OnclickAddBook() {
        try{
            Stage stage;
            stage =(Stage) AdminPane.getScene().getWindow();
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AddBook.fxml"));
            Scene scene3 = new Scene(fxmlLoader.load(), 650, 800);
            Stage stage3 = new Stage();
            stage3.setTitle("Admin_Interface");
            stage3.setScene(scene3);
            stage3.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs=null;
    private ObservableList<Book> data;

//    try
//    {
//        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "Thinuja21033");
//        Statement statement = connection.createStatement();
//        ResultSet resultSet = statement.executeQuery("select * from login");
//    }catch (Exception e){
//        e.printStackTrace();
//    }
//
//    ObservableList <Book> list = FXCollections.observableArrayList(
//            new Book("A0001","Mathematics","Thinuja","Maths","200.00","500","M5"),
//            new Book("A0002","Commerce","Tiran","Statistic","1000.00","2500","C3"),
//            new Book("A0003","Science","Nuran","Science","2000.00","1200","S10")
//
//    );
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            lblProUser.setText(UserName);
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "Thinuja21033");
            data = FXCollections.observableArrayList();
            colBookID.setCellValueFactory(new PropertyValueFactory<Book,String>("BookID"));
            colBookName.setCellValueFactory(new PropertyValueFactory<Book,String>("BookName"));
            colAuthor.setCellValueFactory(new PropertyValueFactory<Book,String>("Author"));
            colCategory.setCellValueFactory(new PropertyValueFactory<Book,String>("Category"));
            colPrice.setCellValueFactory(new PropertyValueFactory<Book,String>("Price"));
            colNoPages.setCellValueFactory(new PropertyValueFactory<Book,String>("NoPages"));
            colLocation.setCellValueFactory(new PropertyValueFactory<Book,String>("Location"));

            loadDataFromDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void loadDataFromDatabase() throws SQLException {
        pst = con.prepareStatement("Select  * from book_details");
        rs = pst.executeQuery();
        while(rs.next()){
            data.add(new Book(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5), rs.getString(6),rs.getString(7)));

        }
        BookTable.setItems(data);
    }






}
