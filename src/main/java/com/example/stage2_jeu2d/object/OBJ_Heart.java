package com.example.stage2_jeu2d.object;


import com.example.stage2_jeu2d.GamePanel;
import com.example.stage2_jeu2d.entity.Entity;
import javafx.scene.image.Image;

public class OBJ_Heart extends Entity {

    public OBJ_Heart(GamePanel gp){
        super(gp);
        name="Heart";
        image=setup("/objects/heart_full",gp.tileSize,gp.tileSize);
        image2 = setup("/objects/heart_half", gp.tileSize, gp.tileSize);
        image3=setup("/objects/heart_blank",gp.tileSize,gp.tileSize);


    }


}
