package com.amadeus.main;

import com.amadeus.binarytree.BinarySearchTree;
import com.amadeus.binarytree.BinaryTree;
import com.amadeus.printer.BinaryTrees;



public class tests {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.add(1);
        bst.add(-1);
        bst.add(3);
        bst.add(4);
        bst.add(-1);
        bst.add(7);
        bst.add(-6);
        bst.add(3);
        bst.add(2);
        bst.add(6);
        BinaryTrees.println(bst);
        bst.inorder(new BinaryTree.Visitor() {
            @Override
            public boolean visit(Object element) {
                System.out.print(element + " ");
                return false;
            }
        });

    }
}
