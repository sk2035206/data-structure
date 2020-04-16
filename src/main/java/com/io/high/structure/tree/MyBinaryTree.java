package com.io.high.structure.tree;

import lombok.Data;

/**
 *	二叉树
 * @author sk
 * @email ma.keke@foxmail,com
 * @date 2019-11-04
 */
public class MyBinaryTree {
	
	public static void main(String[] args) {
		// 构建二叉树
		MyNode H = new MyNode('H', null, null);
		MyNode K = new MyNode('K', null, null);
		MyNode D = new MyNode('D', null, null);
		MyNode G = new MyNode('G', H, K);
		MyNode F = new MyNode('F', G, null);
		MyNode C = new MyNode('C', D, null);
		MyNode E = new MyNode('E', null, F);
		MyNode B = new MyNode('B', null, C);
		MyNode A = new MyNode('A', B, E);
		// 三大遍历方式 遍历复杂度为O(n) → N^2 O(2*n) => O(n);
		MyBinaryTree binaryTree = new MyBinaryTree();
		System.out.println("\n前序遍历");
		binaryTree.pre(A);
		System.out.println("\n中序遍历");
		binaryTree.mid(A);
		System.out.println("\n后序遍历");
		binaryTree.post(A);
		
	}
	
	/**
	 * 前序遍历 根(输出) 左 右
	 * @param root 根节点
	 */
	public void pre(MyNode root){
		System.out.print(root.getData());
		if(root.getLeft() != null){
			pre(root.getLeft());
		}
		if(root.getRight() != null){
			pre(root.getRight());
		}
	}

	/**
	 * 中序遍历  左 根(输出)  右
	 * @param root 根节点
	 */
	public void mid(MyNode root){
		if(root.getLeft() != null){
			mid(root.getLeft());
		}
		System.out.print(root.getData());
		if(root.getRight() != null){
			mid(root.getRight());
		}
	}

	/**
	 * 后序遍历  左  右 根(输出)
	 * @param root 根节点
	 */
	public void post(MyNode root){
		if(root.getLeft() != null){
			post(root.getLeft());
		}
		if(root.getRight() != null){
			post(root.getRight());
		}
		System.out.print(root.getData());
	}
}

@Data
class MyNode {

	/** 节点内容 */
	private char data;
	/** 左节点 */
	private MyNode left;
	/** 右节点 */
	private MyNode right;

	public MyNode(char data, MyNode left, MyNode right) {
		this.setData(data);
		this.setLeft(left);
		this.setRight(right);
	}
}
