package tp.pr5.console;

import java.util.List;

import tp.pr5.*;
import tp.pr5.items.InventoryObserver;
import tp.pr5.items.Item;
import tp.pr5.items.ItemContainer;

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
			System.out.println(Constants.MESSAGE_FIN_SPACESHIP.trim());
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
		String newFuel=Integer.toString(fuelCalc(fuel));
		String newRecycledMaterial=Integer.toString(recycledMaterial);
		System.out.println(Constants.MESSAGE_ROBOT_POWER.replace("{ID}", newFuel.trim() ));
		System.out.println(Constants.MESSAGE_ROBOT_RECYCLED_MATERIAL.replace("{ID}", newRecycledMaterial));
	}

	private int fuelCalc(int num)
	{
		if (num < 0)
			return num=0;
		return num;
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
		robotArrivesAtPlace(heading, initialPlace);
	}

	@Override
	public void robotArrivesAtPlace(Direction heading, PlaceInfo place) {
		System.out.println(place);
		// TODO 
		// Esto es lo que hay que cambiar
		headingChanged(heading);
	}

	@Override
	public void placeScanned(PlaceInfo placeDescription) {
		System.out.println(placeDescription.getDescription()+"\n");
		// TODO
		//Aqui hay que sacar la descripcion del sitio y los objetos que hay mas o menos asi
		//esto es cuando Haces un RADAR
		/*
		 WALL·E> In this square you can find a code card
   			Battery
		 */
	}

	@Override
	public void placeHasChanged(PlaceInfo placeDescription) {
		
		
	}

	@Override
	public void undone(Direction direction) {
		
		
	}

}
