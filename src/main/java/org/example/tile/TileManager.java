package org.example.tile;

import org.example.GamePanel;
import org.example.UtilityTool;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
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

            setup(0,"grass01",false);
            setup(1,"wall",true);
            setup(2,"water00",true);
            setup(3,"earth",false);
            setup(4,"tree",true);
            setup(5,"sand",false);

    }

    public void setup(int index, String imagName, boolean collision ){

        UtilityTool uTool = new UtilityTool();
        try {
            tiles[index] = new Tile();
            tiles[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+imagName+".png"));
            tiles[index].image = uTool.scaleImage(tiles[index].image, gamePanel.tileSize, gamePanel.tileSize);
            tiles[index].collision=collision;

        }catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void readMapFile(String path){
        try {
            InputStream is = getClass().getResourceAsStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col=0;
            int row=0;

                while (col< gamePanel.maxWorldCol  && row < gamePanel.maxWorldRow){
                        String line = br.readLine();
                        while (col <gamePanel.maxWorldRow){
                            String numbers[]=line.split(" ");
                            int num= Integer.parseInt(numbers[col]);
                            mapTileNum[col][row]= num;
                            col++;
                        }
                        if(col == gamePanel.maxWorldCol){
                            col =0;
                            row++;
                        }
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
           int worldX = worldCol * gamePanel.tileSize;
           int worldY = worldRow * gamePanel.tileSize;
           int screenX= worldX - gamePanel.player.worldX+ gamePanel.player.screenX;
           int screenY= worldY - gamePanel.player.worldY+ gamePanel.player.screenY;
           if(worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
              worldX - gamePanel.tileSize < gamePanel.player.worldX+ gamePanel.player.screenX &&
              worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
              worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY
             ){
               g.drawImage(tiles[tileNum].image,screenX,screenY, null);
              }

           worldCol++;

           if(worldCol== gamePanel.maxWorldCol){
               worldCol =0;
               worldRow++;

           }


        }
    }
}
