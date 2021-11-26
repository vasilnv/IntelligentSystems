package is.hw4;

public class Node {
    String[][] board = {{"-", "-", "-"}, {"-", "-", "-"}, {"-", "-", "-"}};

    public String[][] getBoard() {
        return board;
    }

    public void setBoard(String[][] board) {
        this.board = board;
    }

    public Node() {
    }

    public Node(String[][] board) {
        this.board = board;
    }

    public void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean terminalTest() {
        for (int i = 0; i < 3; i ++) {
            for (int j = 0; j < 3; j++) {
                if (this.board[i][j].equals("-")){
                    return false;
                }
            }
        }
        return true;
    }

    int utility(String playerSymbol)
    {
        String computerSymbol = playerSymbol.equals("X") ? "O" : "X";
        for (int row = 0; row < 3; row++)
        {
            if (board[row][0].equals(board[row][1]) && board[row][1].equals(board[row][2]))
            {
                if (board[row][0].equals(computerSymbol))
                    return 1 + checkNumOfEmptyCells(board);
                else if (board[row][0].equals(playerSymbol))
                    return -1 - checkNumOfEmptyCells(board);
            }
        }

        for (int col = 0; col < 3; col++)
        {
            if (board[0][col].equals(board[1][col]) && board[1][col].equals(board[2][col]))
            {
                if (board[0][col].equals(computerSymbol))
                    return 1 + checkNumOfEmptyCells(board);

                else if (board[0][col].equals(playerSymbol))
                    return -1 - checkNumOfEmptyCells(board);
            }
        }

        if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]))
        {
            if (board[0][0].equals(computerSymbol))
                return 1 + checkNumOfEmptyCells(board);
            else if (board[0][0].equals(playerSymbol))
                return -1 - checkNumOfEmptyCells(board);
        }

        if (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]))
        {
            if (board[0][2].equals(computerSymbol))
                return 1 + checkNumOfEmptyCells(board);
            else if (board[0][2].equals(playerSymbol))
                return -1 - checkNumOfEmptyCells(board);
        }

        return 0;
    }

    private int checkNumOfEmptyCells(String[][] board) {
        int result = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].equals("-")) {
                    result++;
                }
            }
        }
        return result;
    }

    public boolean doWeHaveWinner(String playerSymbol) {
        int score = utility(playerSymbol);
        return score != 0;
    }
}
