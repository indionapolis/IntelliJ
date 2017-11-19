package NOV2017;

/**
 * Project name: HomeWork
 * Created by pavel on 04.11.2017.
 * Pavel Nikulin BS1-8
 * Copyright ©
 * v0.0
 */
public class Brick {
    public static void main(String[] args) {
        int x = 164; //ступени
        int y = 20; //кирпичи


        System.out.println("F(H, B): " + g(x, y));
        System.out.println("F(H, 2): " + g(x));
    }



    //решение общей задачи
    private static int g(int H){

        int[] G = new int[H+1];

        for (int i = 0; i < H + 1; i++) {

            if (i == 0) {
                G[i] = 0;
            }
            else {
                int min = 9999;
                for (int h = 1; h < i + 1; h++) {

                    int tmp = Math.max(h - 1, G[i - h]);
                    if (tmp < min) min = tmp;
                }

                G[i] = 1 + min;
            }
        }
        //for (int i = 0; i < G.length; i++) {
        //    System.out.println("(" + i + " : " + G[i] + ") ");
        //}
        return G[H];
    }


    //решение для любого количества кирпичей и ступеней

    /**
     *
     * @param H - ступени
     * @param B - кирпичи
     * @return количество минимальных бросаний для B кирпичей с H башни
     */
    private static int g(int H, int B){

        int[][] G = new int[H + 1][B + 1];


        //делаем сетап
        for (int i = 0; i < B + 1; i++) {
            G[0][i] = 0;

        }
        //делаем сетап
        for (int j = 0; j < H + 1; j++) {
            G[j][0] = 0;
            G[j][1] = j; //когда один кирпич
        }



        // b - brick ; l - lair ; h - optimal height of drop
        for (int b = 2; b < B + 1; b++) {
            for (int l = 1; l < H + 1; l++) {

                int min = 99999;
                for (int h = 1; h < l + 1; h++) {

                    int max = Math.max(G[h - 1][b - 1], G[l - h][b]); //если кирпич сломался/не сломался

                    if (max < min) min = max; //берем оптимальный минимум

                }
                G[l][b] = 1 + min;
            }
        }





        for (int i = 0; i < H + 1; i++) {
            System.out.print("Ступень: " + i + "    | ");
            for (int j = 0; j < B + 1; j++) {
                System.out.print(G[i][j] + " | ");
            }
            System.out.println("");
        }
        System.out.println("");


        return G[H][B]; //result of F(H,B)
    }
}
