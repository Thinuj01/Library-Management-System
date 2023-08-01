package com.example.librarymanagementsystem;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoadWindow {
    public static void loadInterFace(String source,String title,int width,int height){

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(source));
            Scene scene2 = new Scene(fxmlLoader.load(),width,height);
            Stage stage2 = new Stage();
            stage2.setTitle(title);
            stage2.setScene(scene2);
            stage2.show();
        }catch(Exception e){
            System.out.println(e);
        }

    }
}
