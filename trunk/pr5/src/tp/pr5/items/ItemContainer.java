package tp.pr5.items;



import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tp.pr5.Observable;

public class ItemContainer extends Observable<InventoryObserver> {
	
	private Item[] items= new Item[0];
	
	/**
	 * Creates the empty container
	 */
	public ItemContainer(){
		
	}
	
	/**
	 * Returns the container's size
	 * @return The number of items in the container
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
		if(position<0){
			Item[] newItems= new Item[this.items.length+1];
			for (int i = 0; i < this.items.length; i++) {
				newItems[i]=this.items[i];
			}
			newItems[newItems.length-1]=item;
			this.items=newItems;
			this.azSort();
			
			this.emitInventoryChange(this.toImmutableList());
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
			this.emitInventoryChange(this.toImmutableList());
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
	
	/**
	 * Method called by the OperateInstruction when an item stored in the collection is successfully used.
	 * The collection then checks if the item could be used again in the future. If it is not possible because the item is "empty", then it is removed from the collection (and the method notifies all the observers).
	 * @param item to be used
	 */
	public void useItem(Item item){
		if(!item.canBeUsed()){
			this.pickItem(item.getId());
			this.emitItemEmpty(item.getId());
			this.emitInventoryChange(Arrays.asList(this.items));
		}
		
	}
	
	/**
	 * 
	 */
	public void requestScanCollection(){
		this.emitInventoryScanned(this.toString());
	}
	
	/**
	 * PRECOND: The item exists
	 * @param id
	 */
	public void requestScanItem(String id){
		this.emitItemScanned(id);
	}
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
	
	private List<Item> toImmutableList(){
		return Collections.unmodifiableList(Arrays.asList(this.items));
	}
	
	private void emitInventoryChange(List<Item> inventory){
		for (InventoryObserver inventoryobserver : this.observers) {
			inventoryobserver.inventoryChange(inventory);
		}
	}
	
	private void emitInventoryScanned(String inventoryDescription){
		for (InventoryObserver inventoryobserver : this.observers) {
			inventoryobserver.inventoryScanned(inventoryDescription);
		}
	}

	private void emitItemScanned(String description){
		for (InventoryObserver inventoryobserver : this.observers) {
			inventoryobserver.itemScanned(description);
		}
	}
	
	private void emitItemEmpty(String itemName){
		for (InventoryObserver inventoryobserver : this.observers) {
			inventoryobserver.itemEmpty(itemName);
		}
	}
}
