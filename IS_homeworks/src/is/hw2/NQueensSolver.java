package is.hw2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueensSolver {
    private final int n;
    private int[] board;
    private boolean isSolved = false;
    private int[] rows;
    private int[] rd;
    private int[] ld;

    public NQueensSolver(int n) {
        this.n = n;
        init();
    }

    public int[] init() {
        System.out.println("Initializing board...");
        this.board = new int[this.n];
        for (int i = 0; i < n; i++) {
            this.board[i] = -1;
        }
        this.rows = new int[n];
        this.rd = new int[2 * n - 1];
        this.ld = new int[2 * n - 1];

        int row;
        for (int col = 0; col < n; col++)
        {
            row = getRowWithMinConfs(col, board, rows, ld, rd);
            board[col] = row;
            rows[row]++;
            ld[col - row + n - 1]++;
            rd[col + row]++;
        }
        printBoard();
        return board;

    }

    public void printBoard() {
        if (n < 10) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[j] == i) {
                        System.out.print("* ");
                    } else {
                        System.out.print("_ ");
                    }
                }
                System.out.println();
            }
        }
    }

    public boolean solve(int maxSteps) {
        for (int i = 0; i < maxSteps; i ++) {
            int col = getColWithQueenWithMaxConfs(board, rows, ld, rd);
            if (isSolved) {
                System.out.println("board: " + Arrays.toString(this.board));
                System.out.println("rows: " + Arrays.toString(this.rows));
                System.out.println("ld: " + Arrays.toString(this.ld));
                System.out.println("rd: " + Arrays.toString(this.rd));
                return true;
            }
            int row = getRowWithMinConfs(col, board, rows, ld, rd);
            int prevRow = board[col];
            board[col] = row;
            rows[prevRow]--;
            ld[col - prevRow + n - 1]--;
            rd[col + prevRow]--;

            //increase number of queens for new position
            rows[row]++;
            ld[col - row + n - 1]++;
            rd[col + row]++;

        }
        return isSolved;
    }

    public int getColWithQueenWithMaxConfs(int[] board, int[] rows, int[] ld, int[] rd) {
        int max = Integer.MIN_VALUE;
        List<Integer> colsWithTheSameNumberOfConflicts = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int row = board[i];
            int numOfConflicts = rows[row] + ld[i - row + n - 1] + rd[i + row];
            if (numOfConflicts == max) {
                colsWithTheSameNumberOfConflicts.add(i);
            } else if (numOfConflicts > max) {
                max = numOfConflicts;
                colsWithTheSameNumberOfConflicts = new ArrayList<>();
                colsWithTheSameNumberOfConflicts.add(i);
            }
        }
        if (max == 3) {
            isSolved = true;
        }
        int pos = (int) (Math.random() * colsWithTheSameNumberOfConflicts.size());
        return colsWithTheSameNumberOfConflicts.get(pos);
    }

    public int getRowWithMinConfs(int col, int[] board, int[] rows, int[] ld, int[] rd) {
        List<Integer> rowsWithMinConflicts = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int numOfConflicts = rows[i] + ld[col - i + n - 1] + rd[col + i];
            if (board[col] == i) {
                numOfConflicts -= 3;
            }
            if (numOfConflicts == min) {
                rowsWithMinConflicts.add(i);
            } else if (numOfConflicts < min) {
                rowsWithMinConflicts = new ArrayList<>();
                rowsWithMinConflicts.add(i);
                min = numOfConflicts;
            }
        }
        int pos = (int) (Math.random() * rowsWithMinConflicts.size());
        return rowsWithMinConflicts.get(pos);
    }

}
