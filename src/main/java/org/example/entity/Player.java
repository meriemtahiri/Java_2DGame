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
    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gp = gamePanel;
        this.kh = keyHandler;
        setDefaultValue();
        getPlayerImages();
    }
    public void setDefaultValue(){
        x=100;
        y=100;
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
        if(kh.upPressed){ direction="up"; y -= speed; }
        else if(kh.downPressed){ direction="down"; y += speed; }
        else if(kh.leftPressed){ direction="left"; x -= speed; }
        else if(kh.rightPressed){ direction="right"; x += speed; }
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
        g.drawImage(image, x, y, 48, 48, null);
    }
}
