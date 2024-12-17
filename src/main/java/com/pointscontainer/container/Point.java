package com.pointscontainer.container;

public class Point {
    public double x;
    public double y;
    Point next;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
        this.next = null;
    }

    public double magnitude(){
        return Math.sqrt(x*x + y*x);
    }
}