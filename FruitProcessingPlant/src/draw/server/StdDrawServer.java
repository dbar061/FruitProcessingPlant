package draw.server;

import draw.StdDraw;
import draw.Colors;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

/**
 * StdDrawServer.java
 * 
 * @author:			Devin Barry
 * @date:			15.11.2012
 * @lastModified: 	09.01.2013
 * 
 * This class is the core of the Drawing server that can be run by
 * executing DrawServer.java.
 * 
 * DrawServer.java instantiates a copy of StdDrawServer and it is
 * in this class that the translating of DrawCommands into visible
 * drawn objects in the frame occurs.
 * 
 * StdDrawServer uses its assist class (ServerSupport) to call StdDraw
 * commands, which actually draw the items required.
 */
public class StdDrawServer {
	
	public StdDrawServer() {
		initialiseWindow();
	}

	private void initialiseWindow() {
		
		final int WINDOW_LENGTH = 600;
		final int WINDOW_HEIGHT = 1200; //sometimes 600
		//zoom out to allow more draw space in the window
		final double SCALE = 2.2; //how the factory dimensions relate to the window dimensions
		
		final double FACTORY_LENGTH = WINDOW_LENGTH * SCALE;
		final double FACTORY_HEIGHT = WINDOW_HEIGHT * SCALE; 
		
		StdDraw.setCanvasSize(WINDOW_HEIGHT, WINDOW_LENGTH); //pixel size of window
		StdDraw.setXscale(0, FACTORY_HEIGHT);
		StdDraw.setYscale(0, FACTORY_LENGTH);
		StdDraw.show(0);
	}
	
	/***********************************************************************************
	 * Server Test methods
	 ***********************************************************************************/
	//main method used for testing draw server
	public static void main(String[] args) {
		StdDrawServer sdserver = new StdDrawServer();
		
		//testDrawCmdProcessing();
		
		DrawCommandList dcl = createDrawCommands();
		//execute test draw commands
		sdserver.drawItems(dcl);
	}
	
	//Create a DrawCommandList with commands to draw two circles
	private static DrawCommandList createDrawCommands() {
		DrawCommandList dcl = new DrawCommandList();
		
		//clear the background
		//StdDraw.clear();
		dcl.addCommand("clear");
		
		//StdDraw.setPenColor(StdDraw.CYAN);
		dcl.addCommand("setPenColor", Colors.CYAN);
		
		//StdDraw.filledCircle(100, 100, 100);
		List<Double> args2 = new ArrayList<>();
		args2.add(100.0);
		args2.add(100.0);
		args2.add(100.0);
		dcl.addCommand("filledCircle", args2);
		
		//StdDraw.setPenColor(StdDraw.BLUE);
		dcl.addCommand("setPenColor", Colors.BLUE);
		
		//StdDraw.circle(200, 200, 100);
		List<Double> args4 = new ArrayList<>();
		args4.add(200.0);
		args4.add(200.0);
		args4.add(100.0);
		dcl.addCommand("circle", args4);
		
		//Show everything that has been drawn
		//StdDraw.show(30);
		dcl.addCommand("show", 30);
		
		return dcl;
	}
	
	
	
	/***********************************************************************************
	 * DrawCommand processing
	 ***********************************************************************************/
	@SuppressWarnings("unused")
	private void paint() {
		paint(30);
	}
	
	private void paint(int delay) {
		// clear the background
		StdDraw.clear();
		//execute all the draw commands received
		//Show everything that has been drawn
		StdDraw.show(delay);
	}
	
	//Iterates through all commands in the command list and draws them
	public void drawItems(DrawCommandList commands) {
		List<DrawCommand> ldc = commands.getAllCommands();
		//System.out.println("Drawing item from list of size: " + ldc.size());
		for (DrawCommand dc : ldc) {
			if (dc.getIsList()) {
				processDrawCommand(dc.getCommandString(), dc.getListArgs());
			}
			else {
				processDrawCommand(dc.getCommandString(), dc.getObjectArgs());
			}
		}
	}
	
	/***********************************************************************************
	 * VARARGS
	 * Note Object... arguments should be thought of as Object[] arguments
	 ***********************************************************************************/
	
