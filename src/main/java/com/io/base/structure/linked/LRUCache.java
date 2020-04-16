package com.io.base.structure.linked;

import java.util.HashMap;
import java.util.Map;

/**
 * 哨兵模式
 * LRU(Least Recently Used)，即* 最近最少使用 *，是一种缓存置换算法。
 * 在使用内存作为缓存的时候，缓存的大小一般是固定的。当缓存被占满，
 * 这个时候继续往缓存里面添加数据，就需要淘汰一部分老的数据，释放内存空间用来存储新的数据。
 * 这个时候就可以使用LRU算法了。其核心思想是：如果一个数据在最近一段时间没有被用到，那么将来被使用到的可能性也很小，所以就可以被淘汰掉。
 * @author sk
 * @date 2019-10-30
 */
public class LRUCache<k, v> {

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
   * 头节点
   */
  private Node<k, v> head;
  /**
   * 尾节点
   */
  private Node<k, v> tail;

  /**
   * 构造容器
   *
   * @param capacity 指定的容量
   */
  public LRUCache(int capacity) {
    // 容量小于1抛出异常
    if (capacity < 1) {
      throw new IllegalArgumentException(String.valueOf(capacity));
    }
    // 设置参数
    this.capacity = capacity;
    // 初始化节点容器以及头节点和尾节点，利用哨兵模式减少判断头结点和尾节点为空的代码
    this.nodeMap = new HashMap<>();
    Node headNode = new Node(null, null);
    Node tailNode  = new Node(null, null);
    // 将链表头尾节点相互连接
    headNode.next = tailNode;
    tailNode.prev = headNode;
    this.head = headNode;
    this.tail = tailNode;

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
    // 添加节点到链表头
    addToHead(node);
    this.count++;
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
    Node head = this.head.next;
    head.prev = node;
    node.next = head;
    node.prev = this.head;
    this.head.next = node;



  }

  /**
   * 移动节点信息到头部
   *
   * @param node
   */
  public void moveNodeToHead(Node<k, v> node) {
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
    next.prev = prev;
    // 将节点前后信息清空
    node.prev = null;
    node.next = null;

  }

  @Override
  public String toString() {
    // 判断当前链表是否有无数据
    if (this.count  == 0) {
      return "当前链表长度为：" + this.count;
    }
    String str = this.tail.prev.toString();
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
      if (this.prev.key == null) {
        return "[key:" + this.key + ",value:" + this.value + "],";
      }
      String str = this.prev.toString();
      if (this.next.key == null) {
        return str + "[key:" + this.key + ",value:" + this.value + "]";
      }
      return str + "[key:" + this.key + ",value:" + this.value + "],";
    }
  }

  public static void main(String[] args) {
    LRUCache cache = new LRUCache(16);
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