package tp.pr5.items;

import tp.pr5.gui.RobotPanel;

public class ItemContainer {
	
	private Item[] items= new Item[0];
	private RobotPanel robotPanel;
	
	
	/**
	 * Returns the number of items contained
	 * @return the number of items in the container
	 */
	public int numberOfItems()
	{
		return this.items.length;
	}
	
	/**
	 * Add an item to the container. The operation can fail, returning false
	 * @param item The name of the item to be added.
	 * @return true if and only if the item is added, i.e., an item with the same identifier does not exists in the container
	 */
	public boolean addItem(Item item){
		int position=this.findItemPosition(item.getId());
		Item[] newItems= new Item[this.items.length+1];
		if(position<0){
			for (int i = 0; i < this.items.length; i++) {
				newItems[i]=this.items[i];
			}
			newItems[newItems.length-1]=item;
			this.items=newItems;
			this.azSort();
			
			updateItems();
			
			return true;
		}
		return false;
	}
	
	
	/**
	 * Returns the item from the container according to the item name
	 * @param id Item name
	 * @return Item with that name or null if the container does not store an item with that name.
	 */
	public Item getItem(String id){
		for (int i = 0; i < this.items.length; i++) {
			if(this.items[i].getId().equalsIgnoreCase(id)){
				return this.items[i];
			}
		}
		return null;
	}
	
	/**
	 * Returns and deletes an item from the inventory. This operation can fail, returning null
	 * @param id Name of the item
	 * @return An item if and only if the item identified by id exists in the inventory. Otherwise it returns null
	 */
	public Item pickItem(String id){
		Item item=null;
		int position=this.findItemPosition(id);
		
		if(position>=0){
			Item[] newItems= new Item[this.items.length-1];
			int j=0;
			int i=0;
			do{
				if(j!=position){
					newItems[i]=this.items[j];
					++i;
				} else{
					item=this.items[position];
				}
				++j;
			} while(j<this.items.length);
			this.items=newItems;
			updateItems();
		}
		return item;
	}
	
	
	/**
	 * Generates a String with information about the items contained in the container. Note that the items must appear sorted but the item name.
	 */
	public String toString(){
		String LINE_SEPARATOR = System.getProperty("line.separator");
		String inventary="";
		for (int i = 0; i < this.items.length; i++) {
			inventary+= "   "+this.items[i].getId()+LINE_SEPARATOR;
		}
		return inventary.trim().replaceFirst("", "   ");
	}
	//inventary+= "   "+this.items[i].getId()+LINE_SEPARATOR;
	/**
	 * get the array position due to an id item
	 * @param id id item
	 * @return position
	 */
	private int findItemPosition(String id)
	{
		int position=-1;
		for (int i = 0; i < this.items.length; i++) 
		{
			if(this.items[i].getId().equalsIgnoreCase(id))
			{
				position=i;
			}
		}
		return position;
	}
	
	private void azSort(){
		int j;
		Item temp;
		for (int i = 1; i < this.items.length; i++){
			temp = this.items[i];
			j = i-1;
			while (j>=0 && this.items[j].getId().compareToIgnoreCase(temp.getId())>0){
				this.items[j+1] = this.items[j];
				j--;
			}
		    this.items[j+1] = temp;
		}
	}
	
	/**
	 * Checks if the Item with this id exists in the container.
	 * @param id - Name of the item.
	 * @return true if the container has an item with that name.
	 */
	public boolean containsItem(String id)
	{
		for (int i = 0; i < this.items.length; i++) 
		{
			if(this.items[i].getId().equalsIgnoreCase(id))
			{
				return true;
			}
		}
		return false;
		
	}
	
	public void setRobotPanel(RobotPanel robotPanel){
		this.robotPanel=robotPanel;
	}
	
	public void updateItems(){
		if(this.robotPanel!=null){
			this.robotPanel.updateItems(this.items);
		}
	}

}
