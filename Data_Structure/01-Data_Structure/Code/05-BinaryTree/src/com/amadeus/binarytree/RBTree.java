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

        // 如果父节点是黑色，直接返回
        if (isBlack(parent)) return;

        // 叔父节点。
        Node<E> uncle = parent.sibling();
        // 祖父节点
        Node<E> grand = red(parent.parent);
        if (isRed(uncle)) { // 叔父节点是红色【B树节点上溢】
            black(parent);
            black(uncle);
            // 把祖父节点当做是新添加的节点
            afterAdd(grand);
            return;
        }

        // 叔父节点不是红色
        if (parent.isLeftChild()) { // L
            if (node.isLeftChild()) { // LL
                black(parent);
            } else { // LR
                black(node);
                rotateLeft(parent);
            }
            rotateRight(grand);
        } else { // R
            if (node.isLeftChild()) { // RL
                black(node);
                rotateRight(parent);
            } else { // RR
                black(parent);
            }
            rotateLeft(grand);
        }



    }




    //

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
