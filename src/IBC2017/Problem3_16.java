package sav;

/**
 * Project name: HomeWork
 * Created by pavel on 09.08.17.
 */


class Problem3_16 {
    public static void main(String args [ ]) {
        long    start = System.currentTimeMillis();
        Fib f = new Fib();
        for (int i = 0; i <= 40; i++) {
            f.fib(i);
        }
        long timeWorkCode = System.currentTimeMillis() - start; //считаем первое время

        long start1 = System.currentTimeMillis();
        Fib2 f2 = new Fib2();
        for (int i = 0; i <= 40; i++) {
            f2.F(i);
        }
        long timeWorkCode1 = System.currentTimeMillis() - start1; //считаем второе время


        System.out.println("Program run-time is " + timeWorkCode + " milisecond(s)");
        System.out.println("f.fib call times: " + f.sum);
        System.out.println("Program run-time is " + timeWorkCode1 + " milisecond(s)");
        System.out.println("f2.F call times: " + f.sum);
    }


    public static class Fib { //стандартная рекурсия для чисел Фибоначчи
        public int sum = 0;
        int fib(int n) {
            sum++;
            if(n == 1){return 1;}
            if(n == 0){return 0;}
            else {return fib(n - 1) + fib(n - 2);}
        }
    }

    public static class Fib2 { //хвостовая рекурсия для чисел Фибоначчи
        public static int fib2(int n, int left, int right){
            if (n == 0){
                return 0;
            }
            if (n == 1){
                return right;
            }
            else {return fib2(n - 1, right,left + right);}
        }
        public static int F(int n){
            return fib2(n, 0, 1);
        } //функция для вызова хвостовой функции
    }
}
