package tp.pr4.instructions;

import java.util.StringTokenizer;

import tp.pr4.NavigationModule;
import tp.pr4.RobotEngine;
import tp.pr4.instructions.exceptions.InstructionExecutionException;
import tp.pr4.instructions.exceptions.WrongInstructionFormatException;
import tp.pr4.items.Item;
import tp.pr4.items.ItemContainer;

public class OperateInstruction implements Instruction
{
	private String id;
	private RobotEngine engine;
	private ItemContainer robotContainer;
	private NavigationModule navigation;
	private Item lastItem;
	private int lastFuel;
	private int lastRecycledMaterial;

	public OperateInstruction(){
		
	}
	
	public OperateInstruction(String id){
		this.id=id;
	}
	
	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException {
		
		StringTokenizer cmd= new StringTokenizer(cad, " ");
		
		if(cmd.countTokens()>0){
			String cmdOperate=cmd.nextToken();
			if(cmdOperate.equalsIgnoreCase("OPERATE") || cmdOperate.equalsIgnoreCase("OPERAR")){
				if(cmd.hasMoreTokens()){
					String id=cmd.nextToken();
					if(!cmd.hasMoreTokens()){
						return new OperateInstruction(id);
					} 
				}
			}
		}
		throw new WrongInstructionFormatException();
		
	}

	@Override
	public String getHelp() {
		return "     OPERATE|OPERAR";
	}

	@Override
	public void configureContext(RobotEngine engine, NavigationModule navigation, ItemContainer robotContainer) {
		this.engine=engine;
		this.navigation=navigation;
		this.robotContainer=robotContainer;
	}

	@Override
	public void execute() throws InstructionExecutionException {
		
		Item inventoryOperate=this.robotContainer.getItem(this.id);
		try{
			this.lastItem=(Item)inventoryOperate.clone();
			this.lastFuel = this.engine.getFuel();
			this.lastRecycledMaterial = this.engine.getRecycledMaterial();
			if (inventoryOperate.use(engine, navigation))
			{
				
				if (!inventoryOperate.canBeUsed())
				{
					this.robotContainer.pickItem(this.id);
					
					System.out.println("WALL·E says: What a pity! I have no more " + id +" in my inventory");
				}
				this.robotContainer.updateItems();
			}
			else{
				throw new InstructionExecutionException("WALL·E says: I have not such object " + id);
			}
			engine.outOfFuel();
		} 
		catch(Exception e)
		{
			throw new InstructionExecutionException();
		}
		
	}
	
	@Override
	public void undo() throws InstructionExecutionException {
		//segun las variables de la ejecucion, deshacer
		try
		{
			this.robotContainer.pickItem(id);
			this.robotContainer.addItem(lastItem);
			this.engine.addFuel(lastFuel-this.engine.getFuel());
			this.engine.addRecycledMaterial(lastRecycledMaterial-this.engine.getRecycledMaterial());
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
