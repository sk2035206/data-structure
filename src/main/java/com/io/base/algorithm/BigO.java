package com.io.base.algorithm;

/**
 * 时间复杂度
 * @author sk
 * @date 2020-04-07
 */
public class BigO {
	public static void main(String[] args) {

		// O(常量) => O(1)
		int a = 1;
		// 这里运行几次？ 3次 O(3) ? => O(1)
		for(int i = 0 ;i < 3;i++){
			a = a + 1;
		}

		// 表示n是未知
		int n = Integer.MAX_VALUE;
		int i = 1;
		// i的值：2 4 8 16 32,=》2^0,2^1,2^2,2^3,.....2^n
		// O(logn) n一定是一个未知的，如果n是已知的，那么就是O(1)
		// 2^x = n => 求出x就是我们运行的次数 => x = log2n => 计算机忽略掉常数 => x = logn => 时间复杂度O(logn)
		while( i <= n){
			i = i * 2;
		}

		// 双层未知循环
		for(i = 0 ; i < n;i++){
			// n次
			for(int j = 0 ; j < n ;j ++){
				//运行了多少次？		n * n => O(n^2)
				a = a + 1;
			}
		}

		// 外面的循环次数是确定的 O(n) n次，1 2 3 4 。。。n
		for(i = 0 ; i < n;i++){
			// 里面的循环次数 i = n 运行1次 → i= n - 1 运行2次 → 1,2,3 …… n次
			for(int j = i ; j < n ;j ++){
				// 最后里面这层要运行多少次 ？ 1 + 2 + 3 + …… + n = n * (n + 1) / 2 => (n^2 + n) / 2 => 计算机忽略掉常数 O(n^2)
				a = a +1;
			}
		}

	}
}
