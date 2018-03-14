package SEP2017;

import java.util.Random;

/**
 * Project name: HomeWork
 * Created by pavel on 16.09.17.
 */
public class DivideAndConquer {
    public static void main(String[] args) {
        Random ran = new Random();
        int[] arr = new int[20];
        int[] S = new int[20];
        for (int i = 0; i < 20; i++) {
            arr[i] = (i % 2 == 0) ? ran.nextInt(100) : ran.nextInt(100) * -1;
        }
        for (int i = 1; i < 20; i++) {
            S[i] = Math.max(S[i - 1] + arr[i], arr[i]);
        }
        for (int i = 0; i < 20; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("");
        for (int i = 0; i < 20; i++) {
            System.out.print(S[i] + " ");
        }
    }

}
