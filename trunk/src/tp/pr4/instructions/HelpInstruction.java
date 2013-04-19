package tp.pr4.instructions;
//Funciona
import java.util.StringTokenizer;

import tp.pr4.*;
import tp.pr4.instructions.exceptions.*;
import tp.pr4.items.*;

/**
 * Shows the game help with all the instructions that the robot can execute. 
 * This instruction works if the user writes HELP or AYUDA
 * 
 **/
public class HelpInstruction implements Instruction 
{
	private RobotEngine engine;
	private ItemContainer robotContainer;
	private NavigationModule navigation;
	/**
	 *Parses the String returning a HelpInstruction instance or throwing a WrongInstructionFormatException() 
	 */
	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException 
	
	{
		StringTokenizer cmd= new StringTokenizer(cad, " ");
		if (cmd.countTokens()>0)
		{
			String cmdHelp = cmd.nextToken();
			if (cmdHelp.equalsIgnoreCase("HELP") || cmdHelp.equalsIgnoreCase("AYUDA"))
			{
				if (!cmd.hasMoreTokens())
				{
					return new HelpInstruction();
				}
			}
		}
		throw new WrongInstructionFormatException();
			
	}

	/**
	 * Prints the help string of every instruction. It delegates to the Interpreter class.
	 * 
	 */
	@Override
	public String getHelp() 
	{
	
		return  "     HELP|AYUDA";
	}

	/**
	 * Configuration of the context for this instruction
	 */
	@Override
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) 
	{
	
		this.engine=engine;
		this.navigation=navigation;
		this.robotContainer=robotContainer;
	}
	
	/**
	 * Prints the help string of every instruction. It delegates to the Interpreter class.
	 */
	@Override
	public void execute() throws InstructionExecutionException 
	{
		try
		{
			this.engine.requestHelp();
		}
		catch (Exception e)
		{
			throw new InstructionExecutionException();
		}
		
		
	}

	@Override
	public void undo() {
		//segun las variables de la ejecucion, deshacer
		
	}

	@Override
	public boolean isUndoable() {
		return false;
	}
	
}
