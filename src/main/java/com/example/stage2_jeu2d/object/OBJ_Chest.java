package com.example.stage2_jeu2d.object;


import com.example.stage2_jeu2d.GamePanel;
import com.example.stage2_jeu2d.entity.Entity;

public class OBJ_Chest extends Entity {


    public OBJ_Chest( GamePanel gp){
        super(gp);
        name="Chest";
        down1=setup("/objects/chest",gp.tileSize,gp.tileSize);
    }
}