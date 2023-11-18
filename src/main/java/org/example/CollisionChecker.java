package org.example;
public class CollisionChecker {
    public CollisionChecker(GamePanel gp){
        this.gp=gp;

    }
    public void checkTile(Entity entity){
        int entityLeftWorldX=entity.WorldX+entity.solidArea.x;
        int entityRightWorldX=entity.WorldX+entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY=entity.WorldY+entity.solidArea.y;
        int entityBottomWorldY=entity.WorldY+entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol=entityLeftWorldX/gp.tileSize;
        int entityRightCol=entityRightWorldX/gp.tileSize;
        int entityTopRow=entityTopWorldY/gp.tileSize;
        int entityBottomRow=entityBottomWorldY/gp.tileSize;

        int tileNum1,tileNum2;

        switch (entity.direction){
            case "up":
                entityTopRow=(entityTopWorldY-entity.speed)/gp.tileSize;
                tileNum1=gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2=gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if(gp.tileM.tile[tileNum1].collision==true || gp.tileM.tile[tileNum2].collision==true){
                    entity.collisionOn=true;
                }

                break;
            case "down":
                entityBottomRow=(entityBottomWorldY+entity.speed)/gp.tileSize;
                tileNum1=gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2=gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision==true || gp.tileM.tile[tileNum2].collision==true){
                    entity.collisionOn=true;
                }
                break;
            case "left":
                entityLeftCol=(entityLeftWorldX-entity.speed)/gp.tileSize;
                tileNum1=gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2=gp.tileM.mapTileNum[entityLefttCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision==true || gp.tileM.tile[tileNum2].collision==true){
                    entity.collisionOn=true;
                }
                break;
            case "right":
                entityRightCol=(entityRightWorldX-entity.speed)/gp.tileSize;
                tileNum1=gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2=gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision==true || gp.tileM.tile[tileNum2].collision==true){
                    entity.collisionOn=true;
                }
                break;
        }
    }
    public int checkObject(Entity entity,boolean player){
        int index=999;
        for(int i=0;i<gp.obj.length;i++){
            if(gp.obj[i]!=null){
                entity.solidArea.x=entity.WorldX+entity.solidArea.x;
                entity.solidArea.y=entity.WorldY+entity.solidArea.y;

                gp.obj[i].solidArea.x=gp.obj[i].worldX+gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y=gp.obj[i].worldY+gp.obj[i].solidArea.y;

                switch (entity.direction){
                    case "up":
                        entity.solidArea.y -=entity.speed;
                        if(entity.solidArea.intersects(gp.OBJ[i].solidArea)){
                            if(gp.obj[i].collision==true){
                                entity.collisionOn=true;
                            }
                            if(player==true){
                                index=i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y +=entity.speed;
                        if(entity.solidArea.intersects(gp.OBJ[i].solidArea)){
                            if(gp.obj[i].collision==true){
                                entity.collisionOn=true;
                            }
                            if(player==true){
                                index=i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -=entity.speed;
                        if(entity.solidArea.intersects(gp.OBJ[i].solidArea)){
                            if(gp.obj[i].collision==true){
                                entity.collisionOn=true;
                            }
                            if(player==true){
                                index=i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x +=entity.speed;
                        if(entity.solidArea.intersects(gp.OBJ[i].solidArea)){
                            if(gp.obj[i].collision==true){
                                entity.collisionOn=true;
                            }
                            if(player==true){
                                index=i;
                            }
                        }
                        break;
                }
            }
            entity.solidArea.x=entity.solidAreaDefaultX;
            entity.solidArea.y=entity.solidAreaDefaultY;
            gp.obj[i].solidArea.x=gp.obj[i].solidAreaDefaultX;
            gp.obj[i].solidArea.y=gp.obj[i].solidAreaDefaultY;
        }
        return index;
    }
}