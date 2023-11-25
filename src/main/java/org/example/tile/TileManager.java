package org.example.tile;

import org.example.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class TileManager {
    GamePanel gamePanel;
    public Tile[] tiles;
    public int mapTileNum[][];

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tiles = new Tile[10];
        mapTileNum = new int[gamePanel.maxWorldRow][gamePanel.maxWorldCol];
        getTileImages();
        readMapFile("/maps/world01.txt");
    }

    public void getTileImages(){
        try {
            tiles[0]=new Tile();
            tiles[0].image= ImageIO.read(getClass().getResourceAsStream("/tiles/grass00.png"));
            tiles[1]=new Tile();
            tiles[1].image= ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
            tiles[1].collision=true;
            tiles[2]=new Tile();
            tiles[2].image= ImageIO.read(getClass().getResourceAsStream("/tiles/water00.png"));
            tiles[2].collision=true;
            tiles[3]=new Tile();
            tiles[3].image= ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));
            tiles[4]=new Tile();
            tiles[4].image= ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
            tiles[4].collision=true;
            tiles[5]=new Tile();
            tiles[5].image= ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void readMapFile(String path){
        try {
            InputStream is = getClass().getResourceAsStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
                for (int i=0 ; i< gamePanel.maxWorldCol ; i++){
                        String[] num = br.readLine().split(" ");
                        for (int j=0; j<gamePanel.maxWorldRow ; j++)
                            mapTileNum[i][j]= Integer.parseInt(num[j]);
                }
                br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void draw(Graphics2D g){
        int worldCol=0;
        int worldRow=0;


        while (worldCol< gamePanel.maxWorldCol  && worldRow<gamePanel.maxWorldRow){
           int tileNum = mapTileNum[worldCol][worldRow];
           int worldX = worldCol* gamePanel.tileSize;
           int worldY = worldRow* gamePanel.tileSize;
           int screenX= worldX - gamePanel.player.worldX+ gamePanel.player.screenX;
           int screenY= worldY - gamePanel.player.worldY+ gamePanel.player.screenY;
           if(worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
              worldX - gamePanel.tileSize < gamePanel.player.worldX+ gamePanel.player.screenX &&
              worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
              worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY
             ){
               g.drawImage(tiles[tileNum].image,screenX,screenY,gamePanel.tileSize, gamePanel.screenHeight, null);
              }

           worldCol++;

           if(worldCol== gamePanel.maxWorldCol){
               worldCol =0;
               worldRow++;

           }


        }
    }
}
