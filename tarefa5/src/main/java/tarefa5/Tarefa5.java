package tarefa5;

import java.util.function.BiFunction;

public class Tarefa5 {
    public static void main(String args[]) {

        System.out.println("Gauss legendre");

        float xi = 0;
        float xf = 1;
        System.out.println("2 pontos");
        iterate(xi, xf, GaussLegendre::twopoints);
        System.out.println("3 pontos");
        iterate(xi, xf, GaussLegendre::threepoints);
        System.out.println("4 pontos");
        iterate(xi, xf, GaussLegendre::fourpoints);
    }


    private static void iterate(float xi, float xf, BiFunction<Float, Float, Float> function) {

        float error = Float.MAX_VALUE;
        float epsilon = 0.00001f;
        int iterations = 1;
        float nv = Float.MAX_VALUE;
        int itcount = 0;
        while (error > epsilon) {
            float ov = nv;
            nv = 0;
            float delta = (xf - xi) / (float) iterations;
            for (int x = 0; x < iterations; x++) {
                float initial = xi + (x * delta);
                float ending = initial + delta;
                nv += function.apply(initial, ending);
            }
            iterations *= 2;
            itcount++;
            error = ((float) Math.abs(nv - ov) / (float) nv);
        }
        System.out.println("Valor " + nv);
        System.out.println("Numero de iterações = " + itcount);
    }
}
