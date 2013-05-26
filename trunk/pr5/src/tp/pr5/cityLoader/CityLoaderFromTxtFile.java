package tp.pr5.cityLoader;

import java.io.*;
import java.util.ArrayList;


import tp.pr5.*;
import tp.pr5.cityLoader.cityLoaderExceptions.WrongCityFormatException;
import tp.pr5.items.CodeCard;
import tp.pr5.items.Fuel;
import tp.pr5.items.Garbage;
import tp.pr5.items.Item;

public class CityLoaderFromTxtFile 
{
	private ArrayList<Place> place;
	private ArrayList<Street> street;
	private City city;
	
	public CityLoaderFromTxtFile()
	{
		this.place=new ArrayList<Place>();
		this.street=new ArrayList<Street>();
		this.city= new City();
	}
	
	/**
	 * Loads the city
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public City loadCity(InputStream file) throws IOException
	{
		BufferedReader buffer=null;
		try{
			
			buffer=new BufferedReader(new InputStreamReader(file));

			this.checkBeginCity(buffer);
			this.loadPlaces(buffer);
			this.loadStreets(buffer);
			this.loadItems(buffer);
			this.checkEndCity(buffer);
			
			
			this.city.setCurrentPlace(this.place.get(0));
	
		} catch(IOException e){
			throw new WrongCityFormatException();
		} catch(Exception e){
			throw new WrongCityFormatException();
		}finally {
			if(buffer!=null) 
				buffer.close();
			if(file!=null) 
				file.close();
		}
		
		return this.city;
	}
	
	/**
	 * Prepares the first tag that will be compared
	 * @param line -  input line
	 * @return new string
	 */
	private String firstTag(String line){
		String[] parts=line.split(" ");
		return parts[0];
	}
	
	/**
	 * Checks if the map start it's fine
	 * @param buffer
	 * @throws IOException
	 */
	private void checkBeginCity(BufferedReader buffer) throws IOException{
		String line=buffer.readLine();
		if(!this.firstTag(line).equalsIgnoreCase("begincity")){
			throw new WrongCityFormatException();
		}
	}
	
	/**
	 * Checks if the input place tag and his length are valid
	 * @param tag
	 * @param length
	 * @return
	 */
	private boolean isValidPlace(String tag,int length){
		return length==5 && tag.equalsIgnoreCase("place");
	}
	
	/**
	 * Load the places after the analysis stage where we will go
	 * @param buffer
	 * @throws IOException
	 */
	private void loadPlaces(BufferedReader buffer) throws IOException
	{
		int num=0;
		boolean places=true;
		String line=buffer.readLine();
		String tag=this.firstTag(line);
		String lastLine=line;
		
		if(tag.equalsIgnoreCase("beginplaces")){
			while ((line=buffer.readLine())!=null && places) {
				line=prepareDesc(line);
				String[] place=line.split(" ");
				if(this.isValidPlace(place[0], place.length) && parseNum(place[1])==num){
					Place p=createPlace(place);
					this.place.add(p);
					num++;
				} else {
					places=false;
					lastLine=line;
					break;
				}
			}
			tag=this.firstTag(lastLine);
			if(!tag.equalsIgnoreCase("endplaces")){
				throw new WrongCityFormatException();
			}
		}else {
			throw new WrongCityFormatException();
		}
	}
	
	/**
	 * Checks if the input item string tag and his length are valid
	 * @param tag
	 * @param length
	 * @return true if the input tag is a valid street and false if is not
	 */
	private boolean isValidStreet(String tag,int length){
		return (length==8 || length==9) && tag.equalsIgnoreCase("street");
	}
	
	/**
	 * Load the streets after the analysis stage where we will play
	 * @param buffer
	 * @throws IOException
	 */
	private void loadStreets(BufferedReader buffer) throws IOException{
		
		int num=0;
		boolean streets=true;
		String line=buffer.readLine();
		String tag=this.firstTag(line);
		String lastLine=line;
		
		if(tag.equalsIgnoreCase("beginstreets")){
			while ((line=buffer.readLine())!=null && streets) {
				String[] street=line.split(" ");
				if(this.isValidStreet(street[0], street.length) && parseNum(street[1])==num){
					Street s=createStreet(street);
					this.street.add(s);
					this.city.addStreet(s);
					num++;
				} else {
					streets=false;
					lastLine=line;
					break;
				}
			}
			tag=this.firstTag(lastLine);
			if(!tag.equalsIgnoreCase("endstreets")){
				throw new WrongCityFormatException();
			}
		}else {
			throw new WrongCityFormatException();
		}
	}
	
	/**
	 * Checks if the input item string tag and his length are valid
	 * @param tag - input tag
	 * @param length - input length
	 * @return true if the item is valid nd false if is not
	 */
	private boolean isValidItem(String tag,int length){
		return (length==7 || length==8) && (tag.equalsIgnoreCase("fuel") || tag.equalsIgnoreCase("garbage") || tag.equalsIgnoreCase("codecard"));
	}
	
