package dimension;

/**
 * BasicDimension.java
 * 
 * @author:			Devin Barry
 * @date:			13.10.2012
 * @lastModified: 	13.10.2012
 * 
 * Generic dimension class for any basic machine
 */
public class BasicDimension implements FactoryDimension {
	
	public static final double RADIUS = 20; //scaled pixels
	
	private PointXY startPoint; //the physical start point
	
	/**
	 * BasicDimension is instantiated with a start point only
	 * @param start
	 */
	public BasicDimension(PointXY start) {
		this.startPoint = new PointXY(start);
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
		return RADIUS;
	}
	
	@Override
	public String toString() {
		return new String("BasicDimension startPoint " + startPoint + "\nBasicDimension radius " + RADIUS + "\n"); 
	}

}
