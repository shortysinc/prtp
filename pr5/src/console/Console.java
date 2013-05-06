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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void raiseError(String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void communicationHelp(String help) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void engineOff(boolean atShip) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void communicationCompleted() {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
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
