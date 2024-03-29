package tp.pr4.instructions;

import tp.pr4.*;
import tp.pr4.instructions.exceptions.*;
import tp.pr4.items.*;

public interface Instruction
{
	/**
	 * 
	 * @param cad
	 * @return
	 * @throws WrongInstructionFormatException
	 */
	Instruction parse(String cad) throws WrongInstructionFormatException;
	/**
	 * 
	 * @return
	 */
	String getHelp();
	
	/**
	 * 
	 * @param engine
	 * @param navigation
	 * @param robotContainer
	 */
	void configureContext(RobotEngine engine,
            NavigationModule navigation,
            ItemContainer robotContainer);
	
	/**
	 * 
	 * @throws InstructionExecutionException
	 */
	void execute()throws InstructionExecutionException;
	
	boolean isUndoable();
	
	void undo() throws InstructionExecutionException;
}
