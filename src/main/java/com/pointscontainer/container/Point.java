package com.pointscontainer.container;

public class Point {
    public int data;
    Point next;

    public Point(int data) {
        this.data = data;
        this.next = null;
    }
}