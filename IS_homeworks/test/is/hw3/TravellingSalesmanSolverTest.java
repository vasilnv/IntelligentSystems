package is.hw3;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class TravellingSalesmanSolverTest {

    @Test
    public void initRandomPopulationTest() {
        Point p1 = new Point(1,2);
        Point p2 = new Point(3,4);
        Point p3 = new Point(4, 5);
        Point p4 = new Point(1, 3);
        Point p5 = new Point(2, 5);
        Point[] all = new Point[5];
        all[0] = p1;
        all[1] = p2;
        all[2] = p3;
        all[3] = p4;
        all[4] = p5;
        TravellingSalesmanSolver solver = new TravellingSalesmanSolver(5);
        Point[] init = solver.initRandomPath(all);
        Set<Point> allCities = new HashSet<>(Arrays.asList(all));
        Set<Point> initCities = new HashSet<>(Arrays.asList(init));
        assertEquals(allCities.size(), initCities.size());
        assertEquals(5, initCities.size());
    }

    @Test
    public void getPriorityQueueCorrect() {
        TravellingSalesmanSolver solver = new TravellingSalesmanSolver(5);
        Path[] population = solver.initRandomPopulation();
        PriorityQueue<Path> queue = new PriorityQueue<>(new Comparator<Path>() {
            @Override
            public int compare(Path path, Path t1) {
                return (int) (path.calcPathCost() - t1.calcPathCost());
            }
        });
        queue.addAll(Arrays.asList(population));
        while (!queue.isEmpty()) {
            System.out.println(queue.poll().calcPathCost());
        }
    }

    @Test
    public void simpleTest() {
        TravellingSalesmanSolver solver = new TravellingSalesmanSolver(3);
        Point p1 = new Point(1,1);
        Point p2 = new Point(4,5);
        Point p3 = new Point(7, 5);
        Point[] path = new Point[3];
        path[0] = p1;
        path[1] = p2;
        path[2] = p3;
        solver.setAllCities(path);
        assertEquals(8, solver.solve(), 0.1);
    }

    @Test
    public void harderTest() {
        TravellingSalesmanSolver solver = new TravellingSalesmanSolver(12);
        Point p1 = new Point(0.190032E-03,-0.285946E-03);
        Point p2 = new Point(383.458,-0.608756E-03);
        Point p3 = new Point(-27.0206, -282.758);
        Point p4 = new Point(335.751,-269.577);
        Point p5 = new Point(69.4331,-246.780);
        Point p6 = new Point(168.521, 31.4012);
        Point p7 = new Point(320.350,-160.900);
        Point p8 = new Point(179.933,-318.031);
        Point p9 = new Point(492.671, -131.563);
        Point p10 = new Point(112.198,-110.561);
        Point p11 = new Point(306.320,-108.090);
        Point p12 = new Point(217.343, -447.089);

        Point[] path = new Point[12];
        path[0] = p1;
        path[1] = p2;
        path[2] = p3;
        path[3] = p4;
        path[4] = p5;
        path[5] = p6;
        path[6] = p7;
        path[7] = p8;
        path[8] = p9;
        path[9] = p10;
        path[10] = p11;
        path[11] = p12;
        solver.setAllCities(path);
        assertEquals(1595.738522033024, solver.solve(), 0.1);
    }

//            492.671,-131.563
//            112.198,-110.561
//            306.320,-108.090
//            217.343,-447.089
}