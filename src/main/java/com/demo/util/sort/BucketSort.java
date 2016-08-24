package com.demo.util.sort;

import com.demo.util.Utils;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 桶排序
 *
 * @author zhanghanlin
 */
public class BucketSort {

    /**
     * 桶排序
     * 伪代码
     * BUCKET-SORT(A)
     * --n = A.length
     * --let B[0..n-1] be a new array
     * --for i = 0 to n - 1
     * ----make B[i] an empty list
     * --for i = 1 to n
     * ----insert A[i] into list B[[nA[i]]]
     * --for i = 0 to n - 1
     * ----sort list B[i] with insertion sort
     * --concatenate the lists B[0], B[1],..., B[n-1] together in order
     *
     * @param src
     */
    public static void bucketSort(Double[] src) {
        int n = src.length;
        ArrayList<Double>[] srcList = new ArrayList[n];
        //把src中的数均匀的的分布到[0,1)上，每个桶是一个list，存放落在此桶上的元素
        for (int i = 0; i < n; i++) {
            int temp = (int) Math.floor(n * src[i]);
            if (srcList[temp] == null) {
                srcList[temp] = new ArrayList<>();
            }
            srcList[temp].add(src[i]);
        }
        //对每个桶中的数进行插入排序
        for (int i = 0; i < n; i++) {
            if (srcList[i] != null) {
                insert(srcList[i]);
            }
        }
        //把各个桶的排序结果合并
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (srcList[i] != null) {
                Iterator<Double> iterator = srcList[i].iterator();
                while (iterator.hasNext()) {
                    Double d = iterator.next();
                    src[count++] = d;
                }
            }
        }
    }

    /**
     * 用插入排序对每个桶进行排序
     *
     * @param list
     */
    public static void insert(ArrayList<Double> list) {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) < list.get(i - 1)) {
                Double temp = list.get(i);
                int j = i;
                while (j > 0 && list.get(j - 1) > temp) {
                    list.set(j--, list.get(j));
                }
                list.set(j, temp);
            }
        }
    }

    public static void main(String[] args) {
        Double[] src = Utils.randomDouble(20, 90, 10);
        Utils.print(src, "排序前");
        bucketSort(src);
        Utils.print(src, "排序后");
    }
}
