package com.io.base.structure.linked;

import java.util.HashMap;
import java.util.Map;

/**
 * LRU 无哨兵模式
 * @author sk
 * @date 2019-10-30
 */
public class LRUCache2<k, v> {

  /**
   * 容量
   */
  private int capacity;
  /**
   * 当前有多少节点的统计
   */
  private int count;
  /**
   * 缓存节点
   */
  private Map<k, Node<k, v>> nodeMap;
  /**
   * 节点头
   */
  private Node<k, v> head;
  /**
   * 节点尾
   */
  private Node<k, v> tail;
  /**
   * 构造容器
   *
   * @param capacity 指定的容量
   */
  public LRUCache2(int capacity) {
    // 容量小于1抛出异常
    if (capacity < 1) {
      throw new IllegalArgumentException(String.valueOf(capacity));
    }
    // 设置参数
    this.capacity = capacity;
    // 初始化节点容器
    this.nodeMap = new HashMap<>();
  }


  /**
   * 根据key获取节点
   *
   * @param key 键
   * @return 该键的节点信息
   */
  public Node<k, v> get(k key) {
    // 获取缓存中该key的节点信息
    Node<k, v> node = this.nodeMap.get(key);
    // 判断缓存中是否存在该值，存在则移动到链表头
    if (node != null) {
      moveNodeToHead(node);
    }
    return node;
  }

  /**
   * 写入节点
   *
   * @param key   键
   * @param value 值
   */
  public void put(k key, v value) {
    // 根据key获取当前节点
    Node<k, v> node = this.nodeMap.get(key);
    // 判断获取的节点是否为空
    if (node == null) {
      // 判断容器的节点总数是否已满，已满则移除节点
      if (this.count >= this.capacity) {
        removeNode();
      }
      // 将写入的节点放置到链表头
      node = new Node<>(key, value);
      addNode(node);
    } else {
      // 将当前写入节点移动至链表头
      moveNodeToHead(node);
    }
  }

  /**
   * 添加节点
   *
   * @param node 节点信息
   */
  public void addNode(Node<k, v> node) {
    // 添加节点信息到缓存容器
    this.nodeMap.put(node.key, node);
    // 判断是否为初次添加节点
    if (checkFirstNode(node)) { return; }
    // 添加节点到链表头
    addToHead(node);
    this.count++;
  }

  /**
   * 检查是否为初次添加节点
   * @param node 当前添加节点
   * @return true 首次添加 false 不是首次添加
   */
  private boolean checkFirstNode(Node<k,v> node) {
    if (this.head == null) {
      this.tail = node;
      this.head = node;
      // 增加节点总数量
      this.count++;
      return true;
    }
    return false;
  }

  /**
   * 移除节点
   */
  public void removeNode() {
    // 获取链表尾部上一个节点
    Node node = this.tail;
    // 从链表以及容器中移除该节点
    removeFromList(node);
    this.nodeMap.remove(node.key);
    // 减少节点总数量
    this.count--;
  }

  /**
   * 添加节点信息到头部
   *
   * @param node 节点信息
   */
  public void addToHead(Node<k, v> node) {
    // 获取链表头节点信息与新增节点的前后位置互换
    Node head = this.head;
    head.prev = node;
    node.next = head;
    this.head = node;
  }

  /**
   * 移动节点信息到头部
   *
   * @param node
   */
  public void moveNodeToHead(Node<k, v> node) {
    // 判断是否只有一个链表节点，是则直接返回
    if (node.prev == null) {
      return;
    }
    // 移除链表中的节点信息
    removeFromList(node);
    // 将节点移动到头部
    addToHead(node);
  }

  /**
   * 从链表中移除节点信息
   *
   * @param node
   */
  public void removeFromList(Node<k, v> node) {
    // 获取删除节点的上下节点信息
    Node prev = node.prev;
    Node next = node.next;
    // 修改前后指向节点信息
    prev.next = next;
    // 判断是否为最后一个节点,是则将上节点设为链接未节点
    if (next == null) {
      this.tail = prev;
    } else {
      next.prev = prev;
    }
    // 清空节点信息
    node.next = null;
    node.prev = null;

  }

  @Override
  public String toString() {
    // 判断当前链表是否有无数据
    if (this.count  == 0) {
      return "当前链表长度为：" + this.count;
    }
    String str = this.tail.toString();
    return "当前链表长度为：" + this.count + "\n链表内容为：" + str;
  }

  /**
   * 节点数据
   *
   * @param <k> 键
   * @param <v> 值
   */
  public class Node<k, v> {

    /** 键 */
    k key;
    /** 值 */
    v value;
    /** 上一个节点 */
    Node prev;
    /** 下一个节点 */
    Node next;

    /**
     * 带参构造函数
     *
     * @param key   键
     * @param value 值
     */
    public Node(k key, v value) {
      this.key = key;
      this.value = value;
    }

    @Override
    public String toString() {
      if (this.prev == null){
        return "[key:" + this.key + ",value:" + this.value + "], ";
      }
      String str = this.prev.toString();
      if (this.next == null) {
        return str + "[key:" + this.key + ",value:" + this.value + "]";
      }
      return str + "[key:" + this.key + ",value:" + this.value + "],";
    }
  }

  public static void main(String[] args) {
    LRUCache2 cache = new LRUCache2(5);
    cache.put("3","C");
    cache.put("2","B");
    cache.put("1","A");
    cache.put("2","B");
    cache.put("4","D");
    cache.put("3","C");
    System.out.println(cache.get("1"));
    System.out.println(cache.toString());
  }
}