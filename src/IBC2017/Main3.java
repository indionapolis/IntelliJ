package IBC2017;

/**
 * Project name: HomeWork
 * Created by pavel on 18.08.17.
 */
public class Main3 {
    public static void main(String[] args) {
        ARR[] arr = new ARR[5];
        try {
            for (int i = 0; i < 7; i++) {
                arr[i] = new ARR(i);
            }
        }finally {
            System.out.println("let continue");
        }
        for (int i = 0; i < 3; i++) {
            System.out.println(arr[i].getW());
        }
    }
}
