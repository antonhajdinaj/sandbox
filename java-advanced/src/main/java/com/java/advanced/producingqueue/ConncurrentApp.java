package com.java.advanced.producingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConncurrentApp {

	public static void main(String[] args) {
		BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);

		Producer producer = new Producer(queue);

		Consumer consumer = new Consumer(queue);

		// start !

		new Thread(producer).start();
		
		new Thread(consumer).start();

	}
}
