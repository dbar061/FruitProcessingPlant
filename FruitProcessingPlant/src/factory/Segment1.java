package factory;

import inventory.fruit.Apple;
import inventory.fruit.Banana;
import inventory.fruit.Pear;

import java.util.*;

import factory.machine.BufferMachine;
import factory.machine.Machine;
import factory.plant.*;
import factory.dimension.ConveyorBeltDimension;
import factory.dimension.HoldingBayDimension;
import factory.dimension.PointXY;
import factory.dimension.SorterDimension;

import draw.StdDraw;

/**
 * Factory.java
 * 
 * @author:			Devin Barry
 * @date:			01.10.2012
 * @lastModified: 	23.10.2012
 *
 * This class draws conveyors and can be manipulated by SystemJ to
 * animate the conveyors.
 * 
 * Some new test methods have been added recently 22.10.2012
 */

/*************************************************************************
 * Dependecies: StdDraw.java
 * 
 *************************************************************************/

public class Segment1 {
	
	public static final int WINDOW_LENGTH = 600;
	public static final int WINDOW_HEIGHT = 1200;
	public static final double SCALE = 1.0; //how the factory dimensions relate to the window dimensions
	
	private static final double FACTORY_LENGTH = WINDOW_LENGTH * SCALE;
	private static final double FACTORY_HEIGHT = WINDOW_HEIGHT * SCALE; 
	
	public static List<Machine> productionLine = new ArrayList<Machine>(20); //initial capacity of 20
	public static Map<String,Machine> map = new HashMap<String,Machine>(20);
	
	public static final PointXY base = new PointXY(0, 0);
	
	public Segment1() {
		StdDraw.setCanvasSize(WINDOW_HEIGHT, WINDOW_LENGTH); //pixel size of window
		StdDraw.setXscale(0, FACTORY_HEIGHT);
		StdDraw.setYscale(0, FACTORY_LENGTH);
		StdDraw.show(0);
		this.createSegment1();
	}
	
	public void paint() {
		paint(30);
	}
	
	/**
	 * This should probably be made to be thread safe
	 * TODO
	 * @param delay
	 */
	public void paint(int delay) {
		// clear the background
		//StdDraw.clear(StdDraw.GRAY);
		StdDraw.clear();
		
		//Draw all machines in the factory
		Iterator<Machine> i = productionLine.iterator();
		while (i.hasNext()) {
			//i.next().draw(base);
		}
		
		//Show everything that has been drawn
		StdDraw.show(delay);
	}

	/**
	 * a test client for the factory
	 */
	public static void main(String[] args) {
		final Segment1 c = new Segment1();
		//c.createSegment1();
		c.addTestFruits();
		c.paint();
	}
	
