package tp.pr5;
//uni doc ok
//otros doc ok
//imp ok
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import tp.pr5.cityLoader.CityLoaderFromTxtFile;
import tp.pr5.cityLoader.cityLoaderExceptions.WrongCityFormatException;
import tp.pr5.console.ConsoleController;
import tp.pr5.gui.GUIController;
import tp.pr5.gui.MainWindow;

public class Main 
{
	static String LINE_SEPARATOR = System.getProperty("line.separator");
	/**
	 * Application entry-point. The application admits a parameter -m | --map with the name of the 
	 * map file to be used, a parameter -i | --interface with the type of interface (console, swing or both) 
	 * and a parameter -h | --help to show a help message about how to use this application.
	 
	 * If no arg is specified (or more than one file is given), it prints an error message (in System.err) and 
	 * the application finishes with an error code (-1).

	 * If the map file cannot be read (or it does not exist), the application ends with a different 
	 * error code (-2).

	 * If the interface arg is not correct (console, swing or both) the application prints a message and 
	 * the application finishes with an error code (-3). Otherwise, the simulation starts and eventually 
	 * the application will end normally (return code 0).
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		String 				i 		= null;
		String            	map     = null;
        CommandLineParser	parser  = null;
        CommandLine       	cmdLine = null;
		
		Options options = new Options();
		
		Option interfaces= new Option("i", "interface", true, "The type of interface: console, swing or both");
		interfaces.setArgName("type");
		
		Option maps= new Option("m","map",true, "File with the description of the city");
		maps.setArgName("mapfile");
		
		options.addOption("h","help",false, "Shows this help message");
		
		options.addOption(interfaces);
		options.addOption(maps);
		
		try{
			parser  = new BasicParser();
	        cmdLine = parser.parse(options, args);
	        
	        if (cmdLine.hasOption("h")){
	        	//Execute this assignment with these parameters:
	        	HelpFormatter help= new HelpFormatter();
	        	System.out.println("Execute this assignment with these parameters:");
	        	help.printHelp(Main.class.getCanonicalName(),options,true );
	        	return;
	        }
	        
	        if(!cmdLine.hasOption("m") || (map = cmdLine.getOptionValue("m"))==null){
	        	throw new org.apache.commons.cli.MissingArgumentException("Map file not specified"); 
	        }

	        if (cmdLine.hasOption("i")){
	        	i = cmdLine.getOptionValue("i");
	        	if(i==null || (!i.equalsIgnoreCase("console") && !i.equalsIgnoreCase("swing") && !i.equalsIgnoreCase("both"))){
	        		throw new org.apache.commons.cli.UnrecognizedOptionException("Wrong type of interface"); 
	        	}
	        } else {
	        	throw new org.apache.commons.cli.MissingArgumentException("Interface not specified"); 
	        }
	        
	        
	        File file= new File(map);
			
			//File file= new File("src/tp/pr3/cityLoader/file.txt");
			FileInputStream stream;
			InputStream input=null;
			
			try {
				stream = new FileInputStream(file);
				input = stream;
			} catch (FileNotFoundException e1) {
				System.err.println("Error reading the map file: noExiste.txt (No existe el fichero o el directorio)");
				System.exit(2);
			}
	
			CityLoaderFromTxtFile city= new CityLoaderFromTxtFile();
			
			try {
				RobotEngine engine;
				
				engine = new RobotEngine(city.loadCity(input),city.getInitialPlace(),Direction.NORTH);
				
				GUIController gc;
				MainWindow mainWindow;
				ConsoleController cc;
								
				switch (i.toUpperCase()) {
				
					case "SWING":
						gc = new GUIController(engine);
						mainWindow = new MainWindow(gc);
						gc.startController();
						break;
	
					case "CONSOLE":
						cc = new ConsoleController(engine);
						cc.startController();
						break;
						
					case "BOTH":
						gc = new GUIController(engine);
						mainWindow = new MainWindow(gc);
						gc.startController();
						cc = new ConsoleController(engine);
						
						break;
						default:
							System.out.print("");
				}
				
			} catch(WrongCityFormatException e){
				System.err.println("Error the map is incorrect");
				System.exit(2);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
	        
		} catch (org.apache.commons.cli.UnrecognizedOptionException e) {
			System.err.println(e.getMessage());
			System.exit(3);
		} catch (org.apache.commons.cli.MissingArgumentException e){
			System.err.println(e.getMessage());
			System.exit(1);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
		
	}

}
