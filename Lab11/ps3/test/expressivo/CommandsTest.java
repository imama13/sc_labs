/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package expressivo;


import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class CommandsTest {
	@Test
    public void testDifferentiateSimple() {
        Expression expr = Parser.parse("x");
      //Differentiation with respect to x but hard-coded for ease
        Expression derivative = expr.differentiate("x");
        assertEquals(new Value(1), derivative);
    }
	

    @Test
    public void testDifferentiateAddition() {
        Expression expr = Parser.parse("x + y");
        Expression derivative = expr.differentiate("x");
        assertEquals(new Addition(new Value(1), new Value(0)), derivative);
    }


    @Test
    public void testevaluateAddition() {
        Environment env = new Environment();
        env.setVariable("x", 3.0);
        env.setVariable("y", 4.0);
        Expression expr = new Addition(new Variable("x"), new Variable("y"));
        double result = expr.evaluate(env);
        System.out.println("testevaluateAddition "+result);
        assertEquals("Evaluating addition failed", 7.0, result, 0.0001);
    }




    
    @Test
    public void testevaluateVariable() {
        Environment env = new Environment();
        env.setVariable("x", 5.0);
        Expression expr = new Variable("x");
        double result = expr.evaluate(env);
        System.out.println("testevaluateVariable "+result);
        assertEquals("Evaluating a variable failed", 5.0, result, 0.0001);
    }

    
    @Test
    public void testDifferentiateMultiplication() {
        Expression expr = Parser.parse("x * y");
        Expression derivative = expr.differentiate("x");
        assertEquals(
            new Addition(
                new Multiplication(new Value(1), new Variable("y")),
                new Multiplication(new Variable("x"), new Value(0))
            ),
            derivative
        );
    }


    @Test
    public void testevaluateMultiplication() {
        Environment env = new Environment();
        env.setVariable("x", 2.0);
        env.setVariable("y", 3.0);
        Expression expr = new Multiplication(new Variable("x"), new Variable("y"));
        double result = expr.evaluate(env);
        System.out.println("testevaluateMultiplication "+result);
        assertEquals("Evaluating multiplication failed", 6.0, result, 0.0001);
    }


    @Test
    public void testSimplifyComplexExpression() {
        // Set up the environment
        Environment env = new Environment();
        env.setVariable("x", 2.0);  // Set values for variables (if needed)
        env.setVariable("y", 3.0);

        // Parse the expression
        Expression expr = Parser.parse("x * (0 + y)");

        // Evaluate the expression using the environment
        double result = expr.evaluate(env);
        System.out.println("testSimplifyComplexExpression "+result);

        // Check if the simplified result is as expected
        assertEquals("Simplifying a complex expression failed", 6.0, result, 0.0001);
    }
    
}