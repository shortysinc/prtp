package tp.pr4.instructions;
//Funciona
import java.util.StringTokenizer;

import org.omg.CORBA.ExceptionList;

import tp.pr4.NavigationModule;
import tp.pr4.RobotEngine;
import tp.pr4.instructions.exceptions.InstructionExecutionException;
import tp.pr4.instructions.exceptions.WrongInstructionFormatException;
import tp.pr4.items.ItemContainer;

/**
 * Its execution request the robot to finish the simulation This Instruction works if the user writes QUIT or SALIR
 */
public class QuitInstruction implements Instruction
{
	
	private RobotEngine engine;
	private ItemContainer robotContainer;
	private NavigationModule navigation;
	
	
	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException 
	{
		StringTokenizer cmd= new StringTokenizer(cad, " ");
		if (cmd.countTokens()>0)
		{
			String cmdQuit = cmd.nextToken();
			if (cmdQuit.equalsIgnoreCase("QUIT") || cmdQuit.equalsIgnoreCase("SALIR"))
			{
				if (!cmd.hasMoreTokens())
				{
					return new QuitInstruction();
				}
			}
		}
		throw new WrongInstructionFormatException();
	}

	@Override
	public String getHelp() 
	{
		return "     QUIT|SALIR";
	}

	@Override
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) 
	{
		this.engine=engine;
		this.navigation=navigation;
		this.robotContainer=robotContainer;
	}

	@Override
	public void execute() throws InstructionExecutionException 
	{
		try
		{
			System.out.println("WALL·E says: I have communication problems. Bye bye");
			this.engine.printRobotState();
			this.engine.requestQuit();
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
