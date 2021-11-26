package is.hw1;

import java.util.Arrays;

public class Node {
    int [][] state;
    int numRows;
//    Node parent;
    String movement = null;

    public int[][] getState() {
        return state;
    }

    public void setState(int[][] state) {
        this.state = state;
    }

    public int getNumRows() {
        return numRows;
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
    }

    public String getMovement() {
        return movement;
    }

    public void setMovement(String movement) {
        this.movement = movement;
    }

//    public Node getParent() {
//        return parent;
//    }

//    public void setParent(Node parent) {
//        this.parent = parent;
//    }

    public boolean checkUp() {
        int[] emptyPosArr = getNumberPosition(0);
        int emptyPos = emptyPosArr[0] * this.numRows + emptyPosArr[1];
        return emptyPos / numRows < numRows - 1;
    }

    public boolean checkDown() {
        int[] emptyPosArr = getNumberPosition(0);
        int emptyPos = emptyPosArr[0] * this.numRows + emptyPosArr[1];
        return emptyPos / numRows > 0;
    }

    public boolean checkLeft() {
        int[] emptyPosArr = getNumberPosition(0);
        int emptyPos = emptyPosArr[0] * this.numRows + emptyPosArr[1];
        return emptyPos % numRows < numRows - 1;
    }

    public boolean checkRight() {
        int[] emptyPosArr = getNumberPosition(0);
        int emptyPos = emptyPosArr[0] * this.numRows + emptyPosArr[1];
        return emptyPos % numRows > 0;
    }

    public void printMatrix() {
        for (int i = 0; i < this.numRows; i++) {
            for (int j = 0; j < this.numRows; j++) {
                System.out.print(this.state[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int[] getNumberPosition(int number) {
        int[] res = new int[2];
        for (int i = 0; i < this.numRows; i++) {
            for (int j = 0; j < this.numRows; j++) {
                if (this.state[i][j] == number) {
                    res[0] = i;
                    res[1] = j;
                }
            }
        }
        return res;
    }

    public int manhattanDistance(Node node, int number) {
        int[] currentNodeNumPos = this.getNumberPosition(number);
        int[] otherNodeNumPos = node.getNumberPosition(number);
        return Math.abs(currentNodeNumPos[0] - otherNodeNumPos[0]) + Math.abs(currentNodeNumPos[1] - otherNodeNumPos[1]);
    }

    public int manhattanDistanceNodes(Node node) {
        int result = 0;
        for (int i = 1; i < this.numRows * this.numRows; i++) {
            result += manhattanDistance(node, i);
        }
        return result;
    }

    public int[][] moveUp() {
        int[] emptyPosArr = getNumberPosition(0);
        int numToMove = this.state[emptyPosArr[0] + 1][emptyPosArr[1]];
        int[][] newState = copyState(this.getState());
        newState[emptyPosArr[0]][emptyPosArr[1]] = numToMove;
        newState[emptyPosArr[0] + 1][emptyPosArr[1]] = 0;
        return newState;
    }

    public int[][] moveDown() {
        int[] emptyPosArr = getNumberPosition(0);
        int numToMove = this.state[emptyPosArr[0] - 1][emptyPosArr[1]];
        int[][] newState = copyState(this.getState());
        newState[emptyPosArr[0]][emptyPosArr[1]] = numToMove;
        newState[emptyPosArr[0] - 1][emptyPosArr[1]] = 0;
        return newState;
    }

    public int[][] moveLeft() {
        int[] emptyPosArr = getNumberPosition(0);
        int numToMove = this.state[emptyPosArr[0]][emptyPosArr[1] + 1];
        int[][] newState = copyState(this.getState());
        newState[emptyPosArr[0]][emptyPosArr[1]] = numToMove;
        newState[emptyPosArr[0]][emptyPosArr[1]  + 1] = 0;
        return newState;
    }

    public int[][] moveRight() {
        int[] emptyPosArr = getNumberPosition(0);
        int numToMove = this.state[emptyPosArr[0]][emptyPosArr[1] -1];
        int[][] newState = copyState(this.getState());
        newState[emptyPosArr[0]][emptyPosArr[1]] = numToMove;
        newState[emptyPosArr[0]][emptyPosArr[1] - 1] = 0;
        return newState;
    }

    private int[][] copyState(int[][] state) {
        int[][] copy = new int[numRows][numRows];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numRows; j++) {
                copy[i][j] = state[i][j];
            }
        }
        return copy;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Arrays.deepEquals(state, node.state);
    }

}
