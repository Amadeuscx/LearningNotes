package com.amadeus.tools;

public interface MyList<E> {
    static final int ELEMENT_NOT_FOUND = -1;



    /**
     * 增
     */

    public void add(E element);

    public void add(int index, E element);

    /**
     *删
     */

    public E remove (int index);
    public void clear();

    /**
     *查
     */
    public int indexOf(E element);

    public boolean contains(E element);

    public E get(int index);

    /**
     * 改
     */
    public E set(int index, E element);

    /**
     * 打印
     */
    public String toString();

    public int size();

    public boolean isEmpty();
}
