package org.example.entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int worldX, worldY, speed;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2; // it describes an Image with an accessible buffer of image data.(we use this to store our image files)
    public String direction;
    public int spriteCounter=0;
    public int spriteNum=1;
    public Rectangle solidArea;
    public int solidAreaDefaultX,solidAreaDefaultY;
    public boolean collisionOn=false;

}