	/**
	 * Create the components in their correct locations
	 */
	private void createSegment1() {
		//Central sorter divides between two lines
		PointXY s8_Pos = new PointXY(50,310);
		Sorter s8 = new Sorter(s8_Pos);
		productionLine.add(s8);
		map.put("s8", s8);
		
		
		//Bottom Row
		double conveyorLine1Y = 280;
		
		PointXY cbl1_4Pos = new PointXY(100,conveyorLine1Y);
		ConveyorBelt cbl1_4 = new ConveyorBelt(cbl1_4Pos, 12, 30); //size 8, 0 angle
		productionLine.add(cbl1_4);
		map.put("cb18", cbl1_4);
		
		double hbl1_1_y = cbl1_4.nextMachineStartPoint().getY() + HoldingBayDimension.RADIUS; // + ConveyorBeltDimension.WIDTH;
		PointXY hbl1_1Pos = new PointXY(cbl1_4.nextMachineStartPoint().getX(), hbl1_1_y);
		HoldingBay hbl1_1 = new HoldingBay(hbl1_1Pos);
		productionLine.add(hbl1_1);
		map.put("hb6", hbl1_1);
		
		PointXY sl1_1Pos = new PointXY(hbl1_1.nextMachineStartPoint().getX(), hbl1_1.nextMachineStartPoint().getY() + SorterDimension.RADIUS);
		Sorter sl1_1 = new Sorter(sl1_1Pos);
		productionLine.add(sl1_1);
		map.put("s5", sl1_1);
		
		double yPosL1 = sl1_1.nextMachineStartPoint().getY() + (ConveyorBeltDimension.WIDTH / 2);
		PointXY cbl1_1Pos = new PointXY(sl1_1.nextMachineStartPoint().getX() - 10, yPosL1 + 30);
		ConveyorBelt cbl1_1 = new ConveyorBelt(cbl1_1Pos, 10, -25); //size 8, -30 angle
		productionLine.add(cbl1_1);
		map.put("cb16", cbl1_1);
		
		PointXY cbl1_2Pos = new PointXY(sl1_1.nextMachineStartPoint().getX(), yPosL1 - 30);
		ConveyorBelt cbl1_2 = new ConveyorBelt(cbl1_2Pos, 8, 0); //size 8, 15 degree angle
		productionLine.add(cbl1_2);
		map.put("cb15", cbl1_2);
		
		
		//Top Row
		double conveyorLine2Y = 320;
		
		PointXY cbl2_4Pos = new PointXY(85,conveyorLine2Y);
		ConveyorBelt cbl2_4 = new ConveyorBelt(cbl2_4Pos, 12, -30); //size 8, 0 angle
		productionLine.add(cbl2_4);
		map.put("cb19", cbl2_4);
		
		double hbl2_1_y = cbl2_4.nextMachineStartPoint().getY() + HoldingBayDimension.RADIUS - ConveyorBeltDimension.WIDTH;
		PointXY hbl2_1Pos = new PointXY(cbl2_4.nextMachineStartPoint().getX(), hbl2_1_y);
		HoldingBay hbl2_1 = new HoldingBay(hbl2_1Pos);
		productionLine.add(hbl2_1);
		map.put("hb2", hbl2_1);
		
		
		PointXY sl2_1Pos = new PointXY(hbl2_1.nextMachineStartPoint().getX(), hbl2_1.nextMachineStartPoint().getY() + SorterDimension.RADIUS);
		Sorter sl2_1 = new Sorter(sl2_1Pos);
		productionLine.add(sl2_1);
		map.put("s2", sl2_1);
		
		double yPosL2 = sl2_1.nextMachineStartPoint().getY() + (ConveyorBeltDimension.WIDTH / 2);
		PointXY cbl2_1Pos = new PointXY(sl2_1.nextMachineStartPoint().getX(), yPosL2 + 30);
		ConveyorBelt cbl2_1 = new ConveyorBelt(cbl2_1Pos, 8, 0); //size 8, 0 angle
		productionLine.add(cbl2_1);
		map.put("cb2", cbl2_1);
		
		PointXY cbl2_2Pos = new PointXY(sl2_1.nextMachineStartPoint().getX(), yPosL2 - 30);
		ConveyorBelt cbl2_2 = new ConveyorBelt(cbl2_2Pos, 10, 25); //size 8, 30 angle
		productionLine.add(cbl2_2);
		map.put("cb3", cbl2_2);
	}
	
	/**
	 * Add various test fruits to the machines for testing
	 */
	private void addTestFruits() {
		int maxCount;
		double random;
		Machine m;
		BufferMachine bm;
		Iterator<Machine> i = productionLine.iterator();
		while (i.hasNext()) {
			m = i.next();
			if (m instanceof BufferMachine) bm = (BufferMachine)m;
			else continue;
			
			if (m instanceof HoldingBay) maxCount = 49;
			else if (m instanceof ConveyorBelt) maxCount = 6;
			else maxCount = 1;
			
			for (int j = 0; j < maxCount; j++) {
				random = Math.random();
				if (random <= 0.333) bm.addInventory(new Apple());
				else if (random <= 0.666) bm.addInventory(new Banana());
				else bm.addInventory(new Pear());
			}
		}
	}
	
	/**
	 * Tests advancing of conveyors
	 */
	private void advanceConveyors() {
		Machine m;
		BufferMachine bm;
		Iterator<Machine> i = productionLine.iterator();
		while (i.hasNext()) {
			m = i.next();
			if (m instanceof BufferMachine) bm = (BufferMachine)m;
			else continue;
			
			if (bm instanceof ConveyorBelt) {
				bm.AdvanceBuffer();
			}
		}
	}

}
