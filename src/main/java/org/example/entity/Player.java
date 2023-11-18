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
    public final int secreenX;
    public final int secreenY;
    int hasKey=0;
    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gp = gamePanel;
        this.kh = keyHandler;

        screenX=gp.screenWidth/2 - (gp.tileSize/2);
        screenY=gp.screenHeight/2 - (gp.tileSize/2);

        solidArea=new Rectangle();
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
        worldX=gp.tileSise *23;
        worldY=gp.tileSise * 21;
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
        if(kh.upPressed){ direction="up"; }
        else if(kh.downPressed){ direction="down"; }
        else if(kh.leftPressed){ direction="left";  }
        else if(kh.rightPressed){ direction="right";  }
    }
    collisionOn=false;
    gp.cChecker.checkTile(this);

    int objIndex=gp.cCheckObject(this,true);
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
    public void pickupObject(int i){
        if(i!=999){
            String objectName=gp.obj[i].name;
            switch (objectName){
                case "Key":
                    hasKey++;
                    gp.obj[i]=null;
                    System.out.println("key:"+hasKey);
                    break;
                case "Door":
                    if(hasKey>0){
                        gp.obj[i]=null;
                        hasKey--;
                    }
                    System.out.println("key:"+hasKey);
                    break;
            }
        }
    }
    public void draw(Graphics2D g){
        //g.setColor(Color.WHITE);
        //g.fillRect(x,y,48,48);
        BufferedImage image = switch (direction) {
            case "up" -> up1;
            case "down" -> down1;
            case "left" -> left1;
            case "right" -> right1;
            default -> null;
        };
        g.drawImage(image, secreenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
