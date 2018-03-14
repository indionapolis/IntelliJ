package IBC2017;

/**
 * Project name: HomeWork
 * Created by pavel on 18.08.17.
 */
public class Main2 {
    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        System.out.println(a.getA());
        System.out.println(a.getB());
        System.out.println(a.getC());
        System.out.println(b.getB());
        System.out.println(((C) b).getC());
        b.makeNoys();
        a.sum();
    }
}
