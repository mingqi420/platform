package com.mingqi.demo;

import com.mingqi.link.ListNode;
import com.mingqi.link.RandomListNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

/**

 */
public class Solution {
    /**
     * * 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
     * * 输入：
     * * {1,3,5},{2,4,6}
     * * 返回值：
     * * {1,2,3,4,5,6}
     * 如果list1小于list2，则list1作为新序列的开头，后面应该接的部分等同于list1.next和list2的重新排序。反之同理。
     *
     * @param list1
     * @param list2
     * @return
     */
    public ListNode Merge(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        if (list1.val < list2.val) {
            list1.next = Merge(list1.next, list2);
            return list1;
        } else {
            list2.next = Merge(list2.next, list1);
            return list2;
        }
    }

    /**
     * 输入一个链表，输出一个链表，该输出链表包含原链表中从倒数第k个结点至尾节点的全部节点。
     * 如果该链表长度小于k，请返回一个长度为 0 的链表。
     * 输入：
     * {1,2,3,4,5},1
     * 返回值：
     * {5}
     *
     * @param pHead
     * @param k
     * @return
     */
    public ListNode FindKthToTail(ListNode pHead, int k) {
        //1、先定义两个指针，fast、slow指向链表的头节点
        ListNode fast = pHead;
        ListNode slow = pHead;
        //2、fast指针先走k步，走的时候注意判断长度是否大于k，如果链表长度也等于k的话，fast走k步正好会走到空，如果在第k部之前链表就指向空的话说明链表长度小于k，直接返回nul
        for (int i = 0; i < k; i++) {
            if (fast == null) {
                return null;
            }
            fast = fast.next;
        }
        // fast和slow 一块走，当fast指针指向null的时候，slow指针指向的就是倒数第k个元素。
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;

    }

    /**
     * 输入一个链表，反转链表后，输出新链表的表头。
     * 示例1
     * 输入：
     * {1,2,3}
     * 返回值：
     * {3,2,1}
     *
     * @param head
     * @return 把链表节点一个个入栈，当全部入栈完之后再一个个出栈，出栈的时候在把出栈的结点串成一个新的链表
     */
    public ListNode ReverseList(ListNode head) {
        Stack<ListNode> stack = new Stack<ListNode>();
        //把链表节点全部摘掉放到栈中
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        if (stack.isEmpty()) {
            return null;
        }
        //栈顶元素取出
        ListNode node = stack.pop();
        ListNode dummy = node;
        //栈中的节点全部出栈，然后依次连城一个新的链表
        while (!stack.isEmpty()) {
            ListNode tempNode = stack.pop();
            node.next = tempNode;
            node = node.next;
        }
        //最后一个结点就是反转前的头结点，一定要让他的next
        //等于空，否则会构成环
        node.next = null;
        return dummy;
    }

    /**
     * 复杂链表的复制
     * 输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针random指向一个随机节点），
     * 请对此链表进行深拷贝，并返回拷贝后的头结点。（注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）。
     *
     * @param phead
     * @return
     */
    public RandomListNode Clone(RandomListNode phead) {
        if (phead == null) {
            return phead;
        }
        // p1指向旧链表的指针，将新旧节点对应
        RandomListNode p1 = phead;
        //p2 指向新链表的指针
        RandomListNode p2 = phead;
        HashMap<RandomListNode, RandomListNode> map = new HashMap<>();
        while (p1 != null) {
            //存放的是p1节点和一个新的节点，这个新的节点值和p1的值相等
            //通俗来看，这一步是将节点复制，但是没有串起来（复制好了每一个颗糖葫芦，但是没串到竹签上）
            RandomListNode clone = new RandomListNode(p1.label);
            map.put(p1, clone);
            p1 = p1.next;
        }
        while (p2 != null) {
            //p2在原来链表上移动
            //map.get(p2)通过键值对，拿到了新链表在p2位置的节点应该是哪一个
            //map.get(p2).next= 表示这个新链表的p2位置节点的下一个节点应该指向？
            //map.get(p2.next) 表示拿到原链表p2位置节点指向的下一个节点
            //这个赋值通俗来就是，根据原来的糖葫芦顺序，把复制的每一颗的糖葫芦依葫芦画瓢的串起来
            if (p2.next != null) {
                map.get(p2).next = map.get(p2.next);
            } else {
                map.get(p2).next = null;
            }
            //这里就是进行random指针的复制，这里的random和next的复制顺序可以交换，p2=p2.next必须在最后
            map.get(p2).random = map.get(p2.random);
            p2 = p2.next;
        }
        return map.get(phead);
    }

