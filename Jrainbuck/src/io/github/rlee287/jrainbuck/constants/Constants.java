package io.github.rlee287.jrainbuck.constants;

import java.util.HashMap;

public class Constants {
	//public static final int CLASSIC_SIZE=30000;
	public static final String[] LIST_SWITCHES={"-file","-input","-output"};
	/* 0|file   MANDATORY
	 * 1|stdin  OPTIONAL
	 * 2|stdout OPTIONAL
	 */
	public static final Character[] ALLOWED_CHAR={'[',']','+','-','<','>','.',','};
	private static HashMap<Character, Byte> INIT_UVB_MAP=
			new HashMap <Character,Byte>();
	static {
		/* JUMP_FORWARD_ZERO 0b1000 0001*/
		INIT_UVB_MAP.put('[', new Byte((byte)-127));
		/* JUMP_BACKWARD_NONZERO 0b1000 0000*/
		INIT_UVB_MAP.put(']', new Byte((byte)-128));
		/* ARRAY_INCREMENT 0b0100 0001*/
		INIT_UVB_MAP.put('+', new Byte((byte)65));
		/* ARRAY_DECREMENT 0b0100 0000*/
		INIT_UVB_MAP.put('-', new Byte((byte)64));
		/* POINTER_LEFT 0b0010 0000*/
		INIT_UVB_MAP.put('<', new Byte((byte)32));
		/* POINTER_RIGHT 0b0010 0001*/
		INIT_UVB_MAP.put('>', new Byte((byte)33));
		/* STDOUT 0b0001 0000*/
		INIT_UVB_MAP.put('.', new Byte((byte)16));
		/* STDIN 0b0001 0001*/
		INIT_UVB_MAP.put(',', new Byte((byte)17));
	}
	private static HashMap<Character, Byte> INIT_UVC_MAX=
			new HashMap <Character, Byte>();
	static {
		INIT_UVC_MAX.put('[', new Byte((byte)63));
		INIT_UVC_MAX.put(']', new Byte((byte)63));
		INIT_UVC_MAX.put('+', new Byte((byte)31));
		INIT_UVC_MAX.put('-', new Byte((byte)31));
		INIT_UVC_MAX.put('<', new Byte((byte)15));
		INIT_UVC_MAX.put('>', new Byte((byte)15));
		INIT_UVC_MAX.put('.', new Byte((byte)7));
		INIT_UVC_MAX.put(',', new Byte((byte)7));
	}
	public static final HashMap<Character,Byte> UVB_MAP=INIT_UVB_MAP;
	public static final HashMap<Character,Byte> UVC_MAX=INIT_UVC_MAX;
	/*
	 * 0 |0 |0 |0 |0 |0 |0 |0
	 * []|+-|<>|.,|00|00|00|sign
	 */
	public static final int ARRAY_SIZE = 10000;
}
