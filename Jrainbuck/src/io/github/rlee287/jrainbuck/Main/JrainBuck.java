package io.github.rlee287.jrainbuck.Main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import io.github.rlee287.jrainbuck.constants.SwitchList;

import java.io.File;

public class JrainBuck {

	public static void main(String[] args) {
		/*System.out.println("List of Arguments");
		for (String a : args){
			System.out.println(a);
		}*/
		HashMap <String,String> arguments=CommandParse(args);
		if (arguments==null) {
			System.err.println("Try using -help for more information.");
			System.runFinalization();
			System.exit(0);
		}
		if (arguments.containsKey("ranhelp")) {
			System.runFinalization();
			System.exit(0);
		}
	}

	public static HashMap <String,String> CommandParse(String[] args) {
		List<String> ArgumentsAsList = Arrays.asList(args);
		HashMap <String,String> Arguments=new HashMap<String, String>(4);
		if (ArgumentsAsList.contains("-help")) {
			System.out.println("This is help message");
			Arguments.put("ranhelp", "dummy");
			return Arguments;
		}
		System.err.println();
		for ( String Switch : SwitchList.LIST_SWITCHES) {
			int IndexFile=ArgumentsAsList.indexOf(Switch);
			if (IndexFile==-1) {
				if (SwitchList.LIST_SWITCHES[0]==Switch){
					System.err.println("Error: A file must be specified with "+Switch);
					return null;
				}
				else {
					Arguments.put(Switch.substring(1), "\200");
				}
			}
			else {
				try {
					String filename=args[IndexFile+1];
					File filePath=new File(filename);
					if ( !filePath.isFile() ) {
						throw new ArrayIndexOutOfBoundsException();
					}
					Arguments.put(Switch.substring(1), filename);
				}
				catch (ArrayIndexOutOfBoundsException e) {
					System.err.println("Error: "+Switch+" given without valid filename");
					return null;
				}
			}
		}
		return Arguments;
	}
}
