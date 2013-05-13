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
				//System.out.println("WALL·E says: I am happy! Now I have "+ this.id);
				//this.engine.getNavigationPanel().updateLog();
				/*if(this.engine.isSwingInteface())
				{
					this.engine.getNavigationPanel().updateLog();
				}*/
			} 
			else if(it!=null){
				String err="WALL·E says: I am stupid! I had already the object "+this.id;
				/*if(this.engine.isSwingInteface()){
					JOptionPane.showMessageDialog(null, err);
				}*/
				throw new InstructionExecutionException(err);
				//throw new InstructionExecutionException();
			} else {
				String err="WALL·E says: Ooops, this place has not the object "+ this.id;
				/*if(this.engine.isSwingInteface()){
					JOptionPane.showMessageDialog(null, err);
				}*/
				throw new InstructionExecutionException(err);
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
