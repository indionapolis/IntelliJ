package IBC2017;

import IBC2017.Coupling;

import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Coupling coupling = new Coupling();
        //coupling.printCoupling();

        int[] intersection;

        while ((intersection = coupling.findIntersection()) != null) {
            coupling.swap(intersection[0], intersection[1]);
            System.out.println("Swapped segments " + intersection[0] + " & " + intersection[1]);
        }

        //coupling.printCoupling();
        double[] set  = coupling.findseparator();
        for (int i = 0; i < 4; i++) {
            System.out.print(set[i] + " ");
        }
    }
}