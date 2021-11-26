package is.hw1;

import is.hw1.Node;
import org.junit.Test;

import static org.junit.Assert.*;

public class NodeTest {

    @org.junit.Test
    public void checkUp() {
        Node testNode = new Node();
        testNode.setNumRows(3);
        int[][] state0 = {{-1,-1,-1},
                         {-1,-1,-1},
                         {-1,-1,0}};
        testNode.setState(state0);
        assertFalse(testNode.checkUp());
        int[][] state1 = {{-1,-1,-1},
                {-1,-1,-1},
                {0,-1,-1}};
        testNode.setState(state1);
        assertFalse(testNode.checkUp());
        int[][] state2 = {{-1,-1,-1},
                {-1,-1,0},
                {-1,-1,-1}};
        testNode.setState(state2);
        assertTrue(testNode.checkUp());

        int[][] state3 = {{-1,0,-1},
                {-1,-1,-1},
                {-1,-1,-1}};
        testNode.setState(state3);
        assertTrue(testNode.checkUp());

        int[][] state4 = {{0,-1,-1},
                {-1,-1,-1},
                {-1,-1,-1}};
        testNode.setState(state4);
        assertTrue(testNode.checkUp());
    }

    @org.junit.Test
    public void checkDown() {
        Node testNode = new Node();
        testNode.setNumRows(3);
        int[][] state0 = {{-1,-1,-1},
                {-1,-1,-1},
                {-1,-1,0}};
        testNode.setState(state0);
        assertTrue(testNode.checkDown());

        int[][] state1 = {{-1,-1,-1},
                {-1,-1,-1},
                {0,-1,-1}};
        testNode.setState(state1);
        assertTrue(testNode.checkDown());

        int[][] state2 = {{-1,-1,-1},
                {-1,-1,0},
                {-1,-1,-1}};
        testNode.setState(state2);
        assertTrue(testNode.checkDown());

        int[][] state3 = {{-1,0,-1},
                {-1,-1,-1},
                {-1,-1,-1}};
        testNode.setState(state3);
        assertFalse(testNode.checkDown());

        int[][] state4 = {{0,-1,-1},
                {-1,-1,-1},
                {-1,-1,-1}};
        testNode.setState(state4);
        assertFalse(testNode.checkDown());

    }

    @org.junit.Test
    public void checkLeft() {
        Node testNode = new Node();
        testNode.setNumRows(3);

        int[][] state0 = {{-1,-1,-1},
                {-1,-1,-1},
                {-1,-1,0}};
        testNode.setState(state0);
        assertFalse(testNode.checkLeft());

        int[][] state1 = {{-1,-1,-1},
                {-1,-1,-1},
                {0,-1,-1}};
        testNode.setState(state1);
        assertTrue(testNode.checkLeft());

        int[][] state2 = {{-1,-1,-1},
                {-1,-1,0},
                {-1,-1,-1}};
        testNode.setState(state2);
        assertFalse(testNode.checkLeft());

        int[][] state3 = {{-1,0,-1},
                {-1,-1,-1},
                {-1,-1,-1}};
        testNode.setState(state3);
        assertTrue(testNode.checkLeft());

        int[][] state4 = {{0,-1,-1},
                {-1,-1,-1},
                {-1,-1,-1}};
        testNode.setState(state4);
        assertTrue(testNode.checkLeft());
    }

    @org.junit.Test
    public void checkRight() {
        Node testNode = new Node();
        testNode.setNumRows(3);

        int[][] state0 = {{-1,-1,-1},
                {-1,-1,-1},
                {-1,-1,0}};
        testNode.setState(state0);
        assertTrue(testNode.checkRight());

        int[][] state1 = {{-1,-1,-1},
                {-1,-1,-1},
                {0,-1,-1}};
        testNode.setState(state1);
        assertFalse(testNode.checkRight());

        int[][] state2 = {{-1,-1,-1},
                {-1,-1,0},
                {-1,-1,-1}};
        testNode.setState(state2);
        assertTrue(testNode.checkRight());

        int[][] state3 = {{-1,0,-1},
                {-1,-1,-1},
                {-1,-1,-1}};
        testNode.setState(state3);
        assertTrue(testNode.checkRight());

        int[][] state4 = {{0,-1,-1},
                {-1,-1,-1},
                {-1,-1,-1}};
        testNode.setState(state4);
        assertFalse(testNode.checkRight());
    }

