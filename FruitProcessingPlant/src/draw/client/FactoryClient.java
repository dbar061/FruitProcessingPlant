package draw.client;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import inventory.fruit.Apple;
import inventory.fruit.Banana;
import inventory.fruit.Pear;

import factory.machine.BufferMachine;
import factory.machine.Machine;
import factory.plant.*;
import factory.dimension.ConveyorBeltDimension;
import factory.dimension.PointXY;
import factory.dimension.SorterDimension;

import buffer.ProductionBuffer;

//import draw.server.DrawCommand;
import draw.server.DrawCommandList;
import network.ObjectSocketClient;

/**
 * FactoryClient.java
 * 
 * @author:			Devin Barry
 * @date:			29.12.2012
 * @lastModified: 	09.01.2013
 *
 * This class is based from code in factory.Factory.java
 * 
 * This class serves as a test client for the draw server
 */
public class FactoryClient {
	
	public static List<Machine> productionLine = new ArrayList<Machine>(20); //initial capacity of 20
	private static final PointXY base = new PointXY(0, 0);
	private DrawCommandList dcl;
	
	public FactoryClient() {
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
		
		//clearing the background is not actually required
		//the machines draw over all the previous content
		dcl.addCommand("clear");
		
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
	 * a test client for the factory
	 */
	public static void main(String[] args) {
		final FactoryClient c = new FactoryClient();
		c.testFactory();
		c.addTestFruits();
		c.paint();
		
		for (int i = 0; i < 5; i++) {
			//wait then advance conveyers and repaint
			try {
				Thread.sleep(3000);
			} catch (InterruptedException ie) {
				System.out.println(ie);
				System.out.println("Continuing...");
			}
			c.advanceConveyors();
			c.paint();
		}
		
	}
	
	/**
	 * Tests a mock factory setup
	 */
	private void testFactory() {
		double conveyorLine1Y = 150;
		PointXY hbl1_1Pos = new PointXY(100,conveyorLine1Y);
		HoldingBay hbl1_1 = new HoldingBay(hbl1_1Pos);
		productionLine.add(hbl1_1);
		
		PointXY sl1_1Pos = new PointXY(hbl1_1.nextMachineStartPoint().getX(), hbl1_1.nextMachineStartPoint().getY() + SorterDimension.RADIUS);
		Sorter sl1_1 = new Sorter(sl1_1Pos);
		productionLine.add(sl1_1);
		ProductionBuffer s1pb = sl1_1.getProductionBuffer();
		System.out.println("test " + s1pb.isFull());
		
		PointXY cbl1_1Pos = new PointXY(sl1_1.nextMachineStartPoint().getX(), sl1_1.nextMachineStartPoint().getY() + (ConveyorBeltDimension.WIDTH / 2));
		ConveyorBelt cbl1_1 = new ConveyorBelt(cbl1_1Pos, 6, 0); //size 8, 0 angle
		PointXY cbl1_2Pos = new PointXY(cbl1_1.nextMachineStartPoint());
		ConveyorBelt cbl1_2 = new ConveyorBelt(cbl1_2Pos, 12, 15); //size 8, 15 degree angle
		PointXY cbl1_3Pos = new PointXY(cbl1_2.nextMachineStartPoint());
		ConveyorBelt cbl1_3 = new ConveyorBelt(cbl1_3Pos, 8, 0); //size 8, 0 angle
		productionLine.add(cbl1_1);
		productionLine.add(cbl1_2);
		productionLine.add(cbl1_3);
		
		double conveyorLine2Y = 300;
		PointXY cbl2_1Pos = new PointXY(100,conveyorLine2Y);
		ConveyorBelt cbl2_1 = new ConveyorBelt(cbl2_1Pos, 8, 0); //size 8, 0 angle
		PointXY cbl2_2Pos = new PointXY(cbl2_1.nextMachineStartPoint());
		ConveyorBelt cbl2_2 = new ConveyorBelt(cbl2_2Pos, 8, -35); //size 8, 30 angle
		productionLine.add(cbl2_1);
		productionLine.add(cbl2_2);
		
		PointXY el2_1Pos = new PointXY(cbl2_2.nextMachineStartPoint());
		ExtendedPlatform el2_1 = new ExtendedPlatform(el2_1Pos);
		productionLine.add(el2_1);
		
		PointXY cbl2_3Pos = new PointXY(el2_1.nextMachineStartPoint());
		ConveyorBelt cbl2_3 = new ConveyorBelt(cbl2_3Pos, 8, 15); //size 8, 0 angle
		productionLine.add(cbl2_3);
		PointXY hbl2_1Pos = new PointXY(cbl2_3.nextMachineStartPoint());
		HoldingBay hbl2_1 = new HoldingBay(hbl2_1Pos);
		productionLine.add(hbl2_1);
		
		
		ConveyorBelt test1 = new ConveyorBelt(new PointXY(600, 500), 19, 160);
		productionLine.add(test1);
		ConveyorBelt test2 = new ConveyorBelt(new PointXY(600, 500), 3, 0);
		productionLine.add(test2);
		PointXY sl3_1Pos = new PointXY(test2.nextMachineStartPoint());
		Sorter sl3_1 = new Sorter(sl3_1Pos);
		productionLine.add(sl3_1);
		
		System.out.println("test " + s1pb.isFull());
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
	 * Tests advancing of conveyers
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
