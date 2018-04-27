package compiler;

import matrix.Matrix;

import java.security.InvalidParameterException;

public class OperatorNode extends Node {
    private char operation;

    private Node left;
    private Node right;

    public OperatorNode(char token, Node l, Node r) {
        operation = token;
        left = l;
        right = r;
    }

    public Object value() {
        Object leftValue = (left != null) ? left.value() : null;
        Object rightValue = right.value();

        Matrix matrixL = (leftValue instanceof  Matrix) ? (Matrix) leftValue : null;
        Matrix matrixR = (rightValue instanceof  Matrix) ? (Matrix) rightValue : null;
        Double doubleL = (leftValue instanceof  Double) ? (Double) leftValue : null;
        Double doubleR = (rightValue instanceof  Double) ? (Double) rightValue : null;



        switch (operation) {
            case '-': {
                if (matrixL != null && matrixR != null)
                    return matrixL.diff(matrixR);
                else if (doubleL != null && doubleR != null)
                    return doubleL - doubleR;
                throw new InvalidParameterException("Can not diff " + rightValue.getClass().getName() + " from " + leftValue.getClass().getName());
            }
            case '/': {
                if (matrixL != null && doubleR != null)
                    return matrixL.divide(doubleR);
                else if (doubleL != null && doubleR != null)
                    return doubleL / doubleR;
                throw new InvalidParameterException("Can not divide " + leftValue.getClass().getName() + " on " + rightValue.getClass().getName());
            }
            case 'd': {
                if (matrixR != null)
                    return matrixR.determinant();
                throw new InvalidParameterException("det() only works with Matrix");
            }
            default:
                return "Not found close bracket";
        }
    }
}
