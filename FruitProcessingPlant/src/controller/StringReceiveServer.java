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

public class StringReceiveServer implements Runnable {
	
	private String port;
	private ServerQueue<String> q;

	@Override
	public void run() {
		System.out.println("Starting StringReceiveServer...");
		createStringReceiveServer();
	}
	
	public StringReceiveServer(String port, ServerQueue<String> q) {
		this.port = port.trim();
		this.q = q;
	}
	
	private void createStringReceiveServer() {
		int bufferSize = 100;
			ServerSocket serverSocket = null;
			Socket clientSocket = null;
			try {
				serverSocket = new ServerSocket(Integer.parseInt(port));
				while (true) {
					clientSocket = serverSocket.accept();
					InputStream in = clientSocket.getInputStream();
					byte[] data = new byte[bufferSize];
					int count = in.read(data, 0, bufferSize);
					System.out.println("Read " + count + " bytes from the socket");
					Vector v = new Vector();
					int totalLength = 0;
					while (count != -1) {
						v.addElement(data);
						v.addElement(count);
						totalLength = totalLength + count;
						data = new byte[bufferSize];
						count = in.read(data, 0, bufferSize);
					}
					// use totalLength to create the overall byte string
					data = new byte[totalLength];
					int currPos = 0;
					for (int i = 0; i < v.size(); i = i + 2) {
						int currLen = ((Integer) v.elementAt(i + 1)).intValue();
						System.arraycopy(v.elementAt(i), 0, data, currPos,
								currLen);
						currPos = currPos + currLen;
					}
					//Add the received data to the ServerQueue
					String receiveString = new String(data);
					q.put(receiveString);
					System.out.println(receiveString);
					
					//Close everything down
					in.close();
					clientSocket.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				if (clientSocket != null) {
					try {
						clientSocket.close();
						serverSocket.close();
					} catch (Exception ee) {
						ee.printStackTrace();
					}
				}
			}
	}
	
	private void createObjectSocketServer() {
		int bufferSize = 100;
		ServerSocket serverSocket = null;
		Socket clientSocket = null;
		try {
			serverSocket = new ServerSocket(Integer.parseInt(port));
			while (true) {
				clientSocket = serverSocket.accept();
				InputStream in = clientSocket.getInputStream();
				byte[] data = new byte[bufferSize];
				int count = in.read(data, 0, bufferSize);
				System.out.println("Read " + count + " bytes from the socket");
				Vector v = new Vector();
				int totalLength = 0;
				while (count != -1) {
					v.addElement(data);
					v.addElement(count);
					totalLength = totalLength + count;
					data = new byte[bufferSize];
					count = in.read(data, 0, bufferSize);
				}
				// use totalLength to create the overall byte string
				data = new byte[totalLength];
				int currPos = 0;
				for (int i = 0; i < v.size(); i = i + 2) {
					int currLen = ((Integer) v.elementAt(i + 1)).intValue();
					System.arraycopy(v.elementAt(i), 0, data, currPos, currLen);
					currPos = currPos + currLen;
				}
				// print out the received data
				System.out.println(new String(data));
				in.close();
				clientSocket.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			if (clientSocket != null) {
				try {
					clientSocket.close();
					serverSocket.close();
				}
				catch (Exception ee) {
					ee.printStackTrace();
				}
			}
		}
	}
}
