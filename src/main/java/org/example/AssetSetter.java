package org.example;

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
        gp.obj[0]=new OBJ_key();
        gp.obj[0].worldX=23*gp.tileSize;
        gp.obj[0].worldY=7*gp.tileSize;

        gp.obj[1]=new OBJ_key();
        gp.obj[1].worldX=23*gp.tileSize;
        gp.obj[1].worldY=40*gp.tileSize;

        gp.obj[3]=new OBJ_key();
        gp.obj[3].worldX=38*gp.tileSize;
        gp.obj[3].worldY=8*gp.tileSize;

        gp.obj[4]=new OBJ_Door();
        gp.obj[4].worldX=10*gp.tileSize;
        gp.obj[4].worldY=11*gp.tileSize;

        gp.obj[5]=new OBJ_Door();
        gp.obj[5].worldX=8*gp.tileSize;
        gp.obj[5].worldY=28*gp.tileSize;

        gp.obj[6]=new OBJ_Door();
        gp.obj[6].worldX=12*gp.tileSize;
        gp.obj[6].worldY=22*gp.tileSize;

        gp.obj[7]=new OBJ_Chest();
        gp.obj[7].worldX=10*gp.tileSize;
        gp.obj[7].worldY=7*gp.tileSize;
    }
}