package com.example.stage2_jeu2d;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class UtilityTool {

    public Image scaleImage(Image original, int width, int height) {
        WritableImage scaledImage = new WritableImage(width, height);
        PixelReader reader = original.getPixelReader();
        PixelWriter writer = scaledImage.getPixelWriter();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                double sourceX = (double) x / width * original.getWidth();
                double sourceY = (double) y / height * original.getHeight();
                Color color = reader.getColor((int) sourceX, (int) sourceY);
                writer.setColor(x, y, color);
            }
        }

        return scaledImage;
    }
}
