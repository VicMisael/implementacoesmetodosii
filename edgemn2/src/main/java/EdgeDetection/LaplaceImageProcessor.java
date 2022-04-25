package EdgeDetection;

public class LaplaceImageProcessor {

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
        laplace(pixels, A, width,height);
        return A;

    }

    private static void laplace(int[][] input, float[][] output, int width, int height) {
        //First and second derivativeX
        float[][] tempinput = new float[width][height];
        float[][] tempx = new float[width][height];
        float[][] outputx = new float[width][height];
        float[][] tempy = new float[width][height];
        float[][] outputy = new float[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                tempinput[i][j] = input[i][j];
            }
        }
        derivativeX(tempinput, tempx, width, height);
        derivativeX(tempx, outputx, width, height);
        derivativeY(tempinput, tempy, width, height);
        derivativeY(tempy, outputy, width, height);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                output[i][j] = outputx[i][j] + outputy[i][j];
            }
        }

    }

    private static void derivativeX(float[][] pixels, float[][] A, int width, int height) {

        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                float value;
                if (w == 0) {
                    float[] values = getForwardGrayscaleX(pixels, 0, h);
                    value = Math.abs(values[0] - values[1]);
                } else if ((width - 1) == w) {
                    float values[] = getBackwardGrayscaleX(pixels, width - 1, h);
                    value = Math.abs(values[0] - values[1]);
                } else {
                    float values[] = getCentralGrayscaleX(pixels, w, h);
                    value = Math.abs(values[0] - values[1]) / 2;
                }
                A[w][h] = value;
            }
        }
    }

    private static void derivativeY(float[][] pixels, float[][] B, int width, int height) {

        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                float value;
                if (h == 0) {
                    float values[] = getForwardGrayscaleY(pixels, w, 0);
                    value = Math.abs(values[0] - values[1]);
                } else if ((height - 1) == h) {
                    float values[] = getBackwardGrayscaleY(pixels, w, height - 1);
                    value = Math.abs(values[0] - values[1]);
                } else {
                    float values[] = getCentralGrayscaleY(pixels, w, h);
                    value = Math.abs(values[0] - values[1]) / 2;
                }
                B[w][h] = value;
            }
        }
    }

    private static float[] getBackwardGrayscaleY(float pixels[][], int x, int y) {
        float[] values = new float[2];
        values[0] = pixels[x][y - 1];
        values[1] = pixels[x][y];
        return values;
    }


    private static float[] getForwardGrayscaleY(float pixels[][], int x, int y) {
        float[] values = new float[2];
        values[0] = pixels[x][y];
        values[1] = pixels[x][y + 1];
        return values;
    }

    private static float[] getCentralGrayscaleY(float pixels[][], int x, int y) {
        float[] values = new float[2];
        values[0] = pixels[x][y - 1];
        values[1] = pixels[x][y + 1];
        return values;
    }


    private static float[] getBackwardGrayscaleX(float pixels[][], int x, int y) {
        float[] values = new float[2];
        values[0] = pixels[x - 1][y];
        values[1] = pixels[x][y];
        return values;
    }

    private static float[] getForwardGrayscaleX(float pixels[][], int x, int y) {
        float[] values = new float[2];
        values[0] = pixels[x][y];
        values[1] = pixels[x + 1][y];
        return values;
    }

    private static float[] getCentralGrayscaleX(float pixels[][], int x, int y) {
        float[] values = new float[2];
        values[0] = pixels[x - 1][y];
        values[1] = pixels[x + 1][y];
        return values;
    }
}
