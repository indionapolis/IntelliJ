package sav;

import java.io.*;
import java.util.Scanner;

/**
 * Created by pavel on 02.08.17.
 */
public class Problem7_32 {
    public static void main(String[] args) throws IOException {

        File inputFile = new File("in.txt");
        File outputFile = new File("out.txt");

        Scanner in = new Scanner(inputFile);

        FileWriter fileWriter = new FileWriter(outputFile);
        try (PrintWriter out = new PrintWriter(fileWriter)) {

            double x1 = in.nextDouble();
            double y1 = in.nextDouble();
            double x2 = in.nextDouble();
            double y2 = in.nextDouble();
            double x3 = in.nextDouble();
            double y3 = in.nextDouble();
            double x4 = in.nextDouble();
            double y4 = in.nextDouble();


            out.print(two_segments_intersects(x1, y1, x2, y2, x3, y3, x4, y4));

            out.close();
        }
    }

    public static String two_segments_intersects(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {

            double x = ((x1 * y2 - x2 * y1) * (x4 - x3) - (x3 * y4 - x4 * y3) * (x2 - x1)) / ((y1 - y2) * (x4 - x3) - (y3 - y4) * (x2 - x1));
            double y = ((y3 - y4) * x - (x3 * y4 - x4 * y3)) / (x4 - x3);
            if (((x1 <= x) && (x2 >= x) && (x3 <= x) && (x4 >= x)) || ((y1 <= y) && (y2 >= y) && (y3 <= y) && (y4 >= y))) {
                if ((x2-x1)/(y2-y1)!=(x4-x3)/(y4-y3)){return "TRUE";} else {return "FALSE";}}
            else {return "FALSE";}



    }
}
