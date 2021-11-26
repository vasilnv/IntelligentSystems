package is.hw2;

import org.junit.Test;

import static org.junit.Assert.*;

public class NQueensSolverTest {

    @Test
    public void getColWithQueenWithMaxConfs() {
        int n = 4;
        int[] board = {3,2,2,0};
        int[] ld = {1,0,1,1,0,0,1};
        int[] rd = {0,0,0,3,1,0,0};
        int[] rows = {1,0,2,1};

        NQueensSolver solver = new NQueensSolver(n);
        assertEquals(solver.getColWithQueenWithMaxConfs(board, rows, ld, rd), 1);
    }

    @Test
    public void getRowWithMinConfs() {
        int n = 4;
        int[] board = {3,1,2,1};
        int[] ld = {1,0,0,2,0,1,0};
        int[] rd = {0,0,1,1,2,0,0};
        int[] rows = {0,2,1,1};

        NQueensSolver solver = new NQueensSolver(n);
        assertEquals(solver.getRowWithMinConfs(0, board, rows, ld, rd), 3);
    }
}