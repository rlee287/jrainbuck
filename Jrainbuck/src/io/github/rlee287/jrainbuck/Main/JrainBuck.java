package io.github.rlee287.jrainbuck.Main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import io.github.rlee287.jrainbuck.constants.Constants;
import io.github.rlee287.jrainbuck.uppering.UVBCreator;
import io.github.rlee287.jrainbuck.uppering.UVCreator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class JrainBuck {

	public static void main(String[] args) {
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
		UVCreator uppering=new UVBCreator(arguments.get("file"));
		byte[] test=uppering.getUV();
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
		for ( String Switch : Constants.LIST_SWITCHES) {
			int IndexFile=ArgumentsAsList.indexOf(Switch);
			if (IndexFile==-1) {
				if (Constants.LIST_SWITCHES[0]==Switch){
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
