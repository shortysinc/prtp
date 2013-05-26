package tp.pr5.instructions;
//Funciona
import java.util.StringTokenizer;

import tp.pr5.Constants;
import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.ItemContainer;

public class ScanInstruction implements Instruction
{

	private String id;
	private RobotEngine engine;
	private ItemContainer robotContainer;
	private NavigationModule navigation;
	
	
	/**
	 * Default constructor
	 */
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
					this.robotContainer.requestScanCollection();
					//System.out.println("WALL·E says: I am carrying the following items");
					//System.out.print(this.robotContainer.toString()+ "\n");
				}
				else
				{
					if(this.robotContainer.containsItem(this.id))
					{
						//System.out.println("WALL·E says: "+scanId.getId()+": " +scanId.toString());
						this.robotContainer.requestScanItem(this.id);
					}
					else 
					{
						//System.out.println("WALL·E says: I have not such object");
						throw new InstructionExecutionException(Constants.MESSAGE_NO_ITEM.replace("{ID}", this.id));
					}
				}
			} 
			else 
			{
				throw new InstructionExecutionException(Constants.MESSAGE_NO_ITEMS);
				
			}
		} catch(InstructionExecutionException e){
			throw e;
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
