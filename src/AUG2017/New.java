package AUG2017;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.*;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

/**
 * Project name: HomeWork
 * Created by pavel on 25.08.17.
 */
public class New {
    public static void main(String[] args) throws IOException {
        Locale.setDefault(Locale.US);

        File inputFile = new File("in.txt");
        File outputFile = new File("out.txt");

        Scanner in = new Scanner(inputFile);
        PrintWriter out = new PrintWriter(new FileWriter(outputFile));

        Crossing_number_algorithm cna = new Crossing_number_algorithm();

        Point point = new Point();
        Polygon polygon = new Polygon();

        while (in.hasNextDouble()){
            Point p = new Point();
            p.x = in.nextDouble();
            p.y = in.nextDouble();
            point = p;
            if (in.hasNextDouble()) polygon.addnewpoint(point);
        }

        out.println((cna.point_in_polygon(point, polygon)) ? "YES" : "NO");
        //out.print(polygon.area());
        out.close();
    }

    static class Crossing_number_algorithm{
        public static boolean point_in_polygon(Point point, Polygon polygon){
            Polygon.PolygonPoint p = polygon.head;
            int i = 0;
            for (int j = 0; j < polygon.number-1; j++) {

                // point.x  point.y  polygon.x_max  polygon.y_max  p.point.x   p.point.y   p.next.point.x p.next.point.y
                // ax1,     ay1,     ax2,           ay2,           bx1,        by1,        bx2,           by2
                // your code here

                double v1 = (p.next.point.x - p.point.x) * (point.y - p.point.y) - (p.next.point.y - p.point.y) * (point.x - p.point.x);
                double v2 = (p.next.point.x - p.point.x) * (polygon.y_max + 100 - p.point.y) - (p.next.point.y - p.point.y) * ( - p.point.x);
                double v3 = (polygon.x_max + 100 - point.x) * (p.point.y - point.y) - (polygon.y_max + 100 - point.y) * (p.point.x - point.x);
                double v4 = (polygon.x_max + 100 - point.x) * (p.next.point.y - point.y) - (polygon.y_max + 100 - point.y) * (p.next.point.x - point.x);

                if (( v1 * v2 < 0) && ( v3 * v4 < 0)){
                    i++;
                }

                p = p.next;
            }
            System.out.println(i);
            return (i % 2 == 1);
        }
    }

    static class Point{
        double x;
        double y;
    }

    static class Polygon {

        public class PolygonPoint {
            public Point point;
            public PolygonPoint next;
        }

        public int x_min = 1000000;
        public int y_min = 1000000;
        public int x_max = -1000000;
        public int y_max = -1000000;
        public int number = 0;
        public PolygonPoint head;



        public void addnewpoint(Point point){
            this.number++;
            if (point.x > x_max) x_max = (int) point.x;
            if (point.y > y_max) y_max = (int) point.y;
            if (point.x < x_min) x_min = (int) point.x;
            if (point.y < y_min) y_min = (int) point.y;

            PolygonPoint n = new PolygonPoint();

            n.point = point;
            n.next = null;
            if(head == null) head = n;
            else {
                n.next = head;
                head = n;
            }

        }


        public double area(){
            Crossing_number_algorithm cna = new Crossing_number_algorithm();
            Random random = new Random();
            Point point = new Point();
            double area = 0;
            double d = 1;
            double s;
            double in = 0, all = 0;
            while (d > 0.0001){

                for (int i = 0; i < 1000000; i++) {
                    point.x = random.nextInt(x_max - x_min + 200) + x_min - 100;
                    point.y = random.nextInt(y_max - y_min + 200) + y_min - 100;
                    if (cna.point_in_polygon(point, this)){
                        in++;
                    }
                    all++;
                }


                s = (x_max - x_min) * (y_max - y_min) * (in / all);
                d = Math.abs(area - s);
                area = s;
                System.out.println(x_max - x_min);
                System.out.println(y_max - y_min);
            }
            return area;
        }

    }
}
