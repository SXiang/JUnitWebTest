package surveyor.unittest.source;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import common.source.FileUtility;
import common.source.HostSimInstructions;
import common.source.HostSimInstructions.Action;
import common.source.HostSimInstructions.Anemometer;
import common.source.HostSimInstructions.Gps;
import common.source.HostSimInstructions.Measurement;
import common.source.HostSimInstructions.Selector;

public class HostSimInstructionsTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSelector() throws IOException {
		final List<String> expectedFileLines = Arrays.asList(new String[] {"every(2000)"});

		HostSimInstructions instructions = new HostSimInstructions("UnitTest_testSelector");
		instructions.addSelector(Selector.EveryM, 2000);
		String filePath = instructions.createFile();
		assertTrue(FileUtility.fileExists(filePath));
		assertTrue(FileUtility.readFileLinesToList(filePath).equals(expectedFileLines));
		FileUtility.deleteFile(Paths.get(filePath));
	}

	@Test
	public void testSelectorOneAction() throws IOException {
		final List<String> expectedFileLines = Arrays.asList(new String[] {"every(2000)", "update('CH4',0)"});

		HostSimInstructions instructions = new HostSimInstructions("UnitTest_testSelector");
		instructions.addSelector(Selector.EveryM, 2000)
			.addMeasurementAction(Action.Update, Measurement.Column.CH4, "0");
		String filePath = instructions.createFile();
		assertTrue(FileUtility.fileExists(filePath));
		assertTrue(FileUtility.readFileLinesToList(filePath).equals(expectedFileLines));
		FileUtility.deleteFile(Paths.get(filePath));
	}

	@Test
	public void testSelectorMultipleActions() throws IOException {
		final List<String> expectedFileLines = Arrays.asList(new String[] {"every(1000000,5000)", "update('CH4',0)", "updateFieldBy('GPS_ABS_LAT',0.5)"});

		HostSimInstructions instructions = new HostSimInstructions("UnitTest_testSelector");
		instructions.addSelector(Selector.EveryMK, 1000000, 5000)
			.addMeasurementAction(Action.Update, Measurement.Column.CH4, "0")
			.addGpsAction(Action.UpdateFieldBy, Gps.Column.GPS_ABS_LAT, "0.5");
		String filePath = instructions.createFile();
		assertTrue(FileUtility.fileExists(filePath));
		assertTrue(FileUtility.readFileLinesToList(filePath).equals(expectedFileLines));
		FileUtility.deleteFile(Paths.get(filePath));
	}

	@Test
	public void testEmptyInstruction() throws IOException {
		HostSimInstructions instructions = new HostSimInstructions("UnitTest_testSelector");
		String filePath = instructions.createFile();
		assertTrue(FileUtility.fileExists(filePath));
		assertTrue(FileUtility.readFileLinesToList(filePath).size()==0);
		FileUtility.deleteFile(Paths.get(filePath));
	}

	@Test
	public void testOneAction() throws IOException {
		final List<String> expectedFileLines = Arrays.asList(new String[] {"drop()"});

		HostSimInstructions instructions = new HostSimInstructions("UnitTest_testSelector");
		instructions.addMeasurementAction(Action.Drop, Measurement.Column.CH4);
		String filePath = instructions.createFile();
		assertTrue(FileUtility.fileExists(filePath));
		assertTrue(FileUtility.readFileLinesToList(filePath).equals(expectedFileLines));
		FileUtility.deleteFile(Paths.get(filePath));
	}

	@Test
	public void testMultipleActions_withNaN() throws IOException {
		final List<String> expectedFileLines = Arrays.asList(new String[] {"every(2000)",
				"updateRandom('WS_STATUS',1,0.1)",
				"update('CH4',numpy.float64(numpy.nan))",
				"insertPeak('CH4',5.5,1,0.01,'insert_peak_ampl_5_5_sigma_1_randomizer_1.log')"});

		HostSimInstructions instructions = new HostSimInstructions("UnitTest_testSelector");
		instructions.addSelector(Selector.EveryM, 2000)
			.addAnemometerAction(Action.UpdateRandom, Anemometer.Column.WS_STATUS, "1","0.1")
			.addMeasurementAction(Action.Update, Measurement.Column.CH4, "numpy.float64(numpy.nan)")
			.addMeasurementAction(Action.InsertPeak, Measurement.Column.CH4, "5.5", "1", "0.01", "insert_peak_ampl_5_5_sigma_1_randomizer_1.log");
		String filePath = instructions.createFile();
		assertTrue(FileUtility.fileExists(filePath));
		assertTrue(FileUtility.readFileLinesToList(filePath).equals(expectedFileLines));
		FileUtility.deleteFile(Paths.get(filePath));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidActionParams_throwsException() {
		HostSimInstructions instructions = new HostSimInstructions("UnitTest_testSelector");
		instructions.addSelector(Selector.EveryM, 2000)
			.addAnemometerAction(Action.UpdateFieldBit, Anemometer.Column.WS_STATUS, "1");
	}
}
