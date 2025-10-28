package lab21;

import org.junit.Test;

import static org.junit.Assert.*;

public class MyCollectionsTest {

    @Test
    public void testSortWithComparable() {
        Integer[] arr = {4, 2, 1, 5, 3};
        MyCollections.sort(arr);
        Integer[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, arr);
    }

    @Test
    public void testSortWithComparator() {
        Integer[] arr = {4, 2, 1, 5, 3};
        MyCollections.sort(arr, new IntegerAscendingComparator());
        Integer[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, arr);
    }
}
