package com.amadeus.binarytree;

import com.amadeus.printer.BinaryTreeInfo;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public abstract class BinaryTree<E> implements BinaryTreeInfo {
    protected int size;
    protected Node<E> root;



    /**
     * 前序遍历
     */
    protected void preorder(Node<E> root, Visitor<E> visitor) {
        if (root == null || visitor == null) {return;}
        Node<E> cur = root;
        Stack<Node<E>> stack = new Stack<>();
        stack.push(cur);
        while (!stack.isEmpty()) {
            cur = stack.pop();
            if (visitor.visit(cur.element)) {return;};
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
    }
    public void preorder(Visitor<E> visitor) {
        preorder(root, visitor);
    }

    /**
     * 中序遍历
     */
    protected void inorder(Node<E> root, Visitor<E> visitor) {
        if (root == null && visitor == null) {return;}

        Node<E> cur = root;
        Stack<Node<E>> stack = new Stack<>();
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            if (!stack.isEmpty()) {
                cur = stack.pop();
                if (visitor.visit(cur.element)) {return;}
                cur = cur.right;
            }
        }
    }
    public void inorder(Visitor<E> visitor) {
        inorder(root, visitor);
    }

    /**
     * 寻找前驱节点
     */
    protected Node<E> predecessor(Node<E> node) {
        if (node == null) {return null;}
        Node<E> current = node.left;

        if (current != null) {
            while (current.right != null){
                current = current.right;
            }
            return current;
        }

        while (node.parent != null && node == node.parent.left) {
            node = node.parent;
        }
        return node.parent;
    }

    /**
     * 寻找后继节点
     */
    protected Node<E> successor(Node<E> node) {
        if (node == null) return null;
        Node<E> current = node.right;

        if (current != null) {
            while (current.left != null) {
                current = current.left;
            }
            return current;
        }

        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }
        return node.parent;
    }

    /**
     * 后序遍历
     * @param root
     * @param visitor
     */
    protected void postorder(Node<E> root, Visitor visitor) {
        if (root == null || visitor == null) return;

        Stack<Node<E>> resultStack = new Stack<>();
        Stack<Node<E>> tmpStack = new Stack<>();
        Node<E> current = root;

        while (current != null || !tmpStack.isEmpty()) {
            while (current != null) {
                resultStack.push(current);
                tmpStack.push(current);
                current = current.right;
            }

            if (!tmpStack.isEmpty()) {
                current = tmpStack.pop();
                current = current.left;
            }
        }
    }

    /**
     * 层序遍历
     * @param root 开始节点
     * @param visitor 访问器（拿到节点后干什么）
     */
    protected void levelOrder(Node<E> root, Visitor visitor) {
        if (root == null || visitor == null) return;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node<E> cur = queue.poll();
            if (visitor.visit(cur)) return;

            if (cur.left != null) {
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
            }
        }
    }






















    protected static class Node<E> {
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }
        public Node(E element) {
            this(element, null);
        }

        public boolean hasTwoChilden() {
            return (left != null && right != null);
        }

        public boolean isLeaf() {
            return (left == null && right == null);
        }

        public boolean isLeftchild() {
            return (parent != null && this == parent.left);
        }

        public boolean isRightChild() {
            return (parent != null && this == parent.right);
        }

    }



    public interface Visitor<E> {
        boolean visit(E element);
    }















    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>)node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>)node).right;
    }

    @Override
    public Object string(Object node) {
        Node<E> myNode = (Node<E>)node;
        String parentString = "null";
        if (myNode.parent != null) {
            parentString = myNode.parent.element.toString();
        }
        return myNode.element + "_p(" + parentString + ")";
    }
}
