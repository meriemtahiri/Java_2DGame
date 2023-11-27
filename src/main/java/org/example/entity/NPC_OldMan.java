package org.example.entity;

import org.example.GamePanel;

import java.util.Random;

public class NPC_OldMan extends Entity{

    public NPC_OldMan(GamePanel gamePanel) {
        super(gamePanel);
        direction="down";
        speed=1;
        getOldManImages();
    }
    public void getOldManImages(){
        up1 = setup("/npc/oldman_up_1");
        up2 = setup("/npc/oldman_up_2");
        down1 = setup("/npc/oldman_down_1");
        down2 = setup("/npc/oldman_down_2");
        left1 = setup("/npc/oldman_left_1");
        left2 = setup("/npc/oldman_left_2");
        right1 = setup("/npc/oldman_right_1");
        right2 = setup("/npc/oldman_right_2");
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
