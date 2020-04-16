package com.io.high.structure.tree;

/**
 *	二叉树
 * @author sk
 * @email ma.keke@foxmail,com
 * @date 2019-11-04
 */
public class SimpleBinarySearchTree {

	/** 节点内容 */
	int data;
	/** 左节点 */
	SimpleBinarySearchTree left;
	/** 右节点 */
	SimpleBinarySearchTree right;
	/** 父节点 */
	SimpleBinarySearchTree parent;
	
	public SimpleBinarySearchTree(int data) {
		this.data = data;
		this.left = null;
		this.parent = null;
		this.right = null;
	}

	/**
	 * 插入节点
	 * @param root 当前节点
	 * @param data 插入的值
	 */
	public void insert(SimpleBinarySearchTree root, int data) {
		// 判断当前节点是否为空
		if (root == null) { return; }
		// 与当前节点比较值，大于则插到右节点，小于则插到左节点
		if(data > root.data) {
			// 插入右节点
			if(root.right == null) {
				root.right = new SimpleBinarySearchTree(data);
			} else {
				insert(root.right, data);
			}
		} else {
			// 插入左节点
			if(root.left == null) {
				root.left = new SimpleBinarySearchTree(data);
			}else {
				insert(root.left, data);
			}
		}
	}

	/**
	 * 查找节点信息
	 * @param root 当前节点
	 * @param data 查找值
	 */
	public void find(SimpleBinarySearchTree root, int data) {
		if(root != null) {
			// 大于则查找右边
			if(data > root.data) {
				find(root.right, data);
			// 小于则查找左边
			} else if(data < root.data) {
				find(root.left, data);
			} else {
				System.out.println("\n当前节点存在，值为：" + root.data);
				return ;
			}
		}
	}

	/**
	 * 前序遍历
	 * @param root 根节点
	 */
	public void pre(SimpleBinarySearchTree root) {
		if(root == null){ return; }
		System.out.print(root.data + " ");
		pre(root.left);
		pre(root.right);
	}


	/**
	 * 中序遍历
	 * @param root 根节点
	 */
	public void mid(SimpleBinarySearchTree root) {
		if(root == null){ return; }
		mid(root.left);
		System.out.print(root.data + " ");
		mid(root.right);
	}

	/**
	 * 后序遍历
	 * @param root 根节点
	 */
	public void post(SimpleBinarySearchTree root) {
		if(root == null){ return; }
		post(root.left);
		post(root.right);
		System.out.print(root.data + " ");
	}
	
	public static void main(String[] args) {
		// 快速排序，归并排序，二叉树排序
		int data[] = {4,5,9,1,2,3,10};
		// 第一个点作为跟结点
		SimpleBinarySearchTree root = new SimpleBinarySearchTree(data[0]);
		for(int i = 1 ; i < data.length ; i ++) {
			root.insert(root, data[i]);
		}
		System.out.println("前序遍历:");
		root.pre(root);
		System.out.println("\n中序遍历:");
		root.mid(root);
		System.out.println("\n后序遍历:");
		root.post(root);
		root.find(root, 1);
	}
}
