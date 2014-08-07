package com.java.advanced.concurrency;

public class RandomGenerator implements Runnable {

	private boolean running = true;
	private String name;

	public RandomGenerator(String name) {
		this.name = name;
	}

	public void stopRunning() {
		this.running = false;
	}

	@Override
	public void run() {
		while (running) {
			System.out.println(name + ", " + (int) Math.random() * 100);
		}
	}
}
