package com.example.stage2_jeu2d.entity;

import com.example.stage2_jeu2d.GamePanel;
import com.example.stage2_jeu2d.KeyHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class Player extends Entity {
    private KeyHandler kh;
    public double screenX;
    public double screenY;

    GamePanel gamePanel;
    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        super(gamePanel);
        this.kh = keyHandler;
        this.gamePanel=gamePanel;

        screenX = gamePanel.screenWidth / 2 - (gamePanel.tileSize / 2);
        screenY = gamePanel.screenHeight / 2 - (gamePanel.tileSize / 2);

        solidArea = new Rectangle();
        solidArea.setX(8);
        solidArea.setY(16);
        solidAreaDefaultX = (int) solidArea.getX();
        solidAreaDefaultY = (int) solidArea.getY();
        solidArea.setWidth(32);
        solidArea.setHeight(32);

        attackArea.setWidth(36);
        attackArea.setHeight(36);

        setDefaultValue();
        getPlayerImages();
        getPlayerAttackImage();
    }

    public void setDefaultValue() {
        worldX = this.gamePanel.tileSize * 23;
        worldY = this.gamePanel.tileSize * 22;
        speed = 4;
        direction = "down";

        maxLife = 6;
        life = maxLife;
    }

    public void getPlayerImages() {
        up1 = setup("/player/boy_up_1", gamePanel.tileSize, gamePanel.tileSize);
        up2 = setup("/player/boy_up_2", gamePanel.tileSize, gamePanel.tileSize);
        down1 = setup("/player/boy_down_1", gamePanel.tileSize, gamePanel.tileSize);
        down2 = setup("/player/boy_down_2", gamePanel.tileSize, gamePanel.tileSize);
        left1 = setup("/player/boy_left_1", gamePanel.tileSize, gamePanel.tileSize);
        left2 = setup("/player/boy_left_2", gamePanel.tileSize, gamePanel.tileSize);
        right1 = setup("/player/boy_right_1", gamePanel.tileSize, gamePanel.tileSize);
        right2 = setup("/player/boy_right_2", gamePanel.tileSize, gamePanel.tileSize);
    }

    public void getPlayerAttackImage() {
        attackUp1 = setup("/player/boy_attack_up_1", gamePanel.tileSize, gamePanel.tileSize);
        attackUp2 = setup("/player/boy_attack_up_2", gamePanel.tileSize, gamePanel.tileSize);
        attackDown1 = setup("/player/boy_attack_down_1", gamePanel.tileSize, gamePanel.tileSize);
        attackDown2 = setup("/player/boy_attack_down_2", gamePanel.tileSize, gamePanel.tileSize);
        attackLeft1 = setup("/player/boy_attack_left_1", gamePanel.tileSize, gamePanel.tileSize);
        attackLeft2 = setup("/player/boy_attack_left_2", gamePanel.tileSize, gamePanel.tileSize);
        attackRight1 = setup("/player/boy_attack_right_1", gamePanel.tileSize, gamePanel.tileSize);
        attackRight2 = setup("/player/boy_attack_right_2", gamePanel.tileSize, gamePanel.tileSize);
    }

    public void update() {
        if (attackKing) {
            attackKing();
        } else if (kh.leftPressed || kh.downPressed || kh.upPressed || kh.rightPressed || kh.enterPressed) {
            if (kh.upPressed) {
                direction = "up";
            } else if (kh.downPressed) {
                direction = "down";
            } else if (kh.leftPressed) {
                direction = "left";
            } else if (kh.rightPressed) {
                direction = "right";
            }

            collisionOn = false;
            gamePanel.cChecker.checkTile(this);

            int objIndex = gamePanel.cChecker.checkObject(this, true);
            pickupObject(objIndex);

            int npcIndex = gamePanel.cChecker.checkEntity(this, gamePanel.npc);
            interactNPC(npcIndex);

            int monsterIndex = gamePanel.cChecker.checkEntity(this, gamePanel.monster);
            contactMonster(monsterIndex);

            gamePanel.eHandler.checkEvent();

            if (!collisionOn && !kh.enterPressed) {
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
                }
            }

            kh.enterPressed = false;
            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                } else spriteNum = 0;
            }
        }
        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void pickupObject(int i) {
        if (i != 999) {

        }
    }

    public void attackKing() {
        System.out.println("hiiiiii");
        spriteCounter++;
        if (spriteCounter <= 5) {
            spriteNum = 1;
        }
        if (spriteCounter > 5 && spriteCounter <= 25) {
            spriteNum = 2;
            double currentWorldX = worldX;
            double currentWorldY = worldY;
            double solidAreaWidth = solidArea.getWidth();
            double solidAreaHeight = solidArea.getHeight();
            switch (direction) {
                case "up":
                    worldY -= attackArea.getHeight();
                    break;
                case "down":
                    worldY += attackArea.getHeight();
                    break;
                case "left":
                    worldX -= attackArea.getWidth();
                    break;
                case "right":
                    worldX += attackArea.getWidth();
                    break;
            }
            solidArea.setWidth(attackArea.getWidth());
            solidArea.setHeight(attackArea.getHeight());
            int monsterIndex = gamePanel.cChecker.checkEntity(this, gamePanel.monster);
            damageMonster(monsterIndex);
            worldX = (int) currentWorldX;
            worldY = (int) currentWorldY;
            solidArea.setWidth(solidAreaWidth);
            solidArea.setHeight(solidAreaHeight);
        }
        if (spriteCounter > 25) {
            spriteNum = 1;
            spriteCounter = 0;
            attackKing = false;
        }
    }

    public void interactNPC(int i) {
        if (gamePanel.keyHandler.enterPressed) {
            if (i != 999) {
                gamePanel.gameState = gamePanel.dialogueState;
                gamePanel.npc[i].speak();
            } else {
                attackKing = true;
            }
        }
    }

    public void contactMonster(int i) {
        if (i != 999) {
            if (!invincible) {
                life -= 1;
                invincible = true;
            }
        }
    }

    public void damageMonster(int i) {
        if (i != 999) {
            if (!gamePanel.monster[i].invincible) {
                gamePanel.monster[i].life -= 1;
                gamePanel.monster[i].invincible = true;
                if (gamePanel.monster[i].life <= 0) {
                    gamePanel.monster[i] = null;
                }
            }
        }
    }

    public void draw(GraphicsContext g) {
        Image image = null;
        double tempScreenX = screenX;
        double tempScreenY = screenY;
        switch (direction) {
            case "up":
                if (!attackKing) {
                    if (spriteNum == 1) {
                        image = up1;
                    }
                    if (spriteNum == 2) {
                        image = up2;
                    }
                }
                if (attackKing) {
                    if (spriteNum == 1) {
                        image = attackUp1;
                    }
                    if (spriteNum == 2) {
                        image = attackUp2;
                    }
                }
                break;
            case "down":
                if (!attackKing) {
                    if (spriteNum == 1) {
                        image = down1;
                    }
                    if (spriteNum == 2) {
                        image = down2;
                    }
                }
                if (attackKing) {
                    if (spriteNum == 1) {
                        image = attackDown1;
                    }
                    if (spriteNum == 2) {
                        image = attackDown2;
                    }
                }
                break;
            case "left":
                if (!attackKing) {
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                }
                if (attackKing) {
                    if (spriteNum == 1) {
                        image = attackLeft1;
                    }
                    if (spriteNum == 2) {
                        image = attackLeft2;
                    }
                }
                break;
            case "right":
                if (!attackKing) {
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                }
                if (attackKing) {
                    if (spriteNum == 1) {
                        image = attackRight1;
                    }
                    if (spriteNum == 2) {
                        image = attackRight2;
                    }
                }
                break;
        }
        if (invincible) {
            g.setGlobalAlpha(0.3);
        }
        g.drawImage(image, tempScreenX, tempScreenY);
        g.setGlobalAlpha(1.0);
    }
}
