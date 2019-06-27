package com.demo.util.binary;

import java.util.Arrays;

/**
 * 二叉查找树
 *
 * @author zhanghanlin
 * @date 2016-08-24
 */
class BinarySearchTree {

    /**
     * 根节点
     */
    private TreeNode<Integer> root = null;

    /**
     * 默认构造函数
     */
    BinarySearchTree() {
    }

    /**
     * 构造函数
     *
     * @param root 二叉树节点
     */
    private BinarySearchTree(TreeNode<Integer> root) {
        this.root = root;
    }

    /**
     * 判断二叉查找树是否为空
     *
     * @return boolean
     */
    boolean isEmpty() {
        return root == null;
    }

    /**
     * 插入
     *
     * @param object object
     * @throws Exception object is not exist
     */
    void insert(Integer object) throws Exception {
        TreeNode<Integer> newNode = new TreeNode<>(object);
        if (root == null) {
            root = newNode;
            return;
        }
        TreeNode<Integer> rootNode = root;
        TreeNode<Integer> parentNode = null;
        while (rootNode != null) {
            parentNode = rootNode;
            if (object.compareTo(rootNode.getObject()) < 0) {
                rootNode = rootNode.getLeftNode();
            } else if (object.compareTo(rootNode.getObject()) > 0) {
                rootNode = rootNode.getRightNode();
            } else {
                throw new Exception(String.format("%s is exist", object));
            }
        }
        newNode.setParentNode(parentNode);
        if (object.compareTo(parentNode.getObject()) < 0) {
            parentNode.setLeftNode(newNode);
        } else {
            parentNode.setRightNode(newNode);
        }
    }

    /**
     * 删除
     *
     * @param object object
     * @throws Exception object is not exist
     */
    void delete(Integer object) throws Exception {
        TreeNode<Integer> node = search(object);
        if (node == null) {
            throw new Exception("object is not exist!");
        }
        delete(node);
    }

    /**
     * 查询
     *
     * @param t Object
     * @return TreeNode
     */
    TreeNode<Integer> search(Integer t) {
        TreeNode<Integer> parentNode = root;
        while (parentNode != null && !parentNode.getObject().equals(t)) {
            if (t.compareTo(parentNode.getObject()) < 0) {
                parentNode = parentNode.getLeftNode();
            } else {
                parentNode = parentNode.getRightNode();
            }
        }
        return parentNode;
    }

    /**
     * 获取给定节点最小结点
     *
     * @param node TreeNode
     * @return TreeNode
     * @throws Exception node is null
     */
    TreeNode<Integer> minElemNode(TreeNode<Integer> node) throws Exception {
        if (node == null) {
            throw new Exception("node is null");
        }
        TreeNode<Integer> parentNode = node;
        while (parentNode.getLeftNode() != null) {
            parentNode = parentNode.getLeftNode();
        }
        return parentNode;
    }

    /**
     * 获取给定节点最大结点
     *
     * @param node TreeNode
     * @return TreeNode
     * @throws Exception node is null
     */
    TreeNode<Integer> maxElemNode(TreeNode<Integer> node) throws Exception {
        if (node == null) {
            throw new Exception("node is null");
        }
        TreeNode<Integer> parentNode = node;
        while (parentNode.getRightNode() != null) {
            parentNode = parentNode.getRightNode();
        }
        return parentNode;
    }

    /**
     * 获取给定结点在中序遍历顺序下的后继结点
     *
     * @param node TreeNode
     * @return TreeNode
     * @throws Exception node is null
     */
    TreeNode<Integer> successor(TreeNode<Integer> node) throws Exception {
        if (node == null) {
            throw new Exception("node is null");
        }
        //若该结点的右子树不为空，则其后继结点就是右子树中的最小关键字结点
        if (node.getRightNode() != null) {
            return minElemNode(node.getRightNode());
        }
        //若该结点右子树为空
        TreeNode<Integer> parentNode = node.getParentNode();
        while (parentNode != null && node == parentNode.getRightNode()) {
            node = parentNode;
            parentNode = parentNode.getParentNode();
        }
        return parentNode;
    }

    /**
     * 获取给定结点在中序遍历顺序下的前趋结点
     *
     * @param node TreeNode
     * @return TreeNode
     * @throws Exception node is null
     */
    TreeNode<Integer> precursor(TreeNode<Integer> node) throws Exception {
        if (node == null) {
            throw new Exception("node is null");
        }
        //若该结点的左子树不为空，则其前趋结点就是左子树中的最大关键字结点
        if (node.getLeftNode() != null) {
            return maxElemNode(node.getLeftNode());
        }
        //若该结点左子树为空
        TreeNode<Integer> parentNode = node.getParentNode();
        while (parentNode != null && node == parentNode.getLeftNode()) {
            node = parentNode;
            parentNode = parentNode.getParentNode();
        }
        return parentNode;
    }

