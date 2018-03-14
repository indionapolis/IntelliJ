package sav;

/**
 * Project name: HomeWork
 * Created by pavel on 08.08.17.
 */
public class Problem2_18 {

    /**
     * Основная формула вычисления количества наборов сокращается по правилам математики,
     * и в итоге мы, через сумму и разность логорифмов, находим количество разрядов -> количество цифр
     */

    public static void main(String[] args) {
        double sum = 0;
        for (int i = 101; i < 401; i++) {
            sum += Math.log10(i);
        }

        for (int i = 1; i < 301; i++) {
            sum -= Math.log10(i);
        }

        System.out.println((int) (Math.floor(sum) + 1));
    }
}
