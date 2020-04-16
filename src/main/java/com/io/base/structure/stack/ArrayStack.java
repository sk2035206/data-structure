package com.io.base.structure.stack;

/**
 * 自定义栈类 FILO 先进后出
 * @author sk
 * @date 2019-10-30
 */
public class ArrayStack<Item> implements MyStack<Item>{

	/** 栈数据源 */
	private Item [] data;
	/** 初始的元素个数 */
	private int count = 0;

	/**
	 * 初始构造函数
	 * @param cap
	 */
	public ArrayStack(int cap) {
		data = (Item[]) new Object[cap];
	}

	@Override
	public Item push(Item item) {
		judgeSize();
		data[count++] = item;
		return item;
	}

/** 判断元素大小是否超出数组大小 */
	private void judgeSize(){
		// 判断元素大小是否已经超出了数组的个数
		if( count >= data.length){
			// 对当前栈进行动态扩容
			resize(2 * data.length);
			// 判断剩余数据是否超过
		}else if(count > 0 && count <= data.length / 2){
			//  对当前栈进行动态缩容
			resize(data.length / 2);
		}
	}

	/**
	 * 栈扩容 复杂度O（n）
	 * @param size 扩容长度
	 */
	private void resize(int size){
		// 创建临时栈数组
		Item[] temp = (Item[]) new Object[size];
		// 迁移原始栈数据
		for(int i = 0 ; i < count; i ++){
			temp[i] = data[i];
		}
		// 赋值数据
		data = temp;
	}

	@Override
	public Item pop() {
		// 判断当前栈是否为空
		if(isEmpty()){
			return null;
		}
		//item[n--]
		//item[--n]
		// 获取最后进来的栈数据
		Item item = data[--count];
		// 清空出栈数据空间
		data[count] = null;
		return item;
	}

	@Override
	public int size() {
		return count;
	}

	@Override
	public boolean isEmpty() {
		return count == 0;
	}

}
