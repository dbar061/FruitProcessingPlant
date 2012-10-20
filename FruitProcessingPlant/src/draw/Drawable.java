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
	
	public void draw(PointXY location);
}
