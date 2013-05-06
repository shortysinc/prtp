package tp.pr5;

import java.util.ArrayList;

/**
 * Helper class to create classes that support an observer interface.
 *
 * @param <T>
 */
public class Observable<T> 
{
	protected ArrayList<T> observers;
	
	public void addObserver(T observer)
	{
		this.observers.add(observer);
	}
	
	public void removeObserver(T observer)
	{
		this.observers.remove(observer);
	}
	
}
