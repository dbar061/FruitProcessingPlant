package network;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ObjectReceiveServer.java
 * 
 * @author			Devin Barry
 * @date			24.10.2012
 * @lastModified	06.01.2013
 * 
 * This server listens on a port and expects to receive a Java Object
 * over the socket.
 * 
 * The server is parameterized and is setup at creation time to
 * receive objects of a specific type.
 *
 */

public class ObjectReceiveServer<T> implements Runnable {
	
	Class<T> type; 
	private String port;
	private SimpleServerQueue<T> q;

	@Override
	public void run() {
		System.out.println("Starting ObjectReceiveServer...");
		createObjectReceiveServer();
	}
	
	public ObjectReceiveServer(String port, SimpleServerQueue<T> q, Class<T> type) {
		this.port = port.trim();
		this.q = q;
		this.type = type;
		//new Thread(this, "ObjectReceiveServer").start();
	}
	
	//Static factory pattern for storing Type inside this object
	public static <S> ObjectReceiveServer<S> createMyObject(String port, SimpleServerQueue<S> q, Class<S> type) {
		return new ObjectReceiveServer<S>(port, q, type);
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
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(Integer.parseInt(port));
			while (true) {
				byte[] data = readData(serverSocket);
				//convert bytes from socket into an object
				if ((data[0] == -84) && (data[1] == -19)) {
					ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
					Object objectOut = ois.readObject();
					//write object to the queue
					if (objectOut != null && this.type.isAssignableFrom(objectOut.getClass())) {
						//This cast is guaranteed because of the if statement above
						q.put((T)objectOut);
					}
				}
			}
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
		finally {
			if (serverSocket != null) {
				try {
					serverSocket.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
	}

	/**
	 * This method has been completely re-written.
	 * We have scrapped most of the stupid ass code from the 704 Labs
	 * Now this method looks a bit more reasonable.
	 * 
	 * I think it should be completely removed and the input stream
	 * should be wrapped in the object input stream above.
	 * 
	 * @param serverSocket
	 * @return
	 */
	private byte[] readData(ServerSocket serverSocket) {
		int bufferSize = 100;
		
		Socket clientSocket = null;
		InputStream in = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream(bufferSize);
		
		try {
			clientSocket = serverSocket.accept(); //block until a connection is made to this socket
			in = clientSocket.getInputStream(); //fetch the input stream for this connection
			byte[] data = new byte[bufferSize];
			int count = 0;
			while (count != -1) {
				count = in.read(data, 0, bufferSize);
				//System.out.println("Read " + count + " bytes");
				if (count > 0) baos.write(data, 0, count);
			}
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		finally {
			//close the inputStream and clientSocket
			try {
				if (in != null) in.close();
				if (clientSocket != null) clientSocket.close();
			}
			catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
		return baos.toByteArray();
		//ByteArrayOutputStream does not need to be closed
	}
}