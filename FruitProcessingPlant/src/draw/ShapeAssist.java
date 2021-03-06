package draw;

/*************************************************************************
 * @Author:				Devin Barry
 * @Date:				21.10.2012
 * @LastModified:		28.10.2012
 * 
 * @OriginalAuthor: 	Robert Sedgewick and Kevin Wayne
 * @location:			introcs.cs.princeton.edu
 * 
 * This class is based on work originally in StdDraw written by Robert
 * Sedgewick and Kevin Wayne. However most of the methods here are either
 * totally new (written by me) or highly modified. I have added
 * significantly more power and flexability to the StdDraw library.
 * 
 * I have modified methods to improve flexibility of this class. The
 * modifications were significant enough that I decided these methods
 * should be in their own class, and not contribute to the already very
 * large size of StdDraw.
 * 
 * This class is entirely an extension to StdDraw and cannot be used
 * without it. It is not stand alone, more like a break up of a large class
 * into several smaller files. This is easy with StdDraw because the entire
 * class is static.
 * 
 * ShapeAssist is about the construction of shapes used in the StdDraw
 * library. The object of this class is to create java.awt.Shapes from
 * each of these methods.
 * 
 * Of some importance is the fact that the addition of this class removed
 * an important aspect of the original StdDraw class. Shapes that are very
 * small are no longer drawn as a pixel. They will be drawn at whatever
 * size they come out to when scaled. If this means that they are drawn
 * too small (ie not visible) then that may be the case. I am not too
 * worried about this effect. If I am drawing very small shapes or am
 * scaling shapes to the point that they are less than 1 pixel in size, I
 * am not too worried if they are not drawn.
 * 
 * ************************************************************************
 *  Remarks
 *  -------
 *    -  don't use AffineTransform for rescaling since it inverts
 *       images and strings
 *    -  careful using setFont in inner loop within an animation -
 *       it can cause flicker
 *
 *************************************************************************/

import java.awt.*;
import java.awt.geom.*;

public final class ShapeAssist {
	
	// singleton pattern: client can't instantiate
	private ShapeAssist() {
	}

	/*************************************************************************
	 * Drawing geometric shapes.
	 *************************************************************************/

	/**
	 * Create a line shape from (x0, y0) to (x1, y1).
	 * 
	 * @param x0
	 *            the x-coordinate of the starting point
	 * @param y0
	 *            the y-coordinate of the starting point
	 * @param x1
	 *            the x-coordinate of the destination point
	 * @param y1
	 *            the y-coordinate of the destination point
	 */
	public static Line2D.Double line(double x0, double y0, double x1, double y1) {
		return new Line2D.Double(StdDraw.scaleX(x0), StdDraw.scaleY(y0), StdDraw.scaleX(x1), StdDraw.scaleY(y1));
	}

	/**
	 * Create a point shape at (x, y).
	 * 
	 * @param x
	 *            the x-coordinate of the point
	 * @param y
	 *            the y-coordinate of the point
	 */
	public static Ellipse2D.Double point(double x, double y, double r) {
		double xs = StdDraw.scaleX(x);
		double ys = StdDraw.scaleY(y);
		return new Ellipse2D.Double(xs - r / 2, ys - r / 2, r, r);
	}

	/**
	 * Create a circle of radius r, centered on (x, y).
	 * 
	 * @param x
	 *            the x-coordinate of the center of the circle
	 * @param y
	 *            the y-coordinate of the center of the circle
	 * @param r
	 *            the radius of the circle
	 * @throws RuntimeException
	 *             if the radius of the circle is negative
	 */
	public static Ellipse2D.Double circle(double x, double y, double r) {
		if (r < 0)
			throw new RuntimeException("circle radius can't be negative");
		double xs = StdDraw.scaleX(x);
		double ys = StdDraw.scaleY(y);
		double ws = StdDraw.factorX(2 * r);
		double hs = StdDraw.factorY(2 * r);
		//if (ws <= 1 && hs <= 1)
		//	pixel(x, y);
		//else
			return new Ellipse2D.Double(xs - ws / 2, ys - hs / 2, ws, hs);
	}

