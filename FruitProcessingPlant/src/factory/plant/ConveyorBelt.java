package factory.plant;

import draw.StdDraw;
import buffer.DrawableFruitBuffer;
import buffer.ProductionBuffer;
import fruit.Fruit;
import fruit.Apple;
import factory.dimension.PointXY;
import factory.dimension.ConveyorBeltDimension;
import factory.machine.BufferMachine;

/**
 * ConveyorBelt.java
 * @author:			Devin Barry
 * @date:			12.10.2012
 * @lastModified:	23.10.2012
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
	
	public final int NUM_SLOTS; //normal ConveyorBelt is size 8, Large is size 19
	private DrawableFruitBuffer dfb; //This buffer can draw itself and must be associated with the dimensions of its parent Machine
	private ConveyorBeltDimension cbd; //We pass it these dimensions when we create it
	
	public ConveyorBelt(PointXY start, int slots, double angle) {
		NUM_SLOTS = slots;
		//startPosition, angle, bufferSize
		cbd = new ConveyorBeltDimension(start, angle, NUM_SLOTS);
		dfb = new DrawableFruitBuffer(cbd, NUM_SLOTS);
	}
	
	/**
	 * This method is called to add Fruit to the machine
	 */
	public void addFruit(Fruit fruit) {
		dfb.addFruit(fruit);
	}
	
	/**
	 * This method is called to remove from from the machine
	 */
	public Fruit removeFruit() {
		return dfb.removeFruit();
	}
	
	/**
	 * This method moves the fruit along in the buffer by
	 * a single space. This method is called instead of
	 * adding a Fruit to the buffer. When this method is
	 * called an empty space is added to the buffer instead
	 */
	public void AdvanceBuffer() {
		dfb.AdvanceBuffer();
	}
	
	/**
	 * This method gets the underlying buffer from this machine
	 */
	public ProductionBuffer getProductionBuffer() {
		return dfb;
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
	 * Gets the end position of this item. This end position is dependent
	 * on the size of the item. It is mostly useful for positioning
	 * other items that follow on in the production line
	 * @return the point where this item ends in the factory
	 */
	public PointXY getEndPoint() {
		return cbd.getEndPoint();
	}
	
	/**
	 * Gets the end position of this item, but factors in the
	 * machine spacing and angle of the current machine to choose
	 * an optimal starting position for the next machine on the
	 * production line.
	 * @return the point where this item ends in the factory
	 */
	public PointXY nextMachineStartPoint() {
		return cbd.nextMachineStartPoint();
	}
	
	/**
	 * All classes that implement the Drawable Interface must be able to draw themselves
	 */
	public void draw(PointXY location) {
		PointXY drawPoint = cbd.getDrawPoint(location);
		//Draw the conveyer belt (currently a black rectangle)
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.filledAngledRectangle(drawPoint, cbd.getDrawHalfWidth(), cbd.getDrawHalfHeight(), cbd.getAngle());
		
		//if (cbd.getAngle() == 0) {
			//Draw the DrawableFruitBuffer that is part of this ConyeorBelt
			dfb.draw(cbd.getFirstSlotDrawPoint());
		//}
		//else {
			//Need to figure out how to draw buffer at an angle
		//}
	}
	
	@Override
	public String toString() {
		return "ConveyorBelt:\n" + dfb.toString(); 
	}
	
	//Main method used for testing this class separately
	public static void main(String[] args) {
		ConveyorBelt cb = new ConveyorBelt(new PointXY(50, 50), 8, 0); //8 slots, 0 degrees
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