package recursion;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ParserTest {

    // Create an instance of the Parser class to test
    Parser parser = new Parser();

   
    @Test
    public void testEvaluateExpressionWithMultiplicationOnly() {
        // Test a multiplication-only expression
        assertEquals(15, parser.evaluateExpression("5*3"), "Expected result for 5*3 is 15");
    }

    @Test
    public void testEvaluateExpressionWithDivision() {
        // Test an expression with division
        assertEquals(2, parser.evaluateExpression("8/4"), "Expected result for 8/4 is 2");
    }


    @Test
    public void testEvaluateExpressionWithAdditionAndSubtraction() {
        // Test an expression with both addition and subtraction
        assertEquals(10, parser.evaluateExpression("5+3+2"), "Expected result for 5+3+2 is 10");
    }


    @Test
    public void testEvaluateExpressionWithZero() {
        // Test an expression that evaluates to zero
        assertEquals(0, parser.evaluateExpression("5 - 5"), "Expected result for 5 - 5 is 0");
    }
}
