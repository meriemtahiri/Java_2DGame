package ma.enset.jeu2d_stage1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
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
        scene.setOnKeyPressed(e -> keyHandler.handleKeyPress(e));
        scene.setOnKeyReleased(e -> keyHandler.handleKeyRelease(e));
        gamePanel.setupGame();
        gamePanel.startGameThread();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void startGame(ActionEvent actionEvent) {
        Scene scene = ((Node) actionEvent.getSource()).getScene();
        Stage stage = (Stage) scene.getWindow();
        start(actionEvent, stage);
    }
}
