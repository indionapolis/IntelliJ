package SEP2017;

import java.io.*;
import java.util.Scanner;

/**
 * Project name: HomeWork
 * Created by pavel on 27.09.2017.
 * v5.0
 * Finished?
 */
public class Main2 {
    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(new File("out.txt")));

        Object[] array1 = getArray();
        Object[] array2 = array1.clone();
        Object[] array3 = array1.clone();               //copy array
        Object[] array4 = array1.clone();
        Object[] array5 = array1.clone();


        array1 = Bubble.Sort(array1);
        array2 = Insertion.Sort(array2);
        array3 = Selection.Sort(array3);                //sorted arrays
        array4 = Quick.Sort(array4);
        array5 = Merge.Sort(array5);



        for (int i = 0; i < array1.length; i++) {

            if (!array1[i].equals(array2[i])) throw new Error();
            if (!array1[i].equals(array3[i])) throw new Error();
            if (!array1[i].equals(array4[i])) throw new Error();
            if (!array1[i].equals(array5[i])) throw new Error();
            if (!array2[i].equals(array3[i])) throw new Error();    //check all arrays
            if (!array2[i].equals(array4[i])) throw new Error();
            if (!array2[i].equals(array5[i])) throw new Error();
            if (!array3[i].equals(array4[i])) throw new Error();
            if (!array3[i].equals(array5[i])) throw new Error();
            if (!array4[i].equals(array5[i])) throw new Error();

        }



        for (int i = 0; i < array1.length; i++) {
            out.print(array5[i] + " ");             //output in file
        }
        out.close();

    }


    /**
     * method for getting array
     * @return
     * @throws FileNotFoundException
     */
    private static String[] getArray() throws FileNotFoundException {
        Scanner in = new Scanner(new File("in.txt"));               //input file

        LinkedQueue<String> queue = new LinkedQueue();                           //queue for IO

        while (in.hasNext()){
            queue.add(in.next());
        }

        String[] array = new String[queue.size()];                               //create array

        for (int i = 0; i < array.length; i++) {
            array[i] = queue.remove();
        }

        return array;
    }


}
