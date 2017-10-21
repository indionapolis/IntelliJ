package TREE;

import SEP2017.LinkedQueue;

/**
 * Project name: HomeWork
 * Created by pavel on 30.09.2017.
 * v2.0
 */
public class BinarySearchTree<K extends Comparable<K>> implements BST {
    private Node root;
    private int size;

    public BinarySearchTree(){
        this.size = 0;
        this.root = null;
    }


    @Override
    public Comparable find(Comparable key) {
        Node current = this.root;
        while (current != null) {
            if (current.key.compareTo(key) == 0) {
                return (K) current.key;
            }
            if (current.key.compareTo(key) > 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return null;
    }


    @Override
    public void insert(Comparable key) {
        Node current = root;
        Node other = null;

        while (current != null) {
            other = current;
            if (current.key.compareTo(key) > 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }


        if (other == null) {
            root = new Node(key, null);
        } else {
            if (other.key.compareTo(key) > 0) {
                other.left = new Node(key, other.left);
            } else {
                other.right = new Node(key, other.right);
            }
        }
        size++;
    }



    @Override
    public Comparable remove(Comparable key) {
        Node current = root;
        Node other = null;
        while (current != null) {
            if (current.key.compareTo(key) == 0) {
                break;
            } else {
                other = current;
                if (current.key.compareTo(key) > 0) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }
        }

        if (current == null) {
            return null;
        }

        K ret = (K) current.key;

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
        }


        return ret;
    }


    @Override
    public String traverse() {
        return toStr(this.root);
    }

    @Override
    public void print() {
        display(root, "");
    }

    private void display(Node root , String lvl){
        if(root != null){
            display(root.left, lvl + "    ");
            System.out.println(lvl + root.key);
            display(root.right, lvl + "    ");
        }
    }

    private String toStr(Node node){
        if(node != null){
            return ( toStr(node.left) + node.key + " " + toStr(node.right) );
        }
        return "";
    }


    public String mirror(){
        return mirror(root);
    }


    private String mirror(Node node){
        String srt = "";
        if (node == null || (node.right == null && node.left == null)) return "";
        else srt += node.key + " ";
        if (node.left != null) srt += node.left.key + " ";
        if (node.right != null) srt += node.right.key + " ";
        return (srt +  "\n");
    }

    public String wtr() {
        String str = "";
        LinkedQueue queue = new LinkedQueue();
        queue.add(root);
        while (queue.size() != 0){
            Node tmp = (Node) queue.remove();
            str += mirror(tmp);
            if (tmp.left != null) queue.add(tmp.left);
            if (tmp.right != null) queue.add(tmp.right);

        }
        return str;
    }

    private class Node<K extends Comparable<K>, V>{
        private K key;
        Node<K, V> left;
        Node<K, V> right;
        Node<K, V> parent;


        Node(K key, Node<K, V> parent) {
            this.key = key;
            this.parent = parent;
        }

    }
}
