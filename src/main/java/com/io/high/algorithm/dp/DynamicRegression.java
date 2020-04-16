package com.io.high.algorithm.dp;

/**
 * 动态归划
 * 背包问题
 *
 * 小偷去某商店盗窃，背有一个背包，容量是50kg，现在有以下物品（物品不能切分,且只有一个），请问小偷应该怎么拿才能得到最大的价值？
 * 物品表	 重量    价值   每kg性价比
 * 物品1   10kg    60元   60 / 10 = 6
 * 物品2   20kg    100元  100 / 20 = 5
 * 物品3   40kg    120元  120 / 40 = 3
 *
 * @author sk
 * @email ma.keke@foxmail,com
 * @date 2019-11-04
 */
public class DynamicRegression {
	public static void main(String[] args) {
		// 重量列表
		int weigth[] = {10,20,40};
		// 价格列表
		int value [] ={60,100,120};
		// 重量
		int w = 50;
		// 物品个数
		int n = 3;
		// 初始化矩阵表
		int dp[][] = new int[n+1][w+1];

		//每次加的物品
		for(int i = 1; i<= n; i++){
			//分割的背包
			for(int cw = 1; cw <= w; cw ++){
				//表示这个物品可以装进去
				if(weigth[i - 1] <= cw){
					dp[i][cw] = Math.max(value[i-1] + dp[i-1][cw-weigth[i-1]], dp[i-1][cw]);
				}else{
					dp[i][cw] = dp[i-1][cw];
				}
			}
		}
		System.out.println("最大价值为：" + dp[n][w]);
		
	}
}
