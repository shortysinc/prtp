package tp.pr5;

import tp.pr5.instructions.*;
import tp.pr5.instructions.exceptions.*;


/**
 * The interpreter is in charge of converting the user input in an instruction for the robot. Up to now, the valid instructions are:
 * MOVE
 * TURN { LEFT | RIGHT }
 * HELP
 * QUIT
 */
public class Interpreter 
{
	
	
	/**
	 * Generates a new instruction according to the user input
	 * @param line - A string with the user input
	 * @return The instruction read from the given line. If the instruction is not correct, 
	 * 		   then it returns a not valid instruction
	 * @throws InstructionExecutionException 
	 */
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
	
	
	public static Instruction generateInstruction(String line) throws WrongInstructionFormatException
	{
		
		//String[] cmd=line.trim().split(" +");
		//otro metodo
		/*StringTokenizer cmd= new StringTokenizer(line, " ");*/
		
		
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
		/*
		if(cmd.countTokens()>0){
			switch (cmd.nextToken().toUpperCase()){
				
				case "MOVE":
					if(!cmd.hasMoreTokens()){
						instruction= new Instruction(Action.MOVE);
					}
					break;
				 
				case "TURN":
					action=Action.TURN;
					
					if(cmd.hasMoreTokens()){
						
						String cmdRot=cmd.nextToken().toUpperCase();
						if(!cmd.hasMoreTokens())
						{
							if(cmdRot.equals("LEFT"))
							{
								rotation=Rotation.LEFT;
							} 
							else if(cmdRot.equals("RIGHT")) 
							{
								rotation=Rotation.RIGHT;
							}
						}
					}
					instruction= new Instruction(action,rotation);
					break;
					
				case "QUIT":
					if(!cmd.hasMoreTokens())
					{
						action=Action.QUIT;
						instruction= new Instruction(action);
					}
					break;
				
				case "HELP":
					if(!cmd.hasMoreTokens()){
						action=Action.HELP;
						instruction= new Instruction(action);
					}
					break;
				
				case "PICK":
					action=Action.PICK;
					if(cmd.hasMoreTokens()){
						String id=cmd.nextToken();
						if(!cmd.hasMoreTokens()){
							instruction= new Instruction(action,id);
						} else {
							instruction= new Instruction(action);
						}
					}
					else{
						instruction= new Instruction(action);
					}
					
					break;
				
				case "OPERATE":
					action=Action.OPERATE;
					if(cmd.hasMoreTokens())
					{
						String id=cmd.nextToken();
						if(!cmd.hasMoreTokens())
						{
							instruction= new Instruction(action,id);
						} 
						else 
						{
							instruction= new Instruction(action);
						}
					}
					else
					{
						instruction= new Instruction(action);
					}
					break;
					
				case "SCAN":
					action=Action.SCAN;
					if(cmd.hasMoreTokens())
					{
						String id=cmd.nextToken();
						if(!cmd.hasMoreTokens())
						{
							instruction= new Instruction(action,id);
						} 
						else 
						{
							instruction= new Instruction(action);
						}
					}
					else
					{
						instruction= new Instruction(action);
					}
					break;
	
			}
		}
		*/
		
		/*
		 
		 */
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
