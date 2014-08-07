package com.java.advanced.producingqueue;

import java.util.concurrent.BlockingQueue;

import com.java.advanced.exception.ErrorLogger;

/**
 * Produces messages
 * 
 * @author antha as a thread
 * 
 */
public class Producer implements Runnable {

	private BlockingQueue<String> queue;

	public Producer(BlockingQueue<String> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		while (true) {
			System.out.println("Producer");
			try {
				this.queue.put(String.valueOf((int) (Math.random() * 100)));
			} catch (InterruptedException e) {
				ErrorLogger.INSTANCE.handleError(e);
			}
		}

	}

}
