package tp.pr5;
//100%test
import java.util.Scanner;

import javax.swing.JOptionPane;

import tp.pr5.gui.MainWindow;
import tp.pr5.gui.NavigationPanel;
import tp.pr5.gui.RobotPanel;
import tp.pr5.instructions.*;
import tp.pr5.instructions.exceptions.*;
import tp.pr5.items.*;

/**
 * This class represents the robot engine. It controls robot movements by processing the instructions introduced with the keyboard. The engine stops when the robot arrives at the spaceship or receives a quit instruction.
 */
public class RobotEngine extends Observable<RobotEngineObserver> {

	private NavigationModule navigationModule;
	private int fuel;
	private int recycledMaterial;
	private ItemContainer inventary;
	private boolean quitRequest=false;
	//private Direction currentDirection;
	private MainWindow mainWindow;
	private NavigationPanel navigationPanel;
	private RobotPanel robotPanel;
	private Instruction lastInstruction;
	

	/**
	 * Creates the robot engine in an initial place, facing an initial direction and with a city map. Initially the robot has not any items or recycled material but it has an initial amount of fuel (50).
	 * @param initialPlace
	 * @param direction
	 * @param cityMap
	 */
	public RobotEngine(City cityMap, Place initialPlace,Direction direction)
	{

		this.fuel				= 100;
		this.recycledMaterial	= 0;
		this.inventary			= new ItemContainer();
		this.navigationModule	= new NavigationModule(cityMap, initialPlace);
		//this.currentDirection 	= direction;
		this.navigationModule.initHeading(direction);
		
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
	 * Adds an amount of fuel to the robot (it can be negative)
	 * @param fuel  Amount of fuel added to the robot
	 */
	public void addFuel(int fuel)
	{
		this.fuel+=fuel;
		for (RobotEngineObserver robotEngineObserver : this.observers) {
			robotEngineObserver.robotUpdate(fuel, recycledMaterial);
		}
		if(this.isSwingInteface()){
			this.robotPanel.updateFuel();
		}
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
	 * Increases the amount of recycled material of the robot
	 * @param weight Amount of recycled material
	 */
	public void addRecycledMaterial(int weight)
	{
		this.recycledMaterial+=weight;
		for (RobotEngineObserver robotEngineObserver : this.observers) {
			robotEngineObserver.robotUpdate(fuel, weight);
		}
		if(this.robotPanel!=null){
			this.robotPanel.updateRecycledMaterial();
		}
	}
	
	/**
	 * Returns the current weight of recycled material that the robot carries. This method is mandatory FOR TESTING PURPOSES
	 * @return The current fuel level of the robot
	 */
	public int getRecycledMaterial(){
		return this.recycledMaterial;
	}
	
	/**
	 * It starts the robot engine.
	 */
	public void startEngine()
	{
		showPlace();
		showFirstItems();
		showDirection(); 
		printRobotState();
		
		//
		
		Scanner sc= new Scanner(System.in);

		do
		{
			showPrompt();
			Instruction instruction;
			try 
			{
				instruction = Interpreter.generateInstruction(sc.nextLine());
				this.communicateRobot(instruction);
			} 
			catch (WrongInstructionFormatException e) 
			{
				System.out.println("WALL·E says: I do not understand. Please repeat");
			}
			
		} while(!this.quitRequest && this.fuel>0 && !this.navigationModule.atSpaceship());
		sc.close();
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
		}
		catch (InstructionExecutionException e) 
		{
			//System.out.println("me has pasado cosas mal");
		}
	}
	
	public Direction getCurrentDirection() 
	{
		return this.navigationModule.getCurrentHeading();
	}

	/**
	 * Show the WALL-E prompt
	 */
	void showPrompt()
	{
		System.out.print("WALL·E> ");
	}
	
	private int fuelCalc(int num)
	{
		if (num < 0)
			return num=0;
		return num;
	}
	
	
		
	/**
	 * Show the current Direction
	 */
	public void showDirection()
	{
		System.out.println("WALL·E is looking at direction "+  this.navigationModule.getCurrentHeading().toString());
		//System.out.println();
	}
	
	/**
	 * Show the current Place
	 */
	public void showPlace()
	{
		System.out.println(this.navigationModule.getCurrentPlace().toString());
	}
	
	public void showCurrentItems() 
	{
		if(this.navigationModule.getCurrentPlace().getItems().numberOfItems()>0){
			System.out.println("The place contains these objects:");
			System.out.println(this.navigationModule.getCurrentPlace().getItems().toString()+"\n");
		} else {
			System.out.println("The place is empty. There are no objects to pick"+"\n");
		}
	}
	public void showFirstItems() 
	{
		if(this.navigationModule.getCurrentPlace().getItems().numberOfItems()>0){
			System.out.println("The place contains these objects:");
			System.out.println(this.navigationModule.getCurrentPlace().getItems().toString());
		} else {
			System.out.println("The place is empty. There are no objects to pick"+"\n");
		}
	}
	
	/**
	 * Requests the end of the simulation
	 */
	public void requestQuit()
	{
		this.quitRequest=true;
	}
	
	/**
	 * Prints the information about all possible instructions
	 */
	public void requestHelp()
	{
		for (RobotEngineObserver robotEngineObserver : this.observers) {
			robotEngineObserver.communicationHelp(Interpreter.interpreterHelp());
		}
	}
	
	/**
	 * Prints the state of the robot
	 */
	
	public void printRobotState()
	{
		System.out.println("      * My power is " + fuelCalc(this.getFuel()));
		System.out.println("      * My reclycled material is " + this.recycledMaterial);
	}
	
	public void outOfFuel (){
		if (this.fuel<=0){
			if(this.isSwingInteface()){
				int exit= JOptionPane.showConfirmDialog(null,"WALL·E says: I run out of fuel", 
						"Confirmation", JOptionPane.CLOSED_OPTION);
				if (exit==JOptionPane.YES_OPTION)
				{
					QuitInstruction quit=new QuitInstruction();
					this.communicateRobot(quit);
					//this.closeGUI();
				}
			}
			System.out.println("WALL·E says: I run out of fuel. I cannot move. Shutting down...");
			this.quitRequest=true;
		}
	}
	
	public void atSpaceShip(){
		if(this.isSwingInteface()){
			int exit= JOptionPane.showConfirmDialog(null,"WALL·E says: I am at my spaceship. Bye bye", 
					"Confirmation", JOptionPane.CLOSED_OPTION);
			if (exit==JOptionPane.YES_OPTION)
			{
				QuitInstruction quit=new QuitInstruction();
				this.communicateRobot(quit);
				//this.closeGUI();
			}
		}
	}
	
	public void setMainWindow(MainWindow mainWindow){
		this.mainWindow=mainWindow;
	}
	
	public void closeGUI(){
		this.mainWindow.dispose();
	}
	
	public boolean isSwingInteface(){
		return this.mainWindow!=null;
	}
	
	public void setNavigationPanel(NavigationPanel navPanel){
		this.navigationPanel = navPanel;
		this.navigationModule.setNavigationPanel(navPanel);
	}
	
	public void setRobotPanel(RobotPanel robotPanel){
		this.robotPanel = robotPanel;
		this.inventary.setRobotPanel(robotPanel);
	}
	
	public RobotPanel getRobotPanel(){
		return this.robotPanel;
	}
	
	public NavigationPanel getNavigationPanel(){
		return this.navigationPanel;
	}
	
	public void undo() throws InstructionExecutionException{
		this.lastInstruction.undo();
		this.lastInstruction = null;
	}
}
