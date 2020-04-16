package com.io.high.structure.tree;

import java.util.Scanner;

/**
 *	二叉搜索树
 * @author sk
 * @email ma.keke@foxmail,com
 * @date 2019-11-04
 */
public class BinarySearchTree {

	public static void main(String[] args) {
		BinarySearchTree binarySearchTree = new BinarySearchTree();
		BinaryNode root;
		Scanner input = new Scanner(System.in);
		int t = 1;
		System.out.println("二叉搜索树,开始进行构建~~");
		System.out.println("请输入根节点：");
		int rootData = input.nextInt();
		root = new BinaryNode(rootData);
		System.out.println("节点" + t + "构建完成，请继续输入节点信息:输入-1跳出构建");
		while (true) {
			int data = input.nextInt();
			if (data == -1){ break; }
			binarySearchTree.insert(root, data);
			t++;
			System.out.println("节点" + t + "构建完成，请继续输入节点信息:输入-1跳出构建");
		}
		binarySearchTree.show(root);
		binarySearchTree.midOrde(root);
		System.out.println("←中序遍历结果\n二叉搜索树构建完成！");
		while(true) {
			System.out.println("请输入删除的节点：");
			int key = input.nextInt();
			root = binarySearchTree.remove(root, key);
			binarySearchTree.show(root);
			if(root == null) {
				System.out.println("根节点已删除，程序结束~~");
				break;
			}
		}
	}

	/**
	 * 查找节点信息
	 * @param root 根节点
	 * @param key 查找值
	 * @return 节点信息
	 */
	public BinaryNode find(BinaryNode root, int key) {
		BinaryNode current = root;
		while (current != null) {
			if (key < current.data) {
				current = current.left;
			} else if (key > current.data) {
				current = current.right;
			} else {
				return current;
			}
		}
		return null;
	}

	/**
	 * 获取树的最大深度
	 * @param root 当前节点
	 * @return 树的深度
	 */
	public int getTreeDepth(BinaryNode root) {
		return root == null ? 0 : (1 + Math.max(getTreeDepth(root.left), getTreeDepth(root.right)));
	}

	/**
	 * 写入数组
	 * @param currNode 当前节点信息
	 * @param rowIndex 行坐标
	 * @param columnIndex 列坐标
	 * @param res 结果集
	 * @param treeDepth 树深度
	 */
	private void writeArray(BinaryNode currNode, int rowIndex, int columnIndex, String[][] res, int treeDepth) {
		// 判断是否为空
		if (currNode == null) { return; }
		// 写入坐标值
		res[rowIndex][columnIndex] = String.valueOf(currNode.data);
		// 当前层级
		int currLevel = ((rowIndex + 1) / 2);
		// 判断是否处于最低层
		if (currLevel == treeDepth) { return; }
		// 间隙值
		int gap = treeDepth - currLevel - 1;

		// 写入左节点
		if (currNode.left != null) {
			res[rowIndex + 1][columnIndex - gap] = "/";
			writeArray(currNode.left, rowIndex + 2, columnIndex - gap * 2, res, treeDepth);
		}

		// 写入右节点
		if (currNode.right != null) {
			res[rowIndex + 1][columnIndex + gap] = "\\";
			writeArray(currNode.right, rowIndex + 2, columnIndex + gap * 2, res, treeDepth);
		}
	}

