package compiler;

import matrix.Matrix;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Stack;


public class Compiler {
    private HashMap<String, Object> vars = new HashMap<>();
    private Stack<Character> operatorStack = new Stack<>();
    private Stack<Node> operandStack = new Stack<>();

    public Object compile(String input) {
        try {
            if (input.contains("=")) {
                String[] parts = input.split("=");
                if (!parts[0].trim().matches("[a-zA-Z_][a-zA-Z_0-9]*") || parts.length != 2)
                    throw new InvalidParameterException("Invalid Input");
                Object data = (Object) compile(parts[1].trim());
                vars.put(parts[0].trim(), data);
                return data;
            }
            parse(input);
            while (!operatorStack.empty())
                turnOperationToOperands(operatorStack.pop());

            if (operandStack.size() != 1)
                throw new InvalidParameterException("Invalid number of operands");

            Node root = operandStack.pop();
            return root.value();

        } catch (Exception e) {
            operandStack = new Stack<>();
            operatorStack = new Stack<>();
            return e.getMessage();
        }
    }

    private void parse(String input) {
        input = input.trim();
        for (int i = 0; i < input.length(); i++) {
            char token = input.charAt(i);
            switch (token) {
                case '-':
                case '/': {
                    processOperator(token);
                    break;
                }
                case 'd': {
                    if (input.length() - i >= 10 && input.substring(i, i + 4).matches("det\\(")) {
                        i = i + 2;
                        processOperator(token);
                        break;
                    } else
                        throw new InvalidParameterException("Invalid input det()");
                }

                case ')':
                    processRightParenthesis();
                    break;

                case '(':
                    operatorStack.push(token);
                    break;
                case '[': {
                    if (!input.contains("]]"))
                        throw new InvalidParameterException("Invalid Matrix");
                    int closeSymbolIndex = input.indexOf("]]", i) + 2;
                    String subString = input.substring(i, closeSymbolIndex);
                    if (subString.matches("\\[\\[\\d+\\.?\\d{0,2}(,\\s?\\d+\\.?\\d{0,2})*](,\\s?\\[\\d+\\.?\\d{0,2}(,\\s?\\d+\\.?\\d{0,2})*])*]")) {
                        operandStack.push(new DataNode(Matrix.convert(input.substring(i, closeSymbolIndex))));
                        i = closeSymbolIndex - 1;
                        break;
                    } else
                        throw new InvalidParameterException("Invalid Matrix");
                }
                default: {
                    if (Character.isLetter(token) || token == '_') {
                        int startPos = i;
                        while (Character.isLetterOrDigit(token) || token == '_') {
                            i++;
                            token = (i != input.length()) ? input.charAt(i) : ' ';
                        }
                        String var = input.substring(startPos, i);
                        if (!vars.containsKey(var))
                            throw new InvalidParameterException("Does not exist variable - " + var);
                        operandStack.push(new DataNode(vars.get(var)));
                        i--;
                    } else if (Character.isDigit(token)) {
                        int startPos = i;
                        boolean dot = false;
                        while (Character.isDigit(token) || token == '.') {
                            if (token == '.')
                                if (!dot) dot = true;
                                else
                                    throw new InvalidParameterException("Unexpected token (pos = " + i + " )");
                            i++;
                            token = (i != input.length()) ? input.charAt(i) : ' ';
                        }
                        String doubleStr = input.substring(startPos, i);
                        operandStack.push(new DataNode(Double.parseDouble(doubleStr)));
                        i--;
                    }
                    break;
                }
            }
        }
    }

    private void processOperator(Character token) {
        int tokenPriority = priority(token);
        while (!operatorStack.empty() && tokenPriority <= priority(operatorStack.peek()))
            turnOperationToOperands(operatorStack.pop());
        operatorStack.push(token);
    }

    private void processRightParenthesis() {
        while (!operatorStack.empty() && operatorStack.peek() != '(')
            turnOperationToOperands(operatorStack.pop());
        operatorStack.pop();
    }

    private void turnOperationToOperands(Character token) {
        Node right = operandStack.pop();
        Node left = (token != 'd') ? operandStack.pop() : null;
        OperationNode node = new OperationNode(token, left, right);
        operandStack.push(node);
    }

    private int priority(Character op) {
        switch (op) {
            case '-':
                return 1;
            case '/':
                return 2;
            case 'd':
                return 3;
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        Compiler compiler = new Compiler();
        System.out.println(compiler.compile("([[6, 6, 6]] - [[3, 3, 3]]) / (3 - det([[1, 2], [2, 1]] - [[1, 2], [2, 1]]))"));
        System.out.println(compiler.compile("[[1, 1, 1], [1, 1, 1]] - [[1, 1, 1], [1, 1, 1]]"));
        System.out.println(compiler.compile("[[1, 1, 1], [1, 1, 1]]-[[1, 1, 1], [1, 1, 1]]"));
        System.out.println(compiler.compile("                [[1, 1, 1], [1, 1, 1]]    "));
        System.out.println(compiler.compile("A = [[1, 1, 1], [1, 1, 1]]"));
        System.out.println(compiler.compile("A = A - A - A"));
        System.out.println(compiler.compile("A =A-A-A"));
        System.out.println(compiler.compile("B = 2"));
        System.out.println(compiler.compile("B =2"));
        System.out.println(compiler.compile("D =B / B"));
        System.out.println(compiler.compile("D =B/B"));
        System.out.println(compiler.compile("C =A/B"));
        System.out.println(compiler.compile("C =A / B"));
        System.out.println(compiler.compile("B - D"));
        System.out.println(compiler.compile("det([[1]])"));
        System.out.println(compiler.compile("det([[0]])"));
        System.out.println(compiler.compile("A = [[1, 2], [10, 1]]"));
        System.out.println(compiler.compile("det(A - (A - A) / det([[0.5]])) / 0.5"));

    }
}