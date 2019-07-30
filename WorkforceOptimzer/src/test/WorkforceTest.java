package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.spo.*;

public class WorkforceTest {

	@Test
	public void calculateWorkforceLimitTest() {
		Workforce wf = new Workforce();
		int[] rooms = {35,17,21,28};
		Contractor con = new Contractor(rooms, 10, 6);
		double output = wf.calculateWorkforceLimit(con);
		assertEquals(4.0, output, "Calculated Limit value of team size is correct");
	}
	
	@Test
	void RequiredEffortsForStructureTest() {
		Workforce wf = new Workforce();
		int output = wf.RequiredEffortsForStructure(2, 1, 10,6);
		assertEquals(26, output, "Calculated effort is correct");
	}
	
	@Test
	void optimizeWorkforceTest() {
		Workforce wf = new Workforce();
		int[] rooms = {35,21,17,28};
		Contractor con = new Contractor(rooms, 10, 6);
		double limit = wf.calculateWorkforceLimit(con);
		int[][] output = wf.optimizeWorkforce(con, limit);
		
		assertEquals(3, output[3][0], "Calculated effort for "+rooms[0]+" passed");
		assertEquals(1, output[3][1], "Calculated effort for "+rooms[0]+" passed");
		assertEquals(1, output[2][0], "Calculated effort for "+rooms[3]+" passed");
		assertEquals(3, output[2][1], "Calculated effort for "+rooms[3]+" passed");
		assertEquals(1, output[1][0], "Calculated effort for "+rooms[1]+" passed");
		assertEquals(2, output[1][1], "Calculated effort for "+rooms[1]+" passed");
		assertEquals(2, output[0][0], "Calculated effort for "+rooms[2]+" passed");
		assertEquals(0, output[0][1], "Calculated effort for "+rooms[2]+" passed");
	}

}
