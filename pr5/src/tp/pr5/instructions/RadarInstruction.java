package tp.pr5.instructions;
//Funciona
//
import java.util.StringTokenizer;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.ItemContainer;

public class RadarInstruction implements Instruction
{
	
	private RobotEngine engine;
	private ItemContainer robotContainer;
	private NavigationModule navigation;

	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException {
		StringTokenizer cmd= new StringTokenizer(cad, " ");
		if (cmd.countTokens()>0)
		{
			String cmdRadar = cmd.nextToken();
			if (cmdRadar.equalsIgnoreCase("RADAR"))
			{
				if (!cmd.hasMoreTokens())
				{
					return new RadarInstruction();
				}
			}
		}
		throw new WrongInstructionFormatException();
	}

	@Override
	public String getHelp() {
		return "     RADAR";
	}

	@Override
	public void configureContext(RobotEngine engine, NavigationModule navigation, ItemContainer robotContainer) {
		this.engine=engine;
		this.navigation=navigation;
		this.robotContainer=robotContainer;
		
	}

	@Override
	public void execute() throws InstructionExecutionException {
		try{
			System.out.println(this.navigation.getCurrentPlace().toString());
			//this.engine.showFirstItems();
				   
		} catch (Exception e){
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
