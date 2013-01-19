package controller;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.net.InetSocketAddress;

import javax.swing.*;

import console.TextAreaOutputStream;

/**
 * @file RandomFruitGeneratoin.java
 * @author				Devin Barry
 * @date 				19/01/2013
 * @lastModification 	19/01/2013
 *
 * This code is based upon recent versions of Devin's code from
 * from controller.Controller
 * 
 * This code creates a window with buttons and an output console.
 * It is used to start the random fruit generator for Devins 
 * SystemJ FruitProductionLine.
 */
public class RandomFruitGeneration extends JPanel implements ActionListener, Runnable {
	
	// All serializable objects need a serialVersionUID
	private static final long serialVersionUID = -7606337001834860495L;

	private int defaultPort = 44442;
	//private String integerPort = "44442";
	//private String test1Port = "44441";
	//private String test2Port = "6665";
	
	private InetSocketAddress serverAddress;
	private PrintStream local;
	
	//For ip address
	private JLabel ipLabel;
	private JTextField ipField;
	private JButton ipSetButton;
	private JButton fruitButton;
	
	JTextArea log;
	
	public  RandomFruitGeneration() {
		super(new BorderLayout());
		
		// Create the log first, because the action listeners need to refer to it.
		log = new JTextArea(10, 50);
		log.setMargin(new Insets(5, 5, 5, 5));
		log.setEditable(false);
		JScrollPane logScrollPane = new JScrollPane(log);

		// Create the PrintStream and make sure it auto-flushes
		local = new PrintStream(new TextAreaOutputStream(log), true);
		
		//create the IP address area
		ipLabel = new JLabel("IP Address:");
		ipField = new JTextField("localhost");
		ipSetButton = new JButton("Set IP");
		ipSetButton.addActionListener(this);

		//Create all the buttons
		fruitButton = new JButton("Start sending random fruit");
		fruitButton.addActionListener(this);
		
		// For layout purposes, put the buttons in a separate panel
		// which consists of three children panels in BoxLayout
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
		
		//These children panels each hold a row of buttons or labels
		JPanel ipChildPanel = new JPanel(); // use FlowLayout
		JPanel topChildPanel = new JPanel(); // use FlowLayout
		buttonPanel.add(ipChildPanel);
		buttonPanel.add(topChildPanel);
		
		ipChildPanel.add(ipLabel);
		ipChildPanel.add(ipField);
		ipChildPanel.add(ipSetButton);
		topChildPanel.add(fruitButton);

		// Add the buttons and the log to the main panel.
		add(buttonPanel, BorderLayout.PAGE_START);
		add(logScrollPane, BorderLayout.CENTER);
	}

	// When the user clicks on the various buttons, these events are handled
	// here
	@Override
	public void actionPerformed(ActionEvent e) {
		//Set ip address
		if (e.getSource() == ipSetButton) {
			serverAddress = new InetSocketAddress(ipField.getText().trim(), defaultPort);
			local.println("IP Address set to: " + serverAddress);
			log.setCaretPosition(log.getDocument().getLength());
		}
		// Handle appleButton action.
		if (e.getSource() == fruitButton) {
			local.println("Sending random fruit...");
			log.setCaretPosition(log.getDocument().getLength());
			RandomFruitGenerator rfg = new RandomFruitGenerator(serverAddress);
			Thread fruitGen = new Thread(rfg);
			fruitGen.start();
		}
		log.setCaretPosition(log.getDocument().getLength());
	}
	
	@Override
	public void run() {
		for (;;) {
			log.setCaretPosition(log.getDocument().getLength());
		}
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event dispatch thread.
	 */
	private void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("Production Line Controller");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add content to the window.
		frame.add(this);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public void createConsole() {
		// Schedule a job for the event dispatch thread:
		// creating and showing this application's GUI.
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// Turn off metal's use of bold fonts
				UIManager.put("swing.boldMetal", Boolean.FALSE);
				createAndShowGUI();
			}
		});
	}

	/**
	 * Main method used for testing this class separately
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		RandomFruitGeneration rfg = new RandomFruitGeneration();
		rfg.createConsole();
	}
}
