package ma.enset.jeu2d_stage1.object;

import javafx.scene.image.Image;
import ma.enset.jeu2d_stage1.GamePanel;
import ma.enset.jeu2d_stage1.UtilityTool;

import java.io.InputStream;

public class OBJ_key extends SuperObject {
    GamePanel gp;

    public OBJ_key(GamePanel gp) {
        this.gp = gp;
        name = "key";
        try {
            InputStream is = getClass().getResourceAsStream("/ma/enset/objects/key.png");
            image = new Image(is);
            image = UtilityTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
