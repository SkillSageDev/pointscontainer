package com.pointscontainer;

import java.util.Iterator;

import com.pointscontainer.container.Point;
import com.pointscontainer.container.PointsContainer;

public class App {
    static void display(Iterator<Point> it) {
        int i = 1;
        while (it.hasNext()) {
            Point point = it.next();
            System.out.println("p" + i + ": " + "(" + point.x + ", " + point.y + ")");
            i++;
        }
    }

    public static void main(String[] args) {
        Point p1 = new Point(1, 2);
        Point p2 = new Point(3, 4);
        Point p3 = new Point(5, 6);
        PointsContainer l1 = new PointsContainer();
        PointsContainer l2 = new PointsContainer(2);
        System.out.println(l2.maxSize);
        System.out.println(l2.size);
        l1.add(p3);
        l1.insert(p1, 0);
        l2.add(p3);
        l2.insert(p1, 0);
        System.out.println(l1.search(p2));
        // l2.insert(p1, 0);
        // l2.add(p3);
        // l2.insert(p2, 1);
        System.out.println("after insert");
        Iterator<Point> it = l1.iterator();
        Iterator<Point> itL = l2.iterator();
        display(itL);
    }
}
