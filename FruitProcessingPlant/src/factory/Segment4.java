package factory;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import network.ObjectSocketClient;

import factory.machine.Machine;
import factory.plant.*;
import factory.dimension.ConveyorBeltDimension;
import factory.dimension.HoldingBayDimension;
import factory.dimension.PointXY;
import factory.dimension.SorterDimension;

import draw.server.DrawCommandList;

/**
 * Segment4.java
 * 
 * @author:			Devin Barry
 * @date:			11.01.2013
 * @lastModified: 	11.01.2013
 *
 * This class contains the machines required to draw segment4
 * of the production line.
 */

public class Segment4 {
	
	public static List<Machine> productionLine = new ArrayList<Machine>(20); //initial capacity of 20
	public static Map<String,Machine> map = new HashMap<String,Machine>(20);
	public static final PointXY base = new PointXY(0, 0);
	private DrawCommandList dcl;
	
	public Segment4() {
		this.createSegment4();
	}
	
	public void paint() {
		paint(30);
	}
	
	/**
	 * This should probably be made to be thread safe because
	 * this paint method may be called from multiple different
	 * threads inside the SystemJ program
	 * TODO
	 * 
	 * @param delay
	 */
	public void paint(int delay) {
		dcl = new DrawCommandList(); //start with a new list each time
		
		//Draw all machines in the factory
		Iterator<Machine> i = productionLine.iterator();
		while (i.hasNext()) {
			i.next().draw(dcl, base);
		}
		
		//Show everything that has been drawn
		dcl.addCommand("show", delay);
		
		//optimally, we create a queue and we put the new dcl on the queue
		//the network client reads from the queue and send items on queue
		//to the server
		
		//right now we will just send item straight to server
		ObjectSocketClient osc = new ObjectSocketClient("localhost", "55551");
		osc.setSendObject(dcl);
		
		//start the ObjectSocketClient in a new thread
		Thread t = new Thread(osc);
		t.start();
	}

	/**
	 * Create the components in their correct locations
	 */
	private void createSegment4() {
		PointXY startPointCEight = new PointXY(510, 620);
		ConveyorBelt conveyorEight = new ConveyorBelt(startPointCEight, 8, 0);
		productionLine.add(conveyorEight);
		
		PointXY startPointCSeventeen = new PointXY(510, 520);
		ConveyorBelt conveyorSeventeen = new ConveyorBelt(startPointCSeventeen, 8, 0);
		productionLine.add(conveyorSeventeen);
		
		PointXY startPointHBNine = new PointXY(850, 530);
		HoldingBay holdingBayNine = new HoldingBay(startPointHBNine);
		productionLine.add(holdingBayNine);
		
		PointXY startPointETwo = new PointXY(420, 620);
		ExtendedPlatform extendedPlatformTwo = new ExtendedPlatform(startPointETwo);
		productionLine.add(extendedPlatformTwo);
		
		PointXY startPointEFour = new PointXY(760, 540);
		ExtendedPlatform extendedPlatformFour = new ExtendedPlatform(startPointEFour);
		productionLine.add(extendedPlatformFour);
		
		PointXY startPointEFive = new PointXY(420, 460);
		ExtendedPlatform extendedPlatformFive = new ExtendedPlatform(startPointEFive);
		productionLine.add(extendedPlatformFive);
		
	}
	
	public static void main(String[] args) {
		System.out.println("Segment4 test");
		Segment4 seg4 = new Segment4();
		seg4.paint();
	}
}
