package tp.pr5.gui;

import tp.pr5.Controller;
import tp.pr5.RobotEngine;
import tp.pr5.Rotation;
import tp.pr5.instructions.DropInstruction;
import tp.pr5.instructions.MoveInstruction;
import tp.pr5.instructions.OperateInstruction;
import tp.pr5.instructions.PickInstruction;
import tp.pr5.instructions.QuitInstruction;
import tp.pr5.instructions.TurnInstruction;
import tp.pr5.instructions.UndoInstruction;

public class GUIController extends Controller
{

	/**
	 * Constructor that uses the model
	 * @param game - The reference to the model
	 */
	public GUIController(RobotEngine game) {
		super(game);
	}

	@Override
	public void startController() {
		this.game.requestStart();
	}
	
	/**
	 * Executes an OPERATE instruction
	 * @param itemName Name of the used item
	 */
	public void executeOperateAction(String itemName){
		game.communicateRobot(new OperateInstruction(itemName));
	}
	
	/**
	 * Executes a DROP instruction
	 * @param item Item dropped
	 */
	public void executeDropAction(java.lang.String item){
		game.communicateRobot(new DropInstruction(item));
	}

	/**
	 * Executes a PICK instruction
	 * @param item Item picked
	 */
	public void executePickAction(String item){
		game.communicateRobot(new PickInstruction(item));
	}
	
	/**
	 * Executes a QUIT instruction
	 */
	public void executeQuitAction(){
		game.communicateRobot(new QuitInstruction());
	}
	
	/**
	 * Executes a MOVE instruction
	 */
	public void executeMoveAction(){
		game.communicateRobot(new MoveInstruction());	
	}

	/**
	 * Executes a TURN instruction
	 * @param rotation
	 */
	public void executeTurnAction(Rotation rotation) {
		game.communicateRobot(new TurnInstruction(rotation));	
	}

	/**
	 * Executes a Undo instruction
	 */
	public void executeUndoAction() {
		game.communicateRobot(new UndoInstruction());
	}
}
