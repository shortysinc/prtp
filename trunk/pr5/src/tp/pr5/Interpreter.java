package tp.pr5;
//uni doc ok
//otros doc ok
//imp ok
import tp.pr5.instructions.*;
import tp.pr5.instructions.exceptions.*;


/**
 * The interpreter is in charge of converting the user input in an instruction for the robot. Up to now, the valid instructions are:
 * MOVE | MOVER
 * TURN | GIRAR { LEFT | RIGHT }
 * PICK | COGER <ITEM>
 * DROP | SOLTAR <ITEM>
 * SCAN | ESCANEAR [ <ITEM> ]
 * RADAR
 * OPERATE | OPERAR <ITEM>
 * HELP | AYUDA
 * QUIT | SALIR
 */
public class Interpreter 
{
	
	
	
	private static Instruction[] instructions= new Instruction[]
	{
		new MoveInstruction(),
		new TurnInstruction(),
		new PickInstruction(),
		new ScanInstruction(),
		new OperateInstruction(),
		new RadarInstruction(),
		new DropInstruction(),
		new HelpInstruction(),
		new QuitInstruction(),
		
	};
	
	/**
	 * Generates a new instruction according to the user input
	 * @param line - A string with the user input
	 * @return The instruction read from the given line. If the instruction is not correct, then it throws an exception.
	 * @throws WrongInstructionFormatException
	 */
	public static Instruction generateInstruction(String line) throws WrongInstructionFormatException
	{
		Instruction cmd=null;
		for (int i = 0; i < instructions.length; i++) 
		{
			try
			{
				cmd = instructions[i].parse(line);
				return cmd;
			} 
			catch (WrongInstructionFormatException e)
			{
				//continue
			}
		}
		throw new WrongInstructionFormatException();
	}
	
	
	/**
	 * It returns information about all the instructions that the robot understands
	 * @return A string with the information about all the available instructions
	 */
	public static String interpreterHelp()
	{
		
		String help="";
		for (int i = 0; i < instructions.length; i++) 
		{
			help+=instructions[i].getHelp() + "\n";
		}
		
		return "The valid instructions for WALL-E are:" + "\n" + help;
		
	}
	
}
