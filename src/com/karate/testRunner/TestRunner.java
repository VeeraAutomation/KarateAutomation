package com.cognizant.testRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import com.cognizant.utilities.ExcelHandling;
import com.intuit.karate.Results;
import com.intuit.karate.Runner;

public class TestRunner {

	@Test
	void executeTest() {
	//	ExcelHandling.storeDataDetails();
	//	ExcelHandling.getExecutableScenariosWithIteratios();
		Results results = Runner.path("classpath:")
				.tags("Corporate")
				.parallel(1);
		assertEquals(0, results.getFailCount(), results.getErrorMessages());
	}
}
