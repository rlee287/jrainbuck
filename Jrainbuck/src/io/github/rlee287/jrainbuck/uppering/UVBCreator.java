package io.github.rlee287.jrainbuck.uppering;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import io.github.rlee287.jrainbuck.constants.Constants;

public class UVBCreator implements UVCreator {
	public byte[] rawInput;
	private String inputFile;
	private ArrayList<Byte> UVB=new ArrayList<Byte>();
	public UVBCreator(String inputFile) {
		this.inputFile=inputFile;
	}
	private void readRaw() throws IOException {
		Path inputFilePath=Paths.get(inputFile);
		rawInput=Files.readAllBytes(inputFilePath);
	}
	private void emitUV() {
		/*
		 * 0 |0 |0 |0 |0 |0 |0 |0
		 * []|+-|<>|.,|00|00|00|sign
		 */
		for ( byte inst:rawInput ) {
			Character in=new Character((char) inst);
			Byte mapped=Constants.UVB_MAP.get(in);
			if (mapped!=null) {
				UVB.add(mapped);
			}
		}
	}
	/* (non-Javadoc)
	 * @see io.github.rlee287.jrainbuck.uppering.UVCreator#getUV()
	 */
	@Override
	public byte[] getUV() {
		if ( UVB.size()==0 ) {
			/* Re-emit UVB only if necessary
			 * UV can be quite dangerous if re-emitted
			 * (CPU usage, sunburn :-) [extra )]? 
			 */
			try {
				this.readRaw();
			} catch (IOException e) {
				// Should never happen
				System.err.println("An error has occured during uppering.");
				e.printStackTrace();
			}
			this.emitUV();
		}
		byte[] returnUVB =new byte[UVB.size()];
		for (int index=0;index<UVB.size();index++) {
			returnUVB[index]=UVB.get(index);
		}
		return returnUVB;
	}
}
