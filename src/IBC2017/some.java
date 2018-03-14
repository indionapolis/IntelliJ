package sav;

/**
 * Created by pavel on 05.08.17.
 */
public class some {
    public static void main(String[] args) {
        System.out.println(G(5));
    }

    public static int G(int x){
        return F(G(x));
    }
    public static int F(int x){
        return G(F(x)+1);
    }
}
