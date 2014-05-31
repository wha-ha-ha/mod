package mod.util;

import java.util.ArrayList;

public class NumberUtil {

	public static ArrayList<Integer> getIntListFromBytes(int startIndex, int endIndex, byte[] bytes) {
		if((endIndex - startIndex) % 4 != 0) {
			throw new IllegalArgumentException("Got bad indices for converting byte array to int list - region to intify must be a multiple of 4 in size!");
		}
		
		ArrayList<Integer> ints = new ArrayList<Integer>();
		
		for(int i = startIndex; i < endIndex; i = i+4) {
			ints.add(((bytes[i] & 255) | ((bytes[i+1] & 255) << 8) | ((bytes[i+2] & 255) << 16) | (bytes[i+3] & 255) << 24));
		}
		
		return ints;
	}
	
	public static ArrayList<Byte> getByteListFromInt(int number) {
		ArrayList<Byte> bytes = new ArrayList<Byte>(4);
		bytes.add((byte) (number & 255));
		bytes.add((byte) ((number >> 8) & 255));
		bytes.add((byte) ((number >> 16) & 255));
		bytes.add((byte) ((number >> 24) & 255));
		
		return bytes;
	}
	
	public static byte[] getByteArrayFromList(ArrayList<Byte> bytes) {
		byte[] primBytes = new byte[bytes.size()];
		for(int i = 0; i < primBytes.length; i++) {
			primBytes[i] = bytes.get(i);
		}
		return primBytes;
	}
	
}
