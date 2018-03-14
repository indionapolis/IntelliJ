package HASH;

/**
 * Project name: HomeWork
 * Created by pavel on 27.09.2017.
 */
public class HashFunction<K> {
    int capacity = 0;

    private int hash(K key){
        String s = String.valueOf(key).toLowerCase();         //make key type string
        int code = capacity/3;

        for (int i = 0, j = s.length(); i < s.length(); i++, j--) {           //calculate a number depend on key sting
            if (i == 0) code += (capacity/26) * (((int) s.charAt(i))) - 97;          //in alphabet order 1st element more then any previous
            if (i != 0) code -= (i*(int) s.charAt(i)) / (capacity/26) - 97;      //in alphabet order not 1st element less then any previous
        }

        return code % this.capacity;                 //get result
    }







    private int hash1(K key){
        String s = String.valueOf(key).toLowerCase();         //make key type string
        int code = 0;

        for (int i = 0, j = 2; i < s.length(); i++, j++) {           //calculate a number depend on key sting
            if (i == 0) code += (capacity/26) * (((int) s.charAt(i))) - 97;          //in alphabet order 1st element more then any previous
            if (i != 0) code += (((int) s.charAt(i))) / (j*capacity/26) - 97;      //in alphabet order not 1st element less then any previous
        }

        return code % this.capacity;                 //get result
    }
}
