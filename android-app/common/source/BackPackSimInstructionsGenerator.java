package common.source;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class BackPackSimInstructionsGenerator {
	public static class Arguments {
		public static void check(String filename) {
			if (BaseHelper.isNullOrEmpty(filename)) {
				throw new IllegalArgumentException("'filename' must be specified.");
			}
		}

		public static void check(Map<String, String> replaceColValuesMap) {
			if (replaceColValuesMap == null || replaceColValuesMap.size() == 0) {
				throw new IllegalArgumentException("'replaceColValuesMap' cannot be NULL or empty.");
			}
		}

		public static void check(List<String> dropColumns) {
			if (dropColumns == null || dropColumns.size() == 0) {
				throw new IllegalArgumentException("'dropColumns' cannot be NULL or empty.");
			}
		}
	}


	public static String generateInstructionFileForReplace(String filename, Map<String, String> replaceColValuesMap) throws IOException {
		Arguments.check(filename);
		Arguments.check(replaceColValuesMap);
		BackPackSimInstructions instructions = BackPackSimInstructions.newInstructionSet(filename);
		addReplaceInstructions(replaceColValuesMap, instructions);
		return instructions.toFile();
	}

	public static String generateInstructionFileForDrop(String filename, List<String> dropColumns) throws IOException {
		Arguments.check(filename);
		Arguments.check(dropColumns);
		BackPackSimInstructions instructions = BackPackSimInstructions.newInstructionSet(filename);
		addDropInstructions(dropColumns, instructions);
		return instructions.toFile();
	}

	public static String generateInstructionFileForDropReplace(String filename, Map<String, String> replaceColValuesMap, List<String> dropColumns) throws IOException {
		Arguments.check(filename);
		Arguments.check(replaceColValuesMap);
		Arguments.check(dropColumns);
		BackPackSimInstructions instructions = BackPackSimInstructions.newInstructionSet(filename);
		addReplaceInstructions(replaceColValuesMap, instructions);
		addDropInstructions(dropColumns, instructions);
		return instructions.toFile();
	}

	private static void addReplaceInstructions(Map<String, String> replaceColValuesMap, BackPackSimInstructions instructions) {
		replaceColValuesMap.entrySet().stream()
			.map(es -> new BackPackSimInstructions.ReplaceInstruction(es.getKey(), es.getValue()))
			.forEach(ins -> instructions.addInstruction(ins));
	}

	private static void addDropInstructions(List<String> dropColumns, BackPackSimInstructions instructions) {
		dropColumns.stream()
			.map(c -> new BackPackSimInstructions.DropInstruction(c))
			.forEach(ins -> instructions.addInstruction(ins));
	}
}