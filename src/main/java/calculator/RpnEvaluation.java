package calculator;

import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Class which is describing methods for parsing an expression and calculating its value using RPN (Reverse Polish Notation)
 * @author Nikita Safonov, student of AMM VSU, 3rd year, 3rd group
 */
public class RpnEvaluation {

    /**
     * Calculating the value of an arithmetic operation
     * @param op1 first operand
     * @param op2 second operand
     * @param operator arithmetic operation
     * @return the value of the calculated arithmetic operation
     */
    public static double someCalc(double op1, double op2, String operator){
        switch (operator){
            case "+":
                return op2 + op1;
            case "-":
                return op2 - op1;
            case "*":
                return op2 * op1;
            case "/":
                return op2 / op1;
            case "^":
                return Math.pow(op2,op1);
        }
        return -1;
    }

    /**
     * Method for determining the priority of arithmetic operations
     * @param c arithmetic operation
     * @return priority
     */
    public static int operatorPriority(String c) {
        if (c.equals("+") || c.equals("-")) {
            return 1;
        } else if (c.equals("*") || c.equals("/")) {
            return 2;
        } else if (c.equals("^")) {
            return 3;
        } else return -1;
    }

    /**
     * Checking if the string is an arithmetic operation
     * @param c element (string) of the expression
     * @return true if element is an arithmetic operation, false otherwise
     */
    public static boolean isOperator(String c) {
        return c.equals("+") || c.equals("-") || c.equals("*") || c.equals("/") || c.equals("^")|| c.equals("(") || c.equals(")");
    }

    /**
     * Converting an infix expression to a postfix expression
     * @param infixExpr infix expression
     * @return postfix expression
     */
    public static String infixToPostfix(String infixExpr) {
        StringTokenizer infix = new StringTokenizer(infixExpr, "+-*/^()", true);
        Stack<String> stack = new Stack<>();
        StringBuilder postfixExpr = new StringBuilder();

        while (infix.hasMoreTokens()) {
        String temp = infix.nextToken();

            if (!isOperator(temp))
                postfixExpr.append(temp + " ");

            else if (temp.equals("("))
                stack.push(temp);

            else if (temp.equals(")")) {
                while (!stack.isEmpty() && !stack.peek().equals("("))
                    postfixExpr.append(stack.pop() + " ");
                if (!stack.isEmpty() && !stack.peek().equals("("))
                    return null;
                else if (!stack.isEmpty())
                    stack.pop();
            }

            else {  //ifOperator (+, -, *, /, ^)
                while (!stack.isEmpty() && (operatorPriority(temp) <= operatorPriority(stack.peek())))
                    postfixExpr.append(stack.pop() + " ");
                stack.push(temp);
            }
        }

        while (!stack.isEmpty())
            postfixExpr.append(stack.pop());

        return postfixExpr.toString();
    }


    /**
     * Evaluating the value of a postfix expression
     * @param postfixExpr postfix expression
     * @return evaluated value
     */
    public static double postfixEvaluation(String postfixExpr) {
        StringTokenizer postfix = new StringTokenizer(postfixExpr, " ");
        Stack<Double> opStack = new Stack<>();
        double value = 0;

        while (postfix.hasMoreTokens()) {
            String temp = postfix.nextToken();

            if (!isOperator(temp))
                opStack.push(Double.valueOf(String.valueOf(temp)));

            else {
                double op1 = opStack.pop();
                double op2 = opStack.pop();
                double result = someCalc(op1, op2, temp);
                opStack.push(result);
            }
        }
            value = opStack.pop();

            if (!opStack.isEmpty()) {
                System.out.println("Error! Wrong input.");
                return -1;
            }
        return value;
    }

}
