package tp.pr4.instructions;
import java.util.StringTokenizer;

import tp.pr4.*;
import tp.pr4.instructions.exceptions.InstructionExecutionException;
import tp.pr4.instructions.exceptions.WrongInstructionFormatException;
import tp.pr4.items.*;
//Funciona
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
		if(item!=null)
		{
			
			if(!navigation.findItemAtCurrentPlace(this.id))
			{
				navigation.dropItemAtCurrentPlace(item);
				System.out.println("Great! I have dropped "+ this.id);
			}
			
			else
			{
				throw new InstructionExecutionException("WALL·E> The object " + this.id+ " already in this place");
				
			}
			
		} 
		else 
		{
			throw new InstructionExecutionException("You do not have any " + this.id+ ".");
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
