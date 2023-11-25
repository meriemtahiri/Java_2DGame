package org.example;

import org.example.entity.Player;
import org.example.object.SuperObject;
import org.example.tile.TileManager;

import javax.swing.JPanel;
import java.awt.*;

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



    public TileManager tileManager=new TileManager(this);
    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread; //when we start gameThread it's automaticaly calls the run method

    public CollisionChecker cChecker=new CollisionChecker(this);
    public Player player = new Player(this,keyHandler);
    public SuperObject obj[]=new SuperObject[10];
    public AssetSetter asetter=new AssetSetter(this);

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);//enabling this can improve game's rendering performance
        this.addKeyListener(keyHandler);
        this.setFocusable(true);//with this, this GamePanel can be "focused" to receive key input

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
        while (gameThread != null) {
            currentTime=System.nanoTime();
            delta+=(currentTime-lastTime)/drawInterval;
            timer+=currentTime-lastTime;
            lastTime=currentTime;
            if(delta>=1) {
                update();
                repaint(); // that's how we can call the paintComponent methode!!
                delta--;
            }
            if(timer>=1000000000) timer=0;
        }
    }

    public void update(){
        player.update();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        tileManager.draw(g2);
        for(int i=0;i<obj.length;i++){
            if(obj[i]!=null){
               obj[i].draw(g2,this);
           }
         }
        tileManager.draw(g2);
        player.draw(g2);
        g2.dispose();
    }





    public void setupGame(){
        asetter.setObject();
    }



}
