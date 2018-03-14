package AUG2017;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Project name: HomeWork
 * Created by pavel on 25.08.17.
 */

/**
 * Class AUG2017.New2
 * Initializing reading and writing to a file
 *
 */

public class New2 {
    public static void main(String[] args) throws IOException {
        String s, name = "";
        int k = 0;

        File inputFile = new File("in.txt");
        File outputFile = new File("out.txt");

        Scanner in = new Scanner(inputFile);
        PrintWriter out = new PrintWriter(new FileWriter(outputFile));

        LinkedQueue linkedQueue = new LinkedQueue();  //Initializing LinkedQueue

        s = in.nextLine();                            //reading

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' '){                  //get names
                name += s.charAt(i);
            }else{
                linkedQueue.addnewitem(name);
                name = "";
            }
        }

        k = GetIntFromString(name);                   //last name == k

        out.print(linkedQueue.printQueueItem(linkedQueue.josephus(k)));
        out.close();

    }

    /**
     * method Get Int From String
     * @param x string with int number
     * @return int number
     */

    public static int GetIntFromString(String x) {
        String digit = new String();
        Pattern p = Pattern.compile("[-]?[0-9]");
        Matcher m = p.matcher(x);
        while (m.find()) {
            digit += m.group();
        }
        return Integer.parseInt(digit);
    }


    /**
     * Class LinkedQueue
     */
    public static class LinkedQueue{

        /**
         * Class Item
         */
        private class Item {
            private String name;  //data
            private Item next;    //link to the next
        }

        public int number = 0;   //amount of elements
        private Item head;        //pointer to the head
        private Item tail;        //pointer to the tail


        /**
         * method addnemitem - Adds a new item
         * @param name name to add
         */
        public void addnewitem(String name){
            Item item = new Item();

            item.name = name;        // head -> i -> i -> tail
            if(head == null){        //   ^\_______________/
                head = item;
                tail = item;
                head.next = tail;
            }
            else {
                tail.next = item;
                tail = item;
                tail.next = head;
            }
            number++;
        }


        /**
         * method Josephus solves the problem
         * @param k
         * @return answer
         */

        public int josephus(int k) {
            return josephus(number, k, 1);
        }

        private int josephus(int n, int k, int start) {
            if(n == 1) return 1;
            int newstart = (start + k - 2) % n + 1;

            int survivor = josephus(n - 1, k, newstart);
            return (survivor < newstart) ? survivor : survivor + 1;
        }


        /**
         * method deliteitem removes an item
         * @param id item index
         */

        public void deliteitem(int id){
            Item item = tail;
            for (int i = 0; i < id-1; i++){             //looking for a pointer to the desired element
                    item = item.next;
            }
            if (item.next == head) head = head.next;  //If we remove the head we assign a new head
            if (item.next == tail) tail = item;       //If we remove the tail we assign a new tail

            item.next = item.next.next;               // id-1   id   id+1
            number--;                                 //  i -X-> i -> i ->
        }                                             //  \__________/^


        /**
         * method search for item
         * @param id item index
         * @return item name
         */
        public String  printQueueItem(int id){
            Item cursor = head;
            String name = cursor.name;
            for (int i = 0; i < number; i++) {
                if (i == id-1){
                    name = cursor.name;
                }
                cursor = cursor.next;
            }
            return name;
        }
    }
}
