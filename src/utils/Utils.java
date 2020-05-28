package utils;

import java.util.ArrayList;

public class Utils {
	public static ArrayList<Integer> intArraytoArrayList(int[] arr) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int value : arr) {
			result.add(value);
		}
		return result;
	}

}
