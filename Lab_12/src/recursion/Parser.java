package recursion;

import java.util.List;
import java.util.ArrayList;

public class Parser {
  
  /**
   * Recursive function to handle multiplication and division operations.
   * It checks for the '*' or '/' operator and performs the corresponding operation.
   * 
   * @param expression The part of the mathematical expression to evaluate.
   * @return The result of multiplication or division of the given expression.
   */
  public static int multiply(String expression){
    // Get the first digit as the initial number
    int num1 = expression.charAt(0) - '0';

    // Base case: If the expression is a single number, return the number
    if (expression.length() == 1)
      return num1;

    // Get the operator at the second position (either '*' or '/')
    char oper = expression.charAt(1);

    // If operator is '*', recursively perform multiplication
    if (oper == '*'){
      return num1 * multiply(expression.substring(2, expression.length()));
    } else {
      // If operator is '/', recursively perform division
      return num1 / multiply(expression.substring(2, expression.length()));
    }
  }
  
  /**
   * Recursive function to handle addition and subtraction operations.
   * It finds the first occurrence of '+' or '-' and splits the expression
   * into two sub-expressions to recursively evaluate.
   * 
   * @param expression The part of the mathematical expression to evaluate.
   * @return The result of the addition or subtraction of the given expression.
   */
  public static int addition(String expression){
    // Base case: If the expression is a single number, return the number
    if (expression.length() == 1){
      int num1 = expression.charAt(0) - '0';
      return num1;
    }

    // Variable x will store the index of the first '+' or '-' operator
    int x = 0;
    while (x < expression.length() && (expression.charAt(x) != '+' && expression.charAt(x) != '-')){
      x++;
    }

    // If no '+' or '-' operator is found, the expression only contains multiplication/division
    if (x == expression.length()){
      return multiply(expression);
    }

    // If operator is '+', recursively perform addition
    if (expression.charAt(x) == '+'){
      return addition(expression.substring(0, x)) 
          + addition(expression.substring(x + 1, expression.length()));
    } else {
      // If operator is '-', recursively perform subtraction
      return addition(expression.substring(0, x)) 
          - addition(expression.substring(x + 1, expression.length()));
    }
  }
  
  /**
   * Main function to evaluate the entire mathematical expression.
   * It removes any whitespace and calls the 'addition' function to evaluate the expression.
   * 
   * @param expression The mathematical expression to evaluate.
   * @return The final result of the expression.
   */
  public static int evaluateExpression(String expression){
    expression = expression.replaceAll("\\s+", "");  // Remove all spaces
    return addition(expression);  // Call addition() to evaluate the expression
  }

  /**
   * Main method to test the evaluateExpression function with a sample input.
   */
  public static void main(String[] args) {
    // Sample test expression
    System.out.println(evaluateExpression("5*3-2+7"));  // Expected output: 18
  }
}
