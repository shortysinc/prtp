package tp.pr5.gui;

import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import tp.pr5.Direction;
import tp.pr5.NavigationObserver;
import tp.pr5.PlaceInfo;
import tp.pr5.RobotEngineObserver;
import tp.pr5.items.InventoryObserver;
import tp.pr5.items.Item;

/**
 * Panel at the bottom of the window that displays messages about the events that occur during the simulation.
 * This panel implements all the observer interfaces in order to be notified about all event ocurred
 */
public class InfoPanel extends JPanel implements RobotEngineObserver, NavigationObserver, InventoryObserver{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel info= new JLabel();
	
	/**
	 * Default constructor
	 */
	public InfoPanel(){
		this.build();
	}
	
	private void build(){
		this.add(info);
	}
	
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
	public void headingChanged(Direction newHeading) {
		// TODO Auto-generated method stub
		
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
		this.info.setText("Robot attributes has been updated("+fuel+","+recycledMaterial+")");
		
	}

	@Override
	public void robotSays(String message) {
		// TODO Auto-generated method stub
		
	}
	
	public void setInfoText(String msg){
		this.info.setText(msg);
	}

}
