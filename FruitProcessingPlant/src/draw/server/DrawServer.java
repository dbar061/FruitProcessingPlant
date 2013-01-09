package draw.server;

import network.ObjectSocketServer;
import network.SimpleServerQueue;

public class DrawServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StdDrawServer sdServer = new StdDrawServer(); //Create the draw server
		
		//create the queue onto which the network server will dump draw command lists
		SimpleServerQueue<DrawCommandList> drawQ = new SimpleServerQueue<DrawCommandList>();
		
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
