package tp.pr5.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import tp.pr5.PlaceInfo;

/**
 * Represents a Place in the city on the Swing interface. It is a button, which name is the place name.
 * A PlaceCell needs to store a reference to the place that it represents. However, this place should not be modified by the PlaceCell
 * When the user clicks on a PlaceCell the NavigationPanel will show the place description if the Place was previously visited.
 */

public class PlaceCell extends JButton
{
	private static final long serialVersionUID = 1L;
	private PlaceInfo placeInfo;
	private NavigationPanel owner;
	
	/**
	 * Constructor. It needs a reference to the panel that references it
	 * @param owner
	 */
	public PlaceCell(NavigationPanel owner){
		this.owner=owner;
		this.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(visited()){
					PlaceCell.this.owner.placeScanned(placeInfo);
				}
			}
		});
	}
	
	/**
	 * Notifies that the robot arrives at a place. This PlaceCell will use the information provided in order to update its state
	 * @param arrivalPlace Place where the robot arrives at
	 */
	public void arriveAt(PlaceInfo arrivalPlace){
		this.setText(arrivalPlace.getName());
		if(arrivalPlace.isSpaceship()){
			this.setBackground(Color.red);
		} else {
			this.setBackground(Color.green);
		}
		this.placeInfo = arrivalPlace;
		
	}
	
	/**
	 * Updates the PlaceCell because that the robot leaves the place
	 */
	public void left(){
		this.setBackground(Color.gray);
	}
	
	/**
	 * Activate Cells
	 */
	public void activate(){
		if(this.placeInfo.isSpaceship()){
			this.setBackground(Color.red);
		} else {
			this.setBackground(Color.green);
		}
	}
	
	/**
	 * Desactivate cells
	 */
	public void desactivate(){
		if(this.visited()){
			this.setBackground(Color.gray);
		}
	}
	
	/**
	 * Set default properties to cells
	 */
	public void undo(){
		this.placeInfo=null;
		this.setText("");
		this.setBackground(null);
	}
	
	/*public String getDescriptionPlace(){
		String LINE_SEPARATOR = System.getProperty("line.separator");
		if(this.place.getItems().numberOfItems()>0){
			return this.place.toString()+LINE_SEPARATOR+"The place contains these objects:"+LINE_SEPARATOR+this.place.getItems();
		}
		return this.place.toString()+LINE_SEPARATOR+"The place is empty. There are no objects to pick";
	}*/
	
	/**
	 * Checks if the place has been visited
	 * @return
	 */
	public boolean visited(){
		return this.placeInfo!=null;
	}
	
	@Override
	public String toString(){
		return placeInfo.toString();
	}
	
}
