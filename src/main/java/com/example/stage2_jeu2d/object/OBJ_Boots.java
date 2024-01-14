package com.example.stage2_jeu2d.object;


import com.example.stage2_jeu2d.GamePanel;
import com.example.stage2_jeu2d.entity.Entity;

public class OBJ_Boots extends Entity {


    public OBJ_Boots( GamePanel gp) {
       super(gp);

        name="Boots";
        down1=setup("/objects/boots",gp.tileSize,gp.tileSize);
    }
}
