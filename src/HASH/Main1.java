package HASH;

import java.io.*;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Project name: HomeWork
 * Created by pavel on 25.09.17.
 */
public class Main1 {
    public static void main(String[] args) throws IOException {
        make(newTable());
    }


    /**
     * method solve problem B
     * @param table
     * @throws IOException
     */
    public static void make(HashTable table) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(new File("out.txt")));    // file out

        String[] s = table.entrySet();
        String[] keys = table.keySet();
        int[] count = Arrays.stream(table.valueSet()).mapToInt(Integer::parseInt).toArray();   //cast values arr to int arr

        for (int j = 0, i = 0; i < s.length; i++) {
            if (count[i] > 1) {                        //if word appear more then ones
                if (j != 0) out.println();             //problem with IO on codetest
                j++;                                   //print in file with new line
                out.print(s[i]);                       //print in file if count > 1
            }
            table.remove(keys[i]);                     // "at the end of the task your hash table must be empty."
        }
        out.close();

    }

    /**
     * method create new HASH table and fills it
     * @return new HASH table
     * @throws IOException
     */
    public static HashTable newTable() throws IOException {
        Locale.setDefault(Locale.US);
        Scanner in = new Scanner(new File("in.txt"));

        String[] e = ("a in at to on not for s is are am has i we you").split(" +");  //consider all the words except that are in the following list
        HashTable stopList = new HashTable();
        for (int i = 0; i < e.length; i++) {
            stopList.put(e[i], 1);
        }

        HashTable newtable = new HashTable();


        while (in.hasNext()){
            String word = format(in.next().toLowerCase());                  //get "clean" word
            int c = 1;


            if (word.equals("") || stopList.get(word) != null) continue;    //if word empty or contains stopWord don't add


            if (newtable.get(word.substring(0, word.length()-1)) != null){
                word = word.substring(0, word.length()-1);                  //if we have ' word - "s" '
                c = 1 + (int) newtable.get(word);
            }

            if (newtable.get((word + "s")) != null){
                c = 1 + (int) newtable.remove(word + "s");             //if we have ' word + "s" '
            }


            if (newtable.get(word) != null){
                c = 1 + (int) newtable.get(word);                           //if word already exist
            }

            newtable.put(word, c);                                          //value (c) = number of coincidence
        }

        return newtable;
    }


    public static String format(String s) {
        String word = new String();
        Pattern pat = Pattern.compile("^[a-z]+");        //all laters from start of word
        Matcher matcher = pat.matcher(s);
        while (matcher.find()) {
            word += matcher.group();
        }

        return word;                                     //return "clean" word
    }
}
