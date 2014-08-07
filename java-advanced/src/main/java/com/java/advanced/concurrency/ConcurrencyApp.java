package com.java.advanced.concurrency;

public class ConcurrencyApp {

	public static void main(String[] args) throws InterruptedException {
		RandomGenerator generator1 = new RandomGenerator("Generator 1");
		Thread thread = new Thread(generator1);
		thread.start();
		
		Thread.sleep(10000);

		generator1.stopRunning();
	}
}
