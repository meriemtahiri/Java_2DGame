package org.example.object;

import org.example.GamePanel;
import org.example.entity.Entity;


public class OBJ_Door extends Entity {


    public OBJ_Door( GamePanel gp){
        super(gp);
        name="Door";
        down1=setup("/objects/door");
        collision=true;
    }
}