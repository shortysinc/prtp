package tp.pr4.instructions;
//Funciona
import java.util.StringTokenizer;

import tp.pr4.NavigationModule;
import tp.pr4.RobotEngine;
import tp.pr4.instructions.exceptions.InstructionExecutionException;
import tp.pr4.instructions.exceptions.WrongInstructionFormatException;
import tp.pr4.items.Item;
import tp.pr4.items.ItemContainer;

public class ScanInstruction implements Instruction
{

	private String id;
	private RobotEngine engine;
	private ItemContainer robotContainer;
	private NavigationModule navigation;
	
	public ScanInstruction(){
		
	}
	
	public ScanInstruction(String id){
		this.id=id;
	}
	
	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException {
		StringTokenizer cmd= new StringTokenizer(cad, " ");
		
		if(cmd.countTokens()>0){
			String cmdScan=cmd.nextToken();
			if(cmdScan.equalsIgnoreCase("SCAN") || cmdScan.equalsIgnoreCase("ESCANEAR")){
				if(cmd.hasMoreTokens()){
					String id=cmd.nextToken();
					if(!cmd.hasMoreTokens()){
						return new ScanInstruction(id);
					} 
				} else {
					return new ScanInstruction();
				}
			}
		}
		throw new WrongInstructionFormatException();
	}

	@Override
	public String getHelp() {
		return "     SCAN|ESCANEAR";
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

		try{
			if(this.robotContainer.numberOfItems()>0)
			{
				if(this.id==null)
				{
					System.out.println("WALL·E says: I am carrying the following items");
					System.out.print(this.robotContainer.toString()+ "\n");
				}
				else
				{
					Item scanId=this.robotContainer.getItem(this.id);
					if(scanId!=null)
					{
						System.out.println("WALL·E says: "+scanId.getId()+": " +scanId.toString());
						
					} 
					else 
					{
						System.out.println("WALL·E says: I have not such object");
						
					}
				}
			} 
			else 
			{
				throw new InstructionExecutionException("WALL·E says: My inventory is empty");
				
			}
		} catch(Exception e){
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
