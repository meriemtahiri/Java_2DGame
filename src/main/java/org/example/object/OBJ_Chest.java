package org.example.object;

import org.example.GamePanel;
import org.example.entity.Entity;


public class OBJ_Chest extends Entity {


    public OBJ_Chest( GamePanel gp){
        super(gp);
        name="Chest";
        down1=setup("/objects/chest");
    }
}