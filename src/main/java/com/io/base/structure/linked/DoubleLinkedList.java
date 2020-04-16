package com.io.base.structure.linked;

/**
 * 自定义双向链表
 * @author sk
 * @date 2019-10-30
 */
public class DoubleLinkedList {

	/** 头节点 */
	private DoubleNode tail;
	/** 尾节点 */
	private DoubleNode head;

	/** 初始化构造 */
	DoubleLinkedList(){
		head = null;
		tail = null;
	}

	/**
	 * 插入节点
	 * @param data 插入数据
	 */
	public void inserHead(int data){
		DoubleNode newNode = new DoubleNode(data);
		// 判断是否为第一个接节点
		if(head == null){
			tail = newNode;
		}else{
			head.pre = newNode;
			newNode.next = head;
		}
		head = newNode;
	}

	/**
	 * 删除头节点
	 */
	public void deleteHead(){
		if(head == null) {
			return ;
		}
		// 是否只有一个节点
		if(head.next == null){
			tail = null;
		}else{
			// 将次节点的上级指针置空
			head.next.pre = null;	
		}
		// 次节点赋值为头节点
		head = head.next;
	}

	/**
	 * 删除指定内容的节点
	 * @param data 删除节点的内容
	 */
	public void deleteKey(int data){
		// 获取头节点
		DoubleNode current = head;
		// 循环查找节点内容
		while (current.value != data) {
			if (current.next == null) {
				System.out.println("没找到节点");
				return ;
			}
			current = current.next;
		}
		// 指向下个就表示删除第一个
		if (current == head) {
			deleteHead();
		} else {
			// 将上个节点的下个节点指针指向当前节点的下个节点
			current.pre.next = current.next;
			//判断删除的是否为尾部
			if(current == tail){
				tail = current.pre;
				current.pre = null;
			}else{
				// 将下个节点的上级节点指针指向当前节点的上级节点
				current.next.pre = current.pre;
			}
		}
	}

	/**
	 * 输出链表数组信息
	 */
	public void print(String msg){
		System.out.println(msg);
		DoubleNode cur = head;
		while(cur != null){
			System.out.print(cur.value + " ");
			cur = cur.next;
		}
		System.out.println();
	}

	public static void main(String[] args) {
		DoubleLinkedList myList = new DoubleLinkedList();
		myList.inserHead(5);
		myList.inserHead(7);
		myList.inserHead(10);
		myList.print("插入数据初始值：");
		myList.deleteKey(7);
		myList.print("删除指定位置链表值:");
		myList.deleteHead();
		myList.print("删除头部节点链表值:");
	}
}
