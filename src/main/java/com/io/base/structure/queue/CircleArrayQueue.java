package com.io.base.structure.queue;

import java.util.Arrays;

/**
 * 约瑟夫问题
 * @author sk
 * @date 2019-10-30
 */
public class CircleArrayQueue {

	/** 队列数据 */
	private int[] data;
	/** 队列头位置 */
	private int head = 0;
	/** 队列尾位置 */
	private int tail = 0;
	/** 队列大小 */
	private int cap;
	/** 队列当前存放个数 */
	private int size;

	/**
	 * 初始化队列
	 * @param capacity 初始化大小
	 */
	public CircleArrayQueue(int capacity){
		data = new int[capacity];
		cap = capacity;
	}

	/**
	 * 入列
	 * @param m 入列数
	 */
	public void push(int m){
		// 判断是否队列已满
		if((tail + 1) % cap == head){
			// 编写你的数组移动逻辑
			// 在这里才去移动	最好的情况是O(1),最坏的情况下才是O(n);
			// 项目中如何来进行准确的估算呢？平均时间复杂度，n=1000，前999都是O(1) n*2/n,最坏的情况只有一次
			return ; 
		}
		data[tail] = m;
		//循环队列 tail = 7 长度越界了 因此使用(7 + 1) % 8 == 0
		tail = (tail + 1) % cap;
		size++;
	}

	/**
	 * 出队 O(1) 很大的空间浪费
	 * 想要减少浪费需要移动数组，这个时间复杂度：O(n)，我们在入队的时候如果没有空间了我们在集中移动一次
	 * @return  队列数据
	 */
	public int pop(){
		//判断是否为空
		if(isEmpty()) { return -1; };
		int m = data[head];
		// 移动数组
		for (int i = head; i < size;) {
			data[i] = data[++i];
		}
		head = (head + 1) % cap;
		tail--;
		size--;
		return m;
	}
	
	
	
	public boolean isEmpty(){
		if(head == tail) { return true; };
		return false;
	}

	public static void main(String[] args) {
		CircleArrayQueue queue = new CircleArrayQueue(5);
		if (queue.isEmpty()) {
			queue.push(10);
			queue.push(7);
			queue.push(5);
			System.out.println("初始化数据：" + Arrays.toString(queue.data));
			System.out.println("当前数据容量大小：" + queue.size);
			queue.pop();
			System.out.println("出队后数据：" + Arrays.toString(queue.data));
			System.out.println("当前数据容量大小：" + queue.size);
			queue.push(3);
			System.out.println("入队后数据：" + Arrays.toString(queue.data));
			System.out.println("当前数据容量大小：" + queue.size);
		}
	}

}