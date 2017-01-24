package surveyor.parsers.source;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import common.source.BaseHelper;
import common.source.Log;
import common.source.LogHelper;
import common.source.RegexUtility;

public class SSRSIsotopicAnalysisTableParser {

	public List<String> parse(String parseString, String columnSeperator) throws IOException {
		List<String> reportIsotopicList = new ArrayList<String>();
		InputStream inputStream = new ByteArrayInputStream(parseString.getBytes());
		BufferedReader bufferReader = new BufferedReader(new InputStreamReader(inputStream));
		String line = null;
		try {
			Log.info("Matching lines to check if they are table rows");
			while ((line = bufferReader.readLine()) != null) {
				if (!line.trim().matches(RegexUtility.INDICATION_TABLE_LINE_REGEX_PATTERN)) {
					List<String> matchingGroups = RegexUtility.getMatchingGroups(line.trim(),
								RegexUtility.ISOTOPIC_ANALYSIS_TABLE_LINE_REGEX_GROUP_PATTERN);
					if (matchingGroups != null && matchingGroups.size() > 0) {
						// If column seperator is provided upon match build the line using seperator.
						Log.info("Matched line as a table row!");
						if (!BaseHelper.isNullOrEmpty(columnSeperator)) {
							Log.info(String.format("Column Groups are : %s", LogHelper.strListToString(matchingGroups)));
							StringBuffer buffer = new StringBuffer();
							if (matchingGroups.size() > 1) {
								for (int i = 1; i < matchingGroups.size(); i++) {
									buffer.append(matchingGroups.get(i).trim());
									if (i < matchingGroups.size() -1) {
										buffer.append(columnSeperator);
									}
								}
								line = buffer.toString();
							}
						} else {
							line = matchingGroups.get(0);
							line = line.replaceAll(" +", " ").trim();
						}
						reportIsotopicList.add(line);
					}
				}
			}

			Log.info(String.format("ReportIsotopic ArrayList Values : %s", LogHelper.strListToString(reportIsotopicList)));
		} finally {
			bufferReader.close();
		}

		return reportIsotopicList;
	}

	public List<String[]> parseAsTable(String parseString, String columnSeperator) throws IOException {
		List<String[]> outputList = new ArrayList<String[]>();
		List<String> resultList = parse(parseString, columnSeperator);
		if (resultList != null && resultList.size() > 0) {
			resultList.forEach(e -> {
				List<String> split = RegexUtility.split(e, columnSeperator);
				outputList.add(split.toArray(new String[split.size()]));
			});
		}
		return outputList;
	}
}
