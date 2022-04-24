import EdgeDetection.ImageForProcessing;
import EdgeDetection.ImageProcessor;
import com.jhlabs.image.GaussianFilter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageProcessing {

    public static void main(String args[]) {

        try {
            BufferedImage original = ImageIO.read(ImageProcessing.class.getResource("original.png"));
            BufferedImage filtered = new BufferedImage(original.getWidth(), original.getHeight(), BufferedImage.TYPE_INT_RGB);
            new GaussianFilter(1).filter(original, filtered);
            ImageForProcessing image = new ImageForProcessing(filtered);
            final float[][] result = ImageProcessor.processImage(image, ImageProcessor.GRAYSCALE);
            final float threshold = 0.001f;
            int D[][] = new int[image.width][image.height];
            BufferedImage outputImage = new BufferedImage(image.width, image.height, BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < image.width; i++) {
                for (int j = 0; j < image.height; j++) {
                    D[i][j] = result[i][j] < threshold ? Color.black.getRGB() : Color.white.getRGB();
                    outputImage.setRGB(i, j, D[i][j]);
                }
            }
            System.out.println("processado");
            save(filtered, "process_filtered");
            save(outputImage, "output_processed");
            save(image.getGrayscale(), "output_grayscale");
        } catch (Exception except) {
            except.printStackTrace();
        }
    }

    private static void save(BufferedImage image, String name) throws IOException {
        File outputEdgeDetection = new File(name + ".png");
        ImageIO.write(image, "png", outputEdgeDetection);
    }
}
