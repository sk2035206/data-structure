package com.io.high.algorithm;

import com.io.utils.ArrayUtil;

import java.util.Arrays;

/**
 * 计数排序
 *
 * 稳定性：稳定
 * 空间复杂度：O(n+k)
 * 时间复杂度：O(n+k)
 *
 * @author make
 * @email ma.keke@foxmail,com
 * @date 2020/4/14
 */
public class CountSort {

  public static void main(String[] args) {
    // 创建一个容量为10，值为 1 至 100 范围内的随机数组
    int data[] = ArrayUtil.createData(10, 100);
    System.out.println("排序前：" + Arrays.toString(data));
    countSort(data,data.length);
    System.out.println("排序后：" + Arrays.toString(data));
  }

  public static void countSort(int[] data, int length) {
    // 获取最大数值
    int maxValue = ArrayUtil.maxValue(data);
    int[] countData = new int[maxValue + 1];
    int dataIndex = 0;
    // 计数
    for (int i = 0; i < length; i++) {
      countData[data[i]]++;
    }
    // 排序
    for (int j = 0; j < countData.length; j++) {
      while (countData[j] > 0) {
        // 将下标做为值放入原始数组中
        data[dataIndex++] = j;
        // 减少一次计数
        countData[j]--;
      }
    }
  }
}
