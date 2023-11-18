package object

public class SuperObject{
    public BuffereImage image;
    public String name;
    public boolean collision=false;
    public int worldX,worldY;
    public int solidAreaDefaultX=0;
    public int solidAreaDefaultY=0;
    public void draw(Graphic2D g2 ,GamePanel gp){
        int screenX=WorldX- gp.player.WorldX+gp.player.screenX;
        int screenY=WorldY- gp.player.WorldY+gp.player.screenY;
        if( WorldX +gp.tileSize > gp.player.WordX- gp.player.screenX &&
                WorldX - gp.tileSize<gp.player.WordX+gp.player.screenX &&
                WorldY +gp.tileSize> gp.player.WordY- gp.player.screenY &&
                WorldY - gp.tileSize<gp.player.WordY+gp.player.screenY){
            g.drawImage(image, screenX, screenY, 48, 48, null);
        }

    }
}