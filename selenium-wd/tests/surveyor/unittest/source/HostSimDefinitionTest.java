package surveyor.unittest.source;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import org.junit.Test;

import common.source.Log;
import common.source.FileUtility;
import common.source.HostSimDataConditionerNode;
import common.source.HostSimDataConditionerNode.ConditionerMethod;
import common.source.HostSimDbReaderNode;
import common.source.HostSimDefinition;
import common.source.HostSimInstructions;
import common.source.HostSimDefinition.Option;
import common.source.HostSimInstructions.Action;
import common.source.HostSimInstructions.Anemometer;
import common.source.HostSimInstructions.Measurement;
import common.source.HostSimInstructions.Selector;
import common.source.HostSimStreamMergerNode;
import common.source.HostSimNode;
import common.source.HostSimNode.HostSimNodeTarget;
import common.source.HostSimDataTransformerNode;
import common.source.HostSimZmqSenderNode;
import common.source.HostSimZmqSenderNode.Mode;

public class HostSimDefinitionTest {

	private HostSimNode anemNode = null;
	private HostSimNode gpsNode = null;
	private HostSimNode measNode = null;
	private HostSimNode mergerNode = null;
	private HostSimNode zmqSenderNode = null;

	@Test
	public void testDefnFEDSMode() throws IOException {
		final int EXPECTED_NUM_LINES = 10;   // for 5 nodes = 5*2

		String defnFile = createBasicHostSimDefinition("replayDb3-meth.defn", Mode.FEDS_mode, 30, null)
							.writeFile();

		int numLines = FileUtility.readFileLinesToList(defnFile).size();

		Log.info(String.format("Defn file created at -> [%s]", defnFile));
		Log.info(String.format("Defn file : number of lines = %d", numLines));

		assertTrue(FileUtility.fileExists(defnFile));
		assertTrue(numLines==EXPECTED_NUM_LINES);
		Log.info(FileUtility.readFileContents(defnFile));
		FileUtility.deleteFile(Paths.get(defnFile));
	}

	@Test
	public void testDefnFEDSMode_WithOptions() throws IOException {
		final int EXPECTED_NUM_LINES = 11;   // for 5 nodes = 5*2 + 1 option line

		String defnFile = createBasicHostSimDefinition("replayDb3-meth.defn", Mode.FEDS_mode, 30, null)
							.addOption(Option.iCH4)
							.writeFile();

		int numLines = FileUtility.readFileLinesToList(defnFile).size();

		Log.info(String.format("Defn file created at -> [%s]", defnFile));
		Log.info(String.format("Defn file : number of lines = %d", numLines));

		assertTrue(FileUtility.fileExists(defnFile));
		assertTrue(numLines==EXPECTED_NUM_LINES);
		Log.info(FileUtility.readFileContents(defnFile));
		FileUtility.deleteFile(Paths.get(defnFile));
	}

	@Test
	public void testDefnRFADSMode_WithOptions() throws IOException {
		final int EXPECTED_NUM_LINES = 11;   // for 5 nodes = 5*2 + 1 option line

		String defnFile = createBasicHostSimDefinition("replayDb3-eth.defn", Mode.RFADS_mode, 60, null)
							.addOption(Option.Ethane)
							.writeFile();

		int numLines = FileUtility.readFileLinesToList(defnFile).size();

		Log.info(String.format("Defn file created at -> [%s]", defnFile));
		Log.info(String.format("Defn file : number of lines = %d", numLines));

		assertTrue(FileUtility.fileExists(defnFile));
		assertTrue(numLines==EXPECTED_NUM_LINES);
		Log.info(FileUtility.readFileContents(defnFile));
		FileUtility.deleteFile(Paths.get(defnFile));
	}

	@Test
	public void testDefnRFADSMode_WithDataConditioner() throws IOException {
		final int EXPECTED_NUM_LINES = 13;   // for 6 nodes = 6*2 + 1 option line

		String defnFile = createBasicHostSimDefinition("replayDb3-data-cond-eth.defn", Mode.RFADS_mode, 60, () -> createDataConditionerNode())
							.addOption(Option.Ethane)
							.writeFile();

		int numLines = FileUtility.readFileLinesToList(defnFile).size();

		Log.info(String.format("Defn file created at -> [%s]", defnFile));
		Log.info(String.format("Defn file : number of lines = %d", numLines));

		assertTrue(FileUtility.fileExists(defnFile));
		assertTrue(numLines==EXPECTED_NUM_LINES);
		Log.info(FileUtility.readFileContents(defnFile));
		FileUtility.deleteFile(Paths.get(defnFile));
	}

