package com.example.stage2_jeu2d.entity;


import com.example.stage2_jeu2d.GamePanel;

import java.util.Random;

public class NPC_OldMan extends Entity{
    public NPC_OldMan(GamePanel gamePanel) {
        super(gamePanel);
        direction="down";
        speed=1;
        getOldManImages();
        setDialogue();
    }
    public void getOldManImages(){
        up1 = setup("/npc/oldman_up_1",gamePanel.tileSize,gamePanel.tileSize);
        up2 = setup("/npc/oldman_up_2",gamePanel.tileSize,gamePanel.tileSize);
        down1 = setup("/npc/oldman_down_1",gamePanel.tileSize,gamePanel.tileSize);
        down2 = setup("/npc/oldman_down_2",gamePanel.tileSize,gamePanel.tileSize);
        left1 = setup("/npc/oldman_left_1",gamePanel.tileSize,gamePanel.tileSize);
        left2 = setup("/npc/oldman_left_2",gamePanel.tileSize,gamePanel.tileSize);
        right1 = setup("/npc/oldman_right_1",gamePanel.tileSize,gamePanel.tileSize);
        right2 = setup("/npc/oldman_right_2",gamePanel.tileSize,gamePanel.tileSize);
    }
    public void setDialogue(){
        dialogues[0]="hallo, Yassmina.";
        dialogues[1]="So you've come to this islan to \nfind the treasure?";
        dialogues[2]="I uset to be a great wizard but now ... \nI'm a bit too old for talking an adventure.";
        dialogues[3]="Well, good luck on you.";
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
            actionLookCont=0;}
    }
    public void speak(){
        super.speak();
    }
}
