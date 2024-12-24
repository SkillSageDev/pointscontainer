package com.pointscontainer.iterators;

import com.pointscontainer.container.Point;
import java.util.Iterator;

public class PointsIterator implements Iterator<Point> {
    private Point current;

    public PointsIterator(Point head) {
        this.current = head;
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public Point next() {
        Point temp = current;
        current = current.getNext();
        return temp;
    }
}
