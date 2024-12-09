package recursion;


/**
 * A class that provides a recursive function to compute the sum of digits of a number.
 */
public class DigitSum {

    /**
     * Computes the sum of the digits of a given integer using recursion.
     *
     * @param number The input integer. Can be positive, negative, or zero.
     * @return The sum of the digits of the absolute value of the number.
     */
    public int sumOfDigits(int number) {
        // Convert negative numbers to positive
        number = Math.abs(number);

        // Base case: if the number is reduced to 0, return 0
        if (number == 0) {
            return 0;
        }

        // Recursive case: Add the last digit to the sum of the remaining digits
        return (number % 10) + sumOfDigits(number / 10);
    }

    // Main method for testing the sumOfDigits function
    public static void main(String[] args) {
        DigitSum digitSum = new DigitSum();

        // Test cases
        int[] testNumbers = {123, -456, 0, 987654321, -987654321, 999999999};

        System.out.println("Recursive Sum of Digits:");
        for (int number : testNumbers) {
            System.out.println("Number: " + number + " -> Sum of Digits: " + digitSum.sumOfDigits(number));
        }
    }
}

