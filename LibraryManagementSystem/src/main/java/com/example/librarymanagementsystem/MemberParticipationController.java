package com.example.librarymanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;

public class MemberParticipationController implements Initializable {

    @FXML
    private AnchorPane memberPane1;

    @FXML
    private TableView<login> usersTable;

    @FXML
    private TableColumn<login,String> colUserID;

    @FXML
    private TableColumn<login,String> colFname;

    @FXML
    private TableColumn<login,String> colLname;

    @FXML
    private TableColumn<login,String> colDob;

    @FXML
    private TableColumn<login,String> colUserName;

    @FXML
    private TableColumn<login,String> ColRole;

    @FXML
    private TextField userIDTextBox;

    @FXML
    private Button submitBtn;

    @FXML
    private TextField searchText1;

    @FXML
    private AnchorPane memberPane2;

    @FXML
    private TableView<User_books> bookTable;

    @FXML
    private TableColumn<User_books,String> colBookID;

    @FXML
    private TableColumn<User_books,String> coliDate;

    @FXML
    private TableColumn<User_books,String> colResubDate;

    @FXML
    private TableColumn<User_books,String> colRDate;

    @FXML
    private TableColumn<User_books,String> colFine;

    @FXML
    private TableColumn<User_books,String> colStatus;

    @FXML
    private TextField searchText2;

    @FXML
    private Button selectBtn;


    @FXML
    private Button backBtn1;

    @FXML
    private Button delMemberBtn;

    @FXML
    private Button backBtn2;
    public ObservableList<login> data1;
    public ObservableList<User_books> data2;
    int userID,userID1;
    static int index;



    @FXML
    void onClickBack(ActionEvent event) {
        Stage stage1 = (Stage) memberPane1.getScene().getWindow();
        stage1.close();
        new HelloApplication().openDelete = false;
        LoadWindow.loadInterFace("Admin_Interface.fxml","Admin_Interface", 1280, 800);

    }

    @FXML
    void onClickBack2(ActionEvent event) {
        Stage stage2 = (Stage) memberPane2.getScene().getWindow();
        stage2.close();
        LoadWindow.loadInterFace("MemberParticipationInterface.fxml","Member Participation",1280,800);
    }

    @FXML
    void onClickSelect(ActionEvent event) {
        index = usersTable.getSelectionModel().getSelectedIndex();
        userID1 = (Integer.parseInt(colUserID.getCellData(MemberParticipationController.index)));
        userIDTextBox.setText(String.valueOf(userID1));
        if(index<=-1){
            return;
        }

    }

