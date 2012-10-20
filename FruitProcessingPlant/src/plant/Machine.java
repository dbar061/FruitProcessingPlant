package plant;

import dimension.PointXY;
import draw.Drawable;

/**
 * Machine.java
 * @author:			Devin Barry
 * @date:			12.10.2012
 * @lastModified:	13.10.2012
 *
 * Machine interface is meant to be implemented by all the machines
 * in the production line.
 * 
 * Machine extends the Drawable interface and as such all Machines are
 * can be drawn on the factory floor.
 */
public interface Machine extends Drawable {
	
	/**
	 * get the length of the Machine
	 * @return machine length
	 */
	public double getLength();
	
	/**
	 * get the width of the Machine
	 * @return machine width
	 */
	public double getWidth();
	
	/**
	 * Gets the location at which this item is positioned
	 * @return the point where this item is located in the factory
	 */
	public PointXY getStartPoint();

}
