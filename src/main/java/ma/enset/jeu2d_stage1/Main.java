package ma.enset.jeu2d_stage1;

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
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), gamePanel.screenWidth, gamePanel.screenHeight);
        primaryStage.setTitle("Jeu 2D");
        primaryStage.setScene(scene);
        primaryStage.show();

        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());




    }
}


/*KeyHandler keyHandler = new KeyHandler(gamePanel);
        scene.setOnKeyPressed(e -> keyHandler.handleKeyPress(e));
        scene.setOnKeyReleased(e -> keyHandler.handleKeyRelease(e));
        gamePanel.setupGame();
        gamePanel.startGameThread();*/


       /* primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();*/