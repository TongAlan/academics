package utills;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;



class JUnitTest {

	@Test
	void emptyArrayPassed() {  //Empty Array Passed In
		int array[] = {};
		Exception e = assertThrows(IllegalArgumentException.class, () -> {GreedyMakingChange.greedyMakingChange(array, 0);} );
		assertEquals("Array of size 0 is not allowed", e.getMessage());
	}
	
	@Test
	void testFor42() { //Testing for Greedy Making Change
		int array[] = {1,5,10,25};
		int makeChange = 42;
		ArrayList<Integer> testArrayList = new ArrayList<Integer>(Arrays.asList(25,10,5,1,1));
		assertEquals(testArrayList, GreedyMakingChange.greedyMakingChange(array, makeChange));
	}
	
	


}
