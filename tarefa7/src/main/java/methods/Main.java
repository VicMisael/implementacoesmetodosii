package methods;

import java.util.Objects;
import java.util.function.Function;

import static java.lang.Math.*;

public class Main {
    public static float exponencialMethod(float x, float y, float epslon, Function<Float, Float> function, TriFunction<Function<Float, Float>, Float, Float, Function<Float, Float>> f_type) {
        float c = 1f;
        int count = 0;
        float lastResult = 0;
        float result = 0;
        float maxValueC = 0.01f;
        Function<Float, Float> newf = f_type.apply(function, x, y);
        while (true) {
            count++;
            float integral = NewtonCotes.build(newf).integration(-c, c, epslon);
            c *= 1.1f;
            lastResult = result;
            result = integral;
            float error = abs(lastResult - result);
            System.out.println("o erro atual 2 é" + error);
            if (error < maxValueC)
                break;
        }
        return result;
    }

    static final Function<Float, Float> firstProblem = (x) -> 1f / (float) cbrt(pow(x, 2));
    static final Function<Float, Float> secondProblem = (x) -> 1f / (float) sqrt(4f - pow(x, 2));

    static final public Function<Float, Float> solutionFirst(Function<Float, Float> f, final float x_origin, final float y_origin) {
        Function<Float, Float> x_s = (s) ->
                ((x_origin + y_origin) / 2) + (((y_origin - x_origin) / 2) * (float) tanh(s));
        Function<Float, Float> dx_s = s -> (((y_origin - x_origin) / 2) * (1 / (float) (pow(cosh(s), 2))));
        return s -> (f.apply(x_s.apply(s)) * dx_s.apply(s));
    }

    static final public Function<Float, Float> solutionSecond(Function<Float, Float> f, final float x_origin, final float y_origin) {
        Function<Float, Float> x_s = s -> ((x_origin + y_origin) / 2f) + (((y_origin - x_origin) / 2f) * (float) tanh(PI / (2 * sinh(s))));


        Function<Float, Float> dx_s = s -> ((y_origin - x_origin) / 2f) * (float) (PI * (float) cosh(s)) / (2f * (float) pow(cosh(PI / (2f * sinh(s))), 2));
        return s -> (f.apply(x_s.apply(s)) * dx_s.apply(s));
    }


    public static void main(String[] args) {
        System.out.println(firstProblem.apply(1f));
        System.out.println(secondProblem.apply(1f));
        float firstproblem = exponencialMethod(-1, 0, 0.001f, firstProblem, Main::solutionFirst);
        //float secondproblem = exponencialMethod(-2, 0, 0.00001f, secondProblem, Main::solutionSecond);
        System.out.println("Solução 1:" + firstproblem * 2);
        //System.out.println("Solução 2:" + secondproblem);
    }

    @FunctionalInterface
    interface TriFunction<A, B, C, R> {

        R apply(A a, B b, C c);

        default <V> TriFunction<A, B, C, V> andThen(
                Function<? super R, ? extends V> after) {
            Objects.requireNonNull(after);
            return (A a, B b, C c) -> after.apply(apply(a, b, c));
        }
    }
}
