package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ObjectSocketServer.java
 * 
 * @author			Devin Barry
 * @date			06.01.2013
 * @lastModified	10.01.2013
 * 
 * This server listens on a port and expects to receive a Java Object
 * over the socket.
 * 
 * The server is parameterized and is setup at creation time to
 * receive objects of a specific type.
 *
 */

public class ObjectSocketServer<T> implements Runnable {
	
	Class<T> type; 
	private String port;
	private ServerQueue<T> q;

	@Override
	public void run() {
		System.out.println("Starting ObjectSocketServer...");
		createObjectReceiveServer();
	}
	
	public ObjectSocketServer(String port, ServerQueue<T> q, Class<T> type) {
		this.port = port.trim();
		this.q = q;
		this.type = type;
		//new Thread(this, "ObjectSocketServer").start();
	}
	
	//Static factory pattern for storing Type inside this object
	public static <S> ObjectSocketServer<S> createServer(String port, ServerQueue<S> q, Class<S> type) {
		return new ObjectSocketServer<S>(port, q, type);
	}
	
	/**
	 * This method converts the bytes from the socket into a Java Object
	 * using ObjectInputStream.
	 * 
	 * This is the main operating method of the class. It has an infinite
	 * while loop, where new data is read from the socket, converted to an
	 * Object and then placed onto the output queue.
	 */
	@SuppressWarnings("unchecked")
	private void createObjectReceiveServer() {
		try (ServerSocket serverSocket = new ServerSocket(Integer.parseInt(port))) {
			while (true) {
				Socket clientSocket = serverSocket.accept(); //block until a connection is made to this socket
				
				//fetch the input stream for this connection and wrap it in an ObjectInputStream
				ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
				Object objectOut = ois.readObject();
				
				//write object to the queue
				if (objectOut != null && this.type.isAssignableFrom(objectOut.getClass())) {
					//This cast is guaranteed because of the if statement above
					q.put((T)objectOut);
					//System.out.println("Received new object " + objectOut.getClass() + " and put it on the queue");
				}
				
				//It may be that we don't need to close the stream or the socket
				//TODO
				ois.close();
				clientSocket.close();
			}
		}
		catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}