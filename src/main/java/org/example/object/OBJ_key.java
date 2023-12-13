package org.example.object;

import org.example.GamePanel;

import org.example.entity.Entity;




public class OBJ_key extends Entity {

    public OBJ_key(GamePanel gp){
        super(gp);

        name="key";
        down1=setup("/objects/key");

    }
}