	@Test
	public void testDefnRFADSMode_WithDataTransformer() throws IOException {
		final int EXPECTED_NUM_LINES = 13;   // for 6 nodes = 6*2 + 1 option line

		String defnFile = createBasicHostSimDefinition("replayDb3-data-cond-eth.defn", Mode.RFADS_mode, 60, () -> createDataTransformerNode())
							.addOption(Option.Ethane)
							.writeFile();

		int numLines = FileUtility.readFileLinesToList(defnFile).size();

		Log.info(String.format("Defn file created at -> [%s]", defnFile));
		Log.info(String.format("Defn file : number of lines = %d", numLines));

		assertTrue(FileUtility.fileExists(defnFile));
		assertTrue(numLines==EXPECTED_NUM_LINES);
		Log.info(FileUtility.readFileContents(defnFile));
		FileUtility.deleteFile(Paths.get(defnFile));
	}

	private HostSimDefinition createBasicHostSimDefinition(String defnFileName, Mode mode, Integer delay, Supplier<List<HostSimNode>> nodeSupplier) {
		final String DB3_FILE_PATH = "C:\\temp\\surveyor.db3";
		anemNode = new HostSimDbReaderNode("amemometer_reader", DB3_FILE_PATH , HostSimNodeTarget.dbreader_anemometer_reader);
		gpsNode = new HostSimDbReaderNode("gps_reader", DB3_FILE_PATH , HostSimNodeTarget.dbreader_gps_reader);
		measNode = new HostSimDbReaderNode("measurement_reader", DB3_FILE_PATH , HostSimNodeTarget.dbreader_measurement_reader);
		mergerNode = new HostSimStreamMergerNode("stream_merger", HostSimNodeTarget.stream_merger_stream_merger).addInNode(anemNode).addInNode(gpsNode).addInNode(measNode);
		zmqSenderNode = new HostSimZmqSenderNode("zmq_sender", HostSimNodeTarget.zmqsender_zmq_sender, mode).setDelay(delay).addInNode(mergerNode);

		HostSimDefinition hostSimDefn = new HostSimDefinition(defnFileName)
			.addNode(anemNode)
			.addNode(gpsNode)
			.addNode(measNode);

		if (nodeSupplier != null) {
			nodeSupplier.get().forEach(node -> hostSimDefn.addNode(node));
		}

		hostSimDefn.addNode(mergerNode)
			.addNode(zmqSenderNode);

		return hostSimDefn;
	}

	private List<HostSimNode> createDataConditionerNode() {
		List<HostSimNode> nodesList = new ArrayList<HostSimNode>();
		nodesList.add(new HostSimDataConditionerNode("data_conditioner", 1000, 100, 1)
				.addConditionerMethod(ConditionerMethod.delete_c2h6)
				.addConditionerMethod(ConditionerMethod.set_c2h6_to_0)
				.addInNode(measNode));
		return nodesList;
	}

	private List<HostSimNode> createDataTransformerNode() {
		List<HostSimNode> nodesList = new ArrayList<HostSimNode>();
		nodesList.add(new HostSimDataTransformerNode("data_transformer", getHostSimInstructions())
				.addInNode(measNode));
		return nodesList;
	}

	private HostSimInstructions getHostSimInstructions() {
		HostSimInstructions instructions = new HostSimInstructions("UnitTest");
		instructions.addSelector(Selector.EveryM, 2000)
			.addAnemometerAction(Action.UpdateRandom, Anemometer.Column.WS_STATUS, "1","0.1")
			.addMeasurementAction(Action.Update, Measurement.Column.CH4, "numpy.float64(numpy.nan)")
			.addMeasurementAction(Action.InsertPeak, Measurement.Column.CH4, "5.5", "1", "0.01", "insert_peak_ampl_5_5_sigma_1_randomizer_1.log");
		return instructions;
	}
}