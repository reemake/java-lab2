package calculator;

import java.util.Stack;
import java.util.StringTokenizer;

public class RpnEvaluation {

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

    public static int operatorPriority(String c) {
        if (c.equals("+") || c.equals("-")) {
            return 1;
        } else if (c.equals("*") || c.equals("/")) {
            return 2;
        } else if (c.equals("^")) {
            return 3;
        } else return -1;
    }

    public static boolean isOperator(String c) {
        return c.equals("+") || c.equals("-") || c.equals("*") || c.equals("/") || c.equals("^")|| c.equals("(") || c.equals(")");
    }

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
