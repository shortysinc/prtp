package tp.pr5.gui;

import tp.pr5.RobotEngine;
import tp.pr5.Rotation;
import tp.pr5.instructions.TurnInstruction;

public class GUIController extends tp.pr5.Controller
{

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
		
	}
	
	/**
	 * Executes a DROP instruction
	 * @param item Item dropped
	 */
	public void executeDropAction(java.lang.String item){
		
	}

	/**
	 * Executes a PICK instruction
	 * @param item Item picked
	 */
	public void executePickAction(String item){
		
	}
	
	/**
	 * Executes a QUIT instruction
	 */
	public void executeQuitAction(){
		
	}
	
	/**
	 * Executes a MOVE instruction
	 */
	public void executeMoveAction(){
		
	}

	public void executeTurnAction(Rotation rotation) {
		game.communicateRobot(new TurnInstruction(rotation));		
	}
}
