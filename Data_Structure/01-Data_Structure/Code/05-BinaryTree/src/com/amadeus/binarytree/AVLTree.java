package com.amadeus.binarytree;

import java.util.Comparator;

public class AVLTree<E> extends BinarySearchTree<E> {
    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }

    public AVLTree() {
        this(null);
    }






    private static class AVLNode<E> extends Node<E> {
    int height = 1;

        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }
        public AVLNode(E element) {
            super(element);
        }



    }
}
