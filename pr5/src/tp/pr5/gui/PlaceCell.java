package tp.pr5.gui;

import java.awt.Color;
import javax.swing.JButton;

import tp.pr5.Place;
import tp.pr5.PlaceInfo;

/**
 * Represents a Place in the city on the Swing interface. It is a button, which name is the place name.
 * A PlaceCell needs to store a reference to the place that it represents. However, this place should not be modified by the PlaceCell
 * When the user clicks on a PlaceCell the NavigationPanel will show the place description if the Place was previously visited.
 */

public class PlaceCell extends JButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Place place;
	
	/**
	 * Constructor. It needs a reference to the panel that references it
	 * @param owner
	 */
	public PlaceCell(NavigationPanel owner){
		
	}
	
	/**
	 * Notifies that the robot arrives at a place. This PlaceCell will use the information provided in order to update its state
	 * @param arrivalPlace Place where the robot arrives at
	 */
	public void arriveAt(PlaceInfo arrivalPlace){
		
	}
	
	/**
	 * Updates the PlaceCell because that the robot leaves the place
	 */
	public void left(){
		
	}
	
	public void setPlace(Place place){
		this.place=place;
		this.setText(place.toString());
	}
	
	public void activate(){
		if(this.place.isSpaceship()){
			this.setBackground(Color.red);
		} else {
			this.setBackground(Color.green);
		}
	}
	
	public void desactivate(){
		if(place!=null){
			this.setBackground(Color.gray);
		}
	}
	
	public void close(){
		this.place=null;
		this.setText("");
		this.setBackground(null);
	}
	
	public String getDescriptionPlace(){
		String LINE_SEPARATOR = System.getProperty("line.separator");
		/*if(this.place.getItems().numberOfItems()>0){
			return this.place.toString()+LINE_SEPARATOR+"The place contains these objects:"+LINE_SEPARATOR+this.place.getItems();
		}*/
		return this.place.toString()+LINE_SEPARATOR+"The place is empty. There are no objects to pick";
	}
	
	public boolean visited(){
		return this.place!=null;
	}
	
}
