package com.io.base.structure.queue;

import java.util.Arrays;

/**
 * 自定义队列类 FIFO 先进先出
 * @author sk
 * @date 2019-10-30
 */
public class ArrayQueue {

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
	public ArrayQueue(int capacity){
		data = new int[capacity];
		cap = capacity;
	}

	/**
	 * 入列 O(1)
	 * @param m 入列数
	 */
	public void push(int m){
		//判断我们这个队列是不是已经满了
		if(tail == cap){
			// 编写你的数组移动逻辑
			// 在这里才去移动	最好的情况是O(1),最坏的情况下才是O(n);
			// 项目中如何来进行准确的估算呢？平均时间复杂度，n=1000，前999都是O(1) n*2/n,最坏的情况只有一次
			return;
		}
		data[tail] = m;
		tail++ ;
		size++;
	}

	/**
	 * 出队 O(1) 很大的空间浪费
	 * 想要减少浪费需要移动数组，这个时间复杂度：O(n)，我们在入队的时候如果没有空间了我们在集中移动一次
	 * @return  队列数据
	 */
	public void pop(){
		// 判断是否为空
		if(isEmpty()) {
			return;
		}
		data[head] = 0;
		head++;
		size--;
		// 编写你的数组移动逻辑
	}

	/**
	 * 判断时候为空
	 * @return 空则返回true
	 */
	public boolean isEmpty(){
		return head == tail ? true : false;
	}

	public static void main(String[] args) {
		ArrayQueue queue = new ArrayQueue(5);
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
