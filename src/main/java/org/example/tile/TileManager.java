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
        mapTileNum = new int[12][16];
        getTileImages();
        readMapFile("/maps/map1.txt");
    }
    public void getTileImages(){
        try {
            tiles[0]=new Tile();
            tiles[0].image= ImageIO.read(getClass().getResourceAsStream("/tiles/grass00.png"));
            tiles[1]=new Tile();
            tiles[1].image= ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
            tiles[2]=new Tile();
            tiles[2].image= ImageIO.read(getClass().getResourceAsStream("/tiles/water00.png"));
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
        int col, row, x, y;
        col=row=x=y=0;
        while (col<16 && row<12){
            g.drawImage(tiles[mapTileNum[row][col]].image, x, y, 48, 48, null);
            col++;
            x+=48;
            if(col==16){
                col=0;
                x=0;
                row++;
                y+=48;
            }
        }
    }
}
