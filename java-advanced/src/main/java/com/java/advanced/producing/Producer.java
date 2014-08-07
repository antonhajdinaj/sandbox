package com.java.advanced.producing;

/**
 * Produces messages
 * 
 * @author antha as a thread
 * 
 */
public class Producer implements Runnable {

	private SharedList list;

	public Producer(SharedList list) {
		this.list = list;
	}

	@Override
	public void run() {
		while (true) {
			System.out.println("Producer");
			this.list.put(String.valueOf((int) (Math.random() * 100)));
		}

	}

}
