package draw.server;

import java.util.List;
//import java.io.IOException;
import java.io.Serializable;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;

/**
 * DrawCommand.java
 * 
 * @author:			Devin Barry
 * @date:			28.11.2012
 * @lastModified: 	10.01.2013
 *
 * This class represents a single draw command, whose intention
 * it is to be sent over the network.
 * 
 */
public class DrawCommand implements Serializable {
	
	//This ID number helps identify this class and its version during serialisation
	private static final long serialVersionUID = 3669441916092604118L;
	
	
	/**
	 * Either args1 or args2 are used. Not both
	 */
	private String command; //the operation to perform
	private List<Double> args1; //the arguments for the operation
	private Object[] args2; //the arguments for the operation
	private boolean isList; //true when args1 is used, false otherwise
	
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
	
	//These functions are needed for custom serialization
	/*
	private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
		CommandArguments ca = (CommandArguments)stream.readObject();
		args2 = ca.retrieveObjectArgs();
		
		stream.defaultReadObject();
		//read the object array back here
		System.out.println("Successfully retrieved Object[] from stream!");
	}
	
	private void writeObject(ObjectOutputStream stream) throws IOException {
		stream.defaultWriteObject();
		//write the object array here
		CommandArguments ca = new CommandArguments(args2);
		stream.writeObject(ca);
	}
	*/

}
