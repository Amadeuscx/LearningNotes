package com.amadeus.myqueue;

import com.amadeus.tools.DoublyLinkedList;
import com.amadeus.tools.MyList;

public class MyQueue<E> {
    MyList<E> list = new DoublyLinkedList<>();

    // 入队
    public void offer(E element) {
        list.add(element);
    }

    // 出队
    public E poll() {
        return list.remove(0);
    }

    // 清空
    public void clear() {
        list.clear();
    }

    // 获取队列的头元素
    public E front() {
        return list.get(0);
    }

    public int size(){
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

}
