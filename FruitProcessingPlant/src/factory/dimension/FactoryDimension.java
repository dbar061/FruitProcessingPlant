package factory.dimension;

import factory.dimension.PointXY;

/**
 * FactoryDimension.java
 * @author:			Devin Barry
 * @date:			12.10.2012
 * @lastModified: 	22.10.2012
 * 
 * This interface is used by all items that can be drawn
 * on the factory floor. This interface provides useful methods
 * for translating dimensions of objects in the factory.
 * 
 * This interface is intimately a part of all machines that
 * exist in the factory and thus it shares the Locatable
 * interface, meaning all FactoryDimensions have will possess
 * the methods getStartPoint and getEndPoint from Locatable.
 */
public interface FactoryDimension extends Locatable {
	
	/**
	 * Sets the location at which this item is positioned
	 * @param p - the point where this item is located in the factory
	 */
	public void setStartPoint(PointXY p);
	
	/**
	 * Gets the location at which to draw the item on the factory floor
	 * @return the point at which this item should be drawn
	 */
	public PointXY getDrawPoint();

}
