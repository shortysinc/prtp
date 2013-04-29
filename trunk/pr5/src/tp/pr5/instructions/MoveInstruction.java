package tp.pr5.instructions;
//No funciona
import java.util.StringTokenizer;

import tp.pr5.*;
import tp.pr5.instructions.exceptions.*;
import tp.pr5.items.ItemContainer;

public class MoveInstruction implements Instruction 
{
	
	private RobotEngine engine;
	private ItemContainer robotContainer;
	private NavigationModule navigation;
	private static String LINE_SEPARATOR = System.getProperty("line.separator");
	
	
	
	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException 
	{
		StringTokenizer cmd= new StringTokenizer(cad, " ");
		if (cmd.countTokens()>0)
		{
			String cmdHelp = cmd.nextToken();
			if (cmdHelp.equalsIgnoreCase("MOVE") || cmdHelp.equalsIgnoreCase("MOVER"))
			{
				if (!cmd.hasMoreTokens())
					return new MoveInstruction();
			}
		}
		throw new WrongInstructionFormatException();
	}

	@Override
	public void execute() throws InstructionExecutionException 
	{
		try
		{
			this.navigation.move();
			this.engine.addFuel(-5);
			System.out.println("WALL·E says: Moving in direction " + this.navigation.getCurrentHeading());
			
			engine.showPlace();
			engine.showCurrentItems();
			engine.printRobotState();
			
			if(this.navigation.atSpaceship()){
				this.engine.atSpaceShip();
				this.engine.requestQuit();
				System.out.println("WALL·E says: I am at my spaceship. Bye bye");
			} else {
				engine.outOfFuel();
			}
			
		}catch (Exception e){
			throw new InstructionExecutionException();
		}
	}
	
	@Override
	public String getHelp() 
	{
		return "     MOVE|MOVER";
	}

	@Override
	public void configureContext(RobotEngine engine, NavigationModule navigation, ItemContainer robotContainer) 
	{
		this.engine=engine;
		this.navigation=navigation;
		this.robotContainer=robotContainer;
	}

	@Override
	public void undo() throws InstructionExecutionException {
		//segun las variables de la ejecucion, deshacer
		try
		{
			
			this.engine.addFuel(10);
			this.navigation.rotate(Rotation.LEFT);
			this.navigation.rotate(Rotation.LEFT);
			this.engine.getNavigationPanel().closeCell();
			this.engine.communicateRobot(new MoveInstruction());
			this.navigation.rotate(Rotation.LEFT);
			this.navigation.rotate(Rotation.LEFT);
		} 
		catch (Exception e)
		{
			throw new InstructionExecutionException();
		}
		
	}

	@Override
	public boolean isUndoable() {
		return true;
	}
	

}
