package is.hw1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class IDAStarPuzzleSolver {
    static long startTime;
    private int steps = 0;

    public int idaStar(Node initNode, Node finalNode) {
        Stack<Node> path = new Stack<>();

        startTime = System.currentTimeMillis();
        int threshold = finalNode.manhattanDistanceNodes(initNode);
        path.push(initNode);

        while (true) {
            steps = 0;
            int temp = search(path, 0, finalNode, threshold);
            threshold++;
            if (temp == 0) {
                return steps;
            }
        }
    }

    private int search(Stack<Node> path, int g, Node finalNode, int threshold) {
        Node currentNode = path.peek();
        int f = g + finalNode.manhattanDistanceNodes(currentNode);
        if (f > threshold) return f;
        if (currentNode.manhattanDistanceNodes(finalNode) == 0) {
            long timeMillis = System.currentTimeMillis() - startTime;

            System.out.println(steps);
            for (Node n : path) {
                if (n.getMovement() != null) {
                    System.out.println(n.getMovement());
                }
            }
            System.out.println("Process finished for " + String.format("%.2f", (timeMillis / 1000.00)));
            return 0;
        }
        int min = Integer.MAX_VALUE;
        List<Node> children = getChildren(currentNode);
        for (Node child : children) {
//            if (!path.contains(child)) {
                path.push(child);
                steps++;
                int t = search(path, g + 1, finalNode, threshold);
                if (t == 0) return 0;
                if (t < min) min = t;
                path.pop();
                steps--;
//            }
        }
        return min;
    }

    public List<Node> getChildren(Node parent) {
        List<Node> children = new ArrayList<>();
        if (parent.checkUp() && (parent.getMovement() == null || !parent.getMovement().equals("down"))) {
            int[][] stateUp = parent.moveUp();
            Node nodeUp = new Node();
            nodeUp.setState(stateUp);
            nodeUp.setNumRows(parent.getNumRows());
            nodeUp.setMovement("up");

//            nodeUp.setParent(parent);
            children.add(nodeUp);
        }
        if (parent.checkDown() && (parent.getMovement() == null || !parent.getMovement().equals("up"))) {
            int[][] stateDown = parent.moveDown();
            Node nodeDown = new Node();
            nodeDown.setState(stateDown);
            nodeDown.setNumRows(parent.getNumRows());
            nodeDown.setMovement("down");

//            nodeDown.setParent(parent);
            children.add(nodeDown);
        }
        if (parent.checkLeft() && (parent.getMovement() == null || !parent.getMovement().equals("right"))) {
            int[][] stateLeft = parent.moveLeft();
            Node nodeLeft = new Node();
            nodeLeft.setState(stateLeft);
            nodeLeft.setNumRows(parent.getNumRows());
            nodeLeft.setMovement("left");

//            nodeLeft.setParent(parent);
            children.add(nodeLeft);
        }
        if (parent.checkRight() && (parent.getMovement() == null || !parent.getMovement().equals("left"))) {
            int[][] stateRight = parent.moveRight();
            Node nodeRight = new Node();
            nodeRight.setState(stateRight);
            nodeRight.setNumRows(parent.getNumRows());
            nodeRight.setMovement("right");

//            nodeRight.setParent(parent);
            children.add(nodeRight);
        }
        return children;
    }


}
