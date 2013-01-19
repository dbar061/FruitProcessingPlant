package factory;

import java.net.InetSocketAddress;

import network.ObjectSocketClient;
import draw.server.DrawCommandList;

/**
 * AllSegments.java
 * 
 * @author:			Devin Barry
 * @date:			15.01.2013
 * @lastModified: 	17.01.2013
 *
 * This class calls and draws all segments on the production line
 * first clearing anything already drawn to the server.
 */
public class AllSegments {
	
	private static InetSocketAddress drawDestination;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		drawDestination = new InetSocketAddress("192.168.252.109", 55551);
		
		//create a new draw command list
		DrawCommandList dcl = new DrawCommandList();
		
		dcl.addCommand("clear"); //clear the draw server pane
		//Send clear command to the server
		ObjectSocketClient osc = new ObjectSocketClient(drawDestination);
		osc.setSendObject(dcl);
		Thread t = new Thread(osc);
		t.start();
		
		//Create the factory and send all machine draw commands to server
		Factory factory = new Factory(drawDestination);
		factory.paint1(30);
		factory.paint2(30);
		factory.paint3(30);
		factory.paint4(30);
	}

}
