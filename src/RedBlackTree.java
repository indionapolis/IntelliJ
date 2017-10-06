/**
 * Project name: HomeWork
 * Created by pavel on 06.10.2017.
 */
public class RedBlackTree {
    public static void main(String[] args) {
        System.out.println("hello");
    }



    static class Entry<K,V>{
        private K key;
        private V value;
        boolean color;
        Entry<K, V> left;
        Entry<K, V> right;
        Entry<K, V> parent;


        Entry(K key, V value, Entry<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        Entry predecessor(){
            return parent;
        }


        Entry findSuccessor(Entry node) {
            if (node == null) return null;
            if (node.right != null){                 //to the left as far as possible
                Entry z = node.right;
                for (; z.left != null ; z = z.left);
                return z;
            }


            Entry y = node.parent;
            Entry x = node;
            while (y != null && x == y.right) { //while x is right child of y
                x = y;
                y = y.parent;
            }
            //Intuition: as we traverse left up the tree we traverse smaller values
            //The first node on the right is the next larger number
            return y;
        }

        public int compareTo(K key){
            try {
                return compareTo(Integer.parseInt(key.toString()), (Integer.parseInt(this.key.toString())));
            }finally {

                /*try {
                    return compareTo(Integer.parseInt(key.toString()), this.key.toString());
                }finally {
                    try {
                        return compareTo(key.toString(), Integer.parseInt(this.key.toString()));
                    }finally {
                        return compareTo(key.toString(), this.key.toString());
                    }
                }*/
            }

        }

        public int compareTo(int a, int b){
            return a - b;
        }

        public int compareTo(String a, int b){
            return -1;
        }

        public int compareTo(int a, String b){
            return 1;
        }

        public int compareTo(String a, String b){
            return a.compareTo(b);
        }

    }
}
