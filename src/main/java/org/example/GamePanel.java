package org.example;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel{
    final int screenWidth = 768;
    final int screenHeight = 576;
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
    }

}
