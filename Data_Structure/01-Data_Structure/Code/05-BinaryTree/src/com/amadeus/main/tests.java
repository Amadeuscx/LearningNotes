package com.amadeus.main;

import com.amadeus.binarytree.BinarySearchTree;
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

    }
}
