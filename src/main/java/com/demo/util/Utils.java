package com.demo.util;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Random;

/**
 * 公共类
 *
 * @author zhanghanlin
 */
public class Utils {

    /**
     * 交换数组t中下标为i和j的位置
     *
     * @param t 数组对象
     * @param i 下标A
     * @param j 下标B
     */
    public static <T extends Comparable<T>> void swap(T[] t, int i, int j) {
        T temp = t[i];
        t[i] = t[j];
        t[j] = temp;
    }


    /**
     * 得到父节点下标
     * 伪代码
     * PARENT(i)
     * --return [i / 2]
     *
     * @param i
     * @return
     */
    public static int parent(int i) {
        return i / 2;
    }

    /**
     * 得到左孩子下标
     * 伪代码
     * LEFT(i)
     * --return 2i
     *
     * @param i
     * @return
     */
    public static int left(int i) {
        return 2 * i;
    }

    /**
     * 得到右孩子下标
     * 伪代码
     * RIGHT(i)
     * --return 2i + 1
     *
     * @param i
     * @return
     */
    public static int right(int i) {
        return 2 * i + 1;
    }

    /**
     * 打印输出
     *
     * @param o
     */
    public static <T extends Comparable<T>> void print(Comparable<T>[] o) {
        if (o != null) {
            for (Comparable<T> i : o) {
                if (i != null) {
                    System.out.print(i.toString() + " ");
                }
            }
        }
        System.out.println("");
    }

    /**
     * 打印输出
     *
     * @param o
     * @param info 在输出结果前增加消息
     */
    public static <T extends Comparable<T>> void print(Comparable<T>[] o, String info) {
        System.out.print(info + " : ");
        print(o);
    }

    /**
     * 判断对象是否为空
     *
     * @param o
     * @return
     */
    public static boolean isNotBlank(Object o) {
        return o != null && !o.toString().equals("");
    }

    /**
     * 判断对象是否为空
     *
     * @param o
     * @return
     */
    public static boolean isBlank(Object o) {
        return o == null || o.toString().equals("");
    }

    /**
     * 得到一个介于a和b之间的随机数
     *
     * @param a
     * @param b
     * @return
     */
    public static Integer random(int a, int b) {
        return a + new Double(Math.random() * (b - a)).intValue();
    }

    /**
     * 得到一个长度为N介于a和b之间的随机数组
     *
     * @param a
     * @param b
     * @param n
     * @return
     */
    public static Integer[] random(int a, int b, int n) {
        if (a > b) {
            int temp = a;
            a = b;
            b = temp;
        }
        Random random = new Random(Calendar.getInstance().get(Calendar.SECOND));
        Integer[] res = new Integer[n];
        for (int i = 0; i < n; i++) {
            res[i] = (int) ((random.nextDouble() * (Math.abs(b - a) + 1)) + a);
        }
        return res;
    }

    /**
     * 得到一个小数部分介于a-b之间小于1的随机Double数组
     *
     * @param a
     * @param b
     * @param n
     * @return
     */
    public static Double[] randomDouble(int a, int b, int n) {
        Double[] d = new Double[n];
        Integer[] res = random(a, b, n);
        for (int j = 0; j < res.length; j++) {
            d[j] = Double.valueOf(formatBit(res[j], 2));
        }
        return d;
    }

    /**
     * 格式化
     *
     * @param o
     * @param bit 保留几位小数<0,1,2> 其他值默认为2
     * @return 保留bit位小数字符串
     */
    public static String formatBit(Integer o, int bit) {
        String p = "0.00";
        try {
            if (o != null && o.longValue() > 0) {
                BigDecimal b = new BigDecimal(o / 100.0);
                String format = "%.2f";
                if (bit <= 2 && bit >= 0) {
                    format = "%." + bit + "f";
                }
                p = String.format(format, b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }
}
