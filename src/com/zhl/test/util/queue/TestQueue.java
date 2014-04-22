package com.zhl.test.util.queue;

public class TestQueue {
	public static void main(String[] args) {
		testSequence();
		System.out.println("");
		testLoop();
		System.out.println("");
		testLink();
	}
	
	public static void testLoop(){
		LoopQueue<Integer> lq = new LoopQueue<Integer>(10);
		lq.add(1);
		lq.add(3);
		lq.add(4);
		lq.add(5);
		lq.add(7);
		lq.add(9);
		System.out.println(lq.toString());
	}
	
	public static void testSequence(){
		SequenceQueue<Integer> sq = new SequenceQueue<Integer>(10,20);
		System.out.println(sq.toString() + ">>>" + sq.length());
		sq.add(1);
		sq.add(5);
		sq.add(3);
		sq.add(8);
		sq.add(9);
		sq.add(2);
		System.out.println(sq.toString() + ">>>" + sq.length());
		System.out.println(sq.remove());
		System.out.println(sq.toString() + ">>>" + sq.length());
	}
	
	public static void testLink(){
		LinkQueue<Integer> lq = new LinkQueue<Integer>();
		lq.add(1);
		lq.add(4);
		lq.add(3);
		lq.add(6);
		lq.add(9);
		System.out.println(lq.toString());
	}
}
