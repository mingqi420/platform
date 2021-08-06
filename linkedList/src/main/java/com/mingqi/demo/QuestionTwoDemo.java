package com.mingqi.demo;

import com.mingqi.link.MyLinkedList;
import com.mingqi.link.Node;
import org.junit.Test;

/*
  查找单向链表中的倒数第k个节点
* */
public class QuestionTwoDemo {
    //先将整个链表从头到尾遍历一次，计算出链表的长度size，得到链表的长度之后，就好办了，
    // 直接输出第 size-k 个节点就可以了（注意链表为空，k为0，k大于链表中节点个数的情况）。因为需要遍历两次链表，所以时间复杂度为 T(2n) = O(n)
    /**
     * 查找链表中的倒数第k个节点的方法
     *
     * @param myLinkedList 需要查找的链表作为参数传递进来
     * @param k            代表倒数第k个节点的位置
     * @return
     */
    public Node reciprocalFindNode(MyLinkedList myLinkedList, int k) throws Exception {
        int size = 0;

        // 如果头节点为null，说明链表为空
        if (myLinkedList.getHeadNode() == null) {
            throw new Exception("链表为空");
        }

        // 判断k，k不能为0
        if (k == 0) {
            throw new Exception("k不能为0");
        }

        // 第一次遍历，计算出链表的长度size
        Node tempNode = myLinkedList.getHeadNode();
        while (tempNode != null) {
            size++;
            tempNode = tempNode.getNext();
        }

        // 判断k，k不能大于链表中节点的个数
        if (k > size) {
            throw new Exception("k不能大于链表中节点的个数");
        }

        // 第二次遍历，找出倒数第k个节点
        tempNode = myLinkedList.getHeadNode();
        for (int i = 0; i < size - k; i++) {
            tempNode = tempNode.getNext();
        }
        return tempNode;
    }
    /**
     * 查找链表中的倒数第k个节点的方法2
     *
     * @param myLinkedList 需要查找的链表作为参数传递进来
     * @param k            代表倒数第k个节点的位置
     * @return
     */
    public Node reciprocalFindNode2(MyLinkedList myLinkedList, int k) throws Exception {
        // 如果头节点为null，说明链表为空
        if (myLinkedList.getHeadNode() == null) {
            throw new Exception("链表为空");
        }

        Node first = myLinkedList.getHeadNode();
        Node second = myLinkedList.getHeadNode();

        // 让second节点往后挪 k-1 个位置
        for (int i = 0; i < k - 1; i++) {
            second = second.getNext();
        }

        // 让first节点和second节点整体向后移动，直到second节点走到最后一个节点
        while (second.getNext() != null) {
            first = first.getNext();
            second = second.getNext();
        }
        // 当second节点走到最后一个节点时，first节点就是我们要找的节点
        return first;
    }
    /**
     * 用来测试的方法
     */
    @Test
    public void test() throws Exception {
        MyLinkedList myLinkedList = new MyLinkedList();

        Node newNode1 = new Node("欧阳锋", null);
        Node newNode2 = new Node("黄药师", null);
        Node newNode3 = new Node("洪七公", null);

        myLinkedList.insert(newNode1, 1);
        myLinkedList.insert(newNode2, 2);
        myLinkedList.insert(newNode3, 3);

        System.out.println("-----完整的链表start-----");
        myLinkedList.showAll();
        System.out.println("-----完整的链表end-------");
        System.out.println("");

        Node node = reciprocalFindNode(myLinkedList, 1);
        System.out.println("链表的倒数第1个节点是：" + node.getElement());

        node = reciprocalFindNode(myLinkedList, 2);
        System.out.println("链表的倒数第2个节点是：" + node.getElement());

        node = reciprocalFindNode(myLinkedList, 3);
        System.out.println("链表的倒数第3个节点是：" + node.getElement());

        node = reciprocalFindNode(myLinkedList, 4);
        System.out.println("链表的倒数第4个节点是：" + node.getElement());
    }
}
