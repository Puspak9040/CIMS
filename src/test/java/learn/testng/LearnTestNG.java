package learn.testng;

import org.testng.annotations.Test;
import java.text.MessageFormat;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cycle.Cycle;

public class LearnTestNG {

	@DataProvider(name = "my_test_data")
	Object[][] getData() {

		String testData[][] = { { "Role No", "Name", "Religion" }, { "512", "Debasish", "Hindu" },
				{ "500", "Pushpak", "NA" }, };

		return testData;

	}

	@DataProvider(name = "my_test_cycles")
	Object[][] getCycles() {

		int count = 0;
		Cycle objCycles[][] = new Cycle[4][1];
		String[][] modelcolorArr = { { "Avon", "Orange" }, { "Hero", "Red" }, { "Atlas", "Pink" }, { "TVS", "Gry" } };

		for (String[] modelcolorpair : modelcolorArr) {
			{
				String model = modelcolorpair[0];
				String color = modelcolorpair[1];
				Cycle c1 = new Cycle(model, color);
				objCycles[count++][0] = c1;

			}
		}

		return objCycles;

	}

	@Test(dataProvider = "my_test_data")
	public void testDataProviderForTest(String testData1, String testData2, String testData3) {
		System.out.println(testData1);
		System.out.println(testData2);
		System.out.println(testData3);
	}

	@Test(dataProvider = "my_test_cycles")
	public void testDataProviderForCycles(Cycle c) {
		System.out.println(MessageFormat.format("Cycle Model - {0}, Cycle Color - {1}", c.getModel(), c.getColor()));

	}
}
