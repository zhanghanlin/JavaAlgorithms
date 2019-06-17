package com.demo.util.typical;

/**
 * QueenEight
 * 八皇后算法
 *
 * @author zhanghanlin6
 * @date 2019-06-17 21:46
 */
public class QueenEight {
    /**
     * 棋盘格子8x8
     */
    private static final int MAX_NUM = 8;
    /**
     * 二维数组作为棋盘
     */
    private static int[][] chessBoard = new int[MAX_NUM][MAX_NUM];

    /**
     * main函数
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        QueenEight.settleQueen(0);
        QueenEight.printChessBoard();
    }

    /**
     * 输出棋盘
     */
    private static void printChessBoard() {
        for (int x = 0; x < MAX_NUM; x++) {
            for (int y = 0; y < MAX_NUM; y++) {
                System.out.print(chessBoard[x][y]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    /**
     * 递归回溯
     *
     * @param y Y坐标
     * @return 是否可以落子
     */
    private static boolean settleQueen(int y) {
        //行数超过8,则已有解决方案
        if (y == MAX_NUM) {
            return true;
        }
        //横向遍历
        for (int x = 0; x < MAX_NUM; x++) {
            //清理当前行
            for (int c = 0; c < MAX_NUM; c++) {
                chessBoard[c][y] = 0;
            }
            //符合条件,则改变元素值再次递归
            if (check(x, y)) {
                chessBoard[x][y] = 1;
                //如果返回true,则表示下一行已有解决方案,无需继续循环
                if (settleQueen(y + 1)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 检查落点是否符合
     *
     * @param x X坐标
     * @param y Y坐标
     * @return 落点是否符合
     */
    private static boolean check(int x, int y) {
        for (int i = 0; i < y; i++) {
            //检查纵向-Y轴
            if (chessBoard[x][i] == 1) {
                return false;
            }
            //检查左侧斜向
            if (x - i - 1 >= 0 && chessBoard[x - i - 1][y - i - 1] == 1) {
                return false;
            }
            //检查右侧斜向
            if (x + i + 1 < MAX_NUM && chessBoard[x + i + 1][y - i - 1] == 1) {
                return false;
            }
        }
        return true;
    }
}
