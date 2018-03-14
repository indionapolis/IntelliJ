package sav;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by pavel on 03.08.17.
 */
public class Problem8_36 {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        double a = Double.parseDouble(in.readLine()); //ввод A
        double b = Double.parseDouble(in.readLine()); //ввод B
        double c = Double.parseDouble(in.readLine()); //ввод C
        double e = Double.parseDouble(in.readLine()); //ввод E
        double D = b*b-4*a*c;
        if (a != 0) {
            if(D < 0) { // D < 0, compute numerically a complex “root”s
                if (b != 0){
                    System.out.println((-b / (2 * a) + "+" + sq(-1 * D, e) / (2 * Math.abs(a)) + "i" + ", " + -b / (2 * a) + "-" + sq(-1 * D, e) / (2 * Math.abs(a)) + "i"));
                }
                else {

                    System.out.println((sq(-1 * D, e)) / (2 * Math.abs(a)) + "i" + ", -" + (sq(-1 * D, e)) / (2 * Math.abs(a)) + "i");
                }
            }
            else{
                if (D == 0){ // D = 0, one "root"
                    System.out.println(-b / 2 * a);
                }
                else{ // D > 0 normal "root"s
                    if (b != 0){
                        System.out.println(((-b + sq(D, e)) / (2 * a) + ", " + (-b - sq(D, e)) / (2 * a) ));
                    }
                    else {
                        System.out.println((sq(D, e) / (2 * a) + ", " + (-1* sq(D, e)) / (2 * a) ));
                    }
                }
            }

        }
        else {
            if(b != 0) {
                System.out.println(-c / b);
            }
            else {
                if (c == 0){
                    System.out.println("x : R");
                }
                else {
                    System.out.println("ERROR");
                }
            }
        }
    }

    public static double sq(double n, double E) { // function for get root from number (n) with accuracy (E),  Newton's method
        double x = 1;
        for (;;) {
            double nx = (x + n / x) / 2;
            if ((Math.abs(x - nx)) < E){ break;}
            x = nx;
        }
        return x;
    }
}
