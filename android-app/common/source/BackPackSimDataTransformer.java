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

	private BackPackSimDataTransformer() {
	}

	public static BackPackSimDataTransformer newDataTransformer(String inputDataFile) throws IOException {
		return new BackPackSimDataTransformer().setInputDataFile(inputDataFile).init();
	}

	public BackPackSimDataTransformer transformDataFileWithRevert(String instructionsFile, Boolean replaceInputFile, CheckedConsumer testMethods) throws Exception {
		Log.method("transformDataFileWithRevert", instructionsFile, replaceInputFile);
		return transformDataFileWithRevertInternal(instructionsFile, replaceInputFile, false /*cleanUpAfterTransform*/, testMethods);
	}

	public BackPackSimDataTransformer transformDataFileWithRevertAndClean(String instructionsFile, Boolean replaceInputFile, CheckedConsumer testMethods) throws Exception {
		Log.method("transformDataFileWithRevertAndClean", instructionsFile, replaceInputFile);
		return transformDataFileWithRevertInternal(instructionsFile, replaceInputFile, true /*cleanUpAfterTransform*/, testMethods);
	}

	private BackPackSimDataTransformer transformDataFileWithRevertInternal(String instructionsFile,
			Boolean replaceInputFile, Boolean cleanUpAfterTransform, CheckedConsumer testMethods) throws IOException, Exception {
		try {
			transformDataFile(instructionsFile, replaceInputFile);
			testMethods.execute();
		} finally {
			revertInputFileToOriginal();
			if (cleanUpAfterTransform) {
				this.cleanUp();
			}
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

	public BackPackSimDataTransformer init() throws IOException {
		Log.method("init");
		String folder = Paths.get(inputDataFile).getParent().toString();
		String fileNameWithExt = Paths.get(inputDataFile).getFileName().toString();
		revertInputFileToBakFileIfExists(folder, fileNameWithExt);
		createBackupFile(folder, fileNameWithExt);
		return this;
	}

	public BackPackSimDataTransformer setInputDataFile(String value) {
		this.inputDataFile = value;
		return this;
	}

	private void createBackupFile(String folder, String fileNameWithExt) throws IOException {
		Log.method("createBackupFile", folder, fileNameWithExt);
		String bakFileName = fileNameWithExt.substring(0, fileNameWithExt.indexOf(".")) + ".bak";
		String bakDatFile = Paths.get(folder, bakFileName+".dat").toString();
		Log.info(String.format("Creating a copy of the input data file-'%s' at -> '%s'", inputDataFile, bakDatFile));
		Files.copy(Paths.get(inputDataFile), Paths.get(bakDatFile), StandardCopyOption.REPLACE_EXISTING);

		this.inputDataFileBackup = bakDatFile;
	}

	private void replaceInputWithOutputFile() throws IOException {
		Log.method("replaceInputWithOutputFile");
		Log.info(String.format("Replacing input data file-'%s' with transformed data file -> '%s'", inputDataFile, outputDataFile));
		FileUtility.copyFile(outputDataFile, inputDataFile);
	}

	private void revertInputFileToOriginal() throws IOException {
		Log.method("revertInputFileToOriginal");
		if (!BaseHelper.isNullOrEmpty(inputDataFileBackup)) {
			Log.info(String.format("Reverting input data file to original. Copying '%s' -> '%s'", inputDataFileBackup, inputDataFile));
			FileUtility.copyFile(inputDataFileBackup, inputDataFile);
		}
	}

	private void revertInputFileToBakFileIfExists(String folder, String fileNameWithExt) throws IOException {
		Log.method("revertInputFileToBakFileIfExists", folder, fileNameWithExt);
		String bakFileName = fileNameWithExt.substring(0, fileNameWithExt.indexOf(".")) + ".bak";
		String bakDatFile = Paths.get(folder, bakFileName+".dat").toString();
		if (FileUtility.fileExists(bakDatFile)) {
			Log.info(String.format("Detected .bak file. Copying .bak file - '%s' to input data file -> '%s'", bakDatFile, inputDataFile));
			Files.copy(Paths.get(bakDatFile), Paths.get(inputDataFile), StandardCopyOption.REPLACE_EXISTING);
		}
	}

	private void transformDataFile(String instructionsFile, Boolean replaceInputFile) throws IOException {
		Log.method("transformDataFile", instructionsFile, replaceInputFile);

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
			replaceInputWithOutputFile();
		}
	}
}