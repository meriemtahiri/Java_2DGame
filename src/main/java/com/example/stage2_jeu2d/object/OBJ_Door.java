package com.example.stage2_jeu2d.object;


import com.example.stage2_jeu2d.GamePanel;
import com.example.stage2_jeu2d.entity.Entity;

public class OBJ_Door extends Entity {


    public OBJ_Door( GamePanel gp){
        super(gp);
        name="Door";
        down1=setup("/objects/door",gp.tileSize,gp.tileSize);
        collision=true;
        solidArea.setX(0);
        solidArea.setY(16);
        solidArea.setWidth(32);
        solidArea.setHeight(32);
        solidAreaDefaultX = (int) solidArea.getX();
        solidAreaDefaultY = (int) solidArea.getY();

    }
}