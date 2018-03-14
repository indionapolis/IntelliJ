package HASH;

/**
 * Project name: HomeWork
 * Created by pavel on 09.09.17.
 */
public class Main {
    public static void main(String[] args) {
        HashTableList map = new HashTableList(1);
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        map.put(4, 4);
        map.put(5, 5);
        map.put(6, 6);
        map.put(7, 7);
        map.put(8, 8);
        map.put(9, 9);
        map.put(9, 9);
        map.put(9, 9);
        map.put(9, "dev");
        map.put(9, "nie");
        map.put(10, 10);
        for (int i = 1; i != 11; i=i+1) {
            System.out.println(map.get(i));
        }
        System.out.println("");

        for (int i = 0; i < 17; i++) {
            System.out.println("size " + map.volume());
            System.out.println(map.remove(i));
            System.out.println(" ");
        }

    }
}
