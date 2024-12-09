package recursion;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Arrays;

public class BinarySearchTest {

    @Test
    public void testBinarySearchIntFound() {
        int[] arr = {1, 3, 5, 7, 9};
        assertEquals(2, BinarySearch.binarySearchRecursive(arr, 5, 0, arr.length - 1));
    }

    @Test
    public void testBinarySearchIntNotFound() {
        int[] arr = {1, 3, 5, 7, 9};
        assertEquals(-1, BinarySearch.binarySearchRecursive(arr, 4, 0, arr.length - 1));
    }

    @Test
    public void testFindAllOccurrencesInt() {
        int[] arr = {1, 3, 3, 3, 5};
        assertEquals(Arrays.asList(1, 2, 3), BinarySearch.findAllOccurrences(arr, 3));
    }

    @Test
    public void testBinarySearchStringFound() {
        String[] arr = {"apple", "banana", "cherry", "date"};
        assertEquals(2, BinarySearch.binarySearchRecursive(arr, "cherry", 0, arr.length - 1));
    }

    @Test
    public void testBinarySearchStringNotFound() {
        String[] arr = {"apple", "banana", "cherry", "date"};
        assertEquals(-1, BinarySearch.binarySearchRecursive(arr, "fig", 0, arr.length - 1));
    }

    @Test
    public void testFindAllOccurrencesString() {
        String[] arr = {"apple", "banana", "cherry", "cherry", "date"};
        assertEquals(Arrays.asList(2, 3), BinarySearch.findAllOccurrences(arr, "cherry"));
    }
}
