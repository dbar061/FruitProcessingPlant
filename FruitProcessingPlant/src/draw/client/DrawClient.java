package draw.client;

import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.net.Socket;
import java.net.SocketException;
import java.net.ConnectException;

/**
 * DrawClient.java
 * 
 * @author			Devin Barry
 * @date			31.12.2012
 * @lastModified	07.01.2013
 * 
 * This class is the network client used to send draw commands to
 * the draw server.
 * 
 * It implements the runnable interface, allowing the run method
 * to be started in a new thread.
 */
public class DrawClient implements Runnable {
	
	private String ipAddress;
	private String port;
	private Socket socket;
	private Object sendObject;

	@Override
	public void run() {
		sendNetworkObject();
	}
	
	public DrawClient(String ipAddress) {
		this.ipAddress = ipAddress;
		port = "0";
		sendObject = null;
	}
	
	public DrawClient(String ipAddress, String port) {
		this(ipAddress);
		this.port = port;
	}
	
	public void setPortNumber(String port) {
		this.port = port;
	}
	
	public void setSendObject(Object sendObject) {
		this.sendObject = sendObject;
	}
	
	private void sendNetworkObject() {
		Integer portNum = Integer.parseInt(port);
		
		Class<?> objectClass = sendObject.getClass();
		String outString = new String("Sending " + objectClass.toString() + " to " + ipAddress + ":" + portNum.intValue());
		System.out.println(outString);
		
		ObjectOutputStream oostream = null;
		
		try {
			//This call should be wrapped in some kind of timeout method
			//or maybe it has a default timeout built in that were not using?
			//If no port is available to listen this method takes forever
			socket = new Socket(ipAddress, portNum.intValue());
		}
		catch (UnknownHostException uhe) {
			System.out.println("Host IP address is unknown: " + ipAddress);
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
			System.out.println("Error occured when sending to " + ipAddress + ":" + port);
			System.out.println("Aborting send...");
			ioe.printStackTrace();
			return;
		}
		System.out.println("completed sending " + objectClass.toString());
	}
	
	/**
	 * Main method used for testing this class separately
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		//Shared object for producer/consumer queue
		//This allows the use of wait and notify
		//ServerQueue<String> sq = new ServerQueue<String>();
		
		String testString = new String("Oh hai from Devin!!");
				
		DrawClient dc = new DrawClient("localhost", "55551");
		dc.setSendObject(testString);
		//Start network stuff in a new thread
		Thread t = new Thread(dc);
		t.start();
		//System.out.println("Completed");
	}
}
