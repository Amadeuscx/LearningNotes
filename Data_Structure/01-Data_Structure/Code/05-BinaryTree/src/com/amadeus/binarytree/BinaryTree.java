package com.amadeus.binarytree;

public abstract class BinaryTree<E> {
    protected int size;
    protected Node<E> root;

    protected static class Node<E> {
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }
    }
}
