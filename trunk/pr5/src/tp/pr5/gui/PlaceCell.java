package tp.pr5.gui;

import java.awt.Color;
import javax.swing.JButton;

import tp.pr5.Place;

public class PlaceCell extends JButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Place place;
	
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
		if(this.place.getItems().numberOfItems()>0){
			return this.place.toString()+LINE_SEPARATOR+"The place contains these objects:"+LINE_SEPARATOR+this.place.getItems();
		}
		return this.place.toString()+LINE_SEPARATOR+"The place is empty. There are no objects to pick";
	}
	
	public boolean visited(){
		return this.place!=null;
	}
	
}
