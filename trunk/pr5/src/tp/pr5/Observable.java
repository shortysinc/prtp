package tp.pr5;

import java.util.Vector;

/**
 * Helper class to create classes that support an observer interface.
 * @param <T> Observer interface
 */
public class Observable<T> 
{
	protected Vector<T> observers;
	
	public Observable() {
		this.observers = new Vector<T>();
	}

	/**
	 * Add New Observer
	 * @param observer
	 */
	public void addObserver(T observer){
		this.observers.add(observer);
	}
	
	/**
	 * Tries to remove a observer
	 * @param observer
	 */
	public void removeObserver(T observer){
		this.observers.remove(observer);
	}
	
}
