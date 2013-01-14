package factory.dimension;

import factory.dimension.PointXY;

/**
 * BasicDimension.java
 * 
 * @author:			Devin Barry
 * @date:			13.10.2012
 * @lastModified: 	15.01.2013
 * 
 * Generic dimension class for any basic machine. This class
 * refers to machine/shape because at this level of abstraction
 * we are only really considering the drawing of the machines
 * and hence all machines are actually simple shapes.
 * 
 * All the dimension calculations supported by this class are in
 * essence methods for determining graphical parameters of the
 * shapes that we are drawing, so that the entire production line
 * can be semi-automatically assembled.
 */
public class BasicDimension implements FactoryDimension {
	
	//The start point is the top left corner of a shape/machine
	//The end point is the top right corner of a shape/machine
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
	 * Gets the end position of this item, but factors in the
	 * machine spacing and angle of the current machine to choose
	 * an optimal starting position for the next machine on the
	 * production line.
	 * @return the point where this item ends in the factory
	 */
	public PointXY nextMachineStartPoint() {
		//assumes machines are lined up along the x coordinate
		double x = endPoint.getX();
		double y = endPoint.getY();
		return new PointXY(x + Locatable.SPACING, y - radius);
	}
	
	/**
	 * Basic machines/shapes such as circles, squares and diamonds should
	 * be thought of as only taking up space in the x dimension. This is
	 * because the machines are lined up along the x dimension. The
	 * beginning of the machine/shape (usually the left most x-coordinate)
	 * should be nearly touching the last machines end (usually the right
	 * most x-coordinate). Machines almost never line up using their y
	 * position. The y-coordinate of a shape is just to determine what
	 * "row" of machines it is in.
	 * 
	 * Therefore the "end point" of a machine has an identical y-coordinate
	 * as its start point and the x-coordinate is the start x plus twice
	 * the radius of the shape. This how far it extends in the x-dimension.
	 * 
	 * This "end point" is used when lining new machines/shapes up with
	 * others already on the floor. In this circumstance we want to put the
	 * next machine in the same "row" (i.e. identical y-coordinate), but we
	 * want it to start where the last machine ends. 
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
		//the object always draws with its position centred on the x,y
		//we want to draw the object based upon its start and end points
		double x = startPoint.getX() + getDrawRadius();
		double y = startPoint.getY() - getDrawRadius();
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
		drawPoint.add(base); //add the base coordinates on
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
