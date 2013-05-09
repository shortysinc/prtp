package tp.pr5;
//uni doc ok
//otros doc no
//imp no
import tp.pr5.items.*;



/**
 * It represents a place in the city. Places are connected by streets according to the 4 compass directions: North, East, South and West. Every place has a name and a textual description about itself. This description is displayed when the robot arrives at the place.
 * A place can represent the spaceship where the robot is safe. When the robot arrives at this place, the robot shuts down and the application will finish.
 */
public class Place implements PlaceInfo 
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
	 * Overrides toString method. Returns the place name, its description and the list of items contained in the place
	 */
	public String toString()
	{
		String place=this.name+Constants.LINE_SEPARATOR+this.description+Constants.LINE_SEPARATOR;
		if(this.items.numberOfItems()>0){
			place+="The place contains these objects:"+Constants.LINE_SEPARATOR;
			place+=this.items.toString()+Constants.LINE_SEPARATOR;
		} else {
			place+="The place is empty. There are no objects to pick"+Constants.LINE_SEPARATOR+Constants.LINE_SEPARATOR;
		}
		return place;
	}
	
	/**
	 * Checks if an item is in this place
	 * @param id Identifier of an item
	 * @return true if and only if the place contains the item identified by id
	 */
	public boolean existItem(String id)
	{
		return this.items.containsItem(id);
		
	}	
	
	/**
	 * Tries to pick an item characterized by a given identifier from the place. 
	 * If the action was completed the item must be removed from the place. 
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

	/**
	 * Drop an item in this place. The operation can fail, returning false
	 * @param it The name of the item to be dropped.
	 * @return true if and only if the item is dropped in the place, i.e., an item with the same identifier does not exists in the place
	 */
	public boolean dropItem(Item it)
	{
		return this.items.addItem(it);
		
	}
	
	/**
	 * Is it the spaceship?
	 * @return true if the place represents a spaceship.
	 */
	public boolean isSpaceship()
	{
		return this.spaceShip;
	}
	
	/*public ItemContainer getItems() 
	{
		return items;
	}*/

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getDescription() {
		return this.description;
	}	
}
