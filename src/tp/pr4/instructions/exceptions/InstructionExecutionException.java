package tp.pr4.instructions.exceptions;

public class InstructionExecutionException extends Exception 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InstructionExecutionException()
	{
		super();
	}
	public InstructionExecutionException(java.lang.String arg0)
	{
		System.out.println(arg0);
	}
	public InstructionExecutionException(java.lang.Throwable arg0)
	{
		super(arg0);
	}
	
	public InstructionExecutionException(java.lang.String arg0,
            java.lang.Throwable arg1)
	{
		super(arg0,arg1);
	}
}
