package plant;

import buffer.FruitBuffer;
import buffer.ProductionBuffer;
import dimension.PointXY;
import dimension.SorterDimension;
import draw.StdDraw;
import fruit.Fruit;


/**
 * Sorter.java
 * @author:			Devin Barry
 * @date:			13.10.2012
 * @lastModified:	13.10.2012
 *
 * The sorter is too simple to have its own dimension support class
 * It only has a radius and a start point
 */
public class Sorter implements Machine {
	
	SorterDimension sd;
	private FruitBuffer fb;
	public static final int NUM_SLOTS = 1;
	
	public Sorter(PointXY start) {
		sd = new SorterDimension(start);
		fb = new FruitBuffer(NUM_SLOTS);
	}
	
	/**
	 * get the length of the Machine
	 * @return machine length
	 */
	public double getLength() {
		return SorterDimension.RADIUS;
	}
	
	public void addFruit(Fruit fruit) {
		fb.add(fruit);
	}
	
	public Fruit removeFruit() {
		return fb.removeFruit();
	}
	
	
	/**
	 * get the width of the Machine
	 * @return machine width
	 */
	public double getWidth() {
		return SorterDimension.RADIUS;
	}
	
	/**
	 * Gets the location at which this item is positioned
	 * @return the point where this item is located in the factory
	 */
	public PointXY getStartPoint() {
		return sd.getStartPoint();
	}
	
	public ProductionBuffer getProductionBuffer() {
		return fb;
	}
	
	/**
	 * This method causes the current object to draw itself
	 * at the location specified by <location>
	 * @param location
	 */
	public void draw(PointXY location) {
		PointXY drawPoint = sd.getDrawPoint(location);
		
		//Draw the Sorter
		StdDraw.setPenColor(StdDraw.CYAN);
		StdDraw.filledCircle(drawPoint, sd.getDrawRadius());
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.circle(drawPoint, sd.getDrawRadius());
	}

}
