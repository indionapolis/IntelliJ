package IBC2017;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class Coupling {
    private int n;

    private Point2D.Double[] white, black;

    public Coupling() {
        Scanner in;
        try {
            in = new Scanner(new File("in.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            n = 0;
            return;
        }
        n = in.nextInt();

        white = new Point2D.Double[n];
        black = new Point2D.Double[n];

        for (int i = 0; i < n; i++) {
            white[i] = new Point2D.Double(in.nextDouble(), in.nextDouble());
        }
        for (int i = 0; i < n; i++) {
            black[i] = new Point2D.Double(in.nextDouble(), in.nextDouble());
        }
    }

    /**
     * Finds pairs of white and black points for which line segments intersect
     * @return indexes of intersected line segments
     */
    int[] findIntersection() {
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (intersects(i, j)) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    /**
     * Checks if two line segments between white and black points intersect
     *
     * @param pair1 index of the first pair of white and black points
     * @param pair2 index of the second pair of white and black points
     * @return true if they intersect, false otherwise
     */
    boolean intersects(int pair1, int pair2) {
        Line2D line1 = new Line2D.Double(white[pair1], black[pair1]);
        Line2D line2 = new Line2D.Double(white[pair2], black[pair2]);
        return line1.intersectsLine(line2);
    }

    /**
     * Swaps black points for corresponding white points
     *
     * @param pair1 index of the first pair of white and black points
     * @param pair2 index of the second pair of white and black points
     */
    void swap(int pair1, int pair2) {
        Point2D.Double temp = black[pair1];
        black[pair1] = black[pair2];
        black[pair2] = temp;
    }

    /**
     * Outputs all pairs of white and black points
     */
    void printCoupling() {
        for (int i = 0; i < n; i++) {
            System.out.println("Pair " + i);
            System.out.println("white: " + white[i].x + " " + white[i].y);
            System.out.println("black: " + black[i].x + " " + black[i].y);
        }
    }

    /**
     * @return points of separator (W(x y) B(x y))
     * @input  no
     * firstly i count the numbers of white end black points on one side of the straight line
     * if (white > black) or (white < black) or (point lies on the line)
     * i swap, until (white = black) and (point lies on the line = 0)
     * Вроде оно работает, для запуска нужно запросить метод
     */

    double [] findseparator(){
        boolean exit = true;
        while (exit) {
            for (int f = 0; f < this.n; f++) {
                int Woneside = 0; //numbers of white points
                int Boneside = 0; //numbers of black points
                int on = 0; //point lies on the line
                for (int i = 1; i < this.n; i++) {
                    if (((white[i].x - white[0].x) * (black[0].y - white[0].y) - (white[i].y - white[0].y) * (black[0].x - white[0].x)) > 0){
                        Woneside++;
                    }else if(((white[i].x - white[0].x) * (black[0].y - white[0].y) - (white[i].y - white[0].y) * (black[0].x - white[0].x)) == 0){
                        on++;
                    }
                }
                for (int i = 1; i < this.n; i++) {
                    if (((black[i].x - white[0].x) * (black[0].y - white[0].y) - (black[i].y - white[0].y) * (black[0].x - white[0].x)) > 0){
                        Boneside++;
                    }else if(((black[i].x - white[0].x) * (black[0].y - white[0].y) - (black[i].y - white[0].y) * (black[0].x - white[0].x)) == 0){
                        on++;
                    }
                }


                /**
                 * very big swap
                 */
                if (Woneside > Boneside){
                    Point2D.Double tem = white[0];
                    white[0] = white[f];
                    white[f] = tem;
                }
                else if(Woneside == Boneside){
                    if (on == 0)
                    {
                        exit = false;
                        break; // if all right go out
                    }
                    else {
                        if (Woneside > Boneside){
                            Point2D.Double tem = white[0];
                            white[0] = white[f];
                            white[f] = tem;
                        }
                        else {
                            Point2D.Double tem = black[0];
                            black[0] = black[f];
                            black[f] = tem;
                        }
                    }
                }
                else {
                    Point2D.Double tem = black[0];
                    black[0] = black[f];
                    black[f] = tem;
                }
            }
        }
        return new double[] {white[0].x, white[0].y, black[0].x, black[0].y};
    }
}