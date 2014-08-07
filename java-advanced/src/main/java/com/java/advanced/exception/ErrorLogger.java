package com.java.advanced.exception;

import java.util.ArrayList;
import java.util.List;

public class ErrorLogger {

	public final static ErrorLogger INSTANCE = new ErrorLogger();

	private List<Notifier> notifiers;

	private ErrorLogger() {
		notifiers = new ArrayList<>(3);
		notifiers.add(new GuiMessageNotifier());
		notifiers.add(new ConsoleNotifier());
	}

	public void handleError(Exception e) {
		String message = e.getMessage();
		Throwable cause = e.getCause();
		String causeMessage = "";
		if (cause != null) {
			causeMessage = cause.getMessage();
		}

		for (Notifier notifier : this.notifiers) {
			notifier.notify("Message : " + message + "\nCause message : " + causeMessage);
		}
	}

}
