package org.example.tile;

import org.example.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class TileManager {
    GamePanel gamePanel;
    Tile[] tiles;
    int mapTileNum[][];

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tiles = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
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
                for (int i=0 ; i<12 ; i++){
                        String[] num = br.readLine().split(" ");
                        for (int j=0; j<16 ; j++)
                            mapTileNum[i][j]= Integer.parseInt(num[j]);
                }
                br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public void draw(Graphics2D g){
        int col, row;
        WorldCol=WorldRow=0;
        while (WorldCol<gp.maxWordCol  && maxWordRow<gp.maxWordRow){
            int WorldX=WorldCol +gp.tileSize;
            int WorldY=WorldRow +gp.tileSize;
            int screenX=WorldX- gp.player.WorldX+gp.player.screenX;
            int screenY=WorldY- gp.player.WorldY+gp.player.screenY;
            if( WorldX +gp.tileSize > gp.player.WordX- gp.player.screenX &&
                WorldX - gp.tileSize<gp.player.WordX+gp.player.screenX &&
                WorldY +gp.tileSize> gp.player.WordY- gp.player.screenY &&
                WorldY - gp.tileSize<gp.player.WordY+gp.player.screenY){
                g.drawImage(tiles[mapTileNum[WorldRow][WorldCol]].image, screenX, screenY, 48, 48, null);
            }

            WorldCol++;
            if(WorldCol==16){
                WorldCol=0;

                WorldRow++;
                y+=48;
            }
        }
    }
}
