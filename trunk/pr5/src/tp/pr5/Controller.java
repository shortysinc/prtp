package tp.pr5;
//uni doc ok
//otros doc ok
//imp ok
import tp.pr5.items.InventoryObserver;

public abstract class Controller {

	protected RobotEngine game;
	
	/**
	 * Constructor that uses the model
	 * @param game - The reference to the model
	 */
	public Controller(RobotEngine game){
		this.game=game;
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
		this.game.addEngineObserver(gameObserver);
	}
	
	/**
	 * Registers a MapObserver to the model
	 * @param containerObserver - The observer that wants to be registered
	 */
	public void registerItemContainerObserver(InventoryObserver containerObserver){
		this.game.addItemContainerObserver(containerObserver);
	}
	
	/**
	 * Registers a PlayerObserver to the model
	 * @param playerObserver - The observer that wants to be registered
	 */
	public void registerRobotObserver(NavigationObserver playerObserver){
		this.game.addNavigationObserver(playerObserver);
	}
}
