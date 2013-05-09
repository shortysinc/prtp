package tp.pr5;
//uni doc ok
//otros doc no
//imp ok
/**
 * This class represents the city where the robot is wandering. 
 * It contains information about the streets and the places in the city.
 * 
 */
public class City 
{
	private Street[] cityMap= new Street[0];
	private Place currentPlace;
	
		
	/**
	 * Creates a city using an array of streets
	 * @param cityMap
	 */
	public City(Street[] cityMap)
	{
		this.cityMap=cityMap;		
		
	}
	
	/**
	 * Default constructor. Needed for testing purposes
	 */
	public City() 
	{
		
	}


	/**
	 * Looks for the street that starts from the given place in the given direction.
	 * @param currentPlace The place where to look for the street
	 * @param currentHeading The direction to look for the street
	 * @return the street that stars from the given place in the given direction. 
	 * It returns null if there is not any street in this direction from the given place
	 */
	public Street lookForStreet(Place currentPlace, Direction currentHeading)
	{
		if(this.cityMap!=null)
		{
			for (int i = 0; i < this.cityMap.length; i++) 
			{
				if(cityMap[i].comeOutFrom(currentPlace, currentHeading))
				{
					return cityMap[i];
				}
			}
		}
		return null;
	}
	
	/**
	 * Adds an street to the city
	 * @param street - The street to be added
	 */
	public void addStreet(Street street)
	{
		Street[] newStreets= new Street[this.cityMap.length+1];
		for (int i = 0; i < this.cityMap.length; i++) 
		{
			newStreets[i]=this.cityMap[i];
		}
		newStreets[newStreets.length-1]=street;
		this.cityMap=newStreets;
	}


	public Street[] getCityMap() 
	{
		return cityMap;
	}


	public Place getCurrentPlace() 
	{
		return currentPlace;
	}


	public void setCurrentPlace(Place place) 
	{
		this.currentPlace=place;
		
	}

	/**
	 * For debugging purposes
	 */
	public String toString(){
		return super.toString();
	}
	
}
