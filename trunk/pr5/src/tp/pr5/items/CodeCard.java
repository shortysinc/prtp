package tp.pr5.items;

import tp.pr5.*;
//100%
public class CodeCard extends Item{

	private String code;
	/**
	 * Code card constructor
	 * @param id - Code card name
	 * @param description - Code card description
     * @param code - Secret code stored in the code card
	 */
	public CodeCard(String id, String description, String code)
	{
		super(id, description);
		this.code=code;
	}
	
	/**
	 *A code card always can be used. Since the robot has the code card it never loses it 
	 */
	public boolean canBeUsed()
	{
		return true;
	}
	
	/**
	 * The method to use a code card. If the robot is in a place which contains a street in the direction 
	 * he is looking at,then the street is opened or closed if the street code and the card code match.
	 * @param r - the robot engine employed to use the card.
     * @param p - the place where the card is going to be used
     * @return true If the codecard can complete the action of opening or closing a street. 
     * Otherwise it returns false.
	 */
	
	public boolean use(RobotEngine r,NavigationModule nav)
	{
		Street street=nav.getHeadingStreet();
		
		if (canBeUsed() ){
			if(street!=null && this.code!=null){
				if(!street.isOpen()){
					return street.open(this);
				}else{
					return street.close(this);
				}
			}
		}
		return false;

	}
	
	/**
	 * Gets the code stored in the code card
	 * @return A String that represents the code
	 */
	public String getCode() 
	{
		return this.code;
	}
	public String toString()
	{
		return super.toString();
	}

}