	/**
	 * Create a circle of radius r, centered at <location>.
	 * 
	 * @param location
	 *            the Point at the center of the circle
	 * @param r
	 *            the radius of the circle
	 * @throws RuntimeException
	 *             if the radius of the circle is negative
	 */
	public static Ellipse2D.Double circle(Point2D.Double location, double r) {
		return circle(location.getX(), location.getY(), r);
	}

	

	/**
	 * Create an ellipse with given semimajor and semiminor axes, centered on (x,
	 * y).
	 * 
	 * @param x
	 *            the x-coordinate of the center of the ellipse
	 * @param y
	 *            the y-coordinate of the center of the ellipse
	 * @param semiMajorAxis
	 *            is the semimajor axis of the ellipse
	 * @param semiMinorAxis
	 *            is the semiminor axis of the ellipse
	 * @throws RuntimeException
	 *             if either of the axes are negative
	 */
	public static Ellipse2D.Double ellipse(double x, double y, double semiMajorAxis,
			double semiMinorAxis) {
		if (semiMajorAxis < 0)
			throw new RuntimeException(
					"ellipse semimajor axis can't be negative");
		if (semiMinorAxis < 0)
			throw new RuntimeException(
					"ellipse semiminor axis can't be negative");
		double xs = StdDraw.scaleX(x);
		double ys = StdDraw.scaleY(y);
		double ws = StdDraw.factorX(2 * semiMajorAxis);
		double hs = StdDraw.factorY(2 * semiMinorAxis);
		//if (ws <= 1 && hs <= 1)
		//	pixel(x, y);
		//else
			return new Ellipse2D.Double(xs - ws / 2, ys - hs / 2, ws, hs);
	}

	/**
	 * Create an arc of radius r, centered on (x, y), from angle1 to angle2 (in
	 * degrees).
	 * 
	 * @param x
	 *            the x-coordinate of the center of the circle
	 * @param y
	 *            the y-coordinate of the center of the circle
	 * @param r
	 *            the radius of the circle
	 * @param angle1
	 *            the starting angle. 0 would mean an arc beginning at 3
	 *            o'clock.
	 * @param angle2
	 *            the angle at the end of the arc. For example, if you want a 90
	 *            degree arc, then angle2 should be angle1 + 90.
	 * @throws RuntimeException
	 *             if the radius of the circle is negative
	 */
	public static Arc2D.Double arc(double x, double y, double r, double angle1, double angle2) {
		if (r < 0)
			throw new RuntimeException("arc radius can't be negative");
		while (angle2 < angle1)
			angle2 += 360;
		double xs = StdDraw.scaleX(x);
		double ys = StdDraw.scaleY(y);
		double ws = StdDraw.factorX(2 * r);
		double hs = StdDraw.factorY(2 * r);
		//if (ws <= 1 && hs <= 1)
		//	pixel(x, y);
		//else
			return new Arc2D.Double(xs - ws / 2, ys - hs / 2, ws, hs, angle1, angle2 - angle1, Arc2D.OPEN);
	}

	/**
	 * Create a square of side length 2r, centered on (x, y).
	 * 
	 * @param x
	 *            the x-coordinate of the center of the square
	 * @param y
	 *            the y-coordinate of the center of the square
	 * @param r
	 *            radius is half the length of any side of the square
	 * @throws RuntimeException
	 *             if r is negative
	 */
	public static Rectangle2D.Double square(double x, double y, double r) {
		if (r < 0)
			throw new RuntimeException("square side length can't be negative");
		double xs = StdDraw.scaleX(x);
		double ys = StdDraw.scaleY(y);
		double ws = StdDraw.factorX(2 * r);
		double hs = StdDraw.factorY(2 * r);
		//if (ws <= 1 && hs <= 1)
		//	pixel(x, y);
		//else
			return new Rectangle2D.Double(xs - ws / 2, ys - hs / 2, ws, hs);
	}

