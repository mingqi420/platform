package com.mingqi.demo;

import com.mingqi.link.MyNumberLinkedList;
import com.mingqi.link.NumberNode;
import org.junit.Test;

public class QuestionFiveDemo {
    /**
     * 方法：求两个链表相交的第一个公共节点
     *
     * @param head1 链表1头节点后面的第一个节点
     * @param head2 链表2头节点后面的第一个节点
     * @return 返回两个链表的第一个公共节点
     */
    public NumberNode getFirstCommonNode(NumberNode head1, NumberNode head2) {
        if (head1 == null || head2 == null) {
            return null;
        }

        int size1 = getSize(head1);
        int size2 = getSize(head2);
        // 两个链表长度的差值
        int diffSize = 0;

        NumberNode longHead;
        NumberNode shortHead;

        // 找出较长的那个链表
        if (size1 > size2) {
            longHead = head1;
            shortHead = head2;
            diffSize = size1 - size2;
        } else {
            longHead = head2;
            shortHead = head1;
            diffSize = size2 - size1;
        }

        // 把较长的那个链表的指针向后移动diffSize个位置
        for (int i = 0; i < diffSize; i++) {
            longHead = longHead.getNext();
        }

        // 两个链表的指针同时向后移动
        while (longHead != null && shortHead != null) {
            // 第一个相同的节点就是它们的公共节点
            if (longHead.getElement() == shortHead.getElement()) {
                return longHead;
            }
            longHead = longHead.getNext();
            shortHead = shortHead.getNext();
        }
        return null;
    }

    /**
     * 方法：获取链表的长度
     *
     * @param head 指头节点后面的第一个节点
     * @return 返回链表的长度
     */
    public int getSize(NumberNode head) {
        if (head == null) {
            return 0;
        }

        int size = 0;
        NumberNode temp = head;
        while (temp != null) {
            size++;
            temp = temp.getNext();
        }
        return size;
    }

    /**
     * 用来测试的方法
     */
    @Test
    public void test() throws Exception {
        MyNumberLinkedList list1 = new MyNumberLinkedList();
        MyNumberLinkedList list2 = new MyNumberLinkedList();

        // 向链表1中添加数据
        NumberNode node1_1 = new NumberNode(1, null);
        NumberNode node1_2 = new NumberNode(2, null);
        NumberNode node1_3 = new NumberNode(3, null);
        NumberNode node1_4 = new NumberNode(6, null);
        NumberNode node1_5 = new NumberNode(7, null);
        list1.insert(node1_1, 1);
        list1.insert(node1_2, 2);
        list1.insert(node1_3, 3);
        list1.insert(node1_4, 4);
        list1.insert(node1_5, 5);

        // 向链表2中添加数据
        NumberNode node2_4 = new NumberNode(4, null);
        NumberNode node2_5 = new NumberNode(5, null);
        NumberNode node2_6 = new NumberNode(6, null);
        NumberNode node2_7 = new NumberNode(7, null);
        list2.insert(node2_4, 1);
        list2.insert(node2_5, 2);
        list2.insert(node2_6, 3);
        list2.insert(node2_7, 4);

        // 分别输出链表1和链表2
        System.out.println("链表1：");
        list1.showAll();
        System.out.println("");
        System.out.println("链表2：");
        list2.showAll();
        System.out.println("");

        // 输出第一个公共节点
        NumberNode commonNode = getFirstCommonNode(node1_1, node2_4);
        System.out.println("第一个公共节点是：" + commonNode.getElement());
    }
}
