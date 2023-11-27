package org.example.entity;

import org.example.GamePanel;
import org.example.KeyHandler;
import org.example.UtilityTool;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
    KeyHandler kh;
    public int screenX;
    public int screenY;
    // public int hasKey=0;
    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        super(gamePanel);
        this.kh = keyHandler;

        screenX=gamePanel.screenWidth/2 - (gamePanel.tileSize/2);
        screenY=gamePanel.screenHeight/2 - (gamePanel.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x=8;
        solidArea.y=16;
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;
        solidArea.width=32;
        solidArea.height=32;
        setDefaultValue();
        getPlayerImages();
    }
    public void setDefaultValue(){
        worldX=gamePanel.tileSize * 23;   //player's strating position on the world map
        worldY=gamePanel.tileSize * 21;
        speed=4;
        direction="down";
    }
    public void getPlayerImages(){

        up1 = setup("/player/boy_up_1");
        up2 = setup("/player/boy_up_2");
        down1 = setup("/player/boy_down_1");
        down2 = setup("/player/boy_down_2");
        left1 = setup("/player/boy_left_1");
        left2 = setup("/player/boy_left_2");
        right1 = setup("/player/boy_right_1");
        right2 = setup("/player/boy_right_2");

    }

    public void update(){
        if(kh.leftPressed || kh.downPressed || kh.upPressed || kh.rightPressed){
            if(kh.upPressed){ direction="up" ;}
            else if(kh.downPressed){ direction="down"; }
            else if(kh.leftPressed){ direction="left"; }
            else if(kh.rightPressed){ direction="right"; }

            //CHECK TILE COLLISION
            collisionOn=false;
            gamePanel.cChecker.checkTile(this);

            //CHECK OBJECT COLLISION
            int objIndex=gamePanel.cChecker.checkObject(this,true);
            pickupObject(objIndex);

            //CHECK NPC COLLISION
            int npcIndex=gamePanel.cChecker.checkEntity(this,gamePanel.npc);
            inyeractNPC(npcIndex);

            if(collisionOn==false){
                switch (direction){
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

            spriteCounter++;
            if(spriteCounter > 12){
                if(spriteNum == 1) {spriteNum=2;}
                else if(spriteNum == 2){ spriteNum=1;}
                else spriteNum =0;
            }
        }

    }



    public void pickupObject(int i){
        if(i!=999){

        }
    }
    public void inyeractNPC(int i){
        if(i!=999){

        }
    }
    public void draw(Graphics2D g){
        //g.setColor(Color.WHITE);
        //g.fillRect(x,y,48,48);

        BufferedImage image =null;

        switch (direction) {
            case "up":
                if(spriteNum ==1){
                    image= up1;
                }
                if(spriteNum ==2){
                    image= up2;
                }
                break;
            case "down" :
                if(spriteNum ==1){
                    image= down1;
                }
                if(spriteNum ==2){
                    image= down2;
                }
                break;
            case "left":
                if(spriteNum ==1){
                    image= left1;
                }
                if(spriteNum ==2){
                    image= left2;
                }
                break;
            case "right" :
                if(spriteNum ==1){
                    image= right1;
                }
                if(spriteNum ==2){
                    image= right2;
                }
                break;

        }
        g.drawImage(image, screenX, screenY,null);
    }
}
