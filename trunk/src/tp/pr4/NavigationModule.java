package tp.pr4;

import tp.pr4.gui.NavigationPanel;
import tp.pr4.instructions.exceptions.InstructionExecutionException;
import tp.pr4.items.*;

public class NavigationModule {
	
	private City aCity;
	private Place currentPlace;
	private Direction currentDirection;
	private RobotEngine robot;
	private NavigationPanel navigationPanel;
	
	/**
	 * Navigation module constructor. It needs the city map and the initial place
	 * @param aCity - A city map
	 * @param initialPlace - An initial place for the robot
	 */
	public NavigationModule(City aCity, Place initialPlace)
	{
		this.aCity=aCity;
		this.currentPlace=initialPlace;
	}
	
	/**
	 * Checks if the robot has arrived at a spaceship
	 * @return true if an only if the current place is the spaceship
	 */
	public boolean atSpaceship()
	{
		return this.currentPlace.isSpaceship();
	}
	
	
	/**
	 * Updates the current direction of the robot according to the rotation
	 * @param rotation - left or right
	 */
	public void rotate(Rotation rotation)
	{
		this.currentDirection=this.currentDirection.rotate(rotation);
	}
	
	/**
	 * The method tries to move the robot following the current direction. 
	 * If the movement is not possible because there is no street, 
	 * or there is a street which is closed, then it throws an exception. 
	 * Otherwise the current place is updated according to the movement
	 * @throws InstructionExecutionException - An exception with a message about the encountered problem
	 */
	public void move() throws InstructionExecutionException
	{
		
		try 
		{
			Street street=this.getHeadingStreet();
			if(street==null){
				throw new InstructionExecutionException("WALL·E says: There is no street in direction "+this.getCurrentHeading());
			}
			
			Place nextPlace=street.nextPlace(this.currentPlace);
			if (street.isOpen()){
				this.currentPlace=nextPlace;
				if(isSwing()){
					this.navigationPanel.updateCell(currentDirection, nextPlace);
				}
				
			}else{
				throw new InstructionExecutionException("WALL·E says: Arrggg, there is a street but it is closed!");
			}
		}catch (Exception e){
			throw e;
		}
	}
	
	/**
	 * Tries to pick an item characterized by a given identifier from the current place. 
	 * If the action was completed the item is removed from the current place.
	 * @param id - The identifier of the item
	 * @return The item of identifier id if it exists in the place. Otherwise the method returns null
	 */
	public Item pickItemFromCurrentPlace(String id)
	{
		return this.currentPlace.pickItem(id);
	}
	
	/**
	 * Drop an item in the current place. 
	 * It assumes that there is no other item with the same name/id there. 
	 * Otherwise, the behaviour is undefined.
	 * @param it - The name of the item to be dropped.
	 */
	public void dropItemAtCurrentPlace(Item it)
	{
		this.currentPlace.addItem(it);
	}
	
	/**
	 * Checks if there is an item with a given id in this place
	 * @param id - Identifier of the item we are looking for
	 * @return true if and only if an item with this id is in the current place
	 */
	public boolean findItemAtCurrentPlace(String id)
	{
		return this.currentPlace.existItem(id);
		
	}
	
	
	/**
	 * Initializes the current heading according to the parameter
	 * @param heading - New direction for the robot
	 */
	public void initHeading(Direction heading)
	{
		this.currentDirection=heading;
	}
	
	/**
	 * Prints the information (description + inventory) of the current place
	 */
	public void scanCurrentPlace()
	{
		System.out.println(this.getCurrentPlace().toString());
		System.out.println("The place contains these objects: ");
		System.out.println(this.getCurrentPlace().getItems().toString());
	}
	
	/**
	 * Returns the street opposite the robot
	 * @return Returns the street opposite the robot
	 */
	public Street getHeadingStreet()
	{
		return this.aCity.lookForStreet(this.currentPlace, this.currentDirection);
	}
	
	
	/**
	 * Returns the robot heading
	 * @return The direction where the robot is facing to.
	 */
	public Direction getCurrentHeading()
	{
		return this.currentDirection;
		
	}
	
	public void setCurrentPlace(Place currentPlace) {
		this.currentPlace = currentPlace;
	}

	public Place getCurrentPlace()
	{
		return this.currentPlace;
		
	}
	
	public void setNavigationPanel(NavigationPanel navPanel){
		this.navigationPanel=navPanel;
		this.navigationPanel.setInitialPlace(this.currentPlace,this.currentDirection);
	}
	
	private boolean isSwing(){
		return this.navigationPanel!=null;
	}
	
}

