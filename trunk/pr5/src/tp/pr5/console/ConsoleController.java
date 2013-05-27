package tp.pr5.console;

import java.util.Scanner;

import tp.pr5.Constants;
import tp.pr5.Controller;
import tp.pr5.Interpreter;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.Instruction;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;

public class ConsoleController extends Controller {

	public ConsoleController(RobotEngine game) 
	{
		super(game);
	}

	@Override
	public void startController() {
		
		this.game.requestStart();
		
		Scanner sc= new Scanner(System.in);
		String cm;
		boolean fin=false;
		
			do
			{
				//console.robotSays(Constants.PROMPT);
				System.out.print(Constants.PROMPT);
				cm=sc.nextLine();
				try 
				{
					fin=executeCommand(cm);
				} catch (WrongInstructionFormatException e) {
					this.game.requestError(Constants.MESSAGE_WHAT);
				}
			} while(!fin);
		
		sc.close();
		
	}
	
	/**
	 * This method implements the command execution.
	 * The string is parsed and the resulting command is executed in the game.
	 * After command execution, it checks if the game has finished
	 * @param command The string that probably contains a command
	 * @return true if the command execution raises the end of the game.
	 * @throws WrongInstructionFormatException
	 */
	public boolean executeCommand(String command) throws WrongInstructionFormatException
    {
		Instruction instruction = Interpreter.generateInstruction(command);
		this.game.communicateRobot(instruction);
		return this.game.isOver();
    }

}
