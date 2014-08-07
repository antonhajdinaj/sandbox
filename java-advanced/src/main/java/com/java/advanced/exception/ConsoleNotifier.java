package com.java.advanced.exception;

public class ConsoleNotifier implements Notifier {

	@Override
	public void notify(String s) {
		System.err.println(s);
	}

}
