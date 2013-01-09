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
 */
public class DrawServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StdDrawServer sdServer = new StdDrawServer(); //Create the draw server
		
		//create the queue onto which the network server will dump draw command lists
		ServerQueue<DrawCommandList> drawQ = new ServerQueue<DrawCommandList>();
		
		//Create the network server to receive DrawCommandList
		ObjectSocketServer<DrawCommandList> dclServer = ObjectSocketServer.createServer("55551", drawQ, DrawCommandList.class);
		
		//Start server
		System.out.println("Starting Draw Server...");
		Thread serverThread = new Thread(dclServer);
		serverThread.start(); //this runs a while loop that never ends
		
		//fetch items from the queue and draw them
		for (;;) {
			DrawCommandList commands = drawQ.get();
			sdServer.drawItems(commands);
		}

	}

}
