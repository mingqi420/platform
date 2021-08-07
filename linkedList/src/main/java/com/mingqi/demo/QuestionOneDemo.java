package com.mingqi.demo;



import com.mingqi.link.ListNode;
import com.mingqi.link.MyLinkedList;
import com.mingqi.link.Node;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 从尾到头打印单向链表
 * 对于这种颠倒顺序打印的问题，我们应该就会想到栈，后进先出。因此这一题要么自己新建一个栈，
 * 要么使用系统的栈（系统递归调用方法时的栈）。需要把链表遍历完一次，所以它的时间复杂度为 O(n)
 *
 * 注意：不能先把链表反转，再遍历输出，因为这样做会破坏链表节点原来的顺序。
 *
 */
public class QuestionOneDemo {
    /**
     * 从尾到头打印单向链表
     * 方法1：自己新建一个栈
     *
     * @param head 参数为链表的头节点
     */
    public void reversePrint(Node head) {

        // 判断链表是否为空
        if (head == null) {
            return;
        }

        // 新建一个栈
        Stack<Node> stack = new Stack<Node>();

        // 用来遍历的临时节点，从头节点开始
        Node tempNode = head;

        // 从头节点开始遍历链表，将除了头节点之外的所有节点压栈
        while (tempNode.getNext() != null) {
            tempNode = tempNode.getNext();
            stack.push(tempNode);
        }
        // 将栈中的节点打印输出即可
        while (stack.size() > 0) {
            // 出栈操作
            Node node = stack.pop();
            System.out.println(node.getElement());
        }
    }
    /**
     * 从尾到头打印单向链表
     * 方法2：自己新建一个栈
     *方法2是基于递归实现的，代码看起来更简洁，
     * 但有一个问题：当链表很长的时候，就会导致方法调用的层级很深，
     * 有可能造成栈溢出。而方法1是自己新建一个栈，使用循环压栈和循环出栈，
     * 代码的稳健性要更好一些。
     * @param head 参数为链表的头节点
     */
    public void reversePrint2(Node head) {
        // 判断传进来的参数节点是否为空
        if (head == null) {
            return;
        }
        // 递归
        reversePrint2(head.getNext());
        System.out.println(head.getElement());
    }
   //栈
    public ArrayList<Integer> printListFromTailToHeadStack(ListNode listNode) {
     /*   ArrayList<Integer> res = new ArrayList<Integer>();
        Stack<Integer> stack = new Stack<Integer>();    //posh + pop 搞定
        while(listNode != null){
            stack.push(listNode.val);
            listNode = listNode.next;
        }
        while(!stack.isEmpty()){
            res.add(stack.pop());
        }
        return res;*/
     ArrayList<Integer> res=new ArrayList<>();
     Stack<Integer> stack=new Stack<>();
     while(listNode!=null){
         stack.push(listNode.val);
         listNode=listNode.next;
     }
     while (!stack.isEmpty()){
         res.add(stack.pop());
     }
     return res;
    }
    //递归
    ArrayList<Integer> res = new ArrayList<Integer>();//一定要在函数之前定义
    public ArrayList<Integer> printListFromTailToHeadDG(ListNode listNode) {
        if(listNode!=null){
            printListFromTailToHead(listNode.next);    //没有用到printListFromTailToHead的返回值
            res.add(listNode.val);    //这个在递归后面，则可以做到倒序；如果在递归前就是正序
        }
        return res;
    }
    // 头茬发
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer>mylist=new ArrayList<>();//含有<>和（）；别忘了new
        while(listNode!=null){//直接用null对应listNode就行
            mylist.add(0,listNode.val);//list.add(0,value)在list的头部插入值
            listNode=listNode.next;//Java这样就不用到->指针了，只会用到STL里面定义过的操作
        }
        return mylist;
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

        System.out.println("----原链表 start----");
        myLinkedList.showAll();
        System.out.println("----原链表 end----");
        System.out.println("");

        System.out.println("====从尾到头打印链表 start====");
        reversePrint(myLinkedList.getHeadNode());
        System.out.println("====从尾到头打印链表 end====");
        System.out.println("");

        System.out.println("----原链表(依然保留了原来的顺序) start----");
        myLinkedList.showAll();
        System.out.println("----原链表(依然保留了原来的顺序) end----");
    }
}
