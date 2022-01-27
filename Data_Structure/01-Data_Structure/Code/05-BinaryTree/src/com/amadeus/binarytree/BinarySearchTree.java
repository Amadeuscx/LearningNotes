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

    private Node<E> getNode(E element) {
        if (root == null) {return null;}
        Node<E> cur = root;
        while (cur != null) {
            if (compater(cur.element, element) < 0) {
                cur = cur.right;
            } else if (compater(cur.element, element) > 0) {
                cur = cur.left;
            } else {
                return cur;
            }
        }
        return null;
    }

    public void add(E element) {
        elementNotNullCheck(element);
        if (root == null) {
            root = createNode(element);
            size++;
            return;
        }
        Node<E> cur = root;
        Node<E> parent = cur;
        while (cur != null) {
            parent = cur;
            if (compater(cur.element, element) < 0) {
                cur = cur.right;
            } else if (compater(cur.element, element) > 0) {
                cur = cur.left;
            } else {
                cur.element = element;
                return;
            }
        }
        if (compater(parent.element, element) < 0) {
            parent.right = createNode(element, parent);
        } else {
            parent.left = createNode(element, parent);
        }
        size++;
    }

    private void remove(Node<E> node) {
        if (node == null) {return;}

        size--;
        if (node.hasTwoChilden()) {
            Node<E> s = predecessor(node);
            node.element = s.element;
            node = s;
        }



    }

    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new NullPointerException("element值不能为空");
        }
    }

    private Node<E> createNode(E element, Node<E> parent) {
        return new Node<E>(element, parent);
    }

    private Node<E> createNode(E element) {
        return createNode(element, null);
    }

    private int compater(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        } else {
            return ((Comparable) e1).compareTo(e2);
        }
    }


}