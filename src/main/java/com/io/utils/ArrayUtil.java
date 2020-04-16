package com.io.utils;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 数组工具类
 * @author make
 * @email ma.keke@foxmail,com
 * @date 2020/4/13
 */
public class ArrayUtil {

  /**
   * 创建一个容量为size，值为 1 至 range 范围内的随机数组
   * @param capacity 数组容量
   * @param range 随机范围
   * @return 随机数组
   */
  public static int[] createData(int capacity, int range){
    /** 初始化数据 */
    int data[] = new int[capacity];
    // 放数据
    for (int i = 0; i < data.length; i++) {
      data[i] = ThreadLocalRandom.current().nextInt(range);
    }
    return data;
  }

  /**
   * 交换位置
   * @param data 数组
   * @param left 左值
   * @param right 右值
   */
  public static void swap(int data[], int left, int right) {
    data[left] ^= data[right];
    data[right] ^= data[left];
    data[left] ^= data[right];
    maxValue(data);
  }

  /**
   * 获取数组中的最大值
   * @param data 数组
   * @return 最大值
   */
  public static int maxValue(int[] data) {
    // 获取最大数值
    int maxValue = data[0];
    for (int i = 1; i < data.length; i++) {
      if (data[i] > maxValue) {
        maxValue = data[i];
      }
    }
    return maxValue;
  }

}
