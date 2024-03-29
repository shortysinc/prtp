package tp.pr5.console;

import java.util.List;

import tp.pr5.*;
import tp.pr5.items.InventoryObserver;
import tp.pr5.items.Item;

public class Console implements NavigationObserver, RobotEngineObserver, InventoryObserver
{

	@Override
	public void inventoryChange(List<Item> inventory) {
		//
	}

	@Override
	public void inventoryScanned(String inventoryDescription) {
		System.out.println(inventoryDescription);
	}

	@Override
	public void itemScanned(String description) {
		System.out.println(description);
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
		String newFuel=Integer.toString(fuel);
		String newRecycledMaterial=Integer.toString(recycledMaterial);
		System.out.println(Constants.MESSAGE_ROBOT_POWER.replace("{ID}", newFuel));
		System.out.println(Constants.MESSAGE_ROBOT_RECYCLED_MATERIAL.replace("{ID}", newRecycledMaterial));
	}
	
	@Override
	public void robotSays(String message) {
		System.out.println(message);
	}

	@Override
	public void headingChanged(Direction newHeading) {
		System.out.println(Constants.MESSAGE_HEADING.replace("{DIRECTION}", newHeading.toString()));
	}

	@Override
	public void initNavigationModule(PlaceInfo initialPlace, Direction heading) {
		//robotArrivesAtPlace(heading, initialPlace);
		System.out.println(initialPlace);
		headingChanged(heading);
	}

	@Override
	public void robotArrivesAtPlace(Direction heading, PlaceInfo place) {
		System.out.println(Constants.MESSAGE_MOVING.replace("{ID}",heading.toString()));
		System.out.println(place);
	}

	@Override
	public void placeScanned(PlaceInfo placeDescription) {
		System.out.println(placeDescription.getDescription());
	}

	@Override
	public void placeHasChanged(PlaceInfo placeDescription) {
		
		
	}

	@Override
	public void undone(Direction direction) {
		
		
	}

}
