package draw.server;

import draw.StdDraw;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

public class StdDrawServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final int WINDOW_LENGTH = 600;
		final int WINDOW_HEIGHT = 600;
		final double SCALE = 2.0; //how the factory dimensions relate to the window dimensions
		
		final double FACTORY_LENGTH = WINDOW_LENGTH * SCALE;
		final double FACTORY_HEIGHT = WINDOW_HEIGHT * SCALE; 
		
		StdDraw.setCanvasSize(WINDOW_HEIGHT, WINDOW_LENGTH); //pixel size of window
		StdDraw.setXscale(0, FACTORY_HEIGHT);
		StdDraw.setYscale(0, FACTORY_LENGTH);
		StdDraw.show(0);
		
		//testStuff();
		paint();
	}
	
	public static void paint() {
		paint(30);
	}
	
	/**
	 * This should probably be made to be thread safe
	 * TODO
	 * @param delay
	 */
	public static void paint(int delay) {
		// clear the background
		StdDraw.clear();
		
		//execute all the draw commands received
		testStuff();
		
		//Show everything that has been drawn
		StdDraw.show(delay);
	}
	
	public static void drawItems(DrawCommand command) {
		//iterate through command
		//Im pretty sure that because StdDraw is static and only one exists
		//We can simply access it from JavaScript as normal
		//eg
		//interpret(StdDraw.someshit());
		//interpret(StdDraw.someothershit());
	}
	
	public static void testStuff() {
		//StdDraw.setPenColor(StdDraw.CYAN);
		List<Color> args1 = new ArrayList<Color>();
		args1.add(StdDraw.CYAN);
		StdDrawServer.<Color>stdDrawMethod("setPenColor", args1);
		
		//StdDraw.filledCircle(100, 100, 100);
		List<Double> args2 = new ArrayList<Double>();
		args2.add(100.0);
		args2.add(100.0);
		args2.add(100.0);
		stdDrawMethod("filledCircle", args2);
		
		//StdDraw.setPenColor(StdDraw.BLUE);
		List<Color> args3 = new ArrayList<Color>();
		args3.add(StdDraw.BLUE);
		stdDrawMethod("setPenColor", args3);
		
		//StdDraw.circle(200, 200, 100);
		List<Double> args4 = new ArrayList<Double>();
		args4.add(200.0);
		args4.add(200.0);
		args4.add(100.0);
		stdDrawMethod("circle", args4);
	}
	
	private static void reflectiveCall() {
		
		//Reflection
		//http://stackoverflow.com/questions/37628/what-is-reflection-and-why-is-it-useful
		Class<StdDraw> sdclas = StdDraw.class;
		Method method = null;
		try {
			method = StdDraw.class.getMethod("filledCircle", double.class, double.class, double.class);
		}
		catch (NoSuchMethodException nsme) {
			nsme.printStackTrace();
			return;
		}
		try {
			method.invoke(null, 100, 100, 100); //null because method is static
		}
		catch (IllegalAccessException iae) {
			iae.printStackTrace();
		}
		catch (InvocationTargetException ite) {
			ite.printStackTrace();
		}
		
	}
	
	//Things to do here
	//Use type inference or something from generic methods to pass the correct types
	//with this varags method
	//http://docs.oracle.com/javase/tutorial/java/generics/methods.html
	
	//When we know the correct type, there will be no need to do all kinds of wierd casting
	private static <K> void stdDrawMethod(String methodName, List<K> arguments) {
		switch (methodName) {
			case "setPenColor":
				//StdDraw.setPenColor(StdDraw.CYAN);
				Color arg = (Color)arguments.get(0);
				StdDraw.setPenColor(arg);
				break;
			case "filledCircle":
				//StdDraw.filledCircle(100, 100, 100);
				Double arg1 = (Double)arguments.get(0);
				Double arg2 = (Double)arguments.get(1);
				Double arg3 = (Double)arguments.get(2);
				stdFilledCircle(arguments);
				//StdDraw.filledCircle(arg1.doubleValue(), arg2.doubleValue(), arg3.doubleValue());
				break;
			case "circle":
				arg1 = (Double)arguments.get(0);
				arg2 = (Double)arguments.get(1);
				arg3 = (Double)arguments.get(2);
				StdDraw.circle(arg1.doubleValue(), arg2.doubleValue(), arg3.doubleValue());
				break;
		}
	}
	
	//private static void stdFilledCircle(Double arg1, Double arg2, Double arg3) {
	private static void stdFilledCircle(List<Double> arguments) {
		Double arg1 = (Double)arguments.get(0);
		Double arg2 = (Double)arguments.get(1);
		Double arg3 = (Double)arguments.get(2);
		StdDraw.filledCircle(arg1.doubleValue(), arg2.doubleValue(), arg3.doubleValue());
	}
}
