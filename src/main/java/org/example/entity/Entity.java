package org.example.entity;

import org.example.GamePanel;
import org.example.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Entity {
    GamePanel gamePanel;
    public int worldX, worldY, speed;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2; // it describes an Image with an accessible buffer of image data.(we use this to store our image files)
    public String direction;
    public int spriteCounter=0;
    public int spriteNum=1;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX,solidAreaDefaultY;
    public boolean collisionOn=false;
    public int actionLookCont = 0;

    public Entity(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    public void setAction(){}
    public void update(){
        setAction();
        collisionOn=false;
        gamePanel.cChecker.checkTile(this);
        gamePanel.cChecker.checkObject(this,false);
        gamePanel.cChecker.checkPlayer(this);

        if(!collisionOn){
            switch (direction) {
                case "up" -> worldY -= speed;
                case "down" -> worldY += speed;
                case "left" -> worldX -= speed;
                case "right" -> worldX += speed;
                default -> throw new IllegalStateException("Unexpected value: " + direction);
            }
        }

        spriteCounter++;
        if(spriteCounter > 12){
            if(spriteNum == 1) {spriteNum=2;}
            else if(spriteNum == 2){ spriteNum=1;}
            else spriteNum =0;
        }
    }
    public void draw(Graphics2D graphics2D){
        BufferedImage image =null;

        int screenX=worldX- gamePanel.player.worldX+gamePanel.player.screenX;
        int screenY=worldY- gamePanel.player.worldY+ gamePanel.player.screenY;
        if( worldX +gamePanel.tileSize > gamePanel.player.worldX- gamePanel.player.screenX &&
                worldX - gamePanel.tileSize<gamePanel.player.worldX+gamePanel.player.screenX &&
                worldY +gamePanel.tileSize> gamePanel.player.worldY- gamePanel.player.screenY &&
                worldY - gamePanel.tileSize<gamePanel.player.worldY+gamePanel.player.screenY){
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
            graphics2D.drawImage(image, screenX, screenY, 48, 48, null);
        }
    }
    public BufferedImage setup(String imagePath){

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try{
            image = ImageIO.read(getClass().getResource(imagePath+".png"));
            image =uTool.scaleImage(image,gamePanel.tileSize, gamePanel.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }
        return image;
    }
}
