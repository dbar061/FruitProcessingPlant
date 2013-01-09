package draw;

import factory.dimension.PointXY;
import draw.server.DrawCommandList;

/**
 * Drawable.java
 * @author:			Devin Barry
 * @date:			12.10.2012
 * @lastModified:	08.01.2013
 * 
 * This interface is implemented by all objects that can be drawn
 * in the FruitProcessingPlant
 */
public interface Drawable {
	
	/**
	 * This method causes the current object to draw itself,
	 * offset from the the co-ordinates specified by <location>.
	 * The current object draws itself to the DrawCommandList
	 * <dcl> which may be sent over the network to the draw
	 * server.
	 * 
	 * @param location
	 */
	public void draw(DrawCommandList dcl, PointXY location);
}
