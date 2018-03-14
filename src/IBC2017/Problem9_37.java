package sav;

import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

/**
 * Created by pavel on 03.08.17.
 */
public class Problem9_37 {
    public static void main(String[] args) {
        double     Ssq = 0;
        double     Sse = 0;
        long     x;
        long     y;
        double      pi = Math.PI;

        for (int i = 0; i < 1000000; i++) { //“dropping” 10^6 random points.
            x = rnd(10000)-5000;
            y = rnd(10000)-5000;
            if (x * x + y * y < 25000000){ //points in circle
                Sse++; //points in circle
            }
            Ssq++; // points in square
        }
        System.out.println("result: " + Sse / Ssq * 4);
        System.out.println("Pi: " + pi);
    }


    public static int rnd(int max) //random
    {
        return (int) (Math.random() * ++max);
    }
}
