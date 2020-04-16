package com.io.high.structure.graph;

import java.util.Scanner;

/**
 * 深度优先遍历
 * @author sk
 * @email ma.keke@foxmail,com
 * @date 2019-11-04
 */
public class DFS {

	/** 地图的行 */
	int n;
	/** 地图的地图的列行 */
	int m;
	/** 安琪的位置x */
	int dx;
	/** 安琪的位置y */
	int dy;
	/** 邻接矩阵 */
	int data[][];
	/** 标记数据 走过的位置 */
	boolean mark[][];
	/** 要走的最小步数 */
	int minStep = Integer.MAX_VALUE;
	/**  */
	int next[][] = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	public DFS(int n, int m, int dx, int dy, int[][] data, boolean[][] mark) {
		this.n = n;
		this.m = m;
		this.dx = dx;
		this.dy = dy;
		this.data = data;
		this.mark = mark;
	}

	/**
	 *
	 * @param x 我的x位置
	 * @param y 我的y位置
	 * @param step 当前走过的路径长度
	 */
	public void dfs(int x, int y, int step) {
		//枚举了所有的路径
		if (x == dx && y == dy) {
			if (step < minStep) { minStep = step; }
			return;
		}
		for (int i = 0; i < 4; i++) {
			int nextx = x + next[i][0];
			int nexty = y + next[i][1];
			if (nextx < 1 || nextx > n || nexty < 1 || nexty > m) { continue; }
			// 表示可以走 每个点都有4个方向，
			if (data[nextx][nexty] == 0 && !mark[nextx][nexty]) {
				// 这里有三行代码
				mark[nextx][nexty] = true;
				dfs(nextx, nexty, step + 1);
				// 回溯
				mark[nextx][nexty] = false;
			}
		}
	}

	/**
	 * 0 0 1 0
	 * 0 0 0 0
	 * 0 0 0 0
	 * 0 1 0 0
	 * 0 0 0 1
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);
		int n = 5;
		int m = 4;

		int data[][] = new int[n + 1][m + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				data[i][j] = cin.nextInt();
			}
		}
		int dx = cin.nextInt();
		int dy = cin.nextInt();
		
		boolean mark[][] = new boolean[n+1][m+1];
		int x = cin.nextInt();
		int y = cin.nextInt();

		// 我的起始位置
		mark[x][y] = true;
		DFS dfs = new DFS(n, m, dx, dy, data, mark);
		dfs.dfs(x, y, 0);
		System.out.println(dfs.minStep);
	}
}

