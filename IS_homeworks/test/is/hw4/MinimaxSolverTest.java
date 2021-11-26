package is.hw4;

import org.junit.Test;

import static org.junit.Assert.*;

public class MinimaxSolverTest {

    @Test
    public void minimaxDecision() {
        String[][] board = {{"X", "X", "-"},
                {"O", "O", "-"},
                {"X", "O", "X"}};
        MinimaxSolver solver = new MinimaxSolver();
        int[] solution = solver.minimaxDecision(board, "O");
        assertEquals(solution[0], 1);
        assertEquals(solution[1], 2);
    }
}