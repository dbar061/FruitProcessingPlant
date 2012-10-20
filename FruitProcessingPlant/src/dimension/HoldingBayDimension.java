package dimension;

/**
 * ConveyorBeltDimension.java
 * 
 * @author:			Devin Barry
 * @date:			13.10.2012
 * @lastModified: 	13.10.2012
 * 
 *
 */
public class HoldingBayDimension implements FactoryDimension {
	
	public static final double LENGTH = 100; //scaled pixels
	public static final double WIDTH = 100; //scaled pixels
	
	private PointXY startPoint; //the physical start point
	private int size; //the number of buffer spaces in this HoldingBay
	
	/**
	 * This is the preferred way to instantiate this class
	 * 
	 * @param start
	 * @param slots
	 */
	public HoldingBayDimension(PointXY start, int size) {
		this.startPoint = new PointXY(start);
		this.size = size;
	}
	
	/**
	 * Sets the location at which this item is positioned
	 * @param p - the point where this item is located in the factory
	 */
	public void setStartPoint(PointXY p) {
		this.startPoint.setLocation(p);
	}
	
	/**
	 * Gets the location at which this item is positioned
	 * @return the point where this item is located in the factory
	 */
	public PointXY getStartPoint() {
		//we don't want our start point modified by external parties
		return new PointXY(this.startPoint);
	}
	
	/**
	 * Gets the length of the conveyor belt
	 * @return the length
	 */
	public double getLength() {
		return LENGTH;
	}
	
	/**
	 * Gets the location at which to draw the item on the factory floor
	 * @return the point at which this item should be drawn
	 */
	public PointXY getDrawPoint() {
		//the object always draws with its position centered on the x,y
		//we want to draw the object based upon its start and end points
		double x = startPoint.getX() + getRadius();
		double y = startPoint.getY() + getRadius();
		PointXY drawPoint = new PointXY(x ,y);
		return drawPoint;
		
	}
	
	public PointXY getDrawPoint(PointXY base) {
		PointXY drawPoint = getDrawPoint();
		drawPoint.add(base); //add the base co-ordinates on
		return drawPoint;
	}
	
	/**
	 * In the StdDraw library the radius is half the width or length of the square
	 * @return
	 */
	public double getRadius() {
		return (LENGTH / 2);
	}
	
	@Override
	public String toString() {
		return new String("HoldingBay startPoint" + startPoint + "\nHoldingBay size" + size); 
	}

}
