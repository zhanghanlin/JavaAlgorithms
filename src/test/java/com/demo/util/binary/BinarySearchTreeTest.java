package com.demo.util.binary;

/**
 * 二叉查找树Test
 *
 * @author zhanghanlin6
 */
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
            System.out.println("****************************** ");
            sortPrint(bst);
            TreeNode<Integer> searchNode = bst.search(7);
            System.out.println("查询节点7:" + searchNode);
            TreeNode<Integer> precursorNode = bst.precursor(searchNode);
            TreeNode<Integer> successorNode = bst.successor(searchNode);
            System.out.println("本结点:" + searchNode.getObject());
            System.out.println("前趋结点:" + (precursorNode == null ? "NIL" : precursorNode.toString()));
            System.out.println("后继结点:" + (successorNode == null ? "NIL" : successorNode.toString()));
            System.out.println("****************************** ");
            System.out.println("最小节点:" + bst.minElemNode(bst.getRoot()));
            System.out.println("最大节点:" + bst.maxElemNode(bst.getRoot()));
            System.out.println("******************************");
            System.out.println("查询节点6:" + bst.search(6));
            System.out.println("删除节点13");
            bst.delete(13);
            System.out.println("查询节点13:" + bst.search(13));
            System.out.println("新增节点17");
            bst.insert(17);
            System.out.println("******************************");
            Integer[] objectFrontArr = new Integer[]{15, 6, 3, 2, 4, 7, 13, 9, 18, 20};
            Integer[] objectMiddleArr = new Integer[]{2, 3, 4, 6, 7, 9, 13, 15, 18, 20};
            Integer[] objectAfterArr = new Integer[]{2, 4, 3, 9, 13, 7, 6, 20, 18, 15};
            System.out.println("根据前序、中序得到二叉树:");
            BinarySearchTree fmBst = bst.getBstByTraversal(objectFrontArr, objectMiddleArr, SortType.FRONT);
            sortPrint(fmBst);
            System.out.println("根据后序、中序得到二叉树:");
            BinarySearchTree amBst = bst.getBstByTraversal(objectAfterArr, objectMiddleArr, SortType.AFTER);
            sortPrint(amBst);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private static void sortPrint(BinarySearchTree bst) {
        System.out.print("前序排序:");
        bst.preOrderTraversal();
        System.out.print("\r\n中序排序:");
        bst.inOrderTraversal();
        System.out.print("\r\n后序排序:");
        bst.postOrderTraversal();
        System.out.println("\r\n******************************");
    }
}
