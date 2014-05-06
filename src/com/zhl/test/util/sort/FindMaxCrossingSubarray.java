package com.zhl.test.util.sort;

import com.zhl.test.util.Utils;

/**
 * 求最大数组组合
 * @author zhanghanlin
 *
 */
public class FindMaxCrossingSubarray {
	
	public static void main(String[] args) {
		Integer[] src = Utils.random(-10, 10, 10);
		Utils.print(src,"原始数组");
		System.out.println("暴力解决（返回最大和）:" + maxSubSum(src, src.length - 1));
		SubArray subArray = findMaximumSubArray(src, 0, src.length - 1);
		System.out.println(subArray.toString());
	}
	
	/**
	 * 最大子数组问题--分治策略
	 * @param src	数组
	 * @param low	数组起始坐标
	 * @param high	数组结束坐标
	 */
	public static SubArray findMaximumSubArray(Integer[] src ,int low ,int high){
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
	 * @param src	数组
	 * @param low	起始坐标
	 * @param mid	中间坐标
	 * @param high	结束坐标
	 * @return
	 */
	public static SubArray findMaxCrossingSubarray(Integer[] src,int low,int mid,int high){
		//sum:目前最大和	leftSum:左侧最大和	rightSum:右侧最大和
		int sum = 0 , leftSum = 0 , rightSum = 0;
		//左侧最大值的索引,默认起始坐标	右侧最大值索引,默认中值加一
		int leftIndex = low, rightIndex = mid + 1;
		for (int i = mid; i > low; i--) {
			sum += src[i];
			if (sum > leftSum) {
				leftSum = sum;
				leftIndex = i;
			}
		}
		sum = 0;
		for (int i = mid + 1; i < high; i++) {
			sum += src[i];
			if (sum > rightSum) {
				rightSum = sum;
				rightIndex = i;
			}
		}
		return new SubArray(leftIndex, rightIndex, rightSum + leftSum);
	}
	
	static class SubArray{
		int low;	//开始坐标
		int high;	//结束坐标
		int sum;	//src[low]+src[low+1]... + src[high]
		public SubArray(int low,int high,int sum) {
			this.low = low;
			this.high = high;
			this.sum = sum;
		}
		public String toString(){
			return "最小下标：" + low + "，最大下标：" + high + "，总和：" + sum;
		}
	}
	
	/**
	 * 暴力解决
	 * @param src
	 * @param n
	 * @return
	 */
	public static int maxSubSum(Integer[] src,int n){
		int maxSum = 0;
		for (int i = 0; i < src.length; i++) {
			int thisSum = 0;
			for (int j = i; j < n; j++) {
				thisSum += src[j];
				if (thisSum > maxSum) {
					maxSum = thisSum;
				}
			}
		}
		return maxSum;
	}
}
