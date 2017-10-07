import com.sun.org.apache.regexp.internal.RE;

/**
 * Project name: HomeWork
 * Created by pavel on 06.10.2017.
 */
public class RedBlackTree<K,V>{
    private Entry root;
    private static final boolean RED = true;
    private static final boolean BLACK = false;




    public V get(K key)
    {  return get(root, key);  }

    private V get(Entry x, K key)
    {  // Return value associated with key in the subtree rooted at x;
        // return null if key not present in subtree rooted at x.
        if (x == null) return null;
        int cmp = x.compareTo(key);
        if      (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return (V) x.value;
    }



    public void put(K key, V val)
    {  // Search for key. Update value if found; grow table if new.
        root = put(root, key, val);
        root.color = BLACK;
    }

    private Entry put(Entry x, K key, V value)
    {
        // Change key’s value to val if key in subtree rooted at x.
        // Otherwise, add new node to subtree associating key with val.

        if (x == null) return new Entry(key, value, 1, RED);

        int cmp = x.compareTo(key);
        if (cmp < 0) x.left  = put(x.left,  key, value);
        else if (cmp > 0) {
            x.right = put(x.right, key, value);
        } else x.value = value;

        if (isRed(x.right) && !isRed(x.left))    x = rotateLeft(x);
        if (isRed(x.left) && isRed(x.left.left)) x = rotateRight(x);
        if (isRed(x.left) && isRed(x.right))     flipColors(x);

        x.N = size(x.left) + size(x.right) + 1;

        return x;
    }



    public void deleteMin()
    {
        root = deleteMin(root);
    }

    private Entry deleteMin(Entry x)
    {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }


    public K min()
    {
        return (K) min(root).key;
    }

    private Entry min(Entry x)
    {
        if (x.left == null) return x;
        return min(x.left);
    }


    public void delete(K key)
    {  root = delete(root, key);  }

    private Entry delete(Entry x, K key)
    {
        if (x == null) return null;
        int cmp = x.compareTo(key);
        if      (cmp < 0) x.left  = delete(x.left,  key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else
        {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Entry t = x;
            x = min(t.right);  // See page 407.
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }


    Entry rotateLeft(Entry h)
    {
        Entry x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    Entry rotateRight(Entry h) {
        Entry x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left)
                + size(h.right);
        return x;
    }

    void flipColors(Entry h)
    {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }




        private class Entry<K,V>{
        private K key;
        private V value;
        boolean color;
        int N;
        Entry<K, V> left;
        Entry<K, V> right;
        Entry<K, V> parent;


        Entry(K key, V value, int N, boolean color) {
            this.key = key;
            this.value = value;
            //this.parent = parent;
            this.color = color;
            this.N = N;
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

    public int size()
    {  return size(root);  }

    private int size(Entry x)
    {
        return (x == null) ? 0 : x.N;
    }

    private boolean isRed(Entry some)
    {
        if (some == null) return false;
        return some.color == RED;
    }
}
