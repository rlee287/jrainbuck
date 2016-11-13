package io.github.rlee287.jrainbuck.topend;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import io.github.rlee287.jrainbuck.constants.Constants;
import io.github.rlee287.jrainbuck.uppering.UVCreator;

public class LITRun {
	private UVCreator UV;
	private final byte[] UVByte;
	public HashMap<Integer,Integer> rowList = new HashMap<Integer,Integer>();
	public LITRun(UVCreator UV) {
		this.UV=UV;
		this.UVByte=UV.getUV();
	}
	public void bracketTable() {
		byte counterOpen=0;
		byte counterClose=0;
		int ptr=0;
		ArrayList<Integer> listOpen=new ArrayList<Integer>();
		ArrayList<Integer> listClose=new ArrayList<Integer>();
		for (byte inst:UVByte) {
			if ((inst&(byte)(-128))!=0) {
				if ((inst&(byte)1)==1) {
					while(counterOpen>=listOpen.size()) {
						listOpen.add(null);
					}
					listOpen.remove(counterOpen);
					listOpen.add(counterOpen,ptr);
					counterOpen++;
				}
				else {
					while(counterClose>=listClose.size()) {
						listClose.add(null);
					}
					listClose.remove(counterClose);
					listClose.add(counterClose,ptr);
					counterClose++;
				}
			}
			ptr++;
		}
		if (listOpen.size()==listClose.size()) {
			for (int si=0;si<listOpen.size();si++) {
				rowList.put(listOpen.get(si), listClose.get(si));
			}
			for (int si=0;si<listClose.size();si++) {
				rowList.put(listClose.get(si),listOpen.get(si));
			}
		}
		else {
			System.out.println("Size Mismatch");
		}
	}
	public void execute() {
		byte[] turingTape=new byte[Constants.ARRAY_SIZE];
		int tapePtr=0;
		int instPtr=0;
		this.bracketTable();
		switch(UV.getUVType()) {
		case 'A': break;
		case 'B':
			byte[] instTape=UV.getUV();
			System.out.println(instTape.length);
			while(instPtr<instTape.length) {
				System.err.print("PointerTape ");
				System.err.println(tapePtr);
				System.err.print("PointerInst ");
				System.err.println(instPtr);
				byte inst = instTape[instPtr];
				if ((inst&(byte)(64))!=0) {
					if ((inst&(byte)1)==1) {
						System.err.println("Incrementing value");
						turingTape[tapePtr]++;
					}
					else {
						System.err.println("Decrementing value");
						turingTape[tapePtr]--;
					}
				}
				if ((inst&(byte)(32))!=0) {
					if ((inst&(byte)1)==1) {
						System.err.println("Incrementing pointer");
						tapePtr++;
					}
					else {
						System.err.println("Decrementing pointer");
						tapePtr--;
					}
				}
				if ((inst&(byte)(16))!=0) {
					if ((inst&(byte)1)==0) {
						System.out.println(turingTape[tapePtr]);
					}
					else {
						try {
							turingTape[tapePtr]=(byte)System.in.read();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							//Leave value unchanged
						}
					}
				}
				if ((inst&(byte)(-128))!=0) {
					System.err.println("Jumping point");
					System.err.print("Current tape value is ");
					System.err.println(turingTape[tapePtr]);
					if ((inst&(byte)1)==1) { //'['
						//Check for zero
						if (turingTape[tapePtr]==0) {
							instPtr=this.rowList.get(instPtr);
						}
						else {
							instPtr++;
						}
					}
					else { // ']'
						if (turingTape[tapePtr]!=0) {
							instPtr=this.rowList.get(instPtr);
						}
						else {
							instPtr++;
						}
					}
				}
				else {
					instPtr++;
				}
			}
			break;
		case 'C': break;
		default: break;
		}
	}
}
