package TREE;

/**
 * Project name: HomeWork
 * Created by pavel on 30.09.2017.
 * v1.0
 */
public class BinarySearchTree<K extends Comparable<K>, V> implements BST {
    private Node root;
    private int size;

    public BinarySearchTree(){
        this.size = 0;
    }


    @Override
    public Object find(Comparable key) {
        Node current = this.root;
        while (current != null) {
            if (current.key.compareTo(key) == 0) {
                return (V) current.value;
            }
            if (current.key.compareTo(key) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return null;
    }


    @Override
    public void insert(Comparable key, Object value) {
        Node current = root;
        Node other = null;

        while (current != null) {

            if (current.key.compareTo(key) == 0) {
                current.value = value;
                return;
            } else {
                other = current;
                if (current.key.compareTo(key) < 0) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }
        }


        if (other == null) {
            root = new Node(key, value, null);
        } else {
            if (other.key.compareTo(key) < 0) {
                other.left = new Node(key, value, other.left);
            } else {
                other.right = new Node(key, value, other.right);
            }
        }
        size++;
    }



    @Override
    public Object remove(Comparable key) {
        Node current = root;
        Node other = null;
        while (current != null) {
            if (current.key.compareTo(key) == 0) {
                break;
            } else {
                other = current;
                if (current.key.compareTo(key) < 0) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }
        }

        if (current == null) {
            return null;
        }

        V ret = (V) current.value;

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


        return ret;
    }


    @Override
    public String traverse() {
        return null;
    }

    @Override
    public void print() {
        display(root, "");
    }

    public void display(Node root , String lvl){
        if(root!=null){
            display(root.left, lvl + "    ");
            System.out.println(lvl + "key: " + root.key + " value: " + root.value);
            display(root.right, lvl + "    ");
        }
    }






    /**
     *
     * @param key
     * @return
     */
    public V get(K key) {
        Node current = this.root;
        while (current != null) {
            if (current.key.compareTo(key) == 0) {
                return (V) current.value;
            }
            if (current.key.compareTo(key) < 0) {
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
        Node current = root, other = null;
        while (current != null) {

            if (current.key.compareTo(key) == 0) {
                current.value = value;
                return;
            } else {
                other = current;
                if (current.key.compareTo(key) < 0) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }
        }


        if (other == null) {
            root = new Node(key, value, null);
        } else {
            if (other.key.compareTo(key) < 0) {
                other.left = new Node(key, value, other.left);
            } else {
                other.right = new Node(key, value, other.right);
            }
        }
    }



    /**
     *
     * @param key
     * @return
     */
    public V removeq(K key) {
        Node current = root;
        Node other = null;
        while (current != null) {
            if (current.key.compareTo(key) == 0) {
                break;
            } else {
                other = current;
                if (current.key.compareTo(key) < 0) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }
        }

        if (current == null) {
            return null;
        }

        V ret = (V) current.value;

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


        return ret;
    }


    /*
     *
     *
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

    public void displayTree(){
        displayTree(root, "");
    }

    private void displayTree(Node root , String lvl){
        if(root!=null){
            displayTree(root.left, lvl + "   ");
            System.out.println(lvl + "(" + root.key + ")");
            displayTree(root.right, lvl + "   ");
        }
    }
    */




    private class Node<K extends Comparable<K>, V>{
        private K key;
        private V value;
        Node<K, V> left;
        Node<K, V> right;
        Node<K, V> parent;


        Node(K key, V value, Node<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        Node predecessor(){
            return parent;
        }


        Node findSuccessor(Node node) {
            if (node == null) return null;
            if (node.right != null){                 //to the left as far as possible
                Node z = node.right;
                for (; z.left != null ; z = z.left);
                return z;
            }


            Node y = node.parent;
            Node x = node;
            while (y != null && x == y.right) {
                 x = y;
                 y = y.parent;
            }

            return y;
        }



    }
}
