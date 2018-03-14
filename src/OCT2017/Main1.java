package OCT2017;

import java.io.*;
import java.util.Scanner;

/**
 * Project name: HomeWork
 * Created by pavel on 21.10.2017.
 */
public class Main1 {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("in.txt"));
        PrintWriter out = new PrintWriter(new FileWriter(new File("out.txt")));

        BinarySearchTree tree = new BinarySearchTree();             //tree for values

        LinkedQueue queue = new LinkedQueue();                      //queue for values

        while (in.hasNextInt()){
            queue.add(in.nextInt());                                //fill queue
        }

        while (queue.size() > 3){
            tree.insert((Comparable) queue.remove());               //fill tree
        }


        out.println(tree.find((Comparable) queue.remove()));        //find and print

        tree.remove((Comparable) queue.remove());                   //remove
        tree.insert((Comparable) queue.remove());                   //insert

        out.println(tree.traverse());                               //print inorder
        out.println("OCT2017.BST:");
        out.println(tree.print());                                  //print OCT2017.BST
        out.println("BSMT:");
        out.print(tree.mirror());                                   //print BSMT

        out.close();
    }

}
