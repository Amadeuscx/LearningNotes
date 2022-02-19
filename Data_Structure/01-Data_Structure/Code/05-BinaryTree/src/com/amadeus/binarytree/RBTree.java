package com.amadeus.binarytree;

import java.util.Comparator;

public class RBTree<E> extends BBST<E>{

    protected static final boolean RED = false;
    protected static final boolean BLACK = true;


    public RBTree(Comparator<E> comparator) {
        super(comparator);
    }
    public RBTree() {
        super();
    }



    protected void afterAdd(Node<E> node) {
        Node<E> parent = node.parent;

        if (parent == null) {
            black(node);
            return;
        }

        if (isBlack(parent)) return;



    }




    ////

    public static class RBNode<E> extends Node<E> {

        boolean color = RED;

        public RBNode(E element, Node<E> parent) {
            super(element, parent);
        }
        public RBNode(E element) {
            super(element);
        }
    }


    protected Node<E> createNode(E element) {
        return new RBNode<E>(element);
    }
    protected Node<E> createNode(E element, Node<E> parent) {
        return new RBNode<E>(element, parent);
    }

    private Node<E> color(Node<E> node, boolean color) {
        if (node == null) return node;
        ((RBNode<E>)node).color = color;
        return node;
    }

    private Node<E> red(Node<E> noed) {
        return color(noed, RED);
    }

    private Node<E> black(Node<E> node) {
        return color(node, BLACK);
    }

    private boolean isRed(Node<E> node) {
        return ((RBNode<E>)node).color == RED;
    }

    private boolean isBlack(Node<E> node) {
        return ((RBNode<E>)node).color == BLACK;
    }








}
