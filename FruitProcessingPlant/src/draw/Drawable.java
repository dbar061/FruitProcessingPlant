package draw;

import factory.dimension.PointXY;
import draw.server.DrawCommandList;

/**
 * Drawable.java
 * @author:			Devin Barry
 * @date:			12.10.2012
 * @lastModified:	30.12.2012
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
	public void draw(DrawCommandList dcl, PointXY location);
}
