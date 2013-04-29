/**
 * 
 */
package tp.pr5;

import tp.pr5.items.*;



/**
 * It represents a place in the city. Places are connected by streets according to the 4 compass directions: North, East, South and West. Every place has a name and a textual description about itself. This description is displayed when the robot arrives at the place.
 * A place can represent the spaceship where the robot is safe. When the robot arrives at this place, the robot shuts down and the application will finish.
 */
public class Place 
{

	private String name;
	private String description;
	private boolean spaceShip;
	private ItemContainer items;
	
	
	
	/**
	 * Creates the place
	 * @param name - Place name
	 * @param isSpaceShip - Is it a spaceship?
	 * @param description - Place description
	 */
	public Place(String name, boolean isSpaceShip, String description)
	{
		this.name			= name;
		this.description	= description;
		this.spaceShip		= isSpaceShip;
		this.items =new ItemContainer();
	}
	
	/**
	 * Is it the spaceship?
	 * @return true if the place represents a spaceship.
	 */
	public boolean isSpaceship()
	{
		return this.spaceShip;
	}
	
	/**
	 * Overrides toString method. Returns the place name and its description
	 */
	public String toString()
	{
		return this.name+"\n"+this.description;
	}
	
	
	/**
	 *Tries to pick an item characterized by a given identifier from the place. 
	 *If the action was completed the item must be removed from the place. 
	 * @param id - The identifier of the item
	 * @return The item of identifier id if it exists in the place. Otherwise the method returns null
	 */
	public Item pickItem(String id)
	{
		return items.pickItem(id);
	}
	
	
	/**
	 * Tries to add an item to the place. 
	 * The operation can fail (if the place already contains an item with the same name)
	 * @param it - The item to be added
	 * @return true if and only if the item can be added to the place, i.e., the place does not contain an item with the same name
	 */
	public boolean addItem(Item it)
	{
		
		return this.items.addItem(it);
		
		
	}

	public ItemContainer getItems() 
	{
		return items;
	}
	
	
	public boolean existItem(String id)
	{
		return this.items.containsItem(id);
		
	}
	
	
	public boolean dropItem(Item it)
	{
		return this.items.addItem(it);
		
	}
	
	
	
}
