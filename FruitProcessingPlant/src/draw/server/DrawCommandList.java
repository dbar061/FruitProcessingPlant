package draw.server;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

/**
 * This class represents a list of draw commands for the draw server.
 * Draw commands are ordered in the order in which they are put into
 * the command list
 * 
 * @author Devin Barry
 * @last modified 30/12/2012
 *
 */
public class DrawCommandList {
	
	private List<DrawCommand> commands;
	
	public DrawCommandList() {
		commands = new ArrayList<DrawCommand>(); //list is size 10 i think
	}
	
	//========================================
	// Native Adds
	//========================================
	public void addCommand(DrawCommand dc) {
		commands.add(dc);
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