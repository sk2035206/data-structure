package com.io.high.structure.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 赫夫曼树
 *
 * @author sk
 * @email ma.keke@foxmail,com
 * @date 2019-11-04
 */
public class HuffmenTree {

	public static void main(String[] args) {
		// 权重数组
		Map<Character, Integer> weights = new HashMap<>();
		// 一般来说：动态的加密，最开始是不知道里面有什么内容的。我们需要一个密码本，往往就是某个字典。
		// 如果是英文就用英文字典，统计次数。需要改变时就可以换密码本
		// 静态的文件。针对性的做编码.图像加密,没有特性的---hash加密（MD5）
		weights.put('A', 3);
		weights.put('B', 24);
		weights.put('C', 85);
		weights.put('D', 1);
		weights.put('E', 34);
		weights.put('F', 4);
		weights.put('G', 12);

		HuffmenTree huffmenTree = new HuffmenTree(weights);
		huffmenTree.creatTree();
		huffmenTree.code();
		String str = "ACEG";
		System.out.println("编码后的值：");
		char chars[] = str.toCharArray();
		String encode = huffmenTree.encode(chars);
		System.out.println(encode);
		System.out.println("解码后的值：");
		String decode = huffmenTree.decode(encode);
		System.out.println(decode);

	}

	/** 根节点 */
	HfmNode root;
	/** 叶子节点 */
	List<HfmNode> leafs;
	/** 叶子节点的权重 */
	Map<Character, Integer> weights;
	/** 编码字典表 */
	Map<Character, String> codingTable;

	public HuffmenTree(Map<Character, Integer> weights) {
		this.weights = weights;
		leafs = new ArrayList<>();
	}

	/**
	 * 解码
	 * @param enCode 需要编码的字符串
	 * @return 编码结果
	 */
	public String decode(String enCode) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < enCode.length();) {
			// 每次循环都重新赋值
			HfmNode current = root;
			do {
				// 判断是否为左节点
				if (enCode.charAt(i) == "0".charAt(0)) {
					current = current.left;
				} else {
					current = current.right;
				}
				i++;
				// 当前节点的左子节点为空时说明找到一个编码
			} while (current.left != null);
			buffer.append(current.chars);
		}
		return buffer.toString();
	}

	/**
	 * 编码
	 * @param chars 需要编码的字符串
	 * @return 编码结果
	 */
	public String encode(char[] chars) {
		StringBuffer buffer = new StringBuffer();
		for (char c : chars) {
			buffer.append(codingTable.get(c));
		}
		return buffer.toString();
	}

	/**
	 * 叶子节点进行编码
	 */
	public void code() {
		Map<Character, String> codeMap = new HashMap<>();
		for (HfmNode node : leafs) {
			String code = "";
			// 叶子节点肯定只有一个字符
			Character key = new Character(node.chars.charAt(0));
			// 只有一个点
			HfmNode current = node;
			do {
				// 说明当前点是否为左边
				if (current.parent != null && current == current.parent.left) {
					code = "0" + code;
				} else {
					code = "1" + code;
				}
				current = current.parent;
				// parent == null就表示到了根节点
			} while (current.parent != null);
			codeMap.put(key, code);
			System.out.println(key + ":" + code);
		}
		codingTable =  codeMap;
	}

	public void creatTree() {
		// 拿出所有的点
		Character keys[] = weights.keySet().toArray(new Character[0]);
		// jdk底层的优先队列
		PriorityQueue<HfmNode> priorityQueue = new PriorityQueue<>();
		for (Character c : keys) {
			HfmNode hfmNode = new HfmNode();
			hfmNode.chars = c.toString();
			// 权重
			hfmNode.fre = weights.get(c);
			// 首先把我们的优先队列初始化进去
			priorityQueue.add(hfmNode);
			leafs.add(hfmNode);
		}

		int len = priorityQueue.size();
		// 每次找最小的两个点合并
		for (int i = 1; i < len; i++) {
			// 每次取优先队列的前面两个 就一定是两个最小的
			HfmNode n1 = priorityQueue.poll();
			HfmNode n2 = priorityQueue.poll();
			// 权重节点
			HfmNode newNode = new HfmNode();
			// 我们把值赋值一下，也可以不复制
			newNode.chars = n1.chars + n2.chars;
			// 把权重相加
			newNode.fre = n1.fre + n2.fre;
			// 维护出树的结构
			newNode.left = n1;
			newNode.right = n2;
			n1.parent = newNode;
			n2.parent = newNode;
			// 将权重节点添加入优先队列
			priorityQueue.add(newNode);
		}
		// 最后这个点就是我们的根节点
		root = priorityQueue.poll();
		System.out.println("构建完成");
	}

}
