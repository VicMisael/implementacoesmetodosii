package tarefa5;

import java.util.function.Function;

public final class GaussLegendre {

    final Function<Float, Float> function = aFloat -> (float) (Math.pow(aFloat, 4) + Math.sin(aFloat));

    private float pointx(float x1, float xf, float s) {
        return ((x1 + xf) / 2) + ((xf - x1) / 2);
    }

    public float integrate(float xi, float xf, float roots[], float weights[]) {

        float result = 0;

        for (int x = 0; x < roots.length; x++) result += function.apply(pointx(xi, xf, x)) * weights[x++];

        return result;

    }

    public float twopoints(float xi, float xf) {
        final float s = (float) Math.sqrt(1f / 3f);
        float roots[] = {-s, s};
        float weights[] = {1, 1};
        return integrate(xf, xf, roots, weights);
    }

    public float threepoints(float xi, float xf) {
        final float s = (float) Math.sqrt(3f / 5f);
        float roots[] = {-s, 0, s};
        float weights[] = {5 / 9, 8 / 9, 5 / 9};
        return integrate(xi, xf, roots, weights);
    }

    public float fourpoints(float xi, float xf) {
        float roots[] = {-0.861136f, -0.339981f, 0.339981f, 0.861136f};
        float weights[] = {0.347854f, 0.652145f, 0.652145f, 0.347854f};
        return integrate(xi, xf, roots, weights);
    }
}
