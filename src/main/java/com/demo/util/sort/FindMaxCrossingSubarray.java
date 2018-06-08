package com.demo.util.sort;

import com.demo.util.Utils;

/**
 * 求最大数组组合
 * 场景：给出N天的股票价格信息,算出在这N天中的哪几天买收益最大(连续)
 *
 * @author zhanghanlin
 */
public class FindMaxCrossingSubarray {

    public static void main(String[] args) {
        Integer[] src = Utils.random(-10, 10, 10);
        Utils.print(src, "原始数组");
        System.out.println("暴力解决（返回最大和）:" + maxSubSum(src));
        SubArray subArray = findMaximumSubArray(src, 0, src.length - 1);
        System.out.println(subArray.toString());
    }

    /**
     * 最大子数组问题--分治策略
     * 伪代码
     * FIND-MAXIMUM-SUMARRAY(A, low, high)
     * --if high == low
     * ----return (lowd, high, A[low])		//base case: only one element
     * --else mid = [(low + high) / 2]
     * ----(left-low, left-high, left-sum) =
     * ------FIND-MAXIMUM-SUMARRAY(A, low, mid)
     * ----(right-low, right-high, right-sum) =
     * ------FIND-MAXIMUM-SUMARRAY(A, mid + 1, high)
     * ----(cross-low, cross-high, cross-sum) =
     * ------FIND-MAX-CROSSING-SUBARRAY(A, low, mid, high)
     * ----if left-sum ≥ right-sum and left-sum ≥ cross-sum
     * ------return (left-low, left-high, left-sum)
     * ----elseif right-sum ≥ left-sum and right-sum ≥ cross-sum
     * ------return (right-low, right-high, right-sum)
     * ----else return (cross-low, cross-high, cross-sum)
     *
     * @param src  数组
     * @param low  数组起始坐标
     * @param high 数组结束坐标
     */
    public static SubArray findMaximumSubArray(Integer[] src, int low, int high) {
        if (low == high) {
            return new SubArray(low, high, src[low]);
        }
        int mid = (low + high) / 2;
        SubArray leftSub = findMaximumSubArray(src, low, mid);
        SubArray rightSub = findMaximumSubArray(src, mid + 1, high);
        SubArray crossSub = findMaxCrossingSubarray(src, low, mid, high);
        if (leftSub.sum >= rightSub.sum && leftSub.sum >= crossSub.sum) {
            return leftSub;
        }
        if (rightSub.sum >= leftSub.sum && rightSub.sum >= crossSub.sum) {
            return rightSub;
        }
        return crossSub;
    }

    /**
     * 求跨越子数组的最大值
     * 伪代码
     * FIND-MAX-CROSSING-SUBARRAY(A, low, mid, high)
     * --left-sum = -∞
     * --sum = 0
     * --for i = mid downto low
     * ----sum = sum + A[i]
     * ----if sum > left-sum
     * ------left-sum = sum
     * ------max-left = i;
     * --right-sum = -∞
     * --sum = 0
     * --for j = mid + 1 to high
     * ----sum = sum + A[j]
     * ----if sum > right-sum
     * ----max-right = j
     * --return (max-left, max-right, left-sum + right-sum)
     *
     * @param src  数组
     * @param low  起始坐标
     * @param mid  中间坐标
     * @param high 结束坐标
     * @return
     */
    public static SubArray findMaxCrossingSubarray(Integer[] src, int low, int mid, int high) {
        //sum:目前最大和	leftSum:左侧最大和	rightSum:右侧最大和
        int sum = 0, leftSum = 0, rightSum = 0;
        //左侧最大值的索引,默认起始坐标	右侧最大值索引,默认mid加一
        int leftIndex = low, rightIndex = mid + 1;
        for (int i = mid; i >= low; i--) {
            sum += src[i];
            if (sum > leftSum) {
                leftSum = sum;
                leftIndex = i;
            }
        }
        sum = 0;
        for (int i = mid + 1; i <= high; i++) {
            sum += src[i];
            if (sum > rightSum) {
                rightSum = sum;
                rightIndex = i;
            }
        }
        return new SubArray(leftIndex, rightIndex, rightSum + leftSum);
    }

    static class SubArray {
        /**
         * 开始坐标
         */
        int low;
        /**
         * 结束坐标
         */
        int high;
        /**
         * src[low]+src[low+1]... + src[high]
         */
        int sum;

        public SubArray(int low, int high, int sum) {
            this.low = low;
            this.high = high;
            this.sum = sum;
        }

        @Override
        public String toString() {
            return "最小下标：" + low + "，最大下标：" + high + "，总和：" + sum;
        }
    }

    /**
     * 暴力解决
     *
     * @param src
     * @return
     */
    public static int maxSubSum(Integer[] src) {
        int maxSum = 0;
        for (int i = 0; i < src.length; i++) {
            int thisSum = 0;
            for (int j = i; j < src.length; j++) {
                thisSum += src[j];
                if (thisSum > maxSum) {
                    maxSum = thisSum;
                }
            }
        }
        return maxSum;
    }
}