	/**
	 *  Load the items after the analysis stage that we will use in the game
	 * @param buffer
	 * @throws IOException
	 */
	private void loadItems(BufferedReader buffer) throws IOException{
		int num=0;
		boolean items=true;
		String line=buffer.readLine();
		String tag=this.firstTag(line);
		String lastLine=line;
		
		if(tag.equalsIgnoreCase("beginitems")){
			while ((line=buffer.readLine())!=null && items) {
				line=prepareDesc(line);
				String[] item=line.split(" ");
				if(this.isValidItem(item[0], item.length) && parseNum(item[1])==num){
					this.parseItem(item);
					num++;
				} else {
					items=false;
					lastLine=line;
					break;
				}
			}
			tag=this.firstTag(lastLine);
			if(!tag.equalsIgnoreCase("enditems")){
				throw new WrongCityFormatException();
			}
		}else {
			throw new WrongCityFormatException();
		}
	}
	
	/**
	 * Checks if the city ends in the map file
	 * @param buffer
	 * @throws IOException
	 */
	private void checkEndCity(BufferedReader buffer) throws IOException{
		String line=buffer.readLine();
		if(line!=null){
			if(!this.firstTag(line).equalsIgnoreCase("endcity")){
				throw new WrongCityFormatException();
			}
		}
	}
	
	
	/**
	 * Parse the items that we will use in the game 
	 * @param aux
	 * @throws WrongCityFormatException
	 */
	private void parseItem(String [] cad) throws WrongCityFormatException
	{
		if (cad[0].equalsIgnoreCase("fuel"))
		{
			Item fuel = new Fuel(cad[2], parseDesc(cad[3]), parseNum(cad[4]), parseNum(cad[5]));
			this.city.setCurrentPlace(this.place.get(parseNum(cad[7])));
			this.city.getCurrentPlace().addItem(fuel);
		}
		else
		{
			if (cad[0].equalsIgnoreCase("codecard"))
			{
				Item codecard=new CodeCard(cad[2],parseDesc(cad[3]),cad[4]);
				this.city.setCurrentPlace(this.place.get(parseNum(cad[6])));
				this.city.getCurrentPlace().addItem(codecard);
			}
			else
			{
				if(cad[0].equalsIgnoreCase("garbage"))
				{
					Item garbage=new Garbage(cad[2], parseDesc(cad[3]), parseNum(cad[4]));
					this.city.setCurrentPlace((this.place.get(parseNum(cad[6]))));
					this.city.getCurrentPlace().addItem(garbage);
				}
				else
					throw new WrongCityFormatException();
			}
			
		}
		
	}
	/**
	 * Tries to create the streets that has been read from the file
	 * @param cad
	 * @return new Street
	 * @throws WrongCityFormatException
	 */
	private Street createStreet(String[] cad)  throws WrongCityFormatException
	{

		if (cad.length==9){
			return new Street(this.place.get(parseNum(cad[3])), parseDirection(cad[4]), this.place.get(parseNum(cad[6])), parseOpen(cad[7]), cad[8]);
		} else if(cad.length==8){			
			return new Street(this.place.get(parseNum(cad[3])), parseDirection(cad[4]), this.place.get(parseNum(cad[6])));
		} else {
			throw new WrongCityFormatException();
		}

	}


	/**
	 * Checks if the street is open 
	 * @param cad input string to be compared
	 * @return
	 */
	private boolean parseOpen(String cad) 
	{
		return cad.equalsIgnoreCase("open");
	}

	

	/**
	 * Creates new places
	 * @param cad
	 * @return a new place
	 * @throws WrongCityFormatException
	 */
	private Place createPlace(String[] cad) throws WrongCityFormatException
	{
		try
		{
			return new Place(cad[2], parseIsSpaceship(cad[4]), parseDesc(cad[3]));
		}
		catch (Exception e)
		{
			throw new WrongCityFormatException();
		}
		
	}
	
	/**
	 * Checks if the input direction is correct
	 * @param cad - string that will be compared
	 * @return new direction
	 * @throws WrongCityFormatException
	 */
	private Direction parseDirection(String cad)throws WrongCityFormatException
	{
		switch (cad.toUpperCase())
		{
		case "NORTH":
				return Direction.NORTH;
		case "SOUTH":
				return Direction.SOUTH;
		case "EAST":
				return Direction.EAST;
		case "WEST":
				return Direction.WEST;
		default:
				throw new WrongCityFormatException();
		}
	}

	/**
	 * 
	 * @param cad
	 * @return
	 */

	private String parseDesc(String cad) 
	{
		return cad.replaceAll("_"," "); 
	}
	
	/**
	 * 
	 * @param cad
	 * @return
	 */
	private boolean parseIsSpaceship(String cad) 
	{
		return !cad.equalsIgnoreCase("noSpaceShip");
	}

	/**
	 * change type String to Integer
	 * @param entrada
	 * @return integer
	 */
	private int parseNum(String entrada)
	{
		return Integer.parseInt(entrada);	
	}
    
	/**
	 * return initial place
	 * @return
	 */
	public Place getInitialPlace()
    {
		return this.place.get(0);
		
    }
	
	/**
	 * remove the " " and "_" 
	 * @param cad
	 * @return new string
	 */
	private String prepareDesc(String cad){
		
		if(cad.contains("\"")){
			String[] parts=cad.split("\"");
			String desc=parts[1];
			desc=desc.replaceAll(" ", "_");
			return parts[0]+desc+parts[2];
		}
		return cad;
	}
	
}
