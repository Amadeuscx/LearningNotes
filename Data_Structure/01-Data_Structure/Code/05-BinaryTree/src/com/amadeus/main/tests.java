package com.amadeus.main;

import com.amadeus.binarytree.AVLTree;
import com.amadeus.binarytree.BinarySearchTree;
import com.amadeus.binarytree.BinaryTree;
import com.amadeus.printer.BinaryTrees;



public class tests {
    public static void main(String[] args) {
        BinarySearchTree avlt = new AVLTree();
        avlt.add(1);
        avlt.add(-1);
        avlt.add(3);
        avlt.add(4);
        avlt.add(-1);
        avlt.add(7);
        avlt.add(-6);
        avlt.add(3);
        avlt.add(2);
        avlt.add(6);
        BinaryTrees.println(avlt);
        avlt.postorder(new BinaryTree.Visitor() {
            @Override
            public boolean visit(Object element) {
                System.out.print(element + " ");
                return false;
            }
        });

    }
}
