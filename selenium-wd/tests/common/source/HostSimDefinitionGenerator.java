package common.source;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import common.source.HostSimDefinition.Option;
import common.source.HostSimInstructions.Action;
import common.source.HostSimInstructions.Measurement;
import common.source.HostSimInstructions.Selector;
import common.source.HostSimNode.HostSimNodeTarget;
import common.source.HostSimZmqSenderNode.Mode;

public class HostSimDefinitionGenerator {
	private static final String DEFAULT_SIGMA = "0.16";
	private static final String DEFAULT_CH4_INSERT_PEAK_STEP = "0.01";
	private static final String DEFAULT_C2H6_INSERT_PEAK_STEP = "0.1";
	private static final Integer DEFAULT_ETH_DELAY = 60;
	private static final Integer DEFAULT_METH_DELAY = 30;
	private static final Integer DEFAULT_DATA_TRANSFORMER_AFTER_VALUE = 100;

	private HostSimNode anemNode = null;
	private HostSimNode gpsNode = null;
	private HostSimNode measNode = null;
	private HostSimNode mergerNode = null;
	private HostSimNode zmqSenderNode = null;

	public String generateDefaultMethDefinitionForMultiplePeaks(String[] ch4Values) throws IOException {
		String[] sigmaValues = getDefaultSigmaValues(ch4Values.length);
		return generateDefaultDefinitionForMultiplePeaks(ch4Values, null /*c2h6Values*/, sigmaValues, Mode.FEDS_mode, DEFAULT_METH_DELAY);
	}

	public String generateDefaultEthDefinitionForMultiplePeaks(String[] ch4Values) throws IOException {
		String[] sigmaValues = getDefaultSigmaValues(ch4Values.length);
		return generateDefaultDefinitionForMultiplePeaks(ch4Values, null /*c2h6Values*/, sigmaValues, Mode.RFADS_mode, DEFAULT_ETH_DELAY);
	}

	public String generateDefaultEthDefinitionForMultiplePeaks(String[] ch4Values, String[] c2h6Values) throws IOException {
		String[] sigmaValues = getDefaultSigmaValues(ch4Values.length);
		return generateDefaultDefinitionForMultiplePeaks(ch4Values, c2h6Values, sigmaValues, Mode.RFADS_mode, DEFAULT_ETH_DELAY);
	}

	public String generateDefaultDefinitionForMultiplePeaks(String[] ch4Values, String[] c2h6Values, String[] sigmaValues, Mode mode, Integer delay) throws IOException {
		HostSimDefinition hostSimDefinition = createBasicHostSimDefinition(String.format("replay-db3-%s-mult-peaks.defn", mode), mode, delay,
				() -> createDefaultMultiplePeakDataTransformerNodes(ch4Values, c2h6Values, sigmaValues));

		if (mode == Mode.FEDS_mode) {
			hostSimDefinition.addOption(Option.iCH4);
		} else if (mode == Mode.RFADS_mode) {
			hostSimDefinition.addOption(Option.Ethane);
		}

		return hostSimDefinition.writeFile();
	}

	private HostSimDefinition createBasicHostSimDefinition(String defnFileName, Mode mode, Integer delay, Supplier<List<HostSimNode>> measNodeSupplier) {
		final String DB3_FILE_PATH = "%DB3_FILE_PATH%";
		anemNode = new HostSimDbReaderNode("amemometer_reader", DB3_FILE_PATH , HostSimNodeTarget.dbreader_anemometer_reader);
		gpsNode = new HostSimDbReaderNode("gps_reader", DB3_FILE_PATH , HostSimNodeTarget.dbreader_gps_reader);
		measNode = new HostSimDbReaderNode("measurement_reader", DB3_FILE_PATH , HostSimNodeTarget.dbreader_measurement_reader);
		mergerNode = new HostSimStreamMergerNode("stream_merger", HostSimNodeTarget.stream_merger_stream_merger).addInNode(anemNode).addInNode(gpsNode);
		zmqSenderNode = new HostSimZmqSenderNode("zmq_sender", HostSimNodeTarget.zmqsender_zmq_sender, mode).setDelay(delay).addInNode(mergerNode);

		HostSimDefinition hostSimDefn = new HostSimDefinition(defnFileName)
			.addNode(anemNode)
			.addNode(gpsNode)
			.addNode(measNode);

		HostSimNode lastMeasNode = measNode;

		if (measNodeSupplier != null) {
			List<HostSimNode> suppliedMeasNodes = measNodeSupplier.get();
			suppliedMeasNodes.forEach(node -> hostSimDefn.addNode(node));
			lastMeasNode = suppliedMeasNodes.get(suppliedMeasNodes.size()-1);
		}

		mergerNode.addInNode(lastMeasNode);

		hostSimDefn.addNode(mergerNode)
			.addNode(zmqSenderNode);

		return hostSimDefn;
	}

