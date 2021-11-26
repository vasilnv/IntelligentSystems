package is.hw3;

import java.util.*;

public class TravellingSalesmanSolver {
    private static final int POPULATION_SIZE = 1000;
    private static final int NUM_OF_EPOCHS = 100;
    public static final int MUTATION_CHANCE = 80;
    Random r = new Random();

    Point[] allCities;
    int n;

    public Point[] getAllCities() {
        return allCities;
    }

    public void setAllCities(Point[] allCities) {
        this.allCities = allCities;
    }

    public TravellingSalesmanSolver(int n) {
        this.n = n;
        init();
    }

    public Point[] init() {
        this.allCities = new Point[n];
        for (int i = 0; i < n; i ++) {
            int xi = r.nextInt(1000);
            int yi = r.nextInt(1000);
            Point p = new Point(xi, yi);
            this.allCities[i] = p;
        }
        return this.allCities;
    }

    public double solve() {
        Path[] population = initRandomPopulation();
        PriorityQueue<Path> currentPopulation = new PriorityQueue<>(new Comparator<Path>() {
            @Override
            public int compare(Path path, Path t1) {
                return (int) (path.calcPathCost() - t1.calcPathCost());
            }
        });
        currentPopulation.addAll(Arrays.asList(population));
        for (int i = 0; i < NUM_OF_EPOCHS; i++) {
            if (i % 5 == 0) {
                System.out.println(currentPopulation.peek().calcPathCost());
            }
            currentPopulation = (PriorityQueue<Path>) generateCrossover(currentPopulation);
        }
        Point[] allPoints = currentPopulation.peek().getCities();
        System.out.println(currentPopulation.peek().calcPathCost());
//        for (int i = 0; i < n; i++) {
//            System.out.println("Point x: " + allPoints[i].getX() + " y:" + allPoints[i].getY());
//        }
        return currentPopulation.peek().calcPathCost();
    }

    private Path mutate(Path path) {
        Point[] newPath = path.getCities();
        int firstIndex = r.nextInt(newPath.length);
        int secondIndex = r.nextInt(newPath.length);
        Point tmp = newPath[firstIndex];
        newPath[firstIndex] = newPath[secondIndex];
        newPath[secondIndex] = tmp;

        return new Path(newPath);
    }

    private Queue<Path> generateCrossover(Queue<Path> population) {
        int bestPathsNum =  population.size() / 4;
        PriorityQueue<Path> allPaths = new PriorityQueue<>(new Comparator<Path>() {
            @Override
            public int compare(Path path, Path t1) {
                return (int) (path.calcPathCost() - t1.calcPathCost());
            }
        });
        allPaths.addAll(population);
        for (int i = 0; i < bestPathsNum; i+=2) {
           Path current = population.poll();
           Path nextBest = population.poll();
           Path crossover1 = current.twoPointCrossover(nextBest);
            Path crossover2 = nextBest.twoPointCrossover(current);
           if (r.nextInt() < MUTATION_CHANCE) {
               crossover1 = mutate(crossover1);
               crossover2 = mutate(crossover2);
           }
           allPaths.add(crossover1);
           allPaths.add(crossover2);
        }
        PriorityQueue<Path> nextGeneration = new PriorityQueue<>(new Comparator<Path>() {
          @Override
          public int compare(Path path, Path t1) {
              return (int) (path.calcPathCost() - t1.calcPathCost());
          }
        });
        for (int i = 0; i < POPULATION_SIZE; i++) {
            nextGeneration.add(allPaths.poll());
        }
        return nextGeneration;
    }

    public Path[] initRandomPopulation() {
        Path[] paths = new Path[POPULATION_SIZE];
        for (int i = 0; i < POPULATION_SIZE; i++) {
            paths[i] = new Path(initRandomPath(this.allCities));
        }
        return paths;
    }

    public Point[] initRandomPath(Point[] allCities) {
        List<Point> cities = new ArrayList<>(Arrays.asList(allCities));
        Point[] initState = new Point[n];
        int i = 0;
        while (!cities.isEmpty()) {
            int index = r.nextInt(cities.size());
            initState[i] = cities.get(index);
            cities.remove(index);
            i++;
        }
        return initState;
    }


}
