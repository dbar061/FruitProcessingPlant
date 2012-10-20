package plant;

import buffer.FruitBuffer;
import buffer.ProductionBuffer;
import dimension.PointXY;
import dimension.ExtendedPlatformDimension;
import draw.StdDraw;
import fruit.Fruit;


/**
 * ExtendedPlatform.java
 * @author:			Devin Barry
 * @date:			13.10.2012
 * @lastModified:	13.10.2012
 *
 * The sorter is too simple to have its own dimension support class
 * It only has a radius and a start point
 */
public class ExtendedPlatform implements Machine {
	
	public static final int NUM_SLOTS = 1;
	ExtendedPlatformDimension epd;
	private FruitBuffer fb;
	public ExtendedPlatform(PointXY start) {
		epd = new ExtendedPlatformDimension(start);
		fb = new FruitBuffer(NUM_SLOTS);
	}
	
	public void addFruit(Fruit fruit) {
		fb.add(fruit);
	}
	
	public Fruit removeFruit() {
		return fb.removeFruit();
	}
	
	
	/**
	 * get the length of the Machine
	 * @return machine length
	 */
	public double getLength() {
		return ExtendedPlatformDimension.RADIUS;
	}
	
	/**
	 * get the width of the Machine
	 * @return machine width
	 */
	public double getWidth() {
		return ExtendedPlatformDimension.RADIUS;
	}
	
	/**
	 * Gets the location at which this item is positioned
	 * @return the point where this item is located in the factory
	 */
	public PointXY getStartPoint() {
		return epd.getStartPoint();
	}
	
	/**
	 * This method causes the current object to draw itself
	 * at the location specified by <location>
	 * @param location
	 */
	public void draw(PointXY location) {
		PointXY drawPoint = epd.getDrawPoint(location);
		
		//Draw the ExtendedPlatform
		StdDraw.setPenColor(StdDraw.BOOK_BLUE);
		StdDraw.filledDiamond(drawPoint, epd.getDrawRadius());
	}
	
	public ProductionBuffer getProductionBuffer() {
		return fb;
	}

}
