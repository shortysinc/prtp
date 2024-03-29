package tp.pr5.instructions;

import java.util.StringTokenizer;

import tp.pr5.Constants;
import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.Rotation;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.ItemContainer;

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
			engine.addFuel(-Constants.LOST_FUEL);
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
			engine.addFuel(Constants.LOST_FUEL*2);
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
