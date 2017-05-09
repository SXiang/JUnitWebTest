package common.source;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class HostSimDefinition {
	private List<Option> options;
	private List<HostSimNode> nodes;
	private String fileName;

	public static enum Option {
		iCH4 ("iCH4"),
		Ethane ("Ethane");

		private final String name;

		Option(String nm) {
			name = nm;
		}

		public String toString() {
			return this.name;
		}
	}

	public HostSimDefinition(String fileName) {
		this.options = new ArrayList<Option>();
		this.nodes = new ArrayList<HostSimNode>();
		this.fileName = fileName;
	}

	public HostSimDefinition addOption(Option option) {
		if (this.getOptions().size() > 0) {
			throw new IllegalArgumentException("Only 1 option is currently supported. One option has already been added.");
		}

		this.getOptions().add(option);
		return this;
	}

	public HostSimDefinition addNode(HostSimNode node) {
		if (node != null) {
			this.getNodes().add(node);
		}
		return this;
	}

	public List<Option> getOptions() {
		return options;
	}

	public List<HostSimNode> getNodes() {
		return nodes;
	}

	public String getFileName() {
		return fileName;
	}

	public String writeFile() throws IOException {
		Path filePath = Paths.get(TestSetup.getSystemTempDirectory(),
				String.format("%s_%s", TestSetup.getUUIDString(), this.getFileName()));
		FileUtility.writeToFile(filePath.toString(), this.toString());
		return filePath.toString();
	}


	@Override
	public String toString() {
		String optionString = getOptionString();
		StringBuilder nodeString = new StringBuilder();
		for (HostSimNode hostSimNode : this.getNodes()) {
			nodeString.append(hostSimNode.toString() + BaseHelper.getLineSeperator());
		}

		if (!BaseHelper.isNullOrEmpty(optionString)) {
			return optionString + BaseHelper.getLineSeperator() + nodeString.toString();
		}

		return nodeString.toString();
	}

	private String getOptionString() {
		String optionString = null;

		if (getOptions()!=null && getOptions().size() > 0) {
			if (getOptions().get(0) == Option.iCH4) {
				optionString = "options iCH4=True,Ethane=False";
			} else if (getOptions().get(0) == Option.Ethane) {
				optionString = "options iCH4=False,Ethane=True";
			}
		}

		return optionString;
	}
}