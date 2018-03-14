package SEP2017;

/**
 * Project name: HomeWork
 * Created by pavel on 28.09.2017.
 * Finished
 */
public class Selection {

    public static Object[] Sort(Object[] objects){

        Node[] array = new Node[objects.length];
        Object[] other = objects;                                       //copy

        for (int i = 0; i < array.length; i++) {
            array[i] = new Node(i, objects[i]);                         //make comparable array
        }


        for (int i = 0; i < array.length-1; i++) {

            int min = i;
            if (array[min].type.equals("int")) {                        //compere type with equal type inside one array

                for (int j = i + 1; j < array.length; j++) {

                    if (array[j].numvalue < array[min].numvalue &&
                            array[j].type.equals(array[min].type)) {    //compere type with equal type
                        min = j;
                    }
                }

            }else {

                for (int j = i + 1; j < array.length; j++) {

                    if (array[j].numvalue < array[min].numvalue &&
                            array[j].type.equals(array[min].type)) {    //compere type with equal type
                        min = j;
                    }
                }
            }

            Object o1 = other[i];
            other[i] = other[min];                              //swap nodes
            other[min] = o1;


            Object o = array[i];
            array[i] = array[min];                              //swap objects
            array[min] = (Node) o;

        }

        return other;
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
