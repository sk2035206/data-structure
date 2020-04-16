package com.io.base.structure.linked;

/**
 * 单链表节点
 * @author make
 * @email ma.keke@foxmail,com
 * @date 2020/4/9
 */
public class MyNode {

  /** 值 */
  int value;
  /** 下个节点 */
  MyNode next;

  MyNode(int value){
    this.value = value;
    this.next = null;
  }
}
