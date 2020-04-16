package com.io.base.structure.array;

/**
 * 自定义数组
 * @author sk
 * @date 2019-10-30
 */
public class ArrayListTest {

	/** 默认数组长度 */
	private static final int DEFAULT_SIZE = 10;
	/** 数组长度 */
	private int size;
	/** 数组数据 */
	private int[] data;
	/** 当前已经存的数据大小 */
	private int count;

	/**
	 * 初始化 数组
	 */
	public ArrayListTest(){
		this.size = DEFAULT_SIZE;
		data = new int[DEFAULT_SIZE];
		count = 0;
	}
	
	public void print(){
		System.out.println("count:" + count);
		for(int i = 0 ; i < count ; i++){
			System.out.print(data[i] + " ");
		}
		System.out.println();
	}

	/**
	 * 插入数据 时间复杂度 O(n);
	 * @param loc 指定的插入位置
	 * @param n 插入的数据
	 */
	public void add(int loc,int n){
		if(count++ < size) {
			for(int i = size - 1; i > loc ; i --){
				//把数据往后移一个
				data[i] = data[i - 1];
			}
			data[loc] = n;
		}
		//扩容 会把size*2 负载因子:0.75
	}

	/**
	 * 插入数据 时间复杂度 O(n);
	 * @param n 指定的插入位置
	 */
	public void add(int n){
		if(count < size) {
			data[count++] = n;
		}
		//扩容 会把size*2 负载因子:0.75
	}

	/**
	 * 删除数据 复杂度O(n)
	 * @param loc 需要删除的数据位置
	 */
	public void remove(int loc){
		for(int i = loc ; i < size ; i++){
			//判断删除的书是否为最后一个数
			if(i == size - 1){
				data[i] = 0;
			}else{
				data[i] = data[i + 1];
			}
		}
		count -- ;
	}

	/**
	 * 更新数据 复杂度 O(1)
	 * @param loc 更新的位置
	 * @param n  更新数据
	 */
	public void update(int loc,int n){
		data[loc] = n;
	}

	/**
	 * 获取数据 复杂度 O(1)
	 * @param loc 更新的位置
	 */
	public int get(int loc){
		return data[loc];
	}

	/**
	 * 获取数据长度
	 */
	public int size(){
		return size;
	}

	public static void main(String[] args) {
		ArrayListTest list = new ArrayListTest();
		list.add(1);
		list.add(2);
		list.add(2,4);
		System.out.println("当前容器容量大小" + list.size());
		list.print();
		list.update(2, 3);
		list.print();
		list.remove(3);
		list.print();
	}
}
