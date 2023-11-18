package org.example;
public class AssetSetter
{
    GamePanel gp;
    public AssetSetter(GamePnel gp){
        this.gp=gp;
    }
    public void setObject(){
        gp.obj[0]=new OBJ_key();
        gp.obj[0]=WorldX=23*gp.tileSize;
        gp.obj[0]=WorldY=7*gp.tileSize;

        gp.obj[1]=new OBJ_key();
        gp.obj[1]=WorldX=23*gp.tileSize;
        gp.obj[1]=WorldY=40*gp.tileSize;

        gp.obj[3]=new OBJ_key();
        gp.obj[3]=WorldX=38*gp.tileSize;
        gp.obj[3]=WorldY=8*gp.tileSize;

        gp.obj[4]=new OBJ_Door();
        gp.obj[4]=WorldX=10*gp.tileSize;
        gp.obj[4]=WorldY=11*gp.tileSize;

        gp.obj[5]=new OBJ_Door();
        gp.obj[5]=WorldX=8*gp.tileSize;
        gp.obj[5]=WorldY=28*gp.tileSize;

        gp.obj[6]=new OBJ_Door();
        gp.obj[6]=WorldX=12*gp.tileSize;
        gp.obj[6]=WorldY=22*gp.tileSize;

        gp.obj[7]=new OBJ_Chest();
        gp.obj[7]=WorldX=10*gp.tileSize;
        gp.obj[7]=WorldY=7*gp.tileSize;
    }
}