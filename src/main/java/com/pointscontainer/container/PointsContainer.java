package com.pointscontainer.container;

import com.pointscontainer.errors.*;
import com.pointscontainer.iterators.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class PointsContainer implements ContainerOps<Point> {

    // Linked list for the unlimited case
    private Point head;
    private int size;

    // ArrayList for the limited case
    private List<Point> list;
    private int maxSize;

    // Constructor for the unlimited list
    public PointsContainer() {
        this.head = null;
        this.size = 0;
        this.maxSize = -1;  // No size limit
    }

    // Constructor for the limited list
    public PointsContainer(int capacity) {
        this.list = new ArrayList<>(capacity);
        this.maxSize = capacity;
        this.size = 0;
    }

    @Override
    public void insert(Point element, int index) {
        if (list != null) {  // Limited list (ArrayList)
            if (index < 0 || index > size) {
                throw new IndexOutOfBoundsException("Invalid index.");
            } else if (size >= maxSize) {
                throw new CapacityExceededException("Error: list is full!");
            }
            list.add(index, element);
        } else {  // Unlimited list (Linked list)
            if (index < 0 || index > size) {
                throw new IndexOutOfBoundsException("Invalid index.");
            }

            Point newNode = new Point(element.getX(), element.getY());
            if (index == 0) {
                newNode.setNext(head);
                head = newNode;
            } else {
                Point temp = head;
                for (int i = 0; i < index - 1; i++) {
                    temp = temp.getNext();
                }
                newNode.setNext(temp.getNext());
                temp.setNext(newNode);
            }
        }
        size++;
    }

    @Override
    public void delete(Point element) {
        if (list != null) {  // Limited list (ArrayList)
            if (!list.remove(element)) {
                throw new PointNotFoundException("Point " + element + " not found.");
            }
        } else {  // Unlimited list (Linked list)
            if (head == null) throw new EmptyContainerException("The container is empty.");

            if (head.equals(element)) {
                head = head.getNext();
                size--;
                return;
            }

            Point temp = head;
            while (temp.getNext() != null) {
                if (temp.getNext().equals(element)) {
                    temp.setNext(temp.getNext().getNext());
                    size--;
                    return;
                }
                temp = temp.getNext();
            }
            throw new PointNotFoundException("Point " + element + " not found.");
        }
        size--;
    }

    @Override
    public Point deleteAt(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Invalid index.");

        if (list != null) {  // Limited list (ArrayList)
            return list.remove(index);
        } else {  // Unlimited list (Linked list)
            if (head == null) throw new EmptyContainerException("The list is empty!");

            if (index == 0) {
                Point deleted = head;
                head = head.getNext();
                size--;
                return deleted;
            }

            Point temp = head;
            for (int i = 0; i < index - 1; i++) {
                temp = temp.getNext();
            }
            Point deleted = temp.getNext();
            temp.setNext(deleted.getNext());
            size--;
            return deleted;
        }
    }

    @Override
    public void add(Point element) {
        if (list != null) {  // Limited list (ArrayList)
            if (size >= maxSize) throw new CapacityExceededException("Error: list is full!");
            list.add(element);
        } else {  // Unlimited list (Linked list)
            Point newNode = new Point(element.getX(), element.getY());
            if (head == null) {
                head = newNode;
            } else {
                Point temp = head;
                while (temp.getNext() != null) {
                    temp = temp.getNext();
                }
                temp.setNext(newNode);
            }
        }
        size++;
    }

    @Override
    public boolean search(Point element) {
        if (list != null) {
            if (!list.contains(element)) {
                throw new PointNotFoundException("Point " + element + " not found.");
            }
            return true;
        } else {
            Point temp = head;
            while (temp != null) {
                if (temp.equals(element)) {
                    return true;
                }
                temp = temp.getNext();
            }
            throw new PointNotFoundException("Point " + element + " not found.");
        }
    }

    @Override
    public int indexOf(Point element) {
        if (list != null) {
            return list.indexOf(element);
        } else {
            Point temp = head;
            int index = 0;
            while (temp != null) {
                if (temp.equals(element)) return index;
                temp = temp.getNext();
                index++;
            }
            return -1;
        }
    }

    @Override
    public int lastIndexOf(Point element) {
        if (list != null) {
            return list.lastIndexOf(element);
        } else {
            Point temp = head;
            int index = 0;
            int lastIndex = -1;
            while (temp != null) {
                if (temp.equals(element)) lastIndex = index;
                temp = temp.getNext();
                index++;
            }
            return lastIndex;
        }
    }

    @Override
    public Point get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Invalid index.");
        if (list != null) {
            return list.get(index);
        } else {
            Point temp = head;
            for (int i = 0; i < index; i++) {
                temp = temp.getNext();
            }
            return temp;
        }
    }

    @Override
    public void sort() {
        if (size == 0) throw new EmptyContainerException("The container is empty!");

        if (list != null) {  // For limited list (ArrayList)
            Collections.sort(list);
        } else {  // For unlimited list (linked list)
            List<Point> pointsArray = new ArrayList<>();
            Point temp = head;
            int i = 0;
            while (temp != null) {
                pointsArray.add(temp);
                temp = temp.getNext();
            }

            Collections.sort(pointsArray);

            head = pointsArray.get(0);
            temp = head;
            for (i = 1; i < pointsArray.size(); i++) {
                temp.setNext(pointsArray.get(i));
                temp = temp.getNext();
            }
            temp.setNext(null);
        }
    }

    @Override
    public void growCapacity(int newCapacity) {
        if (maxSize == -1) throw new UnsupportedOperationException("Cannot grow unlimited container.");
        if (newCapacity <= maxSize) throw new IllegalArgumentException("New capacity must be greater than temp capacity.");
        maxSize = newCapacity;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Point> iterator() {
        if (size == 0) return new EmptyIterator();
        if (list != null) return list.iterator();
        return new PointsIterator(head);
    }
}
