/**
 * 
 */
package tp.pr4;

/**
 * An enum class that represents in which direction the robot rotates (left or right) plus a value that represents an unknown direction.
 */
public enum Rotation 
{

	LEFT,RIGHT,UNKNOWN; 
	
	public Rotation opposite(){
		if(this.equals(Rotation.LEFT)){
			return Rotation.RIGHT;
		} else if (this.equals(Rotation.RIGHT)){
			return Rotation.LEFT;
		} else {
			return Rotation.UNKNOWN;
		}
	}
	
}
