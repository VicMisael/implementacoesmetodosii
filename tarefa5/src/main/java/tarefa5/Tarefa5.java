package tarefa5;

import java.util.function.BiFunction;

public class Tarefa5 {
    public static void main(String args[]) {

        System.out.println("x^4+cos(x)");
        System.out.println("Gauss legendre");

        float xi = 2;
        float xf = 3;
        System.out.println("2 pontos");
        iterate(xi, xf, GaussLegendre::twopoints);
        System.out.println("3 pontos");
        iterate(xi, xf, GaussLegendre::threepoints);
        System.out.println("4 pontos");
        iterate(xi, xf, GaussLegendre::fourpoints);
    }


    private static void iterate(float xi, float xf, BiFunction<Float, Float, Float> function) {

        float error = 100f;
        float epsilon = 10E-4f;
        int iterations = 1;
        float nv = function.apply(xi, xf);
        System.out.println("Resultado é" + nv);
        while (error > epsilon) {
            float ov = nv;
            iterations = iterations * 2;
            nv = 0;
            float delta = (xi - xf) / iterations;
            int itcount = 0;
            for (int x = 0; x < iterations; x++) {
                float initial = xi + x * delta;
                float ending = initial + delta;
                nv += ((ending - initial) / 2) * function.apply(xi, xf);
                itcount++;
            }
            error = (Math.abs(ov - nv) / nv);
            System.out.println("Numero de iterações = " + itcount);
        }
    }
}
