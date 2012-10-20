package controller;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.UnknownHostException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class NetworkConnection implements Runnable {
	
	public static enum MessageType {
		OBJECT,
		STRING,
		MESSAGE
	}
	
	private MessageType thisType;
	
	private String ipAddress;
	private String port;
	
	private PrintWriter remote;
	private Socket socket;
	
	private Object sendObject;
	private String sendString;
	private String sendMessage;

	@Override
	public void run() {
		if (thisType.equals(MessageType.OBJECT)) {
			sendNetworkObject();
		}
		else if (thisType.equals(MessageType.STRING)) {
			sendNetworkString();
		}
		else {
			sendNetworkMessage();
		}
		
	}
	
	public NetworkConnection(String ipAddress) {
		this.ipAddress = ipAddress;
		port = "0";
		sendObject = null;
		sendString = "";
		sendMessage = "";
		thisType = MessageType.OBJECT;
	}
	
	public NetworkConnection(String ipAddress, String port) {
		this(ipAddress);
		this.port = port;
	}
	
	public void setPortNumber(String port) {
		this.port = port;
	}
	
	public void setSendObject(Object sendObject) {
		this.sendObject = sendObject;
	}
	
	public void setSendString(String sendString) {
		this.sendString = sendString;
	}
	
	public void setSendMessage(String sendMessage) {
		this.sendMessage = sendMessage;
	}
	
	public void setMessageType(MessageType thisType) {
		this.thisType = thisType;
	}
	
	private void sendNetworkMessage() {
		System.out.println("Sending message to " + ipAddress + ":" + port);
		try {
			socket = new Socket(ipAddress, Integer.parseInt(port));
			remote = new PrintWriter(socket.getOutputStream(), true);
			remote.println(sendMessage); //netcat
			remote.close();
			socket.close();
		}
		catch (Exception e) {
			System.out.println("Error occured when connecting to " + ipAddress
					+ ":" + port);
			e.printStackTrace();
		}
		System.out.println("completed sending message " + sendMessage);
	}
	
	private void sendNetworkString() {
		System.out.println("Sending string to " + ipAddress + ":" + port);
		try {
			socket = new Socket(ipAddress, Integer.parseInt(port));
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			byte[] dataToSend = sendString.getBytes();
			out.write(dataToSend, 0, dataToSend.length);
			out.close();
			socket.close();
		}
		catch (Exception e) {
			System.out.println("Error occured when connecting to " + ipAddress
					+ ":" + port);
			e.printStackTrace();
		}
		System.out.println("completed sending String " + sendString);
	}
	
	private void sendNetworkObject() {
		Integer portNum = Integer.parseInt(port);
		//System.out.println("Trying to get class..");
		Class<?> objectClass = sendObject.getClass();
		//System.out.println("Got class...");
		String obClassString = objectClass.toString();
		//System.out.println("Got class String..." + obClassString);
		String outString = new String("Sending " + obClassString + " to " + ipAddress + ":" + portNum.intValue());
		//System.out.println("created string: " + outString);
		ObjectOutputStream oostream = null;
		
		
		System.out.println(outString);
		try {
			//This call should be wrapped in some kind of timeout method
			//or maybe it has a default timeout built in that were not using?
			//If no port is available to listen this method takes forever
			socket = new Socket(ipAddress, portNum.intValue());
		}
		catch (UnknownHostException e) {
			System.out.println(e);
			System.out.println("Aborting send...");
			return;
		}
		catch (IOException e) {
			System.out.println(e);
			System.out.println("Aborting send...");
			return;
		}
		System.out.println("created socket");
		
		try {
			oostream = new ObjectOutputStream(socket.getOutputStream());
		}
		catch (IOException e) {
			System.out.println(e);
			System.out.println("Aborting send...");
			return;
		}
		System.out.println("got outputstream");
		
		try {
			oostream.writeObject(sendObject);
			oostream.close();
			socket.close();
		}
		catch (IOException e) {
			System.out.println("Error occured when sending to " + ipAddress
					+ ":" + port);
			e.printStackTrace();
		}
		System.out.println("completed sending " + obClassString);
	}
}
