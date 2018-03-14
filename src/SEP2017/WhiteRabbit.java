package SEP2017;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Project name: HomeWork
 * Created by pavel on 27.09.2017.
 * Finished
 */
public class WhiteRabbit {

    public static void main(String[] args) throws Exception {
        PrintWriter out = new PrintWriter("out.txt");

        //out.print(bruteForce());
        out.print(dynamic());
        out.close();

    }

    public static int bruteForce() throws FileNotFoundException {
        Scanner in = new Scanner(new File("in.txt"));

        String[] s1 = in.nextLine().split(" +");                     //firs line
        int budget = in.nextInt();                                         //budget or capacity

        int[] time = new int[s1.length/2];                                 //time array
        int[] cost = new int[s1.length/2];                                 //cost array


        for(int i = 0, j = 0 ; i < s1.length; j++, i = i + 2){
            time[j] = Integer.parseInt(s1[i]);                             //fill arrays
            cost[j] = Integer.parseInt(s1[i+1]);
        }


        int maxTime = 0;                                                   //maximum number of seconds that the White Rabbit can buy.

        for (int  i = 1; i < (1 << time.length); i++){

            String s = Integer.toBinaryString(i);                              //for any possible case

            int allCost = 0;
            int allTime = 0;

            for (int j = cost.length - 1; j >= cost.length - s.length(); j--)

                if (s.charAt(j - (cost.length - s.length())) == '1')           //check from end of binary string
                {
                    allCost += cost[j];
                    allTime += time[j];
                }

            if (allCost <= budget) {                                           //if we can get this set

                maxTime = Math.max(allTime, maxTime);                          //get maximum set sum

            }
        }
        return maxTime;
    }



    public static int dynamic() throws FileNotFoundException {
        Scanner in = new Scanner(new File("in.txt"));

        String[] s1 = in.nextLine().split(" +");                  //firs line
        int budget = in.nextInt();                                      //budget or capacity

        int[] time = new int[s1.length / 2];                            //time array
        int[] cost = new int[s1.length / 2];                            //cost array


        for (int i = 0, j = 0; i < s1.length; j++, i = i + 2) {
            time[j] = Integer.parseInt(s1[i]);                          //fill arrays
            cost[j] = Integer.parseInt(s1[i + 1]);
        }

        int maxTime = 0;                                                //maximum number of seconds that the White Rabbit can buy.



        int capacity = budget + 1;
        int numberOfPairs = cost.length + 1;
        int[][] table = new int[capacity][numberOfPairs];                      //create table for results

        for( int i = 0; i < capacity; i++) {
            table[i][0] = 0;
        }

        for( int i = 0; i < numberOfPairs; i++) {
            table[0][i] = 0;
        }



        for( int i = 1; i < numberOfPairs; i++) {                              //fill table

            int currentCost = cost[i - 1];
            int currentTime = time[i - 1];

            for( int j = 1; j < capacity; j++) {                               //search for best value

                if( currentCost <= j) {


                    int currentCostOfSet = j - currentCost;                    //take better time/cost

                    if (table[j][i - 1] > ( table[currentCostOfSet][i - 1] + currentTime)){
                        table[j][i] = table[j][i - 1];
                    }else {
                        table[j][i] = table[currentCostOfSet][i - 1] + currentTime;
                    }


                }
                else {
                    table[j][i] = table[j][i - 1];                             //do not overload the available budget
                }
            }
        }



        int j = capacity - 1;
        for( int i = numberOfPairs - 1; i > 0; i--) {                           //search for best value

            if( table[j][i] != table[j][i - 1]) {
                j -= cost[i - 1];
                maxTime += time[i - 1];
            }

            if( j < 0) break;
        }


        return maxTime;
    }

}
