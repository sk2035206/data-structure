package com.io.high.structure.graph;

import java.util.Scanner;

/**
 * 最短路径 （迪杰斯特拉）
 * 单源最短路径算法
 * @author sk
 * @email ma.keke@foxmail,com
 * @date 2019-11-04
 */
public class Djstl {

	public static void main(String[] args) {
		// n表示点数，m表示边数，x表示我们要的起点
		int n, m, x;
		Scanner cin = new Scanner(System.in);
		n = cin.nextInt();
		m = cin.nextInt();
		x = cin.nextInt();

		// 表示点到点的邻接矩阵
		int value[][] = new int[n + 1][n + 1];
		// 存最短路径的
		int dis[] = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			dis[i] = Integer.MAX_VALUE;
			// 初始化我们的地图
			for (int j = 1; j <= n; j++) {
				// 表示没有路的
				value[i][j] = -1;
				if (i == j) {
					value[i][j] = 0;
				}
			}
		}
		// 表示要输入m个边
		for (int i = 0; i < m; i++) {
			// xx yy v表示从xx到yy有一条路 长度是v
			int xx = cin.nextInt();
			int yy = cin.nextInt();
			int v = cin.nextInt();
			value[xx][yy] = v;
			// dis其实在第一个点时候可以在这里计算
			if (xx == x) {
				dis[yy] = v;
			}
		}
		search(x, dis, value, n);

	}

	/**
	 * 搜索
	 * @param x 起点
	 * @param dis 最短路径
	 * @param value 矩阵图
	 * @param n 点数
	 */
	public static void search(int x, int dis[], int value[][], int n) {
		boolean mark[] = new boolean[n + 1];
		mark[x] = true;
		dis[x] = 0;
		int count = 1;
		while (count <= n) {	//O（n^2）
			int loc = 0; // 表示新加的点
			int min = Integer.MAX_VALUE;
			// 求dis里面的最小值 优先队列,堆 logn
			for (int i = 1; i <= n; i++) {
				if (!mark[i] && dis[i] < min) {
					min = dis[i];
					loc = i;
				}
			}
			// 表示没有可以加的点了
			if (loc == 0) { break; }
			mark[loc] = true;
			// 只需要关注 我们加进来的点 能有哪些路径就可以了，不用扫描所有的dis 最好的情况应该可以达到o(nlogn),最坏的情况才是O(n^2)
			for (int i = 1; i <= n; i++) {
				if (value[loc][i] != -1 && (dis[loc] + value[loc][i] < dis[i])) {
					dis[i] = dis[loc] + value[loc][i];
				}
			}
			count++;
		}
		for (int i = 1; i <= n; i++) {
			System.out.println(x + "到 " + i + "的最短路径为 ：" + dis[i]);
		}

	}

}
