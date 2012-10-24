package controller;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import javax.swing.*;

import console.TextAreaOutputStream;
import controller.NetworkConnection.MessageType;

/**
 * @file Console.java
 * @author				Devin Barry
 * @date 				14/10/2012
 * @lastModification 	24/10/2012
 *
 * This code is based upon recent versions of Devin's code from
 * from console.Console
 */
public class Controller extends JPanel implements ActionListener {

	// All serializable objects need a serialVersionUID
	private static final long serialVersionUID = 1L;
	
	private String ipAddress = "192.168.252.109";
	private String defaultPort = "44442";
	private String integerPort = "44442";
	private String test1Port = "44441";
	private String test2Port = "6665";

	private PrintStream local;
	

	private JButton appleButton, pearButton, bananaButton;
	private JButton integerButton, testButton1, testButton2;
	private JLabel integerLabel, test1Label, test2Label;

	JTextArea log;
	
	public Controller() {
		super(new BorderLayout());

		// Create the log first, because the action listeners need to refer to
		// it.
		log = new JTextArea(10, 50);
		log.setMargin(new Insets(5, 5, 5, 5));
		log.setEditable(false);
		JScrollPane logScrollPane = new JScrollPane(log);

		// Create the PrintStream and make sure it auto-flushes
		local = new PrintStream(new TextAreaOutputStream(log), true);

		appleButton = new JButton("Send Apple");
		appleButton.addActionListener(this);
		pearButton = new JButton("Send Pear");
		pearButton.addActionListener(this);
		bananaButton = new JButton("Send Banana");
		bananaButton.addActionListener(this);
		
		integerButton = new JButton("Send Integer");
		integerButton.addActionListener(this);
		testButton1 = new JButton("test1");
		testButton1.addActionListener(this);
		testButton2 = new JButton("test2");
		testButton2.addActionListener(this);
		
		integerLabel = new JLabel("to port " + integerPort);
		test1Label = new JLabel("to port " + test1Port);
		test2Label = new JLabel("to port " + test2Port);
		
		// For layout purposes, put the buttons in a separate panel
		// which consists of two children panels in BoxLayout
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
		
		//These two children panels each hold a row of buttons
		JPanel topChildPanel = new JPanel(); // use FlowLayout
		JPanel botChildPanel = new JPanel(); // use FlowLayout
		buttonPanel.add(topChildPanel);
		buttonPanel.add(botChildPanel);
		
		topChildPanel.add(appleButton);
		topChildPanel.add(pearButton);
		topChildPanel.add(bananaButton);
		botChildPanel.add(integerButton);
		botChildPanel.add(integerLabel);
		botChildPanel.add(testButton1);
		botChildPanel.add(test1Label);
		botChildPanel.add(testButton2);
		botChildPanel.add(test2Label);

		// Add the buttons and the log to the main panel.
		add(buttonPanel, BorderLayout.PAGE_START);
		add(logScrollPane, BorderLayout.CENTER);
		
		//Start server
		//NetworkServer ns = new NetworkServer();
		//Thread t = new Thread(ns);
		//t.start();
	}

	// When the user clicks on the various buttons, these events are handled
	// here
	@Override
	public void actionPerformed(ActionEvent e) {
		// Handle appleButton action.
		if (e.getSource() == appleButton) {
			local.println("Sending apple...");
			log.setCaretPosition(log.getDocument().getLength());
			//sendNetworkObject(new RawGoods(3,0,0), defaultPort);
			
		}
		if (e.getSource() == pearButton) {
			local.println("Sending pear...");
			log.setCaretPosition(log.getDocument().getLength());
			//sendNetworkObject(new RawGoods(0,1,0), defaultPort);
			
		}
		if (e.getSource() == bananaButton) {
			local.println("Sending banana...");
			log.setCaretPosition(log.getDocument().getLength());
			//sendNetworkObject(new RawGoods(0,0,1), defaultPort);
			
		}
		if (e.getSource() == integerButton) {
			local.println("Sending Integer object...");
			log.setCaretPosition(log.getDocument().getLength());
			sendNetworkObject(new Integer(10), integerPort);
			
		}
		if (e.getSource() == testButton1) {
			local.println("Sending String object...");
			log.setCaretPosition(log.getDocument().getLength());
			//sendNetworkObject(new Integer(5), test1Port);
			sendNetworkString("devinisawse", test1Port);
			
		}
		if (e.getSource() == testButton2) {
			local.println("Sending Integer object...");
			log.setCaretPosition(log.getDocument().getLength());
			//sendNetworkObject(new Integer(-12345), test2Port);
			
		}
		log.setCaretPosition(log.getDocument().getLength());
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
		frame.add(new Controller());

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
		Controller sjc = new Controller();
		sjc.createConsole();
		// DevinConsole.console.flush();
		// DevinConsole.console.close();
		// System.out.println("Closed");
		//try { Thread.sleep(5000); } catch (Exception e) {}
		//System.out.println("Network Controller!");
	}
	
	private void sendNetworkObject(Object object, String port) {
		NetworkConnection network = new NetworkConnection(ipAddress);
		network.setMessageType(MessageType.OBJECT);
		network.setPortNumber(port);
		network.setSendObject(object);
		//Start network stuff in a new thread
		Thread t = new Thread(network);
		t.start();
	}
	
	private void sendNetworkString(String string, String port) {
		NetworkConnection network = new NetworkConnection(ipAddress);
		network.setMessageType(MessageType.STRING);
		network.setPortNumber(port);
		network.setSendString(string);
		//Start network stuff in a new thread
		Thread t = new Thread(network);
		t.start();
	}
}
