package com.io.base.structure.linked;

/**
 * 双链表节点
 * @author make
 * @email ma.keke@foxmail,com
 * @date 2020/4/9
 */
public class DoubleNode {
  /** 值 */
  int value;
  /** 下一个节点 */
  DoubleNode next;
  /** 上一个节点 */
  DoubleNode pre;

  DoubleNode(int value){
    this.value = value;
    this.next = null;
    this.pre = null;
  }

}
