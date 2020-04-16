package com.io.high.algorithm;

import java.util.Arrays;
import java.util.Random;

/**
 * 堆排序 top K 问题
 *
 * 稳定性：不稳定
 * 空间复杂度：O(1)
 * 时间复杂度：初始化0(n) 重建O(nlogn)
 *
 * @author sk
 * @email ma.keke@foxmail,com
 * @date 2019-11-04
 */
public class HeapSort {

	public static void main(String[] args) {
		// 前K个值
		int k = 5;
		int data[] = new int[k];
		// 开始时间
		long time = System.currentTimeMillis();
		Random random = new Random();
		// 容量计数器
		int countSize = 0;
		// 初始化标记
		boolean init = true;
		for (int i = 0; i < 100; i++) {
			int num = random.nextInt(1000);
			// 判断top值是否已经填满
			if (countSize < k) {
				data[countSize++] = num;
			} else {
				// 判断是否为初始化
				if (init) {
					// 初始化复杂度O(n)
					int j = k / 2 - 1;
					System.out.println("初始前：" + Arrays.toString(data));
					for (; j >= 0; j--) {
						minHeap(data, 0, k);
						System.out.println("初始化：" + Arrays.toString(data));
					}
					init = false;
				}
				// 小顶堆那么堆顶是最小的，如果当前的数比堆顶大，则替换堆顶，然后做一次堆化
				if (num > data[0]) {
					data[0] = num;
					minHeap(data, 0, k);
					System.out.println("排序中：" + Arrays.toString(data));
				}
			}
		}
		System.out.println("排序后：" + Arrays.toString(data));
		System.out.println("耗时：" + (System.currentTimeMillis() - time) + "ms");
	}

	/**
	 * 小顶堆 构建一个小顶堆 从上往下构建
	 * @param data 排序数组
	 * @param start 开始位置
	 * @param end 结束位置
	 */
	public static void minHeap(int data[], int start, int end) {
		int parent = start;
		// 下标是从0开始的就要加1，从1就不用
		int left = parent * 2 + 1;
		while (left < end) {
			// 左右子树最小值下标
			int minIndex = left;
			// 比较左右节点的大小
			if (left + 1 < end && data[left] > data[left + 1]) {
				minIndex = left + 1;
			}
			// 判断上级节点是否小于最小子节点,不是则交换
			if (data[parent] < data[minIndex]) {
				return;
			} else {
				data[parent] ^= data[minIndex];
				data[minIndex] ^= data[parent];
				data[parent] ^= data[minIndex];
				// 继续堆化
				parent = minIndex;
				left = (parent << 1) + 1;
			}
		}
	}

}
