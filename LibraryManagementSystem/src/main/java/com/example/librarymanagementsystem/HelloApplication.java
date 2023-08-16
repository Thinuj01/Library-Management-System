package com.example.librarymanagementsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    protected static String DB_USERNAME = "root";
    protected static String DB_PASSWORD = "123456789";
    //protected static String DB_PASSWORD = "Thinuja21033";
    @Override
    public void start(Stage stage) throws IOException {
        LoadWindow.loadInterFace("Login.fxml","Hello!",1280, 800);
    }

    public static void main(String[] args) {
        launch();
    }
}