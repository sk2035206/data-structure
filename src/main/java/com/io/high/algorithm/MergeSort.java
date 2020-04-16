package com.io.high.algorithm;

import com.io.utils.ArrayUtil;

import java.util.Arrays;
/**
 * 归并排序
 *
 * 稳定性：稳定
 * 空间复杂度：O(n)
 * 时间复杂度：O(nlogn)
 *
 * 归并排序是建立在归并操作上的一种有效的排序算法,该算法是采用分治法（Divide and Conquer）的一个非常典型的应用。
 * 将已有序的子序列合并，得到完全有序的序列；即先使每个子序列有序，再使子序列段间有序。
 * 若将两个有序表合并成一个有序表，称为二路归并。归并排序是一种稳定的排序方法。
 *
 * @author sk
 * @email ma.keke@foxmail,com
 * @date 2019-11-04
 */
public class MergeSort {
	public static void main(String[] args) {
		// 创建一个容量为10，值为 1 至 100 范围内的随机数组
		int data[] = ArrayUtil.createData(10, 100);
		System.out.println("排序前：" + Arrays.toString(data));
		mergeSort(data, 0, data.length - 1);
		System.out.println("排序后：" + Arrays.toString(data));
	}

	/**
	 *
	 * @param data 当前数组
	 * @param left 当前数据左边界值
	 * @param right 当前数据右边界值
	 */
	public static void mergeSort(int[] data, int left, int right) {
		// 判断是否相等，相等则表示只有一个数了，不需要再往下拆
		if (left < right) {
			int mid = (left + right) / 2;
			mergeSort(data, left, mid);
			mergeSort(data, mid + 1, right);
			// 分完了 接下来就要进行合并，也就是我们递归里面归的过程
			meger(data, left, mid, right);
		}
	}

	/**
	 * 合并内容
	 * @param data 排序内容
	 * @param left 当前合并数据左边界值
	 * @param mid 当前合并数据中界值
	 * @param right 当前合并数据右边界值
	 */
	public static void meger(int[] data, int left, int mid, int right) {
		// 借助一个临时数组用来保存合并的数据
		int[] temp = new int[data.length];

		// 表示的是左边的第一个数的位置
		int point1 = left;
		// 表示的是右边的第一个数的位置
		int point2 = mid + 1;

		// 当前临时数据位置
		int loc = left;
		// 将当前两个支路的数据排序
		while(point1 <= mid && point2 <= right){
			if(data[point1] < data[point2]){
				temp[loc++] = data[point1++];
			}else{
				temp[loc++] = data[point2++];
			}
		}
		// 将正式数据中未在临时数据中排序的数放入临时数组中，
		while(point1 <= mid){
			// 右支路全部小于左支路最小的
			temp[loc++] = data[point1++];
		}
		while(point2 <= right){
			// 右支路含有大于左支路最大的数
			temp[loc++] = data[point2++];
		}
		// 将该支路的临时数据同步到正式数据中
		for(int i = left; i <= right; i++){
			data[i] = temp[i];
		}
	}
}
