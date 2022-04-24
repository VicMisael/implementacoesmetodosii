package EdgeDetection;


public class ImageProcessor {
    public static final boolean GRAYSCALE = true;
    public static final boolean COLORED = false;

    public static float[][] processImage(ImageForProcessing image, boolean useGrayscale) {
        final int width = image.width;
        final int height = image.height;

        final int[][] pixels;
        if (useGrayscale) {
            pixels = image.pixelsGrayScale;
        } else {
            pixels = image.pixels;
        }
        float[][] A = new float[width][height];
        float[][] B = new float[width][height];
        float[][] C = new float[width][height];
        derivativeX(pixels, A, image);
        derivativeY(pixels, B, image);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                C[i][j] = (float) Math.sqrt(A[i][j] * A[i][j] + B[i][j] * B[i][j]);
            }
        }
        return C;

    }

    private static void derivativeX(int[][] pixels, float[][] A, ImageForProcessing image) {


        for (int w = 0; w < image.width; w++) {
            for (int h = 0; h < image.height; h++) {
                int value;
                if (w == 0) {
                    int[] values = getForwardGrayscaleX(pixels, 0, h);
                    value = Math.abs(values[0] - values[1]);
                } else if ((image.width - 1) == w) {
                    int values[] = getBackwardGrayscaleX(pixels, image.width - 1, h);
                    value = Math.abs(values[0] - values[1]);
                } else {
                    int values[] = getCentralGrayscaleX(pixels, w, h);
                    value = Math.abs(values[0] - values[1]) / 2;
                }
                A[w][h] = value;
            }
        }
    }

    private static void derivativeY(int[][] pixels, float[][] B, ImageForProcessing image) {

        for (int w = 0; w < image.width; w++) {
            for (int h = 0; h < image.height; h++) {
                int value;
                if (h == 0) {
                    int values[] = getForwardGrayscaleY(pixels, w, 0);
                    value = Math.abs(values[0] - values[1]);
                } else if ((image.height - 1) == h) {
                    int values[] = getBackwardGrayscaleY(pixels, w, image.height - 1);
                    value = Math.abs(values[0] - values[1]);
                } else {
                    int values[] = getCentralGrayscaleY(pixels, w, h);
                    value = Math.abs(values[0] - values[1]) / 2;
                }
                B[w][h] = value;
            }
        }
    }

    private static int[] getBackwardGrayscaleY(int pixels[][], int x, int y) {
        int[] values = new int[2];
        values[0] = pixels[x][y - 1];
        values[1] = pixels[x][y];
        return values;
    }


    private static int[] getForwardGrayscaleY(int pixels[][], int x, int y) {
        int[] values = new int[2];
        values[0] = pixels[x][y];
        values[1] = pixels[x][y + 1];
        return values;
    }

    private static int[] getCentralGrayscaleY(int pixels[][], int x, int y) {
        int[] values = new int[2];
        values[0] = pixels[x][y - 1];
        values[1] = pixels[x][y + 1];
        return values;
    }


    private static int[] getBackwardGrayscaleX(int pixels[][], int x, int y) {
        int[] values = new int[2];
        values[0] = pixels[x - 1][y];
        values[1] = pixels[x][y];
        return values;
    }

    private static int[] getForwardGrayscaleX(int pixels[][], int x, int y) {
        int[] values = new int[2];
        values[0] = pixels[x][y];
        values[1] = pixels[x + 1][y];
        return values;
    }

    private static int[] getCentralGrayscaleX(int pixels[][], int x, int y) {
        int[] values = new int[2];
        values[0] = pixels[x - 1][y];
        values[1] = pixels[x + 1][y];
        return values;
    }

}
