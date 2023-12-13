package org.example;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    GamePanel gamePanel ;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
    // DEBUG
    boolean checkDrawTime = false;

    public KeyHandler(GamePanel gp) {
        this.gamePanel = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        //TITLE S TATE
        if(gamePanel.gameState==gamePanel.titleState){
            if(gamePanel.ui.titleScreenState==0){
                if(code == KeyEvent.VK_UP) {
                    gamePanel.ui.commandNum--;
                    if(gamePanel.ui.commandNum<0){
                        gamePanel.ui.commandNum=2;
                    }
                }
                if(code == KeyEvent.VK_DOWN) {
                    gamePanel.ui.commandNum++;
                    if(gamePanel.ui.commandNum>2){
                        gamePanel.ui.commandNum=0;
                    }
                }
                if(code==KeyEvent.VK_ENTER){
                    if(gamePanel.ui.commandNum==0){
                        gamePanel.ui.titleScreenState=1;
                        //gamePanel.playMusic(0);
                    }
                    if(gamePanel.ui.commandNum==1){

                    }
                    if(gamePanel.ui.commandNum==2){
                        System.exit(0);
                    }
                }
            }
            else if(gamePanel.ui.titleScreenState==1){
                if(code == KeyEvent.VK_UP) {
                    gamePanel.ui.commandNum--;
                    if(gamePanel.ui.commandNum<0){
                        gamePanel.ui.commandNum=3;
                    }
                }
                if(code == KeyEvent.VK_DOWN) {
                    gamePanel.ui.commandNum++;
                    if(gamePanel.ui.commandNum>3){
                        gamePanel.ui.commandNum=0;
                    }
                }
                if(code==KeyEvent.VK_ENTER){
                    if(gamePanel.ui.commandNum==0){
                        System.out.println("Do some fighter specific stuff!");
                        gamePanel.gameState=gamePanel.playState;
                        //gamePanel.playMusic(0);
                    }
                    if(gamePanel.ui.commandNum==1){
                        System.out.println("Do some thief specific stuff!");
                        gamePanel.gameState=gamePanel.playState;
                        //gamePanel.playMusic(0);
                    }
                    if(gamePanel.ui.commandNum==2){
                        System.out.println("Do some sorcerer specific stuff!");
                        gamePanel.gameState=gamePanel.playState;
                        //gamePanel.playMusic(0);
                    }
                    if(gamePanel.ui.commandNum==3){
                        gamePanel.ui.titleScreenState=0;

                    }
                }
            }

        }
        if(gamePanel.gameState==gamePanel.playState){

            if(code == KeyEvent.VK_UP) upPressed=true;
            if(code == KeyEvent.VK_DOWN) downPressed=true;
            if(code == KeyEvent.VK_LEFT) leftPressed=true;
            if(code == KeyEvent.VK_RIGHT) rightPressed=true;
            if(code == KeyEvent.VK_SPACE) gamePanel.gameState= gamePanel.pauseState;
            if(code == KeyEvent.VK_ENTER) enterPressed=true;
            // DEBUG
            if(code == KeyEvent.VK_T)
                if(checkDrawTime == false ) checkDrawTime = true;
                else if(checkDrawTime == true) checkDrawTime = false;

        }
        if(gamePanel.gameState==gamePanel.pauseState){
            if(code == KeyEvent.VK_SPACE) gamePanel.gameState= gamePanel.playState;
        }
        if(gamePanel.gameState==gamePanel.dialogueState){
            if(code == KeyEvent.VK_ENTER) gamePanel.gameState=gamePanel.playState;
        }
        }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_UP) upPressed=false;
        if(code == KeyEvent.VK_DOWN) downPressed=false;
        if(code == KeyEvent.VK_LEFT) leftPressed=false;
        if(code == KeyEvent.VK_RIGHT) rightPressed=false;
    }
}
