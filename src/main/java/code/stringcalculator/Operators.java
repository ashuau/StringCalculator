package code.stringcalculator;

public enum Operators {

    ADD('+'),SUBTRACT('-'),MULTIPLY('*'),DIVISION('/'),POWER('^'),
    OPEN_BRACKET('('),CLOSED_BRACKET(')');

    private char op;
    Operators(char s) {
        this.op = s;
    }
    public char getOp() {
        return op;
    }
}
