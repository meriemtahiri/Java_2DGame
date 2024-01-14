package ma.enset.jeu2d_stage1;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import ma.enset.jeu2d_stage1.entity.Player;
import ma.enset.jeu2d_stage1.object.SuperObject;
import ma.enset.jeu2d_stage1.tile.TileManager;


public class GamePanel extends StackPane {

    // SCREEN SETTINGS
    final int originalTileSize = 16;  // 16*16 tile
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;  // 48*48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 16*48 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 12*48 pixels

    // WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    // SYSTEM
    public TileManager tileManager = new TileManager(this);
    KeyHandler keyHandler = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();

    public UI ui = new UI(this);
    private final Canvas canvas;

    private final GraphicsContext g2;


    private boolean running = false;

    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter asetter = new AssetSetter(this);

    // ENTITY AND OBJECT
    public Player player = new Player(this, keyHandler);
    public SuperObject[] obj = new SuperObject[10];

    public GamePanel() {
        this.setPrefSize(screenWidth, screenHeight);
        this.setStyle("-fx-background-color: black;");
        this.setFocusTraversable(true);

        canvas = new Canvas(screenWidth, screenHeight);
        g2 = canvas.getGraphicsContext2D();
        this.getChildren().add(canvas);


        this.setOnKeyPressed(keyHandler::handleKeyPress);
        this.setOnKeyReleased(keyHandler::handleKeyRelease);

        initialize();
    }

    private void initialize() {

    }

    public void setupGame() {
        playMusic(0);
        asetter.setObject();

    }

    public void startGameThread() {
        running = true;
        new AnimationTimer() {
            long lastTime = System.nanoTime();
            final double amountOfTicks = 60.0;
            double ns = 1000000000.0 / amountOfTicks;
            double delta = 0;

            @Override
            public void handle(long now) {
                delta += (now - lastTime) / ns;
                lastTime = now;

                while (delta >= 1) {
                    update();
                    delta--;
                }

                render();
            }
        }.start();
    }

    private void update() {
        player.update();
    }

    private void render() {
        g2.clearRect(0, 0, getWidth(), getHeight());

        // Render game components
        tileManager.draw(g2);
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }
        player.draw(g2);

        // Render UI
        ui.draw(g2);
    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSE(int i) {
        se.setFile(i);
        se.play();
    }



    public void stopGameThread() {
        running=false;
        stopMusic();

    }
}
