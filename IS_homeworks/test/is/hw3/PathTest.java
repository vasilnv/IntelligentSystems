package is.hw3;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

import static org.junit.Assert.*;

public class PathTest {
    @Test
    public void crossoverTwoPathsTest() {
        TravellingSalesmanSolver solver = new TravellingSalesmanSolver(6);
        Path[] population = solver.initRandomPopulation();
        Point[] crossover = population[0].crossover(population[1]).getCities();
        assertEquals(population[0].crossover(population[1]).getCities().length, population[0].getCities().length);
        for (Point p : crossover) {
            System.out.println("Point x:" + p.getX() + " y:" + p.getY());
        }
        System.out.println();
        Point[] population0Cities =population[0].getCities();
        Point[] population1Cities =population[1].getCities();
        System.out.println("Population 0:");
        Arrays.stream(population0Cities).forEach(p -> System.out.println("Point x:" + p.getX() + " y:" + p.getY()));
        System.out.println("Population 1:");
        Arrays.stream(population1Cities).forEach(p -> System.out.println("Point x:" + p.getX() + " y:" + p.getY()));

    }

    @Test
    public void calculatePathCostTest() {
        Point p1 = new Point(1,1);
        Point p2 = new Point(4,5);
        Point p3 = new Point(7, 5);
        Point[] path = new Point[3];
        path[0] = p1;
        path[1] = p2;
        path[2] = p3;
        Path p = new Path(path);
        assertEquals(8, p.calcPathCost(), 0.1);
    }

    @Test
    public void calculatePathCostHarderTest() {
        Point p1 = new Point(0.190032E-03,-0.285946E-03);
        Point p2 = new Point(168.521,31.4012);
        Point p3 = new Point(112.198, -110.561);
        Point p4 = new Point(69.4331,-246.780);
        Point p5 = new Point(-27.0206,-282.758);
        Point p6 = new Point(179.933, -318.031);
        Point p7 = new Point(217.343,-447.089);
        Point p8 = new Point(335.751,-269.577);
        Point p9 = new Point(320.350, -160.900);
        Point p10 = new Point(306.320,-108.090);
        Point p11 = new Point(383.458,-0.608756E-03);
        Point p12 = new Point(492.671, -131.563);

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
        Path p = new Path(path);
        assertEquals(1595.738522033024, p.calcPathCost(), 0.1);
    }

    @Test
    public void twoPointCrossoverTwoPathsTest() {
        TravellingSalesmanSolver solver = new TravellingSalesmanSolver(6);
        Path[] population = solver.initRandomPopulation();
        Point[] crossover = population[0].twoPointCrossover(population[1]).getCities();
        assertEquals(population[0].twoPointCrossover(population[1]).getCities().length, population[0].getCities().length);
        for (Point p : crossover) {
            System.out.println("Point x:" + p.getX() + " y:" + p.getY());
        }
        System.out.println();
        Point[] population0Cities =population[0].getCities();
        Point[] population1Cities =population[1].getCities();
        System.out.println("Population 0:");
        Arrays.stream(population0Cities).forEach(p -> System.out.println("Point x:" + p.getX() + " y:" + p.getY()));
        System.out.println("Population 1:");
        Arrays.stream(population1Cities).forEach(p -> System.out.println("Point x:" + p.getX() + " y:" + p.getY()));

    }


}