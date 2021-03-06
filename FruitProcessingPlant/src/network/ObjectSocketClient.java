package network;

import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.net.Socket;
import java.net.InetSocketAddress;

/**
 * ObjectSocketClient.java
 * 
 * @author			Devin Barry
 * @date			31.12.2012
 * @lastModified	17.01.2013
 * 
 * This class is the network client used to send draw commands to
 * the draw server.
 * 
 * It implements the runnable interface, allowing the run method
 * to be started in a new thread.
 */
public class ObjectSocketClient implements Runnable {
	
	private InetSocketAddress serverAddress;
	private Socket socket;
	private Object sendObject;

	@Override
	public void run() {
		sendNetworkObject();
	}
	
	public ObjectSocketClient(InetSocketAddress serverAddress) {
		this.serverAddress = serverAddress;
		sendObject = null;
	}
	
	public void setServerAddress(InetSocketAddress serverAddress) {
		this.serverAddress = serverAddress;
	}
	
	public void setSendObject(Object sendObject) {
		this.sendObject = sendObject;
	}
	
	private void sendNetworkObject() {
		//Class<?> objectClass = sendObject.getClass();
		//String outString = new String("Sending " + objectClass.toString() + " to " + ipAddress + ":" + portNum.intValue());
		//System.out.println(outString);
		
		ObjectOutputStream oostream = null;
		
		try {
			//This call should be wrapped in some kind of timeout method
			//or maybe it has a default timeout built in that were not using?
			//If no port is available to listen this method takes forever
			socket = new Socket(serverAddress.getAddress(), serverAddress.getPort());
		}
		catch (UnknownHostException uhe) {
			System.out.println("Host IP address is unknown: " + serverAddress.getAddress());
			System.out.println("Aborting send...");
			System.out.println(uhe);
			//No stack trace printed here
			return;
		}
		catch (ConnectException ce) {
			System.out.println("Error occurred while attempting to connect a socket to a remote address and port");
			System.out.println("Aborting send...");
			System.out.println(ce);
			//No stack trace printed here
			return;
		}
		catch (SocketException se) {
			System.out.println("I/O Exception during socket connection");
			System.out.println("Aborting send...");
			se.printStackTrace();
			return;
		}
		catch (IOException ioe) {
			System.out.println("I/O Exception during socket connection");
			System.out.println("Aborting send...");
			ioe.printStackTrace();
			return;
		}
		//System.out.println("created socket");
		
		try {
			oostream = new ObjectOutputStream(socket.getOutputStream());
		}
		catch (IOException ioe) {
			System.out.println("I/O Exception during output stream creation");
			System.out.println("Aborting send...");
			ioe.printStackTrace();
			return;
		}
		//System.out.println("created outputstream");
		
		try {
			oostream.writeObject(sendObject);
			oostream.close();
			socket.close();
		}
		catch (IOException ioe) {
			System.out.println("Error occured when sending to " + serverAddress);
			System.out.println("Aborting send...");
			ioe.printStackTrace();
			return;
		}
		//System.out.println("completed sending " + objectClass.toString());
	}
	
	/**
	 * Main method used for testing this class separately
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Starting Client Test");
		String testString = new String("Oh hai from Devin!!");
		
		InetSocketAddress serverAddress = new InetSocketAddress("localhost", 55551);
		ObjectSocketClient osc = new ObjectSocketClient(serverAddress);
		osc.setSendObject(testString);
		//start the ObjectSocketClient in a new thread
		Thread t = new Thread(osc);
		t.start();
	}
}
