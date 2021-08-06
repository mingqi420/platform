package com.mingqi.demo;

import com.mingqi.link.MyNumberLinkedList;
import com.mingqi.link.NumberNode;
import org.junit.Test;

public class QuestionFourDemo {
    /**
     * 合并两个有序链表，使合并之后的链表依然有序
     *
     * @param head1 有序链表1的第一个有效节点(注意与头节点的区分！)
     * @param head2 有序链表2的第一个有效节点(注意与头节点的区分！)
     * @return 返回合并好的有序链表的第一个有效节点(注意与头节点的区分！)
     * @throws Exception
     */
    public NumberNode mergeLinkedList(NumberNode head1, NumberNode head2) throws Exception {
        // 如果两个链表都为空
        if (head1 == null && head2 == null) {
            throw new Exception("两个链表都为空");
        }
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }

        // 新链表的第一个有效节点(注意与头节点的区分！)
        NumberNode newHead;
        // temp指针会在两个链表中来回选出较小的节点
        NumberNode temp;

        // 一开始，让temp指针指向head1和head2中较小的数据，得到head节点
        if (head1.getElement() < head2.getElement()) {
            newHead = head1;
            temp = head1;
            head1 = head1.getNext();
        } else {
            newHead = head2;
            temp = head2;
            head2 = head2.getNext();
        }

        while (head1 != null && head2 != null) {
            if (head1.getElement() < head2.getElement()) {
                // temp指针的下一个节点对应较小的那个数据
                temp.setNext(head1);
                // temp指针往后移
                temp = temp.getNext();
                // head1也要往后移
                head1 = head1.getNext();
            } else {
                temp.setNext(head2);
                temp = temp.getNext();
                head2 = head2.getNext();
            }
        }

        // 合并剩下的节点，剩下的节点一定是都在同一个链表中
        // 如果head1不为空，说明链表1里面还有节点，链表2已经被遍历完了
        if (head1 != null) {
            temp.setNext(head1);
        }
        if (head2 != null) {
            temp.setNext(head2);
        }
        // 返回新链表的第一个有效节点(注意与头节点的区分！)
        return newHead;
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
        NumberNode node1_4 = new NumberNode(4, null);
        list1.insert(node1_1, 1);
        list1.insert(node1_2, 2);
        list1.insert(node1_3, 3);
        list1.insert(node1_4, 4);

        // 向链表2中添加数据
        NumberNode node2_2 = new NumberNode(2, null);
        NumberNode node2_3 = new NumberNode(3, null);
        NumberNode node2_4 = new NumberNode(4, null);
        NumberNode node2_5 = new NumberNode(5, null);
        list2.insert(node2_2, 1);
        list2.insert(node2_3, 2);
        list2.insert(node2_4, 3);
        list2.insert(node2_5, 4);

        // 分别输出链表1和链表2
        System.out.println("链表1：");
        list1.showAll();
        System.out.println("");
        System.out.println("链表2：");
        list2.showAll();
        System.out.println("");

        // 合并之后输出
        System.out.println("合并之后的链表：");
        NumberNode newNode = mergeLinkedList(list1.getHeadNode().getNext(), list2.getHeadNode().getNext());
        NumberNode newHeadNode = new NumberNode(null, newNode);
        MyNumberLinkedList newList = new MyNumberLinkedList();
        newList.setHeadNode(newHeadNode);
        newList.showAll();
    }
}
