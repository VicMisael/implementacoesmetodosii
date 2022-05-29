//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
package methods;

import java.util.function.Function;

public final class GaussLegendre {
    private final Function<Float, Float> function;

    private GaussLegendre(Function<Float, Float> function) {
        this.function=function;
    }

    public static GaussLegendre build( Function<Float, Float> function){
        return new GaussLegendre(function);
    }

    private  float pointx(float x1, float xf, float s) {
        return (x1 + xf) / 2.0F + s * (xf - x1) / 2.0F;
    }

    private  float integrate(float xi, float xf, float[] roots, float[] weights) {
        float result = 0.0F;

        for(int x = 0; x < roots.length; ++x) {
            result += (Float)function.apply(pointx(xi, xf, roots[x])) * weights[x];
        }

        return result;
    }

    public float twopoints(float xi, float xf) {
        float s = (float)Math.sqrt(0.3333333432674408D);
        float[] roots = new float[]{-s, s};
        float[] weights = new float[]{1.0F, 1.0F};
        return (xf - xi) / 2.0F * integrate(xf, xf, roots, weights);
    }

    public float threepoints(float xi, float xf) {
        float s = (float)Math.sqrt(0.6000000238418579D);
        float[] roots = new float[]{-s, 0.0F, s};
        float[] weights = new float[]{0.5555556F, 0.8888889F, 0.5555556F};
        return (xf - xi) / 2.0F * integrate(xi, xf, roots, weights);
    }

    public float fourpoints(float xi, float xf) {
        float[] roots = new float[]{-0.861136F, -0.339981F, 0.339981F, 0.861136F};
        float[] weights = new float[]{0.347854F, 0.652145F, 0.652145F, 0.347854F};
        return (xf - xi) / 2.0F * integrate(xi, xf, roots, weights);
    }
}
