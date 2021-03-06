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
            afterAdd(root);
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
        Node<E> newNode = createNode(element, parent);
        if (compater(parent.element, element) < 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;

        afterAdd(newNode);
    }

    protected void afterAdd(Node<E> node) {}

    private void remove(Node<E> node) {
        if (node == null) return;

        size--;
        if (node.hasTwoChilden()) {
            Node<E> s = predecessor(node);
            node.element = s.element;
            node = s;
        }

        Node<E> replacement = node.left == null ? node.right : node.left;
        if (replacement != null) {

            replacement.parent = node.parent;

            if (node.parent == null) {
                root = replacement;
            }else if (node.isLeftChild()) {
                node.parent.left = replacement;
            }else {
                node.parent.right = replacement;
            }
            afterRemove(node);
        }else if (node.parent == null) {
            root = null;
            //???????????????????????????????????????
        }else {//node???????????????????????????root
            if (node.isLeftChild()) {
                node.parent.left =null;
            }else {
                node.parent.right = null;
            }
            afterRemove(node);
        }
    }

    protected void afterRemove(Node<E> node) {}

    public void remove(E element) {
        remove(getNode(element));
    }

    public boolean contains(E element) {
        return getNode(element) != null;
    }

    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new NullPointerException("element???????????????");
        }
    }

    protected Node<E> createNode(E element, Node<E> parent) {
        return new Node<E>(element, parent);
    }

    protected Node<E> createNode(E element) {
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