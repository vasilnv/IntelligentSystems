package is.hw3;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();


        TravellingSalesmanSolver solver = new TravellingSalesmanSolver(n);
        solver.solve();
    }
}
