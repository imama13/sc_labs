package recursion;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DigitSumTest {

    // Create an instance of the DigitSum class to test
    DigitSum digitSum = new DigitSum();

    @Test
    public void testSumOfDigitsPositive() {
        // Test with a positive number
        assertEquals(6, digitSum.sumOfDigits(123), "Sum of digits for 123 should be 6");
    }

    @Test
    public void testSumOfDigitsNegative() {
        // Test with a negative number
        assertEquals(15, digitSum.sumOfDigits(-456), "Sum of digits for -456 should be 15");
    }

    @Test
    public void testSumOfDigitsZero() {
        // Test with 0
        assertEquals(0, digitSum.sumOfDigits(0), "Sum of digits for 0 should be 0");
    }

    @Test
    public void testSumOfDigitsLargeNumber() {
        // Test with a large number
        assertEquals(45, digitSum.sumOfDigits(987654321), "Sum of digits for 987654321 should be 45");
    }

    @Test
    public void testSumOfDigitsLargeNegativeNumber() {
        // Test with a large negative number
        assertEquals(45, digitSum.sumOfDigits(-987654321), "Sum of digits for -987654321 should be 45");
    }

    @Test
    public void testSumOfDigitsSingleDigit() {
        // Test with a single-digit number
        assertEquals(7, digitSum.sumOfDigits(7), "Sum of digits for 7 should be 7");
    }

}
