package SEP2017;

/**
 * Project name: HomeWork
 * Created by pavel on 28.09.2017.
 * Finished
 */
public class Quick {

    public static Object[] Sort(Object[] objects){

        Node[] array = new Node[objects.length];
        Object[] other = new Object[objects.length];                                        //copy
        Boolean[] mask = new Boolean[objects.length];

        for (int i = 0; i < array.length; i++) {
            array[i] = new Node(i, objects[i]);          //make comparable array
            mask[i] = array[i].type.equals("int");
        }


        array = doSort(array, 0, array.length - 1);


        LinkedQueue intQueue = new LinkedQueue();
        LinkedQueue charQueue = new LinkedQueue();

        for (int i = 0; i < array.length; i++) {  //restore the order using the mask
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


    private static Node[] doSort(Node[] array, int start, int end) {  //sorting
        if (start >= end)
            return array;
        int i = start, j = end;
        int cur = i - (i - j) / 2;

        while (i < j) {

            while (i < cur && (array[i].numvalue <= array[cur].numvalue) ) {
                i++;
            }
            while (j > cur && (array[j].numvalue >= array[cur].numvalue)) {
                j--;
            }
            if (i < j) {


                Object temp = array[i];                              //swap two abject from different sides of cur by value
                array[i] = array[j];
                array[j] = (Node) temp;


                if (i == cur)
                    cur = j;
                else if (j == cur)
                    cur = i;
            }
        }



        doSort(array, start, cur);                                   //sort left part
        doSort(array, cur+1, end);                             //sort right part

        return array;
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
