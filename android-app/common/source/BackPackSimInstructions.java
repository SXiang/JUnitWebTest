package common.source;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BackPackSimInstructions {

	private String filename;
	private List<Instruction> instructions;

	private BackPackSimInstructions(String filename) {
		this.filename = filename;
		this.instructions = new ArrayList<>();
	}

	public static BackPackSimInstructions newInstructionSet(String filename) {
		return new BackPackSimInstructions(filename);
	}

	public static interface Instruction {
	}

	public static class ReplaceInstruction implements Instruction {
		private String columnName;
		private String columnValue;

		public static String HEADER = "replace";

		public ReplaceInstruction(String colName, String colValue) {
			this.columnName = colName;
			this.columnValue = colValue;
		}

		public String getColumnName() {
			return columnName;
		}
		public void setColumnName(String columnName) {
			this.columnName = columnName;
		}
		public String getColumnValue() {
			return columnValue;
		}
		public void setColumnValue(String columnValue) {
			this.columnValue = columnValue;
		}

		@Override
		public String toString() {
			return String.format("%s = %s", columnName, columnValue);
		}
	}

	public static class DropInstruction implements Instruction {
		private String column;

		public static String HEADER = "drop";

		public DropInstruction(String colName) {
			this.column = colName;
		}

		public String getColumn() {
			return column;
		}

		public void setColumn(String column) {
			this.column = column;
		}

		@Override
		public String toString() {
			return String.format("%s = delete", column);
		}
	}

	public BackPackSimInstructions addInstruction(Instruction instruction) {
		if (instruction != null) {
			instructions.add(instruction);
		}

		return this;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String toFile() throws IOException {
		if (instructions.size()==0) {
			Log.warn("No instructions provided. Instructions file could NOT be created.");
			return null;
		}

		Path filePath = Paths.get(TestSetup.getSystemTempDirectory(),
				String.format("%s_%s.ini", this.getFilename(), TestSetup.getUUIDString()));
		List<String> replaceInsts = instructions.stream()
				.filter(i -> i.getClass().equals(ReplaceInstruction.class))
				.map(i -> i.toString())
				.collect(Collectors.toList());
		List<String> dropInsts = instructions.stream()
				.filter(i -> i.getClass().equals(DropInstruction.class))
				.map(i -> i.toString())
				.collect(Collectors.toList());

		List<String> fileContents = new ArrayList<>();
		if (replaceInsts != null && replaceInsts.size()>0) {
			fileContents.add(String.format("[%s]", ReplaceInstruction.HEADER));
			replaceInsts.stream()
				.forEach(i -> fileContents.add(i));
		}

		if (dropInsts != null && dropInsts.size()>0) {
			fileContents.add(String.format("[%s]", DropInstruction.HEADER));
			dropInsts.stream()
				.forEach(i -> fileContents.add(i));
		}

		FileUtility.writeToFile(filePath.toString(), fileContents.toArray(new String[fileContents.size()]));
		return filePath.toString();
	}
}