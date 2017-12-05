package surveyor.parsers.source;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import common.source.RegexUtility;

public class SSRSViewNamesParser {
	public enum ViewNamesParserAlgorithm {
		BetweenFixedStrings,
		BetweenStartStringAndFooter,
		All
	}

	public List<String> parse(String parseString, ViewNamesParserAlgorithm algorithm) throws IOException {
		if (algorithm == ViewNamesParserAlgorithm.BetweenFixedStrings) {
			return applyBetweenFixedStringsAlgorithm(parseString);
		} else if (algorithm == ViewNamesParserAlgorithm.BetweenStartStringAndFooter) {
			return applyBetweenStartStringAndFooterAlgorithm(parseString);
		} else if (algorithm == ViewNamesParserAlgorithm.All) {
			List<String> matchingList = applyBetweenFixedStringsAlgorithm(parseString);
			if (matchingList == null || matchingList.size() == 0) {
				matchingList = applyBetweenStartStringAndFooterAlgorithm(parseString);
			}
			return matchingList;
		}
		return null;
	}

	private List<String> applyBetweenFixedStringsAlgorithm(String parseString) throws IOException {
		List<String> actualViewNamesList = new ArrayList<String>();
		String viewTable = RegexUtility.getStringInBetween(parseString, "Selected Views", "View Table");
		InputStream inputStream = new ByteArrayInputStream(viewTable.getBytes());
		BufferedReader bufferReader = new BufferedReader(new InputStreamReader(inputStream));
		String line = null;
		try {
			while ((line = bufferReader.readLine()) != null) {
				if (line.matches(RegexUtility.VIEWS_TABLE_LINE_REGEX_PATTERN)) {
					String[] split = line.split("\\s+");
					String viewName = line.replace(split[split.length - 1], "").trim();
					actualViewNamesList.add(viewName);
				}
			}

		} finally {
			bufferReader.close();
		}
		return actualViewNamesList;
	}

	private List<String> applyBetweenStartStringAndFooterAlgorithm(String parseString) throws IOException {
		List<String> actualViewNamesList = new ArrayList<String>();
		InputStream inputStream = new ByteArrayInputStream(parseString.getBytes());
		BufferedReader bufferReader = new BufferedReader(new InputStreamReader(inputStream));
		Boolean startOfTableDetected = false;
		String line = null;
		try {
			while ((line = bufferReader.readLine()) != null) {
				if (!startOfTableDetected && line.startsWith("Selected Views")) {
					startOfTableDetected = true;
					continue;
				}

				if (startOfTableDetected) {
					if (line.trim().matches(RegexUtility.SSRS_PDF_PAGE_FOOTER_PATTERN)) {
						break;
					}

					if (line.matches(RegexUtility.VIEWS_TABLE_LINE_REGEX_PATTERN)) {
						String[] split = line.split("\\s+");
						String viewName = line.replace(split[split.length - 1], "").trim();
						actualViewNamesList.add(viewName);
					}
				}
			}

		} finally {
			bufferReader.close();
		}
		return actualViewNamesList;
	}
}