package expressivo;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * JUnit tests for Expression variants: Value, Variable, Addition, Multiplication, and Parser.
 */
public class ExpressionTest {

    /** Test Value: toString, equals, hashCode */
    @Test
    public void testValue() {
        Expression value1 = new Value(1.0);
        Expression value2 = new Value(1.000001);
        Expression value3 = new Value(1.000009);
        Expression value4 = new Value(2.0);

        // Test toString
        assertEquals("1", value1.toString());
        assertEquals("2", value4.toString());

        // Test equals
        assertTrue(value1.equals(value2));
        assertTrue(value2.equals(value3));
        assertFalse(value1.equals(value4));

        // Test hashCode
        assertEquals(value1.hashCode(), value2.hashCode());
        assertNotEquals(value2.hashCode(), value3.hashCode());
        assertNotEquals(value1.hashCode(), value4.hashCode());
    }

    /** Test Variable: toString, equals, hashCode */
    @Test
    public void testVariable() {
        Expression var1 = new Variable("x");
        Expression var2 = new Variable("x");
        Expression var3 = new Variable("y");

        // Test toString
        assertEquals("x", var1.toString());
        assertEquals("y", var3.toString());

        // Test equals
        assertTrue(var1.equals(var2));
        assertFalse(var1.equals(var3));

        // Test hashCode
        assertEquals(var1.hashCode(), var2.hashCode());
        assertNotEquals(var1.hashCode(), var3.hashCode());
    }

    /** Test Addition: toString, equals, hashCode */
    @Test
    public void testAddition() {
        Expression left = new Variable("x");
        Expression right = new Value(2);
        Expression addition1 = new Addition(left, right);
        Expression addition2 = new Addition(left, right);
        Expression addition3 = new Addition(new Value(1), new Variable("y"));

        // Test toString
        assertEquals("x + 2", addition1.toString());
        assertEquals("x + 2", addition2.toString());
        assertEquals("1 + y", addition3.toString());

        // Test equals
        assertTrue(addition1.equals(addition2));
        assertFalse(addition1.equals(addition3));

        // Test hashCode
        assertEquals(addition1.hashCode(), addition2.hashCode());
        assertNotEquals(addition1.hashCode(), addition3.hashCode());
    }

    /** Test Multiplication: toString, equals, hashCode */
    @Test
    public void testMultiplication() {
        Expression left = new Variable("x");
        Expression right = new Variable("y");
        Expression multiplication1 = new Multiplication(left, right);
        Expression multiplication2 = new Multiplication(left, right);
        Expression multiplication3 = new Multiplication(new Value(3), new Value(4));

        // Test toString
        assertEquals("(x)*(y)", multiplication1.toString());
        assertEquals("(x)*(y)", multiplication2.toString());
        assertEquals("(3)*(4)", multiplication3.toString());

        // Test equals
        assertTrue(multiplication1.equals(multiplication2));
        assertFalse(multiplication1.equals(multiplication3));

        // Test hashCode
        assertEquals(multiplication1.hashCode(), multiplication2.hashCode());
        assertNotEquals(multiplication1.hashCode(), multiplication3.hashCode());
    }

    /** Test combinations of Addition and Multiplication */
    @Test
    public void testComplexExpressions() {
        Expression x = new Variable("x");
        Expression y = new Variable("y");
        Expression three = new Value(3);
        Expression two = new Value(2);

        Expression sum = new Addition(x, y); // x + y
        Expression product = new Multiplication(sum, three); // (x + y) * 3
        Expression complex = new Addition(product, two); // (x + y) * 3 + 2

        // Test toString
        assertEquals("(x + y)*(3) + 2", complex.toString());

        // Test equals
        Expression equivalent = new Addition(new Multiplication(new Addition(x, y), three), two);
        assertTrue(complex.equals(equivalent));

        Expression different = new Addition(new Multiplication(new Addition(x, y), two), three);
        assertFalse(complex.equals(different));

        // Test hashCode
        assertEquals(complex.hashCode(), equivalent.hashCode());
        assertNotEquals(complex.hashCode(), different.hashCode());
    }

    /** Test Parser functionality */
    @Test
    public void testParser() {
        // Parsing values
        assertEquals(new Value(3), Parser.parse("3"));
        assertEquals(new Value(2.5), Parser.parse("2.5"));

        // Parsing variables
        assertEquals(new Variable("x"), Parser.parse("x"));
        assertEquals(new Variable("y"), Parser.parse("y"));

        // Parsing addition and multiplication
        assertEquals(new Addition(new Value(1), new Variable("x")), Parser.parse("1 + x"));
        assertEquals(new Multiplication(new Value(2), new Variable("y")), Parser.parse("2 * y"));

        // Parsing complex expressions
        Expression complex = new Addition(
            new Multiplication(new Variable("x"), new Value(3)),
            new Value(2)
        );
        assertEquals(complex, Parser.parse("(x * 3) + 2"));
    }
}