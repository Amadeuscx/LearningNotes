package com.amadeus.binarytree;

import java.util.Comparator;

public class BinarySearchTree<E> extends BinaryTree<E> {
    private Comparator<E> comparator;

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }
    public BinarySearchTree() {
        this(null);
    }

    public void add(E element) {

    }

    private int compater(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }else {
            return ((Comparable)e1).compareTo(e2);
        }
    }
}
