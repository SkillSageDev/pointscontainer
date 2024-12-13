package com.pointscontainer.container;
import java.util.Iterator;

/**
 * A generic interface for managing a container of elements with various
 * operations
 * such as insertion, deletion, searching, and sorting.
 * 
 * @param <Type> the type of elements maintained by this container.
 */
public interface ContainerOps<Type> {

    /**
     * Inserts the specified element at the specified position in this container.
     * 
     * @param element the element to insert.
     * @param index   the position at which the element is to be inserted.
     * @throws IndexOutOfBoundsException if the index is out of range.
     * @throws CapacityExceededException if the container has reached its maximum
     *                                   capacity.
     */
    void insert(Type element, int index);

    /**
     * Deletes the specified element from this container.
     * 
     * @param element the element to be deleted.
     * @throws EmptyContainerException if the container is empty.
     */
    void delete(Type element);

    /**
     * Deletes the element at the specified position in this container and returns
     * it.
     * 
     * @param index the index of the element to delete.
     * @return the element previously at the specified position.
     * @throws IndexOutOfBoundsException if the index is out of range.
     * @throws EmptyContainerException if the container is empty.
     */
    Type deleteAt(int index);

    /**
     * Adds the specified element to the end of this container.
     * 
     * @param element the element to be added.
     * @throws CapacityExceededException. if the container has reached its maximum
     *                               capacity.
     */
    void add(Type element);

    /**
     * Searches for the specified element in this container.
     * 
     * @param element the element to search for.
     * @return {@code true} if the container contains the specified element,
     *         {@code false} otherwise.
     */
    boolean search(Type element);

    /**
     * Returns the index of the first occurrence of the specified element in this
     * container.
     * 
     * @param element the element to search for.
     * @return the index of the first occurrence of the specified element, or
     *         {@code -1} if not found.
     */
    int indexOf(Type element);

    /**
     * Returns the index of the last occurrence of the specified element in this
     * container.
     * 
     * @param element the element to search for.
     * @return the index of the last occurrence of the specified element, or
     *         {@code -1} if not found.
     */
    int lastIndexOf(Type element);

    /**
     * Returns the element at the specified position in this container.
     * 
     * @param index the index of the element to return.
     * @return the element at the specified position.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    Type get(int index);

    /**
     * Sorts the elements in this container in ascending order.
     * @throws EmptyContainerException if the container is empty.
     */
    void sort();

    /**
     * Grows the capacity of this container to the specified value.
     * This method is only applicable if the container is initialized with a fixed
     * capacity.
     * 
     * @param newCapacity the new capacity of the container.
     * @throws UnsupportedOperationException if the container does not have a fixed
     *                                       capacity.
     */
    void growCapacity(int newCapacity);

    /**
     * Returns the number of elements in this container.
     * 
     * @return the size of the container.
     */
    int size();

    /**
     * Returns an iterator over the elements in this container.
     * 
     * @return an {@code Iterator} over the elements in this container.
     */
    Iterator<Type> iterator();
}
