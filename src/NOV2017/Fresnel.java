package NOV2017;

import java.io.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Locale;
import java.util.Scanner;

/**
 * Project name: HomeWork
 * Created by pavel on 11.11.2017.
 * Pavel Nikulin BS1-8
 * Copyright ©
 * v1.0
 */
public class Fresnel {
    public static void main(String[] args) throws IOException {
        Locale.setDefault(Locale.US);
        DecimalFormat df = new DecimalFormat("#.#####"); //5 character precision
        df.setRoundingMode(RoundingMode.HALF_UP);
        df.setMinimumFractionDigits(0);
        Scanner in = new Scanner(new File("in.txt"));
        PrintWriter out = new PrintWriter(new FileWriter(new File("out.txt")));



        double x = in.nextDouble();

        double f = integral(0, x);



        out.print(df.format(f));
        out.close();
    }


    /**
     * Computation of the NOV2017.Fresnel integral by the Simpson method.
     * I originally wanted to do a search for the optimal partition by
     * epsilon, but the program for a long time considered. As a result, the method
     * the optimal value of the partition of the function was found.
     *
     * @param a - bottom bound
     * @param b - upper bound
     *
     * @return The value of the fixed integral on the interval [a, b]
     */
    private static double integral(double a, double b){

        double n = 6289826;     //splitting
        double step;
        double sum;
        double f2 = 0;          //values of the function with co-ratio 2
        double f4 = 0;          //values of the function with co-ratio 4

        step = (b - a)/(2 * n);

        for (int i = 1; i <= 2 * n - 1; i += 2){
            f4 += F(a + step*i);
            f2 += F(a + step*(i+1));
        }

        sum = F(a) + 4*f4 + 2*f2 - F(b); //Simpson formula sum


        return (step / 3) * sum;
    }

    private static double F(double t){ //Function of the NOV2017.Fresnel integral
        return Math.sin(Math.pow(t, 2)) + Math.cos(Math.pow(t, 2));
    }
}
