package org.example.entity;

import org.example.GamePanel;
import org.example.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler kh;
    public int screenX;
    public int screenY;
    int hasKey=0;
    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gp = gamePanel;
        this.kh = keyHandler;

        screenX=gp.screenWidth/2 - (gp.tileSize/2);
        screenY=gp.screenHeight/2 - (gp.tileSize/2);

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
        worldX=gp.tileSize * 23;   //player's strating position on the world map
        worldY=gp.tileSize * 21;
        speed=4;
        direction="down";
    }
    public void getPlayerImages(){

        try {
            up1 = ImageIO.read(getClass().getResource("/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResource("/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResource("/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResource("/player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResource("/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResource("/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResource("/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResource("/player/boy_right_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void update(){
        if(kh.leftPressed || kh.downPressed || kh.upPressed || kh.rightPressed){
            if(kh.upPressed){ direction="up" ;worldY -= speed;;}
            else if(kh.downPressed){ direction="down"; worldY += speed;}
            else if(kh.leftPressed){ direction="left"; worldX -= speed;}
            else if(kh.rightPressed){ direction="right"; worldX += speed; }

            //CHECK TILE COLLISION
            collisionOn=false;
            gp.cChecker.checkTile(this);

            //CHECK OBJECT COLLISION
            int objIndex=gp.cChecker.checkObject(this,true);
            pickupObject(objIndex);

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
            String objectName=gp.obj[i].name;
            switch (objectName){
                case "Key":
                    gp.playSE(1);
                    hasKey++;
                    gp.obj[i]=null;
                    System.out.println("key:"+hasKey);
                    break;
                case "Door":
                    if(hasKey>0){
                        gp.playSE(3);
                        gp.obj[i]=null;
                        hasKey--;
                    }
                    System.out.println("key:"+hasKey);
                    break;
                case "Boots":
                    gp.playSE(2);
                    speed+=2;
                    gp.obj[i] = null;
                    break;
            }
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
        g.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
