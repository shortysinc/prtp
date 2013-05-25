package tp.pr5.instructions;

import java.util.StringTokenizer;

import javax.swing.JOptionPane;

import tp.pr5.*;
import tp.pr5.instructions.exceptions.*;
import tp.pr5.items.*;


public class PickInstruction implements Instruction
{

	private String id;
	private RobotEngine engine;
	private ItemContainer robotContainer;
	private NavigationModule navigation;
	
	/**
	 * It builds a command without the context of execution. 
	 * Therefore, if the command is executed an exception should be raised.
	 */
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
			//Item it=navigation.getCurrentPlace().pickItem(this.id);
			Item it=navigation.pickItemFromCurrentPlace(this.id);
			
			if(it!=null && this.robotContainer.addItem(it))
			{
				this.engine.saySomething(Constants.MESSAGE_PICK_OK.replace("{ID}", this.id));
			} 
			else if(it!=null){
				throw new InstructionExecutionException(Constants.MESSAGE_ITEM_IS_IN_INVENTORY.replace("{ID}", this.id));
			} else {
				throw new InstructionExecutionException(Constants.MESSAGE_PICK_ERROR1.replace("{ID}", this.id));
			}
		}
		catch (InstructionExecutionException e) {
			throw e;
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
