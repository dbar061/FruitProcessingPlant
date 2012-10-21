package factory.dimension;

/**
 * HoldingBayDimension.java
 * 
 * @author:			Devin Barry
 * @date:			13.10.2012
 * @lastModified: 	22.10.2012
 * 
 *
 */
public class HoldingBayDimension extends BasicDimension {
	
	public static final double LENGTH = 100; //scaled pixels
	public static final double WIDTH = 100; //scaled pixels
	public static final double RADIUS = 50; //radius is half the length
	
	private int size; //the number of buffer spaces in this HoldingBay
	
	/**
	 * This is the preferred way to instantiate this class.
	 * HoldingBays can have a variable number of buffer spaces
	 * 
	 * @param start
	 * @param slots
	 */
	public HoldingBayDimension(PointXY start, int size) {
		super(start, RADIUS);
		this.size = size;
	}
	
	/**
	 * Gets the length of the HoldingBay
	 * @return the length
	 */
	public double getLength() {
		return LENGTH;
	}
	
	/**
	 * In the StdDraw library the radius is half the width or length of the square,
	 * circle or diamond
	 * @return double the radius of the shape which represents this Machine
	 */
	@Override
	public double getDrawRadius() {
		return (LENGTH / 2);
	}
	
	@Override
	public String toString() {
		return new String("HoldingBay startPoint " + super.getStartPoint() + "\nHoldingBay size " + size + "\n"); 
	}

}
