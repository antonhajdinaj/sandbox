package com.java.advanced.producingqueue;

import java.util.concurrent.BlockingQueue;

/***
 * Consumes messages as a thread
 * 
 * @author antha
 * 
 */
public class Consumer implements Runnable {

	private BlockingQueue<String> queue;

	public Consumer(BlockingQueue<String> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		String element;
		while (true) {
			element = this.queue.poll();
			if (element != null) {
				System.out.println("Consumer");
				System.out.println(element);
			}
		}
	}

}
