package org.example;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements Runnable{
    final int screenWidth = 768;
    final int screenHeight = 576;
    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

    }
    public void startGameTread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    /*public void run() {
        double drawInterval = 1000000000.0/60;
        double nextDrawTime = System.nanoTime() + drawInterval;
        while (gameThread != null) {
            update();
            repaint(); // that's how we can call the paintComponent methode!!
            try {
                double remainingTime = (nextDrawTime - System.nanoTime())/1000000.0 ;
                if(remainingTime<0) remainingTime=0;
                Thread.sleep((long)remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }*/
    public void run() {
        double drawInterval = 1000000000.0 / 60;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        while (gameThread != null) {
            currentTime=System.nanoTime();
            delta+=(currentTime-lastTime)/drawInterval;
            timer+=currentTime-lastTime;
            lastTime=currentTime;
            if(delta>=1) {
                update();
                repaint(); // that's how we can call the paintComponent methode!!
                delta--;
            }
            if(timer>=1000000000) timer=0;
        }
    }
    public void update(){
        if(keyHandler.upPressed) playerY -= playerSpeed;
        else if(keyHandler.downPressed) playerY += playerSpeed;
        else if(keyHandler.leftPressed) playerX -= playerSpeed;
        else if(keyHandler.rightPressed) playerX += playerSpeed;
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.WHITE);
        g2.fillRect(playerX,playerY,48,48);
        g2.dispose();
    }
}
