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
public class ConveyorBeltDimension implements FactoryDimension {
	
	//ConveyorBelts always have a fixed size slot length
	//and have fixed width
	public static final double SLOT_LENGTH = 30; //scaled pixels
	public static final double WIDTH = 40; //scaled pixels
	
	private double length; //scaled pixels
	private double angle; //degrees
	
	private PointXY startPoint, endPoint; //the physical start and end points
	private int slots; //the number of slots in this conveyorBelt
	
	/**
	 * This is the preferred way to instantiate this class
	 * 
	 * @param start
	 * @param slots
	 */
	public ConveyorBeltDimension(PointXY start, double angle, int slots) {
		this.startPoint = new PointXY(start);
		this.angle = angle;
		this.slots = slots;
		if (slots < 0) throw new RuntimeException("Cannot have negative slots!\n");
		calculateEndPoint();
		calculateLength();
	}
	
	/**
	 * This constructor is here if it is needed, but it is not preferred
	 * 
	 * @param start
	 * @param end
	 */
	public ConveyorBeltDimension(PointXY start, PointXY end) {
		this.startPoint = new PointXY(start);
		this.endPoint = new PointXY(end);
		calculateAngle();
		calculateLength();
		calculateSlots();
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
	 * Gets the centre point of the first slot of the conveyor
	 * This is the location where we will draw the first item
	 * in the conveyor
	 * @return the point where this item is located in the factory
	 */
	public PointXY getFirstSlotDrawPoint() {
		//Assume all conveyors are horizontal
		double x = startPoint.getX();
		double y = startPoint.getY();
		x += (SLOT_LENGTH / 2);
		y += (WIDTH / 2);
		return new PointXY(x, y);
	}
	
	/**
	 * Gets the length of the conveyor belt
	 * @return the length
	 */
	public double getLength() {
		return length;
	}
	
	/**
	 * Gets the angle of the conveyor belt relative to horizontal
	 * @return the angle
	 */
	public double getAngle() {
		return angle;
	}
	
	private void calculateLength() {
		//length is always positive
		length = Math.abs(startPoint.distance(endPoint));
	}
	
	/**
	 * This assumes that we know the length already
	 * TODO Some checking here could possibly generate
	 * a better number of slots than floor
	 */
	private void calculateSlots() {
		slots = (int)Math.floor(length / SLOT_LENGTH);
	}
	
	/**
	 * This method assumes conveyor belts are always
	 * horizontal
	 */
	private void calculateEndPoint() {
		double x = startPoint.getX();
		double y = startPoint.getY();
		x += (slots * SLOT_LENGTH); //conveyor is horizontal
		endPoint = new PointXY(x, y);
	}
	
	/**
	 * Fetches angle relative to screen centre point
	 * where 3 O'Clock is 0 and 12 O'Clock is 270 degrees
	 * 
	 * @param screenPoint
	 * @return angle in degress from 0-360.
	 */
	private void calculateAngle() {
		double dx = endPoint.getX() - startPoint.getX();
		double dy = endPoint.getY() - startPoint.getY();
		
		double inRads = Math.atan2(dy,dx);
		// We need to map to coord system when 0 degree is at 3 O'clock, 270 at 12 O'clock
		if (inRads < 0) {
			inRads = Math.abs(inRads);
		}
		else {
			inRads = 2*Math.PI - inRads;
		}
		angle = Math.toDegrees(inRads);
	}
	
	/**
	 * Gets the location at which to draw the item on the factory floor
	 * @return the point at which this item should be drawn
	 */
	public PointXY getDrawPoint() {
		//the object always draws with its position centered on the x,y
		//we want to draw the object based upon its start and end points
		double x = startPoint.getX() + getRectHalfLength();
		double y = startPoint.getY() + getRectHalfWidth();
		PointXY drawPoint = new PointXY(x ,y);
		return drawPoint;
		
	}
	
	public PointXY getDrawPoint(PointXY base) {
		PointXY drawPoint = getDrawPoint();
		drawPoint.add(base); //add the base co-ordinates on
		return drawPoint;
	}
	
	/**
	 * Gets the half length of the rectangle dimension
	 * for the conveyor belt
	 * @return
	 */
	public double getRectHalfLength() {
		return (length / 2);
	}
	
	/**
	 * Gets the half width of the rectangle dimension
	 * for the conveyor belt
	 * @return
	 */
	public double getRectHalfWidth() {
		return (WIDTH / 2);
	}
	
	/**
	 * In the StdDraw library width is left-right
	 * @return
	 */
	public double getDrawHalfWidth() {
		return getRectHalfLength(); //this assumes horizontal conveyors
	}
	
	/**
	 * In the StdDraw library height is up-down
	 * @return
	 */
	public double getDrawHalfHeight() {
		return getRectHalfWidth(); //this assumes horizontal conveyors
	}
	
	@Override
	public String toString() {
		return new String("ConveyorBelt startPoint" + startPoint + "\nConveyorBelt endPoint" + endPoint); 
	}

}
