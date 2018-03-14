package PROJECTS;

import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

import javax.swing.*;
import java.awt.*;
import java.io.Console;
import java.io.IOException;
import java.util.Scanner;

/**
 * Project name: HomeWork
 * Created by pavel on 18.08.17.
 */
public class e_Book {


    public static void main(String[] args) throws InterruptedException, IOException {
        Interface e_book = new Interface();
        e_book.setVisible(true);
    }



    private static class Interface extends JFrame{
        public Interface() throws InterruptedException, IOException {
            super("PROJECTS.e_Book.v1.0");
            this.setBounds(500,500,200,500);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //menu();
        }


        private void menu() throws InterruptedException, IOException {
            Scanner in = new Scanner(System.in);

            int x = 0;
            System.out.println("  Menu");
            System.out.println("1) Buy");
            System.out.println("2) Read");
            System.out.println("3) All");
            System.out.println("4) Exit");
            System.out.print("chose one and press ENTER: ");
            if (in.hasNextInt()){
                x = in.nextInt();
            }
            switch (x){
                case 1:
                    buy();
                    break;
                case 2:
                    read();
                    break;
                case 3:
                    all();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("incorrect command");
                    menu();
            }
        }


        private void buy() throws InterruptedException, IOException {
            System.out.println("Buy");
            menu();
        }

        private void read() throws InterruptedException, IOException {
            System.out.println("Read");
            menu();
        }

        private void all() throws InterruptedException, IOException {
            System.out.println("All");
            menu();
        }

    }
}
