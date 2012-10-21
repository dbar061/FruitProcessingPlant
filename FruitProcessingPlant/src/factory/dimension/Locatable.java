package factory.dimension;

import factory.dimension.PointXY;

/**
 * Locatable.java
 * @author:			Devin Barry
 * @date:			22.10.2012
 * @lastModified:	22.10.2012
 *
 * Locatable interface is an interface shared by several
 * items in several packages. Specifically it is used by
 * all machines that have a start and end point on the 
 * factory floor (which is all the machines).
 */
public interface Locatable {
	
	/**
	 * Gets the location at which this item is positioned
	 * @return the point where this item is located in the factory
	 */
	public PointXY getStartPoint();
	
	/**
	 * Gets the end position of this item. This end position is
	 * dependent on the size of the item. If the item is a machine
	 * on the factory floor, then this method is used for
	 * positioning other items that follow on in the production
	 * line
	 * @return the point where this item ends in the factory
	 */
	public PointXY getEndPoint();

}

