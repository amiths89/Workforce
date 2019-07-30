package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.spo.Contractor;
import com.spo.Workforce;

class EffortsEstimationTest {

	@Test
	void test() {
		Workforce wf = new Workforce();
		int[] rooms = {35,17,21,28};
		Contractor con = new Contractor(rooms, 10, 6);
		int output = wf.RequiredEffortsForStructure(2, 1, 10,6);
		assertEquals("Calculated effort is correct", 26);
		fail("Not yet implemented");
	}

}
