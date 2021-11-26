package is.hw1;

import org.junit.Test;

import static org.junit.Assert.*;

public class IDAStarPuzzleSolverTest {

    @Test
    public void idaStarTest1() {
        int[][] initState = {{1,2,3},
                             {4,5,6},
                             {0,7,8}};
        Node initNode = new Node();
        initNode.setState(initState);
        initNode.setNumRows(3);

        int[][] goalState = {{1,2,3},
                {4,5,6},
                {7,8,0}};
        Node goalNode = new Node();
        goalNode.setState(goalState);
        goalNode.setNumRows(3);

        IDAStarPuzzleSolver solver = new IDAStarPuzzleSolver();
        assertEquals(solver.idaStar(initNode, goalNode), 2);

    }

    @Test
    public void idaStarTest2() {
        int[][] initState = {{0,3,2},
                {4,5,6},
                {7,8,1}};
        Node initNode = new Node();
        initNode.setState(initState);
        initNode.setNumRows(3);

        int[][] goalState = {{1,2,3},
                {4,5,6},
                {7,8,0}};
        Node goalNode = new Node();
        goalNode.setState(goalState);
        goalNode.setNumRows(3);

        IDAStarPuzzleSolver solver = new IDAStarPuzzleSolver();
        assertEquals(solver.idaStar(initNode, goalNode), 20);

    }

    @Test
    public void idaStarTest3() {
        int[][] initState = {{6,5,3},
                {2,4,8},
                {7,0,1}};
        Node initNode = new Node();
        initNode.setState(initState);
        initNode.setNumRows(3);

        int[][] goalState = {{1,2,3},
                {4,5,6},
                {7,8,0}};
        Node goalNode = new Node();
        goalNode.setState(goalState);
        goalNode.setNumRows(3);

        IDAStarPuzzleSolver solver = new IDAStarPuzzleSolver();
        assertEquals(solver.idaStar(initNode, goalNode), 21);

    }

    @Test
    public void idaStarTest4() {
        int[][] initState = {{2,3,6},
                {1,5,8},
                {4,7,0}};
        Node initNode = new Node();
        initNode.setState(initState);
        initNode.setNumRows(3);

        int[][] goalState = {{1,2,3},
                {4,5,6},
                {7,8,0}};
        Node goalNode = new Node();
        goalNode.setState(goalState);
        goalNode.setNumRows(3);

        IDAStarPuzzleSolver solver = new IDAStarPuzzleSolver();
        assertEquals(solver.idaStar(initNode, goalNode), 8);

    }

    @Test
    public void idaStarTest5() {
        int[][] initState = {{8,6,7},
                {2,5,4},
                {3,0,1}};
        Node initNode = new Node();
        initNode.setState(initState);
        initNode.setNumRows(3);

        int[][] goalState = {{1,2,3},
                {4,5,6},
                {7,8,0}};
        Node goalNode = new Node();
        goalNode.setState(goalState);
        goalNode.setNumRows(3);

        IDAStarPuzzleSolver solver = new IDAStarPuzzleSolver();
        assertEquals(solver.idaStar(initNode, goalNode), 31);

    }

    @Test
    public void idaStarTest6() {
        int[][] initState = {{6,4,7},
                {8,5,0},
                {3,2,1}};
        Node initNode = new Node();
        initNode.setState(initState);
        initNode.setNumRows(3);

        int[][] goalState = {{1,2,3},
                {4,5,6},
                {7,8,0}};
        Node goalNode = new Node();
        goalNode.setState(goalState);
        goalNode.setNumRows(3);

        IDAStarPuzzleSolver solver = new IDAStarPuzzleSolver();
        assertEquals(solver.idaStar(initNode, goalNode), 31);

    }

