package console;

import tp.pr5.*;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;

public class ConsoleController extends Controller {

	public ConsoleController(RobotEngine game) 
	{
		super(game);
		
	}

	@Override
	public void startController() {
		
		
	}
	
	public boolean executeCommand(java.lang.String command) throws WrongInstructionFormatException
    {
		return false;
		
    }

}
