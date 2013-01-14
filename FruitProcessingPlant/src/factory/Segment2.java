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
import factory.dimension.ExtendedPlatformDimension;
import factory.dimension.HoldingBayDimension;
import factory.dimension.SorterDimension;
import factory.dimension.PointXY;


import draw.server.DrawCommandList;

/**
 * Segment2.java
 * 
 * @author:			Devin Barry
 * @date:			11.01.2013
 * @lastModified: 	15.01.2013
 *
 * This class contains the machines required to draw segment2
 * of the production line.
 * 
 * All the y dimensions for all the machines are automatically determined.
 * The x dimensions are hard-wired and are thus a TODO
 */

public class Segment2 {
	
	public static List<Machine> productionLine = new ArrayList<Machine>(20); //initial capacity of 20
	public static Map<String,Machine> map = new HashMap<String,Machine>(20);
	public static final PointXY base = new PointXY(0, 0);
	private DrawCommandList dcl;
	
	public Segment2() {
		this.createSegment2();
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
	private void createSegment2() {
		ConveyorBelt cb2 = (ConveyorBelt)Segment1.map.get("cb2");
		ConveyorBelt cb15 = (ConveyorBelt)Segment1.map.get("cb15");
		//================= TOP ROW =================
		
		//Sorter Three
		Sorter sorterThree = new Sorter(cb2.nextMachineStartPoint());
		productionLine.add(sorterThree);
		map.put("s3", sorterThree);
		
		//Conveyor Four
		//Conveyor belt is drawn from the top left corner
		double c4_y = sorterThree.nextMachineStartPoint().getY() + SorterDimension.RADIUS + ConveyorBeltDimension.WIDTH;
		PointXY startPointCFour = new PointXY(sorterThree.nextMachineStartPoint().getX(), c4_y);
		ConveyorBelt conveyorFour = new ConveyorBelt(startPointCFour, 8, 0);
		productionLine.add(conveyorFour);
		map.put("cb4", conveyorFour);
		
		//Conveyor Five
		double c5_y = sorterThree.nextMachineStartPoint().getY() - SorterDimension.RADIUS;
		PointXY startPointCFive = new PointXY(sorterThree.nextMachineStartPoint().getX(), c5_y);
		ConveyorBelt conveyorFive = new ConveyorBelt(startPointCFive, 8, 0);
		productionLine.add(conveyorFive);
		map.put("cb5", conveyorFive);
		
		//Extended Platform One
		double ep1_y = sorterThree.nextMachineStartPoint().getY() + ExtendedPlatformDimension.RADIUS;
		PointXY startPointEOne = new PointXY(conveyorFour.nextMachineStartPoint().getX() - 15, ep1_y);
		ExtendedPlatform extendedPlatformOne = new ExtendedPlatform(startPointEOne);
		productionLine.add(extendedPlatformOne);
		map.put("ep1", extendedPlatformOne);
		
		//HoldingBay Three
		double hb3_y = extendedPlatformOne.nextMachineStartPoint().getY() + HoldingBayDimension.RADIUS;
		PointXY startPointHBThree = new PointXY(extendedPlatformOne.nextMachineStartPoint().getX(), hb3_y);
		HoldingBay holdingBayThree = new HoldingBay(startPointHBThree);
		productionLine.add(holdingBayThree);
		map.put("hb3", holdingBayThree);
		
		
		//================= BOTTOM ROW =================
		//Sorter Six
		Sorter sorterSix = new Sorter(cb15.nextMachineStartPoint());
		productionLine.add(sorterSix);
		map.put("s6", sorterSix);
		
		//Conveyor Eighteen
		double c18_y = sorterSix.nextMachineStartPoint().getY() + SorterDimension.RADIUS + ConveyorBeltDimension.WIDTH;
		PointXY startPointCEighteen = new PointXY(sorterSix.nextMachineStartPoint().getX(), c18_y);
		ConveyorBelt conveyorEighteen = new ConveyorBelt(startPointCEighteen, 8, 0);
		productionLine.add(conveyorEighteen);
		map.put("cb18", conveyorEighteen);
		
		//Conveyor Nineteen
		double c19_y = sorterSix.nextMachineStartPoint().getY() - SorterDimension.RADIUS;
		PointXY startPointCNineteen = new PointXY(sorterSix.nextMachineStartPoint().getX(), c19_y);
		ConveyorBelt conveyorNineteen = new ConveyorBelt(startPointCNineteen, 8, 0);
		productionLine.add(conveyorNineteen);
		map.put("cb19", conveyorNineteen);
		
		//ExtendedPlatform Six
		double ep6_y = sorterSix.nextMachineStartPoint().getY() + ExtendedPlatformDimension.RADIUS;
		PointXY startPointESix = new PointXY(conveyorEighteen.nextMachineStartPoint().getX() - 15, ep6_y);
		ExtendedPlatform extendedPlatformSix = new ExtendedPlatform(startPointESix);
		productionLine.add(extendedPlatformSix);
		map.put("ep6", extendedPlatformSix);
		
		//HoldingBay Seven
		double hb7_y = extendedPlatformSix.nextMachineStartPoint().getY() + HoldingBayDimension.RADIUS;
		PointXY startPointHBSeven = new PointXY(extendedPlatformSix.nextMachineStartPoint().getX(), hb7_y);
		HoldingBay holdingBaySeven = new HoldingBay(startPointHBSeven);
		productionLine.add(holdingBaySeven);
		map.put("hb7", holdingBaySeven);
	}
}
