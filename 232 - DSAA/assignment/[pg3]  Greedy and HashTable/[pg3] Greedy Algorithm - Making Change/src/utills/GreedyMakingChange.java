package utills;

import java.util.*;

public class GreedyMakingChange {
	
	public static ArrayList<Integer> greedyMakingChange(int[] coinSystem, int totalCost) {
		ArrayList<Integer> returnArray = new ArrayList<Integer>();
		if(coinSystem.length == 0) {
			throw new IllegalArgumentException("Array of size 0 is not allowed");
		}
		
		Arrays.sort(coinSystem);

		int target = totalCost;
		while(target > 0) {
			for(int i = coinSystem.length-1; i>-1;i--) {
				int max = (int)(target/coinSystem[i]);
				target  = target - (max * coinSystem[i]);
				for(int j = 0; j<max;j++) {
					returnArray.add(coinSystem[i]);
				}
			}
		}
		return returnArray;
	}
	
}
