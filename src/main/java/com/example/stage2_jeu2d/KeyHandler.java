package com.example.stage2_jeu2d;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyHandler implements EventHandler<KeyEvent> {
    GamePanel gamePanel;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
    // DEBUG
    boolean checkDrawTime = false;

    public KeyHandler(GamePanel gp) {
        this.gamePanel = gp;
    }

    @Override
    public void handle(KeyEvent event) {
        KeyCode code = event.getCode();
        // TITLE STATE

        if (gamePanel.gameState == gamePanel.titleState) {
            if (gamePanel.ui.titleScreenState == 0) {
                if (code == KeyCode.UP) {
                    gamePanel.ui.commandNum--;
                    if (gamePanel.ui.commandNum < 0) {
                        gamePanel.ui.commandNum = 2;
                    }
                }
                if (code == KeyCode.DOWN) {
                    gamePanel.ui.commandNum++;
                    if (gamePanel.ui.commandNum > 2) {
                        gamePanel.ui.commandNum = 0;
                    }
                }
                if (code == KeyCode.ENTER) {
                    if (gamePanel.ui.commandNum == 0) {
                        gamePanel.ui.titleScreenState = 1;
                    }
                    if (gamePanel.ui.commandNum == 1) {
                        // Handle LOAD GAME
                    }
                    if (gamePanel.ui.commandNum == 2) {
                        System.exit(0);
                    }
                }
            } else if (gamePanel.ui.titleScreenState == 1) {
                if (code == KeyCode.UP) {
                    gamePanel.ui.commandNum--;
                    if (gamePanel.ui.commandNum < 0) {
                        gamePanel.ui.commandNum = 3;
                    }
                }
                if (code == KeyCode.DOWN) {
                    gamePanel.ui.commandNum++;
                    if (gamePanel.ui.commandNum > 3) {
                        gamePanel.ui.commandNum = 0;
                    }
                }
                if (code == KeyCode.ENTER) {
                    if (gamePanel.ui.commandNum == 0) {
                        System.out.println("Do some fighter specific stuff!");
                        gamePanel.gameState = gamePanel.playState;
                        // gamePanel.playMusic(0);
                    }
                    if (gamePanel.ui.commandNum == 1) {
                        System.out.println("Do some thief specific stuff!");
                        gamePanel.gameState = gamePanel.playState;
                        // gamePanel.playMusic(0);
                    }
                    if (gamePanel.ui.commandNum == 2) {
                        System.out.println("Do some sorcerer specific stuff!");
                        gamePanel.gameState = gamePanel.playState;
                        // gamePanel.playMusic(0);
                    }
                    if (gamePanel.ui.commandNum == 3) {
                        gamePanel.ui.titleScreenState = 0;
                    }
                }
            }
        }

        // PLAY STATE
        if (gamePanel.gameState == gamePanel.playState) {
            if (code == KeyCode.UP) upPressed = true;
            if (code == KeyCode.DOWN) downPressed = true;
            if (code == KeyCode.LEFT) leftPressed = true;
            if (code == KeyCode.RIGHT) rightPressed = true;
            if (code == KeyCode.SPACE) gamePanel.gameState = gamePanel.pauseState;
            if (code == KeyCode.ENTER) {
                enterPressed = true;
            }


            if (gamePanel.player.attackKing) {
                gamePanel.player.attackKing();
            } else if (this.leftPressed || this.downPressed || this.upPressed || this.rightPressed || this.enterPressed) {
                if (this.upPressed) {
                    gamePanel.player.direction = "up";
                } else if (this.downPressed) {
                    gamePanel.player.direction = "down";
                } else if (this.leftPressed) {
                    gamePanel.player.direction = "left";
                } else if (this.rightPressed) {
                    gamePanel.player.direction = "right";
                }

                gamePanel.player.collisionOn = false;
                gamePanel.cChecker.checkTile(gamePanel.player);

                int objIndex = gamePanel.cChecker.checkObject(gamePanel.player, true);
                gamePanel.player.pickupObject(objIndex);

                int npcIndex = gamePanel.cChecker.checkEntity(gamePanel.player, gamePanel.npc);
                if (this.enterPressed) {
                    if (npcIndex != 999) {
                        gamePanel.gameState = gamePanel.dialogueState;
                        gamePanel.npc[npcIndex].speak();
                    } else {
                        gamePanel.player.attackKing = true;
                    }
                }

                int monsterIndex = gamePanel.cChecker.checkEntity(gamePanel.player, gamePanel.monster);
                gamePanel.player.contactMonster(monsterIndex);

                gamePanel.eHandler.checkEvent();

                if (gamePanel.eHandler.hit(23, 12, "up") == true) {
                    if (enterPressed == true) {
                        gamePanel.gameState = gamePanel.dialogueState;
                        gamePanel.ui.currentDialogue = "You drink the water.\n Your life has been recovered.";
                        gamePanel.player.life = gamePanel.player.maxLife;
                    }
                    enterPressed = false;
                }

                if (!gamePanel.player.collisionOn && !this.enterPressed) {
                    switch (gamePanel.player.direction) {
                        case "up":
                            gamePanel.player.worldY -= gamePanel.player.speed;
                            break;
                        case "down":
                            gamePanel.player.worldY += gamePanel.player.speed;
                            break;
                        case "left":
                            gamePanel.player.worldX -= gamePanel.player.speed;
                            break;
                        case "right":
                            gamePanel.player.worldX += gamePanel.player.speed;
                            break;
                    }
                }

                gamePanel.keyHandler.enterPressed = false;
                gamePanel.player.spriteCounter++;
                if (gamePanel.player.spriteCounter > 12) {
                    if (gamePanel.player.spriteNum == 1) {
                        gamePanel.player.spriteNum = 2;
                    } else if (gamePanel.player.spriteNum == 2) {
                        gamePanel.player.spriteNum = 1;
                    } else gamePanel.player.spriteNum = 0;
                }
            }
            if (gamePanel.player.invincible) {
                gamePanel.player.invincibleCounter++;
                if (gamePanel.player.invincibleCounter > 60) {
                    gamePanel.player.invincible = false;
                    gamePanel.player.invincibleCounter = 0;
                }
            }




        }

        // PAUSE STATE
        else if (gamePanel.gameState == gamePanel.pauseState) {
            if (code == KeyCode.SPACE) gamePanel.gameState = gamePanel.playState;
        }

        // DIALOGUE STATE
        else if (gamePanel.gameState == gamePanel.dialogueState) {
            if (code == KeyCode.ENTER) gamePanel.gameState = gamePanel.playState;
        }
    }

    public void keyReleased(KeyEvent event) {
        if (event.getEventType() == KeyEvent.KEY_RELEASED) {
            switch (event.getCode()) {
                case UP:
                    upPressed = false;
                    break;
                case DOWN:
                    downPressed = false;
                    break;
                case LEFT:
                    leftPressed = false;
                    break;
                case RIGHT:
                    rightPressed = false;
                    break;
                case ENTER:
                    enterPressed=false;
            }
        }}


}
