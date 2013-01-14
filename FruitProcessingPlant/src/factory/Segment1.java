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
 * Segment1.java
 * 
 * @author:			Devin Barry
 * @date:			01.10.2012
 * @lastModified: 	15.01.2013
 *
 * This class contains the machines required to draw segment1
 * of the production line.
 */

public class Segment1 {
	
	public static List<Machine> productionLine = new ArrayList<Machine>(20); //initial capacity of 20
	public static Map<String,Machine> map = new HashMap<String,Machine>(20);
	public static final PointXY base = new PointXY(0, 0);
	private DrawCommandList dcl;
	
	public Segment1() {
		this.createSegment1();
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
	private void createSegment1() {
		//Central sorter divides between two lines
		PointXY s8_Pos = new PointXY(0,600);
		Sorter s8 = new Sorter(s8_Pos);
		productionLine.add(s8);
		map.put("s8", s8);
		
		
		//================= BOTTOM ROW =================
		double conveyorLine1Y = s8.nextMachineStartPoint().getY() - 10;
		
		//ConveyorBelt Twenty
		PointXY cbl1_4Pos = new PointXY(s8.nextMachineStartPoint().getX(), conveyorLine1Y);
		ConveyorBelt conveyor18 = new ConveyorBelt(cbl1_4Pos, 18, 35); //size 12, 35 angle
		productionLine.add(conveyor18);
		map.put("cb18", conveyor18);
		
		//HoldingBay 6
		double hbl1_1_y = conveyor18.nextMachineStartPoint().getY() + HoldingBayDimension.RADIUS; // + ConveyorBeltDimension.WIDTH;
		PointXY hbl1_1Pos = new PointXY(conveyor18.nextMachineStartPoint().getX(), hbl1_1_y);
		HoldingBay hbl1_1 = new HoldingBay(hbl1_1Pos);
		productionLine.add(hbl1_1);
		map.put("hb6", hbl1_1);
		
		//Sorter Five
		PointXY sl1_1Pos = new PointXY(hbl1_1.nextMachineStartPoint().getX(), hbl1_1.nextMachineStartPoint().getY() + SorterDimension.RADIUS);
		Sorter sl1_1 = new Sorter(sl1_1Pos);
		productionLine.add(sl1_1);
		map.put("s5", sl1_1);
		
		//ConveyorBelt 16
		double yPosL1 = sl1_1.nextMachineStartPoint().getY() + (ConveyorBeltDimension.WIDTH / 2);
		PointXY cbl1_1Pos = new PointXY(sl1_1.nextMachineStartPoint().getX() - 10, yPosL1 + 30);
		ConveyorBelt conveyor16 = new ConveyorBelt(cbl1_1Pos, 10, -25); //size 10, -25 angle
		productionLine.add(conveyor16);
		map.put("cb16", conveyor16);
		
		//ConveyorBelt 15
		PointXY cbl1_2Pos = new PointXY(sl1_1.nextMachineStartPoint().getX(), yPosL1 - 30);
		ConveyorBelt conveyor15 = new ConveyorBelt(cbl1_2Pos, 8, 0); //size 8, 0 degree angle
		productionLine.add(conveyor15);
		map.put("cb15", conveyor15);
		
		
		//================= TOP ROW =================
		double conveyorLine2Y = s8.nextMachineStartPoint().getY() + 40;
		
		//ConveyorBelt One
		double cb1_x = s8.nextMachineStartPoint().getX() - 20; //this is a hack until we to the TODO s
		PointXY cbl2_4Pos = new PointXY(cb1_x, conveyorLine2Y);
		ConveyorBelt conveyor1 = new ConveyorBelt(cbl2_4Pos, 18, -35); //size 12, -35 angle
		productionLine.add(conveyor1);
		map.put("cb19", conveyor1);
		
		//HoldingBay 2
		double hbl2_1_y = conveyor1.nextMachineStartPoint().getY() + HoldingBayDimension.RADIUS - ConveyorBeltDimension.WIDTH;
		PointXY hbl2_1Pos = new PointXY(conveyor1.nextMachineStartPoint().getX(), hbl2_1_y);
		HoldingBay hbl2_1 = new HoldingBay(hbl2_1Pos);
		productionLine.add(hbl2_1);
		map.put("hb2", hbl2_1);
		
		//Sorter Two
		PointXY sl2_1Pos = new PointXY(hbl2_1.nextMachineStartPoint().getX(), hbl2_1.nextMachineStartPoint().getY() + SorterDimension.RADIUS);
		Sorter sl2_1 = new Sorter(sl2_1Pos);
		productionLine.add(sl2_1);
		map.put("s2", sl2_1);
		
		//ConveyorBelt Two
		double yPosL2 = sl2_1.nextMachineStartPoint().getY() + (ConveyorBeltDimension.WIDTH / 2);
		PointXY cbl2_1Pos = new PointXY(sl2_1.nextMachineStartPoint().getX(), yPosL2 + 30);
		ConveyorBelt conveyor2 = new ConveyorBelt(cbl2_1Pos, 8, 0); //size 8, 0 angle
		productionLine.add(conveyor2);
		map.put("cb2", conveyor2);
		
		//ConveyorBelt Three
		PointXY cbl2_2Pos = new PointXY(sl2_1.nextMachineStartPoint().getX(), yPosL2 - 30);
		ConveyorBelt conveyor3 = new ConveyorBelt(cbl2_2Pos, 10, 25); //size 10, 25 angle
		productionLine.add(conveyor3);
		map.put("cb3", conveyor3);
	}
}
