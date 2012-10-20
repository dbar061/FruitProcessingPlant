package factory;

import java.util.*;
import plant.*;
import fruit.Apple;
import fruit.Banana;
import fruit.Pear;
import dimension.PointXY;

import draw.StdDraw;

/**
 * Factory.java
 * 
 * @author:			Devin Barry
 * @date:			01.10.2012
 * @lastModified: 	14.10.2012
 *
 * This class draws conveyors and can be manipulated by SystemJ to
 * animate the conveyors.
 */

/*************************************************************************
 * Dependecies: StdDraw.java
 * 
 *************************************************************************/

public class Factory {
	
	public static final int WINDOW_LENGTH = 600;
	public static final int WINDOW_HEIGHT = 800;
	public static final double SCALE = 1.5; //how the factory dimensions relate to the window dimensions
	
	private static final double FACTORY_LENGTH = WINDOW_LENGTH * SCALE;
	private static final double FACTORY_HEIGHT = WINDOW_HEIGHT * SCALE; 
	
	
	List<Machine> productionLine = new ArrayList<Machine>(20); //initial capacity of 20
	static final PointXY base = new PointXY(0, 0);
	
	public Factory() {
		StdDraw.setCanvasSize(800, 600); //pixel size of window
		StdDraw.setXscale(0, FACTORY_HEIGHT);
		StdDraw.setYscale(0, FACTORY_LENGTH);
		StdDraw.show(0);
		
		double conveyorLine1Y = 100;
		PointXY hbl1_1Pos = new PointXY(100,conveyorLine1Y);
		HoldingBay hbl1_1 = new HoldingBay(hbl1_1Pos);
		productionLine.add(hbl1_1);
		
		PointXY cbl1_1Pos = new PointXY((hbl1_1.getStartPoint().getX() + hbl1_1.getLength() + 10),conveyorLine1Y);
		ConveyorBelt cbl1_1 = new ConveyorBelt(cbl1_1Pos, 0);
		PointXY cbl1_2Pos = new PointXY((cbl1_1.getStartPoint().getX() + cbl1_1.getLength() + 10), conveyorLine1Y);
		ConveyorBelt cbl1_2 = new ConveyorBelt(cbl1_2Pos, 0);
		PointXY cbl1_3Pos = new PointXY((cbl1_2.getStartPoint().getX() + cbl1_2.getLength() + 10), conveyorLine1Y);
		ConveyorBelt cbl1_3 = new ConveyorBelt(cbl1_3Pos, 0);
		productionLine.add(cbl1_1);
		productionLine.add(cbl1_2);
		productionLine.add(cbl1_3);
		
		double conveyorLine2Y = 300;
		PointXY cbl2_1Pos = new PointXY(100,conveyorLine2Y);
		ConveyorBelt cbl2_1 = new ConveyorBelt(cbl2_1Pos, 0);
		PointXY cbl2_2Pos = new PointXY((cbl2_1.getStartPoint().getX() + cbl2_1.getLength() + 10), conveyorLine2Y);
		ConveyorBelt cbl2_2 = new ConveyorBelt(cbl2_2Pos, 0);
		PointXY cbl2_3Pos = new PointXY((cbl2_2.getStartPoint().getX() + cbl2_2.getLength() + 10), conveyorLine2Y);
		ConveyorBelt cbl2_3 = new ConveyorBelt(cbl2_3Pos, 0);
		productionLine.add(cbl2_1);
		productionLine.add(cbl2_2);
		productionLine.add(cbl2_3);
		
		ExtendedPlatform e1 = new ExtendedPlatform(new PointXY(600, 500));
		productionLine.add(e1);
		ConveyorBelt test = new ConveyorBelt(new PointXY(600, 500), 5);
		productionLine.add(test);
		Sorter s1 = new Sorter(new PointXY(600, 500));
		productionLine.add(s1);
	}
	
	/**
	 * Add various test fruits to the machines for testing
	 */
	public void addTestFruits() {
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
			else maxCount = 3;
			
			for (int j = 0; j < maxCount; j++) {
				random = Math.random();
				if (random <= 0.333) bm.addFruit(new Apple());
				else if (random <= 0.666) bm.addFruit(new Banana());
				else bm.addFruit(new Pear());
			}
		}
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
			i.next().draw(base);
		}
		
		//Show everything that has been drawn
		StdDraw.show(delay);
	}

	// a test client
	public static void main(String[] args) {
		final Factory c = new Factory();
		c.addTestFruits();
		c.paint();

	}

}
