package com.pointscontainer.container;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.pointscontainer.errors.EmptyContainerException;

public class PointsContainer implements ContainerOps<Point> {

    Point head;
    public int size;

    List<Point> list;
    int maxSize;

    public PointsContainer() {
        this.head = null;
        this.size = 0;
    }

    public PointsContainer(int capacity) {
        this.list = new ArrayList<>(capacity);
        this.maxSize = capacity;
    }

    @Override
    public void insert(Point element, int index) {
        Point temp = head;
        int counter = 0;
        if (index > maxSize || index < 0 || size == maxSize) {
            throw new IndexOutOfBoundsException(index);
        }
        if(list != null){
            list.add(index, element);
        }
        if (index == 0) {
            // unlimited list
            element.next = head;
            head = element;
            size++;
        } else {
            // unlimited list
            while (temp != null) {
                if (counter == index - 1) {
                    temp.next = element.next;
                    temp.next = element;
                    size++;
                    break;
                }
                temp = temp.next;
                counter++;
            }
        }
    }

    @Override
    public void delete(Point element) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Point deleteAt(int index) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAt'");
    }

    @Override
    public void add(Point element) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    @Override
    public boolean search(Point element) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'search'");
    }

    @Override
    public int indexOf(Point element) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'indexOf'");
    }

    @Override
    public int lastIndexOf(Point element) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'lastIndexOf'");
    }

    @Override
    public Point get(int index) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public void sort() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sort'");
    }

    @Override
    public void growCapacity(int newCapacity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'growCapacity'");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Point> iterator() {
        if (head != null) {
            Point temp = head;
            List<Point> list = new ArrayList<>();
            while (temp != null) {
                list.add(temp);
                temp = temp.next;
            }
            return list.iterator();

        } else if (!list.isEmpty() || list.size() <= maxSize) {
            return list.iterator();
        } else {
            throw new EmptyContainerException("Error: Container is empty");
        }
    }
}