	/**
	 * Create a filled square of side length 2r, centered at <location>.
	 * 
	 * @param location
	 *            the Point at the center of the square
	 * @param r
	 *            radius is half the length of any side of the square
	 * @throws RuntimeException
	 *             if r is negative
	 */
	public static Rectangle2D.Double square(Point2D.Double location, double r) {
		return square(location.getX(), location.getY(), r);
	}
	
	/**
	 * Create a diamond of side length 2r, centred on (x, y).
	 * 
	 * @param x
	 *            the x-coordinate of the centre of the diamond
	 * @param y
	 *            the y-coordinate of the centre of the diamond
	 * @param r
	 *            radius is half the length of any side of the diamond
	 * @throws RuntimeException
	 *             if r is negative
	 */
	public static Path2D.Double diamond(double x, double y, double r) {
		if (r < 0)
			throw new RuntimeException("diamond side length can't be negative");
		double[] xs = { x - r, x, x + r, x };
		double[] ys = { y, y + r, y, y - r };
		Path2D.Double path = new Path2D.Double();
		path.moveTo(StdDraw.scaleX(xs[0]), StdDraw.scaleY(ys[0]));
		for (int i = 0; i < 4; i++) //4 points in a diamond
			path.lineTo(StdDraw.scaleX(xs[i]), StdDraw.scaleY(ys[i]));
		path.closePath();
		return path;
	}
	
	/**
	 * Create a diamond of side length 2r, centred at <location>.
	 * 
	 * @param location
	 *            the Point at the centre of the square
	 * @param r
	 *            radius is half the length of any side of the diamond
	 * @throws RuntimeException
	 *             if r is negative
	 */
	public static Path2D.Double diamond(Point2D.Double location, double r) {
		return diamond(location.getX(), location.getY(), r);
	}

	/**
	 * Create an equilateral triangle of radius r, centred at <location>.
	 * 
	 * @param location
	 *            the Point at the centre of the triangle
	 * @param r
	 *            radius is the length from the centre of the triangle to
	 *            any of its corners.
	 * @throws RuntimeException
	 *             if r is negative
	 */
	public static Path2D.Double triangle(double x, double y, double r) {
		if (r < 0)
			throw new RuntimeException("diamond side length can't be negative");
		
		//These are the triangle sides formed by connecting each corner
		//of the triangle to its centre point
		//30 degrees is half the internal angle of a equilateral triangle
		double ady = r * Math.sin(Math.toRadians(30));
		double adx = r * Math.cos(Math.toRadians(30));
		
		Point2D.Double c = new Point2D.Double(x, y + r);
		Point2D.Double a = new Point2D.Double(x - adx, y - ady);
		Point2D.Double b = new Point2D.Double(x + adx, y - ady);

		double[] xs = { a.x, b.x, c.x };
		double[] ys = { a.y, b.y, c.y };
		//Polygon triangle = new Polygon(xs, ys, xs.length);
		//Path2D.Double triangle = new Path2D.Double();
		return ShapeAssist.polygon(xs, ys);
	}

	/**
	 * Create a rectangle of given half width and half height, centered on (x, y).
	 * 
	 * @param x
	 *            the x-coordinate of the center of the rectangle
	 * @param y
	 *            the y-coordinate of the center of the rectangle
	 * @param halfWidth
	 *            is half the width of the rectangle
	 * @param halfHeight
	 *            is half the height of the rectangle
	 * @throws RuntimeException
	 *             if halfWidth or halfHeight is negative
	 */
	public static Rectangle2D.Double rectangle(double x, double y, double halfWidth,
			double halfHeight) {
		if (halfWidth < 0)
			throw new RuntimeException("half width can't be negative");
		if (halfHeight < 0)
			throw new RuntimeException("half height can't be negative");
		double xs = StdDraw.scaleX(x);
		double ys = StdDraw.scaleY(y);
		double ws = StdDraw.factorX(2 * halfWidth);
		double hs = StdDraw.factorY(2 * halfHeight);
		//if (ws <= 1 && hs <= 1)
		//	pixel(x, y);
		//else
			return new Rectangle2D.Double(xs - ws / 2, ys - hs / 2, ws, hs);
	}

