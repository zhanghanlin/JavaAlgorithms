package com.demo.util.squarematrix;

/**
 * 矩阵
 *
 * @author zhanghanlin
 */
public class SquareMatrix {

    public static void main(String[] args) {
        int[][] a = new int[5][5];
        int[][] b = new int[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                a[i][j] = i * j;
                b[i][j] = i * j + 1;
            }
        }
        print(a);
        print(b);
        print(squareMatrixMultiplyRecursive(a, b));
    }

    static void print(int[][] c) {
        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < c[i].length; j++) {
                System.out.print("[" + (i + 1) + "][" + (j + 1) + "]=" + c[i][j] + "\t");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    /**
     * 矩阵 - 乘
     * 伪代码
     * SQUARE-MATRIX-MULTIPLY(A, B)
     * --n = A.rows
     * --let C be a new n × n matrix
     * --for i = 1 to n
     * ----for j = 1 to n
     * ------c ij = 0		//i j is Subscript
     * --------for k = 1 to n
     * ----------c ij = c ij + a ik * b kj
     * --return C
     *
     * @param a
     * @param b
     */
    public static void squareMatrixMultiply(int[][] a, int[][] b, int[][] c) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                c[i][j] = 0;
                for (int k = 0; k < n; k++) {
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }
    }

    /**
     * 缺少分解矩阵步骤 - 未完善
     *
     * @param a
     * @param b
     * @return
     */
    public static int[][] squareMatrixMultiplyRecursive(int[][] a, int[][] b) {
        int n = a.length;
        int[][] c = new int[n][n];
        if (n == 1) {
            c[n][n] = a[n][n] * b[n][n];
        } else {
            squareMatrixMultiply(a, b, c);
        }
        return c;
    }
}
