package ma.enset.jeu2d_stage1.object;

import javafx.scene.image.Image;
import ma.enset.jeu2d_stage1.GamePanel;
import ma.enset.jeu2d_stage1.UtilityTool;

import java.io.InputStream;

public class OBJ_Door extends SuperObject {
    GamePanel gp;

    public OBJ_Door(GamePanel gp) {
        this.gp = gp;
        name = "Door";

        try (InputStream is = getClass().getResourceAsStream("/ma/enset/objects/door.png")) {
            if (is != null) {
                image = new Image(is);
                image = UtilityTool.scaleImage(image, gp.tileSize, gp.tileSize);
                collision = true;
            } else {
                System.err.println("Error loading image: InputStream is null");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}