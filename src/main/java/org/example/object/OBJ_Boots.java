package org.example.object;

import org.example.GamePanel;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Boots extends SuperObject{

    GamePanel gp;
    public OBJ_Boots( GamePanel gp) {
        this.gp=gp;

        name="Boots";
        try {
            image= ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
            uTool.scaleImage(image,gp.tileSize,gp.tileSize);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
