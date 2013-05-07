package tp.pr5.console;

import java.util.Scanner;

import tp.pr5.Constants;
import tp.pr5.Controller;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;

public class ConsoleController extends Controller {

	private Console console= new Console();
	
	public ConsoleController(RobotEngine game) 
	{
		super(game);
		this.registerEngineObserver(console);
		this.registerItemContainerObserver(console);
		this.registerRobotObserver(console);
	}

	@Override
	public void startController() {
		
		Scanner sc= new Scanner(System.in);

		
		/*do
		{
			console.robotSays(Constants.PROMPT);
			Instruction instruction;
			try 
			{
				instruction = Interpreter.generateInstruction(sc.nextLine());
				this.game.communicateRobot(instruction);
			} 
			catch (WrongInstructionFormatException e) 
			{
				System.out.println("WALL·E says: I do not understand. Please repeat");
			}
			
		} while(!this.game.quitRequest && this.game.getFuel()>0 && !this.navigationModule.atSpaceship());
		sc.close();*/
		
	}
	
	public boolean executeCommand(String command) throws WrongInstructionFormatException
    {
		return false;
    }

}
