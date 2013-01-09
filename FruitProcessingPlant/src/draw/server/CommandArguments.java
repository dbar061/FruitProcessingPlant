package draw.server;

import java.awt.Color;
import java.io.Serializable;

/**
 * CommandArguments.java
 * 
 * @author:			Devin Barry
 * @date:			09.01.2013
 * @lastModified: 	10.01.2013
 *
 * This class represents the arguments supplied to with
 * a DrawCommand. Often these arguments are in the form
 * of an Object array, which cannot be serialized.
 * 
 * This class reorganises the form of the arguments to
 * allow them to be serialized. In essence, this class
 * allows an Object array to be serialized and
 * deserialized.
 */
public class CommandArguments implements Serializable {
	
	private static final long serialVersionUID = -2794212350079861349L;
	
	/**
	 * List below are the possible combinations of items
	 * that charade as Objects. Thus it can be see that
	 * the maximum length of the object array should be 4
	 */
	//From StdDrawServer (09.01.2013)
	//int
	//Color
	
	//From ServerSupport (09.01.2013)
	//Point2D.Double, double
	//Point2D.Double, double, double
	//Point2D.Double, double, double, double
	//double[], double[]
	//double, double, String
	//Point2d.Double, String
	//double, double, String, double
	//double, double, String
	
	private int intarg;
	private Color colorarg;
	private int length = 0;
	
	public CommandArguments() {
	}
	
	public CommandArguments(Object[] args) {
		length = args.length;
		
		for (int i = 0; i < args.length; i++) {
			if (args[i].getClass().equals(Color.class)) {
				colorarg = (Color)args[i];
				System.out.println("Color!");
			}
			else if (args[i].getClass().equals(int.class)) {
				intarg = (int)args[i];
				System.out.println("int!");
			}
			else {
				System.out.println("Class is: " + args[i].getClass());
			}
		}
	}
	
	public void storeObjectArgs(Object[] args) {
		//do some things
	}
	
	public Object[] retrieveObjectArgs() {
		return new Object[length];
	}

}
