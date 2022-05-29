package methods;

import java.util.function.Function;

import static java.lang.Math.abs;

public class NewtonCotes {

    private final Function<Float, Float> function;

    private NewtonCotes(Function<Float, Float> function) {
        this.function = function;
    }

    public static NewtonCotes build(Function<Float, Float> function) {
        return new NewtonCotes(function);
    }

    public float integration(float x, float y, float epslon) {
        float delta;
        float lastResult;
        float result = 0;
        //        int count = 0;
        int N = 2;
        while (true) {
            //  count += 1;
            delta = (y - x) / N;
            float integral = 0;
            for (int i = 0; i < N; i++) {
                float xi = x + i * delta;
                float xf = xi + delta;
                integral += fourPointsOpen(xi, xf);
            }
            N = N * 2;
            lastResult = result;
            result = integral;
            float error = abs(lastResult - result);
            if (error < epslon)
                break;
            System.out.println("O erro atual é " + error);
        }

        return result;
    }

    private float fourPointsOpen(float xi, float xf) {
        float h = (xf - xi) / 6f;
        return ((6 * h) / 20f) * (
                11f * function.apply(xi + h)
                        - 14f * function.apply(xi + 2 * h)
                        + 26f * function.apply(xi + 3 * h)
                        - 14f * function.apply(xi + 4 * h)
                        + 11f * function.apply(xi + 5 * h));
    }
}
