package common.source;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FixedWidthFileUtility {
	public static List<Map<String, String>> parseFileLines(String filePath, Integer colWidth) throws IOException {
		String lineText = "";
		List<Map<String, String>> rowValues = new ArrayList<Map<String, String>>();
		BufferedReader buffReader = new BufferedReader(new FileReader(filePath));
		try {
			Map<Integer, String> columnHeaders = null;
			if ((lineText = buffReader.readLine()) != null) {
				columnHeaders = splitFixedWidthLine(lineText, colWidth);
			}

			final Map<Integer, String> colHeaders = columnHeaders;
			final Map<String, String> colValues = new HashMap<String, String>();
			columnHeaders.entrySet().stream()
				.forEach(e -> colValues.put(colHeaders.get(e.getKey()), e.getValue()));
			rowValues.add(colValues);

			while ((lineText = buffReader.readLine()) != null) {
				final Map<String, String> colValues2 = new HashMap<String, String>();
				Map<Integer, String> values = splitFixedWidthLine(lineText, colWidth);
				values.entrySet().stream()
					.forEach(e -> colValues2.put(colHeaders.get(e.getKey()), e.getValue()));
				rowValues.add(colValues2);
			}
		} finally {
			buffReader.close();
		}

		return rowValues;
	}

	private static Map<Integer, String> splitFixedWidthLine(String lineText, Integer colWidth) {
		int len = lineText.length();
		if (len % colWidth != 0) {
			throw new RuntimeException(String.format("Found line of incorrect width. Line width=%d. Line text=%s", len, lineText));
		}

		int startIdx = 0;
		int endIdx = colWidth;
		int idx = 0;
		Map<Integer, String> map = new HashMap<Integer, String>();
		while (endIdx <= len) {
			String value = lineText.substring(startIdx, endIdx).trim();
			startIdx += colWidth;
			endIdx += colWidth;
			map.put(idx, value);
			idx++;
		}

		return map;
	}
}