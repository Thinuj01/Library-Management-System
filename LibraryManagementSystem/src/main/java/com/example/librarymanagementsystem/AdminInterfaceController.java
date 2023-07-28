package com.example.librarymanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;


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

    ObservableList <Book> list = FXCollections.observableArrayList(
            new Book("A0001","Mathematics","Thinuja","Maths","200.00","500","M5"),
            new Book("A0002","Commerce","Tiran","Statistic","1000.00","2500","C3"),
            new Book("A0003","Science","Nuran","Science","2000.00","1200","S10")

    );
    @Override
    public void initialize(URL url, ResourceBundle rb){

        colBookID.setCellValueFactory(new PropertyValueFactory<Book,String>("BookID"));
        colBookName.setCellValueFactory(new PropertyValueFactory<Book,String>("BookName"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<Book,String>("Author"));
        colCategory.setCellValueFactory(new PropertyValueFactory<Book,String>("Category"));
        colPrice.setCellValueFactory(new PropertyValueFactory<Book,String>("Price"));
        colNoPages.setCellValueFactory(new PropertyValueFactory<Book,String>("NoPages"));
        colLocation.setCellValueFactory(new PropertyValueFactory<Book,String>("Location"));

        BookTable.setItems(list);
    }



}
