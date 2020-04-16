package com.io.base.structure.linked;

/**
 * 自定义LinkedList
 * @author sk
 * @date 2019-10-30
 */
public class MyLinkedList {

	/** 链表头节点 */
	private MyNode head;
	/** 链表长度 */
	private int size = 0;

	/**
	 * 插入链表的头部 空间复杂度O(1)
	 * @param data 插入的数据
	 */
	public void insertHead(int data){
		MyNode newNode = new MyNode(data);
		// 栈内存的引用
		newNode.next = head;
		head = newNode;
		size++;
	}

	/**
	 * 插入链表的中间 空间复杂度O(n)
	 * @param data 插入的数据
	 * @param position 插入的位置
	 */
	public void insertNth(int data,int position){
		// 判断是否为插入头部
		if(position == 0) {
			insertHead(data);
		}else{
			MyNode cur = head;
			// 因为是单向链表，需要获取插入节点的上级节点来进行插入，所以从 i = 1 开始循环
			for(int i = 1; i < position; i++){
				cur = cur.next;
			}
			MyNode newNode = new MyNode(data);
			// 将新加的下级节点指向找出的节点,保证不断链
			newNode.next = cur.next;
			// 将找出的节点下级节点指向新加节点
			cur.next = newNode;
			size++;
		}
	}

	/**
	 * 删除头节点 空间复杂度O(1)
	 */
	public void deleteHead(){
		head = head.next;
		size--;
	}

	/**
	 * 删除指定节点 空间复杂度O(n)
	 * @param position 删除位置
	 */
	public void deleteNth(int position){
		// 判断是否为头节点
		if(position == 0) {
			deleteHead();
		}else{
			MyNode cur = head;
			// 找出要删除节点的上个节点
			for(int i = 1; i < position ; i ++){
				cur = cur.next;
			}
			//cur.next 表示的是删除的点，后一个next就是我们要指向的
			cur.next = cur.next.next;
			size--;
		}
	}

	/**
	 * 查找指定节点 空间复杂度O(n)
	 * @param position 查找位置
	 */
	public int get(int position){
		MyNode cur = head;
		for (int i = 0; i < position; i++) {
			cur = cur.next;
		}
		return cur.value;
	}

	/**
	 * 输出链表数组信息
	 */
	public void print(){
		MyNode cur = head;
		while(cur != null){
			System.out.print(cur.value + " ");
			cur = cur.next;
		}
		System.out.println();
	}

	public static void main(String[] args) {
		MyLinkedList myList = new MyLinkedList();
		myList.insertHead(5);
		myList.insertHead(7);
		myList.insertHead(10);
		System.out.println("当前链表长度为：" + myList.size);
		// 10 -> 7 -> 5
		myList.print();
		myList.insertNth(11, 1);
		// 10 ->11 -> 7 -> 5
		myList.print();
		System.out.println("位置2的数值是：" + myList.get(2));
		myList.deleteNth(0);
		// 11 -> 7 -> 5
		myList.print();
		myList.deleteHead();
		// 7 -> 5
		myList.print();
		myList.deleteNth(1);
		// 7
		myList.print();
		System.out.println("当前链表长度为：" + myList.size);
	}

}
