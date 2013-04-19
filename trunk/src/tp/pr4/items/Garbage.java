package tp.pr4.items;

import tp.pr4.NavigationModule;
import tp.pr4.RobotEngine;

/**
 * The garbage is a type of item that generates recycled material after using it. 
 * The garbage can be used only once. After using it, it must be removed from the robot inventory
 * 
 *
 */
public class Garbage extends Item{

	private int recycledMaterial;
	private int times;
	
	/**
	 * @return id - Item id
     * @return description - Item description
     * @return recycledMaterial - The amount of recycled material that the item generates
	 */
	public Garbage(String id, String description, int recycledMaterial)
	{
		super(id, description);
		this.recycledMaterial=recycledMaterial;
	}
	
	
	
	
	/**
	 * Garbage can be used only once
	 * @return true if the item has not been used yet
	 */
	public boolean canBeUsed()
	{
		return this.times>=0;
	}
	
	
	
	/**
	 * The garbage generates recycled material for the robot that uses it
	 * @return r - the robot that uses the item
	 * @return p - The place where the item is used
	 * 
	 * @return true if the garbage was transformed in recycled material
	 * 
	 */
	public boolean use(RobotEngine r, NavigationModule nav)
	{
		if (this.canBeUsed()){
			r.addRecycledMaterial(recycledMaterial);
			this.times--;
			r.printRobotState();
			return true;
		}
		return false;
	}

	public String toString()
	{
		return super.toString() + "// recycledMaterial = " + this.recycledMaterial + ", times = " + this.times;
	}
}
