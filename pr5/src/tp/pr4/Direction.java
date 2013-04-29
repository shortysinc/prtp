package tp.pr4;

/**
 * An enum class that represents the compass directions (North, east, south and west) plus a value that represents an unknown direction.
 */
public enum Direction 
{
	
	NORTH{
		private String image = "walleNorth.png";
		@Override
		public String getImage(){
			return this.image;
		};
		@Override
		public int getVerticalMovement(){
			return -1;
		};
	}
	,EAST{
		private String image = "walleEast.png";
		@Override
		public String getImage(){
			return this.image;
		};
		@Override
		public int getHorizontalMovement(){
			return 1;
		};
	}
	,SOUTH{
		public String image = "walleSouth.png";
		@Override
		public String getImage(){
			return this.image;
		};
		@Override
		public int getVerticalMovement(){
			return 1;
		};
	}
	,WEST{
		public String image = "walleWest.png";
		@Override
		public String getImage(){
			return this.image;
		};
		
		@Override
		public int getHorizontalMovement(){
			return -1;
		};
	}
	,UNKNOWN;
	
	
	public String getImage(){
		return "";
	}
	
	public int getVerticalMovement(){
		return 0;
	}
	public int getHorizontalMovement(){
		return 0;
	}
	
	/**
	 * 
	 * @param rotation
	 * @return
	 */
	Direction rotate(Rotation rotation)
	{
		if(rotation.equals(Rotation.RIGHT))
		{
			return rotateRight();
		} 
		else 
		{
			return rotateLeft();
		}
	}
	
	/**
	 * return the next direction
	 * @return Direction
	 */
	Direction rotateRight()
	{
		Direction[] direction=Direction.values();
		int sig= (this.ordinal()+1)%4;
		return direction[sig];
	}
	
	/**
	 * return the previous direction
	 * @return Direction
	 */
	Direction rotateLeft()
	{
		Direction[] direction=Direction.values();
		int sig=3;
		if(this.ordinal()-1>=0)
		{
			sig=(this.ordinal()-1)%4;
		}
		return direction[sig];
	}
	
	/**
	 * return the opposite Direction
	 * @return Direction
	 */
	Direction opposite()
	{
		Direction[] direction=Direction.values();
		int opp;
		if(this.ordinal()-2<0)
		{
			opp=this.ordinal()+2;
		} 
		else 
		{
			opp=Math.abs(this.ordinal()-2);
		}
		return direction[opp];

	}
}
