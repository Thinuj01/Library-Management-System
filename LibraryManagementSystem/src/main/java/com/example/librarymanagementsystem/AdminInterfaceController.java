package com.example.librarymanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;

import static com.example.librarymanagementsystem.LoginController.Password;
import static com.example.librarymanagementsystem.LoginController.UserName;


public class AdminInterfaceController implements Initializable {
    @FXML
    private Label lblProUser;

    @FXML
    private TableView<Book> BookTable;

    @FXML
    private Button logOutBtn;

    @FXML
    private Button deliveryBtn;

    @FXML
    private Button acceptanceBtn;

    @FXML
    private Button exitBtn;

    @FXML
    private Button BorrowBtn;

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
    private Text userIDDisplay;


    @FXML
    private TextField lblSearch;




    @FXML
    protected void OnclickAddBook() {
        try{
            Stage stage;
            stage =(Stage) AdminPane.getScene().getWindow();
            stage.close();
            LoadWindow.loadInterFace("AddBook.fxml","Add Book",650, 800);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void onClickAcceptanceBook(ActionEvent event) {
        Stage stage = (Stage)AdminPane.getScene().getWindow();
        stage.close();
        try{
            LoadWindow.loadInterFace("ReturnBook.fxml","Returning Book",650, 800);

        }catch(Exception e){
            System.out.println(e);
        }



    }

    @FXML
    void onClickDeliveryBook(ActionEvent event) {
        Stage stage = (Stage)AdminPane.getScene().getWindow();
        stage.close();

        try{
            LoadWindow.loadInterFace("issueBooks.fxml","Issuing Book",900, 650);


        }catch(Exception e){
            System.out.println(e);
        }
    }

    @FXML
    void OnClickExit(ActionEvent event) {
        try{
            Stage stage;
            stage = (Stage)AdminPane.getScene().getWindow();
            stage.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    @FXML
    void OnClickLogOut(ActionEvent event) {
        Stage stage;
        stage = (Stage)AdminPane.getScene().getWindow();
        stage.close();
        try{
            LoadWindow.loadInterFace("Login.fxml","Hello!",1280, 800);
        }
        catch(Exception e){
            System.out.println(e);
        }

    }

    @FXML
    void onClickMyBorrowBooks(ActionEvent event) {
        String query= "select * from book_details where Book_ID in (select BookID from user_books where userID = ?)";

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
        LoadWindow.loadInterFace("ModifyBook.fxml","Modify Book",650, 800);
    }

    private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs=null;
    public ObservableList<Book> data;

    @FXML
    void onClickDelete(ActionEvent event) throws IOException {
        index = BookTable.getSelectionModel().getSelectedIndex();
        String BookID = (colBookID.getCellData(AdminInterfaceController.index).toString());
        if(index<=-1){
            return;
        }
        else{

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Please Confirm");
            alert.setHeaderText("Are You Want Delete");
            alert.setContentText("If you delete we cant recover that row if you agree PRESS 'OK' else PRESS 'Cancel'");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                String url = "jdbc:mysql://localhost:3306/lms";
                String query = "delete from book_details where Book_ID=?";
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection conn = DriverManager.getConnection(url,HelloApplication.DB_USERNAME,HelloApplication.DB_PASSWORD);
                    PreparedStatement pst = conn.prepareStatement(query);
                    pst.setString(1,BookID);
                    pst.executeUpdate();
                    pst.close();
                    conn.close();
                    Stage stage;
                    stage = (Stage)AdminPane.getScene().getWindow();
                    stage.close();
                    LoadWindow.loadInterFace("Admin_Interface.fxml","Admin_Interface", 1280, 800);
                    }catch(Exception e){
                        System.out.println(e);
                    }

            }
        }



        }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            lblProUser.setText(UserName);
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", HelloApplication.DB_USERNAME, HelloApplication.DB_PASSWORD);
            data = FXCollections.observableArrayList();
            colBookID.setCellValueFactory(new PropertyValueFactory<Book,String>("BookID"));
            colBookName.setCellValueFactory(new PropertyValueFactory<Book,String>("BookName"));
            colAuthor.setCellValueFactory(new PropertyValueFactory<Book,String>("Author"));
            colCategory.setCellValueFactory(new PropertyValueFactory<Book,String>("Category"));
            colPrice.setCellValueFactory(new PropertyValueFactory<Book,String>("Price"));
            colNoPages.setCellValueFactory(new PropertyValueFactory<Book,String>("NoPages"));
            colLocation.setCellValueFactory(new PropertyValueFactory<Book,String>("Location"));

            loadDataFromDatabase("Select  * from book_details");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try{
            Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms",HelloApplication.DB_USERNAME, HelloApplication.DB_PASSWORD);
            String query = "select user_ID from login where UserName=? and Password=?";
            PreparedStatement pst = con1.prepareStatement(query);
            pst.setString(1,UserName);
            pst.setString(2,Password);
            ResultSet result = pst.executeQuery();
            result.next();
            userIDDisplay.setText(String.valueOf(result.getInt(1)));
            con1.close();
        }catch(Exception e){
            System.out.println(e);
        }


    }

    public void loadDataFromDatabase(String Sql) throws SQLException {
        pst = con.prepareStatement(Sql);
        rs = pst.executeQuery();
        while(rs.next()){
            data.add(new Book(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5), rs.getString(6),rs.getString(7)));

        }
        BookTable.setItems(data);
        Search();
    }

    public void Search() {
        lblSearch.setOnKeyReleased(e ->{
            if(lblSearch.getText().equals("")){
                try {
                    data.clear();
                    loadDataFromDatabase("Select  * from book_details");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }else{
                data.clear();
                String sql = "Select * from book_details where Book_ID Like \'%"+lblSearch.getText()+"%\' "+"UNION Select * from book_details where Book_Name Like \'%"+lblSearch.getText()+"%\' "
                        +"UNION Select * from book_details where Author Like \'%"+lblSearch.getText()+"%\' "+"UNION Select * from book_details where Category Like \'%"+lblSearch.getText()+"%\' "
                        +"UNION Select * from book_details where Price Like \'%"+lblSearch.getText()+"%\' "+"UNION Select * from book_details where NoOfPages Like \'%"+lblSearch.getText()+"%\' "
                        +"UNION Select * from book_details where Location Like \'%"+lblSearch.getText()+"%\' ";
                try {
                    pst = con.prepareStatement(sql);
                    rs=pst.executeQuery();
                    while(rs.next()){
                        data.add(new Book(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5), rs.getString(6),rs.getString(7 )));
                    }
                }catch (Exception a){
                    a.printStackTrace();
                }
            }
        });
    }






}
