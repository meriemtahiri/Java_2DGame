package com.example.stage2_jeu2d.monster;


import com.example.stage2_jeu2d.GamePanel;
import com.example.stage2_jeu2d.entity.Entity;

import java.util.Random;

public class MON_GreenSlime extends Entity {

    public MON_GreenSlime(GamePanel gamePanel) {
        super(gamePanel);
        type=2;
        name="Green Slime";
        speed=1;
        maxLife=4;
        life=maxLife;
        solidArea.setX(3);
        solidArea.setY(18);
        solidArea.setWidth(42);
        solidArea.setHeight(30);
        solidAreaDefaultX = (int) solidArea.getX();
        solidAreaDefaultY = (int) solidArea.getY();
        getImage();
    }



    public void getImage(){

        up1=setup("/monster/greenslime_down_1",gamePanel.tileSize,gamePanel.tileSize);
        up2=setup("/monster/greenslime_down_2",gamePanel.tileSize,gamePanel.tileSize);
        down1=setup("/monster/greenslime_down_1",gamePanel.tileSize,gamePanel.tileSize);
        down2=setup("/monster/greenslime_down_2",gamePanel.tileSize,gamePanel.tileSize);
        left1=setup("/monster/greenslime_down_1",gamePanel.tileSize,gamePanel.tileSize);
        left2=setup("/monster/greenslime_down_2",gamePanel.tileSize,gamePanel.tileSize);
        right1=setup("/monster/greenslime_down_1",gamePanel.tileSize,gamePanel.tileSize);
        right2=setup("/monster/greenslime_down_2",gamePanel.tileSize,gamePanel.tileSize);
    }
    public void setAction(){
        actionLookCont++;
        if(actionLookCont==120){
            Random random = new Random();
            int i = random.nextInt(100)+1;
            if(i<=25) direction="up";
            else if(i>25 && i<=50) direction="down";
            else if(i>50 && i<=75) direction="left";
            else direction="right";
            actionLookCont=0;
        }
    }
}
