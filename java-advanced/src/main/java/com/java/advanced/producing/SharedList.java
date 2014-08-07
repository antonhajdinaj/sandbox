package com.java.advanced.producing;

import java.util.ArrayList;
import java.util.List;

import com.java.advanced.exception.ErrorLogger;

/**
 * Contains the messages produced, returns them to consume.
 * 
 * @author antha
 * 
 */
public class SharedList {

	private List<String> elements = new ArrayList<String>();

	public void put(String element) {
		// no more than 10 elements allowed
		synchronized (this) {
			if (this.elements.size() < 10) {
				this.elements.add(element);
				this.notify();
			} else {
				try {
					this.wait();
				} catch (InterruptedException e) {
					ErrorLogger.INSTANCE.handleError(e);
				}
			}
		}
	}

	public String get() {
		// removes elements from the list
		// but not when it's empty !
		String element = null;

		synchronized (this) {
			if (elements.size() > 0) {
				element = elements.remove(0);
				this.notify();
			} else {
				try {
					this.wait();
				} catch (InterruptedException e) {
					ErrorLogger.INSTANCE.handleError(e);
				}
			}

		}
		return element;
	}

}
