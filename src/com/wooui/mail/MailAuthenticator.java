package com.wooui.mail;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class MailAuthenticator extends Authenticator {
	private JDialog passwordDialog = new JDialog(new JFrame(), true);
	private JLabel mainLabel = new JLabel(
			"Please enter your user name and password:");
	private JLabel userLabel = new JLabel("User name:");
	private JLabel passwordLabel = new JLabel("Password:");
	private JTextField usernameField = new JTextField(30);
	private JPasswordField passwordField = new JPasswordField(30);
	private JButton okButton = new JButton("OK");

	public MailAuthenticator() {
		this("");
	}

	public MailAuthenticator(String username) {
		Container container = passwordDialog.getContentPane();
		container.setLayout(new GridLayout(4, 1));
		container.add(mainLabel);

		JPanel p2 = new JPanel();
		p2.add(userLabel);
		p2.add(usernameField);
		container.add(p2);

		JPanel p3 = new JPanel();
		p3.add(passwordLabel);
		p3.add(passwordField);
		container.add(p3);

		JPanel p4 = new JPanel();
		p4.add(okButton);
		container.add(p4);

		passwordDialog.setSize(400, 200);

		ActionListener al = new HideDialog();
		okButton.addActionListener(al);
		usernameField.addActionListener(al);
		passwordField.addActionListener(al);
	}

	class HideDialog implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			passwordDialog.hide();
		}

	}

	protected PasswordAuthentication getPasswordAuthentication() {
		passwordDialog.show();
		String username = usernameField.getText();
		String password = new String(passwordField.getPassword());
		passwordField.setText("");
		return new PasswordAuthentication(username, password);
	}
}
