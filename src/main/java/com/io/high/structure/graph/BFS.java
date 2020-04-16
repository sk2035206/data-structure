package com.io.high.structure.graph;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 广度优先遍历
 * @author sk
 * @email ma.keke@foxmail,com
 * @date 2019-11-04
 */
public class BFS {

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

	public BFS(int n, int m, int dx, int dy, int[][] data, boolean[][] mark) {
		this.n = n;
		this.m = m;
		this.dx = dx;
		this.dy = dy;
		this.data = data;
		this.mark = mark;
	}

	/**
	 * 查找是否能到达
	 * @param x 当前x坐标
	 * @param y 当前y坐标
	 */
	public void bfs(int x, int y) {
		if(x < 1 || x > n || y < 1 || y > m) { return ; }
		if(x == dx && y == dy) {
			System.out.println("true");
			return ;
		}
		mark[x][y] = true;

		// 因为最多也就是 n * m 个点
		Queue<Point> queue = new ArrayBlockingQueue<>(n * m);
		Point start = new Point();
		start.x = x;
		start.y = y;
		queue.add(start);
		// ACM想到的
		int next[][] = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

		// 时间复杂度：O(n)
		while (!queue.isEmpty()) {
			// 拿出队列的第一个点
			Point point = queue.poll();
			for(int i = 0 ; i < 4; i++) {
				int nextx = point.x + next[i][0];
				int nexty = point.y + next[i][1];
				if(nextx < 1 || nextx > n || nexty < 1 || nexty > m) { continue; }
				// 表示可以走
				if(data[nextx][nexty] == 0 && !mark[nextx][nexty]) {
					// 表示可以到了 就不走了
					if(nextx == dx && nexty == dy) {
						System.out.println("true");
						return ;
					}
					Point newPoint = new Point();
					newPoint.x = nextx;
					newPoint.y = nexty;
					mark[nextx][nexty] = true;
					queue.add(newPoint);
				}
			}
			
		}
		System.out.println("false");
		return ;
	}

	class Point {
		/** 坐标X */
		int x;
		/** 坐标Y */
		int y;
	}

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
		BFS bfs = new BFS(n, m, dx, dy, data, mark);
		bfs.bfs(x, y);
	}
}
