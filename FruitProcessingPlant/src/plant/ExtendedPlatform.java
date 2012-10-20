package plant;

import dimension.PointXY;
import dimension.ExtendedPlatformDimension;
import draw.StdDraw;


/**
 * ExtendedPlatform.java
 * @author:			Devin Barry
 * @date:			13.10.2012
 * @lastModified:	13.10.2012
 *
 * The sorter is too simple to have its own dimension support class
 * It only has a radius and a start point
 */
public class ExtendedPlatform implements Machine {
	
	ExtendedPlatformDimension epd;
	
	public ExtendedPlatform(PointXY start) {
		epd = new ExtendedPlatformDimension(start);
	}
	
	/**
	 * get the length of the Machine
	 * @return machine length
	 */
	public double getLength() {
		return ExtendedPlatformDimension.RADIUS;
	}
	
	/**
	 * get the width of the Machine
	 * @return machine width
	 */
	public double getWidth() {
		return ExtendedPlatformDimension.RADIUS;
	}
	
	/**
	 * Gets the location at which this item is positioned
	 * @return the point where this item is located in the factory
	 */
	public PointXY getStartPoint() {
		return epd.getStartPoint();
	}
	
	/**
	 * This method causes the current object to draw itself
	 * at the location specified by <location>
	 * @param location
	 */
	public void draw(PointXY location) {
		PointXY drawPoint = epd.getDrawPoint(location);
		
		//Draw the ExtendedPlatform
		StdDraw.setPenColor(StdDraw.BOOK_BLUE);
		StdDraw.filledDiamond(drawPoint, epd.getDrawRadius());
	}

}
