package is.hw1;

import java.util.*;

public class Main {

    public static int[][] generateFinalMatrix(int emptyBlockPosition, int numRows) {
        int[][] finalMatrix = new int[numRows][numRows];
        int cnt = 0;
        int num = 0;
        if (emptyBlockPosition == -1) {
            emptyBlockPosition = numRows * numRows - 1;
        }
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numRows; j++) {
                if (cnt != emptyBlockPosition) {
                    cnt++;
                    num++;
                    finalMatrix[i][j] = num;
                } else {
                    cnt++;
                }
            }
        }

        return finalMatrix;
    }



    public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
//        System.out.println("Enter a number of blocks:");
        int numBlocks = sc.nextInt();
        sc.nextLine();

//        System.out.println("Enter the position of the empty block in the end:");
        int emptyFinalPosition = sc.nextInt();
        sc.nextLine();
        int numOfRows = (int) Math.sqrt(numBlocks + 1);
        int[][] initialMatrix = new int[numOfRows][numOfRows];

//        System.out.println("Enter the initial matrix:");
        for (int i = 0; i < numOfRows; i++) {
            String line = sc.nextLine();
            String[] filledRow = line.split(" ");
            int[] row = new int[numOfRows];
            for (int j = 0; j < numOfRows; j++) {
                row[j] = Integer.parseInt(filledRow[j]);
            }
            initialMatrix[i] = row;
        }

        Node initNode = new Node();
        initNode.setNumRows(numOfRows);
        initNode.setState(initialMatrix);

        int[][] finalMatrix = generateFinalMatrix(emptyFinalPosition, numOfRows);
        sc.close();
//        initNode.printMatrix();
        Node finalNode = new Node();
        finalNode.setState(finalMatrix);
        finalNode.setNumRows(numOfRows);
//        System.out.println("----------------Goal State--------------------");
//        finalNode.printMatrix();
//        System.out.println("----------------------------------------------");
        IDAStarPuzzleSolver solver = new IDAStarPuzzleSolver();
        solver.idaStar(initNode, finalNode);
    }

}
