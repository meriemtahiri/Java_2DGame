package com.example.stage2_jeu2d;

import com.example.stage2_jeu2d.entity.Entity;
import com.example.stage2_jeu2d.object.OBJ_Heart;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class UI {
    GamePanel gp;
    GraphicsContext graphicsContext;
    Image heart_full, heart_half, heart_blank;
    Font arial_40, arial_80B;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;
    public int titleScreenState = 0;

    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = Font.font("Arial", FontWeight.NORMAL, 20);
        arial_80B = Font.font("Arial", FontWeight.BOLD, 80);

        // CREATE HUB OBJECT
        Entity heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
        graphicsContext.setFont(arial_40);
        graphicsContext.setFill(Color.WHITE);

        // TITLE STATE
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }

        // PLAY STATE
        if (gp.gameState == gp.playState) {
            drawPlayerLife();
        }

        // PAUSE STATE
        if (gp.gameState == gp.pauseState) {
            drawPlayerLife();
            drawPauseScreen();
        }

        // DIALOGUE STATE
        if (gp.gameState == gp.dialogueState) {
            // drawPlayerLife();
            drawDialogueScreen();
        }
    }

    public void drawPlayerLife() {
        int x = gp.tileSize / 2;
        int y = gp.tileSize / 2;
        int i = 0;
        // DRAW MAX LIFE
        while (i < gp.player.maxLife / 2) {
            graphicsContext.drawImage(heart_blank, x, y);
            i++;
            x += gp.tileSize;
        }
        // RESET
        x = gp.tileSize / 2;
        y = gp.tileSize / 2;
        i = 0;
        // DRAW CURRENT LIFE
        while (i < gp.player.life) {
            graphicsContext.drawImage(heart_half, x, y);
            i++;
            if (i < gp.player.life) {
                graphicsContext.drawImage(heart_full, x, y);
            }
            i++;
            x += gp.tileSize;
        }
    }

    public void drawTitleScreen() {
        if (titleScreenState == 0) {
            graphicsContext.setFill(Color.rgb(70, 120, 80));
            graphicsContext.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

            graphicsContext.setFont(arial_80B);
            String text = "The Girl Warrior";
            double x = getXForCentredText(text);
            double y = gp.tileSize * 3;
            // SHADOW
            graphicsContext.setFill(Color.GRAY);
            graphicsContext.fillText(text, x + 5, y + 5);
            // MAIN COLOR
            graphicsContext.setFill(Color.WHITE);
            graphicsContext.fillText(text, x, y);

            // Blue girl IMAGE
            x = gp.screenWidth / 2 - (gp.tileSize * 2) / 2;
            y += gp.tileSize * 2;
            graphicsContext.drawImage(gp.player.down1, x, y, gp.tileSize * 2, gp.tileSize * 2);

            // Menu
            graphicsContext.setFont(arial_40);
            text = "MENU GAME";
            x = getXForCentredText(text);
            y += gp.tileSize * 3.5;
            graphicsContext.fillText(text, x, y);
            if (commandNum == 0) {
                graphicsContext.fillText(">", x - gp.tileSize, y);
            }

            text = "LOAD GAME";
            x = getXForCentredText(text);
            y += gp.tileSize;
            graphicsContext.fillText(text, x, y);
            if (commandNum == 1) {
                graphicsContext.fillText(">", x - gp.tileSize, y);
            }

            text = "QUIT";
            x = getXForCentredText(text);
            y += gp.tileSize;
            graphicsContext.fillText(text, x, y);
            if (commandNum == 2) {
                graphicsContext.fillText(">", x - gp.tileSize, y);
            }
        } else if (titleScreenState == 1) {
            graphicsContext.setFill(Color.WHITE);
            graphicsContext.setFont(arial_40);
            String text = "Select your class!";
            double x = getXForCentredText(text);
            double y = gp.tileSize * 3;
            graphicsContext.fillText(text, x, y);

            text = "Fighter";
            x = getXForCentredText(text);
            y += gp.tileSize * 3;
            graphicsContext.fillText(text, x, y);
            if (commandNum == 0) {
                graphicsContext.fillText(">", x - gp.tileSize, y);
            }

            text = "Thief";
            x = getXForCentredText(text);
            y += gp.tileSize;
            graphicsContext.fillText(text, x, y);
            if (commandNum == 1) {
                graphicsContext.fillText(">", x - gp.tileSize, y);
            }

            text = "Sorcerer";
            x = getXForCentredText(text);
            y += gp.tileSize;
            graphicsContext.fillText(text, x, y);
            if (commandNum == 2) {
                graphicsContext.fillText(">", x - gp.tileSize, y);
            }

            text = "Back";
            x = getXForCentredText(text);
            y += gp.tileSize * 2;
            graphicsContext.fillText(text, x, y);
            if (commandNum == 3) {
                graphicsContext.fillText(">", x - gp.tileSize, y);
            }
        }
    }

    public void drawPauseScreen() {
        graphicsContext.setFont(arial_80B);
        String text = "PAUSED";
        double x = getXForCentredText(text);
        double y = gp.screenHeight / 2;
        graphicsContext.fillText(text, x, y);
    }

    public void drawDialogueScreen() {
        double x = gp.tileSize * 2;
        double y = gp.tileSize / 2;
        double width = gp.screenWidth - gp.tileSize * 4;
        double height = gp.tileSize * 4;
        drawSubwindow(x, y, width, height);
        graphicsContext.setFont(arial_40);
        graphicsContext.setFill(Color.WHITE);
        x += gp.tileSize;
        y = gp.tileSize + 26;
        for (String line : currentDialogue.split("\n")) {
            graphicsContext.fillText(line, x, y);
            y += 40;
        }
    }

    public void drawSubwindow(double x, double y, double width, double height) {
        graphicsContext.setFill(Color.rgb(0, 0, 0, 0.8));
        graphicsContext.fillRoundRect(x, y, width, height, 35, 35);
        graphicsContext.setStroke(Color.WHITE);
        graphicsContext.setLineWidth(5);
        graphicsContext.strokeRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

    public double getXForCentredText(String text) {
        Text tempText = new Text(text);
        tempText.setFont(graphicsContext.getFont());
        return gp.screenWidth / 2 - tempText.getLayoutBounds().getWidth() / 2;
    }
}
