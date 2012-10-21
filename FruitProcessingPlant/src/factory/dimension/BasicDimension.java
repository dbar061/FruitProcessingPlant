package factory.dimension;

import factory.dimension.PointXY;

/**
 * BasicDimension.java
 * 
 * @author:			Devin Barry
 * @date:			13.10.2012
 * @lastModified: 	22.10.2012
 * 
 * Generic dimension class for any basic machine
 */
public class BasicDimension implements FactoryDimension {
	
	private PointXY startPoint, endPoint; //the physical start and end points
	private double radius = 20; //radius of this shape in scaled pixels
	
	/**
	 * BasicDimension is instantiated with a start point only
	 * @param start
	 */
	public BasicDimension(PointXY start, double radius) {
		this.startPoint = new PointXY(start);
		this.radius = radius;
		this.calculateEndPoint();
	}
	
	/**
	 * Sets the location at which this item is positioned
	 * @param p - the point where this item is located in the factory
	 */
	public void setStartPoint(PointXY p) {
		this.startPoint.setLocation(p);
		this.calculateEndPoint();
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
	 * Gets the end position of this item. This end position is
	 * dependent on the size of the item. If the item is a machine
	 * on the factory floor, then this method is used for
	 * positioning other items that follow on in the production
	 * line
	 * @return the point where this item ends in the factory
	 */
	public PointXY getEndPoint() {
		//we don't want our end point modified by external parties
		return new PointXY(this.endPoint);
	}
	
	/**
	 * Basic shapes such as circles are
	 * only considered to take space in the x
	 * dimension. This is because new items will
	 * be lined up in the x dimension. Thus
	 * twice the radius of the shape is how far
	 * it extends in the x dimension.
	 */
	private void calculateEndPoint() {
		double x = startPoint.getX();
		double y = startPoint.getY();
		endPoint = new PointXY(x + (2 * radius), y);
	}
	
	/**
	 * Gets the location at which to draw the item on the factory floor
	 * @return the point at which this item should be drawn
	 */
	public PointXY getDrawPoint() {
		//the object always draws with its position centered on the x,y
		//we want to draw the object based upon its start and end points
		double x = startPoint.getX() + getDrawRadius();
		double y = startPoint.getY() + getDrawRadius();
		PointXY drawPoint = new PointXY(x ,y);
		return drawPoint;
		
	}
	
	/**
	 * Gets the location at which to draw the item on the factory floor
	 * @param base
	 * @return
	 */
	public PointXY getDrawPoint(PointXY base) {
		PointXY drawPoint = getDrawPoint();
		drawPoint.add(base); //add the base co-ordinates on
		return drawPoint;
	}
	
	/**
	 * In the StdDraw library the radius is half the width or length of the square,
	 * circle or diamond
	 * @return double the radius of the shape which represents this Machine
	 */
	public double getDrawRadius() {
		return radius;
	}
	
	@Override
	public String toString() {
		return new String("BasicDimension startPoint " + startPoint + "\nBasicDimension radius " + radius + "\n"); 
	}

}
