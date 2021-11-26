package is.hw4;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class MinimaxSolver {

    public int[] minimaxDecision(String[][] board, String computerSymbol) {
        int bestVal = -11;
        int bestRow = -1, bestCol = -1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].equals("-")) {
                    board[i][j] = computerSymbol;
                    Node node = new Node(board);
                    int moveVal = minimax(-11, 11, node, false, computerSymbol);
//                    System.out.println(moveVal + ",");
                    board[i][j] = "-";
                    if (moveVal > bestVal) {
                        bestRow = i;
                        bestCol = j;
                        bestVal = moveVal;
                    }
                }
            }
        }
        int[] res = new int[2];
        res[0] = bestRow;
        res[1] = bestCol;
        return res;
    }

    private int minimax(int a, int b, Node node, boolean isMax, String computerSymbol) {
        String playerSymbol = computerSymbol.equals("X") ? "O" : "X";
        int score = node.utility(playerSymbol);
        if (score > 0) {
            return score;
        } else if (score < 0) {
            return score;
        }
        if (node.terminalTest()) {
            return 0;
        }

        String[][] board = node.getBoard();
        if (isMax) {
            int best = -11;
            for (int i = 0; i<3; i++) {
                for (int j = 0; j<3; j++) {
                    if (board[i][j].equals("-")) {
                        board[i][j] = computerSymbol;
                        Node newNode = new Node(board);
                        best = max( best, minimax(best, b, newNode, !isMax, computerSymbol) );
                        board[i][j] = "-";
                        if (best >= b) {
                            return best;
                        }
                    }
                }
            }
            return best;
        } else {
            int best = 11;
            for (int i = 0; i<3; i++) {
                for (int j = 0; j<3; j++) {
                    if (board[i][j].equals("-")) {
                        board[i][j] = playerSymbol;
                        Node newNode = new Node(board);
                        best = min( best, minimax(a, best, newNode, !isMax, computerSymbol) );
                        board[i][j] = "-";
                        if (best <= a) {
                            return best;
                        }
                    }
                }
            }
            return best;
        }
    }
}
