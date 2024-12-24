package com.pointscontainer.container;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.Iterator;

public class PointsContainerTest {

    @Test
    public void testInsert() {
        // Create an unlimited PointsContainer
        PointsContainer unLimitedList = new PointsContainer();

        // Create a limited PointsContainer with a capacity of 2
        PointsContainer limitedList = new PointsContainer(3);

        // Create Point objects
        Point p1 = new Point(3, 4);
        Point p2 = new Point(5, 6);

        // Insert points into the unlimited list
        unLimitedList.insert(p1, 0); // Valid insertion
        unLimitedList.insert(p1, 1); // Valid insertion at the end

        // Insert points into the limited list
        limitedList.insert(p1, 0); // Valid insertion
        limitedList.insert(p2, 1); // Valid insertion at the end

        // Attempt to insert a third point into the limited list
        limitedList.insert(new Point(7, 3), 2); // This should not succeed if the list is limited

        // Check the size of the unlimited list
        assertEquals(2, unLimitedList.size());

        // Check the size of the limited list
        assertEquals(3, limitedList.size()); // Size should still be 2 after trying to insert the third point

        // // Verify the contents of the unlimited list
        Iterator<Point> iterator = unLimitedList.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(5.0, iterator.next().magnitude(), 1);
        assertTrue(iterator.hasNext());
        assertEquals(5, iterator.next().magnitude(), 1);

        // Verify the contents of the limited list
        Iterator<Point> limitedIterator = limitedList.iterator();
        assertTrue(limitedIterator.hasNext());
        assertEquals(5.0, limitedIterator.next().magnitude(), 1);
        assertTrue(limitedIterator.hasNext());
        assertEquals(Math.sqrt(61), limitedIterator.next().magnitude(), 1);
        assertTrue(limitedIterator.hasNext());
        assertEquals(Math.sqrt(58), limitedIterator.next().magnitude(), 1);
        assertFalse(iterator.hasNext());
        
    }
}