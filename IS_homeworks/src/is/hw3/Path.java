package is.hw3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Path {
    Point[] cities;
    Random r = new Random();


    public Point[] getCities() {
        return cities;
    }

    public Path(Point[] cities) {
        this.cities = cities;
    }

    public Point getCity(int i) {
        return cities[i];
    }

    public double calcPathCost() {
        double sum = 0;
        for (int i = 0; i < cities.length - 1; i++) {
            sum += cities[i].calcDistance(cities[i + 1]);
        }
        return sum;
    }

    public Path crossover(Path other) {
        List<Point> newPath = new ArrayList<>();
        int indexToCopyCurrent = r.nextInt(this.cities.length);
        for (int i = 0; i < indexToCopyCurrent; i++) {
            newPath.add(this.cities[i]);
        }
        Point[] otherCities = other.getCities();
        for (int i = 0; i < this.cities.length; i++) {
            if (!newPath.contains(otherCities[i])) {
                newPath.add(otherCities[i]);
            }
        }
        return new Path(newPath.toArray(new Point[0]));
    }

    public Path twoPointCrossover(Path other) {
        Point[] newPath = new Point[other.getCities().length];
        int index1 = r.nextInt(this.cities.length);
        int index2 = r.nextInt(this.cities.length);
//        System.out.println("index1: " + index1);
//        System.out.println("index2: " + index2);
        int min = Math.min(index1, index2);
        int max = Math.max(index1, index2);
        Point[] otherCities = other.getCities();
        for (int i = min; i <= max; i ++) {
            newPath[i] = otherCities[i];
        }
        int i = max + 1, j = max + 1;
        while(i < other.getCities().length) {
            if (!Arrays.asList(newPath).contains(this.cities[j])) {
                newPath[i] = this.cities[j];
                i++;
                j++;
            } else {
                j++;
            }
            if (j == this.cities.length) {
                j=0;
            }
        }
        i = 0;
        j = 0;
        while(i < min) {
            if (!Arrays.asList(newPath).contains(this.cities[j])) {
                newPath[i] = this.cities[j];
                i++;
                j++;
            } else {
                j++;
            }
        }
        return new Path(newPath);
    }

}
