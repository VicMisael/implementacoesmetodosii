package tarefa5;

import java.util.function.Function;

public final class GaussLegendre {

    private static final Function<Float, Float> function = x -> (float) Math.pow(Math.sin(2 * x) + (4f * Math.pow(x, 2)) + 3f * x, 2);

    private static float pointx(float x1, float xf, float s) {
        return ((x1 + xf) / 2f) + (s * (xf - x1) / 2f);
    }

    private static float integrate(float xi, float xf, float roots[], float weights[]) {

        float result = 0;

        for (int x = 0; x < roots.length; x++) result += (function.apply(pointx(xi, xf, roots[x]))) * weights[x];

        return result;

    }

    public static float twopoints(float xi, float xf) {
        final float s = (float) Math.sqrt(1f / 3f);
        float roots[] = {-s, s};
        float weights[] = {1, 1};
        return ((xf - xi) / 2f) * integrate(xf, xf, roots, weights);
    }

    public static float threepoints(float xi, float xf) {
        final float s = (float) Math.sqrt(3f / 5f);
        float roots[] = {-s, 0, s};
        float weights[] = {5f / 9, 8f / 9, 5f / 9};
        return ((xf - xi) / 2f) * integrate(xi, xf, roots, weights);
    }

    public static float fourpoints(float xi, float xf) {
        float roots[] = {-0.861136f, -0.339981f, 0.339981f, 0.861136f};
        float weights[] = {0.347854f, 0.652145f, 0.652145f, 0.347854f};
        return ((xf - xi) / 2f) * integrate(xi, xf, roots, weights);
    }
}
