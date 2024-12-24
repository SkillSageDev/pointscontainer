package com.pointscontainer.container;

public class Point implements Comparable<Point> {
    private double x;
    private double y;
    private Point next;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Point getNext() {
        return next;
    }

    public void setNext(Point next) {
        this.next = next;
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
        this.next = null;
    }

    public double magnitude() {
        return Math.sqrt(x * x + y * x);
    }

    @Override
    public int compareTo(Point o) {
        return Double.compare(this.magnitude(), o.magnitude());
    }

    // compared to x and y instead of magnitude cuz 5,3 and 3,5 has same magnitude but they are not same points.
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || (getClass() != obj.getClass()))
            return false;
        Point other = (Point) obj;
        return (Double.compare(this.x, other.x) == 0) && (Double.compare(this.y, other.y) == 0);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}