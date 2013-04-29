package tp.pr4.instructions;

import java.util.StringTokenizer;

import tp.pr4.NavigationModule;
import tp.pr4.RobotEngine;
import tp.pr4.Rotation;
import tp.pr4.instructions.exceptions.InstructionExecutionException;
import tp.pr4.instructions.exceptions.WrongInstructionFormatException;
import tp.pr4.items.ItemContainer;
//Por arreglar
public class TurnInstruction implements Instruction
{

	private Rotation rotation;
	private RobotEngine engine;
	private ItemContainer robotContainer;
	private NavigationModule navigation;
	
	public TurnInstruction(){
		
	}
	
	public TurnInstruction(Rotation rotation)
	{
		this.rotation=rotation;
	}
	
	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException {
		StringTokenizer cmd= new StringTokenizer(cad, " ");
		
		if(cmd.countTokens()>0){
			String cmdTurn=cmd.nextToken();
			if(cmdTurn.equalsIgnoreCase("TURN") || cmdTurn.equalsIgnoreCase("GIRAR")){
				if(cmd.hasMoreTokens()){
					String rot=cmd.nextToken().toUpperCase();
					if(!cmd.hasMoreTokens())
					{
						if(rot.equalsIgnoreCase("LEFT"))
						{
							return new TurnInstruction(Rotation.LEFT);
						} 
						else if(rot.equalsIgnoreCase("RIGHT")) 
						{
							return new TurnInstruction(Rotation.RIGHT);
						}
						
					} 
				}
			}
		}
		throw new WrongInstructionFormatException();
	}
	/*
	 if(cmd.hasMoreTokens()){
						
						String cmdRot=cmd.nextToken().toUpperCase();
						if(!cmd.hasMoreTokens())
						{
							if(cmdRot.equals("LEFT"))
							{
								rotation=Rotation.LEFT;
							} 
							else if(cmdRot.equals("RIGHT")) 
							{
								rotation=Rotation.RIGHT;
							}
						}
					}
	 */
	@Override
	public String getHelp() 
	{
		return "     TURN|GIRAR < LEFT|RIGHT >";
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
	public void execute() throws InstructionExecutionException {
		try
		{
			navigation.rotate(this.rotation);
			if(this.engine.isSwingInteface()){
				engine.getNavigationPanel().updateDirection(engine.getCurrentDirection());
			}
			engine.addFuel(-5);
			
			engine.showDirection();
			engine.printRobotState();
			engine.outOfFuel();
		} 
		catch (Exception e)
		{
			throw new InstructionExecutionException();
		}
	}
	
	@Override
	public void undo() throws InstructionExecutionException {
		//segun las variables de la ejecucion, deshacer
		
		try
		{
			engine.addFuel(10);
			this.engine.communicateRobot(new TurnInstruction(this.rotation.opposite()));
			
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
