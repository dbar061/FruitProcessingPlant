package draw.server;

import java.awt.geom.Point2D;
import java.util.List;

import draw.StdDraw;

/**
 * This class is a helper class for the StdDraw Server
 * It allows arguments for shapes to be passed as List<Double> or
 * to as Object
 * 
 * @author Devin Barry
 * @last modified 30/12/2012
 *
 */

public class ServerSupport {
	
	/*************************************************************************
	 * Drawing geometric shapes.
	 *************************************************************************/

	public static void line(List<Double> arguments) {
		//public static void line(double x0, double y0, double x1, double y1) {
		StdDraw.line(arguments.get(0), arguments.get(1), arguments.get(2), arguments.get(3)); //unboxing
	}

	public static void point(List<Double> arguments) {
		//public static void point(double x, double y) {
		StdDraw.point(arguments.get(0), arguments.get(1)); //unboxing
	}

	public static void circle(List<Double> arguments) {
		//public static void circle(double x, double y, double r)
		StdDraw.circle(arguments.get(0), arguments.get(1), arguments.get(2)); //unboxing
	}

	public static void circle(Object loc, Object rad) {
		//public static void circle(Point2D.Double location, double r) {
		StdDraw.circle((Point2D.Double)loc, (double)rad);
	}

	public static void filledCircle(List<Double> arguments) {
		//public static void filledCircle(double x, double y, double r)
		StdDraw.filledCircle(arguments.get(0), arguments.get(1), arguments.get(2)); //unboxing
	}

	public static void filledCircle(Object loc, Object rad) {
		//public static void filledCircle(Point2D.Double location, double r) {
		StdDraw.filledCircle((Point2D.Double)loc, (double)rad);
	}

	public static void ellipse(List<Double> arguments) {
		//public static void ellipse(double x, double y, double semiMajorAxis, double semiMinorAxis) {
		StdDraw.ellipse(arguments.get(0), arguments.get(1), arguments.get(2), arguments.get(3)); //unboxing
	}

	public static void filledEllipse(List<Double> arguments) {
		//public static void filledEllipse(double x, double y, double semiMajorAxis, double semiMinorAxis) {
		StdDraw.filledEllipse(arguments.get(0), arguments.get(1), arguments.get(2), arguments.get(3)); //unboxing
	}

	public static void arc(List<Double> arguments) {
		//public static void arc(double x, double y, double r, double angle1, double angle2) {
		StdDraw.arc(arguments.get(0), arguments.get(1), arguments.get(2), arguments.get(3), arguments.get(4)); //unboxing
	}

	public static void square(List<Double> arguments) {
		//public static void square(double x, double y, double r) {
		StdDraw.square(arguments.get(0), arguments.get(1), arguments.get(2)); //unboxing
	}

	public static void filledSquare(List<Double> arguments) {
		//public static void filledSquare(double x, double y, double r) {
		StdDraw.filledSquare(arguments.get(0), arguments.get(1), arguments.get(2)); //unboxing
	}

	public static void filledSquare(Object loc, Object r) {
		//public static void filledSquare(Point2D.Double location, double r) {
		StdDraw.filledSquare((Point2D.Double)loc, (double)r);
	}
	
	public static void diamond(List<Double> arguments) {
		//public static void diamond(double x, double y, double r) {
		StdDraw.diamond(arguments.get(0), arguments.get(1), arguments.get(2)); //unboxing
	}
	
	public static void filledDiamond(List<Double> arguments) {
		//public static void filledDiamond(double x, double y, double r) {
		StdDraw.filledDiamond(arguments.get(0), arguments.get(1), arguments.get(2)); //unboxing
	}

	public static void filledDiamond(Object loc, Object r) {
		//public static void filledDiamond(Point2D.Double location, double r) {
		StdDraw.filledDiamond((Point2D.Double)loc, (double)r);
	}
	
	public static void triangle(List<Double> arguments) {
		//public static void triangle(double x, double y, double r) {
		StdDraw.triangle(arguments.get(0), arguments.get(1), arguments.get(2)); //unboxing
	}
	
	public static void filledTriangle(List<Double> arguments) {
		//public static void filledTriangle(double x, double y, double r) {
		StdDraw.filledTriangle(arguments.get(0), arguments.get(1), arguments.get(2)); //unboxing
	}

	public static void rectangle(List<Double> arguments) {
		//public static void rectangle(double x, double y, double halfWidth, double halfHeight) {
		StdDraw.rectangle(arguments.get(0), arguments.get(1), arguments.get(2), arguments.get(3)); //unboxing
	}

	public static void filledRectangle(List<Double> arguments) {
		//public static void filledRectangle(double x, double y, double halfWidth, double halfHeight) {
		StdDraw.filledRectangle(arguments.get(0), arguments.get(1), arguments.get(2), arguments.get(3)); //unboxing
	}
	
	public static void filledRectangle(Object loc, Object halfWidth, Object halfHeight) {
		//public static void filledRectangle(Point2D.Double location, double halfWidth, double halfHeight) {
		StdDraw.filledRectangle((Point2D.Double)loc, (double)halfWidth, (double)halfHeight);
	}
	
	public static void angledRectangle(List<Double> arguments) {
		//public static void angledRectangle(double x, double y, double halfWidth, double halfHeight, double degrees) {
		StdDraw.angledRectangle(arguments.get(0), arguments.get(1), arguments.get(2), arguments.get(3), arguments.get(4)); //unboxing
	}
	
	public static void filledAngledRectangle(List<Double> arguments) {
		//public static void filledAngledRectangle(double x, double y, double halfWidth, double halfHeight, double degrees) {
		StdDraw.filledAngledRectangle(arguments.get(0), arguments.get(1), arguments.get(2), arguments.get(3), arguments.get(4)); //unboxing
	}
	
	public static void filledAngledRectangle(Object loc, Object halfWidth, Object halfHeight, Object degrees) {
		//public static void filledAngledRectangle(Point2D.Double location, double halfWidth, double halfHeight, double degrees) {
		StdDraw.filledAngledRectangle((Point2D.Double)loc, (double)halfWidth, (double)halfHeight, (double)degrees);
	}
	
	public static void polygon(Object x, Object y) {
		//public static void polygon(double[] x, double[] y) {
		StdDraw.polygon((double[])x, (double[])y);
	}
	
	public static void filledPolygon(Object x, Object y) {
		//public static void filledPolygon(double[] x, double[] y) {
		StdDraw.filledPolygon((double[])x, (double[])y);
	}
	
	/*************************************************************************
	 * Drawing text.
	 *************************************************************************/

	public static void text(Object x, Object y, Object s) {
		//public static void text(double x, double y, String s) {
		StdDraw.text((double)x, (double)y, (String)s);
	}

	public static void text(Object location, Object s) {
		//public static void text(Point2D.Double location, String s) {
		StdDraw.text((Point2D.Double)location, (String)s);
	}

	public static void text(Object x, Object y, Object s, Object degrees) {
		//public static void text(double x, double y, String s, double degrees) {
		StdDraw.text((double)x, (double)y, (String)s, (double)degrees);
	}

	public static void textLeft(Object x, Object y, Object s) {
		//public static void textLeft(double x, double y, String s) {
		StdDraw.textLeft((double)x, (double)y, (String)s);
	}

	public static void textRight(Object x, Object y, Object s) {
		//public static void textRight(double x, double y, String s) {
		StdDraw.textRight((double)x, (double)y, (String)s);
	}

}
