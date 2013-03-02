package inventory.fruit;
 
import inventory.Inventory;
import buffer.BufferSlot;


/**
 * Fruit.java
 * 
 * @author:			Devin Barry
 * @date:			09.10.2012
 * @lastModified: 	20.01.2013
 * 
 * This is the superclass for all fruit in the fruit production line
 */
public abstract class Fruit implements Inventory, BufferSlot {
	
	/**
	 * All serializable classes must declare serialVersionUID
	 */
	private static final long serialVersionUID = -8577691659172441404L;
	
	private static int totalFruit = 0;
	
	public static enum FruitType {
		APPLE,
		BANANA,
		PEAR
	}

	private FruitType fruit;
	private boolean bad;
	private boolean washed;
	private boolean cut;
	private boolean cutCorrect;
	
	public Fruit(FruitType fruit) {
		totalFruit++;
		this.fruit = fruit;
		generateBadness();
		this.washed = false;
		this.cut = false;
		this.cutCorrect = false;
	}
	
	/**
	 * Washes the fruit.
	 * @return
	 */
	public void washFruit() {
		washed = true;
	}

	/**
	 * All fruit gets cut, but a small proportion
	 * ends up being cut incorrectly
	 */
	public void cutFruit() {
		cut = true; //all fruit gets cut
		//some gets cut incorrectly
		double random = Math.random();
		if(random < 0.1) { //probability is especially modified here for the demo
			cutCorrect = false;
		}
		else{
			cutCorrect = true;
		}
	}
	
	/**
	 * Determines whether the fruit is bad or not
	 * 10% of our fruit will be bad
	 * @return
	 */
	private void generateBadness() {
		double random = Math.random();
		if (random < 0.1) { //probability is especially modified here for the demo
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
	
	public boolean getIsCut() {
		return cut;
	}
	
	public boolean getIsCutCorrect(){
		return cutCorrect;
	}
	
	/**
	 * Fruit are all real items and are as such not empty
	 */
	public boolean isEmpty() {
		return false;
	}
	
	@Override
	public String toString() {
		return new String("Abstract Fruit Type\nThere are " + totalFruit + " in the system"); 
	}
}