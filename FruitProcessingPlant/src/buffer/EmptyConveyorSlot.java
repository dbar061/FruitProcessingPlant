package buffer;
 
import draw.StdDraw;
import buffer.BufferSlot;
import factory.dimension.PointXY;
import fruit.ProductionLineItem;


/**
 * Fruit.java
 * 
 * @author:			Devin Barry
 * @date:			24.10.2012
 * @lastModified: 	24.10.2012
 * 
 * This class represents an empty space in the conveyor
 */
public class EmptyConveyorSlot implements ProductionLineItem, BufferSlot {
	
	private static final int SIZE = 10; //scaled pixels
	
	public EmptyConveyorSlot() {
	}
	
	public void getBuffer() {
		System.out.println("No known buffer");
	}
	
	public boolean isEmpty() {
		return true; //this slot is not actually an item, it is an empty space
	}
	
	public void draw(PointXY location) {
		//Draw an empty slot
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.filledCircle(location.getX(), location.getY(), SIZE);
	}
	
	@Override
	public String toString() {
		return new String("Empty slot on a conveyor"); 
	}
}