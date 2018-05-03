package matrix;

import java.security.InvalidParameterException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Matrix {
    private int rows;
    private int cols;
    private double[][] data;

    public Matrix() {
        this(1, 1);
    }

    public Matrix(int size) {
        this(size, size);
    }

    public Matrix(int rows, int cols) {
        if (rows < 1 || cols < 1) throw new InvalidParameterException("Invalid matrix Dimension");
        this.rows = rows;
        this.cols = cols;
        data = new double[rows][cols];
        //this(new double[rows][cols]);
    }

    public Matrix(double[][] data) {
        if (!isValid(data)) throw new InvalidParameterException("Your array is not a matrix");
        rows = data.length;
        cols = data[0].length;
        this.data = new double[rows][cols];
        for (int i = 0; i < rows; i++)
            System.arraycopy(data[i], 0, this.data[i], 0, cols);
    }

    public double[][] getArray() {
        return data;
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    private boolean isValid(double[][] matrix) {
        if (matrix == null) throw new NullPointerException("matrix is null");
        if (matrix.length == 0) return false;
        int cols = matrix[0].length;
        for (int i = 1; i < matrix.length; i++)
            if (matrix[i].length != cols) return false;
        return true;
    }

    public Matrix divide(double k) {
        if (k == 0) throw new InvalidParameterException("Can not divide on zero");
        double[][] result = new double[rows][cols];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                result[i][j] = Math.floor(this.data[i][j] / k * 100) / 100;
        return new Matrix(result);
    }

    public Matrix diff(Matrix matrix) {
        if (matrix == null) throw new NullPointerException("Can not subtract null matrix");
        if (matrix.getRows() != rows || matrix.getCols() != cols)
            throw new InvalidParameterException("Matrices must be of the same dimension");
        double[][] result = new double[rows][cols];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                result[i][j] = Math.floor((data[i][j] - matrix.getArray()[i][j]) * 100) / 100;
        return new Matrix(result);
    }

    @Override
    public String toString() {
        return Arrays.deepToString(Arrays.stream(data)
                .map(numbers -> Arrays.stream(numbers).map(number -> Math.floor(number * 100) / 100).toArray())
                .toArray());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Matrix matrix = (Matrix) obj;
        return rows == matrix.rows &&
                cols == matrix.cols &&
                Arrays.deepEquals(matrix.getArray(), data);
    }

    public double determinant() {
        return determinant(this);
    }

    public static double determinant(Matrix matrix) {
        if (matrix == null) throw new NullPointerException("matrix is null");
        if (matrix.getCols() != matrix.getRows())
            throw new InvalidParameterException("matrix must be square");
        return Math.floor(determinant(matrix.getArray()) * 100) / 100;
    }

    private static double determinant(double[][] matrix) {
        if (matrix.length == 1) return matrix[0][0];
        if (matrix.length == 2)
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];

        double det = 0;
        for (int i = 0; i < matrix[0].length; i++)
            det += Math.pow(-1, i) * matrix[0][i] * determinant(minor(matrix, i));
        return det;
    }

    private static double[][] minor(double[][] matrix, int col) {
        double[][] minor = new double[matrix.length - 1][matrix.length - 1];
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; i != 0 && j < matrix[i].length; j++)
                if (j != col)
                    minor[i - 1][j < col ? j : j - 1] = matrix[i][j];
        return minor;
    }

    public static Matrix convert(String strMatrix) {
        List<Double> list = new ArrayList<>();
        strMatrix = strMatrix.substring(1, strMatrix.length() - 1);
        String[] starts = strMatrix.split("\\[");
        int size = starts.length - 1;
        for (int i = 1; i <= size; i++) {
            if (starts[i].equals("")) continue;
            String[] withoutBracers = starts[i].split("]");
            String[] numbers = withoutBracers[0].split(",\\s?");
            for (String number : numbers)
                list.add(Double.parseDouble(number));
        }
        int size2 = list.size() / size;
        double[][] array = new double[size][size2];
        for (int i = 0; i < list.size(); i++) {
            array[i / size2][i % size2] = list.get(i);
        }
        return new Matrix(array);
    }
}