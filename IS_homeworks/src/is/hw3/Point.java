package is.hw3;

public class Point {
    double x;
    double y;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double calcDistance(Point p2) {
        return Math.sqrt(Math.abs((this.x - p2.getX()) * (this.x - p2.getX())) + Math.abs((this.y - p2.getY()) * (this.y - p2.getY())));
    }


}
