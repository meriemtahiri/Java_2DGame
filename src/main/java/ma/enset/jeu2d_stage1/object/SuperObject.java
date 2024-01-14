package ma.enset.jeu2d_stage1.object;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import ma.enset.jeu2d_stage1.GamePanel;


public class SuperObject {
    public Image image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);


    public void draw(GraphicsContext g, GamePanel gp) {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
            g.drawImage(image, screenX, screenY, 48, 48);
        }
    }
}
