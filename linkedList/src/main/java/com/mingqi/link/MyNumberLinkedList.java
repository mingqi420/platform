package com.mingqi.link;

public class MyNumberLinkedList {
    // 头节点
    private NumberNode headNode;
    // 用来遍历链表的临时节点
    private NumberNode tempNode;

    // Getter
    public NumberNode getHeadNode() {
        return headNode;
    }
    // Setter
    public void setHeadNode(NumberNode headNode) {
        this.headNode = headNode;
    }

    // 链表的初始化方法
    public MyNumberLinkedList() {
        // 初始化时，链表里面只有1个节点，所以这个节点的地址域为null
        NumberNode node = new NumberNode(0, null);
        // 头节点不存储数据，它的数据域为null，它的地址域存储了第1个节点的地址
        headNode = new NumberNode(null, node);
    }

    /**
     * 1、插入节点：时间复杂度为O(n)
     * @param newNode 需要插入的新节点
     * @param position 这个变量的范围可以从0到链表的长度，注意不要越界。
     *                 头节点不算进链表的长度，
     *                 所以从头节点后面的节点开始算起，position为0
     */
    public void insert(NumberNode newNode, int position) {
        // 通过position变量，让tempNode节点从头节点开始遍历，移动到要插入位置的前一个位置
        tempNode = headNode;
        int i = 0;
        while (i < position) {
            tempNode = tempNode.next;
            i++;
        }
        newNode.next = tempNode.next;
        tempNode.next = newNode;
    }

    /**
     * 2、删除节点：时间复杂度为O(n)
     * @param position
     */
    public void delete(int position) {
        // 这里同样需要用tempNode从头开始向后查找position
        tempNode = headNode;
        int i = 0;
        while (i < position) {
            tempNode = tempNode.next;
            i++;
        }
        tempNode.next = tempNode.next.next;
    }

    /**
     * 3、查找节点：时间复杂度为O(n)
     * @param position
     * @return 返回查找的节点
     */
    public NumberNode find(int position) {
        // 这里同样需要用tempNode从头开始向后查找position
        tempNode = headNode;
        int i = 0;
        while (i < position) {
            tempNode = tempNode.next;
            i++;
        }
        return tempNode.next;
    }

    /**
     * 4、获取链表的长度：时间复杂度为O(n)
     * @return
     */
    public int size() {
        tempNode = headNode.next;
        int size = 0;
        while (tempNode.next != null) {
            size = size + 1;
            tempNode = tempNode.next;
        }
        size = size + 1; // tempNode的地址域为null时，size记得加上最后一个节点
        return size;
    }

    // 遍历链表，打印出所有节点的方法
    public void showAll() {
        tempNode = headNode.next;
        while (tempNode.next != null) {
            System.out.println(tempNode.element);
            tempNode = tempNode.next;
        }
        System.out.println(tempNode.element);
    }
}
