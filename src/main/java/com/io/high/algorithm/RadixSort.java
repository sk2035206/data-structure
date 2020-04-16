package com.io.high.algorithm;

import com.io.utils.ArrayUtil;

import java.util.Arrays;

/**
 * 基数排序
 *
 * 稳定性：稳定
 * 空间复杂度：O(M)
 * 时间复杂度：O(N*M)
 *
 * 先排低位数再排高位数 开放寻址方式，因此先确定桶的边界值
 * [70, 92, 53, 24, 65, 76, 17, 18, 68, 49]
 * [17, 18, 24, 49, 53, 65, 68, 70, 76, 92]
 *
 * @author make
 * @email ma.keke@foxmail,com
 * @date 2020/4/14
 */
public class RadixSort {

  public static void main(String[] args) {
    // 创建一个容量为10，值为 1 至 100 范围内的随机数组
    int data[] = ArrayUtil.createData(10, 100);
    System.out.println("排序前：" + Arrays.toString(data));
    radixSort(data, 0 , data.length, 3);
    System.out.println("排序后：" + Arrays.toString(data));
  }

  /**
   * 基数排序
   * @param list 数组
   * @param begin 开始位置
   * @param end 结束位置
   * @param digit 数值范围位数
   */
    public static void radixSort(int[] list, int begin, int end, int digit){
      // 桶基数
      final int base = 10;
      int i, j;
      int[] bucket = new int[end - begin];
      // 按照从低位到高位的顺序执行排序过程
      for (int d = 0; d < digit; d++) {
        // 存放各个桶的数据统计个数，每次都重新置空
        int[] count = new int[base];

        // 统计各个桶将要装入的数据个数
        for (i = begin; i < end; i++) {
          j = getDigit(list[i], d);
          count[j]++;
        }

        // 将边间值填充，count[i]表示第i个桶的右边界索引
        // 例如：[1, 0, 0, 0, 1, 1, 1, 2, 2, 2] → [1, 1, 1, 1, 2, 3, 4, 6, 8, 10]
        for (i = 1; i < base; i++) {
          count[i] = count[i] + count[i - 1];
        }

        // 将数据依次装入桶中，这里要从右向左扫描，保证排序稳定性  和开放寻址类似
        for (i = end - 1; i >= begin; i--) {
          j = getDigit(list[i], d);
          // 求出关键码的第k位的数字， 例如：576的第3位是5
          bucket[count[j] - 1] = list[i];
          // 放入对应的桶中，count[j]-1是第j个桶的右边界索引
          count[j]--; // 对应桶的装入数据索引减一
        }

        // 将已分配好的桶中数据再倒出来，此时已是对应当前位数有序的表
        for (i = begin, j = 0; i < end; i++, j++) {
          list[i] = bucket[j];
        }
        System.out.println("排序中：" + Arrays.toString(bucket));
      }
    }

  private static int getDigit(int data, int index) {
    int a[] = {1, 10, 100};
    return (data / a[index]) % 10;
  }

}
