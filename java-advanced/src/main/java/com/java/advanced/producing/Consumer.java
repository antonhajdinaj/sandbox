package com.java.advanced.producing;

/***
 * Consumes messages as a thread
 * 
 * @author antha
 * 
 */
public class Consumer implements Runnable {

	private SharedList list;

	public Consumer(SharedList list) {
		this.list = list;
	}

	@Override
	public void run() {
		String element;
		while (true) {
			element = this.list.get();
			if (element != null) {
				System.out.println("Consumer");
				System.out.println(element);
			}
		}
	}

}