    @FXML
    void onClickDelMember(ActionEvent event) {
        try{

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Please Confirm");
            alert.setHeaderText("Are You Want Delete this Member");
            alert.setContentText("If you delete we cant recover that Member Account if you agree PRESS 'OK' else PRESS 'Cancel'");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                String url = "jdbc:mysql://localhost:3306/lms";
                String query = "delete from login where user_ID=?";
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection conn = DriverManager.getConnection(url,HelloApplication.DB_USERNAME,HelloApplication.DB_PASSWORD);
                    PreparedStatement pst = conn.prepareStatement(query);
                    pst.setInt(1,userID1);
                    pst.executeUpdate();
                    pst.close();
                    conn.close();
                    Stage stage;
                    stage = (Stage)memberPane1.getScene().getWindow();
                    stage.close();
                    LoadWindow.loadInterFace("MemberParticipationInterface.fxml","Delete Member", 1280, 800);
                }catch(Exception e){
                    System.out.println(e);
                }

            }
        }catch(Exception e){
            System.out.println(e);
        }
    }

    @FXML
    void onClickSubmit(ActionEvent event) {
        searchText1.setVisible(false);
        usersTable.setVisible(false);
        backBtn1.setVisible(false);

        memberPane2.setVisible(true);
        searchText2.setVisible(true);
        bookTable.setVisible(true);
        backBtn2.setVisible(true);
        try{
            ObservableList<User_books> data = FXCollections.observableArrayList();
            colBookID.setCellValueFactory(new PropertyValueFactory<User_books,String>("bookID"));
            coliDate.setCellValueFactory(new PropertyValueFactory<User_books,String>("date_of_issued"));
            colResubDate.setCellValueFactory(new PropertyValueFactory<User_books,String>("date_of_resubmission"));
            colRDate.setCellValueFactory(new PropertyValueFactory<User_books,String>("date_of_recived"));
            colFine.setCellValueFactory(new PropertyValueFactory<User_books,String>("fine"));
            colStatus.setCellValueFactory(new PropertyValueFactory<User_books,String>("status"));

            int uid = Integer.parseInt(userIDTextBox.getText());

            String query="select BookID,date_of_issued,date_of_resubmition,date_of_recived,amount_of_fine,status from user_books where UserID=?";
            Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", HelloApplication.DB_USERNAME, HelloApplication.DB_PASSWORD);
            PreparedStatement pst1 = con1.prepareStatement(query);
            pst1.setInt(1,uid);
            userID=uid;
            ResultSet rst = pst1.executeQuery();
            while(rst.next()){
                data.add(new User_books(rst.getString(1),String.valueOf(rst.getDate(2)),String.valueOf(rst.getDate(3)),String.valueOf(rst.getDate(4)),String.valueOf(rst.getBigDecimal(5)), rst.getString(6)));

            }
            bookTable.setItems(data);
            rst.close();
            pst1.close();
            con1.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void loadDataFromDatabase2(String Sql) throws SQLException {
        Connection con2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", HelloApplication.DB_USERNAME, HelloApplication.DB_PASSWORD);;
        PreparedStatement pst2 = con2.prepareStatement(Sql);
        pst2.setInt(1,userID);
        ResultSet rs2 = pst2.executeQuery();


        while(rs2.next()){
            data2.add(new User_books(rs2.getString(1),String.valueOf(rs2.getDate(2)),String.valueOf(rs2.getDate(3)),String.valueOf(rs2.getDate(4)),String.valueOf(rs2.getBigDecimal(5)), rs2.getString(6)));

        }
        bookTable.setItems(data2);
        Search2();
        rs2.close();
        pst2.close();
        con2.close();
    }
    public void Search2() {
        searchText2.setOnKeyReleased(e ->{
            if(searchText2.getText().equals("")){
                try {
                    data2.clear();
                    loadDataFromDatabase2("select BookID,date_of_issued,date_of_resubmition,date_of_recived,amount_of_fine,status from user_books where UserID=?");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }else{
                data2.clear();
                String sql = "Select Select BookID,date_of_issued,date_of_resubmition,date_of_recived,amount_of_fine,status from User_books where BookID Like \'%"+searchText2.getText()+"%\' "
                        +"UNION Select BookID,date_of_issued,date_of_resubmition,date_of_recived,amount_of_fine,status from User_books where status Like \'%"+searchText2.getText()+"%\' ";
                try {
                    Connection con2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", HelloApplication.DB_USERNAME, HelloApplication.DB_PASSWORD);;
                    PreparedStatement pst2 = con2.prepareStatement(sql);
                    ResultSet rs2=pst2.executeQuery();

                    while(rs2.next()){
                        data2.add(new User_books(rs2.getString(1),String.valueOf(rs2.getDate(2)),String.valueOf(rs2.getDate(3)),String.valueOf(rs2.getDate(4)),String.valueOf(rs2.getBigDecimal(5)), rs2.getString(6)));

                    }
                    rs2.close();
                    pst2.close();
                    con2.close();
                }catch (Exception a){
                    a.printStackTrace();
                }
            }
        });
    }

    public void loadDataFromDatabase1(String Sql) throws SQLException {
        Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", HelloApplication.DB_USERNAME, HelloApplication.DB_PASSWORD);;
        PreparedStatement pst1 = con1.prepareStatement(Sql);
        ResultSet rs1 = pst1.executeQuery();



        while(rs1.next()){
            data1.add(new login(String.valueOf(rs1.getInt(1)),rs1.getString(2),rs1.getString(3),rs1.getString(4), rs1.getString(5),rs1.getString(7)));

        }
        usersTable.setItems(data1);
        Search1();
        rs1.close();
        pst1.close();
        con1.close();
    }

    public void Search1() {
        searchText1.setOnKeyReleased(e ->{
            if(searchText1.getText().equals("")){
                try {
                    data1.clear();
                    loadDataFromDatabase1("select user_ID,FName,LName,BOD,UserName,Role from login");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }else{
                data1.clear();
                String sql = "Select user_ID,FName,LName,BOD,UserName,Role from login where user_ID Like \'%"+Integer.parseInt(searchText1.getText())+"%\' "+"UNION Select user_ID,FName,LName,BOD,UserName,Role from login where FName Like \'%"+searchText1.getText()+"%\' "
                        +"UNION Select user_ID,FName,LName,BOD,UserName,Role from login where LName Like \'%"+searchText1.getText()+"%\' "+"UNION Select user_ID,FName,LName,BOD,UserName,Role from login where Role Like \'%"+searchText1.getText()+"%\' ";
                try {
                    Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", HelloApplication.DB_USERNAME, HelloApplication.DB_PASSWORD);;
                    PreparedStatement pst1 = con1.prepareStatement(sql);
                    ResultSet rs1=pst1.executeQuery();

                    while(rs1.next()){
                        data1.add(new login(String.valueOf(rs1.getInt(1)),rs1.getString(2),rs1.getString(3),String.valueOf(rs1.getDate(4)), rs1.getString(5), rs1.getString(6)));
                    }
                    rs1.close();
                    pst1.close();
                    con1.close();
                }catch (Exception a){
                    a.printStackTrace();
                }
            }
        });
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        memberPane2.setVisible(false);
        searchText2.setVisible(false);
        bookTable.setVisible(false);
        backBtn2.setVisible(false);
        System.out.println(HelloApplication.openDelete);
        delMemberBtn.setVisible(HelloApplication.openDelete);
        submitBtn.setVisible(!HelloApplication.openDelete);
        selectBtn.setVisible(HelloApplication.openDelete);

        try{
            ObservableList<login> data = FXCollections.observableArrayList();
            colUserID.setCellValueFactory(new PropertyValueFactory<login,String>("userID"));
            colFname.setCellValueFactory(new PropertyValueFactory<login,String>("fname"));
            colLname.setCellValueFactory(new PropertyValueFactory<login,String>("lname"));
            colDob.setCellValueFactory(new PropertyValueFactory<login,String>("DOB"));
            colUserName.setCellValueFactory(new PropertyValueFactory<login,String>("userName"));
            ColRole.setCellValueFactory(new PropertyValueFactory<login,String>("role"));


            String query="select user_ID,FName,LName,BOD,UserName,Role from login";
            Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", HelloApplication.DB_USERNAME, HelloApplication.DB_PASSWORD);
            PreparedStatement pst1 = con1.prepareStatement(query);
            ResultSet rst = pst1.executeQuery();
            while(rst.next()){
                data.add(new login(String.valueOf(rst.getInt(1)),rst.getString(2),rst.getString(3), String.valueOf(rst.getDate(4)), rst.getString(5),rst.getString(6)));

            }
            usersTable.setItems(data);
            rst.close();
            pst1.close();
            con1.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
