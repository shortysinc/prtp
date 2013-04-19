package tp.pr4.instructions;

import java.util.StringTokenizer;

import tp.pr4.*;
import tp.pr4.instructions.exceptions.*;
import tp.pr4.items.*;


public class PickInstruction implements Instruction
{

	private String id;
	private RobotEngine engine;
	private ItemContainer robotContainer;
	private NavigationModule navigation;
	
	public PickInstruction(){
		
	}
	
	public PickInstruction(String id){
		this.id=id;
	}
	
	
	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException {
		
		StringTokenizer cmd= new StringTokenizer(cad, " ");
		
		if(cmd.countTokens()>0){
			String cmdPick=cmd.nextToken();
			if(cmdPick.equalsIgnoreCase("PICK") || cmdPick.equalsIgnoreCase("COGER")){
				if(cmd.hasMoreTokens()){
					String id=cmd.nextToken();
					if(!cmd.hasMoreTokens()){
						return new PickInstruction(id);
					}
				}
			}
		}
		throw new WrongInstructionFormatException();
	}

	@Override
	public String getHelp() {
		return "     PICK|COGER";
	}

	@Override
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		this.engine=engine;
		this.navigation=navigation;
		this.robotContainer=robotContainer;
		
	}

	@Override
	public void execute() throws InstructionExecutionException 
	{

		//Item inventoryItem=this.robotContainer.getItem(this.id);
		
		try
		{
			Item it=navigation.getCurrentPlace().pickItem(this.id);
			if(it!=null && this.robotContainer.addItem(it))
			{
				System.out.println("WALL·E says: I am happy! Now I have "+ this.id);
				this.engine.getNavigationPanel().updateLog();
			} 
			else if(it!=null){
				throw new InstructionExecutionException("WALL·E says: I am stupid! I had already the object "+this.id);
				//throw new InstructionExecutionException();
			} else {
				throw new InstructionExecutionException("WALL·E says: Ooops, this place has not the object "+ this.id);
				//throw new InstructionExecutionException();
			}
		}
		catch (Exception e) {
			throw new InstructionExecutionException();
		}
		
	}
	
	@Override
	public void undo() throws InstructionExecutionException {
		//segun las variables de la ejecucion, deshacer
		try
		{
			this.engine.communicateRobot(new DropInstruction(this.id));
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
