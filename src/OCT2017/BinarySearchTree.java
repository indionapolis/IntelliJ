package OCT2017;

/**
 * Project name: HomeWork
 * Created by pavel on 30.09.2017.
 * Pavel Nikulin BS1-8
 * Copyright ©
 * v4.0
 */
public class BinarySearchTree<K extends Comparable<K>> implements BST {
    Node root;
    private int size;
    Node lastBranch = null;      //last used branch (for balance)

    public BinarySearchTree(){
        this.root = null;
        this.size = 0;
    }




    /**
     * returns entree with key if exist, null otherwise.
     * @param key the key to find
     * @return key if found else null
     */
    @Override
    public Comparable find(Comparable key) {
        Node current = this.root;
        while (current != null) {                           //search
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



    /**
     * inserts an entry with key.
     * @param key - the key to insert
     */
    @Override
    public void insert(Comparable key) {
        Node current = root;
        Node other = null;

        while (current != null) {                       //find position for insert
            other = current;
            if (current.key.compareTo(key) > 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }


        if (other == null) {                            //insert
            root = new Node(key, null);
        } else {
            if (other.key.compareTo(key) > 0) {
                other.left = new Node(key, other);
            } else {
                other.right = new Node(key, other);
            }
        }
        this.lastBranch = other;
        size++;
    }



    /**
     * removes all entries with key.
     * @param key - the key to remove
     * @return key if found else null
     */
    @Override
    public Comparable remove(Comparable key) {
        Node current = root;
        Node other = null;
        while (current != null) {                       //find position for remove
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


        K result = (K) current.key;


        if (current.right == null && current.left == null){    //if no child
            if (other == null) {
                root = null;
            } else {
                if (current == other.left) {
                    other.left = null;
                } else {
                    other.right = null;
                }
            }
            this.lastBranch = other;
        }else if (current.right == null) {                     //if only one child (left)
            if (other == null) {
                root = current.left;
                root.parent = null;
            } else {
                if (current == other.left) {
                    other.left = current.left;
                    other.left.parent = other;
                } else {
                    other.right = current.left;
                    other.right.parent = other;
                }
            }
            this.lastBranch = other;
        } else if (current.left == null){                       //if only one child (right)
            if (other == null) {
                root = current.right;
                root.parent = null;
            } else {
                if (current == other.left) {
                    other.left = current.right;
                    other.left.parent = other;
                } else {
                    other.right = current.right;
                    other.right.parent = other;
                }
            }
            this.lastBranch = other;
        } else {

            Node leftMax = current.right;
            Node tmp = null;

            while (leftMax.left != null) {                       //if node has 2 child
                tmp = leftMax;
                leftMax = leftMax.left;
            }
            this.lastBranch = tmp;

            if (tmp != null) {
                tmp.left = leftMax.right;
                if (tmp.left != null) tmp.left.parent = tmp;
            } else {
                current.right = leftMax.right;
                if (current.right != null) current.right.parent = current;
            }

            current.key = leftMax.key;
            leftMax = null;
        }


        size--;
        return result;
    }



    /**
     * A standard (inorder) method for traversing a tree is used,
     * with recursion support method {@code toStr}
     * @return string representation
     */
    @Override
    public String traverse() {
        return toStr(this.root);
    }

    private String toStr(Node node){
        if(node != null){
            return ( toStr(node.left) + node.key + " " + toStr(node.right) );
        }
        return "";
    }






    /**
     * A standard (left -> right) method for in breadth traversing a tree is used,
     * with queue and support method {@code nodeTraversal}
     * @return string representation
     */
    @Override
    public String print() {
        String result = "";
        LinkedQueue queue = new LinkedQueue();
        queue.add(root);
        while (queue.size() != 0){
            Node tmp = (Node) queue.remove();
            result += nodeTraversal(tmp);
            if (tmp.left != null) queue.add(tmp.left);
            if (tmp.right != null) queue.add(tmp.right);
        }
        return result.substring(0, result.length() - 2);
    }

    private String nodeTraversal(Node node){
        String result = "";
        if (node == null || (node.right == null && node.left == null)) return "";
        else result += node.key + " ";
        if (node.left != null) result += node.left.key + " ";
        if (node.right != null) result += node.right.key + " ";
        return (result +  "\n");
    }



    /**
     * A mirror (right -> left) method for in breadth traversing a tree is used,
     * with queue and support method {@code mirrorNodeTraversal}
     * @return string representation
     */
    public String mirror(){
        String result = "";
        LinkedQueue queue = new LinkedQueue();
        queue.add(root);
        while (queue.size() != 0){
            Node tmp = (Node) queue.remove();
            result += mirrorNodeTraversal(tmp);
            if (tmp.right != null) queue.add(tmp.right);
            if (tmp.left != null) queue.add(tmp.left);
        }
        return result.substring(0, result.length() - 2);
    }

    private String mirrorNodeTraversal(Node node){
        String result = "";
        if (node == null || (node.right == null && node.left == null)) return "";
        else result += node.key + " ";
        if (node.right != null) result += node.right.key + " ";
        if (node.left != null) result += node.left.key + " ";
        return (result +  "\n");
    }




    public int size(){return this.size;}

    public boolean isEmpty(){return size == 0;}




    class Node<K extends Comparable<K>, V>{
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
