package com.example.stage2_jeu2d;

import com.example.stage2_jeu2d.entity.Entity;
import com.example.stage2_jeu2d.entity.Player;
import com.example.stage2_jeu2d.tile.TileManager;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Comparator;

public class GamePanel extends Pane {
    //SCREEN SETTINGS
    final int originalTileSize = 16;  //16*16 tile
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;  //48*48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;//16*48 pixels
    public final int screenHeight = tileSize * maxScreenRow;//12*48 pixels

    //WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    //SYSTEM
    public TileManager tileManager = new TileManager(this);
    public KeyHandler keyHandler = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);

    Thread gameThread; //when we start gameThread it's automatically calls the run method

    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter asetter = new AssetSetter(this);

    //ENTITY AND OBJECT
    public Player player = new Player(this, keyHandler);
    public Entity[] obj = new Entity[10];
    public Entity[] npc = new Entity[10];

    // Using a List instead of an Array for dynamic sizing
    java.util.List<Entity> entityList = new ArrayList<>();

    public Entity monster[] = new Entity[20];

    //GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;

    private Canvas canvas;
    private GraphicsContext gc;

    public GamePanel() {
        this.setPrefSize(screenWidth, screenHeight);
        this.setStyle("-fx-background-color: black;");
        this.setFocusTraversable(true);

        canvas = new Canvas(screenWidth, screenHeight);
        gc= canvas.getGraphicsContext2D();
        this.getChildren().add(canvas);


        this.setOnKeyPressed(keyHandler::handle);
      //  this.setOnKeyReleased(keyHandler::handleReleased);
    }

    public void setupGame() {
        asetter.setObject();
        asetter.setNPC();
        asetter.setMonster();
        playMusic(0);
        gameState = titleState;
    }

    public void startGameThread() {
        gameThread = new Thread(this::run);
        gameThread.start(); //automatically call the run method
    }

    public void run() {
        double drawInterval = 1000000000.0 / 60; // we can draw the screen 60 times per second (each 0.01666 seconds)
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += currentTime - lastTime;
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                render();
                delta--;
                drawCount++;
            }
            if (timer >= 1000000000) {
                System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        if (gameState == playState) {
            player.update();
            for (int i = 0; i < npc.length; i++)
                if (npc[i] != null) npc[i].update();

            for (int i = 0; i < monster.length; i++)
                if (monster[i] != null) monster[i].update();
        }
        if (gameState == pauseState) {
            //nothing
        }
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // DEBUG
        long drawStart = 0;

        if (keyHandler.checkDrawTime) {
            drawStart = System.nanoTime();
        }

        //TITLE SCREEN
        if (gameState == titleState) {
            ui.draw(gc);
        }
        //OTHERS
        else {
            //TILE
            tileManager.draw(gc);

            //ADD ENTITIES TO THE LIST
            entityList.add(player);

            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) entityList.add(npc[i]);
            }
            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null) entityList.add(obj[i]);
            }
            for (int i = 0; i < monster.length; i++) {
                if (monster[i] != null) entityList.add(monster[i]);
            }
            // SORT
            entityList.sort(Comparator.comparingInt(entity -> entity.worldY));

            //DRAW ENTITIES
            for (Entity entity : entityList) {
                entity.draw(gc);
            }

            //EMPTY ENTITY LIST
            entityList.clear();

            //UI
            ui.draw(gc);
        }
        //TILE
        if (keyHandler.checkDrawTime) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            gc.setFill(Color.WHITE);
            gc.fillText("Draw Time: " + passed, 10, 400);
            System.out.println("Draw Time: " + passed);
        }
    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSE(int i) {  //sound effect
        se.setFile(i);
        se.play();
    }
}
