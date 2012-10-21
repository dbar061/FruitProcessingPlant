package factory.plant;

import draw.Drawable;
import factory.dimension.Locatable;

/**
 * Machine.java
 * @author:			Devin Barry
 * @date:			12.10.2012
 * @lastModified:	22.10.2012
 *
 * Machine interface is meant to be implemented by all the machines
 * in the production line.
 * 
 * Machine extends the Drawable interface and as such all Machines are
 * can be drawn on the factory floor.
 * 
 * Machine extends the Locatable interface and as such all Machines
 * have a start point and an end point on the factory floor.
 */
public interface Machine extends Drawable, Locatable {
	
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

}
