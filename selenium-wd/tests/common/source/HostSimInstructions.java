package common.source;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HostSimInstructions {
	private String instructionName;

	private Type type;

	private Selector selector;
	private SelectorParams selectorParams;

	private Action action;
	private ActionParams actionParams;

	private HostSimInstructions nextInstruction;

	public HostSimInstructions(String name) {
		this.instructionName = name;
	}

	public enum Type {
		Selector ("Selector"),
		Action ("Action");

		private final String name;

		Type(String nm) {
			name = nm;
		}

		public String toString() {
			return this.name;
		}
	}

	public enum Selector {
		EveryM ("every"),
		EveryMK ("every");

		private final String name;

		Selector(String nm) {
			name = nm;
		}

		public String toString() {
			return this.name;
		}
	}

	public enum Action {
		Drop ("drop"),
		Update ("update"),
		UpdateRandom ("updateRandom"),
		UpdateFieldBy ("updateFieldBy"),
		UpdateFieldBit ("updateFieldBit"),
		InsertPeak ("insertPeak");

		private final String name;

		Action(String nm) {
			name = nm;
		}

		public String toString() {
			return this.name;
		}
	}

	public static class SelectorParams {
		private List<Integer> params;


		public SelectorParams(Integer... inputParams) {
			params = Arrays.asList(inputParams);
		}

		public List<Integer> getParams() {
			return params;
		}

		public void setParams(List<Integer> params) {
			this.params = params;
		}

		@Override
		public String toString() {
			if (params == null || params.size() == 0) {
				return "";
			}

			return String.join(",", params.stream().map(i -> String.valueOf(i)).collect(Collectors.toList()));
		}
	}

	public static class ActionParams {
		private String columnName;
		private List<String> valueParams;

		public ActionParams(String columnName, String... values) {
			this.columnName = columnName;
			this.valueParams = Arrays.asList(values);
		}

		public String getColumnName() {
			return columnName;
		}

		public void setColumnName(String columnName) {
			this.columnName = columnName;
		}

		public List<String> getValueParams() {
			return valueParams;
		}

		public void setValueParams(String... valueParams) {
			this.valueParams = Arrays.asList(valueParams);
		}

		@Override
		public String toString() {
			if (valueParams == null || valueParams.size() == 0) {
				return "";
			}

			return String.format("'%s',%s", columnName, String.join(",", valueParams));
		}
	}

	public static class Measurement {
		public enum Column {
			CAR_VEL_E ("CAR_VEL_E"),
			CAR_VEL_N ("CAR_VEL_N"),
			CH4 ("CH4"),
			co2_conc ("co2_conc"),
			HP_Delta_iCH4_Raw ("HP_Delta_iCH4_Raw"),
			EPOCH_TIME ("EPOCH_TIME"),
			GPS_FIT ("GPS_FIT"),
			GPS_ABS_LAT ("GPS_ABS_LAT"),
			GPS_ABS_LONG ("GPS_ABS_LONG"),
			h2o_conc_pct ("h2o_conc_pct"),
			species ("species"),
			ValveMask ("ValveMask"),
			WIND_DIR_SDEV ("WIND_DIR_SDEV"),
			WIND_N ("WIND_N"),
			WIND_E ("WIND_E"),
			WS_WIND_LAT ("WS_WIND_LAT"),
			WS_WIND_LON ("WS_WIND_LON"),
			CavityPressure ("CavityPressure"),
			CavityTemp ("CavityTemp"),
			DasTemp ("DasTemp"),
			MOBILE_FLOW ("MOBILE_FLOW"),
			PeripheralStatus ("PeripheralStatus"),
			AnalyzerStatus ("AnalyzerStatus"),
			WarmBoxTemp ("WarmBoxTemp"),
			C2H6 ("C2H6"),
			INST_STATUS ("INST_STATUS"),
			AnalyzerMode ("AnalyzerMode"),
			WS_ROTATION ("WS_ROTATION"),
			C2H4_PF ("C2H4_PF"),
			C2H6_nominal_sdev ("C2H6_nominal_sdev"),
			CHEM_DETECT ("CHEM_DETECT");

			private final String name;

			Column(String nm) {
				name = nm;
			}

			public String toString() {
				return this.name;
			}
		}
	}

	public static class Gps {
		public enum Column {
			EPOCH_TIME ("EPOCH_TIME"),
			GPS_ABS_LAT ("GPS_ABS_LAT"),
			GPS_ABS_LONG ("GPS_ABS_LONG"),
			GPS_FIT ("GPS_FIT"),
			GPS_TIME ("GPS_TIME");

			private final String name;

			Column(String nm) {
				name = nm;
			}

			public String toString() {
				return this.name;
			}
		}
	}

	public static class Anemometer {
		public enum Column {
			EPOCH_TIME ("EPOCH_TIME"),
			WS_STATUS ("WS_STATUS"),
			WS_WIND_LAT ("WS_WIND_LAT"),
			WS_WIND_LON ("WS_WIND_LON"),
			WS_INDEX ("WS_INDEX");

			private final String name;

			Column(String nm) {
				name = nm;
			}

			public String toString() {
				return this.name;
			}
		}
	}

	public HostSimInstructions addSelector(Selector selector, Integer... inputParams) {
		if (selector == Selector.EveryM) {
			if (inputParams.length != 1) {
				throw new IllegalArgumentException("For Selector.EveryM exactly one input parameter should be specified.");
			}
		} else if (selector == Selector.EveryMK) {
			if (inputParams.length != 2) {
				throw new IllegalArgumentException("For Selector.EveryMK exactly two input parameters should be specified.");
			}
		}

		HostSimInstructions instruction = new HostSimInstructions(this.getInstructionName());
		instruction.setSelector(selector);
		instruction.setSelectorParams(new SelectorParams(inputParams));
		instruction.setType(Type.Selector);
		this.setNextInstruction(instruction);
		return instruction;
	}

	public HostSimInstructions addMeasurementAction(Action action, HostSimInstructions.Measurement.Column column, String... inputParams) {
		return addAction(action, column.toString(), inputParams);
	}

	public HostSimInstructions addGpsAction(Action action, HostSimInstructions.Gps.Column column, String... inputParams) {
		return addAction(action, column.toString(), inputParams);
	}

	public HostSimInstructions addAnemometerAction(Action action, HostSimInstructions.Anemometer.Column column, String... inputParams) {
		return addAction(action, column.toString(), inputParams);
	}

	private HostSimInstructions addAction(Action action, String column, String... inputParams) {
		HostSimInstructions instruction = new HostSimInstructions(this.getInstructionName());
		String[] inputArray = Arrays.asList(inputParams).toArray(new String[inputParams.length]);
		if (action == Action.Drop) {
			if (inputParams.length != 0) {
				throw new IllegalArgumentException("For Action.Drop no input parameter should be specified.");
			}
		} else if (action == Action.Update || action == Action.UpdateFieldBy) {
			if (inputParams.length != 1) {
				throw new IllegalArgumentException("For Action.Update or Action.UpdateFieldBy exactly one input parameter should be specified.");
			}
		} else if (action == Action.UpdateRandom || action == Action.UpdateFieldBit) {
			if (inputParams.length != 2) {
				throw new IllegalArgumentException("For Action.UpdateRandom or Action.UpdateFieldBit exactly two input parameters should be specified.");
			}
		} else if (action == Action.InsertPeak) {
			if (inputParams.length != 4) {
				throw new IllegalArgumentException("For Action.InsertPeak exactly four input parameters should be specified.");
			}

			// wrap quotes on string type.
			inputArray[3] = String.format("'%s'", inputArray[3]);
		}

		instruction = new HostSimInstructions(this.getInstructionName());
		instruction.setAction(action);
		instruction.setActionParams(new ActionParams(column.toString(), inputArray));
		instruction.setType(Type.Action);
		this.setNextInstruction(instruction);
		return instruction;
	}


	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Selector getSelector() {
		return selector;
	}

	public void setSelector(Selector selector) {
		this.selector = selector;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public SelectorParams getSelectorParams() {
		return selectorParams;
	}

	public void setSelectorParams(SelectorParams selectorParams) {
		this.selectorParams = selectorParams;
	}

	public ActionParams getActionParams() {
		return actionParams;
	}

	public void setActionParams(ActionParams actionParams) {
		this.actionParams = actionParams;
	}

	public HostSimInstructions getNextInstruction() {
		return nextInstruction;
	}

	public void setNextInstruction(HostSimInstructions nextInstruction) {
		this.nextInstruction = nextInstruction;
	}

	public String getInstructionName() {
		return instructionName;
	}

	public void setInstructionName(String instructionName) {
		this.instructionName = instructionName;
	}

	public String createFile() throws IOException {
		Path filePath = Paths.get(TestSetup.getSystemTempDirectory(),
				String.format("%s_%s.ins", this.getInstructionName(), TestSetup.getUUIDString()));
		List<String> fileContents = new ArrayList<String>();
		HostSimInstructions currInst = this.getNextInstruction();
		while (currInst != null) {
			fileContents.add(currInst.toString());
			currInst = currInst.getNextInstruction();
		}

		FileUtility.writeToFile(filePath.toString(), fileContents.toArray(new String[fileContents.size()]));
		return filePath.toString();
	}

	@Override
	public String toString() {
		if (getType() == Type.Selector) {
			return String.format("%s(%s)", getSelector(), getSelectorParams());
		} else if (getType() == Type.Action) {
			return String.format("%s(%s)", getAction(), getActionParams());
		}

		return "";
	}
}