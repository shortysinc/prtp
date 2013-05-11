package tp.pr5;
//uni doc ok
//otros doc ok
//imp no

import tp.pr5.gui.NavigationPanel;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.items.Item;

/**
 * This class is in charge of the robot navigation features. It contains the city where the robot looks for garbage, the current place where the robot is, and the current direction of the robot. It contains methods to handle the different robot movements and to pick and drop items at the current place.
 * @author danidhsm
 */
public class NavigationModule extends Observable<NavigationObserver>
{
	
	private City aCity;
	private Place currentPlace;
	private Direction currentDirection;
	
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
		this.emitHeadingChanged(this.currentDirection);
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
				String err="WALL·E says: There is no street in direction "+this.getCurrentHeading();
				/*if(isSwing()){
					JOptionPane.showMessageDialog(null, err);
				}*/
				throw new InstructionExecutionException(err);
			}
			
			Place nextPlace=street.nextPlace(this.currentPlace);
			if (street.isOpen()){
				this.currentPlace=nextPlace;
				this.emitRobotArrivesAtPlace(currentDirection, currentPlace);
				/*if(isSwing()){
					this.navigationPanel.updateCell(currentDirection, nextPlace);
				}*/
				
			}else{
				String err="WALL·E says: Arrggg, there is a street but it is closed!";
				/*if(isSwing()){
					JOptionPane.showMessageDialog(null, err);
				}*/
				throw new InstructionExecutionException(err);
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
		Item it=this.currentPlace.pickItem(id);
		this.emitPlaceHasChanged(currentPlace);
		return it;
	}
	
	/**
	 * Drop an item in the current place place. It does not check whether the operation fails
	 * @param it - The name of the item to be dropped.
	 */
	public void dropItemAtCurrentPlace(Item it)
	{
		this.currentPlace.addItem(it);
		this.emitPlaceHasChanged(currentPlace);
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
		this.emitInitNavigationModule();
	}
	
	/**
	 * Provides the observers with the information (description + inventory) of the current place
	 */
	public void scanCurrentPlace()
	{
		this.emitPlaceScanned(currentPlace);
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
	
	/**
	 * Returns the current place where the robot is (for testing purposes)
	 * @return The current place
	 */
	public Place getCurrentPlace()
	{
		return this.currentPlace;
		
	}
	
	
	private void emitHeadingChanged(Direction newHeading){
		for (NavigationObserver navigationObserver : this.observers) {
			navigationObserver.headingChanged(newHeading);
		}
	}
	
	private void emitInitNavigationModule(){
		for (NavigationObserver navigationObserver : this.observers) {
			navigationObserver.initNavigationModule(currentPlace, currentDirection);
		}
	}
	
	private void emitRobotArrivesAtPlace(Direction heading, PlaceInfo place){
		
	}
	
	private void emitPlaceScanned(PlaceInfo placeDescription){
		
	}
	
	private void emitPlaceHasChanged(PlaceInfo placeDescription){
		
	}
	
}

