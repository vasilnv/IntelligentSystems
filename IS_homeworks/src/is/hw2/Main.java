package is.hw2;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();

        long start = System.currentTimeMillis();
        NQueensSolver solver = new NQueensSolver(n);

        while (!solver.solve(2 * n / 3)) {
            solver.init();
        }
        long testDuration = System.currentTimeMillis() - start;
        System.out.println("Process finished for " + String.format("%.2f", (testDuration / 1000.00)) + " seconds");
        solver.printBoard();
    }
}