	private String[] getDefaultSigmaValues(Integer arrayLength) {
		String[] sigmaValues = new String[arrayLength];
		for (int i = 0; i < arrayLength; i++) {
			sigmaValues[i] = DEFAULT_SIGMA;
		}

		return sigmaValues;
	}

	private HostSimNode createDefaultDataTransformerNode(String ch4Value, String c2h6Value, String sigmaValue, Integer nodeIndex) {
		return new HostSimDataTransformerNode(String.format("data_transformer_%d", nodeIndex), getDefaultInsertPeakHostSimInstructions(ch4Value, c2h6Value, sigmaValue));
	}

	private List<HostSimNode> createDefaultMultiplePeakDataTransformerNodes(String[] ch4Values, String[] c2h6Values, String[] sigmaValues) {
		List<HostSimNode> nodes = new ArrayList<HostSimNode>();
		for (int i = 0; i < ch4Values.length; i++) {
			String c2h6Value = null;
			if (c2h6Values != null && c2h6Values.length > i) {
				c2h6Value = c2h6Values[i];
			}

			HostSimDataTransformerNode hostSimNode = (HostSimDataTransformerNode)createDefaultDataTransformerNode(ch4Values[i], c2h6Value, sigmaValues[i], i);
			if (i == 0) {
				hostSimNode.addInNode(measNode);
				hostSimNode.setAfter(DEFAULT_DATA_TRANSFORMER_AFTER_VALUE);
			} else {
				hostSimNode.addInNode(nodes.get(i-1));
				hostSimNode.setAfter(DEFAULT_DATA_TRANSFORMER_AFTER_VALUE + (i*DEFAULT_DATA_TRANSFORMER_AFTER_VALUE));
			}

			nodes.add(hostSimNode);
		}

		return nodes;
	}

	private HostSimInstructions getDefaultInsertPeakHostSimInstructions(String ch4Value, String c2h6Value, String sigmaValue) {
		HostSimInstructions instructions = new HostSimInstructions("HostSimInstruction");
		String randomNumString = TestContext.INSTANCE.getTestSetup().getNewFixedSizeRandomNumber(8);
		if (BaseHelper.isNullOrEmpty(c2h6Value)) {
			instructions.addSelector(Selector.EveryMK, 1000000, 2000)
				.addMeasurementAction(Action.InsertPeak, Measurement.Column.CH4, ch4Value, sigmaValue, DEFAULT_CH4_INSERT_PEAK_STEP,
					String.format("insert_peak_%s_sigma_%s_randomizer_%s.log", ch4Value, sigmaValue, randomNumString));
		} else {
			instructions.addSelector(Selector.EveryMK, 1000000, 2000)
				.addMeasurementAction(Action.InsertPeak, Measurement.Column.CH4, ch4Value, sigmaValue, DEFAULT_CH4_INSERT_PEAK_STEP,
						String.format("insert_peak_%s_sigma_%s_randomizer_%s.log", ch4Value, sigmaValue, randomNumString))
				.addMeasurementAction(Action.InsertPeak, Measurement.Column.C2H6, c2h6Value, sigmaValue, DEFAULT_C2H6_INSERT_PEAK_STEP,
					String.format("insert_peak_c2h6_%s_sigma_%s_randomizer_%s.log", c2h6Value, sigmaValue, randomNumString));
		}

		return instructions;
	}
}