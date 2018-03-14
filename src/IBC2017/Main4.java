package IBC2017;

import IBC2017.GameEntry;

/**
 * Project name: HomeWork
 * Created by pavel on 14.08.17.
 */
public class Main4 {
    public static void main(String[] args) {
        int[] B = new int[6];
        GameEntry player[] = new GameEntry[6];
        for (int i = 0; i < 6; i++) {
            player[i] = new GameEntry(i);
        }
        for (int i = 0; i < 6; i++) {
            B[i] = player[i].score;
        }
        player[4].score = 550;

        for (int i = 0; i < 6; i++) {
            System.out.println(player[i].score);
        }

        System.out.println("");

        for (int i = 0; i < 6; i++) {
            System.out.println(B[i]);
        }
    }
}
