package org.example;

import org.example.entity.NPC_OldMan;
import org.example.object.OBJ_Boots;
import org.example.object.OBJ_Chest;
import org.example.object.OBJ_Door;
import org.example.object.OBJ_key;

public class AssetSetter
{
    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp=gp;
    }
    public void setObject(){

    }
    public void setNPC(){
        gp.npc[0]=new NPC_OldMan(gp);
        gp.npc[0].worldX=gp.tileSize*21;
        gp.npc[0].worldY=gp.tileSize*21;
    }
}