	/**
	 * Create a filled rectangle of given half width and half height, centered at
	 * <location>.
	 * 
	 * @param location
	 *            the Point at the center of the rectangle
	 * @param halfWidth
	 *            is half the width of the rectangle
	 * @param halfHeight
	 *            is half the height of the rectangle
	 * @throws RuntimeException
	 *             if halfWidth or halfHeight is negative
	 */
	public static Rectangle2D.Double rectangle(Point2D.Double location, double halfWidth, double halfHeight) {
		return rectangle(location.getX(), location.getY(), halfWidth, halfHeight);
	}
	
	/**
	 * Create a rectangle of given half width and half height, centered on
	 * (x, y) and rotated by d degrees. The rotation is about the top
	 * left corner of the rectangle.
	 * 
	 * @param x
	 *            the x-coordinate of the top left of the rectangle
	 * @param y
	 *            the y-coordinate of the top left of the rectangle
	 * @param halfWidth
	 *            is half the width of the rectangle
	 * @param halfHeight
	 *            is half the height of the rectangle
	 * @param degrees
	 *            is the rotation of the rectangle with 3 o'clock being 0
	 *            degrees and 12 o'clock being 270 degrees
	 * @throws RuntimeException
	 *             if halfWidth or halfHeight is negative
	 */
	public static Shape angledRectangle(double x, double y, double halfWidth,
			double halfHeight, double degrees) {
		if (halfWidth < 0)
			throw new RuntimeException("half width can't be negative");
		if (halfHeight < 0)
			throw new RuntimeException("half height can't be negative");
		double xs = StdDraw.scaleX(x);
		double ys = StdDraw.scaleY(y);
		double ws = StdDraw.factorX(2 * halfWidth);
		double hs = StdDraw.factorY(2 * halfHeight);
		//if (ws <= 1 && hs <= 1)
		//	pixel(x, y);
		
		//Create a new rectangle at position (0,0)
		//Rotate it about it actual location
		//Translate it to its actual location
		
		Rectangle2D.Double rect = new Rectangle2D.Double(0, 0, ws, hs);
		AffineTransform tx = new AffineTransform();
		//tx.translate(xs - ws / 2, ys - hs / 2);
		//tx.translate(-(xs - ws / 2), -(ys - hs / 2));
		//tx.rotate(Math.toRadians(degrees), xs, ys);
		//tx.rotate(Math.toRadians(degrees), 0, 0);
		tx.rotate(Math.toRadians(degrees), xs - ws / 2, ys - hs / 2);
		tx.translate(xs - ws / 2, ys - hs / 2);
		//tx.translate(xs, ys);
		Shape rotatedRect = tx.createTransformedShape(rect);
		return rotatedRect;
	}

	/**
	 * Create a polygon with the given (x[i], y[i]) coordinates.
	 * 
	 * @param x
	 *            an array of all the x-coordindates of the polygon
	 * @param y
	 *            an array of all the y-coordindates of the polygon
	 */
	public static Path2D.Double polygon(double[] x, double[] y) {
		int N = x.length;
		Path2D.Double path = new Path2D.Double();
		path.moveTo(StdDraw.scaleX(x[0]), StdDraw.scaleY(y[0]));
		for (int i = 0; i < N; i++)
			path.lineTo(StdDraw.scaleX(x[i]), StdDraw.scaleY(y[i]));
		path.closePath();
		return path;
	}
}
