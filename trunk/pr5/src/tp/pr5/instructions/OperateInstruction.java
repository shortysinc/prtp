package tp.pr5.instructions;

import java.util.StringTokenizer;

import tp.pr5.Constants;
import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.Item;
import tp.pr5.items.ItemContainer;

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
			if(inventoryOperate!=null){
				this.lastItem=(Item)inventoryOperate.clone();
				this.lastFuel = this.engine.getFuel();
				this.lastRecycledMaterial = this.engine.getRecycledMaterial();
				if (inventoryOperate.use(engine, navigation))
				{
					this.robotContainer.useItem(inventoryOperate);
				}
			} else {
				throw new InstructionExecutionException(Constants.MESSAGE_TRY_USE_ITEM_BUT_NOT_EXISTS.replace("{ID}", this.id));
			}
		} 
		catch(InstructionExecutionException e)
		{
			throw e;
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
