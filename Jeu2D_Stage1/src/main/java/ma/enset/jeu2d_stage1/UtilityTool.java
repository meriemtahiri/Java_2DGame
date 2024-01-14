package ma.enset.jeu2d_stage1;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

public class UtilityTool {

    public static Image scaleImage(Image original, int width, int height) {
        if (original != null) {
            System.out.println("Original image is not null.");

            // Check if the image is fully loaded
            if (original.isBackgroundLoading()) {
                original.progressProperty().addListener((obs, oldVal, newVal) -> {
                    if (newVal.doubleValue() >= 1.0) {
                        System.out.println("Image is fully loaded.");
                    }
                });
            } else {
                System.out.println("Image is not background loading.");
            }

            // Check for null PixelReader
            PixelReader pixelReader = original.getPixelReader();
            if (pixelReader == null) {
                System.err.println("PixelReader of the original image is null. Image dimensions: "
                        + original.getWidth() + "x" + original.getHeight());
                return original; // Return the original image in case of issues
            }

            WritableImage scaledImage = new WritableImage(width, height);
            PixelWriter pixelWriter = scaledImage.getPixelWriter();

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    double sourceX = x * (original.getWidth() / (double) width);
                    double sourceY = y * (original.getHeight() / (double) height);
                    pixelWriter.setArgb(x, y, pixelReader.getArgb((int) sourceX, (int) sourceY));
                }
            }

            return scaledImage;
        } else {
            System.err.println("Original image is null.");
            return null; // Return null if the original image is null
        }
    }
}
