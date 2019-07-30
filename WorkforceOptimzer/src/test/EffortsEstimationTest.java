package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.spo.Contractor;
import com.spo.Workforce;

class EffortsEstimationTest {

	@Test
	void test() {
		Workforce wf = new Workforce();
		int output = wf.RequiredEffortsForStructure(2, 1, 10,6);
		assertEquals(26, output, "Calculated effort is correct");
	}

}
