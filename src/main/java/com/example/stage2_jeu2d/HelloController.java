package com.example.stage2_jeu2d;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class HelloController {

    @FXML
    private void terminate() {
        System.exit(0);
    }

    public void start(ActionEvent actionEvent, Stage stage) {

        GamePanel gamePanel = new GamePanel();
        Scene scene = new Scene(gamePanel, gamePanel.screenWidth, gamePanel.screenHeight);
        KeyHandler keyHandler = new KeyHandler(gamePanel);
        scene.setOnKeyPressed(e -> keyHandler.handle(e));
        scene.setOnKeyReleased(e -> keyHandler.keyReleased(e));
        gamePanel.setupGame();
        gamePanel.startGameThread();
        gamePanel.gameState =  gamePanel.playState;
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void StartStage2(ActionEvent actionEvent) {
        Scene scene = ((Node) actionEvent.getSource()).getScene();
        Stage stage = (Stage) scene.getWindow();
        start(actionEvent, stage);
    }




}