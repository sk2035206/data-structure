package com.io.base.algorithm;

import org.junit.Test;

import java.util.ArrayList;

/**
 * 递归算法(斐波那契)
 * @author sk
 * @date 2019-11-04
 */
public class FibonacciTest {

	/** 初始化数组 */
	private static int[] data;

	/**
	 * 根据当前数球下游两者之和
	 * @param n 当前数
	 * @return 下游的两者数之和
	 * 1 1 2 3 5 8 13
	 * f(n)=f(n-1)+f(n-2)
	 * 分析一段代码好坏，有两个指标，时间复杂度和空间复杂度 该方法都是：O(2^n)
	 */
	public static int fab(int n) {
		// 递归的终止条件
		if (n <= 2) { return 1; }
		// 继续递归的过程
		return fab(n - 1) + fab(n - 2);
	}

	/**
	 * 优化一：使用循环代替递归 时间复杂度：O(n)
	 * @param n 当前数
	 * @return 下游的两者数之和
	 */
	public static int noFab(int n) {
		int a = 1,b = 1,c = 0;
		for (int i = 3; i <= n; i++) {
			c = a + b;
			a = b;
			b = c;
		}
		return c;
	}

	/**
	 * 优化二：用数组来做缓存 时间复杂度和空间复杂度 都是：O(n)
	 * @param n 当前数
	 * @return 下游的两者数之和
	 */
	public static int cacheFab(int n) {
		if (n <= 2) { return 1; }
		if (data[n] > 0) {
			return data[n];
		}
		// 继续递归的过程
		int res = cacheFab(n - 1) + cacheFab(n - 2);
		data[n] = res;
		return res;
	}


	/**
	 * 优化三：尾递归（高级数论）
	 * 时间复杂度和空间复杂度 都是： O(n)
	 * @param pre 上上一次运算出来的结果
	 * @param res 上一次运算出来结果
	 * @param n 是肯定有的
	 * @return 计算结果之和
	 */
	public static int tailFab(int pre,int res,int n) {
		if (n <= 2) { return res; }
		return tailFab(res, pre + res, n - 1);
	}

	@Test
	public void testFab(){
		for (int i = 1; i <= 36; i++) {
			long start = System.currentTimeMillis();
			System.out.println(i + ":" + fab(i) + "所花费的时间为" + (System.currentTimeMillis() - start) + "ms");
		}
		System.out.println("===============循环替代的情况===================");
		long start = System.currentTimeMillis();
		System.out.println("结果为:" + noFab(36) + " 所花费的时间为" + (System.currentTimeMillis() - start) + "ms");

		System.out.println("===============加入缓存的情况===================");
		data = new int[46];
		start = System.currentTimeMillis();
		System.out.println("结果为:" + cacheFab(36) + " 所花费的时间为" + (System.currentTimeMillis() - start) + "ms");

		System.out.println("===============用尾递归的情况===================");
		start = System.currentTimeMillis();
		System.out.println("结果为:" + tailFab(1,1,36) + " 所花费的时间为" + (System.currentTimeMillis() - start) + "ms");
	}

	/**
	 * 求N的阶乘 用普通递归怎么写 5!=5*4*3*2*1
	 * @param n 指定数
	 * @return 当前数的阶乘
	 */
	public static long fac(long n) {
		if (n <= 1) { return 1; }
		new ArrayList<>(1024 * 1024);
		return n * fac(n - 1);
	}

	/**
	 * 求N的阶乘 用尾递归怎么写 5!=5*4*3*2*1
	 * @param n 当前阶乘数
	 * @param res 当前结果值
	 * @return 计算结果
	 */
	public static long tailFac(long n, long res) {
		if (n <= 1) { return res; }
		new ArrayList<>(1024 * 1024);
		return tailFac(n - 1, n * res);
	}

	@Test
	public void testFac(){
		long start = System.currentTimeMillis();
		System.out.println("普通递归阶乘:" + fac(20) + "所花费的时间为" + (System.currentTimeMillis() - start) + "ms");
		long tailStart = System.currentTimeMillis();
		System.out.println("尾递归阶乘:" + tailFac(20, 1) + "所花费的时间为" + (System.currentTimeMillis() - tailStart) + "ms");

	}

}
