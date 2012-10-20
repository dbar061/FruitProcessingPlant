package dimension;

import java.awt.geom.Point2D;

/**
 * PointXY.java
 * 
 * @author:			Devin Barry
 * @date:			13.10.2012
 * @lastModified: 	13.10.2012
 * 
 * The only "point" of this class is to give an extra useful constructor,
 * namely the ability to construct from another PointXY or Point2D.Double
 */
public class PointXY extends Point2D.Double {
	
	/**
	 * Classes all seem to need this
	 */
	private static final long serialVersionUID = 1L;

	public PointXY() {
		super();
	}
	
	/*Additional required constructor */
	public PointXY(PointXY p) {
		super(p.getX(), p.getY());
	}
	
	/*Additional required constructor */
	public PointXY(Point2D.Double p) {
		super(p.getX(), p.getY());
	}
	
	public PointXY(double x, double y) {
		super(x, y);
	}
	
	//Class method for performing addition on two points
	public static PointXY sum(PointXY addend1, PointXY addend2) {
		double xSum = addend1.getX() + addend2.getX();
		double ySum = addend1.getY() + addend2.getY();
		return new PointXY(xSum, ySum);
	}
	
	//Adds another point to the current point
	//TODO this should probably return iteself??
	public void add(PointXY addend) {
		setLocation((this.getX() + addend.getX()), (this.getY() + addend.getY()));
	}

}
