package com.java.advanced.producing;

public class ConncurrentApp {

	public static void main(String[] args) {
		SharedList list = new SharedList();

		Producer producer = new Producer(list);

		Consumer consumer = new Consumer(list);

		// start !

		new Thread(producer).start();
		
		new Thread(consumer).start();

	}
}
