package code.stringcalculator;

public class OperatorUtil {

    public static Operators getOperator(char c) {

        switch (c) {

            case '+':
                return Operators.ADD;
            case '-':
                return Operators.SUBTRACT;
            case '*':
                return Operators.MULTIPLY;
            case '/':
                return Operators.DIVISION;
            case '^':
                return Operators.POWER;

        }


        return null;
    }
}
