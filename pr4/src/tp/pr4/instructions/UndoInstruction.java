package tp.pr4.instructions;

import java.security.acl.LastOwnerException;

import tp.pr4.NavigationModule;
import tp.pr4.RobotEngine;
import tp.pr4.instructions.exceptions.InstructionExecutionException;
import tp.pr4.instructions.exceptions.WrongInstructionFormatException;
import tp.pr4.items.ItemContainer;

public class UndoInstruction implements Instruction {

	private RobotEngine engine;
	private ItemContainer robotContainer;
	private NavigationModule navigation;
	
	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getHelp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		this.engine=engine;
		this.navigation=navigation;
		this.robotContainer=robotContainer;
	}

	@Override
	public void execute() throws InstructionExecutionException {
		
		try
		{
			this.engine.undo();
		}
		catch (Exception e)
		{
			throw new InstructionExecutionException();
		}

	}

	@Override
	public boolean isUndoable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub

	}

}
