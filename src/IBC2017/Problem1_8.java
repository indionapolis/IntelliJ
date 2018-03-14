package sav;

import java.util.Scanner;

/**
 * Project name: HomeWork
 * Created by pavel on 09.08.17.
 */

/**
 * Ввод номера строки с клавиатуры
 */

class Problem1_8 {
    public static void main(String args[]) {
        Fib f = new Fib();
        Scanner in = new Scanner(System.in);
        System.out.println("Fib word: " + f.fib(in.nextInt()));
    }

    /**
     * Реализация формулы чисел фибоначчи для букв
     */

    public static class Fib {

        String fib(int n) {
            if (n == 1) {
                return "a";
            }
            if (n == 0) {
                return "b";
            }
            else {
                return fib(n - 1) + fib(n - 2);
            }
        }
    }
}

