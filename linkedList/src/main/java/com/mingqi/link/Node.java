package com.mingqi.link;
/**
 * 节点类
 */
public class Node {
    Object element; // 数据域
    Node next; // 地址域

    // 节点的构造方法
    public Node(Object element, Node next) {
        this.element = element;
        this.next = next;
    }

    // Gettet and Setter
    public Node getNext() {
        return this.next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Object getElement() {
        return this.element;
    }

    public void setElement(Object element) {
        this.element = element;
    }
}
