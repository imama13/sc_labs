package expressivo;

import java.util.Stack;
import java.util.regex.Pattern;

/**
 * Parser class for parsing mathematical expressions and constructing
 * an abstract syntax tree (AST) representation.
 *
 * Parsing logic:
 * - Tokenize the input string into meaningful components (Values, variables, operators, parentheses).
 * - Use two stacks: one for expressions and another for operators.
 * - Build the AST by handling operators and parentheses according to precedence rules.
 */
public class Parser {

    /**
     * Parses a mathematical expression from a string and returns its abstract syntax tree (AST).
     *
     * @param input the input string representing the mathematical expression
     * @return an Expression object representing the AST of the parsed input
     */
    public static Expression parse(String input) {
        String[] tokens = tokenize(input); // Split the input string into tokens
        Stack<Expression> expressions = new Stack<>(); // Stack for partial expressions
        Stack<String> operators = new Stack<>(); // Stack for operators (+, *, etc.)

        for (String token : tokens) {
            if (isValue(token)) {
                // If the token is a Value, create a Value expression and push it to the stack
                expressions.push(new Value(Double.parseDouble(token)));
            } else if (isVariable(token)) {
                // If the token is a variable, create a Variable expression and push it to the stack
                expressions.push(new Variable(token));
            } else if (token.equals("(")) {
                // Push open parentheses onto the operator stack
                operators.push(token);
            } else if (token.equals(")")) {
                // Process operators within parentheses
                while (!operators.isEmpty() && !operators.peek().equals("(")) {
                    expressions.push(applyOperator(operators.pop(), expressions.pop(), expressions.pop()));
                }
                // Pop the open parenthesis from the operator stack
                operators.pop();
            } else if (token.equals("+") || token.equals("*")) {
                // Handle operators by precedence
                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(token)) {
                    expressions.push(applyOperator(operators.pop(), expressions.pop(), expressions.pop()));
                }
                // Push the current operator onto the stack
                operators.push(token);
            }
        }

        // Process any remaining operators
        while (!operators.isEmpty()) {
            expressions.push(applyOperator(operators.pop(), expressions.pop(), expressions.pop()));
        }

        // Return the final expression (AST root)
        return expressions.pop();
    }

    /**
     * Tokenizes the input string into an array of tokens (Values, variables, operators, parentheses).
     *
     * @param input the input string
     * @return an array of string tokens
     */
    private static String[] tokenize(String input) {
        return input.replaceAll("\\s+", "") // Remove spaces
                    .replaceAll("([+*()])", " $1 ") // Add space around operators and parentheses
                    .trim()
                    .split("\\s+"); // Split by whitespace
    }

    /**
     * Checks if the given token is a valid Value (integer or decimal).
     *
     * @param token the token to check
     * @return true if the token is a Value, false otherwise
     */
    private static boolean isValue(String token) {
        return Pattern.matches("\\d+(\\.\\d+)?", token); // Matches integers or decimals
    }

    /**
     * Checks if the given token is a valid variable name (alphabetic characters).
     *
     * @param token the token to check
     * @return true if the token is a variable, false otherwise
     */
    private static boolean isVariable(String token) {
        return Pattern.matches("[a-zA-Z]+", token); // Matches alphabetic strings
    }

    /**
     * Returns the precedence of an operator.
     *
     * @param operator the operator to check
     * @return the precedence value (higher means higher precedence)
     */
    private static int precedence(String operator) {
        return operator.equals("+") ? 1 : (operator.equals("*") ? 2 : 0);
    }

    /**
     * Applies an operator to two expressions and returns the resulting expression.
     *
     * @param operator the operator to apply ("+" or "*")
     * @param right the right operand
     * @param left the left operand
     * @return the resulting Expression
     */
    private static Expression applyOperator(String operator, Expression right, Expression left) {
        return operator.equals("+") ? new Addition(left, right) : new Multiplication(left, right);
    }
}
