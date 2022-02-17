package com.amadeus.binarytree;

import java.util.Comparator;

public abstract class BBST<E> extends BinarySearchTree<E>{

    public BBST(Comparator<E> comparator) {
        super(comparator);
    }

    public BBST() {
        super();
    }

    protected void rotateLeft(Node<E> grand) {
        Node<E> parent = grand.right;
        Node<E> child = parent.left;

        grand.right = child;
        parent.left = grand;
        afterRotate(grand, parent, child);
    }


    protected void rotateRight(Node<E> grand) {
        Node<E> parent = grand.left;
        Node<E> child = parent.right;

        grand.left = child;
        parent.right = grand;
        afterRotate(grand, parent, child);
    }

    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        parent.parent = grand.parent;

        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        }else if (grand.isRightChild()) {
            grand.parent.right = parent;
        }else {
            root = parent;
        }

        if (child != null) {
            child.parent = grand;
        }

        grand.parent = parent;


    }
}
