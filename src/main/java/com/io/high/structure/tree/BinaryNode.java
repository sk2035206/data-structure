package com.io.high.structure.tree;

/**
 * 二叉树节点
 * @author make
 * @email ma.keke@foxmail,com
 * @date 2020/4/15
 */
public class BinaryNode {

    /** 节点内容 */
    int data;
    /** 左节点 */
    BinaryNode left;
    /** 右节点 */
    BinaryNode right;
    /** 父节点 */
    BinaryNode parent;

    public BinaryNode(int data) {
      this.data = data;
      this.left = null;
      this.right = null;
      this.parent = null;
    }

}
