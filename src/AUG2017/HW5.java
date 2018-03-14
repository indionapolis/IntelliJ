package AUG2017;

import java.util.Scanner;

/**
 * Project name: HomeWork
 * Created by pavel on 18.08.17.
 */
public class HW5 {
    static char[] c = new char[100000];
    public static void main (String[] args) {
        Scanner in = new Scanner(System.in);
        int d = Integer.parseInt(in.nextLine());
        boolean[] p = new boolean[d];

        for(int i = 0; i < d; i++){
            c =  in.nextLine().toCharArray();
            p[i] = find(0);
        }
        for (int i = 0; i < d; i++) {
            if (p[i]){
                System.out.println("YES");
            }else {
                System.out.println("NO");
            }
        }
    }

    public static boolean find(int x){
        if (x < c.length){
            if (c[x] == 'R') {
                if (x < c.length - 1) {
                    if (c[x + 1] == 'Y') {
                        if (x < c.length - 2) {
                            if (c[x + 2] == 'Y') {
                                return find(x + 3);
                            } else {
                                return find(x + 2);
                            }
                        }else {
                            return true;
                        }
                    }else {
                        return  find(x + 1);
                    }
                }else {
                    return true;
                }
            }else {
                return false;
            }
        }else {
            return true;
        }
    }
}
