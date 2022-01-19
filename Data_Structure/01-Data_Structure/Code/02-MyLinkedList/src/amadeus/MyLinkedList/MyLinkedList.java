package amadeus.MyLinkedList;



public class MyLinkedList<E> implements MyList<E> {

    private int size;
    private Node<E> first;



    @Override
    public void add(int index, E element) {
        RangeCheck(index);
        if (index == 0){
            first = new Node<E>(element);
            first.next = null;
        }else {
            Node NewNode = new Node<E>(element);
            Node tmp = GetNode(index);
            GetNode(index - 1).next = NewNode;
            NewNode.next = tmp;
        }
        size++;
    }
    public void add(E element) {
        add(size, element);
    }




    public E remove(int index) {
        RangeCheck(index);
        E old;
        if (index == 0){
            old = first.elcment;
            first = GetNode(index).next;
            return old;
        }else {
            old = GetNode(index).elcment;
            GetNode(index - 1).next = GetNode(index).next;
            return old;
        }
    }


    public void clear() {
        size = 0;
        first = null;
    }


    public int indexOf(Object element) {
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


    public boolean contains(Object element) {
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }

    public E get(int index) {
        return GetNode(index).elcment;
    }

    public E set(int index, E element) {
        return GetNode(index).elcment = element;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty(){
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





    private class Node <E> {
        E elcment;
        Node<E> next;

        public Node(E elcment) {
            this.elcment = elcment;
            next = null;
        }
    }


    //检查索引合法性，若不合法则抛出异常
    private void RangeCheck(int index) {
        if (index < 0 || index >size){
            throw new IndexOutOfBoundsException("YourIndex:" + index + "ButSize=" + size);
        }
    }


    //node索引，返回index的node
    private Node<E> GetNode(int index) {
        RangeCheck(index);
        Node tmp = first;
        for (int i = 0; i < index; i++) {
            tmp = tmp.next;
        }
        return tmp;
    }






}
