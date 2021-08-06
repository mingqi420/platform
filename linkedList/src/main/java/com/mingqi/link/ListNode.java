package com.mingqi.link;

public class ListNode {
    int val;
    ListNode next = null;

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }

    ListNode(int val) {
        this.val = val;
    }
}