package com.demo.util.binary;

public class BinarySearchTreeTest {

    public static void main(String[] args) {
        try {
            BinarySearchTree bst = new BinarySearchTree();
            System.out.println("树是否为空:" + bst.isEmpty());
            Integer[] objectArr = new Integer[]{15, 6, 18, 3, 7, 13, 20, 2, 9, 4};
            for (Integer object : objectArr) {
                bst.insert(object);
            }
            System.out.println("树是否为空:" + bst.isEmpty());
            TreeNode<Integer> minElemNode = bst.minElemNode(bst.getRoot());
            System.out.println("最小:" + minElemNode.getObject());
            testNode(bst, minElemNode);
            TreeNode<Integer> maxKeyNode = bst.maxElemNode(bst.getRoot());
            System.out.println("最大:" + maxKeyNode.getObject());
            testNode(bst, maxKeyNode);
            System.out.println("根结点:" + bst.getRoot().getObject());
            testNode(bst, bst.getRoot());
            testTraverse(bst);
            System.out.println("查找7:" + bst.search(7));
            bst.delete(7);
            System.out.println("查找7:" + bst.search(7));
            System.out.println("查找12:" + bst.search(12));
            bst.insert(12);
            System.out.println("查找12:" + bst.search(12));
            testTraverse(bst);
            bst.insert(16);
            bst.delete(6);
            bst.delete(4);
            testTraverse(bst);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private static void testNode(BinarySearchTree bst, TreeNode<Integer> node) throws Exception {
        TreeNode<Integer> precursorNode = bst.precursor(node);
        TreeNode<Integer> successorNode = bst.successor(node);
        System.out.println("****************************** ");
        System.out.println("本结点:" + node.getObject());
        System.out.println("前趋结点:" + (precursorNode == null ? "NIL" : precursorNode.getObject()));
        System.out.println("后继结点:" + (successorNode == null ? "NIL" : successorNode.getObject()));
        System.out.println("****************************** ");
    }

    private static void testTraverse(BinarySearchTree bst) {
        System.out.println("****************************** ");
        System.out.println("二叉树遍历:" + bst);
        System.out.println("二叉查找树转换为有序列表:" + bst.toStringOfOrderList());
        System.out.println("****************************** ");
    }
}
