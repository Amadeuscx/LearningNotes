package com.amadeus.tools;

public class MyArrayList<E> implements MyList<E> {
    private int size;
    private E[] elements;


    private static final int DEFAULT_CAPACITY = 10;
    private static final int ELEMENT_NOT_FOUND = -1;


    //构造器
    public MyArrayList(int capacity){
        capacity = (capacity > DEFAULT_CAPACITY) ? capacity : DEFAULT_CAPACITY;
        this.elements = (E[]) new Object[capacity];
    }
    public MyArrayList(){
        this(DEFAULT_CAPACITY);
    }


    //检查容量，如果不足则扩容
    private void ensureCapacity() {
        if (elements.length > size) return;
        int NewCapacity = elements.length + (elements.length >> 1);
        E[] NewElements = (E[]) new Object[NewCapacity];
        for (int i = 0; i < size; i++) {
            NewElements[i] = elements[i];
        }
        elements = NewElements;
    }

    //检查索引合法性，若不合法则抛出异常

    private void RangeCheck (int index) {
        if (index < 0 || index >size){
            throw new ArrayIndexOutOfBoundsException("YourIndex:" + index + "ButSize=" + size);
        }
    }



    public void add(E element) {
        add(size, element);
    }

    public void add(int index, E element) {
        RangeCheck(index);
        ensureCapacity();
        for (int i = 0; i < size - index; i++) {
            elements[size - i] = elements[size - 1 - i];
        }
        elements[index] = element;
        size++;
    }



    public E remove (int index) {
        RangeCheck(index);
        E old = elements[index];
        for (int i = index+1; i < size; i++) {
            elements[i - 1] = elements[i];
        }
        elements[--size] = null;
        return old;
    }
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }



    public int indexOf(E element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {return i;}
            }
        }else {
            for (int i = 0; i < size; i++) {
                if (element.equals(elements[i])) {return i;}
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    public boolean contains(E element) {
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }

    public E get(int index) {
        RangeCheck(index);
        return elements[index];
    }


    public E set(int index, E element) {
        RangeCheck(index);
        E tmp = elements[index];
        elements[index] = element;
        return tmp;
    }


    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("size=").append(size).append(", [");
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                str.append(", ");
            }
            str.append(elements[i]);
        }
        str.append("]");
        return str.toString();
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

}
