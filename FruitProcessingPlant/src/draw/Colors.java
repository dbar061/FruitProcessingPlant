package draw;

import java.awt.Color;

/**
 * Apple.java
 * 
 * @author:			Devin Barry
 * @date:			09.01.2013
 * @lastModified: 	09.01.2013
 * 
 * draw.Colors is a static class replacing some of the
 * functionality of the StdDraw class. Because StdDraw
 * statically instantiates itself, it cannot be used in
 * a static manner when instantiation is not intended.
 * Thus I have moved some of its static features outside
 * the class to a class where such static use is possible.
 */
public class Colors {
	
	
	public static final Color BLACK = Color.BLACK;
	public static final Color BLUE = Color.BLUE;
	public static final Color CYAN = Color.CYAN;
	public static final Color DARK_GRAY = Color.DARK_GRAY;
	public static final Color GRAY = Color.GRAY;
	public static final Color GREEN = Color.GREEN;
	public static final Color LIGHT_GRAY = Color.LIGHT_GRAY;
	public static final Color MAGENTA = Color.MAGENTA;
	public static final Color ORANGE = Color.ORANGE;
	public static final Color PINK = Color.PINK;
	public static final Color RED = Color.RED;
	public static final Color WHITE = Color.WHITE;
	public static final Color YELLOW = Color.YELLOW;
	public static final Color BOOK_BLUE = new Color(9, 90, 166); //Pantone 300U. The RGB values are approximately (9, 90, 166).
	public static final Color BOOK_LIGHT_BLUE = new Color(103, 198, 243);
	public static final Color BOOK_RED = new Color(150, 35, 31); //It is Pantone 1805U. The RGB values are approximately (150, 35, 31).
	public static final Color SADDLE_BROWN = new Color(139,69,19); //saddle brown

}
