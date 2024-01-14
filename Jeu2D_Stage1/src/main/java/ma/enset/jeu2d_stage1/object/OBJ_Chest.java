package ma.enset.jeu2d_stage1.object;

import javafx.scene.image.Image;
import ma.enset.jeu2d_stage1.GamePanel;
import ma.enset.jeu2d_stage1.UtilityTool;

import java.io.InputStream;

public class OBJ_Chest extends SuperObject {
    GamePanel gp;

    public OBJ_Chest(GamePanel gp) {
        this.gp = gp;
        name = "Chest";
        try {
            InputStream is = getClass().getResourceAsStream("/ma/enset/objects/chest.png");
            image = new Image(is);
            image = UtilityTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
