package com.mingqi.demo;

import com.mingqi.link.MyLinkedList;
import com.mingqi.link.Node;
import org.junit.Test;

/**
 * 反转一个单向链表
 *
 */
public class QuestionThreeDemo {
    /**
     * 反转一个单向链表的方法
     *
     * @param myLinkedList
     * @throws Exception
     */
    public void reverseList(MyLinkedList myLinkedList) throws Exception {
        // 判断链表是否为null
        if (myLinkedList == null || myLinkedList.getHeadNode() == null || myLinkedList.getHeadNode().getNext() == null) {
            throw new Exception("链表为空");
        }

        // 判断链表里是否只有一个节点
        if (myLinkedList.getHeadNode().getNext().getNext() == null) {
            // 链表里只有一个节点，不用反转
            return;
        }

        // tempNode 从头节点后面的第一个节点开始往后移动
        Node tempNode = myLinkedList.getHeadNode().getNext();
        // 当前节点的下一个节点
        Node nextNode = null;
        // 反转后新链表的头节点
        Node newHeadNode = null;

        // 遍历链表，每遍历到一个节点都把它放到链表的头节点位置
        while (tempNode.getNext() != null) {
            // 把tempNode在旧链表中的下一个节点暂存起来
            nextNode = tempNode.getNext();
            // 设置tempNode在新链表中作为头节点的next值
            tempNode.setNext(newHeadNode);
            // 更新newHeadNode的值，下一次循环需要用
            newHeadNode = tempNode;
            // 更新头节点
            myLinkedList.setHeadNode(newHeadNode);
            // tempNode往后移动一个位置
            tempNode = nextNode;
        }
        // 旧链表的最后一个节点的next为null，要把该节点的next设置为新链表的第二个节点
        tempNode.setNext(newHeadNode);
        // 然后把它放到新链表的第一个节点的位置
        myLinkedList.setHeadNode(tempNode);
        // 新建一个新链表的头节点
        newHeadNode = new Node(null, tempNode);
        myLinkedList.setHeadNode(newHeadNode);
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

        System.out.println("-----反转之后的链表start-----");
        reverseList(myLinkedList);
        myLinkedList.showAll();
        System.out.println("-----反转之后的链表end-------");
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
}
