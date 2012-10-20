package fruit;
 
import buffer.BufferSlot;


/**
 * Fruit.java
 * 
 * @author:			Devin Barry
 * @date:			09.10.2012
 * @lastModified: 	13.10.2012
 * 
 * This is the superclass for all fruit in the fruit production line
 */
public abstract class Fruit implements ProductionLineItem, BufferSlot {
	
	private static int totalFruit = 0;
	
	public static enum FruitType {
		APPLE,
		BANANA,
		PEAR
	}

	private FruitType fruit;
	private boolean bad;
	private boolean washed;
	
	public Fruit(FruitType fruit) {
		totalFruit++;
		this.fruit = fruit;
		generateBadness();
		this.washed = false;
	}
	
	/**
	 * Determines whether the fruit is bad or not
	 * 5% of our fruit will be bad
	 * @return
	 */
	private void generateBadness() {
		double random = Math.random();
		if (random < 0.05) {
			bad = true;
		}
		else {
			bad = false;
		}
	}
	
	public FruitType getFruitType() {
		return fruit;
	}
	
	public boolean getIsBad() {
		return bad;
	}
	
	public boolean getIsWashed() {
		return washed;
	}
	
	public boolean washFruit() {
		washed = true;
		return true;
	}
	
	public boolean isEmpty() {
		return false;
	}
	
	@Override
	public String toString() {
		return new String("Abstract Fruit Type\nThere are " + totalFruit + " in the system"); 
	}
}
 
