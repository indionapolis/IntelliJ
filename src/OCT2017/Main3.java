package OCT2017;

import OCT2017.BTree;

import java.io.*;
import java.util.Scanner;

/**
 * Project name: HomeWork
 * Created by pavel on 22.10.2017.
 * Pavel Nikulin BS1-8
 * Copyright ©
 * v0.0
 */
public class Main3 {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("in.txt"));
        PrintWriter out = new PrintWriter(new FileWriter(new File("out.txt")));

        BTree tree = new BTree(3);                               //tree for values

        while (in.hasNextInt()){
            tree.insert(in.nextInt(), 0);                              //fill tree
        }
                                                                       //int or string
        while (in.hasNext()){
            tree.insert(in.next(), 0);                                 //fill tree
        }

        out.print(tree.traverse());
        out.close();
    }
}