    @Test
    public void getNumberPosition() {
        Node testNode = new Node();
        int[][] state = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                state[i][j] = 3*i + j;
            }
        }
        testNode.setState(state);
        testNode.setNumRows(3);
        assertEquals(testNode.getNumberPosition(0)[0], 0);
        assertEquals(testNode.getNumberPosition(0)[1], 0);

        assertEquals(testNode.getNumberPosition(2)[0], 0);
        assertEquals(testNode.getNumberPosition(2)[1], 2);

        assertEquals(testNode.getNumberPosition(5)[0], 1);
        assertEquals(testNode.getNumberPosition(5)[1], 2);

        assertEquals(testNode.getNumberPosition(6)[0], 2);
        assertEquals(testNode.getNumberPosition(6)[1], 0);

        assertEquals(testNode.getNumberPosition(6)[0], 2);
        assertEquals(testNode.getNumberPosition(6)[1], 0);

        assertEquals(testNode.getNumberPosition(7)[0], 2);
        assertEquals(testNode.getNumberPosition(7)[1], 1);

        assertEquals(testNode.getNumberPosition(8)[0], 2);
        assertEquals(testNode.getNumberPosition(8)[1], 2);

    }

    @Test
    public void manhattanDistanceTest() {
        Node n1 = new Node();
        n1.setNumRows(3);
        int[][] state1 = new int[3][3];
        state1[1][2] = 5;
        state1[0][0] = 1;
        state1[1][1] = 2;
        state1[2][0] = 3;
        n1.setState(state1);

        Node n2 = new Node();
        n2.setNumRows(3);
        int[][] state2 = new int[3][3];
        state2[0][1] = 5;
        state2[2][2] = 1;
        state2[1][2] = 2;
        state2[0][2] = 3;
        n2.setState(state2);

        assertEquals(n1.manhattanDistance(n2, 5), 2);
        assertEquals(n1.manhattanDistance(n2, 1), 4);
        assertEquals(n1.manhattanDistance(n2, 2), 1);
        assertEquals(n1.manhattanDistance(n2, 3), 4);
        assertEquals(n1.manhattanDistance(n2, 3), n2.manhattanDistance(n1, 3));

    }

    @Test
    public void moveUpTest() {
        Node n1 = new Node();
        n1.setNumRows(3);
        int[][] state1 = {{1,2,3},
                          {4,0,5},
                          {6,7,8}};
        n1.setState(state1);
        int[][] stateUp = {{1,2,3},
                           {4,7,5},
                           {6,0,8}};
        assertEquals(n1.moveUp(), stateUp);
        assertEquals(n1.getState(), state1);
    }

    @Test
    public void moveDownTest() {
        Node n1 = new Node();
        n1.setNumRows(3);
        int[][] state1 = {{1,2,3},
                          {4,0,5},
                          {6,7,8}};
        n1.setState(state1);
        int[][] stateDown = {{1,0,3},
                             {4,2,5},
                             {6,7,8}};
        assertEquals(n1.moveDown(), stateDown);
    }

    @Test
    public void moveLeftTest() {
        Node n1 = new Node();
        n1.setNumRows(3);
        int[][] state1 = {{1,2,3},
                          {4,0,5},
                          {6,7,8}};
        n1.setState(state1);
        int[][] stateLeft = {{1,2,3},
                             {4,5,0},
                             {6,7,8}};
        assertEquals(n1.moveLeft(), stateLeft);
    }

    @Test
    public void moveRightTest() {
        Node n1 = new Node();
        n1.setNumRows(3);
        int[][] state1 = {{1,2,3},
                          {4,0,5},
                          {6,7,8}};
        n1.setState(state1);
        int[][] stateRight = {{1,2,3},
                              {0,4,5},
                              {6,7,8}};
        assertEquals(n1.moveRight(), stateRight);
        assertEquals(n1.getState(), state1);
    }

    @Test
    public void manhattanDisanceNodesTest() {
        Node n1 = new Node();
        n1.setNumRows(3);
        int[][] state1 = {{7,2,4},
                          {5,0,6},
                          {8,3,1}};
        n1.setState(state1);
        int[][] state2 = {{1,2,3},
                              {4,5,6},
                              {7,8,0}};
        Node n2 = new Node();
        n2.setNumRows(3);
        n2.setState(state2);
        assertEquals(n1.manhattanDistanceNodes(n2), 14);
        assertEquals(n1.manhattanDistanceNodes(n2), n2.manhattanDistanceNodes(n1));
    }

}