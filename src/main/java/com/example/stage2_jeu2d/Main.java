// Main.java
package com.example.stage2_jeu2d;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        GamePanel gamePanel = new GamePanel();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view2.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), gamePanel.screenWidth, gamePanel.screenHeight);
        primaryStage.setTitle("Jeu 2D");
        primaryStage.setScene(scene);
        primaryStage.show();

        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

    }



}
