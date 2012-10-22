package factory.dimension;

/**
 * ConveyorBeltDimension.java
 * 
 * @author:			Devin Barry
 * @date:			13.10.2012
 * @lastModified: 	22.10.2012
 * 
 * This class should probably extend BasicDimension. Because it is a bit
 * complex it has not been made to extend this class yet, despite all
 * the overlapping functionality.
 */
public class ConveyorBeltDimension implements FactoryDimension {
	
	//ConveyorBelts always have a fixed size slot length
	//and have fixed width
	public static final double SLOT_LENGTH = 30; //scaled pixels
	public static final double WIDTH = 40; //scaled pixels
	
	private double length; //scaled pixels
	private double angle; //degrees
	
	//this is the start point. We have assumed it is the top left corner of a conveyor drawn at 0 degrees
	private PointXY startPoint;
	private PointXY endPoint; //the physical start and end points
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
	 * It doesnt need a slots argument because the number of slots is
	 * calculated from the start and end points.
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
	 * Gets the centre point of the first slot of the conveyor
	 * This is the location where we will draw the first item
	 * in the conveyor.
	 * 
	 * Devin has a bunch of hand drawn notes that describe how
	 * the calculations here are done. All the angles and variables
	 * referred to here match back to the hand drawn notes and
	 * symbols used by Devin there.
	 * 
	 * TODO this can be improved for speed slightly
	 * 
	 * @return the point where this item is located in the factory
	 */
	public PointXY getFirstSlotDrawPoint() {
		double x = startPoint.getX();
		double y = startPoint.getY();
		double a = (SLOT_LENGTH / 2);
		double b = (WIDTH / 2);
		double c = Math.sqrt((a*a) + (b*b)); //this also equals h
		//double theta2 = Math.atan(b / a); //radians
		double theta2 = Math.atan2(b, a); //gives radians, args are y, x
		double theta1 = Math.toRadians(angle);
		double dx = c * Math.cos(theta1 + theta2);
		double dy = c * Math.sin(theta1 + theta2);
		//System.out.println("x is: " + x);
		//System.out.println("y is: " + y);
		//System.out.println("a is: " + a);
		//System.out.println("b is: " + b);
		//System.out.println("c is: " + c);
		//System.out.println("theta1 is: " + theta1);
		//System.out.println("theta2 is: " + theta2);
		//System.out.println("dx is: " + dx);
		//System.out.println("dy is: " + dy);
		return new PointXY(x + dx, y - dy);
	}
	
	public PointXY getNextSlotDrawPoint(PointXY previous) {
		double x = previous.getX();
		double y = previous.getY();
		double hypotenuse = SLOT_LENGTH; //length of a single slot
		double dy = hypotenuse * Math.sin(Math.toRadians(angle));
		double dx = hypotenuse * Math.cos(Math.toRadians(angle));
		return new PointXY(x + dx, y - dy);
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
	 * Conveyor belts may be drawn at an angle. The end point must
	 * take this into account. The length of the conveyor can be
	 * thought of as the hypotenuse of a right angled triangle.
	 * This works even if the angle is 0
	 */
	private void calculateEndPoint() {
		double x = startPoint.getX();
		double y = startPoint.getY();
		double hypotenuse = (slots * SLOT_LENGTH); //length of conveyor
		double dy = hypotenuse * Math.sin(Math.toRadians(angle));
		double dx = hypotenuse * Math.cos(Math.toRadians(angle));
		endPoint = new PointXY(x + dx, y - dy);
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
		double y = startPoint.getY() - getRectHalfWidth();
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
