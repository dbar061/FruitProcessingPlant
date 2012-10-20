package plant;

import dimension.PointXY;
import draw.Drawable;
import fruit.Fruit;

public interface Machinery extends Drawable {
	
	public void addFruit(Fruit fruit);
	
	public Fruit removeFruit();
	
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
