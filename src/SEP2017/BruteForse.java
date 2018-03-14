package SEP2017;

import java.awt.geom.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class BruteForse {
    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.US);
        Scanner in = new Scanner(new File("in.txt"));
        PrintWriter out = new PrintWriter("out.txt");

        int[] ci = new int[20];
        int[] wi = new int[20];
        long result = 0, mw = 0;
        int w = 1000;
        int x = 0;


        for(int i = 0 ; i < 20; i++)
            wi[i] = in.nextInt();

        for(int i = 0 ; i < 20; i++)
            ci[i] = in.nextInt();


        for (int  i = 1; i < (1 << 20); i++){

            String s = Integer.toBinaryString(i);

            int xw = 0, xc = 0;

            for (int j = 0; j < s.length(); j++)

                if (s.charAt(j) == '1') {
                    xw += wi[j];
                    xc += ci[j];
                }

            if (xw <= w) {

                if (xc > result){
                    mw = xw;
                }
                result = Math.max(xc, result);

            }
        }

        System.out.println(result + " " + mw);
    }
}
