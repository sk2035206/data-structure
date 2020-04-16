package com.io.high.algorithm;

import com.io.utils.ArrayUtil;

import java.util.Arrays;

/**
 * 冒泡排序
 *
 * 稳定性：稳定
 * 空间复杂度：O(1)
 * 时间复杂度：优化前 O(n^2) 优化后O(logn)
 *
 * @author sk
 * @email ma.keke@foxmail,com
 * @date 2019-11-04
 */
public class BubbleSort {

	public static void main(String[] args) {
		// 创建一个容量为10，值为 1 至 100 范围内的随机数组
		int data[] = ArrayUtil.createData(10, 100);
		System.out.println("排序前：" + Arrays.toString(data));
		bubbleSort(data,data.length);
		System.out.println("排序后：" + Arrays.toString(data));
	}

	private static void bubbleSort(int[] data, int size) {
		// 排序的次数 最后一次不需要排序，因此循环的长度为 size - 1
		for (int i = 0; i < size - 1; i++) {
			boolean flag = false;
			// 由于排序后的值就固定了，因此每次循环的长度为 size - 1 - i
			for (int j = 0; j < size - 1 - i; j++) {
				// 是否大于下一个数字，是则互换位置
				if (data[j] > data[j + 1]) {
					ArrayUtil.swap(data, j, j + 1);
					flag = true;
				}
			}
			if(!flag) { break; }
		}
	}
}