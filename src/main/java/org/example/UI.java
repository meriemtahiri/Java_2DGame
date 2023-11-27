package org.example;

import org.example.object.OBJ_key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
    GamePanel gp;
    Graphics2D graphics2D;
    Font arial_40,arial_80B;
    // BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "" ;
    int messageCounter =0;
    public boolean gameFinished = false;
    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");
    public UI(GamePanel gp){
        this.gp =gp;
        arial_40 = new Font("Arial",Font.PLAIN,40);
        arial_80B = new Font("Arial",Font.BOLD,80);
        // OBJ_key key=new OBJ_key(gp);
        // keyImage = key.image;
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
    }
    public void drawPauseScreen(){
        graphics2D.setFont(graphics2D.getFont().deriveFont(Font.PLAIN,80F));
        String text = "PAUSED";
        int x=getXForCentredText(text), y=gp.screenHeight/2;
        graphics2D.drawString(text,x,y);
    }
    public int getXForCentredText(String text){
        int length = (int)graphics2D.getFontMetrics().getStringBounds(text,graphics2D).getWidth();
        return gp.screenWidth/2-length/2;
    }
}
