package common.source;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class BackPackSimDataTransformer {
	private final static String TRANSFORM_BACKPACKSIM_INPUTDATA_CMD = "Transform-BackPackSimulatorInputData.cmd";

	private String inputDataFile;
	private String inputDataFileBackup;
	private String outputDataFile;

	public static BackPackSimDataTransformer newDataTransformer() {
		return new BackPackSimDataTransformer();
	}

	public BackPackSimDataTransformer transformDataFileWithRevert(String inputDataFile, String instructionsFile, Boolean replaceInputFile, CheckedConsumer testMethods) throws Exception {
		Log.method("transformDataFileWithRevert", inputDataFile, instructionsFile, replaceInputFile);

		try {
			transformDataFile(inputDataFile, instructionsFile, replaceInputFile);
			testMethods.execute();
		} finally {
			revertInputFileToOriginal();
		}

		return this;
	}

	public BackPackSimDataTransformer cleanUp() {
		Log.method("cleanUp");
		if (!BaseHelper.isNullOrEmpty(outputDataFile)) {
			Log.info(String.format("Deleting tranformed data file -> %s", outputDataFile));
			if (FileUtility.fileExists(outputDataFile)) {
				FileUtility.deleteFile(Paths.get(outputDataFile));
			}
		}

		if (!BaseHelper.isNullOrEmpty(inputDataFileBackup)) {
			Log.info(String.format("Deleting input data bak file -> %s", inputDataFileBackup));
			if (FileUtility.fileExists(inputDataFileBackup)) {
				FileUtility.deleteFile(Paths.get(inputDataFileBackup));
			}
		}

		return this;
	}

	public String getInputDataBakFile() {
		return this.inputDataFileBackup;
	}

	public String getOutputDataFile() {
		return this.outputDataFile;
	}

	private void transformDataFile(String inputDataFile, String instructionsFile, Boolean replaceInputFile) throws IOException {
		Log.method("transformDataFile", inputDataFile, outputDataFile);

		this.inputDataFile = inputDataFile;
		String folder = Paths.get(inputDataFile).getParent().toString();
		String fileNameWithExt = Paths.get(inputDataFile).getFileName().toString();
		String newFileName = fileNameWithExt.substring(0, fileNameWithExt.indexOf(".")) + ".transformed";
		String outputDatFile = Paths.get(folder, newFileName+".dat").toString();

		String transformDataFileCmdFolder = TestSetup.getExecutionPath(TestSetup.getRootPath()) + "lib\\BackPackSim";
		String repoRootFolder = TestSetup.getRootPath();
		String transformDataFileCmd = TRANSFORM_BACKPACKSIM_INPUTDATA_CMD + String.format(" %s %s %s %s",
				"\"" + repoRootFolder + "\"", "\"" + inputDataFile + "\"", "\"" + outputDatFile + "\"", "\"" + instructionsFile + "\"");
		String command = "cd \"" + transformDataFileCmdFolder + "\" && " + transformDataFileCmd;
		Log.info("Executing transform input data file command. Command -> " + command);
		ProcessUtility.executeProcess(command, /* isShellCommand */ true, /* waitForExit */ true);

		this.outputDataFile = outputDatFile;

		if (replaceInputFile) {
			String bakFileName = fileNameWithExt.substring(0, fileNameWithExt.indexOf(".")) + ".bak";
			String bakDatFile = Paths.get(folder, bakFileName+".dat").toString();
			Log.info(String.format("Creating a copy of the input data file-'%s' at -> '%s'", inputDataFile, bakDatFile));
			Files.copy(Paths.get(inputDataFile), Paths.get(bakDatFile), StandardCopyOption.REPLACE_EXISTING);

			this.inputDataFileBackup = bakDatFile;

			Log.info(String.format("Replacing input data file-'%s' with transformed data file -> '%s'", inputDataFile, outputDatFile));
			FileUtility.copyFile(outputDatFile, inputDataFile);
		}
	}

	private void revertInputFileToOriginal() throws IOException {
		Log.method("revertInputFileToOriginal");
		if (!BaseHelper.isNullOrEmpty(inputDataFileBackup)) {
			Log.info(String.format("Reverting input data file to original. Copying '%s' -> '%s'", inputDataFileBackup, inputDataFile));
			FileUtility.copyFile(inputDataFileBackup, inputDataFile);
		}
	}
}