    /**
     * 前序
     * 访问根结点的操作发生在遍历其左右子树之前
     */
    void preOrderTraversal() {
        sort(root, SortType.FRONT);
    }

    /**
     * 中序
     * 访问根结点的操作发生在遍历其左右子树之中
     */
    void inOrderTraversal() {
        sort(root, SortType.MIDDLE);
    }

    /**
     * 后序
     * 访问根结点的操作发生在遍历其左右子树之后
     */
    void postOrderTraversal() {
        sort(root, SortType.AFTER);
    }

    /**
     * 根据中序和前/后序得到BinarySearchTree
     *
     * @param arr       前/后序数组
     * @param middleArr 中序数组
     * @param sortType  排序规则
     * @return BinarySearchTree
     */
    BinarySearchTree getBstByTraversal(Integer[] arr, Integer[] middleArr, SortType sortType) {
        return new BinarySearchTree(getTreeByTraversal(arr, middleArr, sortType));
    }

    /**
     * 删除
     *
     * @param node TreeNode
     * @throws Exception Exception
     */
    private void delete(TreeNode<Integer> node) throws Exception {
        //该结点既无左孩子结点，也无右孩子结点
        TreeNode<Integer> parentNode = node.getParentNode();
        if (node.notChild()) {
            if (parentNode == parentNode.getLeftNode()) {
                parentNode.setLeftNode(null);
            } else {
                parentNode.setRightNode(null);
            }
            return;
        }
        //该结点左孩子结点为空,右孩子结点非空
        if (node.getLeftNode() == null) {
            node.getRightNode().setParentNode(parentNode);
            if (node == parentNode.getLeftNode()) {
                parentNode.setLeftNode(node.getRightNode());
            } else {
                parentNode.setRightNode(node.getRightNode());
            }
            return;
        }
        //该结点左孩子结点非空,右孩子结点为空
        if (node.getRightNode() == null) {
            node.getLeftNode().setParentNode(parentNode);
            if (node == parentNode.getLeftNode()) {
                parentNode.setLeftNode(node.getLeftNode());
            } else {
                parentNode.setRightNode(node.getLeftNode());
            }
            return;
        }
        //该结点左右孩子结点均非空,则删除该结点的后继结点,并用该后继结点取代该结点
        TreeNode<Integer> successorNode = successor(node);
        delete(successorNode);
        node.setObject(successorNode.getObject());
    }

    /**
     * 根据中序和前/后序得到TreeNode
     *
     * @param arr       前/后序数组
     * @param middleArr 中序数组
     * @param sortType  排序规则
     * @return BinarySearchTree
     */
    private TreeNode<Integer> getTreeByTraversal(Integer[] arr, Integer[] middleArr, SortType sortType) {
        Integer object = arr[0];
        if (sortType.equals(SortType.AFTER)) {
            object = arr[arr.length - 1];
        }
        TreeNode<Integer> node = new TreeNode<>(object);
        int index = Arrays.binarySearch(middleArr, node.getObject());
        int leftFrom = 1;
        int leftEnd = index + 1;
        int rightFrom = index + 1;
        int rightEnd = arr.length;
        if (sortType.equals(SortType.AFTER)) {
            leftFrom = 0;
            leftEnd = index;
            rightFrom = index;
            rightEnd = arr.length - 1;
        }
        if (index > 0) {
            node.setLeftNode(getTreeByTraversal(Arrays.copyOfRange(arr, leftFrom, leftEnd), Arrays.copyOfRange(middleArr, 0, index), sortType));
        }
        if (index + 1 < middleArr.length) {
            node.setRightNode(getTreeByTraversal(Arrays.copyOfRange(arr, rightFrom, rightEnd), Arrays.copyOfRange(middleArr, index + 1, middleArr.length), sortType));
        }
        return node;
    }

    /**
     * 排序
     *
     * @param treeNode 二叉树
     * @param sortType 排序规则
     */
    private void sort(TreeNode<Integer> treeNode, SortType sortType) {
        if (treeNode == null) {
            return;
        }
        if (sortType.equals(SortType.FRONT)) {
            System.out.print(treeNode.toString());
        }
        sort(treeNode.getLeftNode(), sortType);
        if (sortType.equals(SortType.MIDDLE)) {
            System.out.print(treeNode.toString());
        }
        sort(treeNode.getRightNode(), sortType);
        if (sortType.equals(SortType.AFTER)) {
            System.out.print(treeNode.toString());
        }
    }

    TreeNode<Integer> getRoot() {
        return root;
    }
}