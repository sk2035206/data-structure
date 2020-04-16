package com.io.base.structure.stack;

/**
 * 栈公用接口
 * @author sk
 * @date 2019-10-30
 */
public interface MyStack<Item> {

	/**
	 * 入栈 时间复杂度 O(1)
	 * @param item 入栈数据
	 * @return 入栈数据
	 */
	Item push(Item item);

	/**
	 * 出栈
	 * @return 出栈数据
	 */
	Item pop();

	/**
	 * 获取使用长度
	 * @return 栈使用长度
	 */
	int size();

	/**
	 * 判断是否为空
	 * @return
	 */
	boolean isEmpty();
}
