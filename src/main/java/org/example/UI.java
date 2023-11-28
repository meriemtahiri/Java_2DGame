package org.example;

import java.awt.*;
import java.text.DecimalFormat;

public class UI {
    GamePanel gp;
    Graphics2D graphics2D;
    Font arial_40,arial_80B;
    public boolean messageOn = false;
    public String message = "" ;
    int messageCounter =0;
    public boolean gameFinished = false;
    public String currentDialogue="";
    public UI(GamePanel gp){
        this.gp =gp;
        arial_40 = new Font("Arial",Font.PLAIN,40);
        arial_80B = new Font("Arial",Font.BOLD,80);
    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D graphics2D){
        this.graphics2D = graphics2D;
        graphics2D.setFont(arial_40);
        graphics2D.setColor(Color.WHITE);
        if(gp.gameState==gp.playState){

        }
        if(gp.gameState==gp.pauseState){
            drawPauseScreen();
        }
        if(gp.gameState==gp.dialogueState){
            drawDialogueScreen();
        }
    }
    public void drawPauseScreen(){
        graphics2D.setFont(graphics2D.getFont().deriveFont(Font.PLAIN,80F));
        String text = "PAUSED";
        int x=getXForCentredText(text), y=gp.screenHeight/2;
        graphics2D.drawString(text,x,y);
    }
    public void drawDialogueScreen(){
        int x=gp.tileSize*2, y=gp.tileSize/2, width=gp.screenWidth-gp.tileSize*4, height=gp.tileSize*4;
        drawSubwindow(x,y,width,height);
        graphics2D.setColor(new Color(255,255,255));
        graphics2D.setFont(graphics2D.getFont().deriveFont(Font.PLAIN,28F));
        for(String line : currentDialogue.split("\n")){
            graphics2D.drawString(currentDialogue,x+gp.tileSize,y+gp.tileSize);
            y+=40;
        }
    }
    public void drawSubwindow(int x, int y, int width, int height){
        graphics2D.setColor(new Color(0,0,0,100));
        graphics2D.fillRoundRect(x,y,width,height,35,35);
        graphics2D.setColor(new Color(255,255,255,100));
        graphics2D.setStroke(new BasicStroke(5));
        graphics2D.drawRoundRect(x+5,y+5,width-10,height-10,25,25);
    }
    public int getXForCentredText(String text){
        int length = (int)graphics2D.getFontMetrics().getStringBounds(text,graphics2D).getWidth();
        return gp.screenWidth/2-length/2;
    }
}
