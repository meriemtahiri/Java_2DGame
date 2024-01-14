package ma.enset.jeu2d_stage1;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import ma.enset.jeu2d_stage1.object.OBJ_key;
import java.text.DecimalFormat;


public class UI {
    private final GamePanel gp;
    private final Font arial_40;
    private final Font arial_80B;
    private final Image keyImage;

    private boolean messageOn = false;
    private String message = "";

    private int messageCounter = 0;
    public boolean gameFinished = false;

    private double playTime;
    private final DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = Font.font("Arial", FontWeight.NORMAL, 40);
        arial_80B = Font.font("Arial", FontWeight.BOLD, 80);
        OBJ_key key=new OBJ_key(gp);
        keyImage = key.image;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(GraphicsContext g) {
        if (gameFinished) {
            g.setFont(arial_40);
            g.setFill(Color.WHITE);
            String text;
            double x, y;

            text = "You found the treasure!";
            Text textNode = new Text(text);
            textNode.setFont(arial_40);
            x = (gp.screenWidth - textNode.getLayoutBounds().getWidth()) / 2;
            y = gp.screenHeight / 2 - (gp.tileSize * 3);
            g.fillText(text, x, y);

            text = "Your Time is: " + dFormat.format(playTime) + "!";
            textNode = new Text(text);
            textNode.setFont(arial_40);
            x = (gp.screenWidth - textNode.getLayoutBounds().getWidth()) / 2;
            y = gp.screenHeight / 2 + (gp.tileSize * 4);
            g.fillText(text, x, y);

            g.setFont(arial_80B);
            g.setFill(Color.YELLOW);

            text = "Congratulations!";
            textNode = new Text(text);
            textNode.setFont(arial_80B);
            x = (gp.screenWidth - textNode.getLayoutBounds().getWidth()) / 2;
            y = gp.screenHeight / 2 + (gp.tileSize * 2);
            g.fillText(text, x, y);

            gp.stopGameThread();

        } else {
            g.setFont(arial_40);
            g.setFill(Color.WHITE);
            g.drawImage(keyImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize);
            g.fillText("x " + gp.player.hasKey, 74, 65);

            // TIME
            playTime += (double) 1 / 60;
            g.fillText("Time:" + dFormat.format(playTime), gp.tileSize * 11, 65);

            // MESSAGE
            if (messageOn) {
                g.setFont(Font.font(g.getFont().getFamily(), FontWeight.NORMAL, 30));
                g.fillText(message, gp.tileSize / 2, gp.tileSize * 5);

                messageCounter++;

                if (messageCounter > 120) {  // Texte disparait après 2 secondes
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
    }
}

