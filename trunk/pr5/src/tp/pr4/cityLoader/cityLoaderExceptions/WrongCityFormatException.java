package tp.pr4.cityLoader.cityLoaderExceptions;

import java.io.IOException;

public class WrongCityFormatException extends IOException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public WrongCityFormatException() 
	{
		super();
	}
	public WrongCityFormatException(String msg) 
	{
		super(msg);
	}
	public WrongCityFormatException(String msg, Throwable arg)
	{
		super(msg, arg);
	}
	public WrongCityFormatException(Throwable arg)
	{
		super(arg);
	}

}
