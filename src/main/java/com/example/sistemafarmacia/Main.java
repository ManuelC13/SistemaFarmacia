package com.example.sistemafarmacia;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 *Clase principal que permite ejecutar la aplicación y abrir la ventana GeneralView.fxml.
 */
public class Main extends Application {
    public static Stage ventana;
    @Override
    public void start(Stage primaryStage) {
        try {
            BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("GeneralView.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            this.ventana = primaryStage;
            primaryStage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}