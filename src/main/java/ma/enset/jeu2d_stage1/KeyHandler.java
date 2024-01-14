package ma.enset.jeu2d_stage1;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyHandler {
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    // DEBUG
    boolean checkDrawTime = false;
    GamePanel gp;
    public KeyHandler(GamePanel gp) {
        this.gp=gp;}
    public void handleKeyPress(KeyEvent event) {
        KeyCode code = event.getCode();
        if (code == KeyCode.UP) {
            this.upPressed = true;
        }
        if (code == KeyCode.DOWN) downPressed = true;
        if (code == KeyCode.LEFT) leftPressed = true;
        if (code == KeyCode.RIGHT) rightPressed = true;
        // DEBUG
        if (code == KeyCode.T) {checkDrawTime = !checkDrawTime;}
        if (leftPressed || downPressed || upPressed || rightPressed) {
            if (upPressed) {
                gp.player.direction = "up";
            } else if (downPressed) {
                gp.player.direction = "down";
            } else if (leftPressed) {
                gp.player.direction = "left";
            } else if (rightPressed) {
                gp.player.direction = "right";
            }

            // CHECK TILE COLLISION
            gp.player.collisionOn = false;
            gp.cChecker.checkTile(gp.player);

            // CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(gp.player, true);
            gp.player.pickupObject(objIndex);

            if (!gp.player.collisionOn) {
                System.out.println(gp.player.collisionOn);
                switch (gp.player.direction) {
                    case "up":
                        gp.player.worldY -= gp.player.speed;
                        break;
                    case "down":
                        gp.player.worldY += gp.player.speed;
                        break;
                    case "left":
                        gp.player.worldX -= gp.player.speed;
                        break;
                    case "right":
                        gp.player.worldX += gp.player.speed;
                        break;
                }

            }

            gp.player.spriteCounter++;
            if (gp.player.spriteCounter > 12) {
                if (gp.player.spriteNum == 1) {
                    gp.player.spriteNum = 2;
                } else if (gp.player.spriteNum == 2) {
                    gp.player.spriteNum = 1;
                } else gp.player.spriteNum = 0;
            }
        }
    }

    public void handleKeyRelease(KeyEvent event) {
        KeyCode code = event.getCode();
        if (code == KeyCode.UP) upPressed = false;
        if (code == KeyCode.DOWN) downPressed = false;
        if (code == KeyCode.LEFT) leftPressed = false;
        if (code == KeyCode.RIGHT) rightPressed = false;

    }
}
