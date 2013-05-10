package tp.pr5.gui;

import tp.pr5.RobotEngine;

public class GUIController extends tp.pr5.Controller
{

	public GUIController(RobotEngine game) {
		super(game);
	}

	@Override
	public void startController() {
		this.game.requestStart();
	}
	
	//metodo por cada instruccion que se llamara desde la vista

}
