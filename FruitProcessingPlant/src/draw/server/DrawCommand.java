package draw.server;

import java.util.List;

/**
 * This class represents a single draw command
 * @author Devin Barry
 * @last modified 30/12/2012
 *
 */
public class DrawCommand {
	
	/**
	 * Either args1 or args2 are used. Not both
	 */
	
	private String command; //the operation to perform
	private List<Double> args1; //the arguments for the operation
	private Object[] args2; //the arguments for the operation
	private boolean isList;
	
	public DrawCommand() {
		command = null;
		args1 = null;
		args2 = null;
	}
	
	//Constructor can take a variable list of object arguments or an object array
	public DrawCommand(String command, Object... arguments) {
		this.command = command;
		this.args2 = arguments;
		isList = false;
	}
	
	//Constructor takes a list of Double
	public DrawCommand(String command, List<Double> arguments) {
		this.command = command;
		this.args1 = arguments;
		isList = true;
	}
	
	//add an object array as the argument
	public void addCommand(String command, Object... arguments) {
		this.command = command;
		this.args2 = arguments;
		isList = false;
	}
	
	//add a list of Double as the argument
	public void addCommand(String command, List<Double> arguments) {
		this.command = command;
		this.args1 = arguments;
		isList = true;
	}
	
	public String getCommandString() {
		return command;
	}
	
	public Object[] getObjectArgs() {
		return args2;
	}
	
	public List<Double> getListArgs() {
		return args1;
	}
	
	public boolean getIsList() {
		return isList;
	}

}
