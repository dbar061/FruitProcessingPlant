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
import factory.dimension.PointXY;

import draw.server.DrawCommandList;

/**
 * Segment3.java
 * 
 * @author:			Devin Barry
 * @date:			11.01.2013
 * @lastModified: 	15.01.2013
 *
 * This class contains the machines required to draw segment3
 * of the production line.
 */

public class Segment3 {
	
	public static List<Machine> productionLine = new ArrayList<Machine>(20); //initial capacity of 20
	public static Map<String,Machine> map = new HashMap<String,Machine>(20);
	public static final PointXY base = new PointXY(0, 0);
	private DrawCommandList dcl;
	
	public Segment3() {
		this.createSegment3();
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
	private void createSegment3() {
		HoldingBay hb3 = (HoldingBay)Segment2.map.get("hb3");
		HoldingBay hb7 = (HoldingBay)Segment2.map.get("hb7");
		//================= TOP ROW =================
		
		//Conveyor Six
		ConveyorBelt conveyorSix = new ConveyorBelt(hb3.nextMachineStartPoint(), 8, 0);
		productionLine.add(conveyorSix);
		map.put("cb6", conveyorSix);
		
		//Conveyor Seven
		double c7_y = conveyorSix.getEndPoint().getY() - (ConveyorBeltDimension.WIDTH * 2);
		PointXY startPointCSeven = new PointXY(conveyorSix.getEndPoint().getX(), c7_y);
		ConveyorBelt conveyorSeven = new ConveyorBelt(startPointCSeven, 17, 165);
		productionLine.add(conveyorSeven);
		map.put("cb7", conveyorSeven);
		
		//Sorter Four
		PointXY startPointSFour = new PointXY(conveyorSix.nextMachineStartPoint());
		Sorter sorterFour = new Sorter(startPointSFour);
		productionLine.add(sorterFour);
		map.put("s4", sorterFour);
		
		//Conveyor Nine
		double c9_y = sorterFour.nextMachineStartPoint().getY() + (ConveyorBeltDimension.WIDTH/2);
		PointXY startPointCNine = new PointXY(sorterFour.nextMachineStartPoint().getX(), c9_y);
		ConveyorBelt conveyorNine = new ConveyorBelt(startPointCNine, 8, 10); //10 degrees
		productionLine.add(conveyorNine);
		map.put("cb9", conveyorNine);
		
		//HoldingBay Four
		double hb4_y = conveyorNine.nextMachineStartPoint().getY() + HoldingBayDimension.RADIUS;
		PointXY startPointHBFour = new PointXY(conveyorNine.nextMachineStartPoint().getX(), hb4_y);
		HoldingBay holdingBayFour = new HoldingBay(startPointHBFour);
		productionLine.add(holdingBayFour);
		map.put("hb4", holdingBayFour);
		
		//Conveyor Ten
		ConveyorBelt conveyorTen = new ConveyorBelt(holdingBayFour.nextMachineStartPoint(), 10, 30); //30 degrees
		productionLine.add(conveyorTen);
		map.put("cb10", conveyorTen);
		
		//================= BOTTOM ROW =================
		//Conveyor Thirteen
		ConveyorBelt conveyorThirteen = new ConveyorBelt(hb7.nextMachineStartPoint(), 8, 0);
		productionLine.add(conveyorThirteen);
		map.put("cb13", conveyorThirteen);
		
		//Conveyor Fourteen
		double c14_y = conveyorThirteen.getEndPoint().getY();
		PointXY startPointCFourteen = new PointXY(conveyorThirteen.getEndPoint().getX(), c14_y);
		ConveyorBelt conveyorFourteen = new ConveyorBelt(startPointCFourteen, 17, 195);
		productionLine.add(conveyorFourteen);
		map.put("cb14", conveyorFourteen);
		
		//Sorter Seven
		PointXY startPointSSeven = new PointXY(conveyorThirteen.nextMachineStartPoint());
		Sorter sorterSeven = new Sorter(startPointSSeven);
		productionLine.add(sorterSeven);
		map.put("s7", sorterSeven);
		
		//Conveyor Twelve
		double c12_y = sorterSeven.nextMachineStartPoint().getY() + (ConveyorBeltDimension.WIDTH/2);
		PointXY startPointCTwelve = new PointXY(sorterSeven.nextMachineStartPoint().getX(), c12_y);
		ConveyorBelt conveyorTwelve = new ConveyorBelt(startPointCTwelve, 8, -10); //-10 degrees
		productionLine.add(conveyorTwelve);
		map.put("cb12", conveyorTwelve);
		
		//HoldingBay Eight
		double hb8_y = conveyorTwelve.nextMachineStartPoint().getY() + HoldingBayDimension.RADIUS;
		PointXY startPointHBEight = new PointXY(conveyorTwelve.nextMachineStartPoint().getX(), hb8_y);
		HoldingBay holdingBayEight = new HoldingBay(startPointHBEight);
		productionLine.add(holdingBayEight);
		map.put("hb8", holdingBayEight);
		
		//Conveyor Eleven
		ConveyorBelt conveyorEleven = new ConveyorBelt(holdingBayEight.nextMachineStartPoint(), 10, -30); //-30 degrees
		productionLine.add(conveyorEleven);
		map.put("cb11", conveyorEleven);
		
		
		//================= CENTRAL =================
		//ExtendedPlatform Three
		double midPointY = ((conveyorTen.getEndPoint().getY() - ConveyorBeltDimension.WIDTH) + conveyorEleven.getEndPoint().getY()) / 2;
		double ep3_y = midPointY + ExtendedPlatformDimension.RADIUS;
		PointXY startPointEThree = new PointXY(conveyorEleven.nextMachineStartPoint().getX(), ep3_y);
		ExtendedPlatform extendedPlatformThree = new ExtendedPlatform(startPointEThree);
		productionLine.add(extendedPlatformThree);
		map.put("ep3", extendedPlatformThree);
		
		//HoldingBay Five
		double hb5_y = extendedPlatformThree.nextMachineStartPoint().getY() + HoldingBayDimension.RADIUS;
		PointXY startPointHBFive = new PointXY(extendedPlatformThree.nextMachineStartPoint().getX(), hb5_y);
		HoldingBay holdingBayFive = new HoldingBay(startPointHBFive);
		productionLine.add(holdingBayFive);
		map.put("hb5", holdingBayFive);
	}
}
