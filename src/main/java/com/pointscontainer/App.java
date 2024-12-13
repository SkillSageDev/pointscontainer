package com.pointscontainer;

import java.util.Iterator;

import com.pointscontainer.container.Point;
import com.pointscontainer.container.PointsContainer;
public class App {
    public static void main(String[] args) {
        PointsContainer unLimitedList = new PointsContainer();
        PointsContainer limitedList = new PointsContainer(2);
        Point p1 = new Point(3);
        Point p2 = new Point(5);
        unLimitedList.insert(p1, 0);
        unLimitedList.insert(p2, 0);
        limitedList.insert(p1, 0);
        limitedList.insert(p2, 0);
        limitedList.insert(p2, 0);
        Iterator<Point> iterator = unLimitedList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().data);
        }
        System.out.println(unLimitedList.size);
        System.out.println(limitedList.size);
    }
}
