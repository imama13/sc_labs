package recursion;

import java.util.ArrayList;
import java.util.List;

public class BinarySearch {

    // //////////////// INTEGER ARRAY /////////////////
    
    /**
     * Recursive binary search for integers.
     *
     * @param arr    The sorted array of integers.
     * @param target The target integer to search for.
     * @param left   The left index of the search range.
     * @param right  The right index of the search range.
     * @return The index of the target if found, or -1 if not found.
     */
    public static int binarySearchRecursive(int[] arr, int target, int left, int right) {
        if (left > right) {
            return -1; // Base case: search range is empty
        }

        int mid = left + (right - left) / 2;
        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] > target) {
            return binarySearchRecursive(arr, target, left, mid - 1);
        } else {
            return binarySearchRecursive(arr, target, mid + 1, right);
        }
    }

    /**
     * Find all occurrences of an integer target in a sorted array.
     *
     * @param arr    The sorted array of integers.
     * @param target The target integer.
     * @return A list of all indices of the target.
     */
    public static List<Integer> findAllOccurrences(int[] arr, int target) {
        List<Integer> indices = new ArrayList<>();
        int index = binarySearchRecursive(arr, target, 0, arr.length - 1);

        if (index == -1) {
            return indices; // Target not found
        }

        // Find all occurrences on the left
        int left = index - 1;
        while (left >= 0 && arr[left] == target) {
            indices.add(left);
            left--;
        }

        // Add the main index
        indices.add(index);

        // Find all occurrences on the right
        int right = index + 1;
        while (right < arr.length && arr[right] == target) {
            indices.add(right);
            right++;
        }

        indices.sort(Integer::compareTo);
        return indices;
    }

    // ////////////////////// STRING ARRAY ////////////////////////
    
    /**
     * Recursive binary search for strings.
     *
     * @param arr    The sorted array of strings.
     * @param target The target string to search for.
     * @param left   The left index of the search range.
     * @param right  The right index of the search range.
     * @return The index of the target if found, or -1 if not found.
     */
    public static int binarySearchRecursive(String[] arr, String target, int left, int right) {
        if (left > right) {
            return -1; // Base case: search range is empty
        }

        int mid = left + (right - left) / 2;
        if (arr[mid].equals(target)) {
            return mid;
        } else if (arr[mid].compareTo(target) > 0) {
            return binarySearchRecursive(arr, target, left, mid - 1);
        } else {
            return binarySearchRecursive(arr, target, mid + 1, right);
        }
    }

    /**
     * Find all occurrences of a string target in a sorted array.
     *
     * @param arr    The sorted array of strings.
     * @param target The target string.
     * @return A list of all indices of the target.
     */
    public static List<Integer> findAllOccurrences(String[] arr, String target) {
        List<Integer> indices = new ArrayList<>();
        int index = binarySearchRecursive(arr, target, 0, arr.length - 1);

        if (index == -1) {
            return indices; // Target not found
        }

        // Find all occurrences on the left
        int left = index - 1;
        while (left >= 0 && arr[left].equals(target)) {
            indices.add(left);
            left--;
        }

        // Add the main index
        indices.add(index);

        // Find all occurrences on the right
        int right = index + 1;
        while (right < arr.length && arr[right].equals(target)) {
            indices.add(right);
            right++;
        }

        indices.sort(Integer::compareTo);
        return indices;
    }

    // /////////////////////// MAIN METHOD ///////////////////////////////

    public static void main(String[] args) {
        // Integer array test
        int[] intArray = {1, 3, 3, 5, 7, 9, 9, 9};
        int intTarget = 9;
        System.out.println("Integer Array:");
        System.out.println("Index of " + intTarget + ": " + binarySearchRecursive(intArray, intTarget, 0, intArray.length - 1));
        System.out.println("All indices of " + intTarget + ": " + findAllOccurrences(intArray, intTarget));

        // String array test
        String[] stringArray = {"apple", "banana", "cherry", "cherry", "date", "fig", "fig"};
        String stringTarget = "cherry";
        System.out.println("\nString Array:");
        System.out.println("Index of \"" + stringTarget + "\": " + binarySearchRecursive(stringArray, stringTarget, 0, stringArray.length - 1));
        System.out.println("All indices of \"" + stringTarget + "\": " + findAllOccurrences(stringArray, stringTarget));
    }
}

