package factory.plant;

import inventory.Inventory;
import inventory.fruit.Apple;
import buffer.DrawableInventoryBuffer;
import buffer.ProductionBuffer;
import factory.dimension.PointXY;
import factory.dimension.ConveyorBeltDimension;
import factory.machine.BufferMachine;
import draw.Colors;
import draw.server.DrawCommandList;
import java.util.List;
import java.util.ArrayList;

/**
 * ConveyorBelt.java
 * @author:			Devin Barry
 * @date:			12.10.2012
 * @lastModified:	09.01.2013
 *
 * ConveyorBelt is a concrete implementation of a conveyor belt for fruit in
 * a fruit processing plant. It contains a DrawableInventoryBuffer to hold the fruit and
 * process the moving of fruit along the conveyor belt.
 * 
 * It also contains a ConveyorBeltDimension, which is a special class customized
 * for dealing with the physical placement of the conveyor belt (with respect to
 * its draw methods) on the factory floor.
 * 
 * Because the DrawableInventoryBuffer can draw itself, it needs access to the dimensions
 * that define this class, and thus is passed a copy of the ConveryBeltDimension
 * class when it is instantiated.
 * 
 * ConveyorBelt implements the Machine interface and as such also implements the
 * Drawable interface. Thus it contains a draw method and can draw itself.
 */
public class ConveyorBelt implements BufferMachine {
	
	public final int NUM_SLOTS; //normal ConveyorBelt is size 8, Large is size 19
	private DrawableInventoryBuffer dfb; //This buffer can draw itself and must be associated with the dimensions of its parent Machine
	private ConveyorBeltDimension cbd; //We pass it these dimensions when we create it
	
	public ConveyorBelt(PointXY start, int slots, double angle) {
		NUM_SLOTS = slots;
		//startPosition, angle, bufferSize
		cbd = new ConveyorBeltDimension(start, angle, NUM_SLOTS);
		dfb = new DrawableInventoryBuffer(cbd, NUM_SLOTS);
	}
	
	/**
	 * This method is called to add Inventory to the machine
	 */
	public void addInventory(Inventory inventory) {
		dfb.addInventory(inventory);
	}
	
	/**
	 * This method is called to remove Inventory from the machine
	 */
	public Inventory removeInventory() {
		return dfb.removeInventory();
	}
	
	/**
	 * This method moves the Inventory along in the buffer by
	 * a single space. This method is called instead of
	 * adding Inventory to the buffer. When this method is
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
	public void draw(DrawCommandList dcl, PointXY location) {
		PointXY drawPoint = cbd.getDrawPoint(location);
		
		//Create the DrawCommandList argument list
		List<Double> args = new ArrayList<>(6);
		args.add(drawPoint.x);
		args.add(drawPoint.y);
		args.add(cbd.getDrawHalfWidth());
		args.add(cbd.getDrawHalfHeight());
		args.add(cbd.getAngle());
		
		//Draw the conveyer belt (currently a black rectangle)
		dcl.addCommand("setPenColor", Colors.BLACK);
		dcl.addCommand("filledAngledRectangle", args);
		
		//Draw the DrawableInventoryBuffer that is part of this ConyeorBelt
		dfb.draw(dcl, cbd.getFirstSlotDrawPoint());
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
		cb.addInventory(a1);
		cb.addInventory(a2);
		cb.addInventory(a3);
		cb.addInventory(a4);
		//print output
		System.out.println(cb);
		cb.removeInventory();
		System.out.println(cb);
		cb.removeInventory();
		System.out.println(cb);
		cb.removeInventory();
		System.out.println(cb);
		cb.removeInventory();
		System.out.println(cb);
	}
}
