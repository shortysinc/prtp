package tp.pr5.items;

import tp.pr5.*;

//100%
public abstract class Item implements Cloneable{

	private String id;
	
	private String description;
	
	/**
	 * Builds an item from a given identifier and description
	 * @param id - Item identifier
	 * @param description - Item description
	 */
	public Item(String id, String description){
		this.id=id;
		this.description=description;
	}
	
	/**
	 * Checks if the item can be used. Subclasses must override this method
	 * @return true - if the item can be used
	 */
	public abstract boolean canBeUsed();
	
	/**
	 * 
	 * @param r -  The robot that uses the item
	 * @param nav
	 * @return true if the action was completed. Otherwise, it returns false
	 */
	public abstract boolean use(RobotEngine r, NavigationModule nav);
		
	
	/**
	 * Return the item identifier
	 * @return The item identifier
	 */
	public String getId() 
	{
		return this.id;
	}

	/**
	 * Generates a String with the Item description
	 */
	public String toString()
	{
		return this.description;
	}
	
	/**
	 * Creates and returns a copy of this object. 
	 * The precise meaning of "copy" may depend on the class of the object.
	 */
	public Object clone(){
		Object obj=null;
        try
        {
            obj=super.clone();
        }
        catch(CloneNotSupportedException ex){
           
        }
        return obj;
	}
}
