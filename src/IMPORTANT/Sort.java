package IMPORTANT;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Random;

/**
 * Project name: HomeWork
 * Created by pavel on 19.08.17.
 */
public class Sort {
    public static void main(String[] args) throws FileNotFoundException, Exception {
        int[] m = new int[500000000];
        Random random = new Random();
        for (int i = 0; i < m.length; i++) {
            m[i] = random.nextInt(m.length * 10);
        }
        //for (int i = 0; i < m.length; i++) {
        //    System.out.print(m[i] + "  ");
        //    if (i%20 == 19){
        //        System.out.println("");
        //    }
        //}

        System.out.println("");
        System.out.println("");


        //m = bubbleSort(m);
        //m = selectSort(m);
        //m = insertSort(m);
        m = quickSort(m);
        //mergeSort(m);



        //for (int i = 0; i < m.length; i++) {
        //   System.out.print(m[i] + "  ");
        //    if (i%20 == 19){
        //        System.out.println("");
        //    }
        //}




    }

    public static int[] bubbleSort(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int t = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = t;
                }
            }
        }
        return arr;
    }


    public static int[] selectSort(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            int min = arr[i];
            int id = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < min){
                    min = arr[j];
                    id = j;
                }
            }
            if (arr[i] != arr[id]){
                arr[id] = arr[i];
                arr[i] = min;
            }
        }
        return arr;
    }


    public static int[] insertSort(int[] arr){
        int j, temp;
        for (int i = 0; i < arr.length; i++) {
            temp = arr[i];
            for (j = i - 1; j >= 0 && arr[j] > temp ; j--) {
                arr[j+1] = arr[j];
            }
            arr[j+1] = temp;
        }
        return arr;
    }



    public static int[] quickSort(int[] arr) {
        int startIndex = 0;
        int endIndex = arr.length - 1;
        arr = doSort(arr, startIndex, endIndex);
        return arr;
    }

    private static int[] doSort(int[] arr, int start, int end) {
        if (start >= end)
            return arr;
        int i = start, j = end;
        int cur = i - (i - j) / 2;
        while (i < j) {
            while (i < cur && (arr[i] <= arr[cur])) {
                i++;
            }
            while (j > cur && (arr[cur] <= arr[j])) {
                j--;
            }
            if (i < j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                if (i == cur)
                    cur = j;
                else if (j == cur)
                    cur = i;
            }
        }
        doSort(arr, start, cur);
        doSort(arr, cur+1, end);
        return arr;
    }


    public static void mergeSort(int[] a)
    {
        int[] tmp = new int[a.length];
        mergeSort(a, tmp,  0,  a.length - 1);
    }


    private static void mergeSort(int[] a, int[] tmp, int left, int right)
    {
        if( left < right )
        {
            int center = (left + right) / 2;
            mergeSort(a, tmp, left, center);
            mergeSort(a, tmp, center + 1, right);
            merge(a, tmp, left, center + 1, right);
        }
    }


    private static void merge(int[] a, int[] tmp, int left, int right, int rightEnd )
    {
        int leftEnd = right - 1;
        int k = left;
        int num = rightEnd - left + 1;

        while(left <= leftEnd && right <= rightEnd)
            if(a[left] < a[right])
                tmp[k++] = a[left++];
            else
                tmp[k++] = a[right++];

        while(left <= leftEnd)    // Copy rest of first half
            tmp[k++] = a[left++];

        while(right <= rightEnd)  // Copy rest of right half
            tmp[k++] = a[right++];

        // Copy tmp back
        for(int i = 0; i < num; i++, rightEnd--)
            a[rightEnd] = tmp[rightEnd];
    }


    public static boolean[] pattern_search(String  pat, String  txt){
        boolean[] x = new boolean[txt.length()-pat.length() + 1];
        for (int i = 0; i < txt.length()-pat.length() + 1; i++) {
            if (txt.substring(i, i + pat.length()).equals(pat)){
                x[i] = true;
            }
        }
        return x;
    }




}
