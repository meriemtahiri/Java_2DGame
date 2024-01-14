package com.example.stage2_jeu2d;

import com.example.stage2_jeu2d.entity.Entity;
import javafx.scene.shape.Rectangle;

public class CollisionChecker {
    private final GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = (int) (entity.worldX + entity.solidArea.getX());
        int entityRightWorldX = (int) (entity.worldX + entity.solidArea.getX() + entity.solidArea.getWidth());
        int entityTopWorldY = (int) (entity.worldY + entity.solidArea.getY());
        int entityBottomWorldY = (int) (entity.worldY + entity.solidArea.getY() + entity.solidArea.getHeight());

        int entityLeftCol = (int) (entityLeftWorldX / gp.tileSize);
        int entityRightCol = (int) (entityRightWorldX / gp.tileSize);
        int entityTopRow = (int) (entityTopWorldY / gp.tileSize);
        int entityBottomRow = (int) (entityBottomWorldY / gp.tileSize);

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (int) ((entityTopWorldY - entity.speed) / gp.tileSize);
                tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityTopRow];
                if (gp.tileManager.tiles[tileNum1].collision || gp.tileManager.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }

                break;
            case "down":
                entityBottomRow = (int) ((entityBottomWorldY + entity.speed) / gp.tileSize);
                tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileManager.tiles[tileNum1].collision || gp.tileManager.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (int) ((entityLeftWorldX - entity.speed) / gp.tileSize);
                tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                if (gp.tileManager.tiles[tileNum1].collision || gp.tileManager.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (int) ((entityRightWorldX + entity.speed) / gp.tileSize);
                tileNum1 = gp.tileManager.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileManager.tiles[tileNum1].collision || gp.tileManager.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
        }
    }

    public int checkObject(Entity entity, boolean player) {
        int index = 999;
        for (int i = 0; i < gp.obj.length; i++) {
            if (gp.obj[i] != null) {
                Rectangle entitySolidArea = new Rectangle(entity.solidArea.getX(), entity.solidArea.getY(),
                        entity.solidArea.getWidth(), entity.solidArea.getHeight());
                entitySolidArea.setX(entity.worldX + entitySolidArea.getX());
                entitySolidArea.setY(entity.worldY + entitySolidArea.getY());

                Rectangle objSolidArea = new Rectangle(gp.obj[i].solidArea.getX(), gp.obj[i].solidArea.getY(),
                        gp.obj[i].solidArea.getWidth(), gp.obj[i].solidArea.getHeight());
                objSolidArea.setX(gp.obj[i].worldX + objSolidArea.getX());
                objSolidArea.setY(gp.obj[i].worldY + objSolidArea.getY());

                switch (entity.direction) {
                    case "up":
                        entitySolidArea.setY(entitySolidArea.getY() - entity.speed);
                        break;
                    case "down":
                        entitySolidArea.setY(entitySolidArea.getY() + entity.speed);
                        break;
                    case "left":
                        entitySolidArea.setX(entitySolidArea.getX() - entity.speed);
                        break;
                    case "right":
                        entitySolidArea.setX(entitySolidArea.getX() + entity.speed);
                        break;
                }
                if (entitySolidArea.intersects(objSolidArea.getBoundsInLocal())) {
                    if (gp.obj[i].collision) {
                        entity.collisionOn = true;
                    }
                    if (player) {
                        index = i;
                    }
                }
            }
        }
        return index;
    }

    public int checkEntity(Entity entity, Entity[] target) {
        int index = 999;
        for (int i = 0; i < target.length; i++) {
            if (target[i] != null) {
                Rectangle entitySolidArea = new Rectangle(entity.solidArea.getX(), entity.solidArea.getY(),
                        entity.solidArea.getWidth(), entity.solidArea.getHeight());
                entitySolidArea.setX(entity.worldX + entitySolidArea.getX());
                entitySolidArea.setY(entity.worldY + entitySolidArea.getY());

                Rectangle targetSolidArea = new Rectangle(target[i].solidArea.getX(), target[i].solidArea.getY(),
                        target[i].solidArea.getWidth(), target[i].solidArea.getHeight());
                targetSolidArea.setX(target[i].worldX + targetSolidArea.getX());
                targetSolidArea.setY(target[i].worldY + targetSolidArea.getY());

                switch (entity.direction) {
                    case "up":
                        entitySolidArea.setY(entitySolidArea.getY() - entity.speed);
                        break;
                    case "down":
                        entitySolidArea.setY(entitySolidArea.getY() + entity.speed);
                        break;
                    case "left":
                        entitySolidArea.setX(entitySolidArea.getX() - entity.speed);
                        break;
                    case "right":
                        entitySolidArea.setX(entitySolidArea.getX() + entity.speed);
                        break;
                }
                if (entitySolidArea.intersects(targetSolidArea.getBoundsInLocal())) {
                    if (target[i] != entity) {
                        entity.collisionOn = true;
                        index = i;
                    }
                }
            }
        }
        return index;
    }

    public boolean checkPlayer(Entity entity) {
        boolean contactPlayer = false;
        Rectangle entitySolidArea = new Rectangle(entity.solidArea.getX(), entity.solidArea.getY(),
                entity.solidArea.getWidth(), entity.solidArea.getHeight());
        entitySolidArea.setX(entity.worldX + entitySolidArea.getX());
        entitySolidArea.setY(entity.worldY + entitySolidArea.getY());

        Rectangle playerSolidArea = new Rectangle(gp.player.solidArea.getX(), gp.player.solidArea.getY(),
                gp.player.solidArea.getWidth(), gp.player.solidArea.getHeight());
        playerSolidArea.setX(gp.player.worldX + playerSolidArea.getX());
        playerSolidArea.setY(gp.player.worldY + playerSolidArea.getY());

        switch (entity.direction) {
            case "up":
                entitySolidArea.setY(entitySolidArea.getY() - entity.speed);
                break;
            case "down":
                entitySolidArea.setY(entitySolidArea.getY() + entity.speed);
                break;
            case "left":
                entitySolidArea.setX(entitySolidArea.getX() - entity.speed);
                break;
            case "right":
                entitySolidArea.setX(entitySolidArea.getX() + entity.speed);
                break;
        }
        if (entitySolidArea.intersects(playerSolidArea.getBoundsInLocal())) {
            entity.collisionOn = true;
            contactPlayer = true;
        }
        return contactPlayer;
    }
}
