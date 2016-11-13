package io.github.rlee287.jrainbuck.uppering;

import java.util.ArrayList;

import io.github.rlee287.jrainbuck.constants.Constants;

public class UVCCreator implements UVCreator {
	private byte[] UVBBytes;
	private ArrayList<Byte> UVC=new ArrayList<Byte>();
	
	public UVCCreator(UVBCreator UVBObj) {
		UVBBytes=UVBObj.getUV();
	}
	@Override
	public byte[] getUV() {
		if (UVC.size()==0) {
			@SuppressWarnings("null")
			byte lastInst=(Byte) null;
			byte counter=0;
			byte toWrite=0;
			for (byte inst:UVBBytes) {
				if (lastInst==inst) {
					counter++;
					if (counter>Constants.UVC_MAX.get(inst)) {
						counter--; //Doesn't matter since it will be reset to 0 below
					}
					toWrite=(byte) (inst+2*counter);
					UVC.add(toWrite);
					if (counter>Constants.UVC_MAX.get(inst)) {
						counter=0;
						continue;
					}
				} else {
					UVC.add(inst);
				}
			}
		}
		byte[] returnUVC =new byte[UVC.size()];
		for (int index=0;index<UVC.size();index++) {
			returnUVC[index]=UVC.get(index);
		}
		return returnUVC;
	}

	@Override
	public char getUVType() {
		return 'C';
	}

}
