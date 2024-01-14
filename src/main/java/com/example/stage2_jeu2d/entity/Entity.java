package com.example.stage2_jeu2d.entity;

import com.example.stage2_jeu2d.GamePanel;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.io.InputStream;

public class Entity {

    protected GamePanel gamePanel;
    public Image image;
    public Image image2;
    public Image image3;
    public int worldX, worldY, speed;
    public Image up1, up2, down1, down2, left1, left2, right1, right2;
    public Image attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
    public String direction = "down";
    public int spriteNum = 1;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;

    public boolean invincible = false;

    String dialogues[] = new String[20];

    public String name;
    public boolean collision = false;
    public boolean attackKing = false;
    public int type;

    public int spriteCounter = 0;
    public int actionLookCont = 0;
    public int invincibleCounter = 0;
    int dialogueIndex = 0;

    public int maxLife;
    public int life;

    public Entity(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setAction() {
    }

    public void speak() {
        if (dialogues[dialogueIndex] == null) {
            dialogueIndex = 0;
        }
        gamePanel.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;
        switch (gamePanel.player.direction) {
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
    }

    public void update() {
        setAction();
        collisionOn = false;
        gamePanel.cChecker.checkTile(this);
        gamePanel.cChecker.checkObject(this, false);
        gamePanel.cChecker.checkEntity(this, gamePanel.npc);
        gamePanel.cChecker.checkEntity(this, gamePanel.monster);
        boolean contactPlayer = gamePanel.cChecker.checkPlayer(this);

        if (this.type == 2 && contactPlayer) {
            if (!gamePanel.player.invincible) {
                gamePanel.player.life -= 1;
                gamePanel.player.invincible = true;
            }
        }

        if (!collisionOn) {
            switch (direction) {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + direction);
            }
        }

        spriteCounter++;
        if (spriteCounter > 12) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            } else spriteNum = 0;
        }
        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 40) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void draw(GraphicsContext graphicsContext) {
        Image image = null;

        double screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
        double screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;
        if (worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
                worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
                worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
                worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY) {
            switch (direction) {
                case "up":
                    if (spriteNum == 1) {
                        image = up1;
                    }
                    if (spriteNum == 2) {
                        image = up2;
                    }
                    break;
                case "down":
                    if (spriteNum == 1) {
                        image = down1;
                    }
                    if (spriteNum == 2) {
                        image = down2;
                    }
                    break;
                case "left":
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                    break;
                case "right":
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                    break;
            }
            if (invincible) {
                graphicsContext.setGlobalAlpha(0.4);
            }
            graphicsContext.drawImage(image, screenX, screenY, 48, 48);
            graphicsContext.setGlobalAlpha(1.0);
        }
    }

    public Image setup(String imagePath, int width, int height) {
        InputStream inputStream = getClass().getResourceAsStream(imagePath + ".png");
        return new Image(inputStream, width, height, false, false);
    }
}
