package OCT2017;

/**
 * Project name: HomeWork
 * Created by pavel on 21.10.2017.
 * Pavel Nikulin BS1-8
 * Copyright ©
 * v1.0
 */
public class AVLTree extends BinarySearchTree{

    /**
     * Inserts an entry with key
     * and balance branch.
     * @param key - the key to insert
     */
    @Override
    public void insert(Comparable key) {
        super.insert(key);
        balanceBranch(lastBranch);
    }


    /**
     * Removes all entries with key
     * and balance branch.
     * @param key - the key to remove
     * @return key if found else null
     */
    @Override
    public Comparable remove(Comparable key) {
        Comparable tmp = super.remove(key);
        balanceBranch(lastBranch);
        return tmp;
    }


    /**
     * The method checks the branch for balance and uses
     * the support method {@code rebalance} if it is not balanced
     * @param current - node in the branch
     */
    private void balanceBranch(Node current){
        while (current != null) {
            if (!isBalanced(current.left)) {
                current.left = rebalance(current.left);
            }
            if (!isBalanced(current.right)) {
                current.right = rebalance(current.right);
            }
            current = current.parent;
        }

        if (!isBalanced()) {
            root = rebalance(root);
        }
    }



    /**
     * The method checks the branch for balance
     * @return is node balanced
     */
    public boolean isBalanced(){
        return isBalanced(root);
    }

    private boolean isBalanced(Node current) {
        if (current == null) return true;
        return Math.abs(height(current.left) - height(current.right)) <= 1;
    }



    /**
     * The method checks the node for height
     * @return height of longest branch
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
     * the method is implemented based on the
     * lecture of the professor (Dr.) Adil M. Khan
     * @param current - node in the branch that need to balance
     * @return balanced node
     */
    private Node rebalance(Node current){
        if (height(current) < 3) return current;
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
}
