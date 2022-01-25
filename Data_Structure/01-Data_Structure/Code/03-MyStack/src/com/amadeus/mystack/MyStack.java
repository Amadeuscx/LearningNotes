package com.amadeus.mystack;

import com.amadeus.tools.MyArrayList;
import com.amadeus.tools.MyList;

public class MyStack<E> {
    private MyList<E> list = new MyArrayList<>();
    public int size() {
        return list.size();
    }
    public boolean isEmpty() {
        return list.isEmpty();
    }
    //入栈
    public void push(E element) {
        list.add(element);
    }
    // 出栈
    public E pop(){
        return list.remove(list.size() - 1);
    }
    // 获取栈顶元素
    public E top() {
        return list.get(list.size() - 1);
    }
    public void clear() {
        list.clear();
    }
}
