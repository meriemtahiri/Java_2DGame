package org.example;

import org.example.entity.Entity;
import org.example.entity.Player;
import org.example.tile.TileManager;

import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable{

    //SCREEN SETTINGS
    final int originalTileSize = 16;  //16*16 tile
    final int scale =3;
    public final int tileSize=originalTileSize*scale;  //48*48 tile
    public final int maxScreenCol=16;
    public final int maxScreenRow=12;
    public final int screenWidth=tileSize* maxScreenCol;//16*48 pixels
    public final int screenHeight=tileSize*maxScreenRow;//12*48 pixels

    //WORLD SETTINGS
    public final int maxWorldCol=50;
    public final int maxWorldRow=50;
    public final int worldWidth  = tileSize*maxWorldCol;
    public final int worldHeight  = tileSize*maxWorldRow;


    //SYSTEM
    public TileManager tileManager=new TileManager(this);
    public KeyHandler keyHandler = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public UI ui= new UI(this);
    public EventHandler eHandler =new EventHandler(this);
    Thread gameThread; //when we start gameThread it's automaticaly calls the run method

    public CollisionChecker cChecker=new CollisionChecker(this);
    public AssetSetter asetter=new AssetSetter(this);

    //ENTITY AND OBJECT
    public Player player = new Player(this,keyHandler);
    public Entity[] obj =new Entity[10];
    public Entity[] npc = new Entity[10];

    ArrayList<Entity> entityList = new ArrayList<>();

    //GAME STATE
    public int gameState;
    public final int titleState=0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);//enabling this can improve game's rendering performance
        this.addKeyListener(keyHandler);
        this.setFocusable(true);//with this, this GamePanel can be "focused" to receive key input

    }

    public void setupGame(){
        asetter.setObject();
        asetter.setNPC();
        playMusic(0);
        gameState=titleState;
    }

    public void startGameTread(){  //for starting the game thread
        gameThread = new Thread(this);
        gameThread.start(); //automatically call the run method
    }

    @Override
    /*public void run() {
        double drawInterval = 1000000000.0/60;
        double nextDrawTime = System.nanoTime() + drawInterval;
        while (gameThread != null) {
            update();
            repaint(); // that's how we can call the paintComponent methode!!
            try {
                double remainingTime = (nextDrawTime - System.nanoTime())/1000000.0 ;
                if(remainingTime<0) remainingTime=0;
                Thread.sleep((long)remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }*/
    public void run() {
        double drawInterval = 1000000000.0/60; // we can draw screen 60 time by second (each 0.01666 seconds)
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount =0;
        while (gameThread != null) {
            currentTime=System.nanoTime();
            delta+=(currentTime-lastTime)/drawInterval;
            timer+=currentTime-lastTime;
            lastTime=currentTime;
            if(delta>=1) {
                update();
                repaint(); // that's how we can call the paintComponent methode!!
                delta--;
                drawCount++;
            }
            if(timer>=1000000000){
                System.out.println("FPS:" + drawCount);
                drawCount=0;
                timer=0;
            }
        }
    }

    public void update(){
        if(gameState==playState){
            player.update();
            for (int i=0 ; i<npc.length ; i++)
                if(npc[i]!=null) npc[i].update();
        }
        if(gameState==pauseState){
            //nothing
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        // DEBUG
        long drawStart =0;


        if(keyHandler.checkDrawTime == true){
            drawStart = System.nanoTime();
        }
        //TITLE SCREEN
        if (gameState==titleState){
                       ui.draw(g2);
        }
        //OTHERS
        else {
            //TILE
            tileManager.draw(g2);

            //ADD ENTITIES TO THE LIST
            entityList.add(player);

            for(int i=0;i<npc.length; i++){
                if(npc[i] != null){
                    entityList.add(npc[i]);
                }
            }
            for(int i= 0; i <obj.length; i++ ){
                if(obj[i] != null){
                    entityList.add(obj[i]);
                }
            }

            // SORT
            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {
                    int result= Integer.compare(e1.worldY,e2.worldY);
                    return result;
                }
            });

            //DRAW ENTITIES
            for(int i=0; i<entityList.size(); i++){
                entityList.get(i).draw(g2);
            }

            //EMPTY ENTITY LIST

            for(int i=0; i<entityList.size(); i++){
                entityList.remove(i);
            }


            //UI
            ui.draw(g2);
        }
        //TILE

        if(keyHandler.checkDrawTime == true){
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.white);
            g2.drawString("Draw Time: " + passed,10,400);
            System.out.println("Draw Time: " + passed);
        }

        g2.dispose();
    }


public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();

}

public void stopMusic(){
       music.stop();
}

public void playSE(int i){  //sound effect
      se.setFile(i);
      se.play();
}






}
