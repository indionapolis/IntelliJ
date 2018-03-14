package SEP2017;

/**
 * Project name: HomeWork
 * Created by pavel on 29.09.2017.
 * Finished
 */
public class Merge {
    private static Node[] array;

    public static Object[] Sort(Object[] objects){

        array = new Node[objects.length];
        Object[] other = new Object[objects.length];     //copy
        Boolean[] mask = new Boolean[objects.length];    //for restore the order

        for (int i = 0; i < array.length; i++) {
            array[i] = new Node(i, objects[i]);          //make comparable array
            mask[i] = array[i].type.equals("int");
        }


        sort(array);                                     //sorting


        LinkedQueue intQueue = new LinkedQueue();
        LinkedQueue charQueue = new LinkedQueue();

        for (int i = 0; i < array.length; i++) {         //restore the order using the mask
            if (array[i].type.equals("int")){
                intQueue.add(array[i].value);
            }else {
                charQueue.add(array[i].value);
            }
        }
        for (int i = 0; i < mask.length; i++) {
            if (mask[i]){                        //<----------
                other[i] = intQueue.remove();
            }else {
                other[i] = charQueue.remove();
            }
        }


        return other;
    }



    public static void sort(Node[] a)
    {
        Node[] buffer = new Node[a.length];
        mergeSort(a, buffer,  0,  a.length - 1);
    }


    private static void mergeSort(Node[] array, Node[] buffer, int left, int right)
    {
        if( left < right )
        {
            int mid = (left + right) / 2;
            mergeSort(array, buffer, left, mid);                    //left part
            mergeSort(array, buffer, mid + 1, right);           //right part
            merge(array, buffer, left, mid + 1, right);        //merge
        }
    }


    private static void merge(Node[] array, Node[] buffer, int left, int right, int rightEnd)
    {
        int leftEnd = right - 1;
        int i = left;
        int length = rightEnd - left + 1;

        while(left <= leftEnd && right <= rightEnd) {    //fill buffer
            if (array[left].numvalue < array[right].numvalue){
                buffer[i++] = array[left++];
            } else {
                buffer[i++] = array[right++];
            }
        }

        while(left <= leftEnd) {
            buffer[i++] = array[left++];                    // Copy rest of first half
        }

        while(right <= rightEnd) {                          // Copy rest of right half
            buffer[i++] = array[right++];
        }

        for(int j = 0; j < length; j++, rightEnd--) {       // Copy buffer back
            array[rightEnd] = buffer[rightEnd];
        }
    }


    public static class Node{
        int id;         //original id
        Object value;
        int numvalue;   //value for compering
        String type;    //type for compering

        public Node(int id, Object object){
            this.value = object;
            this.id = id;
            if (String.valueOf(value).charAt(0) > 64) {                //if value - char
                this.type = "char";
                this.numvalue = (int) String.valueOf(value).charAt(0);
            }else {                                                    //if value - int
                this.type = "int";
                this.numvalue = Integer.parseInt(String.valueOf(value));
            }
        }
    }
}

