package com.mingqi.link;

public class NumberNode {
    Integer element; // 数据域
    NumberNode next; // 地址域

    // 节点的构造方法
    public NumberNode(Integer element, NumberNode next) {
        this.element = element;
        this.next = next;
    }

    // Gettet and Setter
    public Integer getElement() {
        return element;
    }

    public void setElement(Integer element) {
        this.element = element;
    }

    public NumberNode getNext() {
        return next;
    }

    public void setNext(NumberNode next) {
        this.next = next;
    }
}
