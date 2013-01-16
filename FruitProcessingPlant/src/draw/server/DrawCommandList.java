package draw.server;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

/**
 * DrawCommandList.java
 * 
 * @author:			Devin Barry
 * @date:			15.12.2012
 * @lastModified: 	17.01.2013
 *
 * This class represents a list of draw commands for the draw server.
 * Draw commands are ordered in the order in which they are put into
 * the command list.
 * 
 * There are a variety of "addCommand" methods that allow easy addition
 * of draw commands in all the various forms that the come in.
 * 
 * The list size of 50 is quite moderate. A single section can easily
 * have 200 or 300 commands.
 * TODO lookup how quickly the list expands itself
 * 
 */
public class DrawCommandList implements Serializable {
	
	private static final long serialVersionUID = 778925784896344441L;
	
	private List<DrawCommand> commands;
	
	public DrawCommandList() {
		commands = new ArrayList<DrawCommand>(50); //start with a list of size 50
	}
	
	//========================================
	// Native Adds
	//========================================
	public void addCommand(DrawCommand dc) {
		commands.add(dc);
	}
	
	//This method is used for commands like "show" or "clear"
	public void addCommand(String command) {
		commands.add(new DrawCommand(command));
	}
	
	public void addCommand(String command, Object... arguments) {
		commands.add(new DrawCommand(command, arguments));
	}
	
	public void addCommand(String command, List<Double> arguments) {
		commands.add(new DrawCommand(command, arguments));
	}
	
	
	//========================================
	// Constructed Adds
	//========================================
	public void addCommand(String command, Double a1, Double a2, Integer a3) {
		this.addCommand(command, a1, a2, a3.doubleValue());
	}
	
	//Construct a List<Double> from an array of Double
	public void addCommand(String command, Double...arguments) {
		commands.add(new DrawCommand(command, Arrays.asList(arguments)));
	}
	
	
	
	public List<DrawCommand> getAllCommands() {
		return commands;
	}
}