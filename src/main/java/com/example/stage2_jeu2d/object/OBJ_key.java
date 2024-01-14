package com.example.stage2_jeu2d.object;

import com.example.stage2_jeu2d.GamePanel;
import com.example.stage2_jeu2d.entity.Entity;

public class OBJ_key extends Entity {

    public OBJ_key(GamePanel gp){
        super(gp);

        name="key";
        down1=setup("/objects/key",gp.tileSize,gp.tileSize);

    }
}