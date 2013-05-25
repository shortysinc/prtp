package tp.pr5.instructions;
import java.util.StringTokenizer;

import tp.pr5.*;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.*;

public class DropInstruction implements Instruction
{
	private String id;
	private RobotEngine engine;
	private ItemContainer robotContainer;
	private NavigationModule navigation;
	
	public DropInstruction(){
		
	}
	
	public DropInstruction(String id){
		this.id=id;
	}

	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException 
	{
		StringTokenizer cmd= new StringTokenizer(cad, " ");
		
		if(cmd.countTokens()>0){
			String drop=cmd.nextToken();
			if(drop.equalsIgnoreCase("DROP") || drop.equalsIgnoreCase("SOLTAR")){
				if(cmd.hasMoreTokens()){
					String id=cmd.nextToken();
					if(!cmd.hasMoreTokens()){
						return new DropInstruction(id);
					} 
				}
			}
		}
		throw new WrongInstructionFormatException();
	}

	@Override
	public String getHelp() 
	{

		return "     DROP|SOLTAR";
	}

	@Override
	public void configureContext(RobotEngine engine, NavigationModule navigation, ItemContainer robotContainer) 
	{
		this.engine=engine;
		this.navigation=navigation;
		this.robotContainer=robotContainer;
	}

	@Override
	public void execute() throws InstructionExecutionException 
	{
		Item item=this.robotContainer.pickItem(this.id);
		if(item!=null){
			
			if(!navigation.findItemAtCurrentPlace(this.id)){
				
				navigation.dropItemAtCurrentPlace(item);
				this.engine.saySomething(Constants.MESSAGE_DROP_OK.replace("{ID}",item.getId()));
				
			} else {
				throw new InstructionExecutionException(Constants.MESSAGE_DROP_ERROR2.replace("{ID}", this.id));
			}
			
		} else {
			throw new InstructionExecutionException(Constants.MESSAGE_DROP_ERROR.replace("{ID}", this.id));
		}
		
	}

	@Override
	public void undo() throws InstructionExecutionException {
		//segun las variables de la ejecucion, deshacer
		try
		{
			this.engine.communicateRobot(new PickInstruction(this.id));
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
