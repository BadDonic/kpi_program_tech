package compiler;

import matrix.Matrix;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Stack;


public class Compiler {
    private HashMap<String, Object> vars = new HashMap<>();
    private Stack<Character> operatorStack = new Stack<>();
    private Stack<Node> operandStack = new Stack<>();

    private static final String matrixRegex = "\\[\\[-?\\d+\\.?\\d{0,2}(,\\s?-?\\d+\\.?\\d{0,2})*](,\\s?\\[-?\\d+\\.?\\d{0,2}(,\\s?-?\\d+\\.?\\d{0,2})*])*]";
    private static final String varRegex = "[a-zA-Z_][a-zA-Z_0-9]*";
    private static final String digitRegex = "-?\\d+\\.?\\d{0,2}";

    public Object compile(String input) {
        try {
            input = input.trim();
            if (input.contains("=")) {
                if (!input.contains(" = "))
                    throw new InvalidParameterException("Need Space around \"=\" in - " + input);
                String[] parts = input.split(" = ");
                if (parts.length != 2)
                    throw new InvalidParameterException("Need only one sign \"=\" in - " + input);
                if (!parts[0].matches(varRegex))
                    throw new InvalidParameterException("Invalid input before equals: " + parts[0]);
                Object data = compile(parts[1].trim());
                if (data instanceof Matrix || data instanceof Double)
                    vars.put(parts[0].trim(), data);
                return data;
            }
            parse(input);
            while (!operatorStack.empty())
                turnOperationToOperands(operatorStack.pop());
            if (operandStack.size() != 1)
                throw new InvalidParameterException("Invalid number of operators");
            Node root = operandStack.pop();
            return root.value();
        } catch (Exception e) {
            operandStack = new Stack<>();
            operatorStack = new Stack<>();
            return e.getMessage();
        }
    }

    private void processOperator(Character token) {
        int tokenPriority = priority(token);
        while (!operatorStack.empty() && tokenPriority <= priority(operatorStack.peek()))
            turnOperationToOperands(operatorStack.pop());
        operatorStack.push(token);
    }

    private void processCloseBracket() {
        while (!operatorStack.empty() && operatorStack.peek() != '(')
            turnOperationToOperands(operatorStack.pop());
        operatorStack.pop();
    }

    private void turnOperationToOperands(Character token) {
        Node right = operandStack.pop();
        if (operandStack.size() == 0 && token != 'd')
            throw new InvalidParameterException("Invalid number of operands");
        Node left = (token != 'd') ? operandStack.pop() : null;
        OperatorNode node = new OperatorNode(token, left, right);
        operandStack.push(node);
    }

    private int priority(Character token) {
        return '-' == token ? 1 :
                '/' == token ? 2 :
                        'd' == token ? 3 : 0;
    }

    private void parse(String input) {
        String[] subStrings = input.split(" ");
        for (int i = 0; i < subStrings.length; i++) {
            String temp = subStrings[i];
            boolean closeBracket = false;


            if (temp.charAt(0) == '(') {
                operatorStack.push('(');
                temp = (temp.length() != 1) ? temp.substring(1) : "";
                if (temp.length() == 0) continue;
            }
            if (temp.charAt(0) == 'd' && temp.length() >= 4 && temp.substring(0, 4).matches("det\\(")) {
                subStrings[i] = temp.substring(3);
                processOperator('d');
                i--;
                continue;
            }
            if (temp.charAt(temp.length() - 1) == ')') {
                closeBracket = true;
                temp = temp.substring(0, temp.length() - 1);
                if (temp.length() == 0) {
                    processCloseBracket();
                    continue;
                }
            }
            if (temp.matches(varRegex)) {
                if (!vars.containsKey(temp))
                    throw new InvalidParameterException("Does not exist variable - " + temp);
                operandStack.push(new DataNode(vars.get(temp)));
            } else if (temp.matches(digitRegex))
                operandStack.push(new DataNode(Double.parseDouble(temp)));
            else if (temp.charAt(0) == '[') {
                String matrixStr = temp;
                for (i++; i < subStrings.length && !matrixStr.contains("]]"); i++)
                    matrixStr += subStrings[i];
                i--;
                int countBrackets = 0;
                if (matrixStr.contains(")")) {
                    int indexCloseBracket = matrixStr.indexOf(")");
                    countBrackets = matrixStr.length() - indexCloseBracket;
                    matrixStr = matrixStr.substring(0, indexCloseBracket);
                }
                if (matrixStr.matches(matrixRegex)) {
                    operandStack.push(new DataNode(Matrix.convert(matrixStr)));
                    for (int it = 0; it < countBrackets; it++)
                        processCloseBracket();
                } else
                    throw new InvalidParameterException("Invalid input Matrix: " + matrixStr);
            } else if (temp.equals("-") || temp.equals("/"))
                processOperator(temp.charAt(0));
            else
                throw new InvalidParameterException("Invalid input: " + subStrings[i]);
            if (closeBracket) processCloseBracket();
        }
    }
}