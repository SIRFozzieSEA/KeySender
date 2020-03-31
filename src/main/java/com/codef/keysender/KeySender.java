package com.codef.keysender;

import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import com.codef.xsalt.utils.XSaLTRobot;

public class KeySender {

	private JFrame frmKeysender;

	public int initialPauseMs = 3000;
	public int preferredRowsToDisplay = 15;

	private Robot robot;

	private String[] initStrings = {
			"Disposable\tTrue\tEncrypted\tTanium ok\tOwner\tStephan.cossette\tShutdownOrder\t1\tShutdownTag\tEnabled\tWBS\tasset_onshore",
			"sudo minikube start --vm-driver=none", "sudo kubectl get pods", "sudo kubectl get services -w",
			"sudo make stop-container", "make build", "make docker-image", "sudo make run-container",
			"git config --global user.name \"Stephan Cossette\"",
			"git config --global user.email \"stephan.cossette@accenture.com\"", "", "", "", "", "", "" };

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KeySender window = new KeySender();
					window.frmKeysender.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @wbp.parser.entryPoint
	 */
	public KeySender() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frmKeysender = new JFrame();
		frmKeysender.setTitle("KeySender");
		frmKeysender.setBounds(100, 100, 589, 434);
		frmKeysender.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 434, 0 };
		gridBagLayout.rowHeights = new int[] { 261, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		frmKeysender.getContentPane().setLayout(gridBagLayout);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		frmKeysender.getContentPane().add(scrollPane, gbc_scrollPane);

		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(4, 4, 4, 4));
		scrollPane.setViewportView(panel);

		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		if (Desktop.isDesktopSupported()) {

			try {
				robot = new Robot();
			} catch (AWTException e) {
				e.printStackTrace();
			}

		} else {
			System.out.println("Robot not supported");
		}

		for (int i = 0; i < preferredRowsToDisplay; i++) {

			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.insets = new Insets(0, 0, 5, 5);
			gbc_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField.gridx = 0;
			gbc_textField.gridy = i;

			JTextField textField = new JTextField();
			textField.setText(initStrings[i]);

			panel.add(textField, gbc_textField);
			textField.setColumns(1);

			GridBagConstraints gbc_btnX = new GridBagConstraints();
			gbc_btnX.insets = new Insets(0, 0, 5, 0);
			gbc_btnX.gridx = 1;
			gbc_btnX.gridy = i;

			final SpecialJButton btnX = new SpecialJButton("X", textField);

			btnX.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println(btnX.getjTextFieldText());

					XSaLTRobot.getKeyPressKeysForString(robot, initialPauseMs, btnX.getjTextFieldText(), 20);

				}
			});

			panel.add(btnX, gbc_btnX);

		}

	}
}