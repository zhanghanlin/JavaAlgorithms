package com.demo.util.binary;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉查找树 - 根据前/后序和中序得到后/前序
 *
 * @author zhanghanlin
 */
public class BinarySort {

    /**
     * 存储二叉树集合
     */
    static List<TreeNode<String>> list = new ArrayList<>();

    /**
     * 根据前序和中序得到二叉树
     *
     * @param front  前序
     * @param middle 中序
     * @return TreeNode<String>
     */
    TreeNode<String> getTreeByFM(String front, String middle) {
        TreeNode<String> node = new TreeNode<>(String.valueOf(front.charAt(0)));
        int index = middle.indexOf(node.getObject());
        //表示存在左右子树
        if (index > 0) {
            node.setLeftNode(getTreeByFM(front.substring(1, index + 1), middle.substring(0, index)));
        }
        if (index + 1 < middle.length()) {
            node.setRightNode(getTreeByFM(front.substring(index + 1, front.length()), middle.substring(index + 1, middle.length())));
        }
        return node;
    }

    /**
     * 根据后序和中序得到二叉树
     *
     * @param after  后序
     * @param middle 中序
     * @return TreeNode<String>
     */
    TreeNode<String> getTreeByAM(String after, String middle) {
        TreeNode<String> node = new TreeNode<>(String.valueOf(after.substring(after.length() - 1, after.length())));
        int index = middle.indexOf(node.getObject());
        //表示存在左右子树
        if (index > 0) {
            node.setLeftNode(getTreeByAM(after.substring(0, index), middle.substring(0, index)));
        }
        if (index + 1 < middle.length()) {
            node.setRightNode(getTreeByAM(after.substring(index, after.length() - 1), middle.substring(index + 1, middle.length())));
        }
        return node;
    }

    public void sort(TreeNode<String> node, SortType sortType) {
        if (!list.isEmpty()) {
            list.clear();
        }
        sortTree(node, sortType);
    }

    /**
     * 二叉树排序
     *
     * @param node     TreeNode
     * @param sortType SortType
     */
    private void sortTree(TreeNode<String> node, SortType sortType) {
        if (node == null) {
            return;
        }
        if (sortType.equals(SortType.FRONT)) {
            //前序
            list.add(node);
        }
        sortTree(node.getLeftNode(), sortType);
        sortTree(node.getRightNode(), sortType);
        if (sortType.equals(SortType.AFTER)) {
            //后序
            list.add(node);
        }
    }
}
