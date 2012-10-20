package plant;

import draw.StdDraw;
import buffer.DrawableFruitBuffer;
import buffer.ProductionBuffer;
import fruit.Fruit;
import fruit.Apple;
import dimension.PointXY;
import dimension.ConveyorBeltDimension;

/**
 * ConveyorBelt.java
 * @author:			Devin Barry
 * @date:			12.10.2012
 * @lastModified:	14.10.2012
 *
 * ConveyorBelt is a concrete implementation of a conveyor belt for fruit in
 * a fruit processing plant. It contains a DrawableFruitBuffer to hold the fruit and
 * process the moving of fruit along the conveyor belt.
 * 
 * It also contains a ConveyorBeltDimension, which is a special class customized
 * for dealing with the physical placement of the conveyor belt (with respect to
 * its draw methods) on the factory floor.
 * 
 * Because the DrawableFruitBuffer can draw itself, it needs access to the dimensions
 * that define this class, and thus is passed a copy of the ConveryBeltDimension
 * class when it is instantiated.
 * 
 * ConveyorBelt implements the Machine interface and as such also implements the
 * Drawable interface. Thus it contains a draw method and can draw itself.
 */
public class ConveyorBelt implements BufferMachine {
	
	public static final int NUM_SLOTS = 8;
	private DrawableFruitBuffer fb; //This buffer can draw itself and must be associated with the dimensions of its parent Machine
	private ConveyorBeltDimension cbd; //We pass it these dimensions when we create it
	
	public ConveyorBelt(PointXY start, double angle) {
		//startPosition, angle, bufferSize
		cbd = new ConveyorBeltDimension(start, angle, NUM_SLOTS);
		fb = new DrawableFruitBuffer(cbd, NUM_SLOTS);
	}
	
	public void addFruit(Fruit fruit) {
		fb.add(fruit);
	}
	
	public Fruit removeFruit() {
		return fb.removeFruit();
	}
	
	/**
	 * get the length of the Machine
	 * @return machine length
	 */
	public double getLength() {
		return cbd.getLength();
	}
	
	/**
	 * get the width of the Machine
	 * @return machine width
	 */
	public double getWidth() {
		return ConveyorBeltDimension.WIDTH;
	}
	
	/**
	 * Gets the location at which this item is positioned
	 * @return the point where this item is located in the factory
	 */
	public PointXY getStartPoint() {
		return cbd.getStartPoint();
	}
	
	/**
	 * This method gets the underlying buffer from this machine
	 */
	public ProductionBuffer getProductionBuffer() {
		return fb;
	}
	
	/**
	 * All classes that implement the Drawable Interface must be able to draw themselves
	 */
	public void draw(PointXY location) {
		PointXY drawPoint = cbd.getDrawPoint(location);
		//Draw the conveyer belt (currently a black rectangle)
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.filledAngledRectangle(drawPoint, cbd.getDrawHalfWidth(), cbd.getDrawHalfHeight(), cbd.getAngle());
		
		if (cbd.getAngle() == 0) {
			//Draw the DrawableFruitBuffer that is part of this ConyeorBelt
			fb.draw(cbd.getFirstSlotDrawPoint());
		}
		else {
			//Need to figure out how to draw buffer at an angle
		}
	}
	
	@Override
	public String toString() {
		return "ConveyorBelt:\n" + fb.toString(); 
	}
	
	//Main method used for testing this class separately
	public static void main(String[] args) {
		ConveyorBelt cb = new ConveyorBelt(new PointXY(50, 50), 0);
		//add some fruit
		Apple a1 = new Apple();
		Apple a2 = new Apple();
		Apple a3 = new Apple();
		Apple a4 = new Apple();
		cb.addFruit(a1);
		cb.addFruit(a2);
		cb.addFruit(a3);
		cb.addFruit(a4);
		//print output
		System.out.println(cb);
		cb.removeFruit();
		System.out.println(cb);
		cb.removeFruit();
		System.out.println(cb);
		cb.removeFruit();
		System.out.println(cb);
		cb.removeFruit();
		System.out.println(cb);
	}
}
