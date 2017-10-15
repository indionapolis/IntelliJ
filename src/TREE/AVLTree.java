package TREE;

/**
 * Project name: HomeWork
 * Created by pavel on 14.10.2017.
 */
public class AVLTree<K extends Comparable<K>, V> {
    private Node root;


    /**
     *
     * @param key
     * @return
     */
    public V get(K key) {
        Node current = this.root;
        while (current != null) {
            if (current.compareTo(key) == 0) {
                return (V) current.value;
            }
            if (current.compareTo(key) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return null;
    }


    /**
     *
     * @param key
     * @param value
     */
    public void add(K key, V value) {
        Node current = root;
        Node other = null;
        while (current != null) {

            if (current.compareTo(key) == 0) {
                current.value = value;
                return;
            } else {
                other = current;
                if (current.compareTo(key) < 0) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }
        }


        if (other == null) {
            root = new Node(key, value, null);
        } else {
            if (other.compareTo(key) < 0) {
                other.left = new Node(key, value, other.left);
            } else {
                other.right = new Node(key, value, other.right);
            }
        }
    }





    /**
     *
     */
    public void display(){
        display(root);
    }

    public void display(Node root){
        if(root!=null){
            display(root.left);
            System.out.println("key: " + root.key + " value: " + root.value);
            display(root.right);
        }
    }


    public int height(){
        return height(root);
    }

    private int height(Node start){
        if (start == null){
            return 0;
        }else {
            return 1 + Math.max(height(start.left), height(start.right));
        }
    }

    public boolean isBalanced(){
        return isBalanced(root);

    }
    private boolean isBalanced(Node current) {
        return Math.abs(height(current.left) - height(current.right)) <= 1;
    }







    private void rebalance(){

    }












    private class Node<K,V>{
        private K key;
        private V value;
        Node<K, V> parent;
        Node<K, V> left;
        Node<K, V> right;


        Node(K key, V value,Node parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }



        public int compareTo(K key){
            try
            {
                return compareTo(Integer.parseInt(key.toString()), (Integer.parseInt(this.key.toString())));
            }
            catch (java.lang.NumberFormatException e)
            {
                try
                {
                    return compareTo(key.toString(), this.key.toString());
                }
                catch (java.lang.NumberFormatException h)
                {
                    return -1;
                }
            }
        }

        public int compareTo(int a, int b){
            return a - b;
        }

        public int compareTo(String a, String b){
            return a.compareTo(b);
        }

    }

}
