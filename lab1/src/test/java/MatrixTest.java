import org.junit.jupiter.api.Test;
import org.ejml.simple.SimpleMatrix;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {
    double[][] test = {
            { 2, 2, 4, 4 },
            { 8, 2, 4, 10 },
            { 8, 4, 4, 6 },
            { 4, 12, 6, 6 }
    };

    double[][] test2 = {
            { 3.1, 2.2, -2.3 },
            { 1.8, 2.2, 2.4 },
            { 7.9, 3.8, 4.7 }
    };

    double[][] test3 = {
            { 1, 1, 1 },
            { 2, 2, 2 },
            { 3, 3, 3 },
            { 4, 4, 4 }
    };

    double[][] test4 = {
            { 2, 5, 1, 9, 3, 1 },
            { 2, 2, 9, 9, 3 },
            { 3, 2, 4, 12 },
            { 3, 3,}
    };

    double[][] expectedDivided1 = {
            { 1, 1, 2, 2 },
            { 4, 1, 2, 5 },
            { 4, 2, 2, 3 },
            { 2, 6, 3, 3 }
    };

    double[][] expectedDivided2 = {
            {0.6666, 0.6666, 1.33, 1.3333},
            {2.6666, 0.6666, 1.3333, 3.3333},
            {2.6666, 1.3333, 1.3333, 2.00},
            {1.3333, 4.00, 2.00, 2.00}
    };

    @Test
    void constructorWithoutParameters() {
        Matrix matrix = new Matrix();
        assertNotEquals(null, matrix);
        assertArrayEquals(new double[1][1], matrix.getArray());
        assertEquals(0, matrix.getArray()[0][0]);
    }

    @Test
    void constructorWithSize() {
        double[][] res = {
                {0, 0},
                {0, 0}
        };
        assertArrayEquals(res, (new Matrix(2)).getArray());
        assertThrows(InvalidParameterException.class, () -> new Matrix(0));
        assertThrows(InvalidParameterException.class, () -> new Matrix(-2));
    }

    @Test
    void constructorWithRowsAndCols() {
        double[][] res = {
                {0, 0},
                {0, 0},
                {0, 0}
        };
        assertArrayEquals(res, (new Matrix(3, 2)).getArray());
        assertThrows(InvalidParameterException.class, () -> new Matrix(0, 3));
        assertThrows(InvalidParameterException.class, () -> new Matrix(1, 0));
    }

    @Test
    void constructorWithArray() {
        assertArrayEquals(test, (new Matrix(test).getArray()));
        assertArrayEquals(test2, (new Matrix(test2).getArray()));
        assertThrows(InvalidParameterException.class, () -> new Matrix(test4));
        assertThrows(InvalidParameterException.class, () -> new Matrix(test4));
        assertThrows(InvalidParameterException.class, () -> new Matrix(null));
    }

    @Test
    void getArray() {
        assertArrayEquals(test, (new Matrix(test).getArray()));
    }

    @Test
    void getCols() {
        assertEquals(test[0].length, (new Matrix(test).getCols()));
    }

    @Test
    void getRows() {
        assertEquals(test.length, (new Matrix(test).getRows()));
    }

    @Test
    void ToString() {
        assertEquals("[1.00 2.00 3.00 4.00]\n" +
                "[8.00 2.00 3.00 10.00]\n" +
                "[7.00 1.00 4.00 9.00]\n" +
                "[4.00 5.00 1.00 5.00]\n", new Matrix(test).toString());
    }

    @Test
    void equals() {
        Matrix matrix = new Matrix(test);
        Matrix matrix2 = new Matrix(test);
        Matrix matrix3 = new Matrix(test2);

        assertTrue(matrix.equals(matrix));
        assertTrue(matrix.equals(matrix2));
        assertFalse(matrix.equals(null));
        assertFalse(matrix.equals(matrix3));
    }

    @Test
    void divide() {
        Matrix matrix = new Matrix(test);
        Matrix expectedRes = new Matrix(expectedDivided1);
        Matrix expectedRes2 = new Matrix(expectedDivided2);
        assertThrows(InvalidParameterException.class, () -> matrix.divide(0));
        assertTrue(matrix.equals(matrix.divide(1)));
        assertTrue(expectedRes.equals(matrix.divide(2)));
        assertFalse(expectedRes.equals(matrix.divide(3)));
        assertTrue(expectedRes2.equals(matrix.divide(3)));
    }

    @Test
    void diff() {

    }

    @Test
    void determinant() {
    }

    @Test
    void determinant1() {
    }
}