package lab21;

import static org.junit.Assert.*;
import org.junit.Test;

public class MyCollectionsTest {

    @Test
    public void testSortWithComparable() {
        Integer[] arr = {5, 1, 4, 2, 3};
        MyCollections.sort(arr);
        Integer[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, arr);
    }

    @Test
    public void testSortWithComparator() {
        Integer[] arr = {5, 1, 4, 2, 3};
        MyCollections.sort(arr, new IntegerAscendingComparator());
        Integer[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, arr);
    }
}
