package code.stringcalculator;

import java.util.*;

public class EvaluateExpression {

    public static double calculate(String expression) throws InvalidExpression {
        char[] tokens = expression.toCharArray();

        Stack <Double> operands = new Stack<>();

        Stack<Operators> operators = new Stack<>();
        boolean isNumber = false;

        for (int i = 0; i < expression.length(); i++)
        {
            char token = expression.charAt(i);
            if (token == ' ')
                continue;

            if (token >= '0' && token <= '9')
            {
                StringBuilder operandBuffer = new StringBuilder();
                int k = i;
                while (k < tokens.length && tokens[k] >= '0' && tokens[k] <= '9') {
                    operandBuffer.append(tokens[k++]);

                }
                i = k - 1;
                operands.push(Double.parseDouble(operandBuffer.toString()));
                isNumber = true;
            }

            else if (token ==  Operators.OPEN_BRACKET.getOp()) {
                    if(isNumber){
                        throw new InvalidExpression("Invalid Expression");
                    }
                operators.push(Operators.OPEN_BRACKET);
            }

            else if (token == Operators.CLOSED_BRACKET.getOp())
            {

                while (operators.peek() != Operators.OPEN_BRACKET) {
                    operands.push(applyOperation(operators.pop(), operands.pop(), operands.pop()));
                }
                operators.pop();
            }

            else if (token == '+' || token == '-' ||
                    token == '*' || token == '/' || token == '^')
            {
                if (operands.isEmpty()) {
                    throw new InvalidExpression("Invalid Expression");
                }
                while (!operators.empty() && isOperatorHasPrecedence(OperatorUtil.getOperator(token), operators.peek())){

                    operands.push(applyOperation(operators.pop(), operands.pop(), operands.pop()));
                }

                operators.push(OperatorUtil.getOperator(token));
                isNumber = false;
            }
            else {
                throw new InvalidExpression("Invalid Expression");
            }
        }

        while (!operators.empty()){

            operands.push(applyOperation(operators.pop(), operands.pop(), operands.pop()));
        }

        return operands.pop();
    }

    public static boolean isOperatorHasPrecedence(Operators op1, Operators op2)
    {
        if (op2 == Operators.OPEN_BRACKET || op2 ==  Operators.CLOSED_BRACKET)
            return false;

        if (op1 == Operators.POWER) {
            return false;
        }
        return (op1 != Operators.MULTIPLY && op1 != Operators.DIVISION) ||
                (op2 != Operators.ADD && op2 != Operators.SUBTRACT);
    }

    public static double applyOperation(Operators op, double b, double a)
    {
        switch (op)
        {
            case ADD:
                return a + b;
            case SUBTRACT:
                return a - b;
            case MULTIPLY:
                return a * b;
            case POWER:
                return Math.pow(a,b);
            case DIVISION:
                if (b == 0)
                    throw new UnsupportedOperationException("Divide by zero");
                return a / b;
            default:
                throw new UnsupportedOperationException("Unknown operator");
        }
    }

    public static void main(String[] args) throws InvalidExpression {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter number of testcases :");
        int testCaseNo = in.nextInt();
        List result = new ArrayList();
        in = new Scanner(System.in);
        for (int i = 0; i < testCaseNo; i++) {
            System.out.println("enter expression :");
            try {
                result.add(EvaluateExpression.calculate(in.nextLine()));
            } catch (InvalidExpression  | UnsupportedOperationException | EmptyStackException ex) {
                result.add("INVALID EXPRESSION");
            }
        }

        for (int i = 0; i < result.size(); i++) {
            System.out.println("case #"+(i+1)+": "+result.get(i));
        }

    }
}
