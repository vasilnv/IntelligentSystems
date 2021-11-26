package is.hw4;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Enter who will start the game, X is for you, any other symbol is for the computer: ");
        Scanner sc = new Scanner(System.in);
        String whoStarts = sc.nextLine();
        boolean playerTurn = whoStarts.equals("X");
        String playerSymbol = "O";
        if (playerTurn) {
            playerSymbol = "X";
        }
        String computerSymbol = playerSymbol.equals("X") ? "O" : "X";
        System.out.println(playerTurn);
        MinimaxSolver solver = new MinimaxSolver();
        Node node = new Node();
        while (!node.terminalTest() && !node.doWeHaveWinner(playerSymbol)) {
            String[][] currBoard = node.getBoard();
            if (playerTurn) {
                System.out.println("Enter your row and column: ");
                int row = sc.nextInt();
                sc.nextLine();
                int column = sc.nextInt();
                sc.nextLine();
                row--; //user starts counting from 1 and not from zero
                column--;
                if (!currBoard[row][column].equals("-")) {
                    System.out.println("Please enter an empty cell.");
                    continue;
                }
                currBoard[row][column] = playerSymbol;
                node.setBoard(currBoard);
                node.printBoard();
            } else {
                int[] computerMove = solver.minimaxDecision(node.getBoard(), computerSymbol);
                currBoard[computerMove[0]][computerMove[1]] = computerSymbol;
                node.setBoard(currBoard);
                node.printBoard();
            }
            playerTurn = !playerTurn;
        }
        if (!node.doWeHaveWinner(playerSymbol)) {
            System.out.println("It's a draw!");
        } else if (node.utility(playerSymbol) > 0) {
            System.out.println("You lost!");
            System.out.println("Your score: " + -node.utility(playerSymbol));
        } else {
            System.out.println("You win!");
            System.out.println("Your score: " + -node.utility(playerSymbol));
        }
    }
}
