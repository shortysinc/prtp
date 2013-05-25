package tp.pr5.items;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;

//100%
/**
 * An item that represents fuel. This item can be used at least once and it provides power energy to the robot. 
 * When the item is used the configured number of times, then it must be removed from the robot inventory.
 * 
 */
public class Fuel extends Item
{
	private int power;
	private int times;
 
	/**
	 * Fuel constructor
	 * @param id - Item id
	 * @param description - Item description
	 * @param power - The amount of power energy that this item provides the robot
	 * @param times - Number of times the robot can use the item
	 */
	public Fuel(String id, String description, int power, int times)
	{
		super(id, description);
		this.power=power;
		this.times=times;
	}
	
	/**
	 * Fuel can be used as many times as it was configured
	 * @return true it the item still can be used
	 */
	
	public boolean canBeUsed()
	{
		return this.times>0;
				
	}
	/**
	 * Using the fuel provides energy to the robot (if it can be used)
	 * @return r - robot that is going to use the fuel
     * @return p - place where the fuel is going to be used
	 */
	public boolean use(RobotEngine r, NavigationModule nav)
	{ 
		if(this.canBeUsed())
		{
			r.addFuel(power);
			this.times--;
			return true;
		}
		return false;
	}
	public String toString()
	{
		return super.toString() + "// power = " + power + ", times = " + times;
	}

}
