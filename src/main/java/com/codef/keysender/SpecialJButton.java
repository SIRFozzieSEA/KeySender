package com.codef.keysender;

import javax.swing.JButton;
import javax.swing.JTextField;

public class SpecialJButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField jTextField;

	public String getjTextFieldText() {
		return jTextField.getText();
	}

	public SpecialJButton(String text, JTextField jTextField) {
		super(text);
		this.jTextField = jTextField;
	}
}
