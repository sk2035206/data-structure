package com.io.base.structure.array;

/**
 * 自定义List
 * @author sk
 * @date 2019-10-31
 */
public interface MyList<E> {

	/** 新增 */
	void add(E e);

	/** 根据下标删除 */
	void remove(int i);

	/** 根据对象删除 */
	void remove(Object e);

	/** 根据下标获取指定对象 */
	E get(int i);

	/** 获取当前容器对象数量 */
	int size();

	/** 判断容器是否为空 */
	boolean isEmpty();
	
}
