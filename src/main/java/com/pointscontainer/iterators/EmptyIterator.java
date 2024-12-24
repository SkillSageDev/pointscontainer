package com.pointscontainer.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.pointscontainer.container.Point;

public class EmptyIterator implements Iterator<Point> {

    @Override
    public boolean hasNext() {
        return false; // No elements to iterate over
    }

    @Override
    public Point next() {
        throw new NoSuchElementException("No elements in the iterator");
    }
}
