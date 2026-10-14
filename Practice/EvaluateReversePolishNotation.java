package Practice;

import java.util.ArrayDeque;
import java.util.Deque;

public class EvaluateReversePolishNotation {
    /**
     * LeetCode 150: Evaluate Reverse Polish Notation
     * 
     * You are given an array of strings tokens that represents an arithmetic expression 
     * in a Reverse Polish Notation (Postfix).
     * 
     * Evaluate the expression and return an integer that represents the value of the expression.
     */
    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) {
            return 0;
        }

        Deque<Integer> stack = new ArrayDeque<>();

        for (String token : tokens) {
            switch (token) {
                case "+": {
                    int b = stack.pop();
                    int a = stack.pop();
                    stack.push(a + b);
                    break;
                }
                case "-": {
                    int b = stack.pop();
                    int a = stack.pop();
                    stack.push(a - b);
                    break;
                }
                case "*": {
                    int b = stack.pop();
                    int a = stack.pop();
                    stack.push(a * b);
                    break;
                }
                case "/": {
                    int b = stack.pop();
                    int a = stack.pop();
                    stack.push(a / b); // Truncates toward zero by default in Java integer division
                    break;
                }
                default: {
                    stack.push(Integer.parseInt(token));
                    break;
                }
            }
        }

        return stack.pop();
    }

    public static void main(String[] args) {
        EvaluateReversePolishNotation solution = new EvaluateReversePolishNotation();

        // Test 1: ["2","1","+","3","*"] -> ((2 + 1) * 3) = 9
        String[] tokens1 = {"2", "1", "+", "3", "*"};
        System.out.println("Test 1: " + solution.evalRPN(tokens1)); 
        // Expected: 9

        // Test 2: ["4","13","5","/","+"] -> (4 + (13 / 5)) = 6
        String[] tokens2 = {"4", "13", "5", "/", "+"};
        System.out.println("Test 2: " + solution.evalRPN(tokens2)); 
        // Expected: 6

        // Test 3: ["10","6","9","3","+","-11","*","/","*","17","+","5","+"] -> 22
        String[] tokens3 = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        System.out.println("Test 3: " + solution.evalRPN(tokens3)); 
        // Expected: 22
    }
}
