package com.io.high.structure.tree;

import java.util.Arrays;

/**
 * 红黑树
 *
 * 节点性质
 * 1.根结点都是黑色节点
 * 2.每个结点不是红色就是黑色
 * 3.每个叶子节点都是黑色的空节点(null)，也就是说，叶子节点不存储数据
 * 4.每个节点，从该节点到达其可达叶子节点的所有路径，都包含相同数目的黑色节点
 * 5.每个红色节点的两个子节点都是黑色,不可能有连在一起的红色结点（黑色的就可以)
 *
 * 变色规则：
 * 当前父节点是红色，且它的叔叔节点也是红色
 * 把父节点设为黑色，叔叔节点设为黑色，爷爷节点设为红色
 * 把指针定义到爷爷结点设为当前要操作的
 *
 * 左旋：
 * 当前父结点是红色，叔叔是黑色的时候，且当前的结点是右子树
 * 左旋以父结点作为左旋。指针变换到父结点
 *
 * 右旋;
 * 当前父结点是红色，叔叔是黑色的时候，且当前的结点是左子树
 * 左旋以父结点作为左旋。指针变换到父结点
 *
 * @author sk
 * @email ma.keke@foxmail,com
 * @date 2019-11-04
 */
public class RedBlackTree {

	/** 红色 */
	private final int R = 0;
	/** 黑色 */
	private final int B = 1;
	/** 无色 */
	private final int N = -1;
	/** 初始化叶子节点 */
	private final Node nil = new Node(N);
	/** 根节点 */
	private Node root = nil;

	public static void main(String[] args) {
		RedBlackTree bst = new RedBlackTree();
		bst.creatTree();
	}

	/** 创建树 */
	public void creatTree() {
		int data[]= {23, 32, 15, 221, 3};
		Node node;
		System.out.println(Arrays.toString(data));
		for(int i = 0 ; i < data.length ; i++) {
			node = new Node(data[i]);
			insert(node);
		}
		printTree(root);
	}

	/**
	 * 插入节点
	 * @param node 节点数据
	 */
	private void insert(Node node) {
		Node temp = root;
		if (root == nil) {
			root = node;
			node.color = B;
			node.parent = nil;
		} else {
			node.color = R;
			while (true) {
				if (node.key < temp.key) {
					if (temp.left == nil) {
						temp.left = node;
						node.parent = temp;
						break;
					} else {
						temp = temp.left;
					}
				} else if (node.key >= temp.key) {
					if (temp.right == nil) {
						temp.right = node;
						node.parent = temp;
						break;
					} else {
						temp = temp.right;
					}
				}
			}
			fixTree(node);
		}
	}

	/**
	 * 重建树
	 * @param node 节点
	 */
	private void fixTree(Node node) {
		while (node.parent.color == R) {
			Node y;
			if (node.parent == node.parent.parent.left) {
				y = node.parent.parent.right;

				if (y != nil && y.color == R) {
					node.parent.color = B;
					y.color = B;
					node.parent.parent.color = R;
					node = node.parent.parent;
					continue;
				}
				if (node == node.parent.right) {
					node = node.parent;
					rotateLeft(node);
				}
				node.parent.color = B;
				node.parent.parent.color = R;
				rotateRight(node.parent.parent);
			} else {
				y = node.parent.parent.left;
				if (y != nil && y.color == R) {
					node.parent.color = B;
					y.color = B;
					node.parent.parent.color = R;
					node = node.parent.parent;
					continue;
				}
				if (node == node.parent.left) {
					node = node.parent;
					rotateRight(node);
				}
				node.parent.color = B;
				node.parent.parent.color = R;
				rotateLeft(node.parent.parent);
			}
		}
		root.color = B;
	}

	/**
	 * 左旋
	 * @param node 当前节点
	 */
	void rotateLeft(Node node) {
		if (node.parent != nil) {
			if (node == node.parent.left) {
				node.parent.left = node.right;
			} else {
				node.parent.right = node.right;
			}
			node.right.parent = node.parent;
			node.parent = node.right;
			if (node.right.left != nil) {
				node.right.left.parent = node;
			}
			node.right = node.right.left;
			node.parent.left = node;
		} else {
			Node right = root.right;
			root.right = right.left;
			right.left.parent = root;
			root.parent = right;
			right.left = root;
			right.parent = nil;
			root = right;
		}
	}

	/**
	 * 右旋
	 * @param node fixTree
	 */
	void rotateRight(Node node) {
		if (node.parent != nil) {
			if (node == node.parent.left) {
				node.parent.left = node.left;
			} else {
				node.parent.right = node.left;
			}

			node.left.parent = node.parent;
			node.parent = node.left;
			if (node.left.right != nil) {
				node.left.right.parent = node;
			}
			node.left = node.left.right;
			node.parent.right = node;
		} else {
			Node left = root.left;
			root.left = root.left.right;
			left.right.parent = root;
			root.parent = left;
			left.right = root;
			left.parent = nil;
			root = left;
		}
	}

	/**
	 * 输出树内容
	 * @param node 节点
	 */
	public void printTree(Node node) {
		if (node == nil) {
			return;
		}
		printTree(node.left);
		System.out.print(node.toString());
		printTree(node.right);
	}

	/**
	 * 树节点
	 * @author sk
	 */
	private class Node {

		/** 数据 */
		int key;
		/** 颜色 */
		int color = B;
		/** 左节点 */
		Node left = nil;
		/** 右节点 */
		Node right = nil;
		/** 父节点 */
		Node parent = nil;

		Node(int key) { this.key = key; }

		@Override
		public String toString() {
			return "Node [key=" + key + ", color=" + color + ", left=" + left.key + ", right=" + right.key + ", p="
					+ parent.key + "]" + "\r\n";
		}

	}
}