    @Test
    public void idaStarTest7() {
        int[][] initState = {{3,2,1,4},
                {0,5,6,7},
                {8,9,10,11},
                {12,13,14,15}};
        Node initNode = new Node();
        initNode.setState(initState);
        initNode.setNumRows(4);

        int[][] goalState = {{1,2,3,0},
                {4,5,6,7},
                {8,9,10,11},
                {12,13,14,15}};
        Node goalNode = new Node();
        goalNode.setState(goalState);
        goalNode.setNumRows(4);

        IDAStarPuzzleSolver solver = new IDAStarPuzzleSolver();
        assertEquals(solver.idaStar(initNode, goalNode), 24);

    }

//    @Test
    public void idaStarTest8Slow() {
        int[][] initState = {{3,2,1,4},
                {0,5,6,7},
                {8,9,10,11},
                {12,13,15,14}};
        Node initNode = new Node();
        initNode.setState(initState);
        initNode.setNumRows(4);

        int[][] goalState = {{1,2,3,4},
                {5,6,7,8},
                {9,10,11,12},
                {13,14,15,0}};
        Node goalNode = new Node();
        goalNode.setState(goalState);
        goalNode.setNumRows(4);

        IDAStarPuzzleSolver solver = new IDAStarPuzzleSolver();
        assertEquals(solver.idaStar(initNode, goalNode), 43);

    }

    @Test
    public void idaStarTest9() {
        int[][] initState = {{5,6,3,4},
                {8,0,1,15},
                {10,7,2,11},
                {12,9,14,13}};
        Node initNode = new Node();
        initNode.setState(initState);
        initNode.setNumRows(4);

        int[][] goalState = {{1,2,3,4},
                {5,6,7,8},
                {9,10,11,12},
                {13,14,15,0}};
        Node goalNode = new Node();
        goalNode.setState(goalState);
        goalNode.setNumRows(4);

        IDAStarPuzzleSolver solver = new IDAStarPuzzleSolver();
        assertEquals(solver.idaStar(initNode, goalNode), 40);

    }

    @Test
    public void idaStarTest10() {
        int[][] initState = {{10,5,8,11},
                {2,12,9,6},
                {13,1,0,15},
                {4,7,3,14}};
        Node initNode = new Node();
        initNode.setState(initState);
        initNode.setNumRows(4);

        int[][] goalState = {{1,2,3,4},
                {5,6,7,8},
                {9,10,11,12},
                {13,14,15,0}};
        Node goalNode = new Node();
        goalNode.setState(goalState);
        goalNode.setNumRows(4);

        IDAStarPuzzleSolver solver = new IDAStarPuzzleSolver();
        assertEquals(solver.idaStar(initNode, goalNode), 50);

    }

//    @Test
//    public void idaStarTest11Slow() {
//        int[][] initState = {{9,5,1,12},
//                {10,0,11,13},
//                {3,7,14,6},
//                {2,8,15,4}};
//        Node initNode = new Node();
//        initNode.setState(initState);
//        initNode.setNumRows(4);
//
//        int[][] goalState = {{1,2,3,4},
//                {5,6,7,8},
//                {9,10,11,12},
//                {13,14,15,0}};
//        Node goalNode = new Node();
//        goalNode.setState(goalState);
//        goalNode.setNumRows(4);
//
//        IDAStarPuzzleSolver solver = new IDAStarPuzzleSolver();
//        //idk what it is
//        assertEquals(solver.idaStar(initNode, goalNode), 50);
//
//    }

//    @Test
//    public void idaStarTest12Slow() {
//        int[][] initState = {{14,15,8,12},
//                {10,11,9,13},
//                {2,6,5,1},
//                {3,7,4,0}};
//        Node initNode = new Node();
//        initNode.setState(initState);
//        initNode.setNumRows(4);
//
//        int[][] goalState = {{1,2,3,4},
//                {5,6,7,8},
//                {9,10,11,12},
//                {13,14,15,0}};
//        Node goalNode = new Node();
//        goalNode.setState(goalState);
//        goalNode.setNumRows(4);
//
//        IDAStarPuzzleSolver solver = new IDAStarPuzzleSolver();
//        //idk what it is
//        assertEquals(solver.idaStar(initNode, goalNode), 50);
//
//    }

//    @Test
//    public void idaStarTest13Slow() {
//        int[][] initState = {{14,12,9,1},
//                {8,2,4,11},
//                {0,13,3,7},
//                {5,6,10,15}};
//        Node initNode = new Node();
//        initNode.setState(initState);
//        initNode.setNumRows(4);
//
//        int[][] goalState = {{1,2,3,4},
//                {5,6,7,8},
//                {9,10,11,12},
//                {13,14,15,0}};
//        Node goalNode = new Node();
//        goalNode.setState(goalState);
//        goalNode.setNumRows(4);
//
//        IDAStarPuzzleSolver solver = new IDAStarPuzzleSolver();
//        //idk what it is
//        assertEquals(solver.idaStar(initNode, goalNode), 50);
//
//    }

}