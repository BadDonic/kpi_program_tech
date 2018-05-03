import compiler.Compiler;

public class Main {
    public static void main(String[] args) {
        Compiler compiler = new Compiler();
        System.out.println(compiler.compile("[[1, 1, 1], [1, 1, 1]] - [[1, 1, 1], [1, 1, 1]]"));
        System.out.println(compiler.compile("[[1,1,1],[1,1,1]] - [[1, 1, 1], [1, 1, 1]]"));
        System.out.println(compiler.compile("                [[1, 1, 1], [1, 1, 1]]    "));
        System.out.println(compiler.compile("A = [[1, 1, 1], [1, 1, 1]]"));
        System.out.println(compiler.compile("A = A - A - A"));
        System.out.println(compiler.compile("A =A-A-A"));
        System.out.println(compiler.compile("B = 2"));
        System.out.println(compiler.compile("B =2"));
        System.out.println(compiler.compile("D = B / B"));
        System.out.println(compiler.compile("D = B/B"));
        System.out.println(compiler.compile("C = A/B"));
        System.out.println(compiler.compile("C = A / B"));
        System.out.println(compiler.compile("B - D"));
        System.out.println(compiler.compile("K = det([[1]])"));
        System.out.println(compiler.compile("det( [[0]] )"));
        System.out.println(compiler.compile("A = [[1,2],[10,1]]"));
        System.out.println(compiler.compile("det(A-(A - A ) / det( [[0.5]] ) ) / 0.5"));
        System.out.println(compiler.compile("([[6, 6, 6]] - [[3, 3, 3]]) / (3 - det([[1, 2], [2, 1]] - [[1, 2], [2, 1]]))"));
        System.out.println(compiler.compile("det([[1.4, 4.3], [12.12, 2.54]] / 3 - [[1.1, 1.1], [1.1, 1.1]])"));
        System.out.println(compiler.compile("K"));
    }
}