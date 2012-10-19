package draw;

import dimension.PointXY;

/**
 * Drawable.java
 * @author:			Devin Barry
 * @date:			12.10.2012
 * @lastModified:	13.10.2012
 * 
 * This interface is implemented by all objects that can be drawn
 * in the FruitProcessingPlant
 */
public interface Drawable {
	
	/**
	 * This method causes the current object to draw itself
	 * at the location specified by <location>
	 * @param location
	 */
	public void draw(PointXY location);
}
