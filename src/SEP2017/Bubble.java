package SEP2017;

/**
 * Project name: HomeWork
 * Created by pavel on 27.09.2017.
 * Finished
 */
public class Bubble {

    public static Object[] Sort(Object[] objects){

        Node[] array = new Node[objects.length];
        Object[] other = objects;                                       //copy

        for (int i = 0; i < array.length; i++) {
            array[i] = new Node(i, objects[i]);                         //make comparable array
        }

        for (int i = array.length - 1; i > 0; i--) {

            for (int j = 0; j < i; j++) {

                if (array[j].numvalue > array[j + 1].numvalue) {        //compere by value

                    if (array[j].type.equals(array[j + 1].type)){       //if types are equal, then swap their id

                        int id = array[j].id;
                        array[j].id = array[j + 1].id;                  //swap id
                        array[j + 1].id = id;

                        Object o = other[array[j].id];
                        other[array[j].id] = other[array[j + 1].id];    //swap nodes by id in copied array
                        other[array[j + 1].id] = o;

                    }

                    Object o = array[j];
                    array[j] = array[j + 1];                            //swap objects
                    array[j + 1] = (Node) o;

                }
            }
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

