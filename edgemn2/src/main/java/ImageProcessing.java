import EdgeDetection.ImageForProcessing;
import EdgeDetection.LaplaceImageProcessor;
import EdgeDetection.SobelImageProcessor;
import com.jhlabs.image.GaussianFilter;
import org.apache.commons.io.FilenameUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

public class ImageProcessing {

    public static void main(String args[]) {


        Arrays.asList("gato_calvo.jpg", "img.png", "original.png", "sociedade2.jpg", "globorural.jpg").stream().filter(ImageProcessing::imageExtensionFilter).forEach(filename -> {
            try {
                System.out.println(filename);
                URL imagePath = ImageProcessing.class.getResource(filename);
                BufferedImage original = ImageIO.read(imagePath);
                String imagename = FilenameUtils.getBaseName(imagePath.toString());

                BufferedImage filtered = new BufferedImage(original.getWidth(), original.getHeight(), BufferedImage.TYPE_INT_RGB);
                new GaussianFilter(2).filter(original, filtered);
                ImageForProcessing image = new ImageForProcessing(filtered);
                final float[][] resultSobel = SobelImageProcessor.processImage(image, SobelImageProcessor.GRAYSCALE);
                final float[][] resultLaplace = LaplaceImageProcessor.processImage(image, LaplaceImageProcessor.GRAYSCALE);

                final float thresholdSobel = 0.0001f;
                BufferedImage outputImageSobel = new BufferedImage(image.width, image.height, BufferedImage.TYPE_INT_RGB);
                for (int i = 0; i < image.width; i++) {
                    for (int j = 0; j < image.height; j++) {
                        outputImageSobel.setRGB(i, j, resultSobel[i][j] < thresholdSobel ? Color.black.getRGB() : Color.white.getRGB());
                    }
                }

                BufferedImage outputImageLaplace = new BufferedImage(image.width, image.height, BufferedImage.TYPE_INT_RGB);

                float thresholdLaplace = 0.0001f;
                for (int i = 0; i < image.width; i++) {
                    for (int j = 0; j < image.height; j++) {
                        outputImageLaplace.setRGB(i, j, resultLaplace[i][j] < thresholdLaplace ? Color.black.getRGB() : Color.white.getRGB());
                    }
                }

                save(filtered, imagename + "_filtered");
                save(image.getGrayscale(), imagename + "_grayscale");
                save(outputImageSobel, imagename + "_sobel");
                save(outputImageLaplace, imagename + "_laplace");
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        });
    }

    private static boolean imageExtensionFilter(String value) {

        switch (FilenameUtils.getExtension(value)) {
            case "png":
                return true;
            case "jpg":
                return true;
            case "jpeg":
                return true;
            default:
                return false;
        }
    }

    private static void save(BufferedImage image, String name) throws IOException {
        File outputEdgeDetection = new File(name + ".png");
        ImageIO.write(image, "png", outputEdgeDetection);
    }
}