	public void show(BinaryNode root) {
		if (root == null) {
			System.out.println("EMPTY!");
			return ;
		}
		// 获取当前树的深度
		int treeDepth = getTreeDepth(root);

		// 数组行数
		int arrayHeight = treeDepth * 2 - 1;
		// 数组列数
		int arrayWidth = (2 << (treeDepth - 2)) * 3 + 1;
		// 初始化矩阵
		String[][] res = new String[arrayHeight][arrayWidth];
		for (int i = 0; i < arrayHeight; i++) {
			for (int j = 0; j < arrayWidth; j++) {
				res[i][j] = " ";
			}
		}
		// 写入根节点
		writeArray(root, 0, arrayWidth / 2, res, treeDepth);

		// 将数据填入矩阵图，构建搜索树
		for (String[] line : res) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < line.length; i++) {
				sb.append(line[i]);
				if (line[i].length() > 1 && i <= line.length - 1) {
					i += line[i].length() > 4 ? 2 : line[i].length() - 1;
				}
			}
			System.out.println(sb.toString());
		}
	}

	/**
	 * 插入数据
	 * @param root 当前节点
	 * @param data 插入数据
	 */
	public void insert(BinaryNode root, int data) {
		if (data > root.data) {
			if (root.right != null) {
				insert(root.right, data);
			} else {
				BinaryNode newNode = new BinaryNode(data);
				newNode.parent = root;
				root.right = newNode;
			}
		} else {
			if (root.left != null) {
				insert(root.left, data);
			} else {
				BinaryNode newNode = new BinaryNode(data);
				newNode.parent = root;
				root.left = newNode;
			}
		}
	}


	/**
	 * 查找后继节点
	 * @param node 当前节点
	 * @return 后继节点
	 */
	public BinaryNode findSuccessor(BinaryNode node) {
		if (node.right == null) {
			return node;
		}
		// 上级节点
		BinaryNode pre = node.right;
		// 当前节点
		BinaryNode cur = node.right;
		// 当前节点为空时就将上级节点返回
		while (cur != null) {
			pre = cur;
			cur = cur.left;
		}
		return pre;
	}


	/**
	 * 删除节点
	 * @param root 根节点
	 * @param data 删除节点的数据
	 * @return 根节点
	 */
	public BinaryNode remove(BinaryNode root, int data) {
		BinaryNode delNode = find(root, data);
		if (delNode == null) {
			System.out.println("该节点不存在！");
			return root;
		}
		// 1.判断改节点是否子节点都为空
		if (delNode.left == null && delNode.right == null) {
			// 是否为根节点
			if (delNode == root) {
				root = null;
				// 当前删除节点是否大于上级节点，大于则删除上级的右节否则删除左节点
			} else if (delNode.data > delNode.parent.data) {
				delNode.parent.right = null;
			} else {
				delNode.parent.left = null;
			}
			// 2.左右子节点都不为空
		} else if (delNode.left != null && delNode.right != null) {
			// 查找当前的后继节点
			BinaryNode successor = findSuccessor(delNode);
			// 当前节点的左节点设置为后继节点的左节点，并绑定关系
			successor.left = delNode.left;
			successor.left.parent = successor;
			// 后继接点的右节点不为空且不是当前节点的子节点
			if (successor.right != null && successor.parent != delNode) {
				// 将后继节点的右节点设为父节点
				successor.right.parent = successor.parent;
				// 后继节点的右节点设为父节点的左节点
				successor.parent.left = successor.right;
				// 当前节点的右节点设为后继节点的右节点
				successor.right = delNode.right;
				// 当前节点的右节点父级设为后继节点的父级
				successor.right.parent = successor;
			// 后继节点的右节点为空且不是当前节点的子节点
			}else if(successor.right == null && successor.parent != delNode) {
				// 将后继节点指向父级的指针置空
				successor.parent.left = null;
				// 将后继节点的右节点设置为当前节点的右节点
				successor.right = delNode.right;
				// 将右节点的父级节点指向自己
				successor.right.parent = successor;
			}
			// 当前节点是否为根节点
			if (delNode == root) {
				// 将后继节点设为根节点
				successor.parent = null;
				root = successor;
				return root;
			}
			// 将后继节点的父级节点设为当前节点的父级
			successor.parent = delNode.parent;
			// 判断当前节点是否为右节点
			if (delNode.data > delNode.parent.data) {
				// 当前节点父级的右节点设为后继节点
				delNode.parent.right = successor;
			} else {
				// 当前节点父级的左节点设为后继节点
				delNode.parent.left = successor;
			}
		} else {
			// 当前节点是否有子右节点
			if (delNode.right != null) {
				// 判断是否为根节点
				if (delNode == root) {
					// 将右节点设为根节点
					root = delNode.right;
					return root;
				}
				// 将子右节点的父级设为自己的父级
				delNode.right.parent = delNode.parent;
				// 判断当前节点是否为右节点
				if (delNode.data > delNode.parent.data) {
					// 当前节点父级的右节点设为子右节点
					delNode.parent.right = delNode.right;
				} else {
					// 当前节点父级的左节点设为子右节点
					delNode.parent.left = delNode.right;
				}
			// 当前节点有子左节点
			} else {
				// 是否为根节点
				if (delNode == root) {
					// 将左节点设为根节点
					root = delNode.left;
					return root;
				}
				// 将子左节点的父级设为自己的父级
				delNode.left.parent = delNode.parent;
				// 判断当前节点是否为右节点
				if (delNode.data > delNode.parent.data) {
					// 当前节点父级的右节点设为子右节点
					delNode.parent.right = delNode.left;
				} else {
					// 当前节点父级的左节点设为子右节点
					delNode.parent.left = delNode.left;
				}
			}
		}
		return root;
	}

	/**
	 * 中序排序
	 * @param root 当前节点
	 */
	public void midOrde(BinaryNode root) {
		if (root != null) {
			midOrde(root.left);
			System.out.print(root.data);
			midOrde(root.right);
		}
	}

}
