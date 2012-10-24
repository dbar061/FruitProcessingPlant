package factory.dimension;

import factory.dimension.PointXY;

/**
 * Locatable.java
 * @author:			Devin Barry
 * @date:			22.10.2012
 * @lastModified:	23.10.2012
 *
 *
 * The Locatable interface binds together the factory sub packages.
 * It is used by both the Dimension sub-package as well as the plant
 * sub package. All FactoryDimension are Locatable and all Machine
 * are Locatable.
 * 
 * The reason these two sub packages share this interface is because
 * of the intimate link between the Dimension sub-package and the
 * plant sub-package. All machines have an associated dimension and
 * thus both must be Locatable
 * 
 * Specifically the Locatable interface it is used by all machines
 * that have a start and end point on the factory floor (which is
 * all the machines on the floor). Locatable also provides a method
 * for a machine to indicate where the next machine in sequence 
 * should be place.
 */
public interface Locatable {
	
	//This is the preferred spacing between machines
	public static final int SPACING = 5; //scaled pixels
	
	/**
	 * Gets the location at which this item is positioned
	 * @return the point where this item is located in the factory
	 */
	public PointXY getStartPoint();
	
	/**
	 * Gets the end position of this item. This end position is
	 * dependent on the size of the item. If the item is a machine
	 * on the factory floor, then this method is used for
	 * positioning other items that follow on in the production
	 * line
	 * @return the point where this item ends in the factory
	 */
	public PointXY getEndPoint();
	
	/**
	 * Gets the end position of this item, but factors in the
	 * machine spacing and angle of the current machine to choose
	 * an optimal starting position for the next machine on the
	 * production line.
	 * @return the point where this item ends in the factory
	 */
	public PointXY nextMachineStartPoint();

}

