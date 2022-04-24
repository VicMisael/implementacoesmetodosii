package EdgeDetection;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageForProcessing {
    int[][] pixels;
    int[][] pixelsGrayScale;
    final private BufferedImage grayScale;
    public final int width;
    public final int height;

    public ImageForProcessing(BufferedImage image) throws IOException {
        width = image.getWidth();
        height = image.getHeight();
        pixels = new int[width][height];
        pixelsGrayScale = new int[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color pixelColor = new Color(image.getRGB(i, j));
                pixels[i][j] = pixelColor.getRGB();
            }
        }
        grayScale = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        Graphics g = grayScale.getGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color pixelColor = new Color(grayScale.getRGB(i, j));
                pixelsGrayScale[i][j] = pixelColor.getRed();
            }
        }
    }

    public BufferedImage getGrayscale() {
        return grayScale;
    }





}
