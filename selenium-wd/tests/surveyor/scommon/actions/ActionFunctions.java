package surveyor.scommon.actions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import common.source.FileUtility;
import common.source.HostSimDefinitionGenerator;
import common.source.RegexUtility;
import common.source.TestContext;
import common.source.TestSetup;
import surveyor.scommon.source.AnalyzerSerialNumberPool;

public class ActionFunctions {
	public String GenerateRandomEmail(Integer size) {
		String emailPart = "@email.com";
		Integer genSize = size - emailPart.length();
		return String.format("%s%s", TestContext.INSTANCE.getTestSetup().getFixedSizePseudoRandomString(genSize), emailPart);
	}

	public String GenerateRandomString(Integer size) {
		return TestContext.INSTANCE.getTestSetup().getFixedSizePseudoRandomString(size);
	}

	public String GenerateRandomNumber(Integer size) {
		return TestContext.INSTANCE.getTestSetup().getFixedSizeRandomNumber(size);
	}

	public String GetAnalyzerSerialNumberFromPool(Integer index) {
		if (index < 0) {
			return AnalyzerSerialNumberPool.INSTANCE.fetchNext();
		}

		return AnalyzerSerialNumberPool.INSTANCE.getAtIndex(index);
	}

	public String GenerateDefnWithMultipleEthPeaks(String ch4Values) throws IOException {
		List<String> listCh4Values = RegexUtility.split(ch4Values, RegexUtility.VERTICAL_BAR_SPLIT_REGEX_PATTERN);
		String defnFile = new HostSimDefinitionGenerator().generateDefaultEthDefinitionForMultiplePeaks(listCh4Values.toArray(new String[listCh4Values.size()]));
		String defnFilename = Paths.get(defnFile).getFileName().toString();
		String defnFolder = TestSetup.getExecutionPath(TestSetup.getRootPath()) + "data" + File.separator + "defn";
		String defnFullPath = Paths.get(defnFolder, defnFilename).toString();
		Files.copy(Paths.get(defnFile), Paths.get(defnFullPath));
		FileUtility.deleteFile(Paths.get(defnFile));
		return defnFilename;
	}

	public String GenerateDefnWithMultipleEthNotNaturalGasPeaks(String ch4C2H6Values) throws IOException {
		List<String> listCh4C2H6Values = RegexUtility.split(ch4C2H6Values, RegexUtility.VERTICAL_BAR_SPLIT_REGEX_PATTERN);
		List<String> listCh4Values = listCh4C2H6Values.stream()
				.map(e -> RegexUtility.split(e, RegexUtility.SEMI_COLON_SPLIT_REGEX_PATTERN).get(0))
				.collect(Collectors.toList());
		List<String> listC2H6Values = listCh4C2H6Values.stream()
				.map(e -> RegexUtility.split(e, RegexUtility.SEMI_COLON_SPLIT_REGEX_PATTERN).get(1))
				.collect(Collectors.toList());
		String defnFile = new HostSimDefinitionGenerator().generateDefaultEthDefinitionForMultiplePeaks(
				listCh4Values.toArray(new String[listCh4Values.size()]), listC2H6Values.toArray(new String[listC2H6Values.size()]));
		String defnFilename = Paths.get(defnFile).getFileName().toString();
		String defnFolder = TestSetup.getExecutionPath(TestSetup.getRootPath()) + "data" + File.separator + "defn";
		String defnFullPath = Paths.get(defnFolder, defnFilename).toString();
		Files.copy(Paths.get(defnFile), Paths.get(defnFullPath));
		FileUtility.deleteFile(Paths.get(defnFile));
		return defnFilename;
	}
}
