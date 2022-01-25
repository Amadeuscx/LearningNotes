package com.amadeus.tools;

public class DoublyLinkedList<E> implements MyList<E> {
    private int size;
    private Node<E> first;
    private Node<E> last;

    public class Node<E> {
        E element;
        Node<E> next;
        Node<E> prev;

        public Node(E element){
            this.element = element;
        }
        public Node(Node<E> prev, E element, Node<E> next) {
            this.prev = prev;
            this.element = element;
            this.next = next;
        }
    }


    private void RangeCheck(int index) {
        if (index < 0 || index >size) {
            throw new IndexOutOfBoundsException("YourIndex:" + index + "ButSize=" + size);
        }
    }
    private Node<E> GetNode(int index) {
        RangeCheck(index);
        Node<E> tmp;
        if (index < (size >> 1)) {
            tmp = first;
            for (int i = 0; i < index; i++) {
                tmp = tmp.next;
            }
        }else {
            tmp = last;
            for (int i = size - 1; i > index; i--) {
                tmp = tmp.prev;
            }
        }
        return tmp;
    }
    @Override
    public void add(E element) {
        add(size, element);
    }

    @Override
//    public void add(int index, E element) {
//        RangeCheck(index);
//        if (index == 0){
//            first = new Node<E>(element);
//            last = first;
//        }else if (index == size){
//            Node<E> tmp = last;
//            last = new Node<>(element);
//            last.prev = tmp;
//            last.next = null;
//            tmp.next = last;
//        }else {
//            Node NewNode = new Node<E>(element);
//            Node tmp = GetNode(index);
//            GetNode(index - 1).next = NewNode;
//            NewNode.prev = GetNode(index - 1);
//            NewNode.next = tmp;
//        }
//        size++;
//    }


    public void add(int index, E element) {
        RangeCheck(index);

        // size == 0
        // index == 0
        if (index == size) { // 往最后面添加元素
            Node<E> oldLast = last;
            last = new Node<>(oldLast, element, null);
            if (oldLast == null) { // 这是链表添加的第一个元素
                first = last;
            } else {
                oldLast.next = last;
            }
        } else {
            Node<E> next = GetNode(index);
            Node<E> prev = next.prev;
            Node<E> node = new Node<>(prev, element, next);
            next.prev = node;

            if (prev == null) { // index == 0
                first = node;
            } else {
                prev.next = node;
            }
        }

        size++;
    }

    @Override
    public E remove(int index) {
        RangeCheck(index);

        Node<E> old = GetNode(index);
        RangeCheck(index);

        if (old.prev == null) {
            first = old.next;
        }else {
            old.prev.next = old.next;
        }
        if (old.next == null) {
            last = old.prev;
        }else {
            old.next.prev = old.prev;
        }
        size--;
        return old.element;
    }

    @Override
    public void clear() {
        size = 0;
        first = null;
        last = null;
    }

    @Override
    public int indexOf(E element) {
        if (element == null){
            for (int i = 0; i < size; i++) {
                if (get(i) == null) { return i; }
            }
        }else {
            for (int i = 0; i < size; i++) {
                if (element.equals(get(i)) ) { return i; }
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }

    @Override
    public E get(int index) {
        return GetNode(index).element;
    }

    @Override
    public E set(int index, E element) {
        RangeCheck(index);
        E old = GetNode(index).element;
        GetNode(index).element = element;
        return old;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("size=").append(size).append(", [");
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                str.append(", ");
            }
            str.append(get(i));
        }
        str.append("]");
        return str.toString();
    }


}
