package draw.server;

import network.ObjectSocketServer;
import network.ServerQueue;

/**
 * DrawServer.java
 * 
 * @author			Devin Barry
 * @date			06.01.2013
 * @lastModified	10.01.2013
 * 
 * This is the main class for executing the draw server.
 * The main method in this class starts the draw server.
 * 
 * The draw server has been upgraded to use a blocking queue
 * implementation as the queue onto which received objects are
 * placed.
 *
 * TODO Mouse scroll to zoom in and out
 */
public class DrawServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StdDrawServer sdServer = new StdDrawServer(); //Create the draw server

		//create the queue onto which the network server will dump draw command lists
		ServerQueue<DrawCommandList> drawQ = new ServerQueue<DrawCommandList>(20);

		//Create the network servers to receive DrawCommandList
		ObjectSocketServer<DrawCommandList> dclServer1 = ObjectSocketServer.createServer("55551", drawQ, DrawCommandList.class);
		ObjectSocketServer<DrawCommandList> dclServer2 = ObjectSocketServer.createServer("55552", drawQ, DrawCommandList.class);
		ObjectSocketServer<DrawCommandList> dclServer3 = ObjectSocketServer.createServer("55553", drawQ, DrawCommandList.class);
		ObjectSocketServer<DrawCommandList> dclServer4 = ObjectSocketServer.createServer("55554", drawQ, DrawCommandList.class);

		//Start servers
		//Note: each server runs an infinite while loop
		System.out.println("Starting draw server threads...");
		Thread serverThread1 = new Thread(dclServer1);
		Thread serverThread2 = new Thread(dclServer2);
		Thread serverThread3 = new Thread(dclServer3);
		Thread serverThread4 = new Thread(dclServer4);
		serverThread1.start(); 
		serverThread2.start();
		serverThread3.start();
		serverThread4.start(); //this runs a while loop that never ends.

		//fetch items from the queue and draw them
		for (;;) {
			DrawCommandList commands = drawQ.get();
			sdServer.drawItems(commands);
		}

	}

}