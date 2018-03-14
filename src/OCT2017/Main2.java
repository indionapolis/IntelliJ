package OCT2017;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Project name: HomeWork
 * Created by pavel on 22.10.2017.
 *
 * Pavel Nikulin BS1-8
 * Copyright ©
 *
 * v1.0
 */
public class Main2 {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("in.txt"));
        PrintWriter out = new PrintWriter(new FileWriter(new File("out.txt")));
        AVLTree tree = new AVLTree();
        int result = 0;
        while (in.hasNextInt()){
            tree.insert(in.nextInt());                                //fill tree
        }
        String[] arr = tree.traverse().split(" ");             //get array of entrees
        for (int i = 0; i < arr.length; i++) {
            result += i;                                              //count the number
        }

        out.print(result);
        out.close();
    }
}
