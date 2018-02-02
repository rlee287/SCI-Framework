package sci;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.reflections.Reflections;

import sci.modules.Module;
import sci.variables.Configuration;
import scilib.objects.TeamData;
import scilib.utilities.DataParser;
import scilib.utilities.Tokenizer;

public class SCI {

	private static ArrayList<TeamData> masterList;
	
	public static Configuration configuration;
	public static DecimalFormat df;
	public static HashMap<String, Module> modules; // groups.get("ALL")
	public static HashMap<String, ArrayList<TeamData>> groups;
	public static void main(String[] args) {
		configuration = new Configuration(); // This is initialized in this exact position for a reason;
		df = new DecimalFormat(configuration.decimalFormat);
		modules = new HashMap<String, Module>();
		groups = new HashMap<String, ArrayList<TeamData>>();
		init();
		System.out.println("Type \"help\" to see a list of commands.");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String line = sc.nextLine();
		while( run(line) ) {
			line = sc.nextLine();
		}
		
		System.out.println("Terminating Scouting Computer Interface...");
		shutdown();
		System.out.println("Program terminated.");
	}
	
	public static void init() {
		System.out.println("Initializing Scouting Computer Interface...");
		System.out.println("> Loading modules...");
		Reflections reflect = new Reflections("sci.modules");
		for( Class<?> c : reflect.getSubTypesOf( sci.modules.Module.class ) ) {
			Module m = null;
			try {
				m = (Module) c.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}

			if( ( m != null ) ) { // Java does not short-circut, so this is safe
				System.out.println("=> Loaded module: " + m.getName());
				modules.put(m.getInvoker(), m);
			}
		}
		System.out.println("> Modules loaded!");
		DataParser dp = new DataParser();
		System.out.println("> Loading Data File...");
		masterList = dp.process(configuration.dataFile);
		groups.put("ALL", masterList);
		System.out.println("=> File: " + configuration.dataFile);
		System.out.println("> Data File loaded!");
		System.out.println("Initialization complete.");
	}

	public static void shutdown() {
		// TODO: Close all GUIs
	}
	
	public static boolean run(String line) { 
		boolean live = true;
		Tokenizer t = new Tokenizer();
		ArrayList<String> tokens = t.tokenize(line);
		String invoker = tokens.remove(0);
		line = line.substring(invoker.length()); // remove the first word and space
		line.trim();
		String response = "Unrecognized command: \"" + invoker + "\". Type \"help\" to see a list of commands.";
		for( String s : modules.keySet() ) {
			if( s.equalsIgnoreCase(invoker) ) {
				Module m = modules.get(s);
				response = m.process(line, tokens);
			}
		}
		System.out.println(response);
		return live;
	}
	
	
}
