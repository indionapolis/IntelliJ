package IMPORTANT;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Scanner;

/**
 * Project name: HomeWork
 * Created by pavel on 25.08.17.
 */
public class in_out_file {
    public static void main(String[] args) throws IOException {
        Locale.setDefault(Locale.US);

        Scanner in = new Scanner(new File("in.txt"));

        PrintWriter out = new PrintWriter(new FileWriter(new File("out.txt")));

        while (in.hasNextInt()){
            out.println(in.nextInt());
        }

        out.close();
    }
}
