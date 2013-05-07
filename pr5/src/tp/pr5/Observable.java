package tp.pr5;


import java.util.Vector;

/**
 * Helper class to create classes that support an observer interface.
 *
 * @param <T>
 */
public class Observable<T> 
{
	protected Vector<T> observers;
	
	

	public Observable() {
		
		this.observers = new Vector<T>();
	}

	public void addObserver(T observer)
	{
		//if (!this.observers.contains(observer))
			this.observers.add(observer);
	}
	
	public void removeObserver(T observer)
	{
		this.observers.remove(observer);
	}
	
}
