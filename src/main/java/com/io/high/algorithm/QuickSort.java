package com.io.high.algorithm;

import com.io.utils.ArrayUtil;

import java.util.Arrays;

/**
 * 快速排序
 *
 * 稳定性：不稳定
 * 空间复杂度：O(1)
 * 时间复杂度：O(nlogn)
 *
 * @author sk
 * @date 2020-4-10
 */
public class QuickSort {

	public static void main(String[] args) {
		// 创建一个容量为10，值为 1 至 100 范围内的随机数组
		int data[] = ArrayUtil.createData(10, 100);
		System.out.println("排序前：" + Arrays.toString(data));
		quickSort(data,0,data.length - 1);
//		quickSort2(data,0,data.length - 1);
		System.out.println("排序后：" + Arrays.toString(data));
	}

	public static void quickSort(int data[], int left, int right) {
		// 终止条件
		if (left >= right) { return; }

		// base是我们的基准数，取序列的第一个,因为采用的递归所以不能用data[0]
		int base = data[left];
		// 初始化左右游标
		int leftIndex = left, rightIndex = right;

		while (leftIndex < rightIndex) {
			// 从后面往前找比基准数小的数
			while (leftIndex < rightIndex && data[rightIndex] >= base) {
				rightIndex--;
			}
			// 表示是找到有比之小
			if (leftIndex < rightIndex) {
				ArrayUtil.swap(data,leftIndex,rightIndex);
//				System.out.println("右边------" + "左右初始位置" + left + "--" + right + "→替换位置" + rightIndex);
//				System.out.println(Arrays.toString(data));
				leftIndex++;
			}
			// 从前面往后找比基准数大的数
			while (leftIndex < rightIndex && data[leftIndex] <= base) {
				leftIndex++;
			}
			// 是否大于下一个数字，是则互换位置
			if (leftIndex < rightIndex) {
				ArrayUtil.swap(data,leftIndex,rightIndex);
//				System.out.println("左边------" + "左右初始位置" + left + "--" + right + "→替换位置" + leftIndex);
//				System.out.println(Arrays.toString(data));
				rightIndex--;
			}
//			System.out.println("左右内循环结束位置" + leftIndex + "--" + rightIndex);
		}
//		System.out.println("基准坐标位置" + (leftIndex - 1));
		// 肯定是递归 分成了三部分,左右继续快排，注意要加条件不然递归就栈溢出了
		if (left < leftIndex) {
			quickSort(data, left, leftIndex - 1);
		}
		if (leftIndex < right) {
			quickSort(data, leftIndex + 1, right);
		}
	}

	private static void quickSort2(int[] data, int left, int right) {
			// 终止条件
			if (left >= right) { return; }

			// 以左边的元素为基准元素
			int base = data[left];
			// 初始化左右游标
			int i = left, j = right;

			while (i < j) {
				// 右边先走
				while (i < j && data[j] >= base){
					j--;
				}
				while (i < j && data[i] <= base) {
					i++;
				}
				if (i < j) {
					ArrayUtil.swap(data, i, j);
				}
			}
			// 注意，这一步必须要，填上最左边的坑
			data[left] = data[i];
			// 基准元素就位
			data[i] = base;
			// 递归操作左边部分
			quickSort(data, left, i - 1);
			// 递归操作右边部分
			quickSort(data, i + 1, right);
	}
}
