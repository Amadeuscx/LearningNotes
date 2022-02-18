package com.amadeus.binarytree;

import java.util.Comparator;

public class AVLTree<E> extends BBST<E> {


    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }

    public AVLTree() {
        this(null);
    }


    protected void afterAdd(Node<E> node) {
        while ((node = node.parent) != null) {
            if (isBalanced(node)) {
                updateHeight(node);
            }else {
                // 恢复平衡
                rebalance(node);
                // 整棵树恢复平衡
                break;
            }
        }
    }

    protected void afterRemove(Node<E> node) {
        while ((node = node.parent) != null) {
            if (isBalanced(node)) {
                updateHeight(node);
            }else {
                rebalance(node);
            }
        }
    }

    private void rebalance(Node<E> grand) {
        Node<E> parent = ((AVLNode<E>)grand).tallerChild();
        Node<E> node = ((AVLNode<E>)parent).tallerChild();
        if (parent.isLeftChild()) { // L
            if (node.isLeftChild()) { // LL
                rotateRight(grand);
            } else { // LR
                rotateLeft(parent);
                rotateRight(grand);
            }
        } else { // R
            if (node.isLeftChild()) { // RL
                rotateRight(parent);
                rotateLeft(grand);
            } else { // RR
                rotateLeft(grand);
            }
        }
    }

    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        super.afterRotate(grand, parent, child);

        updateHeight(grand);
        updateHeight(parent);
    }

//    private void rotateLeft(Node<E> grand) {
//        Node<E> parent = grand.right;
//        Node<E> child = parent.left;
//
//        grand.right = child;
//        parent.left = grand;
//        afterRotate(grand, parent, child);
//    }
//
//
//    private void rotateRight(Node<E> grand) {
//        Node<E> parent = grand.left;
//        Node<E> child = parent.right;
//
//        grand.left = child;
//        parent.right = grand;
//        afterRotate(grand, parent, child);
//    }
//
//    private void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
//        parent.parent = grand.parent;
//
//        if (grand.isLeftChild()) {
//            grand.parent.left = parent;
//        }else if (grand.isRightChild()) {
//            grand.parent.right = parent;
//        }else {
//            root = parent;
//        }
//
//        if (child != null) {
//            child.parent = grand;
//        }
//
//        grand.parent = parent;
//
//        updateHeight(grand);
//        updateHeight(parent);
//    }










    private static class AVLNode<E> extends Node<E> {
        int height = 1;

        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }
        public AVLNode(E element) {
            super(element);
        }


        public int balanceFactor() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;
            return leftHeight - rightHeight;
        }

        public void updateHeight() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;
            height = 1 + Math.max(leftHeight, rightHeight);
        }

        public Node<E> tallerChild() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;
            if (leftHeight > rightHeight) return left;
            if (leftHeight < rightHeight) return right;
            return isLeftChild() ? left : right;
        }

        public boolean isBalanced() {
            return Math.abs(balanceFactor()) <= 1;
        }

        @Override
        public String toString() {
            String parentString = "null";
            if (parent != null) {
                parentString = parent.element.toString();
            }
            return element + "_p(" + parentString + ")_h(" + height + ")";
        }
    }





    protected Node<E> createNode(E element) {
        return new AVLNode<E>(element);
    }
    protected Node<E> createNode(E element, Node<E> parent) {
        return new AVLNode<E>(element, parent);
    }

    private void updateHeight(Node<E> node){
        ((AVLNode<E>)node).updateHeight();
    }

    private boolean isBalanced(Node<E> node) {
        return Math.abs(((AVLNode<E>)node).balanceFactor()) <= 1;
    }
}
