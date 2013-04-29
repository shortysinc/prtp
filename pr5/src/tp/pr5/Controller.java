package tp.pr5;

import tp.pr5.items.InventoryObserver;

public abstract class Controller {

	/**
	 * Constructor that uses the model
	 * @param game - The reference to the model
	 */
	public Controller(RobotEngine game){
		
	}
	
	/**
	 * Abstract method that runs the game. This method is different whether the application runs the game on console or on a Swing Window
	 */
	public abstract void startController();
	
	/**
	 * Registers a GameObserver to the model
	 * @param gameObserver - The observer that wants to be registered
	 */
	public void registerEngineObserver(RobotEngineObserver gameObserver){
		
	}
	
	/**
	 * Registers a MapObserver to the model
	 * @param containerObserver - The observer that wants to be registered
	 */
	public void registerItemContainerObserver(InventoryObserver containerObserver){
		
	}
	
	/**
	 * Registers a PlayerObserver to the model
	 * @param playerObserver - The observer that wants to be registered
	 */
	public void registerRobotObserver(NavigationObserver playerObserver){
		
	}
}
