package org.example;

import org.example.object.OBJ_Heart;
import org.example.object.SuperObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.text.DecimalFormat;

public class UI {
    GamePanel gp;
    Graphics2D graphics2D;
    BufferedImage heart_full,heart_half,heart_blank;
    Font arial_40,arial_80B;
    public boolean messageOn = false;
    public String message = "" ;
    int messageCounter =0;
    public boolean gameFinished = false;
    public String currentDialogue="";
    public int commandNum=0;
    public int titleScreenState=0;
    public UI(GamePanel gp){
        this.gp =gp;
        arial_40 = new Font("Arial",Font.PLAIN,40);
        arial_80B = new Font("Arial",Font.BOLD,80);

        //CREATE HUB OBJECT
        SuperObject heart=new OBJ_Heart(gp);
        heart_full=heart.image;
        heart_half=heart.image2;
        heart_blank=heart.image3;

    }




    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D graphics2D){
        this.graphics2D = graphics2D;
        graphics2D.setFont(arial_40);
        graphics2D.setColor(Color.WHITE);

        if (gp.gameState==gp.titleState){
            drawTitleScreen();
        }
        if(gp.gameState==gp.playState){
               drawPlayerLife();
        }
        if(gp.gameState==gp.pauseState){
            drawPlayerLife();
            drawPauseScreen();
        }
        if(gp.gameState==gp.dialogueState){
            drawPlayerLife();
            drawDialogueScreen();
        }
    }
    public void drawPlayerLife(){
       // gp.player.life=5;
        int x=gp.tileSize/2;
        int y=gp.tileSize/2;
        int i=0;
       //DRAW MAX LIFE
        while (i<gp.player.maxLife/2){
            graphics2D.drawImage(heart_blank,x,y,null);
            i++;
            x+=gp.tileSize;
        }
        //RESET
        x=gp.tileSize/2;
        y=gp.tileSize/2;
        i=0;
        //DRA CURRENT LIFE
        while (i<gp.player.life){
            graphics2D.drawImage(heart_half,x,y,null);
            i++;
            if(i<gp.player.life){
                graphics2D.drawImage(heart_full,x,y,null);

            }
            i++;
            x+=gp.tileSize;
        }
    }
    public void drawTitleScreen(){
        if(titleScreenState==0){
            graphics2D.setColor(new Color(0,0,0));
            graphics2D.fillRect(0,0,gp.screenWidth,gp.screenHeight);

            graphics2D.setFont(graphics2D.getFont().deriveFont(Font.BOLD,96F));
            String text="Blue Boy Adventure";
            int x=getXForCentredText(text);
            int y=gp.tileSize*3;
            //SHADOW
            graphics2D.setColor(Color.gray);
            graphics2D.drawString(text,x+5,y+5);
            //MAIN COLOR
            graphics2D.setColor(Color.WHITE);
            graphics2D.drawString(text,x,y);
            //BLUE BOY IMAGE
            x=gp.screenWidth/2 - (gp.tileSize*2)/2;
            y +=gp.tileSize*2;
            graphics2D.drawImage(gp.player.down1,x,y,gp.tileSize*2,gp.tileSize*2,null);
            //Menu

            graphics2D.setFont(graphics2D.getFont().deriveFont(Font.BOLD,48F));

            text="MENU GAME";
            x=getXForCentredText(text);
            y+=gp.tileSize*3.5;
            graphics2D.drawString(text,x,y);
            if(commandNum==0){
                graphics2D.drawString(">",x-gp.tileSize,y);
            }

            text="LOAD GAME";
            x=getXForCentredText(text);
            y+=gp.tileSize;
            graphics2D.drawString(text,x,y);
            if(commandNum==1){
                graphics2D.drawString(">",x-gp.tileSize,y);
            }

            text="QUIT";
            x=getXForCentredText(text);
            y+=gp.tileSize;
            graphics2D.drawString(text,x,y);
            if(commandNum==2){
                graphics2D.drawString(">",x-gp.tileSize,y);
            }
        }else if(titleScreenState==1){
            graphics2D.setColor(Color.white);
            graphics2D.setFont(graphics2D.getFont().deriveFont(42F));
            String text="Select your class!";
            int x=getXForCentredText(text);
            int y=gp.tileSize*3;
            graphics2D.drawString(text,x,y);

            text="Fighter";
            x=getXForCentredText(text);
            y+=gp.tileSize*3;
            graphics2D.drawString(text,x,y);
            if(commandNum==0){
                graphics2D.drawString(">",x-gp.tileSize,y);
            }
            text="Thief";
            x=getXForCentredText(text);
            y+=gp.tileSize;
            graphics2D.drawString(text,x,y);
            if(commandNum==1){
                graphics2D.drawString(">",x-gp.tileSize,y);
            }
            text="Sorcerer";
            x=getXForCentredText(text);
            y+=gp.tileSize;
            graphics2D.drawString(text,x,y);
            if(commandNum==2){
                graphics2D.drawString(">",x-gp.tileSize,y);
            }
            text="Back";
            x=getXForCentredText(text);
            y+=gp.tileSize*2;
            graphics2D.drawString(text,x,y);
            if(commandNum==3){
                graphics2D.drawString(">",x-gp.tileSize,y);
            }
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
