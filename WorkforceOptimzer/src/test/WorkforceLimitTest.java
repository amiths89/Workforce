package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.spo.*;

public class WorkforceLimitTest {

	@Test
	public void test() {
		Workforce wf = new Workforce();
		int[] rooms = {35,17,21,28};
		Contractor con = new Contractor(rooms, 10, 6);
		double output = wf.calculateWorkforceLimit(con);
		assertEquals(4.0, output, "Calculated Limit value of team size is correct");
	}

}
