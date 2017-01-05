package common.source;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import static org.junit.Assert.assertTrue;

public class XmlUtility {
	public HashMap<String, String> getNodeValuesByTagNames(String xmlFile, List<String> tagNames)
			throws ParserConfigurationException, SAXException, IOException {
		HashMap<String, String> tagValues = new HashMap<String, String>();

		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
		Document document = documentBuilder.parse(xmlFile);

		for (String tag : tagNames) {
			if (!BaseHelper.isNullOrEmpty(tag)) {
				NodeList nodeList = document.getElementsByTagName(tag);
				tagValues.put(tag, nodeList.item(0).getTextContent());
			}
		}

		return tagValues;
	}

	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
		XmlUtility xmlUtil = new XmlUtility();
		List<String> tagNames = Arrays.asList("EnvironmentUrl", "EnvironmentDBName", "EnvironmentBuildVersion",
				"TestBinaryMajor", "TestBinaryMinor", "TestBinaryBuildNumber", "TestBinaryGitHash", "TestBranch");
		HashMap<String, String> nodeValues = xmlUtil.getNodeValuesByTagNames(TestSetup.getRootPath()+ "\\selenium-wd\\manifest.xml", tagNames);
		Log.info(String.format("Match String Map is : %s", LogHelper.mapToString(nodeValues)));
		for (String key : nodeValues.keySet()) {
			assertTrue(!BaseHelper.isNullOrEmpty(nodeValues.get(key)));
		}
	}
}
