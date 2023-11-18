package org.example;

import org.example.entity.Player;
import org.example.tile.TileManager;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    final int screenWidth = 768;
    final int screenHeight = 576;
    final int maxWorldCol=50;
    final int maxWorldRow=50;
    final int WorldWidth=tileSize* maxWorldCol;
    final int WorldHeight=tileSize*maxWorldRow;
    TileManager tileManager=new TileManager(this);
    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    public CollisionChecker cChecker=new CollisionChecker(this);
    public AssetSetter asetter=new assetSetter(this);
    public Player player = new Player(this,keyHandler);
    public SuperObject obj[]=new SuperObject[10];
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

    }
    public void setupGame(){
        asetter.setObject();
    }
    public void startGameTread(){
        gameThread = new Thread(this);
        gameThread.start();
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
        double drawInterval = 1000000000.0 / 60;
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
                obj[i].draw(g2,this)
            }
        }
        player.draw(g2);
        g2.dispose();
    }
}