	@SuppressWarnings("unused")
	private static void testDrawCmdProcessing() {
		//processDrawCommand("show", 3000);
		
		//clear the background
		//StdDraw.clear();
		processDrawCommand("clear");
		
		
		//StdDraw.setPenColor(StdDraw.CYAN);
		processDrawCommand("setPenColor", Colors.CYAN);
		
		//StdDraw.filledCircle(100, 100, 100);
		List<Double> args2 = new ArrayList<>();
		args2.add(100.0);
		args2.add(100.0);
		args2.add(100.0);
		processDrawCommand("filledCircle", args2);
		
		//StdDraw.setPenColor(StdDraw.BLUE);
		processDrawCommand("setPenColor", Colors.BLUE);
		
		//StdDraw.circle(200, 200, 100);
		List<Double> args4 = new ArrayList<>();
		args4.add(200.0);
		args4.add(200.0);
		args4.add(100.0);
		processDrawCommand("circle", args4);
		
		//Show everything that has been drawn
		//StdDraw.show(30);
		//processDrawCommand("show", 3000);
		processDrawCommand("show");
	}
	
	/**
	 * processDrawCommand
	 * 
	 * This method interprets a draw command by executing the matching method
	 * All arguments in this method come as an array of Object
	 * 
	 * @param methodName
	 * @param arguments - The arguments passed to the method
	 */
	private static void processDrawCommand(String methodName, Object... arguments) {
		switch (methodName) {
		case "clear":
			StdDraw.clear();
			break;
		case "show":
			if (arguments.length > 0) StdDraw.show((int)arguments[0]);
			else StdDraw.show();
			break;
		case "setPenColor":
			Color arg = (Color)arguments[0];
			StdDraw.setPenColor(arg);
			break;
		case "circle":
			ServerSupport.circle(arguments[0], arguments[1]);
			break;
		case "filledCircle":
			ServerSupport.filledCircle(arguments[0], arguments[1]);
			break;
		case "filledSquare":
			ServerSupport.filledSquare(arguments[0], arguments[1]);
			break;
		case "filledDiamond":
			ServerSupport.filledDiamond(arguments[0], arguments[1]);
			break;
		case "filledRectangle":
			ServerSupport.filledRectangle(arguments[0], arguments[1], arguments[2]);
			break;
		case "filledAngledRectangle":
			ServerSupport.filledAngledRectangle(arguments[0], arguments[1], arguments[2], arguments[3]);
			break;
		case "polygon":
			ServerSupport.polygon(arguments[0], arguments[1]);
			break;
		case "filledPolygon":
			ServerSupport.filledPolygon(arguments[0], arguments[1]);
			break;
		case "text":
			if (arguments.length == 2) ServerSupport.text(arguments[0], arguments[1]);
			else if (arguments.length == 3) ServerSupport.text(arguments[0], arguments[1], arguments[2]);
			else System.err.println("Error - Unsupported text method!");
			break;
		default:
			System.err.println("Error - Unknown Object method name: " + methodName);
			break;
		}
	}
	
	
	/**
	 * processDrawCommand
	 * 
	 * This method interprets a draw command by executing the matching method
	 * All arguments in this method come as a List<Double>
	 * 
	 * @param methodName
	 * @param arguments - The arguments passed to the method
	 */
	private static void processDrawCommand(String methodName, List<Double> arguments) {
		switch (methodName) {
		case "line":
			ServerSupport.line(arguments);
			break;
		case "point":
			ServerSupport.point(arguments);
			break;
		case "circle":
			ServerSupport.circle(arguments);
			break;
		case "filledCircle":
			ServerSupport.filledCircle(arguments);
			break;
		case "ellipse":
			ServerSupport.ellipse(arguments);
			break;
		case "filledEllipse":
			ServerSupport.filledEllipse(arguments);
			break;
		case "arc":
			ServerSupport.arc(arguments);
			break;
		case "square":
			ServerSupport.square(arguments);
			break;
		case "filledSquare":
			ServerSupport.filledSquare(arguments);
			break;
		case "diamond":
			ServerSupport.diamond(arguments);
			break;
		case "filledDiamond":
			ServerSupport.filledDiamond(arguments);
			break;
		case "triangle":
			ServerSupport.triangle(arguments);
			break;
		case "filledTriangle":
			ServerSupport.filledTriangle(arguments);
			break;
		case "rectangle":
			ServerSupport.rectangle(arguments);
			break;
		case "filledRectangle":
			ServerSupport.filledRectangle(arguments);
			break;
		case "angledRectangle":
			ServerSupport.angledRectangle(arguments);
			break;
		case "filledAngledRectangle":
			ServerSupport.filledAngledRectangle(arguments);
			break;
		default:
			System.err.println("Error - Unknown List method name: " + methodName);
			break;
		}
	}
}
