package com.java.advanced.exception;

import javax.swing.JOptionPane;

public class GuiMessageNotifier implements Notifier {

	@Override
	public void notify(String s) {
		JOptionPane.showMessageDialog(null, s, "Error", JOptionPane.ERROR_MESSAGE);
	}

}
