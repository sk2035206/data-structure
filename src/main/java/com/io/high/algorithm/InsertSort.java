package com.io.high.algorithm;

import com.io.utils.ArrayUtil;

import java.util.Arrays;

/**
 * 插入排序
 *
 * 稳定性：稳定
 * 空间复杂度：O(1)
 * 时间复杂度：O(n^2)
 *
 * @author sk
 * @email ma.keke@foxmail,com
 * @date 2019-11-04
 */
public class InsertSort {

	public static void main(String[] args) {
		// 创建一个容量为10，值为 1 至 100 范围内的随机数组
		int data[] = ArrayUtil.createData(10, 100);
		System.out.println("排序前：" + Arrays.toString(data));
		insertSort(data,data.length);
		System.out.println("排序后：" + Arrays.toString(data));
	}

	private static void insertSort(int[] data, int length) {
		//为什么i要从1开始？ 第一个不用排序，我们就把数组从i分开，0~I的认为已经排好序
		for(int i = 1; i < length; i++){
			int tmp = data[i];
			// 从前一个值开始循环比较起，从后往前遍历
			int j = i -1;
			for(; j >= 0; j--){
				// 判断上一个数是否大于当前数，大于则往前移动，小于则跳出，因为前面的都是排好序的
				if(data[j] > tmp){
					data[j + 1] = data[j];
				}else{
					// PS：break执行的越多，效率就越高
					break;
				}
			}
			data[j + 1] = tmp;
			System.out.println("第" +i +"次的排序结果为：" + Arrays.toString(data));
		}
	}

}
