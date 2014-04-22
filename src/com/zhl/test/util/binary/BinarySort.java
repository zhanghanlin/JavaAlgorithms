package com.zhl.test.util.binary;
import java.util.ArrayList;
import java.util.List;

public class BinarySort {
	static List<TNode> list = new ArrayList<TNode>();	//存储二叉树集合
	public class TNode{
		String key;
		TNode left;
		TNode right;
	}
	
	/**
	 * 根据前序和中序得到二叉树
	 * @param qian
	 * @param zhong
	 * @return
	 */
	public TNode buildA(String qian,String zhong){
		TNode node = new TNode();
		node.key = String.valueOf(qian.charAt(0));
		int index = zhong.indexOf(node.key);
		if (index > 0) {	//表示存在左右子树
			node.left = buildA(qian.substring(1,index + 1), zhong.substring(0, index));
		}
		if(index + 1 < zhong.length()) {
			node.right = buildA(qian.substring(index + 1,qian.length()), zhong.substring(index + 1 ,zhong.length()));
		}
		return node;
	}
	
	/**
	 * 根据后序和中序得到二叉树
	 * @param hou
	 * @param zhong
	 * @return
	 */
	public TNode buildB(String hou,String zhong){
		TNode node = new TNode();
		node.key = String.valueOf(hou.substring(hou.length() - 1, hou.length()));
		int index = zhong.indexOf(node.key);
		if (index > 0) {	//表示存在左右子树
			node.left = buildB(hou.substring(0,index), zhong.substring(0, index));
		}
		if(index + 1 < zhong.length()) {
			node.right = buildB(hou.substring(index,hou.length() - 1), zhong.substring(index + 1 ,zhong.length()));
		}
		return node;
	}
	
	public void sort(TNode t,int key) {
		if (!list.isEmpty()) {
			list.clear();
		}
		TNSort(t, key);
	}
	/**
	 * 二叉树排序
	 */
	public void TNSort(TNode t,int key){
		if (t == null) return;
		if (key == 1) list.add(t);	//前序
		TNSort(t.left, key);
		if (key == 2) list.add(t);	//中序
		TNSort(t.right, key);
		if (key == 3) list.add(t);	//后序
	}
	
	public static void main(String[] args) {
		BinarySort test = new BinarySort();
		String qian = "ABCDEFG";
		String zhong = "CDBAEGF";
		String hou = "DCBGFEA";
		TNode nodeA = test.buildA(qian,zhong);
		TNode nodeB = test.buildB(hou,zhong);
		test.sort(nodeB, 1);
		System.out.print("先序:");
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i).key + " ");
		}
		System.out.println("");
		
		test.sort(nodeA, 3);
		System.out.print("后序:");
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i).key + " ");
		}
	}
}
