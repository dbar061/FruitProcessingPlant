package draw.server;

import java.util.List;
import java.util.ArrayList;

/**
 * This class represents a list of draw commands for the draw server
 * @author Axion
 *
 */
public class DrawCommand {
	
	private List<String> commands;
	
	public DrawCommand() {
		commands = new ArrayList<String>(); //list is size 10 i think
	}
	
	public boolean addCommand(String command) {
		//parse command here to make sure it is valid
		return commands.add(command);
	}
	
	public List<String> getAllCommands() {
		return commands;
	}

}
