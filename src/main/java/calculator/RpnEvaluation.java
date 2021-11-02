package calculator;

import java.util.Stack;

public class RpnEvaluation {

    public static boolean isOperator(Character c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^' || c == '(' || c == ')';
    }

    public static int operatorPriority(Character c) {
        if (c == '+' || c == '-') return 1;
        else if (c == '*' || c == '/') return 2;
        else if (c == '^') return 3;
        else return -1;
    }

    public static double someCalc(double op1, double op2, Character operator) {
        switch (operator) {
            case '+':
                return op2 + op1;
            case '-':
                return op2 - op1;
            case '*':
                return op2 * op1;
            case '/':
                return op2 / op1;
            case '^':
                return Math.pow(op2, op1);
        }
        return -1;
    }

    public static String infixToPostfix(String infixExpr) {
        StringBuilder postfixExpr = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < infixExpr.length(); i++) {

            if (!isOperator(infixExpr.charAt(i)))
                postfixExpr.append(infixExpr.charAt(i));

            else if (infixExpr.charAt(i) == '(')
                stack.push(infixExpr.charAt(i));

            else if (infixExpr.charAt(i) == ')') {
                while (!stack.isEmpty() && stack.peek() != '(')
                    postfixExpr.append(stack.pop());
                if (!stack.isEmpty() && stack.peek() != '(')
                    return null;
                else if(!stack.isEmpty())
                    stack.pop();
            }

            else {
                while (!stack.isEmpty() && (operatorPriority(infixExpr.charAt(i)) <= operatorPriority(stack.peek())))
                    postfixExpr.append(stack.pop());
                stack.push(infixExpr.charAt(i));
            }
        }

        while (!stack.isEmpty())
            postfixExpr.append(stack.pop());

        return postfixExpr.toString();
    }

    public static double postfixEvaluation(String postfixExpr) {
        double rpnResult = 0;
        Stack<Double> opStack = new Stack<>();

        for (int i = 0; i < postfixExpr.length(); i++) {

            if (!isOperator(postfixExpr.charAt(i)))
                opStack.push(Double.valueOf(String.valueOf(postfixExpr.charAt(i))));

            else {
                double op1 = opStack.pop();
                double op2 = opStack.pop();
                double result = someCalc(op1, op2, postfixExpr.charAt(i));
                opStack.push(result);
            }
        }
            rpnResult = opStack.pop();

            if (!opStack.isEmpty()) {
                System.out.println("Error!");
                return -1;
            }
        return rpnResult;
    }

    public static void main(String[] args) {
        String infix = "3+(2+1)*2^3-8/(5-1*2/2)";
        String postfix = infixToPostfix(infix);
        double result = postfixEvaluation(postfix);
        System.out.println(postfix);
        System.out.println(result);
    }


}
