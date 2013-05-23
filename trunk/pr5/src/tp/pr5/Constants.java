package tp.pr5;
//uni doc ok
//otros doc ok
//imp ok
/*
 * In this class are defined the set of constants of our program.
 */
public class Constants {

	public static final int INITIAL_FUEL 							= 100;
	public static final int INITIAL_RECYCLED_MATERIAL 				= 0;
	public static final int LOST_FUEL 								= 5;
	public static final String MESAGE_POOR 							= "You are poor, you have not got any item (yet).";
	public static final String MESSAGE_CHANGES_ERROR				= "WALL\u00b7E says: I have problems using the object {ID}";
	public static final String MESSAGE_DIE 							= "WALL\u00b7E says: I run out of fuel. I cannot move. Shutting down...";
	public static final String MESSAGE_DOOR_CLOSED 					= "WALL\u00b7E says: There is a street in direction {ID} but it is closed. I cannot move in that way"; 
	public static final String MESSAGE_DROP_ERROR 					= "You do not have any {ID}.";
	public static final String MESSAGE_DROP_ERROR2 					= "WALL\u00b7E says: The place already contains the object {ID}"; 
	public static final String MESSAGE_DROP_OK 						= "WALL\u00b7E says: Great! I have dropped {ID}";
	public static final String MESSAGE_EMPTY 						= "WALL\u00b7E says: What a pity! I have no more {ID} in my inventory"; 
	public static final String MESSAGE_FIN_QUIT 					= "WALL\u00b7E says: I have communications problems. BYE BYE"; 
	public static final String MESSAGE_FIN_SPACESHIP 				= "WALL\u00b7E says: I am at my spaceship. Bye bye";
	public static final String MESSAGE_HEADING 						= "WALL\u00b7E is looking at direction {DIRECTION}";
	public static final String MESSAGE_ITEM_IS_IN_INVENTORY 		= "WALL\u00b7E says: I am stupid! I had already the object {ID}"; 
	public static final String MESSAGE_MOVING 						= "WALL\u00b7E says: Moving in direction {ID}";
	public static final String MESSAGE_NO_ITEM 						= "WALL\u00b7E says: I have not {ID} in my inventory."; 
	public static final String MESSAGE_NO_ITEMS 					= "WALL\u00b7E says: My inventory is empty";
	public static final String MESSAGE_NO_PLACE 					= "WALL\u00b7E says: There is no street in direction {ID}"; 
	public static final String MESSAGE_NO_ROOM_TO_EXAMINE 			= "No place specified";
	public static final String MESSAGE_PICK_ERROR1 					= "WALL\u00b7E says: Ooops, this place has not the object {ID}";
	public static final String MESSAGE_PICK_OK 						= "I am happy! Now I have {ID}";
	public static final String MESSAGE_PLAYER_TOSTRING 				= "FUEL = {HEALTH}, RECYCLED MATERIAL ={SCORE}";
	public static final String MESSAGE_ROBOT_POWER 					= "      * My power is {ID}";
	public static final String MESSAGE_ROBOT_RECYCLED_MATERIAL 		= "      * My reclycled material is {ID}";
	public static final String MESSAGE_TRY_USE_ITEM_BUT_NOT_EXISTS 	= "I am stupid. I have not {ID}.";
	public static final String MESSAGE_UNIDIRECTIONAL_STREET 		= "WALL\u00b7E says: There is a door but in the opposite direction";
	public static final String MESSAGE_WALL 						= "What the hell am I supposed to do going to {DIRECTION} ?";
	public static final String MESSAGE_WHAT 						= "WALL\u00b7E says: I do not understand. Please repeat";
	public static final String PROMPT 								= "WALL\u00b7E> ";
	public static final String LINE_SEPARATOR						= System.getProperty("line.separator");
	
}
