package com.io.high.algorithm;

import com.io.utils.ArrayUtil;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 选择排序
 *
 * 稳定性：不稳定
 * 空间复杂度：O(1)
 * 时间复杂度：O(n^2)
 *
 * @author make
 * @email ma.keke@foxmail,com
 * @date 2020/4/13
 */
public class SelectSort {
  public  static void main(String[] args) {
    // 创建一个容量为10，值为 1 至 100 范围内的随机数组
    int data[] = ArrayUtil.createData(10, 100);
    System.out.println("排序前：" + Arrays.toString(data));
    selectSort(data);
    System.out.println("排序后：" + Arrays.toString(data));
  }

  /**
   * 选择排序
   * @param data 排序数组
   */
  private static void selectSort(int[] data) {
    for (int i = 0,k =0; i < data.length; i++, k = i){
      // 这一层查找后面最小值的下标
      for (int j = i+1; j <data.length; j++) {
        // 这个改为小与符合即为从大到小
        if (data[k] > data[j]) {
          k = j;
        }
      }
      // 优化稳定性
      if( i != k) {
        // 交换值
        ArrayUtil.swap(data, i, k);
      }
    }
  }
}
