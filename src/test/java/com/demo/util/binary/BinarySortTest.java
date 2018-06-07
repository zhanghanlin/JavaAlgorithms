package com.demo.util.binary;

/**
 * 二叉查找树 - 根据前/后序和中序得到后/前序
 *
 * @author zhanghanlin
 */
public class BinarySortTest {

    public static void main(String[] args) {
        BinarySort binarySort = new BinarySort();
        String front = "ABCDEFG";
        String middle = "CDBAEGF";
        String after = "DCBGFEA";
        TreeNode<String> nodeFront = binarySort.getTreeByFM(front, middle);
        TreeNode<String> nodeAfter = binarySort.getTreeByAM(after, middle);

        binarySort.sort(nodeAfter, BinarySort.SortType.FRONT);
        System.out.println("前序-原始:" + front);
        System.out.print("前序-排序:");
        for (TreeNode<String> node : BinarySort.list) {
            System.out.print(node.getObject());
        }
        System.out.println();

        binarySort.sort(nodeFront, BinarySort.SortType.AFTER);
        System.out.println("后序-原始:" + after);
        System.out.print("后序-排序:");
        for (TreeNode<String> node : BinarySort.list) {
            System.out.print(node.getObject());
        }
    }
}
