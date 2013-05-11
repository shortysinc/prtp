package tp.pr5;
//uni doc ok
//otros doc no
//imp no
import java.util.Scanner;

import javax.print.attribute.standard.MediaSize.Engineering;
import javax.swing.JOptionPane;

import tp.pr5.gui.MainWindow;
import tp.pr5.gui.NavigationPanel;
import tp.pr5.gui.RobotPanel;
import tp.pr5.instructions.*;
import tp.pr5.instructions.exceptions.*;
import tp.pr5.items.*;

/**
 * This class represents the robot engine. It controls robot movements by processing the instructions provided by the controllers. The engine stops when the robot arrives at the space ship, runs out of fuel or receives a quit instruction.
 * The robot engine is also responsible for updating the fuel level and the recycled material according to the actions that the robot performs in the city.
 * The robot engine contains an inventory, where the robot stores the items that it collects from the city
 */
public class RobotEngine extends Observable<RobotEngineObserver> {

	private NavigationModule navigationModule;
	private int fuel;
	private int recycledMaterial;
	private ItemContainer inventary;
	private boolean quitRequest=false;
	//private Direction currentDirection;
	private Instruction lastInstruction;
	private Direction direction;
	

	/**
	 * Creates the robot engine in an initial place, facing an initial direction and with a city map. Initially the robot has not any items or recycled material but it has an initial amount of fuel (100).
	 * @param cityMap The city where the robot wanders
	 * @param initialPlace The place where the robot starts
	 * @param direction The initial direction where the robot is facing.
	 */
	public RobotEngine(City cityMap, Place initialPlace,Direction direction)
	{

		this.fuel				= 100;
		this.recycledMaterial	= 0;
		this.inventary			= new ItemContainer();
		this.direction			= direction;
		this.navigationModule	= new NavigationModule(cityMap, initialPlace);
		//this.navigationModule.initHeading(direction);
		
		/**
		 	this.fuel = 100;
			this.recycledMaterial = 0;
			this.items = new ItemContainer();
			this.quit = false;
			this.navModule = new NavigationModule(cityMap, initialPlace);
			this.navModule.initHeading(dir);
		 */
	}
	
	/**
	 * It executes an instruction. The instruction must be configured with the context before executing it.
	 * It controls the end of the simulation. If the execution of the instruction throws an exception,
	 * then the corresponding message is printed
	 * @param c - The instruction to be executed
	 */
	public void communicateRobot(Instruction c)
	{
		//
		
		c.configureContext(this, this.navigationModule, this.inventary);
		try 
		{
			c.execute();
			if(c.isUndoable()){
				this.lastInstruction= c;
			}
			this.checkFuel();
		}
		catch (InstructionExecutionException e) 
		{
			this.emitRaiseError(e.getMessage());
		}
	}
	
	/**
	 * Checks if the simulation is finished
	 * @return true if the game has finished
	 */
	public boolean isOver(){
		return quitRequest;
	}
	
	/**
	 * Increases the amount of recycled material of the robot
	 * @param weight Amount of recycled material
	 */
	public void addRecycledMaterial(int weight)
	{
		this.recycledMaterial+=weight;
		this.emitRobotUpdate();
	}
	
	/**
	 * Adds an amount of fuel to the robot (it can be negative)
	 * @param fuel  Amount of fuel added to the robot
	 */
	public void addFuel(int fuel)
	{
		this.fuel+=fuel;
		this.emitRobotUpdate();
	}
	
	/**
	 * Returns the current fuel level of the robot. This method is mandatory FOR TESTING PURPOSES
	 * @return The current fuel level of the robot
	 */
	public int getFuel()
	{
		return this.fuel;
	}
	
	
	
	
	/**
	 * Returns the current weight of recycled material that the robot carries. This method is mandatory FOR TESTING PURPOSES
	 * @return The current fuel level of the robot
	 */
	public int getRecycledMaterial(){
		return this.recycledMaterial;
	}
	
	/**
	 * Requests the engine to inform the observers that the simulation starts
	 */
	public void requestStart(){
		this.emitRobotUpdate();
		this.navigationModule.initHeading(direction);
	}
	
	/**
	 * Requests the end of the simulation
	 */
	public void requestQuit()
	{
		this.quitRequest=true;
		this.emitCommunicationCompleted();
	}
	
	/**
	 * Prints the information about all possible instructions
	 */
	public void requestHelp()
	{
		this.emitComunicateHelp();
	}
	
	/**
	 * Requests the engine to inform that an error has been raised
	 * @param msg
	 */
	public void requestError(String msg){
		this.emitRaiseError(msg);
	}
	
	/**
	 * Request the engine to say something
	 * @param message
	 */
	public void saySomething(String message){
		this.emitRobotSays(message);
	}
	
	/**
	 * Registers an EngineObserver to the model
	 * @param observer - The observer that wants to be registered
	 */
	protected void addEngineObserver(RobotEngineObserver observer){
		this.addObserver(observer);
	}
	
	/**
	 * Register a NavigationObserver to the model
	 * @param observer - The observer that wants to be registered
	 */
	protected void addNavigationObserver(NavigationObserver robotObserver){
		this.navigationModule.addObserver(robotObserver);
	}
	
	/**
	 * Registers an ItemContainerObserver to the model
	 * @param observer - The observer that wants to be registered
	 */
	protected void addItemContainerObserver(InventoryObserver c){
		this.inventary.addObserver(c);
	}
	
	private void emitComunicateHelp(){
		for (RobotEngineObserver robotEngineObserver : this.observers) {
			robotEngineObserver.communicationHelp(Interpreter.interpreterHelp());
		}
	}
	
	private void emitRobotUpdate(){
		for (RobotEngineObserver robotEngineObserver : this.observers) {
			robotEngineObserver.robotUpdate(this.fuel, this.recycledMaterial);
		}
	}
	
	private void emitRaiseError(String msg){
		for (RobotEngineObserver robotEngineObserver : this.observers) {
			robotEngineObserver.raiseError(msg);
		}
	}
	
	private void emitEngineOff(boolean atShip){
		for (RobotEngineObserver robotEngineObserver : this.observers) {
			robotEngineObserver.engineOff(atShip);
		}
	}
	
	private void emitCommunicationCompleted(){
		for (RobotEngineObserver robotEngineObserver : this.observers) {
			robotEngineObserver.communicationCompleted();
		}
	}
	
	private void emitRobotSays(String message){
		for (RobotEngineObserver robotEngineObserver : this.observers) {
			robotEngineObserver.robotSays(message);
		}
	}
	
	public void checkFuel (){
		if (this.fuel<=0){
			this.quitRequest=true;
		}
	}
	
	public void undo() throws InstructionExecutionException{
		this.lastInstruction.undo();
		this.lastInstruction = null;
	}
}
