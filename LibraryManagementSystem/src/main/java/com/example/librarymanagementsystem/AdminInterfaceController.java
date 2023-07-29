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

import java.io.IOException;
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
    public TableColumn<Book,String> colBookID;

    @FXML
    public TableColumn<Book,String> colBookName;

    @FXML
    public TableColumn<Book,String> colAuthor;

    @FXML
    public TableColumn<Book,String> colCategory;

    @FXML
    public TableColumn<Book,String> colPrice;

    @FXML
    public TableColumn<Book,String> colNoPages;

    @FXML
    public TableColumn<Book,String> colLocation;

    public static String RBookID,RBookName,RAuthor,RCategory,RPrice,RNoPages,RLocation;

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
            stage3.setTitle("Add Book");
            stage3.setScene(scene3);
            stage3.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static Integer index;

    @FXML
    protected void OnclickModify() throws IOException {
        index = BookTable.getSelectionModel().getSelectedIndex();
        RBookID = (colBookID.getCellData(AdminInterfaceController.index).toString());
        RBookName = (colBookName.getCellData(AdminInterfaceController.index).toString());
        RAuthor = (colAuthor.getCellData(AdminInterfaceController.index).toString());
        RCategory = (colCategory.getCellData(AdminInterfaceController.index).toString());
        RPrice = (colPrice.getCellData(AdminInterfaceController.index).toString());
        RNoPages = (colNoPages.getCellData(AdminInterfaceController.index).toString());
        RLocation = (colLocation.getCellData(AdminInterfaceController.index).toString());

        if(index<=-1){
            return;
        }
        Stage stage;
        stage =(Stage) AdminPane.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ModifyBook.fxml"));
        Scene scene4 = new Scene(fxmlLoader.load(), 650, 800);
        Stage stage4 = new Stage();
        stage4.setTitle("Modify Book");
        stage4.setScene(scene4);
        stage4.show();
    }

    private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs=null;
    public ObservableList<Book> data;

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