    /**
     * 输入两个无环的单链表，找出它们的第一个公共结点。（注意因为传入数据是链表，所以错误测试数据的提示是用其他方式显示的，保证传入数据是正确的）
     * 先把第一个链表的节点全部存放到集合set中，然后遍历第二个链表的每一个节点，判断在集合set中是否存在，
     * 如果存在就直接返回这个存在的结点。如果遍历完了，在集合set中还没找到，说明他们没有相交，直接返回null即可
     *
     * @param pHead1
     * @param pHead2
     * @return
     */
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null) {
            return null;
        }
        HashSet<ListNode> set = new HashSet<>();
        HashSet<ListNode> set1 = new HashSet<>();
        while (pHead1 != null && !set.contains(pHead1)) {
            set.add(pHead1);
            pHead1 = pHead1.next;
        }
        while (pHead2 != null && !set1.contains(pHead2)) {
            set1.add(pHead2);
            if (set.contains(pHead2)) {
                return pHead2;
            }
            pHead2 = pHead2.next;
        }
        return null;
    }

    /**
     * 给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，返回null。
     *
     * @param pHead 输入分为2段，第一段是入环前的链表部分，第二段是链表环的部分，后台将这2个会组装成一个有环或者无环单链表
     * @return 返回链表的环的入口结点即可。而我们后台程序会打印这个节点
     * 输入：
     * {1,2},{3,4,5}
     * 返回值：
     * 3
     * 说明：
     * 返回环形链表入口节点，我们后台会打印该环形链表入口节点，即3
     * 示例2
     * 输入：
     * {1},{}
     * 返回值：
     * "null"
     * 说明：
     * 没有环，返回null，后台打印"null"
     * 示例3
     * 输入：
     * {},{2}
     *
     * 返回值：
     * 2
     * 说明：
     * 只有环形链表节点2，返回节点2，后台打印2
     */
    public ListNode EntryNodeOfLoop(ListNode pHead){
        if (pHead == null){
            return null;
        }
        /**
         * 每遍历一个结点就把该结点的下一指针指向标记结点（我自己新建了一个结点，
         * 因为是比较地址值不是val所以建立的结点一定是独一无二的）。
         * 当遍历到某个结点的下一指针为标志结点说明，这个结点遍历过一次，
         * 为环入口结点。如果先遍历到null结点表示没有环。
         */
        // 新建标志结点
        ListNode sign=new ListNode(1);
        ListNode cur = pHead;
        ListNode pro = null;
        // 循环遍历结点
        while (cur.next != sign && cur.next != null){
            pro = cur;
            cur = cur.next;
            pro.next = sign;
        }
        // 判断是有环还是无环
        if (cur.next == null){
            return null;
        }
        if (cur.next == sign){
            return cur;
        }
        return null;
    }
    public ListNode deleteDuplication(ListNode pHead){
        if(pHead == null || pHead.next == null)
            return pHead;
        ListNode newNode = new ListNode(0);  //新建结点，防止头节点被删除
        newNode.next = pHead;
        ListNode tmp = pHead;
        ListNode pre = newNode;  //保存前一个结点
        while(tmp != null && tmp.next != null){
            if(tmp.val == tmp.next.val){
                int val = tmp.val;
                //将跟tmp结点值相等的结点过滤掉
                while(tmp != null && tmp.val == val){
                    tmp = tmp.next;
                }
                //上一个非重复的结点指向下一个非重复的结点
                pre.next = tmp;
            }else{
                pre = tmp;
                tmp = tmp.next;
            }
        }
        return newNode.next;
    }
}

