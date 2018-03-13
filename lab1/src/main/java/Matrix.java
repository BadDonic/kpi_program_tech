import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.Objects;

public class Matrix {
    private int rows;
    private int cols;
    private double[][] data;

    Matrix() { this(1,1); }
    Matrix(int size) { this(size, size); }

    Matrix(int rows, int cols) {
        if (rows < 1 || cols < 1) throw new InvalidParameterException("Invalid Matrix Dimension");
        this.rows = rows;
        this.cols = cols;
        data = new double[rows][cols];
    }

    Matrix(double[][] data) {
        if (!isValid(data)) throw new InvalidParameterException("Your array is not a matrix");
        rows = data.length;
        cols = data[0].length;
        this.data = new double[rows][cols];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                this.data[i][j] = data[i][j];
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

    private boolean isValid (double[][] matrix) {
        if (matrix == null || matrix.length == 0) return false;
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
                result[i][j] = this.data[i][j] / k;
        return new Matrix(result);
    }

    public Matrix diff(Matrix matrix) {
        if (matrix == null) throw new NullPointerException("Can not subtract null matrix");
        if (matrix.getRows() != rows || matrix.getCols() != cols) throw new InvalidParameterException("Matrices must be of the same dimension");
        double[][] result = new double[rows][cols];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                result[i][j] = data[i][j] - matrix.getArray()[i][j];
        return new Matrix(result);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Matrix{");
        sb.append("rows=").append(rows);
        sb.append(", cols=").append(cols);
        sb.append(", data=").append(Arrays.deepToString(data));
        sb.append('}');
        return sb.toString();
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

    public double determinant(Matrix matrix) {
        if ( matrix == null || matrix.getCols() != matrix.getRows())
            throw new InvalidParameterException("Matrix must be square");
        return determinant(data);
    }

    private double determinant(double[][] matrix) {
        if (matrix.length == 1) return matrix[0][0];
        if (matrix.length == 2)
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];

        double det = 0;
        for (int i = 0; i < matrix[0].length; i++)
            det += Math.pow(-1, i) * matrix[0][i] * determinant(minor(matrix,0, i));
        return det;
    }

    private double[][] minor(double[][] matrix, int row, int col) {
        double[][] minor = new double[matrix.length - 1][matrix.length - 1];
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; i != row && j < matrix[i].length; j++)
                if (j != col)
                    minor[i < row ? i : i - 1][j < col ? j : j - 1] = matrix[i][j];
        return minor;
    }
}
