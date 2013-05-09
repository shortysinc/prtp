package tp.pr5;
//uni doc ok
//otros doc ok
//imp ok
/**
 * PlaceInfo defines a non-modifiable interface over a Place. It is employed by the classes that need to access the information contained in the place but that cannot modify the place itself.
 * @author danidhsm
 */
public interface PlaceInfo {

	/**
	 * Return the place name
	 * @return The place name
	 */
	String getName();
	
	/**
	 * Return the place description
	 * @return The place description
	 */
	String getDescription();
	
	/**
	 * Is this place the space ship?
	 * @return true if the place represents a space ship
	 */
	boolean isSpaceship();
	
}
