package tp.pr4;

import tp.pr4.items.CodeCard;

//100%
/**
 *A street links two places A and B in one direction. If it is defined as Street(A,NORTH,B) 
 *it means that Place B is at NORTH of Place A. Streets are two-way streets, i.e. if B is at 
 *NORTH of A then A is at SOUTH of B.  I
 */
public class Street 
{
	private Place source;
	private Direction direction;
	private Place target;
	private boolean isOpen;
	private String code;
	
	//--------------------------------------------------------------------------------------------------//
	/**
	 * Creates an open street and it have not any code to open or close it.
	 * @param source - Source place (in the example A).
	 * @param direction - Represents how is placed the target place with respect to the source place.
	 * @param target - Target place (in the example B).
	 */
	public Street(Place source, Direction direction, Place target)
	{
		
		this.source		= source;
		this.direction	= direction;
		this.target		= target;
		this.isOpen     = true;
		
	}
	
	
	/**
	 * Creates a street that has a code to open and close it
	 * @param source - Source place (in the example A).
	 * @param direction - Represents how is placed the target place with respect to the source place.
	 * @param target - Target place (in the example B).
	 * @param isOpen - Determines is the street is opened or closed
     * @param code - The code that opens and closes the street
	 */
	public Street(Place source, Direction direction,Place target,boolean isOpen, String code)
	{
		this.source= source;
		this.direction=direction;
		this.target= target;
		this.isOpen=isOpen;
		this.code=code;
	}
	
	//--------------------------------------------------------------------------------------------------//
	
	/**
	 * Checks if the street comes out from a place in a given direction. 
	 * Remember that streets are two-way.
	 * @param place - The place to check
	 * @param whichDirection - Direction used.
	 * @Return true if the street comes out from the input Place.
	 */
	public boolean comeOutFrom(Place place, Direction whichDirection)
	{
		if((this.source.equals(place) && this.direction.equals(whichDirection)) || (this.target.equals(place) && this.direction.equals(whichDirection.opposite())))
		{
			return true;
		}
		return false;
	}

	/**
	 * Returns the place of the other side from the place whereAmI.
	 * @param whereAmI - The place where I am.
	 * @return It returns the Place at the other side of the street. 
	 *         Returns null if whereAmI does not belong to the street.
	 */
	public Place nextPlace(Place whereAmI)
	{
		if(this.source.equals(whereAmI))
		{
			return this.target;
		} 
		else if(this.target.equals(whereAmI))
		{
			return this.source;
		} 
		else 
		{
			return null;
		}
		
	}
	/**
	 * Checks if the street is open or closed
	 * @return true, if the street is open, and false when the street is closed
	 */
	public boolean isOpen()
	{
		return this.isOpen;
		
	}
	
	/**
	 * Tries to open a street using a code card. Codes must match in order to complete this action
	 * @param card - A code card to open the street
	 * @return true if the action has been completed
	 */
	public boolean open(CodeCard card)
	{
		return this.isOpen=card.getCode().equals(this.code);
	}
	
	/**
	 * Tries to close a street using a code card. Codes must match in order to complete this action
	 * @param card - A code card to close the street
	 * @return true if the action has been completed
	 */
	public boolean close(CodeCard card)
	{
		if(card.getCode().equals(this.code))
		{
			this.isOpen=false;
			return true;
		}
		
		return false;
	}
	
	
	public String getCode()
	{
		return this.code;
	}
}
