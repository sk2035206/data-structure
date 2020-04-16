package com.io.high.algorithm;

import com.io.utils.ArrayUtil;

import java.util.Arrays;

/**
 * 希尔排序
 *
 * 稳定性：不稳定
 * 空间复杂度：O(1)
 * 时间复杂度：最好 nlogn  最坏 O(n^2)
 *
 * @author sk
 * @email ma.keke@foxmail,com
 * @date 2019-11-04
 */
public class ShellSort {
  public static void main(String[] args) {
    // 创建一个容量为10，值为 1 至 100 范围内的随机数组
    int data[] = ArrayUtil.createData(10, 100);
    System.out.println("排序前：" + Arrays.toString(data));
    shellSort(data);
    System.out.println("排序后：" + Arrays.toString(data));
  }

  /**
   * 对数据进行排序
   * @param data 需要排序的数组
   */
  public static void shellSort(int [] data){
    // 排序数组总长度
    int length = data.length;
    // 用于记录排序次数
    int count = 1;
    // 循环增量间隔
    int group = length / 2;
    System.out.println("本次增量为：" + group);
    for (; group > 0; group /= 2) {
      // 对每组进行排序
      for (int i = group; i < length; i++) {
        // 插入排序
        insert(data, group, i);
      }
      System.out.println("第" + count++ + "次排序：" + Arrays.toString(data));
    }
  }

  public static void insert(int [] data, int group, int index){
    int tmp = data[index];
    int j;
    for (j = index - group; j >= 0 && tmp < data[j]; j-= group) {
      data[j + group] = data[j];
    }
    data[j + group] = tmp;
  }
}