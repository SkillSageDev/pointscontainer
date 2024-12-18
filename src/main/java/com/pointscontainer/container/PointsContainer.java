package com.pointscontainer.container;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.pointscontainer.Iterator.EmptyIterator;
import com.pointscontainer.errors.CapacityExceededException;
import com.pointscontainer.errors.EmptyContainerException;

public class PointsContainer implements ContainerOps<Point> {

    // unlimited list
    Point head;
    public int size;

    // limted list
    List<Point> list;
    public int maxSize;

    public PointsContainer() {
        this.head = null;
        this.size = 0;
    }

    public PointsContainer(int capacity) {
        this.list = new ArrayList<>(capacity);
        this.maxSize = capacity;
        this.size = 0;
    }

    @Override
    public void insert(Point element, int index) {
        Point temp = head;
        int counter = 0;

        if (list != null) {
            if (index > maxSize || index < 0) {
                throw new IndexOutOfBoundsException(index);
            } else if (size >= maxSize) {
                throw new CapacityExceededException("Error: list is already full!, no space for more.");
            }
            list.add(index, element);
            size++;

        } else {
            // unlimited list
            if (index > size || index < 0) {
                throw new IndexOutOfBoundsException(index);
            }
            if (index == 0) {
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
    }

    @Override
    public void delete(Point element) {
        Point temp = head;

        if (head == null && list == null) {
            throw new EmptyContainerException("The list is empty dude!");
        }

        else if (list != null) {
            list.remove(element);
            size--;
        }

        else if (head != null) {
            if (head == element) {
                head = head.next;
                size--;
            } else {
                while (temp != null) {
                    if (temp.next == element) {
                        temp.next = temp.next.next;
                        size--;
                    }
                    temp = temp.next;
                }
            }

        }
    }

    @Override
    public Point deleteAt(int index) {

        if (list == null && head == null) {
            throw new EmptyContainerException("it is empty dude!");
        }

        if (index > maxSize || index < 0) {
            throw new IndexOutOfBoundsException(index);
        }
        // limited list
        if (list != null || head != null) {
            if (list != null) {
                size--;
                return list.remove(index);
            } else {
                Point temp = head;
                int counter = 0;
                if (index == 0) {
                    Point p1 = head;
                    head = head.next;
                    return p1;
                }
                while (temp != null) {
                    if (counter == index - 1) {
                        Point point = temp.next;
                        temp.next = temp.next.next;
                        size--;
                        return point;
                    }
                    temp = temp.next;
                    counter++;
                }
            }
        }
        throw new EmptyContainerException("Error: list is empty.");
        // unlimited list

    }

    @Override
    public void add(Point element) {
        if (list != null) {
            if (size >= maxSize) {
                throw new CapacityExceededException("Error: list is full!, no space to add more.");
            }
            list.add(element);
            size++;
        } else {
            Point temp = head;
            if (head == null) {
                head = element;
            } else {
                while (temp.next != null) {
                    temp = temp.next;
                }
                temp.next = element;
                size++;
            }
        }
    }

    @Override
    public boolean search(Point element) {
        if (list != null) {
            for (Point point : list) {
                if (point.equals(element)) {
                    return true;
                }
            }
            return false;
        } else {
            Point temp = head;
            while (temp != null) {
                if (temp.equals(element)) {
                    return true;
                } else {
                    temp = temp.next;
                }
            }
            return false;
        }
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
        List<Point> newList = new ArrayList<>(newCapacity);
        list = newList;
        maxSize = newCapacity;
        // don't forget
        if (head == null) {
            throw new UnsupportedOperationException("You tried to increase the capcaity on unlimited list, stupid!");
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Point> iterator() {
        if (head != null || list != null) {
            // unlimited
            if (head != null) {
                Point temp = head;
                List<Point> array = new ArrayList<>();
                while (temp != null) {
                    array.add(temp);
                    temp = temp.next;
                }
                return array.iterator();

            } else {
                // limited
                return list.iterator();
            }
        } else {
            return new EmptyIterator();
        }
    }
}