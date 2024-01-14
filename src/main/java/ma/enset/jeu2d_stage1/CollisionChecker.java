package ma.enset.jeu2d_stage1;

import javafx.scene.shape.Rectangle;
import ma.enset.jeu2d_stage1.entity.Entity;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        double entityLeftWorldX = entity.worldX + entity.solidArea.getX();
        double entityRightWorldX = entity.worldX + entity.solidArea.getX() + entity.solidArea.getWidth();
        double entityTopWorldY = entity.worldY + entity.solidArea.getY();
        double entityBottomWorldY = entity.worldY + entity.solidArea.getY() + entity.solidArea.getHeight();

        double entityLeftCol = entityLeftWorldX / 48;
        double entityRightCol = entityRightWorldX / 48;
        double entityTopRow = entityTopWorldY / 48;
        double entityBottomRow = entityBottomWorldY / 48;

        int tileNum1;
        int tileNum2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / 48;
                tileNum1 = gp.tileManager.mapTileNum[(int) entityLeftCol][(int) entityTopRow];
                tileNum2 = gp.tileManager.mapTileNum[(int) entityRightCol][(int) entityTopRow];
                if (gp.tileManager.tiles[tileNum1].collision || gp.tileManager.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / 48;
                tileNum1 = gp.tileManager.mapTileNum[(int) entityLeftCol][(int) entityBottomRow];
                tileNum2 = gp.tileManager.mapTileNum[(int) entityRightCol][(int) entityBottomRow];
                if (gp.tileManager.tiles[tileNum1].collision || gp.tileManager.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / 48;
                tileNum1 = gp.tileManager.mapTileNum[(int) entityLeftCol][(int) entityTopRow];
                tileNum2 = gp.tileManager.mapTileNum[(int) entityLeftCol][(int) entityBottomRow];
                if (gp.tileManager.tiles[tileNum1].collision || gp.tileManager.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / 48;
                tileNum1 = gp.tileManager.mapTileNum[(int) entityRightCol][(int) entityTopRow];
                tileNum2 = gp.tileManager.mapTileNum[(int) entityRightCol][(int) entityBottomRow];
                if (gp.tileManager.tiles[tileNum1].collision || gp.tileManager.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
        }
    }

    public int checkObject(Entity entity, boolean player) {
        int index = 999;

        for (int i = 0; i < gp.obj.length; ++i) {
            if (gp.obj[i] != null) {
                entity.solidArea.setX(entity.solidArea.getX() + entity.worldX);
                entity.solidArea.setY(entity.solidArea.getY() + entity.worldY);
                gp.obj[i].solidArea.setX(gp.obj[i].solidArea.getX() + gp.obj[i].worldX);
                gp.obj[i].solidArea.setY(gp.obj[i].solidArea.getY() + gp.obj[i].worldY);

                Rectangle var10000;

                switch (entity.direction) {
                    case "up":
                        var10000 = entity.solidArea;
                        var10000.setY(entity.solidArea.getY() - entity.speed);
                        if (entity.solidArea.intersects(gp.obj[i].solidArea.getBoundsInLocal())) {
                            if (gp.obj[i].collision) {
                                entity.collisionOn = true;
                            }

                            if (player) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        var10000 = entity.solidArea;
                        var10000.setY(entity.solidArea.getY() + entity.speed);
                        if (entity.solidArea.intersects(gp.obj[i].solidArea.getBoundsInLocal())) {
                            if (gp.obj[i].collision) {
                                entity.collisionOn = true;
                            }

                            if (player) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        var10000 = entity.solidArea;
                        var10000.setX(entity.solidArea.getX() - entity.speed);
                        if (entity.solidArea.intersects(gp.obj[i].solidArea.getBoundsInLocal())) {
                            if (gp.obj[i].collision) {
                                entity.collisionOn = true;
                            }

                            if (player) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        var10000 = entity.solidArea;
                        var10000.setX(entity.solidArea.getX() + entity.speed);
                        if (entity.solidArea.intersects(gp.obj[i].solidArea.getBoundsInLocal())) {
                            if (gp.obj[i].collision) {
                                entity.collisionOn = true;
                            }

                            if (player) {
                                index = i;
                            }
                        }
                        break;
                }

                entity.solidArea.setX(entity.solidAreaDefaultX);
                entity.solidArea.setY(entity.solidAreaDefaultY);
                gp.obj[i].solidArea.setX(gp.obj[i].solidAreaDefaultX);
                gp.obj[i].solidArea.setY(gp.obj[i].solidAreaDefaultY);
            }
        }

        return index;
    }
}
