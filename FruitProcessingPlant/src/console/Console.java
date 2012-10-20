/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 

package console;

/**
 * @file Console.java
 * @author Devin Barry
 * @date 01/10/2012
 * @lastModification 12/10/2012
 *  
 * Console is a class that creates a separate output console that
 * print messages can be sent to for use with SystemJ code. This
 * makes it easier to see certain messages from the huge list of
 * others that are auto-printed by SystemJ.
 * 
 * This code is very loosely based upon code created for COMPSYS.705
 * Assignment 1, which in turn is constructed around a java example
 * from Oracle for a file chooser app. 
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.PrintStream;

public class Console extends JPanel implements ActionListener {
	
	//public PrintStream out;
	public static PrintStream out;
	
	//JPanel needs a serialVersionUID. Not sure why
	private static final long serialVersionUID = 1L;
	private JButton resetButton, testButton;
	JTextArea log;
	
	public Console() {
		super(new BorderLayout());
		
		//Create the log first, because the action listeners need to refer to it.
		log = new JTextArea(10,50);
		log.setMargin(new Insets(5,5,5,5));
		log.setEditable(false);
		JScrollPane logScrollPane = new JScrollPane(log);
		
		//Create the PrintStream and make sure it auto-flushes
		out = new PrintStream(new TextAreaOutputStream(log), true);
		
		resetButton = new JButton("Clear Console");
		resetButton.addActionListener(this);
		testButton = new JButton("Test");
		testButton.addActionListener(this);
		
		//For layout purposes, put the buttons in a separate panel
		JPanel buttonPanel = new JPanel(); //use FlowLayout
		buttonPanel.add(resetButton);
		buttonPanel.add(testButton);
		
		//Add the buttons and the log to this panel.
		add(buttonPanel, BorderLayout.PAGE_START);
		add(logScrollPane, BorderLayout.CENTER);
	}

	//When the user clicks on the various buttons, these events are handled here
	public void actionPerformed(ActionEvent e) {
		//Handle resetButton action.
		if (e.getSource() == resetButton) {
			log.setText(null);
			log.setCaretPosition(log.getDocument().getLength());
		}
		if (e.getSource() == testButton) {
			out.println("Testing Testing 123");
			log.setCaretPosition(log.getDocument().getLength());
		}
		log.setCaretPosition(log.getDocument().getLength());
	}
	
	/**
	 * Create the GUI and show it.  For thread safety,
	 * this method should be invoked from the
	 * event dispatch thread.
	 */
	private void createAndShowGUI() {
		//Create and set up the window.
		JFrame frame = new JFrame("Devin Console");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Add content to the window.
		frame.add(new Console());
		
		//Display the window.
		frame.pack();
		frame.setVisible(true);
	}
	
	public void createConsole() {
		//Schedule a job for the event dispatch thread:
		//creating and showing this application's GUI.
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				//Turn off metal's use of bold fonts
				UIManager.put("swing.boldMetal", Boolean.FALSE);
				createAndShowGUI();
			}
		});
	}
	
	//Main method used for testing this class separately
	public static void main(String[] args) {
		Console dc = new Console();
		dc.createConsole();
		//Sleep for 5s to give console time to show
		try { Thread.sleep(5000); } catch (Exception e) {}
		
		System.out.println("First Test Print");
		Console.out.println("Hello World From SystemJ");
		Console.out.println("LineTest");
		//DevinConsole.console.flush();
		//DevinConsole.console.close();
		//System.out.println("Closed");
	}
	
	
}