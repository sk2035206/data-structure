package com.io.high.structure.tree;

import java.util.Arrays;

/**
 * 二叉树排序树
 * @author sk
 * @email ma.keke@foxmail,com
 * @date 2019-11-04
 */
public class BinaryTreeSort {

  /** 根节点 */
  private Node root;
  /** 统计个数 */
  private int count;
  /** 排序当前位置标识 */
  private int foot;
  /** 排序内容存放组 */
  private Object [] reObj;

  /**
   * 添加排序内容
   * @param data 排序内容
   */
  public void add(Object data) {
    // 实现Comparable接口用于排序
    Comparable com = (Comparable) data;
    // 设置节点是为了排序
    Node newNode = new Node(com);
    // 判断是否有无根节点
    if (this.root == null) {
      this.root = newNode;
    } else {
      this.root.addNode(newNode);
    }
    this.count++;
  }


  /** 输出节点信息 */
  public Object [] toArray() {
    if (this.root == null) {
      return null;
    }
    this.reObj = new Object[this.count];
    this.root.toArrayNode();
    return this.reObj;
  }
// *****************************************华丽的分割线*****************************************
  /**
   * 节点信息
   * @author sk
   */
  private class Node {

    /** 需要排序的内容 */
    private Comparable data;

    /** 左分支 */
    private Node left;

    /** 右分支 */
    private Node right;

    /**
     * 构造方法（将内容放入排序属性中）
     * @param data 排序内容
     */
    public Node(Comparable data) {
      this.data = data;
    }

    /**
     * 将比较的同类放入对应的节点
     * @param newNode 添加的同类
     */
    public void addNode(Node newNode) {
      // 比较前后两个字符串的Unicode码的差值,判断放入那个分支，小于则放入右分支，大于放入左分支
      if (this.data.compareTo(newNode.data) < 0) {
        // 判断是否还有下级节点，往下逐级排查，知道没有分支为止
        if (this.right == null) {
          this.right = newNode;
        } else {
          this.right.addNode(newNode);
        }
      } else {
        if (this.left == null) {
          this.left = newNode;
        } else {
          this.left.addNode(newNode);
        }
      }
    }

    /** 输出节点信息 */
    public void toArrayNode() {
      // 判断此节点是否有分支，有则逐级判断，直到最外节点
      if (this.left != null) {
        this.left.toArrayNode();
      }
      // 先将最左分支排序内容放入排序数组内，然后依次向上逐级放入
      BinaryTreeSort.this.reObj[BinaryTreeSort.this.foot++] = this.data;

      if (this.right != null) {
        this.right.toArrayNode();
      }
    }
  }

  public static void main(String[] args) {
    BinaryTreeSort treeSort = new BinaryTreeSort();
    treeSort.add("C");
    treeSort.add("A");
    treeSort.add("D");
    treeSort.add("B");
    System.out.println(Arrays.toString(treeSort.toArray()));
  }
}
