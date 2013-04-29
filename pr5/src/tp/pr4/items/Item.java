package tp.pr4.items;

import tp.pr4.*;

//100%
public abstract class Item implements Cloneable{

	private String id;
	
	private String description;
	
	public Item(String id, String description){
		this.id=id;
		this.description=description;
	}
	
	public abstract boolean canBeUsed();
	
	public abstract boolean use(RobotEngine r, NavigationModule nav);
	
	
	
	
	public String getId() 
	{
		return this.id;
	}

	public String toString()
	{
		return this.description;
	}
	
	//public abstract Item duplicate();
	
	public Object clone(){
		Object obj=null;
        try{
            obj=super.clone();
        }catch(CloneNotSupportedException ex){
            //
        }
        return obj;
	}
}
