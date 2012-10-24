package factory.dimension;

/**
 * SorterDimension.java
 * 
 * @author:			Devin Barry
 * @date:			13.10.2012
 * @lastModified: 	22.10.2012
 * 
 * Contains dimensions for the Sorter machine
 */
public class SorterDimension extends BasicDimension {
	
	public static final double RADIUS = 20; //radius of this shape in scaled pixels
	
	/**
	 * SorterDimension is instantiated with a start point only
	 * The size of a sorter is hard-wired into this class.
	 * @param start
	 */
	public SorterDimension(PointXY start) {
		super(start, RADIUS);
	}
	
	/**
	 * In the StdDraw library the radius is half the width or length of the square,
	 * circle or diamond
	 * @return double the radius of the shape which represents this Machine
	 */
	@Override
	public double getDrawRadius() {
		return RADIUS;
	}
	
	@Override
	public String toString() {
		return new String("Sorter startPoint " + super.getStartPoint() + "\nSorter radius " + RADIUS + "\n"); 
	}

}
