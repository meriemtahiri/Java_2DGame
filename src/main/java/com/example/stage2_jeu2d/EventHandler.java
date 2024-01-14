package com.example.stage2_jeu2d;

public class EventHandler {
    GamePanel gp;
    EventRect[][] eventRect;

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;

    public EventHandler(GamePanel gp) {
        this.gp = gp;
        eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];
        int col = 0;
        int row = 0;
        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
            eventRect[col][row] = new EventRect();
            eventRect[col][row].setX(23);
            eventRect[col][row].setY(23);
            eventRect[col][row].setWidth(2);
            eventRect[col][row].setHeight(2);
            eventRect[col][row].eventRectDefaultX = (int) eventRect[col][row].getX();
            eventRect[col][row].eventRectDefaultY = (int) eventRect[col][row].getY();
            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;
            }
        }
    }

    public void checkEvent() {
        // CHECK IF THE PLAYER CHARACTER IS MORE THAN 1 TILE AWAY FROM THE LAST EVENT
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if (distance > gp.tileSize) {
            canTouchEvent = true;
        }
        if (canTouchEvent == true) {
            if (hit(27, 16, "right") == true) {
                damagePit(27, 16, gp.dialogueState);
            }
            if (hit(23, 19, "any") == true) {
                damagePit(27, 16, gp.dialogueState);
            }

            if (hit(23, 12, "up") == true) {
                healingPool(23, 12, gp.dialogueState);
            }
        }
    }

    public boolean hit(int col, int row, String regDirection) {
        boolean hit = false;

        gp.player.solidArea.setX(gp.player.worldX + gp.player.solidArea.getX());
        gp.player.solidArea.setY(gp.player.worldY + gp.player.solidArea.getY());
        eventRect[col][row].setX(col * gp.tileSize + eventRect[col][row].getX());
        eventRect[col][row].setY(row * gp.tileSize + eventRect[col][row].getY());

        if (gp.player.solidArea.getBoundsInParent().intersects(eventRect[col][row].getBoundsInParent())
                && !eventRect[col][row].eventDone) {
            if (gp.player.direction.equals(regDirection) || regDirection.equals("any")) {
                hit = true;
                previousEventX = gp.player.worldX;
                previousEventY = gp.player.worldY;
            }
        }
        gp.player.solidArea.setX(gp.player.solidAreaDefaultX);
        gp.player.solidArea.setY(gp.player.solidAreaDefaultY);
        eventRect[col][row].setX(eventRect[col][row].eventRectDefaultX);
        eventRect[col][row].setY(eventRect[col][row].eventRectDefaultY);

        return hit;
    }

    public void damagePit(int col, int row, int gameState) {
        gp.gameState = gameState;
        gp.ui.currentDialogue = "You fall into a Pit!";
        gp.player.life -= 1;
        // eventRect[col][row].eventDone=true;
        canTouchEvent = false;
    }

    public void healingPool(int col, int row, int gameState) {
        if (gp.keyHandler.enterPressed == true) {
            gp.gameState = gameState;
            gp.ui.currentDialogue = "You drink the water.\n Your life has been recovered.";
            gp.player.life = gp.player.maxLife;
        }
        gp.keyHandler.enterPressed = false;
    }
}
