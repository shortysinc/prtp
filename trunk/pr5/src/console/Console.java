package console;

import java.util.List;

import tp.pr5.*;
import tp.pr5.items.InventoryObserver;
import tp.pr5.items.Item;

public class Console implements NavigationObserver, RobotEngineObserver, InventoryObserver
{

	@Override
	public void inventoryChange(List<Item> inventory) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inventoryScanned(String inventoryDescription) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void itemScanned(String description) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void itemEmpty(String itemName) {
		System.out.println(Constants.MESSAGE_EMPTY.replace("{ID}", itemName));
	}

	@Override
	public void raiseError(String msg) {
		System.out.println(msg);		
	}

	@Override
	public void communicationHelp(String help) {
		System.out.println(help);
	}

	@Override
	public void engineOff(boolean atShip) {
		if(atShip){
			System.out.println(Constants.MESSAGE_FIN_SPACESHIP);
		} else {
			System.out.println(Constants.MESSAGE_DIE);
		}
	}

	@Override
	public void communicationCompleted() {
		System.out.println(Constants.MESSAGE_FIN_QUIT);
	}

	@Override
	public void robotUpdate(int fuel, int recycledMaterial) {
		System.out.println(Constants.MESSAGE_ROBOT_POWER.replace("{ID}", ""+fuelCalc(fuel)));
		System.out.println(Constants.MESSAGE_ROBOT_RECYCLED_MATERIAL.replace("{ID}", ""+recycledMaterial));
	}

	private int fuelCalc(int num)
	{
		if (num < 0)
			return num=0;
		return num;
	}
	
	@Override
	public void robotSays(String message) {
		System.out.println(Constants.PROMPT+message);
		
	}

	@Override
	public void headingChanged(Direction newHeading) {
		System.out.println(Constants.MESSAGE_HEADING.replace("{DIRECTION}", newHeading.toString()));
	}

	@Override
	public void initNavigationModule(PlaceInfo initialPlace, Direction heading) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void robotArrivesAtPlace(Direction heading, PlaceInfo place) {
		System.out.println(place.getName());
		System.out.println(place.getDescription());
		headingChanged(heading);
		if(((Place) place).getItems().numberOfItems()>0){
			System.out.println("The place contains these objects:");
			System.out.println(((Place) place).getItems().toString()+"\n");
		} else {
			System.out.println("The place is empty. There are no objects to pick\n");
		}
	}

	@Override
	public void placeScanned(PlaceInfo placeDescription) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void placeHasChanged(PlaceInfo placeDescription) {
		// TODO Auto-generated method stub
		
	}

}
