package org.example;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D GAME");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();

        window.setLocationRelativeTo(null); //not specify the location of the window = the window will be displayed at the center of the screen
        window.setVisible(true);


        gamePanel.setupGame();
        gamePanel.startGameTread();
    }
}