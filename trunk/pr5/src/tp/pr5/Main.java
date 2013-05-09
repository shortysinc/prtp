package tp.pr5;
//uni doc ok
//otros doc ok
//imp ok
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.text.html.MinimalHTMLWriter;

import org.apache.commons.cli.*;

import tp.pr5.cityLoader.CityLoaderFromTxtFile;
import tp.pr5.cityLoader.cityLoaderExceptions.WrongCityFormatException;
import tp.pr5.console.ConsoleController;
import tp.pr5.gui.GUIController;
import tp.pr5.gui.MainWindow;

public class Main 
{
	static String LINE_SEPARATOR = System.getProperty("line.separator");
	/**
	 * Application entry-point
	 * @param args
	 */
	public static void main(String[] args) {
		
		String 				i 		= null;
		String            	map     = null;
        CommandLineParser	parser  = null;
        CommandLine       	cmdLine = null;
		
		Options options = new Options();
		
		Option interfaces= new Option("i", "interface", true, "The type of interface: console or swing");
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
	        	throw new org.apache.commons.cli.ParseException("Map file not specified"); 
	        }

	        if (cmdLine.hasOption("i")){
	        	i = cmdLine.getOptionValue("i");
	        	if(i==null || !i.equalsIgnoreCase("console") && !i.equalsIgnoreCase("swing")){
	        		throw new org.apache.commons.cli.ParseException("Wrong type of interface"); 
	        	}
	        } else {
	        	throw new org.apache.commons.cli.ParseException("Interface not specified"); 
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
				
				if(i.equalsIgnoreCase("swing")){
					GUIController gc = new GUIController(engine);
					MainWindow mainWindow = new MainWindow(gc);
					gc.startController();
					
				} else {
					ConsoleController cc = new ConsoleController(engine);
					cc.startController();
				}
				
			} catch(WrongCityFormatException e){
				System.err.println("Error the map is incorrect");
				System.exit(2);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
	        
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
		
	}

}
