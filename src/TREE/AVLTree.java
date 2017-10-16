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
                other.left = new Node(key, value, other);
            } else {
                other.right = new Node(key, value, other);
            }
        }

        balanceBranch(other);
    }



    /**
     *
     * @param key
     * @return
     */
    public V remove(K key) {
        Node current = root, other = null;
        while (current != null) {
            if (current.compareTo(key) == 0) {
                break;
            } else {
                other = current;
                if (current.compareTo(key) < 0) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }
        }



        if (current == null) {
            return null;
        }
        if (current.right == null) {
            if (other == null) {
                root = current.left;
            } else {
                if (current == other.left) {
                    other.left = current.left;
                } else {
                    other.right = current.left;
                }
            }
        } else {
            Node leftMost = current.right;
            other = null;
            while (leftMost.left != null) {
                other = leftMost;
                leftMost = leftMost.left;
            }
            if (other != null) {
                other.left = leftMost.right;
            } else {
                current.right = leftMost.right;
            }
            current.key = leftMost.key;
            current.value = leftMost.value;
        }
        return (V) current.value;
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




    /**
     *
     * @return
     */
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




    /**
     *
     * @return
     */
    public boolean isBalanced(){
        return isBalanced(root);
    }

    private boolean isBalanced(Node current) {
        if (height(current) == 0 || current == null) return true;
        return Math.abs(height(current.left) - height(current.right)) <= 1;
    }




    /**
     *
     * @param current
     * @return
     */
    private Node rebalance(Node current){
        if (height(current.right) > height(current.left)){
            if (height(current.right.right) > height(current.right.left)){
                //case1
                Node y = current.right;
                y.parent = current.parent;
                current.parent = y;
                current.right = y.left;
                y.left = current;
                return y;
            }else {
                //case3
                Node x = current.right.left;
                Node y = current.right;
                x.parent = current.parent;
                current.right = x.left;
                y.left = x.right;
                x.left = current;
                x.right = y;
                current.parent = x;
                y.parent = x;
                return x;
            }
        }else {
            if (height(current.left.right) > height(current.left.left)){
                //case2
                Node x = current.left.right;
                Node y = current.left;
                x.parent = current.parent;
                current.left = x.right;
                y.right = x.left;
                x.right = current;
                x.left = y;
                current.parent = x;
                y.parent = x;
                return x;
            }else {
                //case4
                Node y = current.left;
                y.parent = current.parent;
                current.parent = y;
                current.left = y.right;
                y.right = current;
                return y;
            }
        }
    }


    /**
     *
     * @param other
     */
    private void balanceBranch(Node other){
        while (other != null) {
            if (!isBalanced(other.left)) {
                other.left = rebalance(other.left);
            }
            if (!isBalanced(other.right)) {
                other.right = rebalance(other.right);
            }
            other = other.parent;
        }
        if (!isBalanced()) {
            root = rebalance(root);

        }
    }




    private class Node<K,V>{
        private K key;
        private V value;
        Node<K, V> parent;
        Node<K, V> left;
        Node<K, V> right;


        Node(K key, V value, Node parent) {
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
